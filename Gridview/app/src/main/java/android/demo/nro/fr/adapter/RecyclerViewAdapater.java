package android.demo.nro.fr.adapter;

import android.content.Context;
import android.demo.nro.fr.activity.AndroidVersion;
import android.demo.nro.fr.gridview.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Nicolas on 16/05/2016.
 */
public class RecyclerViewAdapater extends RecyclerView.Adapter<RecyclerViewAdapater.ViewHolder> {

    private Context context;

    private OnItemClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView mImageView;

        private TextView mTextView;

        private LinearLayout linearLayout;


        private AndroidVersion androidVersion;

        public ViewHolder(LinearLayout v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            this.mImageView = (ImageView)v.findViewById(R.id.imageView);
            if(RecyclerViewAdapater.this.listener!=null){
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RecyclerViewAdapater.this.listener.onItemClick(androidVersion);
                    }
                });

            }
        }

        private void refresh(){
            this.mTextView.setText(androidVersion.getLabel());

            int res = RecyclerViewAdapater.this.context.getResources().getIdentifier(androidVersion.getImageRef(), "mipmap", this.getPackage());
            Bitmap img = BitmapFactory.decodeResource(RecyclerViewAdapater.this.context.getResources(), res);
            this.mImageView.setImageBitmap(img);
        }

        public void setAndroidVersion(AndroidVersion androidVersion){
            this.androidVersion = androidVersion;
            refresh();
        }

        public String getPackage(){
            return this.itemView.getContext().getPackageName();
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(AndroidVersion androidVersion);
    }

    private AndroidVersion[] androidVersions;

    public RecyclerViewAdapater(Context context, AndroidVersion[] androidVersions) {
        this.androidVersions = androidVersions;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_cell_android, parent, false);
        ViewHolder viewHolder = new ViewHolder((LinearLayout) view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AndroidVersion androidVersion = this.androidVersions[position];
       holder.setAndroidVersion(androidVersion);
    }


    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return this.androidVersions.length;
    }
}

