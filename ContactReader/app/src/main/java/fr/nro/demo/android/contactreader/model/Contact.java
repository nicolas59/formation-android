package fr.nro.demo.android.contactreader.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Nicolas on 18/05/2016.
 */
public class Contact implements Parcelable{

    private Long id;

    private String name;

    private long timeContacted;

    public long getTimeContacted() {
        return timeContacted;
    }

    public void setTimeContacted(long timeContacted) {
        this.timeContacted = timeContacted;
    }

    private boolean hasPhoneNumber;

    private String photoUri;


    public Contact(Long id, String name, boolean hasPhoneNumber) {
        this.id = id;
        this.name = name;
        this.hasPhoneNumber = hasPhoneNumber;
    }

    protected Contact(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.hasPhoneNumber = in.readInt() == 1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.hasPhoneNumber?1:0);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasPhoneNumber() {
        return hasPhoneNumber;
    }

    public void setHasPhoneNumber(boolean hasPhoneNumber) {
        this.hasPhoneNumber = hasPhoneNumber;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
