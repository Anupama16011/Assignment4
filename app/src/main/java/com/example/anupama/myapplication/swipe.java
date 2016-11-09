package com.example.anupama.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class swipe extends AppCompatActivity {
    ViewPager viewPager;
    page_viewer adapter1;
    TextView nameTxt,posTxt;
    Button updateBtn,deleteBtn;
    ArrayList<Player> players=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#008080")));
         int pos=getIntent().getExtras().getInt("ID");

        Log.d("hi",pos+"");
        DBAdapter db=new DBAdapter(this);
        players=db.swipe_palyer();
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        adapter1=new page_viewer(this,players);
        viewPager.setAdapter(adapter1);
        viewPager.setCurrentItem(pos-1);
        Log.d("ji","ji");
    }

}
