package fr.nro.demo.android.contactreader;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.nro.demo.android.contactreader.model.Contact;

public class DetailActivity extends AppCompatActivity {

    private static String LOGGER_ID = DetailActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Contact contact = (Contact) this.getIntent().getParcelableExtra("contact");

        long contactId = contact.getId();

        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);


        Cursor cursor = getContentResolver().query(uri,null, null, null, null );
        while(cursor.moveToNext()){
            Log.d(LOGGER_ID, cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED)));



            ((TextView)findViewById(R.id.contactName)).setText(contact.getName());
            ((TextView)findViewById(R.id.contactTimeContacted)).setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.TIMES_CONTACTED)));

            long lastTimeContacted = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts.LAST_TIME_CONTACTED));
            ((TextView)findViewById(R.id.contactLastCalled)).setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(lastTimeContacted)));
        }

    }
}
