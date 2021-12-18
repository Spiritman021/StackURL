package com.tworoot2.stackurl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView link;
    public ImageView copy;
    public ImageView delete;
    public ImageView image;
    public ImageView browse;

    public ViewHolder(View itemView) {
        super(itemView);
        link = (TextView) itemView.findViewById(R.id.link);
        delete = (ImageView) itemView.findViewById(R.id.delete);
        browse = (ImageView) itemView.findViewById(R.id.browse);
        copy = (ImageView) itemView.findViewById(R.id.copy);
        image = (ImageView) itemView.findViewById(R.id.image);


    }
}
