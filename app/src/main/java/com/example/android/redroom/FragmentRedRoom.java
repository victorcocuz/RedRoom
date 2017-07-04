package com.example.android.redroom;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRedRoom extends Fragment {

    private static final String LOG_TAG = FragmentRedRoom.class.getSimpleName();

    public FragmentRedRoom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_red_room, container, false);

        return rootView;
    }
}
