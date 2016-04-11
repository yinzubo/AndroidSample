package com.bitech.androidsample.base;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.bitech.androidsample.R;
import com.bitech.androidsample.callback.OnItemClickListener;

import java.util.List;

/**
 * <p></p>
 * Created on 2016/4/11 15:06.
 *
 * @author Lucy
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context context;
    protected List<T> datas;//数据
    protected RecyclerView.LayoutManager layoutManager;
    protected boolean useAnimation;//是否使用动画

    protected boolean showFooter;//是否显示页脚

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_ITEM = 2;
    public static final int TYPE_FOOTER = 3;

    protected OnItemClickListener onItemClickListener;

    public BaseRecyclerAdapter(Context context, List<T> datas) {
        this(context, datas, true);
    }

    public BaseRecyclerAdapter(Context context, List<T> datas, boolean useAnimation) {
        this(context, datas, useAnimation, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> datas, boolean useAnimation, RecyclerView.LayoutManager layoutManager) {
        this.context = context;
        this.datas = datas;
        this.useAnimation = useAnimation;
        this.layoutManager = layoutManager;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new BaseRecyclerViewHolder(context, LayoutInflater.from(context).inflate(R.layout.recycler_item_footer, null));
        } else {
            BaseRecyclerViewHolder viewHolder = new BaseRecyclerViewHolder(context,
                    LayoutInflater.from(context).inflate(getItemLayoutId(), null));
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            if (layoutManager != null) {
                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    if (((StaggeredGridLayoutManager) layoutManager).getSpanCount() != 1) {
                        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                        layoutParams.setFullSpan(true);
                    }
                    //网格布局
                } else if (layoutManager instanceof GridLayoutManager) {
                    if (((GridLayoutManager) layoutManager)
                            .getSpanCount() != 1 && ((GridLayoutManager) layoutManager)
                            .getSpanSizeLookup() instanceof GridLayoutManager.DefaultSpanSizeLookup) {
                        throw new RuntimeException("网格布局列数大于1时应该继承SpanSizeLookup时处理底部加载时布局占满一行。");
                    }
                }
            } else {
                bindData(holder, position, datas.get(position));
                if (useAnimation) {
                    setAnimation(holder.itemView, position);
                }
                //点击
                if (onItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onItemClickListener(v, position);
                        }
                    });
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        int i = showFooter ? 1 : 0;
        return datas != null ? datas.size() + i : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (showFooter && getItemCount() - 1 == position) {
            return TYPE_FOOTER;
        }
        return bindViewType(position);
    }

    protected int bindViewType(int position) {
        return 0;
    }

    private int lastPosition = -1;

    //设置动画
    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_bottom_in);
            view.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        //当动画运行之后就清除动画
        if (useAnimation && holder.itemView.getAnimation() != null && holder.itemView.getAnimation().hasStarted()) {
            holder.itemView.clearAnimation();
        }
    }

    public void add(int position, T item) {
        datas.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void addMoreData(List<T> datas) {
        int start = this.datas.size();
        datas.addAll(datas);
        notifyItemRangeChanged(start, datas.size());
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    //显示
    public void showFooter() {
        notifyItemInserted(getItemCount());
        showFooter = true;
    }

    //隐藏
    public void hideFooter() {
        notifyItemRemoved(getItemCount() - 1);
        showFooter = false;
    }

    //点击监听
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //返回LayoutView
    protected abstract int getItemLayoutId();

    //binding数据
    protected abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);
}
