package com.baraa.bsoft.tictactoymultiplayer.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.baraa.bsoft.tictactoymultiplayer.Fragments.HomeFragment;
import com.baraa.bsoft.tictactoymultiplayer.Fragments.PlayersFragment;
import com.baraa.bsoft.tictactoymultiplayer.Model.DataAccessLayer;
import com.baraa.bsoft.tictactoymultiplayer.R;

public class MainActivity extends AppCompatActivity {

    DataAccessLayer dataAccessLayer;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this,R.string.title_home,Toast.LENGTH_LONG).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, HomeFragment.newInstance("d","d")).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main, PlayersFragment.newInstance("d","d")).commit();
                    Toast.makeText(MainActivity.this,R.string.title_dashboard,Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(MainActivity.this,R.string.title_notifications,Toast.LENGTH_LONG).show();
                    //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_main,GameFragment.newInstance("d","d")).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        dataAccessLayer = new DataAccessLayer();
        dataAccessLayer.setUserOnline();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        dataAccessLayer.setUserOffline();
        super.onSaveInstanceState(outState);
    }
}
