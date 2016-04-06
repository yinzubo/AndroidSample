package com.bitech.androidsample.base;

/**
 * <p></p>
 * Created on 2016/4/6 16:26.
 *
 * @author Lucy
 */
public interface BasePresenter {

    //逻辑层中的暂停
    void  onResume();
    //销毁
    void onDestory();
}
