package com.duoduolicai360.simpleone.demo4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoduolicai360.simpleone.R;

import java.util.List;

/**
 * Created by swg on 2017/9/20.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.VH> {

    private List<Image> mData;

    public ImageAdapter(List<Image> data) {
        this.mData = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_4, parent ,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        final Image image = mData.get(position);
        holder.mText.setText(image.name);
        holder.mImage.setImageResource(image.id);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.name = "haha";
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class VH extends RecyclerView.ViewHolder{

        private ImageView mImage;
        private TextView mText;

        public VH(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.image);
            mText = (TextView) itemView.findViewById(R.id.name);
        }
    }

}
