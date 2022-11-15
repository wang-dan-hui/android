package com.wangdanhui.mvp.base.presenter;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

//M V 交互 V的接口
public class BasePresenter<V> {
    //解决MVP当中 内存泄漏问题
    //解决请求数据的时候的 内存泄漏问题

    //GC Java垃圾回收机制
    //强 new 正在使用的对象
    //软 GC 发现 内存满了 低内存 就回收
    //弱引用 GC 发现了未被使用和引用的对象
    WeakReference<V> mVWeakReference;
    //订阅的管理 Rxjava当中内存泄漏问题 也就是请求数据之后订阅的管理
    //在请求之后返回的方法里添加
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    //在view里创建的方法
    public  void attachView(V view){
        mVWeakReference = new WeakReference<>(view);
    }
    //出现上下文的时候 必须得是弱引用创建的view 在P层的实现类用
    protected V getView(){
        return  mVWeakReference == null?null:mVWeakReference.get();
    }


    //判断是不是弱引用创建的view  在P层的实现类用
    protected boolean isViewAttachView(){
        return mVWeakReference !=null&&mVWeakReference.get() !=null;
    }


    //在view调用的方法
    public void detachView(){
        if (mVWeakReference != null){
            mVWeakReference.clear();
            mVWeakReference = null;

        }
        mCompositeDisposable.clear();


    }


}
