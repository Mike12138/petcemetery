package activitytest.example.com.logandregister;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;

    private List<Image> mImageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;
        TextView shopName, shopMessage, shopLocation;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            image = view.findViewById(R.id.image);
            shopName = view.findViewById(R.id.shop_name);
            shopMessage = view.findViewById(R.id.shop_message);
            shopLocation = view.findViewById(R.id.shop_location);
        }
    }

    public ImageAdapter(List<Image> imageList) {
        mImageList = imageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Image image = mImageList.get(position);
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", image.getUrl());
                mContext.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                Image image = mImageList.get(position);
                Intent intent = new Intent(mContext, LocationActivity.class);
                intent.putExtra("lng", image.getLng());
                intent.putExtra("lat", image.getLat());
                mContext.startActivity(intent);
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image image = mImageList.get(position);
        holder.shopName.setText(image.getName());
        holder.shopMessage.setText(image.getMessage());
        holder.shopLocation.setText(image.getLocation());
        Glide.with(mContext).load(image.getImageId()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}

