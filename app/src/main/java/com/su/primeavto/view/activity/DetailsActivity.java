package com.su.primeavto.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.model.NameValueModel;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.view.adapter.DetailsAdapter;
import com.su.primeavto.view.adapter.SliderAdapter;
import com.su.primeavto.view.fragment.ConfigureToPayFragment;
import com.su.primeavto.viewmodel.SendViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.su.primeavto.Constants.TOKEN;
import static com.su.primeavto.model.AutoModel.appendValuta;
import static com.su.primeavto.util.Connectivity.isOnline;

public class DetailsActivity extends BaseActivity {

    private SliderView sliderView;
    private LoadingButton btnRent;
    private SendViewModel sendMessageVM;
    private AutoModel car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sendMessageVM = provider.get(SendViewModel.class);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        car = (AutoModel) intent.getSerializableExtra(Constants.INTENT_KEY_AVTO);
        initViews(car);
        setTitle(car.getName());
    }

    private void getUserStatus() {
        if (isOnline()) {
            btnRent.startLoading();
            String phoneNumber = (String) PreferenceUtils.readStringPreference(
                    DetailsActivity.this, Constants.PREF_KEY_PHONE, "");
            sendMessageVM.getUserStatus(phoneNumber, TOKEN)
                    .observe(this, status -> {
                        if (status == Constants.UserStatus.ACCEPTED.getValue()) {
                            btnRent.loadingSuccessful();
                            replaceFragment(ConfigureToPayFragment.newInstance(car));

                        } else if (status == Constants.UserStatus.SUCCESS.getValue()) {
                            btnRent.loadingSuccessful();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btnRent.reset();
                                }
                            }, 500);
                            goToIDValidation();
                        } else {
                            btnRent.loadingFailed();

                            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {
            openConnectivityDialog();
        }
    }


    private void openConnectivityDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.error);
        alertDialogBuilder.setMessage(R.string.message);
        alertDialogBuilder.setNegativeButton(R.string._cancel, (dialog, which) -> dialog.dismiss());

        alertDialogBuilder.setPositiveButton(R.string.refresh, (dialog, which) -> {
            getUserStatus();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }

    private void goToIDValidation() {
        Intent intent = new Intent(this, IDValidationActivity.class);
        intent.putExtra(Constants.INTENT_KEY_AVTO, car);
        startActivity(intent);
    }


    private void initViews(AutoModel car) {
        sliderView = findViewById(R.id.image_slider);
        btnRent = findViewById(R.id.btn_rent);
        btnRent.setOnClickListener(v -> {
            getUserStatus();
        });
        RecyclerView recViewOfCharacteristics = findViewById(R.id.rec_view_characteristic);
        DetailsAdapter adapterOfCharacteristics = new DetailsAdapter(this);
        recViewOfCharacteristics.setAdapter(adapterOfCharacteristics);

        RecyclerView recViewOfAdditionalServices = findViewById(R.id.rec_view_additional_services);
        DetailsAdapter adapterOfAdditionalServices = new DetailsAdapter(this);
        recViewOfAdditionalServices.setAdapter(adapterOfAdditionalServices);

        RecyclerView recViewOfRentTerms = findViewById(R.id.rec_view_rent_terms);
        DetailsAdapter adapterOfRentTerms = new DetailsAdapter(this);
        recViewOfRentTerms.setAdapter(adapterOfRentTerms);

        RecyclerView recViewOfPrice = findViewById(R.id.rec_view_price);
        DetailsAdapter adapterOfPrice = new DetailsAdapter(this);
        recViewOfPrice.setAdapter(adapterOfPrice);

        adapterOfCharacteristics.setData(getCharacteristics(car));
        adapterOfAdditionalServices.setData(getAdditionalServices(car));
        adapterOfRentTerms.setData(getRentTerms(car));
        adapterOfPrice.setData(getPrice(car));

        configureSlider(car);
    }

    private List<NameValueModel> getPrice(AutoModel car) {
        List<NameValueModel> result = new ArrayList<>();
        result.add(appendValuta(car.getOneDayPrice()));
        result.add(appendValuta(car.getTwoFiveDayPrice()));
        result.add(appendValuta(car.getThreeFifteenDayPrice()));
        result.add(appendValuta(car.getFifteenThirtyDayPrice()));
        result.add(appendValuta(car.getDayOffPrice()));
        result.add(appendValuta(car.getWorkingWeekPrice()));
        return result;
    }

    private List<NameValueModel> getRentTerms(AutoModel car) {
        List<NameValueModel> result = new ArrayList<>();
        result.add(car.getMinExperience());
        result.add(car.getMinAge());
        result.add(car.getDeposit());
        return result;
    }

    private List<NameValueModel> getAdditionalServices(AutoModel car) {
        List<NameValueModel> result = new ArrayList<>();
        result.add(appendValuta(car.getDelivery()));
        result.add(appendValuta(car.getRefund()));
        result.add(appendValuta(car.getUnlimitedTariff()));
        result.add(appendValuta(car.getRemoveFranchise()));
        result.add(appendValuta(car.getWashing()));
        return result;
    }

    private List<NameValueModel> getCharacteristics(AutoModel car) {
        List<NameValueModel> characteristics = new ArrayList<>();
        characteristics.add(car.getYear());
        characteristics.add(car.getEngine());
        characteristics.add(AutoModel.toValue(car.getColor()));
        characteristics.add(car.getSalon());
        characteristics.add(car.getKpp());
        characteristics.add(car.getDriveUnit());
        characteristics.add(car.getFuel());
        characteristics.add(car.getPlaceNumber());
        characteristics.add(AutoModel.toValue(car.getEntertainment()));
        characteristics.add(car.getAbs());
        characteristics.add(car.getConditioning());
        characteristics.add(car.getPowerSteering());
        characteristics.add(AutoModel.toValue(car.getAssistance()));
        return characteristics;
    }

    private void configureSlider(AutoModel car) {
        List<String> imageUrls = car.getAdditionalPhotos().getValues();
        List<String> rightImageUrls = new ArrayList<>();
        for (String url :
                imageUrls) {
            rightImageUrls.add(url.replace("gorodavto", "primeavto"));
        }
        SliderAdapter adapter = new SliderAdapter(this, rightImageUrls);

        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.OVER_SCROLL_ALWAYS);

        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}