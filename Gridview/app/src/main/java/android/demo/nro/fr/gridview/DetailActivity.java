package android.demo.nro.fr.gridview;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private AndroidVersion androidVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        this.androidVersion = (AndroidVersion)getIntent().getParcelableExtra("androidItem");
        this.initializeActivity();
    }


    private void initializeActivity(){
        final TextView tvAndroid = (TextView)findViewById(R.id.tvName);
        tvAndroid.setText(androidVersion.getLabel());

        final ImageView imageView;
        imageView = (ImageView)findViewById(R.id.ivAndroid);

        final Resources resources = getResources();
        final int refId = resources.getIdentifier(androidVersion.getImageRef(),"mipmap", getPackageName());

        imageView.setBackground(null);
        imageView.setImageBitmap(BitmapFactory.decodeResource(resources, refId));

        final TextView tvVersion = (TextView)findViewById(R.id.tvVersion);
        tvVersion.setText("API " + androidVersion.getApiVersion());

        final TextView tvDate = (TextView)findViewById(R.id.tvDate);
        tvDate.setText( androidVersion.getDateSortie());

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(androidVersion.getLabel());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
