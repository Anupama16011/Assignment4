package com.example.anupama.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder>
{

    Context c;
    ArrayList<Player> players;



    public MyAdapter(Context ctx,ArrayList<Player> players)
    {
        //ASSIGN THEM LOCALLY
        this.c=ctx;
        this.players=players;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        //VIEW OBJ FROM XML
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,null);

        //holder
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    //BIND DATA TO VIEWS
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.nameTxt.setText(players.get(position).getName());

        //HANDLE ITEMCLICKS
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA
                //CREATE INTENT
                Intent i=new Intent(c,swipe.class);
                //LOAD DATA
                Log.d("hello",players.get(pos).getId()+"");
                Log.d("hello",players.get(pos).getName()+"");
                Log.d("hello",players.get(pos).getPosition()+"");
                i.putExtra("NAME",players.get(pos).getName());
                i.putExtra("POSITION",players.get(pos).getPosition());
                i.putExtra("ID",players.get(pos).getId());
                //START ACTIVITY
                c.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return players.size();
    }

    public void dismiss(int pos)
    {
        int id=players.get(pos).getId();
        DBAdapter adapter =new DBAdapter(c);
        adapter.openDB();
        adapter.Delete(id);

       //this.notifyDataSetChanged();
       this.notifyItemRangeChanged(1,getItemCount());
        this.notifyItemRemoved(pos);
    }
}
