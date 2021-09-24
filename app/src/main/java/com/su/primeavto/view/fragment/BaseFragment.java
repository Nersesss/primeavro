package com.su.primeavto.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.su.primeavto.OnBackClickListener;
import com.su.primeavto.R;

public class BaseFragment extends Fragment implements OnBackClickListener {

    public ViewModelProvider provider;

    @Override
    public boolean onBackPress() {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        provider = new ViewModelProvider(this);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        t.replace(R.id.container, fragment).commit();
    }

}
