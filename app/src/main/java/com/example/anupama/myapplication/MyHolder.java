package com.example.anupama.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTxt;
    ItemClickListener itemClickListener;
    public MyHolder(View itemView) {
        super(itemView);

        //ASSIGN
        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);

        itemView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v,getLayoutPosition());
    }
    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }
}
