package com.example.animation;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String productId = getIntent().getStringExtra(MainActivity.PRODUCT_ID);
        Product product = DataProvider.productMap.get(productId);

        TextView tv = findViewById(R.id.nameText);
        assert product != null;
        tv.setText(product.getName());

        TextView descView = findViewById(R.id.descriptionText);
        descView.setText(product.getDescription());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(product.getPrice());
        TextView priceText = findViewById(R.id.priceText);
        priceText.setText(price);

        ImageView iv = findViewById(R.id.imageView);
        Bitmap bitmap = getBitmapFromAsset(product.getProductId());
        iv.setImageBitmap(bitmap);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private Bitmap getBitmapFromAsset(String productId) {
        AssetManager assetManager = getAssets();
        InputStream stream;

        try {
            stream = assetManager.open(productId + ".png");
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }
}
