package br.com.rsicarelli.frescogallery;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import java.util.List;

import br.com.rsicarelli.frescogallery.widget.GalleryRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_photos)
    GalleryRecyclerView galleryRecyclerView;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainActivityPermissionsDispatcher.setUpPhotosWithCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void setUpPhotos() {
        galleryRecyclerView.addItemDecoration(new MarginDecoration(this));
        galleryRecyclerView.setHasFixedSize(true);

        List<GalleryPhoto> imagesOnDevice = GalleryUtils.getImagesOnDevice(this);

        GalleryAdapter galleryAdapter = new GalleryAdapter(imagesOnDevice, this);
        galleryAdapter.setColumnSize(galleryRecyclerView.getColumnWidth());
        galleryRecyclerView.setAdapter(galleryAdapter);
    }
}
