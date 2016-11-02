package br.com.rsicarelli.frescogallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int columnSize;
    private final List<GalleryPhoto> galleryPhotos;
    private Context context;

    public  GalleryAdapter(List<GalleryPhoto> galleryPhotos, Context context) {
        this.galleryPhotos = galleryPhotos;
        this.context = context;
    }

    public class GalleryHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView draweeView;
        TextView size;

        GalleryHolder(View view) {
            super(view);

            draweeView = (SimpleDraweeView) view.findViewById(R.id.drawee_view_photo);
            size = (TextView) view.findViewById(R.id.size);

            draweeView.setLayoutParams(
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            columnSize)
            );
        }
    }

    public  class EmptyHolder extends RecyclerView.ViewHolder {
        EmptyHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (galleryPhotos.size() > 0) {
            return new GalleryHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_picker_item, parent, false));
        } else {
            return new EmptyHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_empty, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof GalleryHolder) {
            GalleryHolder galleryHolder = (GalleryHolder) holder;
            final GalleryPhoto photoGallery = galleryPhotos.get(position);

            galleryHolder.draweeView.setController(GalleryUtils.getDefaultDraweeController(
                    photoGallery.imageUri
            ));
            galleryHolder.size.setText(Formatter.formatFileSize(context, Long.parseLong(photoGallery.size)));
        }
    }

    @Override
    public int getItemCount() {
        return galleryPhotos.size();
    }

    public void setColumnSize(int columnWidth) {
        this.columnSize = columnWidth;
    }
}