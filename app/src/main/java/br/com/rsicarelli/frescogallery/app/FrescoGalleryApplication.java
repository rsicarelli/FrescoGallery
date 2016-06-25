package br.com.rsicarelli.frescogallery.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

public class FrescoGalleryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient build = new OkHttpClient.Builder().build();
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, build)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }
}