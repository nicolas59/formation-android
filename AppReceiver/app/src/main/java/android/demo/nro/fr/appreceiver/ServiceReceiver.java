package android.demo.nro.fr.appreceiver;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Nicolas on 19/09/2016.
 */
public class ServiceReceiver extends IntentService {

    public ServiceReceiver() {
        super("service.receiver");
    }

    @Override
    protected void onHandleIntent(Intent intentService) {
        Intent intent = new Intent(this, MainActivity.class);
      //  intent.putExtra("text", intentService.getStringExtra("text"));

        Application.INSTANCE.setData(intentService.getStringExtra("text"));

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
