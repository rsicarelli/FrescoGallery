package br.com.rsicarelli.frescogallery;


import android.os.Parcel;
import android.os.Parcelable;

public class GalleryPhoto implements Parcelable {
    public final String imageUri;
    public final String size;

    public  GalleryPhoto(String imageUri, String size) {
        this.imageUri = imageUri;
        this.size = size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUri);
        dest.writeString(this.size);
    }

    protected GalleryPhoto(Parcel in) {
        this.imageUri = in.readString();
        this.size = in.readString();
    }

    public static final Parcelable.Creator<GalleryPhoto> CREATOR = new Parcelable.Creator<GalleryPhoto>() {
        @Override
        public GalleryPhoto createFromParcel(Parcel source) {
            return new GalleryPhoto(source);
        }

        @Override
        public GalleryPhoto[] newArray(int size) {
            return new GalleryPhoto[size];
        }
    };
}
