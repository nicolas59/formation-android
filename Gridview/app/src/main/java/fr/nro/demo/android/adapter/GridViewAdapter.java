package fr.nro.demo.android.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.nro.demo.android.R;
import fr.nro.demo.android.model.AndroidVersion;

/**
 * Created by Nicolas on 03/05/2016.
 */
public class GridViewAdapter extends ArrayAdapter<AndroidVersion> {

    private static final String TAG = GridViewAdapter.class.getName();

    public GridViewAdapter(Context context, int resource, AndroidVersion[] objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            Log.d(TAG, "Création de la vue et du holder");
            convertView  = LinearLayout.inflate(this.getContext(), R.layout.grid_cell_android, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder)convertView.getTag();
            Log.d(TAG, "Restauration du holder");
        }
        AndroidVersion androidVersion = this.getItem(position);
        holder.textView.setText(androidVersion.getLabel());
        int id = this.getContext().getResources().getIdentifier(androidVersion.getImageRef(), "mipmap", this.getContext().getPackageName());
        final Bitmap image =  BitmapFactory.decodeResource(this.getContext().getResources(), id);
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        holder.imageView.setImageBitmap(image);



        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;

        ViewHolder(View view){
            imageView = (ImageView)view.findViewById(R.id.imageView);
            this.textView = (TextView)view.findViewById(R.id.textView);
        }

    }
}
