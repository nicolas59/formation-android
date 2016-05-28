package android.demo.nro.fr.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberService extends Service {

    private static final String TAG = PrimeNumberService.class.getName();

    private boolean mStarted = true;

    private boolean mDestroy = false;

    private List<Long> primeNumbers = new ArrayList<>();

    private Object lock = new Object();


    public class PrimeNumberBinder extends Binder {

        public Long[] getPrimeNumbersCalculated(){
            synchronized (lock) {
                return primeNumbers.toArray(new Long[primeNumbers.size()]);
            }
        }

        public void stop(){
            mStarted = false;
        }

        public void start(){
            mStarted = true;
        }

        public boolean isStarted(){
            return mStarted;
        }
    }

    private PrimeNumberBinder mBinder;

    public PrimeNumberService() {
        this.mBinder = new PrimeNumberBinder();
    }

    public void notifyNewPrimeNumberFound(long primeNumber){
        Intent intent = new Intent();
        intent.setAction("fr.nro.primenummber");
        intent.putExtra("primenumber", primeNumber);
        //sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }



    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: Service demarré");
        super.onCreate();
        Thread thread = new Thread(){

            private boolean isPrime(long currentNumber){
                int numberDivide = 0;
                for(int pos = 0; pos < primeNumbers.size() && (primeNumbers.get(pos) <= Math.sqrt(currentNumber)) && (numberDivide == 0); pos++){
                    if(currentNumber % primeNumbers.get(pos) == 0) {
                        numberDivide++;
                    }
                }
                return numberDivide == 0;
            }

            @Override
            public void run() {
                long currentNumber = 2L;
                while(!mDestroy){
                  if(mStarted) {
                      synchronized (lock) {
                          if (isPrime(currentNumber)) {
                              primeNumbers.add(currentNumber);
                              //notification
                              notifyNewPrimeNumberFound(currentNumber);
                          }
                      }
                      currentNumber++;
                  }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Log.d(TAG, e.getMessage());
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }
}
