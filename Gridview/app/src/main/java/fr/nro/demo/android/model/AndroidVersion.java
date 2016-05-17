package fr.nro.demo.android.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicolas on 03/05/2016.
 */
public class AndroidVersion implements Parcelable{

    private String label;

    private String imageRef;

    private String dateSortie;

    private String apiVersion;



    public static final Parcelable.Creator<AndroidVersion> CREATOR = new Parcelable.Creator<AndroidVersion>()
    {
        @Override
        public AndroidVersion createFromParcel(Parcel source)
        {
            return new AndroidVersion(source);
        }

        @Override
        public AndroidVersion[] newArray(int size)
        {
            return new AndroidVersion[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(label);
        dest.writeString(imageRef);
        dest.writeString(this.apiVersion);
        dest.writeString(this.dateSortie);
    }

    public AndroidVersion(String label, String imageRef) {
        this.label = label;
        this.imageRef = imageRef;
    }


    
    public AndroidVersion(String label, String imageRef, String apiVersion, String dateSortie) {
       this(label, imageRef);
        this.apiVersion =apiVersion;
        this.dateSortie = dateSortie;

    }


    public AndroidVersion(Parcel parcel) {
        this.label = parcel.readString();
        this.imageRef = parcel.readString();
        this.apiVersion = parcel.readString();
        this.dateSortie = parcel.readString();
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public String toString() {
        return label ;
    }



}
