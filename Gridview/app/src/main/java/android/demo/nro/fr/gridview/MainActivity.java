package android.demo.nro.fr.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.gridView);

        AndroidVersion[] androidVersions = {
                new AndroidVersion("Froyo", "froyo"),
                new AndroidVersion("Gingerbread", "gingerbread"),
                new AndroidVersion("Ice Scream Sandwich", "ice_scream_sandwich"),
                new AndroidVersion("Jelly Bean", "jelly_bean"),
                new AndroidVersion("KitKat", "kitkat"),
                new AndroidVersion("Lollipop", "lollipop"),
                new AndroidVersion("Marshmallow", "marshmallow")};

        gridView.setAdapter(new GridViewAdapter(this, R.layout.grid_cell_android, androidVersions));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("...", ((AndroidVersion)parent.getItemAtPosition(position)).getLabel());
                Intent intent = new Intent(getBaseContext(), DetailActivity.class);
                startActivity(intent);
            }
        });

    }
}
