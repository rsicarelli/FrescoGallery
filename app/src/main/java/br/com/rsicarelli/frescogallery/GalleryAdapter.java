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

import butterknife.BindView;
import butterknife.ButterKnife;

class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int columnSize;
    private final List<GalleryPhoto> galleryPhotos;
    private Context context;

    GalleryAdapter(List<GalleryPhoto> galleryPhotos, Context context) {
        this.galleryPhotos = galleryPhotos;
        this.context = context;
    }

    class GalleryHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.drawee_view_photo)
        SimpleDraweeView draweeView;

        @BindView(R.id.size)
        TextView size;

        GalleryHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

            draweeView.setLayoutParams(
                    new FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            columnSize)
            );
        }
    }

    class EmptyHolder extends RecyclerView.ViewHolder {
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

    void setColumnSize(int columnWidth) {
        this.columnSize = columnWidth;
    }
}