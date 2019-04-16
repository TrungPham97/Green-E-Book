package com.sinhvien.greene_book;

import android.app.Application;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements meFragment.OnFragmentInteractionListener,
        BookFragment.OnFragmentInteractionListener,
        CategoryFragment.OnFragmentInteractionListener,
        readingFragment.OnFragmentInteractionListener
{
    /*Database database;*/
    private static final String TAG = "MainActivity";
    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*//Tạo database DocTruyen
        database = new Database(this,"doctruyen.sqlite",null,1);

        //Tạo bảng truyện
        database.QueryData("CREATE TABLE IF NOT EXISTS Truyen(" +
                "Id INTERGER PRIMARY KEY AUTOINCREMENT, " +
                " tenTruyen NVARCHAR(200)" +
                " noiDung NVARCHAR(MAX)" +
                " moTa NVARCHAR(200)" +
                " hinhAnh NVARCHAR(500)");

        //Insert data
        database.QueryData("INSERT INTO Truyen VALUES(null, 'Già Thiên', 'abc....',null,'https://cdnvn.truyenfull.vn/cover/o/eJzLyTDT1430cjdLK64q8w_I1g9zNErTTSwpSrX01HeEAbdA_ZzkLN2q8nTdsBQL_XIjQ1PdDGMjIwBeARKY/gia-thien.jpg')");*/

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Truyện");
        loadFragment(new BookFragment());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_book:
                    toolbar.setTitle("Truyện");
                    fragment = new BookFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_category:
                    toolbar.setTitle("Thể loại");
                    fragment = new CategoryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_reading:
                    toolbar.setTitle("Đang đọc");
                    fragment = new readingFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_me:
                    toolbar.setTitle("Cá nhân");
                    fragment = new meFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
