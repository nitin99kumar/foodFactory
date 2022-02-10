package com.example.foodfactory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodfactory.ui.Allfragments.BlankFragmentForCall;
import com.example.foodfactory.ui.Allfragments.BlankFragmentForHome;
import com.example.foodfactory.ui.Allfragments.BlankFragmentForProfile;
import com.example.foodfactory.ui.Allfragments.BlankFragmentForSettings;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardAfterLogin extends AppCompatActivity {

    Button logoutbtn;
    NavigationView nav;
    DrawerLayout dl;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_after_login);

        /* logoutbtn = findViewById(R.id.logoutbtn);*/

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();*/

        /*NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);*/
        nav = (NavigationView) findViewById(R.id.navigationView);
        dl = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        replaceFragments(new BlankFragmentForHome());
                        Toast.makeText(getApplicationContext(), "home panel is open", Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_call:
                        replaceFragments(new BlankFragmentForCall());
                        Toast.makeText(getApplicationContext(), "call panel is open", Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_settings:
                        replaceFragments(new BlankFragmentForSettings());
                        Toast.makeText(getApplicationContext(), "settings panel is open", Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.profile:
                        replaceFragments(new BlankFragmentForProfile());
                        Toast.makeText(getApplicationContext(), "Profile panel is open", Toast.LENGTH_SHORT).show();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logOut:

                        new AlertDialog.Builder(DashboardAfterLogin.this).setMessage("Log Out")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                                        finish();
                                    }
                                }).setNeutralButton("Help", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Click on Yes for Exit",Toast.LENGTH_SHORT ).show();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                        break;
                }
                return true;
            }
        });

       /* logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login_Activity.class));
                finish();
            }
        });*/

    }

    private void replaceFragments(Fragment frag) {
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frameLayout, frag);
        fragTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.titlt1:
                Toast.makeText(this, "title1 is pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.title2:
                Toast.makeText(this, "title2 is pressed", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return true;
    }
}