package com.fanfq.BTInterphone;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class BTInterphoneActivity extends Activity {
	
	
	private String iTempFileNameString = "iRecorder_";
	private File iRecAudioFile;
	private File iRecAudioDir;
	private File iPlayFile;
	private MediaRecorder iMediaRecorder;

	private ArrayList<String> iRecordFiles;
	private ArrayAdapter<String> iAdapter;
	private TextView iTextView;
	private boolean isSDCardExit;
	private boolean isStopRecord;
	
	
    /** Called when the activity is first created. */
	
	private final boolean D = true;
	private final String TAG = "BTInterphoneActivity";
	private Button choseBtn;
	private Button speakBtn;
	private EditText msgEditText;
	private Button msgSendBtn;
	private Button choseSpeakBtn;
	private Button choseMsgBtn;
	private Button choseSmilyBtn;
	private Button choseImageBtn;
	
	PopupWindow pop;
	MediaRecorder mMediaRecorder;
	View view;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        /* 判断SD Card是否插入 */
		isSDCardExit = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		/* 取得SD Card路径作为录音的文件位置 */
		if (isSDCardExit)
			iRecAudioDir = Environment.getExternalStorageDirectory();
        
        speakBtn = (Button) findViewById(R.id.speakBtn);
        speakBtn.setFocusable(true);
        speakBtn.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == event.ACTION_DOWN){
					speakBtn.setText("松开结束");
					System.out.println("松开结束");
					
				}
				if(event.getAction() == event.ACTION_UP){
					speakBtn.setText("按住对讲");

				}	
				
				return false;
			}
		});
        
        msgEditText = (EditText) findViewById(R.id.msgEditText);
        msgSendBtn = (Button) findViewById(R.id.msgSendBtn);
        
        choseBtn = (Button) findViewById(R.id.choseBtn);
        choseBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(pop.isShowing())
				{
					pop.dismiss();
				}
				else
				{ 
					pop.showAsDropDown(v, 0, -160); 
				}
				
			}
		});
        initPopupWindow();
    }
    
    private void initPopupWindow()
	{
		view = this.getLayoutInflater().inflate(R.layout.popup_window, null);
		pop = new PopupWindow(view, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		pop.setOutsideTouchable(true);
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
		
		choseSpeakBtn = (Button) view.findViewById(R.id.choseSpeakBtn);
        choseSpeakBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("speak");
				msgEditText.setVisibility(View.GONE);
				msgSendBtn.setVisibility(View.GONE);
				speakBtn.setVisibility(View.VISIBLE);
				pop.dismiss();
			}
		});
        
        choseMsgBtn = (Button) view.findViewById(R.id.choseMsgBtn);
        choseMsgBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				msgEditText.setVisibility(View.VISIBLE);
				msgSendBtn.setVisibility(View.VISIBLE);
				speakBtn.setVisibility(View.GONE);
				pop.dismiss();
			}
		});
        
        choseSmilyBtn = (Button) view.findViewById(R.id.choseSmilyBtn);
        choseSmilyBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
        
        choseImageBtn = (Button) view.findViewById(R.id.choseImageBtn);
        choseImageBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pop.dismiss();
			}
		});
	}
    
    @Override
	protected void onStop() {
		if (iMediaRecorder != null && !isStopRecord) {
			/* 停止录音 */
			iMediaRecorder.stop();
			iMediaRecorder.release();
			iMediaRecorder = null;
		}
		super.onStop();
	}
    
}