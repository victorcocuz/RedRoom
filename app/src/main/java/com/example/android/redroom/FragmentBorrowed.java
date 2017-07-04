package com.example.android.redroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBorrowed extends Fragment {

    private static final String LOG_TAG = FragmentBorrowed.class.getSimpleName();

    public FragmentBorrowed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_borrowed, container, false);

        return rootView;
    }
}
