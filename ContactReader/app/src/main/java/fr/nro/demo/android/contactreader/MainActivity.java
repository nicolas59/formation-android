package fr.nro.demo.android.contactreader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.nro.demo.android.contactreader.adapter.ContactAdapter;
import fr.nro.demo.android.contactreader.model.Contact;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = "MainActivity";

    private static int LOADER_CONTACT_ID = 0;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = (ListView) findViewById(R.id.listview);
        this.listView.setAdapter(new ContactAdapter(this, R.layout.listview_contact));
        getSupportLoaderManager().initLoader(LOADER_CONTACT_ID, null, this);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Contact> arrayAdapter = (ArrayAdapter<Contact>)parent.getAdapter();
                Contact contact = arrayAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra("contact", contact);

                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.d(TAG, "onSaveInstanceState: ");

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        if(id == LOADER_CONTACT_ID){
            Uri mContactUri = ContactsContract.Contacts.CONTENT_URI;

            String[] projection =
                    {
                            ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME,
                            ContactsContract.Contacts.HAS_PHONE_NUMBER,
                            ContactsContract.Contacts.TIMES_CONTACTED,
                            ContactsContract.Contacts.PHOTO_URI
                    };

            String sortOrder =
                    ContactsContract.Contacts.TIMES_CONTACTED +
                            " DESC";

            loader = new CursorLoader(
                    getApplicationContext(),  // The activity's context
                    mContactUri,              // The entity content URI for a single contact
                    projection,               // The columns to retrieve
                    null,                     // Retrieve all the raw contacts and their data rows.
                    null,                     //
                    sortOrder);
        }


        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        final List<Contact> contacts = new ArrayList<>();
        while(data.moveToNext()){
            final Contact contact = new Contact(data.getLong(0), data.getString(1), data.getInt(2) == 1);
            contact.setTimeContacted(data.getLong(3));
            contact.setPhotoUri(data.getString(4));
            contacts.add(contact);
        }
        ArrayAdapter adapter = (ArrayAdapter)this.listView.getAdapter();
        adapter.clear();
        adapter.addAll(contacts);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
