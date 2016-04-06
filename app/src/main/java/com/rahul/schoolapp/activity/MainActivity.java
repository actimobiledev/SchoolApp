package com.rahul.schoolapp.activity;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rahul.schoolapp.R;
import com.rahul.schoolapp.adapter.NavDrawerAdapter;
import com.rahul.schoolapp.fragment.HomeFragment;
import com.rahul.schoolapp.utils.Constants;
import com.rahul.schoolapp.utils.LoginDetailsPref;

public class MainActivity extends AppCompatActivity {


    Context context;
    // Action Bar components
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerPanel;
    public static int roll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance();
        roll = loginDetailsPref.getIntPref(MainActivity.this, LoginDetailsPref.ROLL);
        initPref();
        isLogin();
        setUpNavigationDrawer();
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:

                if (mDrawerLayout.isDrawerOpen(mDrawerPanel)) {
                } else {
                    mDrawerLayout.openDrawer(mDrawerPanel);
                }
                break;
            case R.id.logout:
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Log Out");
                alert.setMessage("Are you sure you want to Log Out");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance();
                        loginDetailsPref.putStringPref(MainActivity.this, LoginDetailsPref.USERNAME, "");
                        loginDetailsPref.putStringPref(MainActivity.this, LoginDetailsPref.PASSWORD, "");
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        Constants.username = "";
                        Constants.password = "";
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
                break;
           /* case R.id.calendar:
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;*/
//            case R.id.historicalMessage:
//                Intent intent = new Intent (MainActivity.this, HistoricalMessageActivity.class);
//                startActivity (intent);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();

    /*    LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance ();
        loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.USERNAME, "");
        loginDetailsPref.putStringPref (MainActivity.this, LoginDetailsPref.PASSWORD, "");
        Constants.username = "";
        Constants.password = "";
   */

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Log Out");
        alert.setMessage("Are you sure you want to Log Out");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance();
                loginDetailsPref.putStringPref(MainActivity.this, LoginDetailsPref.USERNAME, "");
                loginDetailsPref.putStringPref(MainActivity.this, LoginDetailsPref.PASSWORD, "");
                Constants.username = "";
                Constants.password = "";

                finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void setUpNavigationDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        try {
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        } catch (Exception ignored) {
        }
        ListView mDrawerListView = (ListView) findViewById(R.id.navDrawerList);
        mDrawerPanel = (RelativeLayout) findViewById(R.id.navDrawerPanel);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



            actionBar.setTitle(getString(R.string.app_name));
            final ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.menulist1));
            mDrawerListView.setAdapter(new NavDrawerAdapter(this, getResources().getStringArray(R.array.menulist1)));
            mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Constants.nav_drawer_selection = position;
                    mAdapter.notifyDataSetChanged();
                    view.setSelected(true);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    switch (position) {
                        case 0:
                            fragmentManager = getFragmentManager();
                            fragmentTransaction = fragmentManager.beginTransaction();
                            HomeFragment homeFragment = new HomeFragment();
                            fragmentTransaction.replace(R.id.fragment_container, homeFragment, "homeFragment");
                            actionBar.setTitle("Home");
                            fragmentTransaction.commit();
                            break;
                        case 1:
                            break;
                    }
                    mDrawerLayout.closeDrawer(mDrawerPanel);
                }
            });
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    //getSupportActionBar().setTitle(getString(R.string.drawer_opened));
                    invalidateOptionsMenu();
                }

                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    //getSupportActionBar().setTitle(mActivityTitle);
                    invalidateOptionsMenu();
                }
            };
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerToggle.syncState();
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }




    private void isLogin() {
        if (Constants.username == "" || Constants.password == "") {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
        }
        if (Constants.username == "" || Constants.password == "")
            finish();
    }

    private void initData() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment, "homeFragment");
        fragmentTransaction.commit();
    }

    private void initPref() {
        LoginDetailsPref loginDetailsPref = LoginDetailsPref.getInstance();
        Constants.username = loginDetailsPref.getStringPref(MainActivity.this, LoginDetailsPref.USERNAME);
        Constants.password = loginDetailsPref.getStringPref(MainActivity.this, LoginDetailsPref.PASSWORD);

    }
}
