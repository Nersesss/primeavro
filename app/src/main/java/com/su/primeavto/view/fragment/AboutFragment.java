package com.su.primeavto.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.su.primeavto.R;
import com.su.primeavto.view.activity.WebViewActivity;

import java.util.HashMap;
import java.util.LinkedList;

public class AboutFragment extends BaseFragment {

    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 12;
    private ImageView imgBackArrow;
    private TextView textToolbarTitle, star, webPage;

    private ViewGroup email, phone;
    private View root;
    private String phoneNumber;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_about, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showActionBar(false);

        email = view.findViewById(R.id.layout_email);
        email.setOnClickListener(v -> sendEmail(""));

        phone = view.findViewById(R.id.layout_phone);
        phone.setOnClickListener(v -> call("+37498456780"));

        star = view.findViewById(R.id.text_star);
        star.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Спасибо за Вашу оценку!", Toast.LENGTH_SHORT).show();
        });
        webPage = view.findViewById(R.id.text_web_page);
        webPage.setOnClickListener(v -> openWebPage());
        imgBackArrow = view.findViewById(R.id.img_back_arrow);
        imgBackArrow.setOnClickListener(v -> onBackPress());
        textToolbarTitle = view.findViewById(R.id.toolbar_title);
        textToolbarTitle.setText(R.string.about);
    }

    private void openWebPage() {
        Intent intent = new Intent(getContext(), WebViewActivity.class);
        getContext().startActivity(intent);
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


    private void sendEmail(String message) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"nersesam@constant-tech.biz"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Help to");
        i.putExtra(Intent.EXTRA_TEXT, message);
        try {
            getContext().startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    private void call(String number) {
        phoneNumber = number;

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_PHONE_CALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                call(phoneNumber);
            } else {
                Toast.makeText(getContext(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
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