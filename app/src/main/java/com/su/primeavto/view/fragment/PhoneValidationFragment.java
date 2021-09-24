package com.su.primeavto.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.view.customview.CustomEditView;
import com.su.primeavto.viewmodel.SendViewModel;

import java.util.Random;

import static com.su.primeavto.util.Connectivity.isOnline;

public class PhoneValidationFragment extends BaseFragment {

    private SendViewModel sendMessageVM;
    private LoadingButton btnSend;
    private CustomEditView edtPhone;

    public PhoneValidationFragment() {
        // Required empty public constructor
    }

    public static PhoneValidationFragment newInstance() {

        return new PhoneValidationFragment();
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
        return inflater.inflate(R.layout.fragment_phone_validation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSend = view.findViewById(R.id.btn_send);
        edtPhone = view.findViewById(R.id.edt_phone_number_of_authorisation);

        btnSend.setOnClickListener(v -> {
            sendNumber();
        });
    }

    private void sendNumber() {
        if (isOnline()) {
            btnSend.startLoading();
            btnSend.setEnabled(false);
            String phoneNumber = edtPhone.getText();
            int otp = d4Results();
            String text = "Ваш код активации " + otp;
            PreferenceUtils.writePreference(getContext(), Constants.PREF_KEY_OTP, otp);
            PreferenceUtils.writePreference(getContext(), Constants.PREF_KEY_PHONE, phoneNumber);

            sendMessageVM.sendMessage("t89261921593", "828965", phoneNumber, text)
                    .observe(getViewLifecycleOwner(), accepted -> {
                        if (accepted) {
                            btnSend.setEnabled(true);
                            btnSend.loadingSuccessful();
                            Navigation.findNavController(btnSend)
                                    .navigate(R.id.action_navigation_phone_to_navigation_otp);
                        } else {
                            btnSend.setEnabled(true);
                            btnSend.loadingFailed();
                            Toast.makeText(getContext(), "Ваш номер телефона неправильный", Toast.LENGTH_SHORT).show();
                        }
                    });
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
            sendNumber();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }


    private int d4Results() {
        final Random random = new Random();
        return random.nextInt(9999);
    }
}