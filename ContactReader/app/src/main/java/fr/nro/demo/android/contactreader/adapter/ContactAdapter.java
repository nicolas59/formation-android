package fr.nro.demo.android.contactreader.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import fr.nro.demo.android.contactreader.R;
import fr.nro.demo.android.contactreader.model.Contact;

/**
 * Created by Nicolas on 18/05/2016.
 */
public class ContactAdapter extends ArrayAdapter<Contact>  {

    private static final String TAG = "ContactAdapter";

    public  static interface ContactListener {
        void doCall(Contact contact);

        void sendMessage(Contact contact);
    }


    public ContactListener mListener;

    class ContactHolder {
        TextView textView;

        ImageView imageView;

        TextView nbTime;

        ImageView btCall;

        Contact contact;

        ContactHolder(View view){
            this.textView = (TextView)view.findViewById(R.id.contactTf);
            this.imageView = (ImageView)view.findViewById(R.id.imageView2);
            this.nbTime = (TextView)view.findViewById(R.id.contactNb);
            this.btCall = (ImageView)view.findViewById(R.id.imageButton);
            this.btCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContactAdapter.this.mListener.doCall(contact);
                }
            });
        }

    }

    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void attach(ContactListener mListener){
        this.mListener = mListener;
    }

    public void detach(){
        this.mListener = null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_contact, parent, false);
            holder = new ContactHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ContactHolder)convertView.getTag();
        }

        Contact contact = this.getItem(position);
        holder.textView.setText(contact.getName());
        holder.contact = contact;

        String nbMessage = getContext().getResources().getString(R.string.nb_called, contact.getTimeContacted());
        holder.nbTime.setText(nbMessage);
    if(contact.getPhotoUri() != null) {
        InputStream st = null;
        try{
            Log.d(TAG, "Contact : " + contact.getName() );
            st = parent.getContext().getContentResolver().openInputStream(Uri.parse(contact.getPhotoUri()));
            Bitmap bp = BitmapFactory.decodeStream(st);
            holder.imageView.setImageBitmap(bp);

        }catch (IOException e) {

        }finally {
            if(st!=null) {
                try {
                    st.close();
                }catch(IOException e){

                }
            }
        }

    }else{
        holder.imageView.setImageBitmap(null);
        holder.imageView.setBackground(getContext().getResources().getDrawable(android.R.drawable.sym_def_app_icon));
    }
        return convertView;
    }


}
