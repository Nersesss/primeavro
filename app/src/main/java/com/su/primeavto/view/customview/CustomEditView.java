package com.su.primeavto.view.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.util.PreferenceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static com.su.primeavto.util.MeasurementUtils.dpToPx;

public class CustomEditView extends LinearLayout {

    private String TAG = CustomEditView.class.getSimpleName();
    private Context context;
    private EditText edtTextCustomView;
    private ViewGroup layoutCountryCode;
    private ImageView startImageView;
    private int blue;
    private TextView textCode;
    private String countryCodeValue;
    private boolean isLeftIconChangeable;
    private TelephonyManager tm;

    public CustomEditView(Context context) {
        super(context);
        init(context, null);
    }

    public CustomEditView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public CustomEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    public CustomEditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.custom_edit_view, this);


        this.context = context;
        blue = ContextCompat.getColor(context, R.color.appRed);

        textCode = findViewById(R.id.text_code);
        startImageView = findViewById(R.id.img_start);
        layoutCountryCode = findViewById(R.id.layout_country_code);
        edtTextCustomView = findViewById(R.id.edt_text_custom_view);
        tm = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);

        setLocalCountryCode();

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomEditView);
        try {
            setData(typedArray);
        } catch (Exception e) {
            if (e instanceof Resources.NotFoundException)
                System.out.println(TAG
                        + ": did you forget to set some attributes?");
        } finally {
            typedArray.recycle();
        }


    }


    private void setData(TypedArray typedArray) throws Resources.NotFoundException {
        String hint = typedArray.getString(R.styleable.CustomEditView_hint);
        String text = typedArray.getString(R.styleable.CustomEditView_text);


        Drawable drawable = typedArray.getDrawable(R.styleable.CustomEditView_android_src);
        boolean isInputEnabled = typedArray.getBoolean(R.styleable.CustomEditView_isInputEnabled, true);
        isLeftIconChangeable = typedArray.getBoolean(R.styleable.CustomEditView_isLeftIconChangeable, false);

        int inputType = typedArray.getInt(R.styleable.CustomEditView_inputType, InputType.TYPE_CLASS_TEXT);
        int countryCodeVisibility = typedArray.getInt(R.styleable.CustomEditView_countryCodeVisibility, GONE);
        int imageColor = typedArray.getColor(R.styleable.CustomEditView_iconColor, blue);

        setHint(hint);
        setText(text);
        setInputType(inputType);
        setVisibilityCountryCode(countryCodeVisibility);
        setResourceImage(drawable);

        setImageColor(imageColor, isLeftIconChangeable);
    }


    private void setImageColor(int imageColor, boolean isLeftIconChangeable) {
        if (!isLeftIconChangeable) {
            startImageView.setColorFilter(imageColor, PorterDuff.Mode.SRC_IN);
        }
    }

    private void setResourceImage(Drawable drawable) {
        startImageView.setImageDrawable(drawable);
        if (drawable != null) {
            startImageView.setVisibility(VISIBLE);
        }
    }

    private void setInputType(int inputType) {
        edtTextCustomView.setInputType(inputType);
    }

    private void setVisibilityCountryCode(int countryCodeVisibility) {
        layoutCountryCode.setVisibility(countryCodeVisibility);
        startImageView.setVisibility(countryCodeVisibility);
        edtTextCustomView.setPaddingRelative(dpToPx(16), dpToPx(10), 0, dpToPx(10));

    }

    public CustomEditView setHint(CharSequence hint) {
        ((TextView) findViewById(R.id.edt_text_custom_view)).setHint(hint);
        return this;
    }

    public void setText(CharSequence text) {
            edtTextCustomView.setText(text);
    }

    private void setImageAsset(ImageView imageView, String fileName) {
        // load image
        try {
            // get input stream

            InputStream ims = context.getAssets().open(fileName.toLowerCase());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView

            imageView.setImageDrawable(d);
        } catch (IOException e) {
            Log.e("", "setImageAsset: e = " + e.getMessage());
            System.err.println("setImageAsset: IOException: " + e.getMessage());
        }
    }

    private void setLocalCountryCode() {
        new Handler().postDelayed(() -> {
            countryCodeValue = tm.getNetworkCountryIso().toUpperCase();
            if (countryCodeValue.equals("")) {
                Locale locale = Locale.getDefault();
                countryCodeValue = locale.getDisplayCountry(locale);
            }

            String countryCode = Constants.countryCodes.get(countryCodeValue.toUpperCase());
            textCode.setText(countryCode);

            if (isLeftIconChangeable) {
                setImageAsset(startImageView, countryCodeValue.toLowerCase() + ".png");
            }
        }, 1);

    }

    public boolean isLeftIconChangeable() {
        return isLeftIconChangeable;
    }

    public void setLeftIconChangeable(boolean leftIconChangeable) {
        isLeftIconChangeable = leftIconChangeable;
    }

    public String getCountryCodeValue() {
        return countryCodeValue;
    }

    public String getText() {
            return textCode.getText().toString().replace("+", "")
                    + edtTextCustomView.getText().toString();
    }

}
