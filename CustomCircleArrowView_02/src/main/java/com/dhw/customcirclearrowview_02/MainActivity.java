package com.dhw.customcirclearrowview_02;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.dhw.customcirclearrowview_02.view.MyCircleView;

//主页面
public class MainActivity extends AppCompatActivity {
    //全局变量
    private MyCircleView my_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
        my_view = (MyCircleView) findViewById(R.id.my_view);
    }
    public void onClick(View view){
        my_view.setColor(Color.BLUE);
    }
    public void add(View view){
        my_view.speed();
    }
    public void slow(View view){
        my_view.slowDown();
    }
    public void pauseOrStart(View view){
        my_view.pauseOrStart();
    }
}