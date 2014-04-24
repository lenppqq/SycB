package com.example.sycb;

import GreatDouBaba.GDB_DTB_High_Interface;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.*;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class WebActivity extends Activity {

	private WebView webView;
	private GestureDetector mGestureDetector;
	private ImageView img;
	public static GDB_DTB_High_Interface GDHI;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		System.out.println("Start");
		setContentView(R.layout.webcontent);

		webView = (WebView) findViewById(R.id.webView);

		webView.getSettings().setJavaScriptEnabled(true);

		webView.loadUrl("http://www.google.com");

		GDHI = new GDB_DTB_High_Interface(this);
		webView.setWebViewClient(new WebViewClient() {

			public void onPageFinished(WebView view, String url) {
				System.out.println(url);
				WebActivity.GDHI.setAction("viewPage");
				WebActivity.GDHI.setWebview(view);
				Thread viewP = new Thread(WebActivity.GDHI);
				viewP.start();

				/*
				 * String[][] test = WebActivity.GDHI.getHistory();
				 * if(test!=null) for(int i=0; i<test.length; i++){
				 * System.out.println(test[i][0]+ "-"+ test[i][1]+ "-"+
				 * test[i][2]); } else
				 * System.out.println("SHITTTTTTTTTTTTTTTTTTT"); String[] test2
				 * = WebActivity.GDHI.getUrlInfo(test[0][1]);
				 * System.out.println(test2[0]+ "\n"+ test2[1]+ "\n"+ test2[2]);
				 */
			}
		});
		img = (ImageView) findViewById(R.id.imageView1);
		mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
		webView.setOnTouchListener(new OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		        Log.i(getClass().getName(), "onTouch-----" + getActionName(event.getAction()));
		        mGestureDetector.onTouchEvent(event);
		        //Must return true to obtain full event
		        return false;
		    }
		});
		// GDHI.viewPage(webView);
		System.out.println("End");

		// String customHtml =
		// "<html><body><h2>Greetings from JavaCodeGeeks</h2></body></html>";
		// webView.loadData(customHtml, "text/html", "UTF-8");

	}
	
	class MyOnGestureListener extends SimpleOnGestureListener {
		@Override
		public void onLongPress(MotionEvent e) {
		    Log.i(getClass().getName(), "onLongPress-----" + getActionName(e.getAction()));
		    //Set image visible
		    img.setVisibility(View.VISIBLE);
		    img.setX(e.getX());
		    img.setY(e.getY());
		}
	}
	
	private String getActionName(int action) {
	    String name = "";
	    switch (action) {
	        case MotionEvent.ACTION_DOWN: {
	            name = "ACTION_DOWN";
	            break;
	        }
	        case MotionEvent.ACTION_MOVE: {
	            name = "ACTION_MOVE";
	            break;
	        }
	        case MotionEvent.ACTION_UP: {
	            name = "ACTION_UP";
	            break;
	        }
	        default:
	        break;
	    }
	    return name;
	}

}


