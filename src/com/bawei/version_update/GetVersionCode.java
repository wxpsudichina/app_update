package com.bawei.version_update;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;


/**
 * 
 * �Զ����࣬��ȡ��ǰapk�İ汾
 * @author ǳ�鰮
 *
 */
public class GetVersionCode {
	
	public int getVersionCode(Context context) {
		int versionCode = 0;
		try{
			// ��ȡ����汾�ţ�
			versionCode = context.getPackageManager().getPackageInfo(
					"com.bawei.version_update", 0).versionCode;
		}catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}

}
