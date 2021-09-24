package com.su.primeavto.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.view.activity.SplashActivity;

public class MenuFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textAbout = view.findViewById(R.id.text_about);
        textAbout.setOnClickListener(v -> Navigation.findNavController(view)
                .navigate(R.id.action_navigation_menu_to_navigation_about));

        TextView textRentTerms = view.findViewById(R.id.text_rent_terms);
        textRentTerms.setOnClickListener(v -> Navigation.findNavController(view)
                .navigate(R.id.action_navigation_menu_to_navigation_rent_terms));

        TextView textLogOut = view.findViewById(R.id.text_loge_out);

        textLogOut.setOnClickListener(v -> {
            cleanPreferences();
            SplashActivity.start(getContext());
            getActivity().finish();
        });
    }

    private void cleanPreferences() {
        PreferenceUtils.removeFromPreference(getContext(), Constants.PREF_KEY_PHONE);
        PreferenceUtils.removeFromPreference(getContext(), Constants.PREF_KEY_OTP);
        PreferenceUtils.removeFromPreference(getContext(), Constants.PREF_KEY_LOGGED_IN);
    }

}