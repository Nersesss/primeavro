package com.su.primeavto.view.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.su.primeavto.OnBackClickListener;
import com.su.primeavto.R;
import com.su.primeavto.view.fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity implements OnBackClickListener {
    public ViewModelProvider provider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        provider = new ViewModelProvider(this);
    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        t.replace(R.id.container, fragment).commit();
    }

    @Override
    public void onBackPressed() {

        boolean handled = false;
        for (final Fragment f : getSupportFragmentManager().getFragments())
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPress();
            }
        if (!handled) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onBackPress() {
        onBackPressed();
        return false;
    }
}
