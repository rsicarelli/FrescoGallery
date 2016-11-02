package br.com.rsicarelli.frescogallery;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class GalleryUtils {

    public static List<GalleryPhoto> getImagesOnDevice(Context context) {
        String[] columns = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.SIZE
        };

        List<GalleryPhoto> elements = new ArrayList<>();

        Cursor cursor;

        Uri queryUri;

        if (isExternalStorageReadable()) {
            queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else {
            queryUri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        }

        cursor = context.getContentResolver().query(
                queryUri,
                columns,
                null,
                null,
                MediaStore.Images.Media._ID + " DESC"
        );

        elements.addAll(convertCursor(cursor));

        if (cursor != null) {
            cursor.close();
        }

        return elements;
    }

    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public  static DraweeController getDefaultDraweeController(final String path) {
        Uri uriPhoto = Uri.parse("file://" + path);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uriPhoto)
                .setResizeOptions(new ResizeOptions(150, 150))
                .setProgressiveRenderingEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .build();

        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build();
    }

    private static List<GalleryPhoto> convertCursor(Cursor cursor) {
        List<GalleryPhoto> elements = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                String size = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.SIZE));

                if (path != null && !path.endsWith(".gif")) {
                    Log.d("size", size);
                    GalleryPhoto galleryPhoto = new GalleryPhoto(path, size);
                    elements.add(galleryPhoto);
                }
            } while (cursor.moveToNext());
        }
        return elements;
    }

}
