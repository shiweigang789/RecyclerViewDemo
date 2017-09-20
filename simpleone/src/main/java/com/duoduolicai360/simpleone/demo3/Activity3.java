package com.duoduolicai360.simpleone.demo3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.duoduolicai360.simpleone.R;
import com.duoduolicai360.simpleone.demo1.ObjectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swg on 2017/9/20.
 */

public class Activity3 extends AppCompatActivity implements OnStartDragListener {

    private RecyclerView mRv;
    private NormalAdapter mAdapter;
    private ItemTouchHelper mHelper;
    private List<ObjectModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NormalAdapter(mData = initData(), this);
        mRv.setAdapter(mAdapter);
        mHelper = new ItemTouchHelper(new SimpleItemTouchCallback(mAdapter, mData));
        mHelper.attachToRecyclerView(mRv);

    }

    public ArrayList<ObjectModel> initData(){
        ArrayList<ObjectModel> models = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.title_array);
        for(int i=0;i<titles.length;i++){
            ObjectModel model = new ObjectModel();
            model.number = i + 1;
            model.title = titles[i];
            models.add(model);
        }
        return models;
    }

    @Override
    public void startDrag(RecyclerView.ViewHolder holder) {
        mHelper.startDrag(holder);
    }

}
