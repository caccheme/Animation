package com.example.animation;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ItemListFragment.ListEventHandler {

    public static final String PRODUCT_ID = "PRODUCT_ID";
    private final List<Product> products = DataProvider.productList;
    private boolean mShowingAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Display list fragment
        ListFragment fragment = new ItemListFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();

//        Display data
        ProductListAdapter adapter = new ProductListAdapter(
                this, R.layout.list_item, products);
        fragment.setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            viewAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        Product product = products.get(position);
        intent.putExtra(PRODUCT_ID, product.getProductId());

        startActivity(intent);
    }

    private void viewAbout() {

        if (mShowingAbout) {
            getFragmentManager().popBackStack();
            mShowingAbout = false;
            return;
        }


        AboutFragment aboutFragment = new AboutFragment();
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, aboutFragment)
                .addToBackStack(null)
                .commit();
        mShowingAbout = true;
    }

    @Override
    public void onBackPressed() {
        if (mShowingAbout) {
            getFragmentManager().popBackStack();
            mShowingAbout = false;
        } else {
            super.onBackPressed();
        }
    }
}
