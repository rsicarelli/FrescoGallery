package br.com.rsicarelli.frescogallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import java.util.List;

import br.com.rsicarelli.frescogallery.widget.GalleryRecyclerView;

public class MainActivity extends AppCompatActivity implements GalleryAdapter.OnGalleryClickListener {
    private static final String EXTRA_GALLERY_PHOTO = "extraGalleryPhoto";

    GalleryRecyclerView galleryRecyclerView;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        galleryRecyclerView = (GalleryRecyclerView) findViewById(R.id.recycler_photos);
        setUpPhotos();
    }

    public void setUpPhotos() {
        galleryRecyclerView.addItemDecoration(new MarginDecoration(this));
        galleryRecyclerView.setHasFixedSize(true);

        List<GalleryPhoto> imagesOnDevice = GalleryUtils.getImagesOnDevice(this);

        GalleryAdapter galleryAdapter = new GalleryAdapter(imagesOnDevice, this, this);
        galleryAdapter.setColumnSize(galleryRecyclerView.getColumnWidth());
        galleryRecyclerView.setAdapter(galleryAdapter);
    }

    @Override
    public void onItemSelected(GalleryPhoto galleryPhoto) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_GALLERY_PHOTO, galleryPhoto);
        setResult(RESULT_OK, intent);
        finish();
    }
}
