package com.example.anupama.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class page_viewer extends PagerAdapter
{
    private Context ctx;
    private LayoutInflater layoutInflater;
    DBHelper helper;
    ArrayList<Player> players=new ArrayList<>();


    public page_viewer(Context ctx,ArrayList<Player> players)
    {
        this.ctx=ctx;
        this.players=players;
    }

    @Override
    public int getCount() {
        Log.d("ki","ki");
        return players.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position)    {
        Log.d("ki","ki12344");
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layoutInflater.inflate(R.layout.swipe_layout,container,false);
        TextView textView=(TextView)item_view.findViewById(R.id.name_txt);
        TextView textView1=(TextView)item_view.findViewById(R.id.pos_txt);

        textView.setText(players.get(position).getName());
        Log.d("jk",players.get(position).getPosition()+"");
        textView1.setText(players.get(position).getPosition()+"");


        container.addView(item_view);
        return item_view;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.d("ki","1");
        return (view==(LinearLayout)object);
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
