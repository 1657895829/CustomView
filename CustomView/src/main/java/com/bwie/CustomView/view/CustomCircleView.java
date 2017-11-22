package com.bwie.CustomView.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 自定义圆环view
 */
public class CustomCircleView extends View{
    private Paint paint;
    private int xCircle = 200;  //圆心的x坐标
    private int yCircle = 200;  //圆心的y坐标
    public CustomCircleView(Context context) {
        super(context);
    }

    //CustomCircleView方法：设置自定义圆形view的视图
    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置画笔的颜色，style，setAntiAlias 抗锯齿形式（true为去除锯齿，false则不去），setStrokeWidth方法：设置空心线宽
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(80);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //getRawX()和getRawY()获得的是相对屏幕的位置
                System.out.println("按下时：    x  "+event.getRawX()+"     y  "+event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                //getRawX()和getRawY()获得的是相对屏幕的位置
                System.out.println("移动时：    x  "+event.getRawX()+"     y  "+event.getRawY());

                //获取鼠标移动时的坐标，为X轴和Y轴坐标重新赋值：getX()和getY()获得的永远是view的触摸位置坐标
                xCircle = (int) event.getX();
                yCircle = (int)event.getY();
                /**
                 *  Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：
                    1. Android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。
                 　　 invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
                   2.Android 程序默认情况下也只有一个进程，但一个进程下却可以有许多个线程。在这么多线程当中，把主要是负责控
                        制UI界面的显示、更新和控件交互的线程称为UI线程，由于onCreate()方法是由UI线程执行的，所以也可以把UI线程理解
                        为主线程。其余的线程可以理解为工作者线程。invalidate()得在UI线程中被调动，在工作者线程中可以通过Handler来通
                        知UI线程进行界面更新。而postInvalidate()在工作者线程中被调用。
                 */
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //getRawX()和getRawY()获得的是相对屏幕的位置
                System.out.println("抬起时：    x  "+event.getRawX()+"     y  "+event.getRawY());
                break;
        }
        return true;
    }

    /**
     * onMeasure方法：计算子控件的尺寸和模式，以及设置自己的宽和高
        widthMeasureSpec：当前父容器的widthMeasureSpec
        heightMeasureSpec：当前父容器的heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //onDraw方法：绘图方法（canvas画布）
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * canvas.drawCircle()方法：该方法用于在画布上绘制圆形，通过指定圆形圆心的坐标和半径来实现。该方法是绘制圆形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的圆形。
         *  cx：圆心的x坐标。
            cy：圆心的y坐标。
            radius：圆的半径。
            paint：绘制时所使用的画笔。
         */
        canvas.drawCircle(xCircle,yCircle,200,paint);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
