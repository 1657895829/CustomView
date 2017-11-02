package com.bwie.CustomView.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义显示圆形进度条的view
 */
public class CustomProgressView extends View{
    private boolean runing = true;
    private int progress = 0;
    private Paint paint;

    public CustomProgressView(Context context) {
        super(context);
    }

    public CustomProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //创建一个画笔
        paint = new Paint();

        //设置画笔的颜色
        paint.setColor(Color.BLUE);
        //设置画笔的style：内容是填充的空心圆
        paint.setStyle(Paint.Style.STROKE);

        //执行进度条是耗时操作，需放在子线程中执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (runing){
                    if (progress >= 360){
                        runing = false;
                        return;
                    }
                    progress += 10;

                    //子线程刷新方法：postInvalidate()，此事系统会调用onDraw()方法
                    postInvalidate();
                    try {
                        Thread.sleep(188);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //（1）获取当前自定义view的宽高度
        int x = getWidth() / 2;
        int y = getHeight() / 2;

        //（2）设置圆半径的大小
        int radius = 300;

        //（3）设置画笔的粗细
        paint.setStrokeWidth(18);

        //（4）定义一个矩形区域：RectF对象持有一个矩形的四个float坐标值
        RectF rectF = new RectF(x - radius, y - radius, x + radius, y + radius);
        /**
         *  画一个圆弧：drawArc方法：绘制圆弧，该方法用于在画布上绘制圆弧，通过指定圆弧所在的椭圆对象、起始角度、终止角度来实现。
         *   oval：圆弧所在的椭圆对象。
             startAngle：圆弧的起始角度。
             sweepAngle：圆弧的角度。
             useCenter：是否显示半径连线，true表示显示圆弧与圆心的半径连线，false表示不显示。
             paint：绘制时所使用的画笔。
         */
        canvas.drawArc(rectF,-90,progress,false,paint);

        //（5）把progress转换为int值
        int intProgress = (int) ((float)progress/360 * 100);

        //（6） measureText  测量字符串的宽度
        float textWidth = paint.measureText(intProgress + "%");
        //定义一个矩形区域：Rect对象持有一个矩形的四个integer坐标值
        Rect rect = new Rect();
        //测量矩形中内容
        paint.getTextBounds(intProgress+"%",0,(intProgress+"%").length(),rect);

        //设置画笔写出内容的size和画笔的粗细西
        paint.setTextSize(90);
        paint.setStrokeWidth(1);

        //画出文字  rect.height() 获取字符串的高度
        /**
         * drawText方法参数：
         * text:要绘制的文字
         * x：绘制原点x坐标
         * y：绘制原点y坐标
         * paint:用来做画的画笔
         */
        canvas.drawText(intProgress+"%",x-textWidth/2,y+rect.height()/2,paint);
    }

    public CustomProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
