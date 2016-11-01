package com.bawei.version_update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
/**
 * �汾���£�ʵ�ְ汾���£������������°汾��apk
 * 
 * @author Administrator
 * 
 */
@SuppressLint("InflateParams")
public class MainActivity extends Activity {
	
	//���������
    private ProgressBar progressbar1;
    
    //����������
    private int pregress_cont;
    
    //����汾�ţ�����õ��˵�ǰapk�İ汾�ţ�   
	private GetVersionCode getcode;
	private List<NameValuePair> pair;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getcode = new GetVersionCode();
		
	}

	//����¼�
	public void version_check(View view){
		pair = new ArrayList<NameValuePair>();
		pair.add(new BasicNameValuePair("", ""));
		new Thread(){
			public void run() {
//				���﷢������
//				String str = http_post("http://169.254.61.100:8080/1310A/json.txt",pair);
				
				
				String str = Httputils.http_post("http://www.baidu.com",pair);
				Log.i("TAG","���ؽ��=="+str);
				if(null!=str && !str.equals("000")){
					//����Է���ֵ�����жϣ�����������汾���ڵ�ǰ�汾����ô�õ���һ������ʾ����
//					��Ϊ����ԭ������ֱ�ӵ���һ����ѡ�������ļ�
					mHandler.sendEmptyMessage(1);
				}else{
//					��ʾ�û�������ʧ��
				}
			};
		}.start();
	}
	
	//չʾdialog
	public void showwdialog(){
//		int code = getcode.getVersionCode(MainActivity.this);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("�����£�");
		builder.setMessage("�Ƿ����ظ���");
		builder.setPositiveButton("��������", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showload();
			}
		}).setNegativeButton("�Ժ���˵", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		
		builder.show();
		
	}
	
	
	public void showload(){
//		��ʼ�����ļ�
		new Thread(){
			public void run() {
				Download.loadFile("http://gdown.baidu.com/data/wisegame/f98d235e39e29031/baiduxinwen.apk",mHandler);
			};
		}.start();
		
//		�����Ƿ�����̨���ؿ�
		LinearLayout layout = (LinearLayout)LayoutInflater.from(MainActivity.this).inflate(R.layout.loaddialog,null);
		progressbar1 = (ProgressBar) layout.findViewById(R.id.progressbar1);
		Builder my_builder  = new Builder(MainActivity.this);
		my_builder.setView(layout);
		
		my_builder.setPositiveButton("��̨����", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		my_builder.show();
	}
	
	
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
            case 1:
            	showwdialog();
            	break;
            case 11:  
                // ��������   ���ý�����λ��   
            	pregress_cont = msg.arg1;
            	
            	progressbar1.setProgress(pregress_cont);
                break;  
            case 200:  
//            	֪ͨ��װ���ص�apk
            	Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(), "baiduxinwen.apk")),
						"application/vnd.android.package-archive");
				MainActivity.this.startActivity(intent);
                break;  
            default:  
                break;  
            }  
        };  
    };
 

}
