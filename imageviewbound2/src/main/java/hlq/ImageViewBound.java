package hlq;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by  Huanglinqing on 2018/8/23/023.
 * 带有角标的iamgeview  类似于消息个数
 */

public class ImageViewBound extends ImageView {

    String TAG = "ImageViewBound";

    private Paint paint;//画圆
    private Paint painttext;//写字
    private int messageNum = 0;//未读消息的个数


    public ImageViewBound(Context context) {
        super(context);
        initpanit();
    }

    public ImageViewBound(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpanit();
    }

    public ImageViewBound(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpanit();
    }

    /**
     * 初始化画笔 画角标圆
     */
    private void initpanit() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        painttext = new Paint();
        painttext.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        painttext.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        Log.d(TAG, "getLeft:" + getLeft() + "getTop:" + getTop());
        Log.d(TAG, "getWidth:" + getWidth() + "getHeight:" + getHeight());

        /**
         * 如果有未读消息则进行绘制
         */
        if (getMessageNum() != 0) {

            String message;
            if (messageNum < 10) {
                message = getMessageNum() + " ";
            } else if (getMessageNum() > 10 && getMessageNum() < 100) {
                message = getMessageNum() + "";
            } else {
                message = "99+";
            }

            canvas.drawCircle(getWidth() / 2 + getWidth() / 4, getHeight() / 4, getHeight() / 4, paint);
            //设置中间字体的大小
            float textSize = getTextSize(message, getHeight() / 4);
            painttext.setTextSize(textSize);
            //获取中间文字的宽度
            int textWidth = getTextWidth(painttext, message.trim());
            canvas.drawText(message, getWidth() / 2 + getWidth() / 4 - textWidth / 2, getHeight() / 4 + textSize / 2, painttext);
        }

    }

    /**
     * dp转换px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取字体的大小
     *
     * @param str
     * @return
     */
    private float getTextSize(String str, float mRadius) {
        int strLength = str.length();
        return mRadius * 2 / strLength;
    }

    /**
     * 获取字符串的宽度
     *
     * @param paint
     * @param str
     * @return
     */
    private static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }

    /**
     * 设置未读消息
     */
    public void setMessageNum(int messageNum) {
        invalidate();
        this.messageNum = messageNum;
    }

    /**
     * 获取未读消息个数
     */
    public int getMessageNum() {
        return messageNum;
    }
}
