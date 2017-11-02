package com.bwie.CustomView.view;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.bwie.CustomView.R;

/**
 * 自定义TextView的view
 */
public class CustomTextView extends TextView{
    private String text;
    private int color;
    private Paint paint;
    private int size;
    private Rect rect;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //通过context.obtainStyledAttributes()方法读取自定义view的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        //获取自定义view的属性的数量
        int count = typedArray.getIndexCount();
        //通过下标获取自定义view的属性
        for (int i=0;i<count;i++){
            int indexAttr = typedArray.getIndex(i);
            switch (indexAttr){
                //获取自定义view的text属性
                case R.styleable.CustomTextView_text:
                    text = typedArray.getString(indexAttr);
                    break;
                //获取自定义view的color属性，先设置一个默认颜色
                case R.styleable.CustomTextView_textColor:
                    color = typedArray.getInt(indexAttr, Color.YELLOW);
                    break;
                //获取自定义view的size属性
                case R.styleable.CustomTextView_textSize:
                    //通过下面前2行代码的方法转换获取的size属性数据
                    DisplayMetrics metrics = getResources().getDisplayMetrics();
                    float dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 18, metrics);
                    size = typedArray.getDimensionPixelSize(indexAttr, (int) dimension);
                    break;
            }
        }
        //数据不用时，回收以下
        typedArray.recycle();

        //设置画笔的size
        paint = new Paint();
        paint.setTextSize(size);

        //设置一个矩形。对text内容进行测量
        rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
    }

    /**
     *  onMeasure方法：计算子控件的尺寸和模式，以及设置自己的宽和高，测量View大小
        widthMeasureSpec：当前父容器的widthMeasureSpec
        heightMeasureSpec：当前父容器的heightMeasureSpec
        使用的是onMeasure函数，关于三种测量模式mode说法：
     （1）EXACTLY：一般是设置了明确的值（100dp）或者是MATCH_PARENT；
     （2）AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT；
     （3）UNSPECIFIED：表示子布局想要多大就多大，很少使用；
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //计算当前父容器下view的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //计算当前父容器下view的size
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        System.out.println("width = " + width);
        System.out.println("height = " + height);
    }

    //onDraw方法：绘图方法（canvas画布）
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //getWidth(): View在设定好布局后，整个View的宽度     getHeight(): View在设定好布局后，整个View的高度
        int widht = getWidth();
        int height = getHeight() ;

        //获取矩形的宽高
        int rectWidth = rect.width();
        int rectHeight = rect.height();

        //设置画笔的颜色
        paint.setColor(Color.YELLOW);
        //drawRect 就是使用画笔绘制一个矩形 前面两个参数代表起始坐标， 也就是左上角 后面两个参数可以标识你想画的长度和宽度 也可以理解为右下角的坐标点。
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);

        //设置画笔画出的内容颜色，设置画出内容的所在位置坐标
        paint.setColor(color);
        /**
         * drawText方法：绘制文字。参数：
         * text:要绘制的文字
         * x：绘制原点x坐标
         * y：绘制原点y坐标
         * paint:用来做画的画笔
         */
        canvas.drawText(text,(getWidth()-rect.width())/2,(getHeight()+rect.height())/2,paint);

        System.out.println("getWidth() = " + getWidth());
        System.out.println("getMeasuredWidth() = " + getMeasuredWidth());
    }

    /**
     *
     * @param w view改变后当前的宽
     * @param h  view改变后当前的高
     * @param oldw  view改变前的宽
     * @param oldh   view改变前的高
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("w = " + w + "  " + h  + " " + oldw + "  " + oldh);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
