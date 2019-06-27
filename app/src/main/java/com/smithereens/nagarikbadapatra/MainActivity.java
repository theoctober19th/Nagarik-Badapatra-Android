package com.smithereens.nagarikbadapatra;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jakewharton.processphoenix.ProcessPhoenix;
import com.smithereens.nagarikbadapatra.api.RetrofitClientInstance;
import com.smithereens.nagarikbadapatra.custom.TinyDB;
import com.smithereens.nagarikbadapatra.fragments.HistoryFragment;
import com.smithereens.nagarikbadapatra.fragments.HomeFragment;
import com.smithereens.nagarikbadapatra.fragments.InformationFragment;
import com.smithereens.nagarikbadapatra.fragments.NoticeFragment;
import com.smithereens.nagarikbadapatra.qr.QRCodeActivity;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.scanFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanQRCode();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setFragmentFromExtras();
    }

    private void setFragmentFromExtras() {
        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getStringExtra("loadfragment")!=null){
            String loadfragment = intent.getStringExtra("loadfragment");
            switch (loadfragment){
                case "information":
                    String officeid = intent.getStringExtra("officeid");
                    Bundle bundle = new Bundle();
                    bundle.putString("officeid", officeid);
                    InformationFragment myObj = new InformationFragment();
                    myObj.setArguments(bundle);
                    displaySelectedFragment(myObj);
            }
        }else{
            displaySelectedFragment(new HomeFragment());
        }
    }

    private void scanQRCode() {
        Intent intent = new Intent(this, QRCodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            setIPAddressForPi();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setIPAddressForPi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("IP address of Server");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ipaddress = input.getText().toString();
                if(!ipaddress.equals("")){
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putString("ipaddress", input.getText().toString()).apply();
                    ProcessPhoenix.triggerRebirth(MainActivity.this);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            displaySelectedFragment(new HomeFragment());
        } else if (id == R.id.nav_history) {
            displaySelectedFragment(new HistoryFragment());
        } else if (id == R.id.nav_qr) {
            scanQRCode();
        }   else if (id == R.id.nav_notice) {
            displaySelectedFragment(new NoticeFragment());
        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void displaySelectedFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}
