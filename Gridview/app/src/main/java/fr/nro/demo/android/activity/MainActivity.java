package fr.nro.demo.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Arrays;

import fr.nro.demo.android.OnFragmentInteractionListener;
import fr.nro.demo.android.R;
import fr.nro.demo.android.fragment.GridFragment;
import fr.nro.demo.android.fragment.ListFragment;
import fr.nro.demo.android.fragment.RecyclerViewFragment;
import fr.nro.demo.android.model.AndroidVersion;



public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private   AndroidVersion[] androidVersions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GridView gridView = (GridView)findViewById(R.id.gridView);

       this.androidVersions = Arrays.asList(
                new AndroidVersion("Froyo", "froyo", "8", "mai 2010"),
                new AndroidVersion("Gingerbread", "gingerbread"),
                new AndroidVersion("Ice Scream Sandwich", "ice_scream_sandwich"),
                new AndroidVersion("Jelly Bean", "jelly_bean"),
                new AndroidVersion("KitKat", "kitkat"),
                new AndroidVersion("Lollipop", "lollipop"),
                new AndroidVersion("Marshmallow", "marshmallow")).toArray(new AndroidVersion[7]);


        GridFragment fragment = GridFragment.newInstance(androidVersions);
        loadFragmen(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void loadFragmen(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.grid_item:
                GridFragment fragment = GridFragment.newInstance(androidVersions);
                loadFragmen(fragment);
                break;
            case R.id.listview_item:
                ListFragment fragment2 = ListFragment.newInstance(androidVersions);
                loadFragmen(fragment2);
                break;
            case R.id.recycler_item:
                RecyclerViewFragment fragment3 = RecyclerViewFragment.newInstance(androidVersions);
                loadFragmen(fragment3);
                break;
            case R.id.close_item:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSelectedItem(AndroidVersion androidVersion) {
        //création du nouvel iten pour l'activité DetailActivity
        Intent intent = new Intent(getBaseContext(), DetailActivity.class);

        //passage de l'objet decrivant une version d'android
        intent.putExtra("androidItem", androidVersion);

        //demarrage de la nouvelle activité
        startActivity(intent);
    }
}
