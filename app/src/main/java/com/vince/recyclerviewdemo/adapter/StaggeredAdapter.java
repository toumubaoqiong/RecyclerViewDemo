package com.vince.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vince.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * decription ：瀑布流适配器，动态设置itemView的高度
 * author ： zhua
 * Created at 2016/8/11.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ItemViewHolder>{

    private static final String TAG = "SimpleAdapter";

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mData;
    private List<Integer> mHeights;//动态设置高度

    public StaggeredAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.mData = data;
        mInflater = LayoutInflater.from(mContext);

        mHeights = new ArrayList<>();
        for(int i =0;i < mData.size(); i++){
            mHeights.add((int)(100 + Math.random()*300));
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_single_textview,parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.itemView.setLayoutParams(lp);
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ItemViewHolder(View itemView) {
            super(itemView);

            tv = (TextView)itemView.findViewById(R.id.TextView_tv);
        }
    }
}
