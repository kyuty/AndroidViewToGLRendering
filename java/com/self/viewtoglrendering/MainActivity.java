package com.self.viewtoglrendering;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.self.viewtoglrendering.cuberenerer.CubeGLRenderer;


public class MainActivity extends Activity {

    private GLSurfaceView mGLSurfaceView;
    private GLRenderable mGLLinearLayout;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);

        final ViewToGLRenderer viewToGlRenderer = new CubeGLRenderer(this);

        mGLSurfaceView = (GLSurfaceView) findViewById(R.id.gl_surface_view);
        mGLLinearLayout = (GLRenderable) findViewById(R.id.gl_layout);
        mWebView = (WebView) findViewById(R.id.web_view);

        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(viewToGlRenderer);

        mGLLinearLayout.setViewToGLRenderer(viewToGlRenderer);

//        int measuredWidth = ((LinearLayout) (mGLLinearLayout)).getMeasuredWidth();
//        int measuredHeight = ((LinearLayout) (mGLLinearLayout)).getMeasuredHeight();
//        System.out.println("measuredWidth = " + measuredWidth);
//        System.out.println("measuredHeight = " + measuredHeight);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
//        mWebView.loadUrl("http://stackoverflow.com/questions/12499396/is-it-possible-to-render-an-android-view-to-an-opengl-fbo-or-texture");
        mWebView.loadUrl("http://www.baidu.com");

        Button button = (Button)findViewById(R.id.wd_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("wangdong onClick button");
                Toast.makeText(MainActivity.this, "wangdong", Toast.LENGTH_LONG).show();
            }
        });

        Button notRotate = (Button)findViewById(R.id.not_rotate);
        notRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                ((CubeGLRenderer) viewToGlRenderer).updateRotate();
            }
        });
    }

}
