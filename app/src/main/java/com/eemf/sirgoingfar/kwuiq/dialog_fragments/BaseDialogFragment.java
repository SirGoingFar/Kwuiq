package com.eemf.sirgoingfar.kwuiq.dialog_fragments;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;


public class BaseDialogFragment extends DialogFragment {

    protected FragmentManager fragmentManager;
    protected AppCompatActivity appCompatActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AppCompatActivity)
            appCompatActivity = (AppCompatActivity) context;

        fragmentManager = getFragmentManager();
    }

    protected void dismissAllDialogFragments() {

        List<Fragment> fragList = fragmentManager.getFragments();

        if (fragList == null)
            return;

        for (Fragment fragment : fragList) {

            if (fragment instanceof BaseDialogFragment)
                ((BaseDialogFragment) fragment).dismissAllowingStateLoss();

        }

    }

    protected void dismissDialog() {
        List<Fragment> fragList = fragmentManager.getFragments();

        if (fragList == null)
            return;

        Fragment fragment = fragList.get(fragList.size() - 1);

        if (fragment == null)
            return;

        if (fragment instanceof BaseDialogFragment)
            ((BaseDialogFragment) fragment).dismissAllowingStateLoss();

    }
}
