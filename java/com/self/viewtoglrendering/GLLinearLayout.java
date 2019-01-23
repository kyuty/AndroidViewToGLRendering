package com.self.viewtoglrendering;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by user on 3/15/15.
 */
public class GLLinearLayout extends LinearLayout implements GLRenderable {

    private ViewToGLRenderer mViewToGLRenderer;
//    private boolean renderToGL = true;

    // default constructors

    public GLLinearLayout(Context context) {
        super(context);
    }

    public GLLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public GLLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // drawing magic
    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        return;

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        System.out.println("measuredWidth = " + measuredWidth);
        System.out.println("measuredHeight = " + measuredHeight);
        if (mViewToGLRenderer.getTextureWidth() != measuredWidth
                || mViewToGLRenderer.getTextureHeight() != measuredHeight) {
            mViewToGLRenderer.setTextureWidth(measuredWidth);
            mViewToGLRenderer.setTextureHeight(measuredHeight);
        }

        Canvas glAttachedCanvas = mViewToGLRenderer.onDrawViewBegin();
        if(glAttachedCanvas != null) {
            // prescale canvas to make sure content fits
            //float xScale = glAttachedCanvas.getWidth() / (float)canvas.getWidth();
            //glAttachedCanvas.scale(xScale, xScale);
            super.draw(glAttachedCanvas); // draw the view to provided canvas
        }
        // notify the canvas is updated
        mViewToGLRenderer.onDrawViewEnd();

//        if (renderToGL) {
//            Canvas glAttachedCanvas = mViewToGLRenderer.onDrawViewBegin();
//            if(glAttachedCanvas != null) {
//                //prescale canvas to make sure content fits
//                float xScale = glAttachedCanvas.getWidth() / (float)canvas.getWidth();
//                glAttachedCanvas.scale(xScale, xScale);
//                //draw the view to provided canvas
//                super.draw(glAttachedCanvas);
//            }
//            // notify the canvas is updated
//            mViewToGLRenderer.onDrawViewEnd();
//        } else {
//            super.draw(canvas);
//        }
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        System.out.println("wangdong GLLinearLayout onInterceptTouchEvent " + ev.getAction());
//        int actionMasked = ev.getActionMasked();
//        switch (actionMasked) {
//            case MotionEvent.ACTION_DOWN:{
//                renderToGL = false;
//                requestLayout();
//                System.out.println("wangdong GLLinearLayout onInterceptTouchEvent ACTION_DOWN");
//                break;
//            }
//            case MotionEvent.ACTION_MOVE:{
//                System.out.println("wangdong GLLinearLayout onInterceptTouchEvent ACTION_MOVE");
//                break;
//            }
//            case MotionEvent.ACTION_UP:{
//                renderToGL = true;
//                requestLayout();
//                System.out.println("wangdong GLLinearLayout onInterceptTouchEvent ACTION_UP");
//                break;
//            }
//        }
//        return super.onInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        System.out.println("wangdong GLLinearLayout onTouchEvent " + event.getAction());
//        return super.onTouchEvent(event);
//    }

    public void setViewToGLRenderer(ViewToGLRenderer viewToGLRenderer){
        mViewToGLRenderer = viewToGLRenderer;
    }
}
