package com.example.netflex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<slide> lstslides;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView MovieRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderPager  = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MovieRv = findViewById(R.id.Rv_movies);



        //prepare a list of slides ..
        lstslides = new ArrayList<>();
        lstslides.add(new slide(R.drawable.slide1, "slide Title /nmore text here"));
        lstslides.add(new slide(R.drawable.slide2, "slide Title /nmore text here"));
        lstslides.add(new slide(R.drawable.slide1, "slide Title /nmore text here"));
        lstslides.add(new slide(R.drawable.slide2, "slide Title /nmore text here"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstslides);
        sliderPager.setAdapter(adapter);
        //set up timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.sliderTimer(),2000,2000);
        indicator.setupWithViewPager(sliderPager,true);

        //Recycler View setup ..

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Avenger",R.drawable.marvel));
        lstMovies.add(new Movie("Eternal",R.drawable.marvel2));
        lstMovies.add(new Movie("Doctor Strange",R.drawable.marvel3));
        lstMovies.add(new Movie("Morbius",R.drawable.marvel4));
        lstMovies.add(new Movie("Multiver's",R.drawable.marvel5));
        lstMovies.add(new Movie("Marvel",R.drawable.slide1));
        lstMovies.add(new Movie("Thor",R.drawable.slide2));

        MovieAdapter movieAdapter = new MovieAdapter(this,lstMovies);
        MovieRv.setAdapter(movieAdapter);
        MovieRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    class sliderTimer extends TimerTask{

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<lstslides.size()-1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    }
                    else
                        sliderPager.setCurrentItem(0);
                }
            });
        }
    }

}