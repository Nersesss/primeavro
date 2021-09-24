package com.su.primeavto.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.mukesh.OtpView;
import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.view.activity.DashBoardActivity;
import com.su.primeavto.viewmodel.SendViewModel;

import static com.su.primeavto.Constants.TOKEN;
import static com.su.primeavto.util.Connectivity.isOnline;

public class OTPValidationFragment extends BaseFragment {

    SendViewModel sendMessageVM;
    private OtpView otpView;
    private LoadingButton btnSend;

    public OTPValidationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sendMessageVM = provider.get(SendViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp_validation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSend = view.findViewById(R.id.btn_send);
        otpView = view.findViewById(R.id.otp_view);
        otpView.setTextIsSelectable(true);


        btnSend.setOnClickListener(v -> {

            getUserStatus();
        });
    }

    private void getUserStatus() {
        if (isOnline()) {
            Integer otp = (Integer) PreferenceUtils.readStringPreference(getContext(), Constants.PREF_KEY_OTP, 0);

            String phoneNumber = (String) PreferenceUtils.readStringPreference(getContext(), Constants.PREF_KEY_PHONE, "");

            btnSend.startLoading();
            btnSend.setEnabled(false);
            if (otpView.getText() != null && String.valueOf(otp).equals(otpView.getText().toString())) {
                sendMessageVM.getUserStatus(phoneNumber, TOKEN)
                        .observe(getViewLifecycleOwner(), status -> {
                            Toast.makeText(getContext(), String.valueOf(status), Toast.LENGTH_LONG).show();

                            if (status == Constants.UserStatus.ACCEPTED.getValue()) {
                                btnSend.loadingSuccessful();
                                btnSend.setEnabled(true);
                                goToHome();
                            } else if (status == Constants.UserStatus.SUCCESS.getValue()) {
                                btnSend.loadingSuccessful();
                                btnSend.setEnabled(true);
                                goToHome();
//                            Navigation.findNavController(view)
//                                    .navigate(R.id.action_navigation_otp_to_navigation_id);
                            } else {
                                sendMessageVM.addUser(phoneNumber, TOKEN)
                                        .observe(getViewLifecycleOwner(), success -> {
                                            btnSend.setEnabled(true);
                                            if (success.equals("success")) {
                                                btnSend.loadingSuccessful();
                                                getUserStatus();
                                            } else {
                                                btnSend.loadingFailed();
                                                Toast.makeText(getContext(), success, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
            } else {
                btnSend.loadingFailed();
                btnSend.setEnabled(true);
                Toast.makeText(getContext(), getString(R.string.wrong_activation_code), Toast.LENGTH_SHORT).show();
            }
        }else {
            openConnectivityDialog();
        }
    }


    private void openConnectivityDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(R.string.error);
        alertDialogBuilder.setMessage(R.string.message);
        alertDialogBuilder.setNegativeButton(R.string._cancel, (dialog, which) -> dialog.dismiss());

        alertDialogBuilder.setPositiveButton(R.string.refresh, (dialog, which) -> {
            getUserStatus();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }


    private void goToHome() {
        PreferenceUtils.writePreference(getContext(), Constants.PREF_KEY_LOGGED_IN, true);
        Intent intent = new Intent(getContext(), DashBoardActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}