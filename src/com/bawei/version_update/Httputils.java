package com.bawei.version_update;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class Httputils {
	
	
	//请求服务器
		public static String http_post(String url,List<NameValuePair> pair){
			String str = "";
			HttpPost httpRequest = new HttpPost(url);
			try {
				httpRequest.setEntity(new UrlEncodedFormEntity(pair,HTTP.UTF_8));
//				HttpResponse httpResponse=new DefaultHttpClient().execute(httpRequest);
				HttpResponse response = new DefaultHttpClient().execute(httpRequest);
				if(response.getStatusLine().getStatusCode()==200){
					str = EntityUtils.toString(response.getEntity());
					Log.i("TAG", "请求返回结果==="+str.toString());
				}else{
					//请求失败返回值
					str = "000";
				}
			} catch (Exception e) {
				e.printStackTrace();
				//请求失败返回值
				str = "000";
			} 
			return str;
		}

}
