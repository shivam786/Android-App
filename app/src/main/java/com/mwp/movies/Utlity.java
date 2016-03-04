package com.mwp.movies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utlity {

	public static boolean isOnline(Context ct) {
		ConnectivityManager cm = (ConnectivityManager)ct.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
}
