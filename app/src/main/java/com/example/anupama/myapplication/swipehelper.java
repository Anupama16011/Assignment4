package com.example.anupama.myapplication;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by anupama on 11/9/2016.
 */
public class swipehelper extends ItemTouchHelper.SimpleCallback
{
    MyAdapter adapter;
   public swipehelper(int drag,int swipedirs)
   {
       super(drag,swipedirs);

   }
    public swipehelper(MyAdapter adapter)
    {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT);
        this.adapter=adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.dismiss(viewHolder.getAdapterPosition());

    }
}
