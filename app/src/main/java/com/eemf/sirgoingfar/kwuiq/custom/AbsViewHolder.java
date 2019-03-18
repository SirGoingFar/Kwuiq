package com.eemf.sirgoingfar.kwuiq.custom;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.eemf.sirgoingfar.kwuiq.api_communications.NetworkCallback;
import com.eemf.sirgoingfar.kwuiq.utils.Prefs;

import java.util.List;

import butterknife.ButterKnife;

public abstract class AbsViewHolder {

    protected Context mContext;
    private View containerView;
    private int containerLayout;
    protected Prefs prefs;

    protected AbsViewHolder(@NonNull Context context, int containerLayout){
        this.containerLayout = containerLayout;
        mContext = context;
//        View containerView = LayoutInflater.from(context).inflate(containerLayout, )
    }

    protected AbsViewHolder(@NonNull Context context, @NonNull View containerView) {
        this.containerView = containerView;
        ButterKnife.bind(this, containerView);
        mContext = context;
        prefs = Prefs.getInstance();
        init(containerView);
    }

    protected View findViewById(@IdRes int viewId){
        return containerView == null ? null : containerView.findViewById(viewId);
    }

    protected abstract void init(View containerView);

    protected void toggleActionViewsEnable(@NonNull List<View> views, boolean isEnable) {

        if (views.isEmpty())
            return;

        for (View view : views)
            toggleActionViewEnable(view, isEnable);
    }

    protected void toggleActionViewEnable(@NonNull View view, boolean isEnable) {
        view.setEnabled(isEnable);
    }

    protected void toggleActionViewsVisibility(@NonNull List<View> views, boolean isVisible) {

        if (views.isEmpty())
            return;

        for (View view : views)
            toggleActionViewVisibility(view, isVisible);
    }

    protected void toggleActionViewVisibility(@NonNull View view, boolean isVisible) {

        int visibility;

        if (isVisible)
            visibility = View.VISIBLE;
        else
            visibility = View.INVISIBLE;

        view.setVisibility(visibility);
    }

    protected void goneView(@NonNull View view) {
        view.setVisibility(View.GONE);
    }

    public void setErrorMessage(@NonNull AutoCompleteTextView atv, @NonNull String errorMessage){

        if(TextUtils.isEmpty(errorMessage))
            return;

        atv.setError(errorMessage);
    }


    public abstract class AbsViewState {

        public AbsViewState() {
            setNoData();
        }

        protected abstract void setNoData();

        protected abstract void setHasData();

        protected abstract void setLoading();

        protected abstract void setLoaded();
    }
}
