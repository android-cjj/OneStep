
package com.cjj.onestep;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cjj on 2016/10/22.
 */

public class OneStepAdapter extends RecyclerView.Adapter<BindingHolder> {

    private List<OneStepEntity> mList;

    private OnItemClickListener mListener;

    private OnItemLongClickListener mLongListener;

    public OneStepAdapter(List<OneStepEntity> list){
        mList = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View v,OneStepEntity entity);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v,OneStepEntity entity);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setItemLongClickListener(OnItemLongClickListener longListener) {
        mLongListener = longListener;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()),R.layout.item_icon,parent,false);
        return new BindingHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        final OneStepEntity entity = mList.get(position);
        holder.getBinding().setVariable(BR.entity,entity);
        holder.getBinding().executePendingBindings();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener!=null){
                    mListener.onItemClick(view,entity);
                }
            }
        });

        holder.itemView.setTag(entity.title);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(mLongListener!=null){
                    mLongListener.onItemLongClick(view,entity);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
