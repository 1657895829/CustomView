package com.dhw.customcirclearrowview_01;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.dhw.customcirclearrowview_01.view.MyCustomCircleArrowView;

//最后呢,在MainActivity中实现改变颜色,加速,减速,暂停,开始
public class MainActivity extends AppCompatActivity {
    private MyCustomCircleArrowView myCustomCircleArrowView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCustomCircleArrowView = (MyCustomCircleArrowView) findViewById(R.id.my_view);
    }
    public void onClick(View view) {
        myCustomCircleArrowView.setColor(Color.BLUE);
    }

    public void add(View view) {
        myCustomCircleArrowView.speed();
    }

    public void slow(View view) {
        myCustomCircleArrowView.slowDown();
    }


    public void pauseOrStart(View view) {
        myCustomCircleArrowView.pauseOrStart();
    }
}