package com.vince.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.vince.recyclerviewdemo.R;
import com.vince.recyclerviewdemo.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *description:RecyclerView 瀑布流效果，动态设置itemView的高度
 *author:vince
 *creator at:2016/8/11
 */
public class StaggeredGridLayoutActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
        
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView_show);
        mAdapter = new StaggeredAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView布局管理
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //设置RecyclerView分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){

        }

        return super.onOptionsItemSelected(item);
    }

    private void initDatas() {

        mDatas = new ArrayList<>();
        for(int i = 'A';i <= 'z';i++){
            mDatas.add((char)i + "");
        }
    }
}
