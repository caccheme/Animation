package com.example.animation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.animation.transformers.DepthPageTransformer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Product> products = DataProvider.productList;
    private final int numPages = products.size();
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        PagerAdapter mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private static final String TAG = "Adapter";

        @SuppressWarnings("deprecation")
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return ItemFragment.create(products.get(position));
        }

        @Override
        public int getCount() {
            return numPages;
        }
    }

}