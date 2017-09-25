package com.cpxiao.gamelib.mode.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.cpxiao.AppConfig;

/**
 * @author cpxiao on 2017/07/16.
 */

public class Sprite {

    protected boolean DEBUG = AppConfig.DEBUG;
    protected String TAG = getClass().getSimpleName();

    private float x, y;//坐标
    private float width, height;//宽高

    /**
     * 精灵矩形
     */
    private RectF mRectF = new RectF();
    /**
     * 绘制矩形，根据精灵矩形的百分比计算
     */
    private float mDrawRectFPercentW = 1, mDrawRectFPercentH = 1;
    private RectF mDrawRectF = new RectF();
    /**
     * 碰撞矩形，根据精灵矩形的百分比计算
     */
    private float mCollideRectFPercentW = 1, mCollideRectFPercentH = 1;
    private RectF mCollideRectF = new RectF();


    private boolean visible = true;//是否可见
    private boolean destroyed = false;//是否已销毁
    private long mFrame = 0;//绘制的次数

    private Bitmap bitmap = null;

    public Sprite() {
    }

    public Sprite(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisibility() {
        return visible;
    }

    public float getCenterX() {
        return x + 0.5F * width;
    }

    public float getCenterY() {
        return y + 0.5F * height;
    }

    public RectF getRectF() {
        mRectF.left = x;
        mRectF.top = y;
        mRectF.right = mRectF.left + getWidth();
        mRectF.bottom = mRectF.top + getHeight();
        return mRectF;
    }

    public void setDrawRectFPercent(float percent) {
        mDrawRectFPercentW = percent;
        mDrawRectFPercentH = percent;
    }

    public void setDrawRectFPercent(float percentW, float percentH) {
        mDrawRectFPercentW = percentW;
        mDrawRectFPercentH = percentH;
    }

    public RectF getDrawRectF() {
        mDrawRectF.left = getCenterX() - 0.5F * mDrawRectFPercentW * getWidth();
        mDrawRectF.top = getCenterY() - 0.5F * mDrawRectFPercentH * getHeight();
        mDrawRectF.right = getCenterX() + 0.5F * mDrawRectFPercentW * getWidth();
        mDrawRectF.bottom = getCenterY() + 0.5F * mDrawRectFPercentH * getHeight();
        return mDrawRectF;
    }

    public void setCollideRectFPercent(float percent) {
        mCollideRectFPercentW = percent;
        mCollideRectFPercentH = percent;
    }

    public void setCollideRectFPercent(float percentW, float percentH) {
        mCollideRectFPercentW = percentW;
        mCollideRectFPercentH = percentH;
    }

    public RectF getCollideRectF() {
        mCollideRectF.left = getCenterX() - 0.5F * mCollideRectFPercentW * getWidth();
        mCollideRectF.top = getCenterY() - 0.5F * mCollideRectFPercentH * getHeight();
        mCollideRectF.right = getCenterX() + 0.5F * mCollideRectFPercentW * getWidth();
        mCollideRectF.bottom = getCenterY() + 0.5F * mCollideRectFPercentH * getHeight();
        return mCollideRectF;
    }

    public void moveBy(float offsetX, float offsetY) {
        x += offsetX;
        y += offsetY;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void centerTo(float centerX, float centerY) {
        float w = getWidth();
        float h = getHeight();
        x = centerX - 0.5F * w;
        y = centerY - 0.5F * h;
    }

    public void draw(Canvas canvas, Paint paint) {
        mFrame++;
        beforeDraw(canvas, paint);
        onDraw(canvas, paint);
        afterDraw(canvas, paint);
    }

    protected void beforeDraw(Canvas canvas, Paint paint) {

    }

    public void onDraw(Canvas canvas, Paint paint) {

    }

    protected void afterDraw(Canvas canvas, Paint paint) {

    }

    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setFrame(long frame) {
        mFrame = frame;
    }

    public long getFrame() {
        return mFrame;
    }


    /**
     * @param x x
     * @param y y
     * @return 是否点中精灵
     */
    public boolean isClicked(float x, float y) {
        RectF rectF = getCollideRectF();
        return x >= rectF.left && x <= rectF.right && y >= rectF.top && y <= rectF.bottom;
    }

}
