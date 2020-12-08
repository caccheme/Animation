package com.example.animation;

import android.content.Context;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;

public class ItemListFragment extends ListFragment {
    private ListEventHandler parentActivity;

    @SuppressWarnings("deprecation")
    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (ListEventHandler) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener((parent, view, position, id) -> parentActivity.onListItemClick(position));
    }

    public interface ListEventHandler {
        void onListItemClick(int position);
    }
}
