package com.duoduolicai360.stickydecorationdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.duoduolicai360.stickydecorationdemo.databinding.ActivityMainBinding;
import com.duoduolicai360.stickydecorationdemo.view.BeautifulRecyclerViewActivity;
import com.duoduolicai360.stickydecorationdemo.view.PowerfulStickyRecyclerViewActivity;
import com.duoduolicai360.stickydecorationdemo.view.StickyRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setVariable(com.duoduolicai360.stickydecorationdemo.BR.presenter, new Presenter());


    }

    public class Presenter {

        public void onClick(View view){

            switch (view.getId()){
                case R.id.btn_toSticky:
                    startActivity(new Intent(MainActivity.this, StickyRecyclerViewActivity.class));
                    break;
                case R.id.btn_toPowerfulSticky:
                    startActivity(new Intent(MainActivity.this, PowerfulStickyRecyclerViewActivity.class));
                    break;
                case R.id.btn_toPowerfulSticky2:
                    startActivity(new Intent(MainActivity.this, BeautifulRecyclerViewActivity.class));
                    break;


            }
        }
    }

}
