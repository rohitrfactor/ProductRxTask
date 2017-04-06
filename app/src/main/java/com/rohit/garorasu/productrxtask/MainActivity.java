package com.rohit.garorasu.productrxtask;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.rohit.garorasu.productrxtask.Form.FormFragment;
import com.rohit.garorasu.productrxtask.Result.ResultFragment;

public class MainActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new TabAdapter(getSupportFragmentManager());

        vpPager.setAdapter(adapterViewPager);
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    final InputMethodManager imm = (InputMethodManager)getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(vpPager.getWindowToken(), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(vpPager);

    }
    public void setUpForm(){
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,new FormFragment()).commit();
    }
    public void setUpResult(){
        //getSupportFragmentManager().beginTransaction().replace(R.id.container,new ResultFragment()).commit();
    }
}
