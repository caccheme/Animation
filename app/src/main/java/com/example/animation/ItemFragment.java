package com.example.animation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.NumberFormat;
import java.util.Objects;

public class ItemFragment extends Fragment {

    // constants for argument keys
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_NAME = "productName";
    private static final String PRODUCT_DESC = "productDesc";
    private static final String PRODUCT_PRICE = "productPrice";

    //  required no-args constructor
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//      Inflate the layout
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_detail, container, false);

        Bundle args = getArguments();

//      display text and image
        TextView nameText = rootView.findViewById(R.id.nameText);
        assert args != null;
        nameText.setText(args.getString(PRODUCT_NAME));

        TextView descriptionText = rootView.findViewById(R.id.descriptionText);
        descriptionText.setText(args.getString(PRODUCT_DESC));

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(args.getDouble(PRODUCT_PRICE));
        TextView priceText = rootView.findViewById(R.id.priceText);
        priceText.setText(price);

        String productId = args.getString(PRODUCT_ID);
        int imageResource = Objects.requireNonNull(getActivity()).getResources()
                .getIdentifier(productId, "drawable", getActivity().getPackageName());
        ImageView iv = rootView.findViewById(R.id.imageView);
        iv.setImageResource(imageResource);

        return rootView;
    }

}
