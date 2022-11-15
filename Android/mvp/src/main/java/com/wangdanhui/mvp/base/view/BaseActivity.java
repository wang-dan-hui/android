package com.wangdanhui.mvp.base.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wangdanhui.mvp.base.presenter.BasePresenter;


//拿UI里的参数 给UI填充
//V view接口 P 的实现类
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
//    1 获取布局的ID
    protected abstract int getContentViewId();
    //2 需要Presenter
    protected  P mPresenter;
    protected  abstract  P createPresenter();
    //3 初始化
    protected abstract void init(Bundle savedInstanceState);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //1
        setContentView(getContentViewId());
        //2
        mPresenter = createPresenter();
        //创建弱引用的view
        if (mPresenter != null){
            mPresenter.attachView((V)this);
        }
        //3
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空  OOM?
        if (mPresenter!=null){
            mPresenter.detachView();
        }

    }
}
