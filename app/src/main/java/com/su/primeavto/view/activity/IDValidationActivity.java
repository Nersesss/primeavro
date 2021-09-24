package com.su.primeavto.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.view.fragment.IDValidationFragment;

import static com.su.primeavto.Constants.ARG_CAR;
import static com.su.primeavto.Constants.INTENT_KEY_AVTO;

public class IDValidationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_validation);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        if (savedInstanceState == null) {
            replaceFragment(IDValidationFragment.newInstance((AutoModel) getIntent().getSerializableExtra(INTENT_KEY_AVTO)));
        }
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