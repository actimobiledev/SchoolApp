package com.rahul.schoolapp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.schoolapp.R;

public class AddMessageActivity extends AppCompatActivity {

    ImageView ivAddMessageSend;
    ImageView ivAddMessageCancel;
    TextView tvAddMessageSend;
    TextView tvAddMessageCancel;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_message);
        setUpNavigationDrawer ();
        initView ();
        initListener ();
        initAdapter ();
    }

    private void initAdapter () {
    }

    private void initView () {
        ivAddMessageCancel = (ImageView) findViewById (R.id.ivMessageCancel);
        ivAddMessageSend = (ImageView) findViewById (R.id.ivMessageSend);
        tvAddMessageCancel = (TextView) findViewById (R.id.tvMessageCancel);
        tvAddMessageSend = (TextView) findViewById (R.id.tvMessageSend);
    }

    private void initListener () {
        ivAddMessageSend.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tvAddMessageSend.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    ivAddMessageSend.setImageResource (R.drawable.ic_send_disabled);
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tvAddMessageSend.setTextColor (getResources ().getColor (R.color.colorHint));
                    ivAddMessageSend.setImageResource (R.drawable.ic_send_disabled);
                    finish ();
                }
                return true;
            }
        });
        ivAddMessageCancel.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tvAddMessageCancel.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    ivAddMessageCancel.setImageResource (R.drawable.ic_cancel_enabled);
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tvAddMessageCancel.setTextColor (getResources ().getColor (R.color.colorHint));
                    ivAddMessageCancel.setImageResource (R.drawable.ic_cancel_disabled);
                    finish ();
                }
                return true;
            }
        });
    }

    private void setUpNavigationDrawer () {
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar1);
        setSupportActionBar (toolbar);
        ActionBar actionBar = getSupportActionBar ();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled (true);
            actionBar.setHomeButtonEnabled (true);
            actionBar.setTitle ("Send Message");
            actionBar.setDisplayShowTitleEnabled (true);
        } catch (Exception ignored) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater ().inflate (R.menu.menu_rest, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                finish ();
        }
        return super.onOptionsItemSelected (item);
    }
}
