package android.demo.nro.fr.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private BroadcastReceiver mBroadcastReceiver;

    private GridView mGridView;

    private TextView mTextView;

    private long mCounter;

    private PrimeNumberService.PrimeNumberBinder mPrimeNumberBinder;

    private ServiceConnection mConnexion = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            mPrimeNumberBinder = ((PrimeNumberService.PrimeNumberBinder)service);

            List<Long> primeNumbers = Arrays.asList(mPrimeNumberBinder.getPrimeNumbersCalculated());
            ((ArrayAdapter)mGridView.getAdapter()).addAll(primeNumbers);
            mCounter = primeNumbers.size();
            mTextView.setText(String.valueOf(mCounter));
        }

        // Se déclenche dès que le service est déconnecté
        public void onServiceDisconnected(ComponentName className) {
            mPrimeNumberBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView)findViewById(R.id.gridView);
        mTextView = (TextView)findViewById(R.id.counter);


    }

    public void pressStateButton(View button){
        if(this.mPrimeNumberBinder.isStarted()){
            this.mPrimeNumberBinder.stop();
            ((Button)button).setText("Reprendre");
        }else{
            ((Button)button).setText("Arreter");
            this.mPrimeNumberBinder.start();

        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();

        Intent intent = new Intent(this, PrimeNumberService.class);
        bindService(intent, this.mConnexion, BIND_AUTO_CREATE);
        startService(intent);

        List<Long> primeNumbers = this.mPrimeNumberBinder!=null? Arrays.asList(this.mPrimeNumberBinder.getPrimeNumbersCalculated()):new ArrayList<Long>();
        ArrayAdapter<Long> adapter = new ArrayAdapter<Long>(this, R.layout.primenumber, R.id.tvPrimeNumber,
                primeNumbers);
        mGridView.setAdapter(adapter);

        IntentFilter filter = new IntentFilter("fr.nro.primenummber");

        mBroadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                ((ArrayAdapter)mGridView.getAdapter()).add((Long) intent.getLongExtra("primenumber", 0));
                ((ArrayAdapter)mGridView.getAdapter()).notifyDataSetChanged();

                mCounter++;

                mTextView.setText(String.valueOf(mCounter));

            }
        };

       // registerReceiver(mBroadcastReceiver, filter);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        //this.unregisterReceiver(mBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        this.unbindService(this.mConnexion);
    }
}
