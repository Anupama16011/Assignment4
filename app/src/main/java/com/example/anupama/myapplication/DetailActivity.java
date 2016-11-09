package com.example.anupama.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ImageView img;
    TextView nameTxt,posTxt;
    Button updateBtn,deleteBtn;
    ViewPager viewPager;
    page_viewer adapter1;
    ArrayList<Player> players=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DBAdapter db=new DBAdapter(this);
        players=db.swipe_palyer();
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        adapter1=new page_viewer(this,players);
        viewPager.setAdapter(adapter1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i=getIntent();
        final String name=i.getExtras().getString("NAME");
        final String pos=i.getExtras().getString("POSITION");
        final int id=i.getExtras().getInt("ID");
        updateBtn= (Button) findViewById(R.id.updateBtn);
        deleteBtn= (Button) findViewById(R.id.deleteBtn);
        nameTxt= (TextView) findViewById(R.id.nameEditTxt);
        posTxt= (TextView) findViewById(R.id.posEditTxt);
        //ASSIGN DATA TO THOSE VIEWS
        nameTxt.setText(name);
        posTxt.setText(pos);
        //update
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(id,nameTxt.getText().toString(),posTxt.getText().toString());
            }
        });
        //DELETE
        //update
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id);
            }
        });
    }
    private void update(int id,String newName,String newPos)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.UPDATE(id,newName,newPos);
        if(result>0)
        {
            nameTxt.setText(newName);
            nameTxt.setText(newPos);
            Snackbar.make(nameTxt,"Updated Sucesfully",Snackbar.LENGTH_SHORT).show();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }
        db.close();
    }
    //DELETE
    private void delete(int id)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        long result=db.Delete(id);
        if(result>0)
        {
            this.finish();
        }else
        {
            Snackbar.make(nameTxt,"Unable to Update",Snackbar.LENGTH_SHORT).show();
        }
        db.close();
    }
}
