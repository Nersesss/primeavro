package com.su.primeavto.util;

import android.content.res.Resources;

public class MeasurementUtils {


	public static int pxToDp(int px) {
		return Math.round(px / (Resources.getSystem().getDisplayMetrics().densityDpi / 160f));
	}

	public static int dpToPx(int dp) {
		return Math.round(dp * (Resources.getSystem().getDisplayMetrics().densityDpi / 160f));
	}
}