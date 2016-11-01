package com.bawei.version_update;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;


/**
 * 
 * 自定义类，获取当前apk的版本
 * @author 浅议爱
 *
 */
public class GetVersionCode {
	
	public int getVersionCode(Context context) {
		int versionCode = 0;
		try{
			// 获取软件版本号，
			versionCode = context.getPackageManager().getPackageInfo(
					"com.bawei.version_update", 0).versionCode;
		}catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

}
