package com.su.primeavto.view.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.su.primeavto.R;


public class RentTermsFragment extends BaseFragment {

    private ImageView imgBackArrow, imgLogo;
    private TextView textToolbarTitle;

    private View root;

    public RentTermsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_rent_terms, container, false);
        showActionBar(false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLogo = view.findViewById(R.id.toolbar_logo);
        imgLogo.setVisibility(View.GONE);
        imgBackArrow = view.findViewById(R.id.img_back_arrow);
        imgBackArrow.setOnClickListener(v -> onBackPress());
        textToolbarTitle = view.findViewById(R.id.toolbar_title);
        textToolbarTitle.setText(R.string.rentTerms);
    }

    private void showActionBar(boolean show) {
        if (getActivity() != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            if (show) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().show();
            } else {
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        showActionBar(true);
    }

    @Override
    public boolean onBackPress() {
        Navigation.findNavController(root).popBackStack();

        return super.onBackPress();
    }
}