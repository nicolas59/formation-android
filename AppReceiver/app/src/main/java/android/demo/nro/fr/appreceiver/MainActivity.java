package android.demo.nro.fr.appreceiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv = (TextView)findViewById(R.id.tv);
        Intent intent = this.getIntent();
        if(Application.INSTANCE.getData()!=null){
            tv.setText(Application.INSTANCE.getData());
        }

    }
}
