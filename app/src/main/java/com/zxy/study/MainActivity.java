package com.zxy.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zxy.study.blur.BlurActivity;
import com.zxy.study.transition.TransitionActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * 关于布局代码自动生成
     * <p>
     * 用android findVIewById()在线生成工具 生成的控件代码
     * http://android.lineten.net/layout.php
     * 可选择标准模式和注解模式（类似ButterKnife）
     * ButterKnife:
     * 将鼠标移到代码中的布局的位置，摁快捷键“Alt+Insert”弹出对话框，点击“Generate ButterKnife Injections”
     */

    // Content View Elements

    private Button btn_transition,btn_blur;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        bindViews();
    }


    // End Of Content View Elements

    private void bindViews() {

        btn_transition = (Button) findViewById(R.id.btn_transition);
        btn_blur = (Button) findViewById(R.id.btn_blur);
        btn_blur.setOnClickListener(this);
        btn_transition.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e("zxy", "onViewClicked: ");
        switch (v.getId()){
            case R.id.btn_transition:
                Intent intent = new Intent(this, TransitionActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_blur:
                Intent i = new Intent(this, BlurActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
