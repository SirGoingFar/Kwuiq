package com.eemf.sirgoingfar.kwuiq.activities;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.eemf.sirgoingfar.kwuiq.R;

import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private Fragment currentFragment;
    protected FragmentManager fragmentManager = getSupportFragmentManager();

    public void activityStackClearFlagSetter(Intent intent) {
        intent.replaceExtras(intent);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    protected void startFragment(Fragment fragment, boolean addToBackStack, boolean allowStateLoss) {
        if (fragment != null) {
            currentFragment = fragment;
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.container, fragment, fragment.getClass().getName());
            if (addToBackStack) {
                ft.addToBackStack(fragment.getClass().getName());
            }

            if (allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
        }
    }

    public void startFragment(Fragment fragment, boolean addToBackStack) {
        startFragment(fragment, addToBackStack, false);
    }

    public void startFragmentAllowStateLoss(Fragment fragment, boolean addToBackStack) {
        startFragment(fragment, addToBackStack, true);
    }

    public void closeFragment() {
        if (fragmentManager.getBackStackEntryCount() > 0 && isActivityStarted()) {
            hideKeyboard();
            fragmentManager.popBackStack();
        } else {
            hideKeyboard();
            finish();
        }
    }

    public Fragment getCurrentFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.isEmpty()) {
            return null;
        }
        return fragments.get(fragments.size() - 1);
    }

    public boolean isActivityStarted() {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }

    public void showKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            inputManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    public void showKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            showKeyboard(view);
        }
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (fragmentManager == null)
            return;

        if (fragmentManager.getBackStackEntryCount() <= 1)
            finish();
        else
            super.onBackPressed();
    }

    protected void popBackStackTo(@NonNull String fragmentName) {

        if (TextUtils.isEmpty(fragmentName) || fragmentManager == null || fragmentManager.getBackStackEntryCount() == 0)
            return;

        fragmentManager.popBackStack(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
