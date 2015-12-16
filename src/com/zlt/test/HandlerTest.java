package com.zlt.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HandlerTest extends Activity {
	 private Handler handler = null;  
	    private ProgressBar progressBar = null;  
	    private int i = 0;  
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.handler_layout);  
	          
	        progressBar = (ProgressBar)findViewById(R.id.pb_id);  
	        Button start = (Button)findViewById(R.id.start);  
	        Button stop = (Button)findViewById(R.id.stop);  
	          
	        System.out.println("log1 : "+Thread.currentThread().getName());  
//	         
	        HandlerThread ht = new HandlerThread("myHandlerThread");
	        ht.start();
	        
	        handler = new Handler(ht.getLooper(),new Handler.Callback() {  
	            @Override  
	            public boolean handleMessage(Message msg) {  
	                // TODO Auto-generated method stub  
	                System.out.println("log2 : " + Thread.currentThread().getName());  
	                progressBar.setProgress(msg.arg1);  
	                progressBar.setSecondaryProgress(msg.arg1 + 5 );  
	                return false;  
	            }  
	        });  
	          
	        start.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                // TODO Auto-generated method stub  
	                handler.post(run);  
	            }  
	        });  
	           
	        stop.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                // TODO Auto-generated method stub  
	                handler.removeCallbacks(run);  
	            }  
	        });  
	    }  
	  
	      
	    Runnable run = new Runnable() {  
	        @Override  
	        public void run() {  
	            // TODO Auto-generated method stub  
	            System.out.println("log3 : "+Thread.currentThread().getName());  
	            handler.postDelayed(run, 2000);  
	            Message msg = handler.obtainMessage();  
	            msg.arg1 = i * 10 ;  
	            i++;  
	            handler.sendMessage(msg);  
	        }  
	    };  
	
//	 	Handler handler = null;  
//	  
//	    @Override  
//	    protected void onCreate(Bundle savedInstanceState) {  
//	        super.onCreate(savedInstanceState);  
//	        setContentView(R.layout.handler_layout);  
//	  
//	        System.out.println("log1 : " + Thread.currentThread().getId());  
//	  
//	        HandlerThread ht = new HandlerThread("MyThread");  
//	        ht.start();  
//	        handler = new Handler(ht.getLooper(), new Handler.Callback() {  
//	            @Override  
//	            public boolean handleMessage(Message msg) {  
//	                // TODO Auto-generated method stub  
//	                int tmp = msg.arg1;  
//	                System.out.println("log2 : " + Thread.currentThread().getId() + ":" + tmp);  
//	                return false;  
//	            }  
//	        });  
//	  
//	        Button btn = (Button) findViewById(R.id.start);  
//	        btn.setOnClickListener(new View.OnClickListener() {  
//	  
//	            @Override  
//	            public void onClick(View v) {  
//	                // TODO Auto-generated method stub  
//	                handler.post(run);  
//	            }  
//	        });  
//	  
//	        Button btn2 = (Button) findViewById(R.id.stop);  
//	        btn2.setOnClickListener(new View.OnClickListener() {  
//	  
//	            @Override  
//	            public void onClick(View v) {  
//	                // TODO Auto-generated method stub  
//	                handler.removeCallbacks(run);  
//	            }  
//	        });  
//	    }  
//	  
//	    Runnable run = new Runnable() {  
//	  
//	        @Override  
//	        public void run() {  
//	            // TODO Auto-generated method stub  
//	            handler.postDelayed(run, 5000);  
//	            Message msg = handler.obtainMessage();  
//	            msg.arg1 = 1123;  
//	            handler.sendMessage(msg);  
//	            System.out.println("log3 : " + Thread.currentThread().getId());  
//	        }  
//	    };  
}
