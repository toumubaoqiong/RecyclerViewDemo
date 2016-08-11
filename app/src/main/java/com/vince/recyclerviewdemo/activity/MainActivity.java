package com.vince.recyclerviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vince.recyclerviewdemo.R;
import com.vince.recyclerviewdemo.adapter.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *description: 实现gridView、listView、HorizontalGridView；动态添加删除动画；onClick监听
 *author:vince
 *creator at:2016/8/11
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initView();
        
    }

    private void initView() {
        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView_show);
        mAdapter = new SimpleAdapter(this,mDatas);

        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"click:" + postion ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int postion) {
                Toast.makeText(MainActivity.this,"longClick" + postion ,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        //设置RecyclerView布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置RecyclerView动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置RecyclerView分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_list_view:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_grid_view:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.action_hor_grid_view:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_staggered:

                Intent intent = new Intent(this,StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.action_add:
                mAdapter.addData(1);
                break;
            case R.id.action_delete:
                mAdapter.deleteData(1);
                break;
            case R.id.action_setting:
                break;
            default:
                break;
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
