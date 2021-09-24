package com.su.primeavto.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.savvi.rangedatepicker.CalendarPickerView;
import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.databinding.FragmentConfigureToPayBinding;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.model.NameValueModel;
import com.su.primeavto.util.DateUtils;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.util.StringUtil;
import com.su.primeavto.view.activity.WebViewPaymentActivity;
import com.su.primeavto.viewmodel.SendViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.su.primeavto.Constants.ARG_CAR;
import static com.su.primeavto.Constants.RUB;
import static com.su.primeavto.util.Connectivity.isOnline;


public class ConfigureToPayFragment extends BaseFragment {
    public static final String TAG = ConfigureToPayFragment.class.getSimpleName();
    private AutoModel autoModel;
    CalendarPickerView picker;
    private TextView textSelectedDates, textCarPrice, textFullPrice;
    private CheckBox chbDelivery, chbRefund, chbUnlimitedDrive, chbWithdrawFranchises, chbWashing;
    private LoadingButton btnPay;
    private int fullPrice;
    private int mWashingPrice;
    private int mWithdrawFranchisesPrice;
    private int mUnlimitedDrivePrice;
    private int mRefundPrice;
    private int mDeliveryPrice;

    private boolean isWashingPrice;
    private boolean isWithdrawFranchisesPrice;
    private boolean isUnlimitedDrivePrice;
    private boolean isRefundPrice;
    private boolean isDeliveryPrice;

    private SendViewModel sendViewModel;
    private String phone;

    public ConfigureToPayFragment() {
        // Required empty public constructor
    }

    public static ConfigureToPayFragment newInstance(AutoModel autoModel) {
        ConfigureToPayFragment fragment = new ConfigureToPayFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR, autoModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = (String) PreferenceUtils.readStringPreference(getContext(), Constants.PREF_KEY_PHONE, "");
        sendViewModel = provider.get(SendViewModel.class);
        if (getArguments() != null) {
            autoModel = (AutoModel) getArguments().getSerializable(ARG_CAR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configure_to_pay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initData();
    }

    private void initData() {

        btnPay.setOnClickListener(v -> {
            sendDate();

        });

        Calendar prevYear = Calendar.getInstance();
//        prevYear.add(Calendar.YEAR, -1);
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        picker.init(prevYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault()))
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(new Date());
        setDateRange();
// deactivates given dates, non selectable
//                .withDeactivateDates(list);
// highlight dates in red color, mean they are aleady used.
//                .withHighlightedDates(arrayList)
// add subtitles to the dates pass a arrayList of SubTitle objects with date and text
//                .withSubtitles(getSubtitle())

        picker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                setDateRange();
            }

            @Override
            public void onDateUnselected(Date date) {
//                setDateRange();
            }
        });
        picker.setOnInvalidDateSelectedListener(date ->
                Toast.makeText(
                        getContext(),
                        DateUtils.dateToString(date) + " прошедший...",
                        Toast.LENGTH_SHORT).show());

        NameValueModel delivery = autoModel.getDelivery();
        NameValueModel refund = autoModel.getRefund();
        NameValueModel unlimitedTariff = autoModel.getUnlimitedTariff();
        NameValueModel removeFranchise = autoModel.getRemoveFranchise();

        configureChB(delivery, chbDelivery);
        configureChB(refund, chbRefund);
        configureChB(unlimitedTariff, chbUnlimitedDrive);
        configureChB(removeFranchise, chbWithdrawFranchises);

//

        configureWashingChB();

        chbDelivery.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDeliveryPrice = isChecked;
            mDeliveryPrice += isChecked ? getPrice(delivery) : -getPrice(delivery);
            fullPrice += isChecked ? getPrice(delivery) : -getPrice(delivery);
            configureFullPrice();
        });
        chbRefund.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRefundPrice = isChecked;
            mRefundPrice += isChecked ? getPrice(refund) : -getPrice(refund);
            fullPrice += isChecked ? getPrice(refund) : -getPrice(refund);
            configureFullPrice();
        });
        chbUnlimitedDrive.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isUnlimitedDrivePrice = isChecked;
            mUnlimitedDrivePrice += isChecked ? getPrice(unlimitedTariff) : -getPrice(unlimitedTariff);
            fullPrice += isChecked ? getPrice(unlimitedTariff) : -getPrice(unlimitedTariff);
            configureFullPrice();
        });
        chbWithdrawFranchises.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isWithdrawFranchisesPrice = isChecked;
            mWithdrawFranchisesPrice += isChecked ? getPrice(removeFranchise) : -getPrice(removeFranchise);
            fullPrice += isChecked ? getPrice(removeFranchise) : -getPrice(removeFranchise);
            configureFullPrice();
        });

    }

    private int getPrice(NameValueModel nameValueModel) {
        return Integer.parseInt(nameValueModel.getValue());
    }

    private void configureChB(NameValueModel nameValueModel, CheckBox checkBox) {
        int price = Integer.parseInt(nameValueModel.getValue());
        String text = nameValueModel.getName() + ": " + price + RUB;
        checkBox.setText(text);
    }

    private void configureWashingChB() {

        int washingPrice = Integer.parseInt(autoModel.getWashing().getValue());
        String washing = autoModel.getWashing().getName() + ": " + washingPrice + RUB;
        chbWashing.setText(washing);

        isWashingPrice = true;
        chbWashing.setChecked(isWashingPrice);
        chbWashing.setEnabled(false);
        mWashingPrice = washingPrice;
        fullPrice += mWashingPrice;
        configureFullPrice();
    }

    private void sendDate() {
        if (isOnline()) {
            btnPay.startLoading();

            WebViewPaymentActivity.start(getContext(), fullPrice, getTitle(), getBody());
        } else {
            openConnectivityDialog();
        }
    }

    private String getTitle() {
        return "Оплата от " + phone;
    }

    private String getBody() {
        Date startDate = picker.getSelectedDates().get(0);
        Date endDate = picker.getSelectedDates().get(picker.getSelectedDates().size() - 1);
        String start = DateUtils.dateToString(startDate);
        String end = DateUtils.dateToString(endDate);

        return "Клиент с номером " + phone + " сделать оплату:"
                + "\r\nАвто: " + autoModel.getName()
                + "\nДата: от " + start + " до " + end
                + "\r\nДоп услуги:\nДоставка в пределах МКАД: " + (isDeliveryPrice ? "Да" : "Нет")
                + "\r\nЗабор в пределах МКАД: " + (isRefundPrice ? "Да" : "Нет")
                + "\r\nБезлимит пробег за пределами МО: " + (isUnlimitedDrivePrice ? "Да" : "Нет")
                + "\nСнять франшизы: " + (isWithdrawFranchisesPrice ? "Да" : "Нет")
                + "\nМойка: Да\n" + "ОПЛАЧЕНО " + fullPrice + Constants.RUB;
    }

    private void openConnectivityDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(R.string.error);
        alertDialogBuilder.setMessage(R.string.message);
        alertDialogBuilder.setNegativeButton(R.string._cancel, (dialog, which) -> dialog.dismiss());

        alertDialogBuilder.setPositiveButton(R.string.refresh, (dialog, which) -> {
            sendDate();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }

    private void configureFullPrice() {
        String fullPriceKey = getString(R.string.full_price) + ": ";
        String fullPriceValue = fullPrice + RUB;
        textFullPrice.setText(String.format("%s%s", fullPriceKey, fullPriceValue));
        StringUtil.setSpannableText(getContext(), textFullPrice, fullPriceKey, fullPriceValue);
    }

    private void setDateRange() {
        int days = picker.getSelectedDates().size();

        String key = getString(R.string.days_number) + ": ";
        String value = String.valueOf(days);

        textSelectedDates.setText(String.format("%s%s", key, value));
        StringUtil.setSpannableText(getContext(), textSelectedDates, key, value);

        String priceKey = getString(R.string.car_price) + ": ";

        int oneDayPrice = Integer.parseInt(autoModel.getOneDayPrice().getValue());
        int datePrice = days * oneDayPrice;

        fullPrice = datePrice + mDeliveryPrice + mRefundPrice + mUnlimitedDrivePrice + mWithdrawFranchisesPrice + mWashingPrice;
        String priceValue = datePrice + RUB;
        textCarPrice.setText(String.format("%s%s", priceKey, priceValue));
        StringUtil.setSpannableText(getContext(), textCarPrice, priceKey, priceValue);

        configureFullPrice();
    }

    private void initViews(View view) {
        btnPay = view.findViewById(R.id.btn_pay);
        picker = view.findViewById(R.id.calendar_view);
        textSelectedDates = view.findViewById(R.id.text_selected_dates);
        textCarPrice = view.findViewById(R.id.text_price);
        textFullPrice = view.findViewById(R.id.text_full_price);
        chbDelivery = view.findViewById(R.id.chb_delivery);
        chbRefund = view.findViewById(R.id.chb_pick_up);
        chbUnlimitedDrive = view.findViewById(R.id.chb_unlimited_drive);
        chbWithdrawFranchises = view.findViewById(R.id.chb_withdraw_franchises);
        chbWashing = view.findViewById(R.id.chb_washing);
    }
}