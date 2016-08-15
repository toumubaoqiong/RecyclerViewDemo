package com.vince.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vince.recyclerviewdemo.R;

import java.util.List;

/**
 * decription ：简单适配器(自定义分割线)
 * author ： zhua
 * Created at 2016/8/11.
 */
public class SimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "SimpleAdapter";

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mData;
    private OnItemClickListener mOnItemClickListener;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public interface OnItemClickListener {
        void onItemClick(View view, int postion);

        void onItemLongClick(View view, int postion);
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public SimpleAdapter(Context mContext, List<String> data) {
        this.mContext = mContext;
        this.mData = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = mInflater.inflate(R.layout.item_single_textview, parent, false);
            return new ItemViewHolder(v);
        }else if(viewType == TYPE_FOOTER){
            View view = mInflater.inflate(R.layout.footerview, parent,false);
            return new FooterViewHolder(view);
        }

        return null;
    }

    public void addData(int pos) {
        mData.add(pos, "Insert One");
        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        mData.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if(!(holder instanceof ItemViewHolder)){
            return ;
        }
        ((ItemViewHolder)holder).tv.setText(mData.get(position));

        if (!holder.itemView.hasOnClickListeners()) {//加入判断之后只需要调用一次即可
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //这里使用的布局上的位置
                        int layoutPostion = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, layoutPostion);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        //这里使用的布局上的位置
                        int layoutPostion = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, layoutPostion);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public ItemViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.TextView_tv);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
