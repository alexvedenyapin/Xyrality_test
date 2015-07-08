package com.alex.xyrality_test.xyrality_test.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.alex.xyrality_test.xyrality_test.rest.results.World;

public class RetainedFragment extends Fragment{

    public static final String TAG = "retained_fragment";

    private World[] mWorlds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setWorlds(World[] worlds) {
        mWorlds = worlds;
    }

    public World[] getWorlds() {
        return mWorlds;
    }
}
