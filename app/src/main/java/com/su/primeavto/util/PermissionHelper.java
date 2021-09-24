package com.su.primeavto.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.annotation.IntDef;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

@MainThread
public class PermissionHelper {


	private static WeakReference<Callback> sWeakCallback;


	@Retention(RetentionPolicy.SOURCE)
	@IntDef({PermissionTypes.STORAGE_WRITE})
	@interface PermissionType {}


	public static class PermissionTypes {
		public static final int STORAGE_WRITE = 4862;
	}


	public interface Callback {
		void onPermissionOk(@PermissionType int permissionType);
	}


	public static class StorageWrite {

		public static void handlePermission(Activity activity, Callback callback) {
			if (isGranted(ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)))
				callback.onPermissionOk(PermissionTypes.STORAGE_WRITE);
			else requestPermission(activity, callback);
		}

		private static void requestPermission(Activity activity, Callback callback) {
			sWeakCallback = new WeakReference<>(callback);
			ActivityCompat.requestPermissions(activity, new String[]{
					Manifest.permission.WRITE_EXTERNAL_STORAGE}, PermissionTypes.STORAGE_WRITE);
		}
	}


	/*
	 *  Put this in onActivityResult
	 */
	public static void handlePermissionResult(int requestCode, @NonNull int[] grantResults) {
		if (requestCode == PermissionTypes.STORAGE_WRITE && grantResults.length > 0 && isGranted(grantResults[0]))
			callBack(PermissionTypes.STORAGE_WRITE);
	}


	private static void callBack(int storageWrite) {
		Callback callback = sWeakCallback.get();
		if (callback != null) callback.onPermissionOk(PermissionTypes.STORAGE_WRITE);
	}


	private static boolean isGranted(int permission) {
		return permission == PackageManager.PERMISSION_GRANTED;
	}


	private PermissionHelper() {}
}
