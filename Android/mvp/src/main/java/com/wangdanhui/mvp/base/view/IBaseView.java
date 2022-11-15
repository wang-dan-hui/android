package com.wangdanhui.mvp.base.view;
//view里可能有的共性的状态
public interface IBaseView {

    void loading();

    void loadSuccess();

    void loadFail();
}
