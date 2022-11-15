package com.wangdanhui.mvp.base.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wangdanhui.mvp.base.presenter.BasePresenter;


//拿UI里的参数 给UI填充
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends Fragment {
    //    1
    protected abstract int getContentViewId();

    protected View mRootView;
    //2
    protected P mPresenter;

    protected abstract P createPresenter();

    //3
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //1
        if (mRootView == null) {
            mRootView = inflater.inflate(getContentViewId(), container, false);
        }
        //2 挂载弱引用的view
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        //3
        init();
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }

    }
}
