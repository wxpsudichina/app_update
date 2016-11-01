package com.bawei.version_update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * 定义下载类
 * 该类调用网络请求的方式，对apk文件进行下载
 * 并且瞎子啊到sd中，
 * @author 浅议爱
 *
 */
public class Download {

	
	//下载文件
    public static void loadFile(String url,Handler mHandler) {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			
			HttpEntity entity = response.getEntity();
			float length = entity.getContentLength();

			InputStream is = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (is != null) {
				File file = new File(Environment.getExternalStorageDirectory(),
						"baiduxinwen.apk");
				fileOutputStream = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int ch = -1;
				float count = 0;
				while ((ch = is.read(buf)) != -1) {
					fileOutputStream.write(buf, 0, ch);
					count += ch;
//					sendMsg(1,(int) (count*100/length));
					//正在下载，计算下载大小
					int pregress_cont = (int) (((float) count / length) * 100); 
					Message mes = new Message();
					mes.arg1 = pregress_cont;
					mes.what= 11;
					mHandler.sendMessage(mes);
				}
			}
			
			//下载完成，发送消息---通知安装（非静默安装）
			mHandler.sendEmptyMessage(200);
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		} catch (Exception e) {
//			sendMsg(-1,0);
		}
	}
}
