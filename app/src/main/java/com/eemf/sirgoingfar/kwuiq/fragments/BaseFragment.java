package com.eemf.sirgoingfar.kwuiq.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.eemf.sirgoingfar.kwuiq.utils.Prefs;


public class BaseFragment extends Fragment {

    protected Prefs prefs;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        prefs = Prefs.getInstance();
    }
}
