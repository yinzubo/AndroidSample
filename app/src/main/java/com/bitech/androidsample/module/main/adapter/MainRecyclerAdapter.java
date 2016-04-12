package com.bitech.androidsample.module.main.adapter;

import android.content.Context;

import com.bitech.androidsample.R;
import com.bitech.androidsample.base.BaseRecyclerAdapter;
import com.bitech.androidsample.base.BaseRecyclerViewHolder;
import com.bitech.androidsample.utils.Logger;

import java.util.List;

/**
 * <p></p>
 * Created on 2016/4/12 10:06.
 *
 * @author Lucy
 */
public class MainRecyclerAdapter extends BaseRecyclerAdapter<String>{

    public MainRecyclerAdapter(Context context,List<String> datas){
        super(context,datas);
    }
    public MainRecyclerAdapter(Context context, List<String> datas,boolean useAnimation){
        super(context,datas,useAnimation);
    }
    @Override
    protected int getItemLayoutId() {
        return R.layout.activity_main_recycler_item;
    }

    @Override
    protected void bindData(BaseRecyclerViewHolder holder, int position, String item) {

        holder.setText(R.id.main_recycler_item_textview,item);
    }
}
