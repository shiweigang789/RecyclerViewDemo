package com.duoduolicai360.simpleone.demo4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.duoduolicai360.simpleone.R;
import com.duoduolicai360.simpleone.demo1.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swg on 2017/9/20.
 */

public class Activity4 extends AppCompatActivity {

    private RecyclerView mRv;
    private List<Image> mData;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mData = initData();
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ImageAdapter(mData);
        //((SimpleItemAnimator)mRv.getItemAnimator()).setSupportsChangeAnimations(false);
        mRv.setAdapter(mAdapter);
        mRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }


    public List<Image> initData(){
        List<Image> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Image image = new Image();
            image.id = R.drawable.bg;
            image.name = "bg";
            list.add(image);
        }
        return list;
    }

}
