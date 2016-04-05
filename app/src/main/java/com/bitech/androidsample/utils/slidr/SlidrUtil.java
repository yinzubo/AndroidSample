package com.bitech.androidsample.utils.slidr;

import android.app.Activity;
import android.support.v4.content.ContextCompat;

import com.bitech.androidsample.R;
import com.bitech.androidsample.utils.slidr.model.SlidrConfig;
import com.bitech.androidsample.utils.slidr.model.SlidrInterface;
import com.bitech.androidsample.utils.slidr.model.SlidrPosition;

/**
 * <p></p>
 * Created on 2016/3/16 14:22.
 *
 * @author Lucy
 */
public class SlidrUtil {

    public static SlidrInterface initSlidrDefaultConfig(Activity activity, int statusBarColor, int toolbarColor, boolean enableEdge){
        SlidrPosition slidrPosition=SlidrPosition.LEFT;
        SlidrConfig config=new SlidrConfig.Builder().primaryColor(statusBarColor)//状态栏的颜色
                .secondaryColor(toolbarColor)//toolbar的颜色
                .edge(enableEdge)//从边画出
                .position(slidrPosition)//画出位置
                .velocityThreshold(100f)//速度阀值
                .distanceThreshold(0.3f)//距离阀值，即划出多少退出
                .edgeSize(1.0f)
                .sensitivity(1f)
                .build();
        return Slidr.attach(activity,config);
    }

    public static SlidrInterface initSlidrDefaultConfig(Activity activity,boolean enableEdge){
        SlidrPosition position=SlidrPosition.LEFT;
        SlidrConfig config = new SlidrConfig.Builder()
                .primaryColor(ContextCompat.getColor(activity, R.color.colorPrimary))
                .secondaryColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark))
                .position(position).velocityThreshold(100f)// 速度阀值
                .distanceThreshold(0.3f)//划出多少退出
                //.touchSize(100f)//该库没用到此参数
                .edge(enableEdge)//从边划出
                .edgeSize(1f).sensitivity(1f).build();
        return Slidr.attach(activity,config);
    }
}
