package com.eemf.sirgoingfar.kwuiq.custom;

public abstract class AbsModel<T> {

    private T cachedData;
    private T localDbData;
    private T apiData;
    private T availableData;

    protected abstract void initModel();

    protected void setCachedData(T data) {
        this.cachedData = data;
    }

    protected void setLocalDbData(T data) {
        this.localDbData = data;
    }

    protected void setApiData(T data) {
        this.apiData = data;
    }

    protected void setAvailableData(T data) {
        this.availableData = data;
    }

    protected abstract void resolveAvailableData();

    protected T getCachedData() {
        return cachedData;
    }

    protected T getLocalDbData() {
        return localDbData;
    }

    protected T getApiData() {
        return apiData;
    }

    protected T getAvailableData() {
        return availableData;
    }

    protected abstract boolean isCachedDataAvailable();

    protected abstract boolean isLocalDbDataAvailable();

    protected abstract boolean isApiDataAvailable();

    protected boolean isClientSideDataAvailable() {
        return isCachedDataAvailable() || isLocalDbDataAvailable();
    }
}
