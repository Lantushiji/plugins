package io.flutter.plugins.webviewflutter;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class InAppWebViewChromeClient extends WebChromeClient {

    private View mCustomView;
    private CustomViewCallback mCustomViewCallback;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;

    public InAppWebViewChromeClient() {
        super();
    }

    public Bitmap getDefaultVideoPoster() {
        if (mCustomView == null) {
            return null;
        }
        return BitmapFactory.decodeResource(WebViewFlutterPlugin.activity.getResources(), 2130837573);
    }

    public void onHideCustomView() {
        View decorView = WebViewFlutterPlugin.activity.getWindow().getDecorView();
        ((FrameLayout) decorView).removeView(this.mCustomView);
        this.mCustomView = null;
        decorView.setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        WebViewFlutterPlugin.activity.setRequestedOrientation(this.mOriginalOrientation);
        this.mCustomViewCallback.onCustomViewHidden();
        this.mCustomViewCallback = null;
    }

    public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback) {
        if (this.mCustomView != null) {
            onHideCustomView();
            return;
        }
        View decorView = WebViewFlutterPlugin.activity.getWindow().getDecorView();
        this.mCustomView = paramView;
        this.mOriginalSystemUiVisibility = decorView.getSystemUiVisibility();
        this.mOriginalOrientation = WebViewFlutterPlugin.activity.getRequestedOrientation();

        WebViewFlutterPlugin.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        if (this.mCustomView.getWidth() > this.mCustomView.getHeight()) {
//
//        } else {
//            WebViewFlutterPlugin.activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }
        this.mCustomViewCallback = paramCustomViewCallback;
        this.mCustomView.setBackgroundColor(Color.parseColor("#000000"));
        ((FrameLayout) decorView).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));

        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // Set the content to appear under the system bars so that the
//                        // content doesn't resize when the system bars hide and show.
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        // Hide the nav bar and status bar
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}
