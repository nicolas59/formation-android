package fr.nro.demo.android.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import fr.nro.demo.android.R;
import fr.nro.demo.android.model.AndroidVersion;

/**
 * Activité pour afficher le detail d'une version d'android.
 *
 */
public class DetailActivity extends AppCompatActivity {

    /**
     * Bean transmis par l'activité "main"
     */
    private AndroidVersion androidVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        this.androidVersion = (AndroidVersion)getIntent().getParcelableExtra("androidItem");
        this.initializeActivity();
    }

    private void initializeActivity(){
        Resources res = this.getResources();

        //remplissage de la zone de detail
        final TextView tvAndroid = (TextView)findViewById(R.id.tvName);
        tvAndroid.setText(androidVersion.getLabel());

        final ImageView imageView;
        imageView = (ImageView)findViewById(R.id.ivAndroid);
        final int refId = res.getIdentifier(androidVersion.getImageRef(),"drawable", getPackageName());
        imageView.setBackground(null);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), refId, null));

        final TextView tvVersion = (TextView)findViewById(R.id.tvVersion);
        tvVersion.setText("API " + androidVersion.getApiVersion());

        final TextView tvDate = (TextView)findViewById(R.id.tvDate);
        tvDate.setText( androidVersion.getDateSortie());

        //affichage du bouton retour

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recuperation du titre dans les ressources

        String title = String.format(res.getString(R.string.detail_titre),
                androidVersion.getLabel());
        this.getSupportActionBar().setTitle(title);

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
