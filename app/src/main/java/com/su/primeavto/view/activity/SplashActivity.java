package com.su.primeavto.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.su.primeavto.R;
import com.su.primeavto.util.PreferenceUtils;

import static com.su.primeavto.Constants.PREF_KEY_LOGGED_IN;

public class SplashActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SplashActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Boolean loggedIn = (Boolean) PreferenceUtils.readStringPreference(this, PREF_KEY_LOGGED_IN, false);
        if (loggedIn != null && loggedIn) {
            go(DashBoardActivity.class);
        } else {
            go(AuthorizationActivity.class);
        }

        finish();
    }

    private void go(Class activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}