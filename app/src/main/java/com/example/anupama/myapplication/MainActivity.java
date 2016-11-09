package com.example.anupama.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    EditText nameTxt,posTxt;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<Player> players=new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //recycler
        rv= (RecyclerView) findViewById(R.id.myRecycler);

        //SET ITS PROPS
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
       adapter=new MyAdapter(this,players);
       retrieve();
        ItemTouchHelper.Callback callback=new swipehelper(adapter);
        ItemTouchHelper helper=new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        showDialog();

        return true;
    }

    private void showDialog()
    {
       final Dialog d=new Dialog(this);
        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //layout of dialog
        d.setContentView(R.layout.custom_layout);
        nameTxt= (EditText) d.findViewById(R.id.nameEditTxt);
        posTxt= (EditText) d.findViewById(R.id.posEditTxt);
        Button savebtn= (Button) d.findViewById(R.id.saveBtn);


        //ONCLICK LISTENERS
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EditText nameTxt= (EditText) d.findViewById(R.id.nameEditTxt);
               EditText posTxt= (EditText) d.findViewById(R.id.posEditTxt);
                if (nameTxt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter a title !! ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (posTxt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter a Description !! ",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                save(nameTxt.getText().toString(),posTxt.getText().toString(),d);
            }
        });

        //SHOW DIALOG
        d.show();
    }
    //SAVE
    private void save(String name,String pos,Dialog d)
    {
        DBAdapter db=new DBAdapter(this);
        //OPEN
        db.openDB();
        //INSERT
        long result=db.add(name,pos);
        if(result>0)
        {
            nameTxt.setText("");
            posTxt.setText("");
            d.dismiss();

        }else
        {
            Snackbar.make(nameTxt,"Unable To Insert",Snackbar.LENGTH_SHORT).show();
        }

        //CLOSE
        db.close();
        //refresh
        retrieve();
    }

    //RETRIEVE
    public void retrieve()
    {
        DBAdapter db=new DBAdapter(this);
        //OPEN
        db.openDB();
        players.clear();
        //SELECT
        Cursor c=db.getAllPlayers();
        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);
            //CREATE PLAYER
            Player p=new Player(name,pos,id);
            //ADD TO PLAYERS
            players.add(p);
        }
        //SET ADAPTER TO RV
        if(!(players.size()<1))
        {
            rv.setAdapter(adapter);
        }
    }

    public void del(int id,Context c)
    {
        DBAdapter db=new DBAdapter(c);
        db.openDB();
        long h=db.Delete(id);
        retrieve();
    }
    public ArrayList<Player> retrieve1pter()
    {
        DBAdapter db=new DBAdapter(this);
        //OPEN
        db.openDB();
        players.clear();
        //SELECT
        Cursor c=db.getAllPlayers();
        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);
            //CREATE PLAYER
            Player p=new Player(name,pos,id);
            //ADD TO PLAYERS
            players.add(p);
        }
        //SET ADAPTER TO RV
        return players;
    }
    @Override
    protected void onResume() {
        super.onResume();
        retrieve();
    }

}