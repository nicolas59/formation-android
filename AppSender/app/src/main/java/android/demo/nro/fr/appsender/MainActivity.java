package android.demo.nro.fr.appsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.btEnvoyer);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView view = (TextView)findViewById(R.id.etEnvoyer);

        Intent intent = new Intent();
        intent.setPackage("android.demo.nro.fr.appreceiver");
        intent.setAction("service.receiver");
        intent.putExtra("text", view.getText().toString());
        startService(intent);
    }
}
