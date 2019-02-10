package com.eemf.sirgoingfar.kwuiq.custom;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

public abstract class AbsViewHolder {

    private View containerView;

    protected AbsViewHolder(@NonNull View containerView) {
        this.containerView = containerView;
        ButterKnife.bind(this, containerView);
        initChildViews(containerView);
    }

    protected abstract void initChildViews(View containerView);

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
