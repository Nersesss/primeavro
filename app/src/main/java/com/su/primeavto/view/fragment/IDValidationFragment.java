package com.su.primeavto.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.dx.dxloadingbutton.lib.LoadingButton;
import com.su.primeavto.Constants;
import com.su.primeavto.R;
import com.su.primeavto.model.AutoModel;
import com.su.primeavto.util.PermissionHelper;
import com.su.primeavto.util.PicassoHelper;
import com.su.primeavto.util.PreferenceUtils;
import com.su.primeavto.viewmodel.CarViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

import static com.su.primeavto.Constants.ARG_CAR;
import static com.su.primeavto.util.Connectivity.isOnline;
import static com.su.primeavto.util.FileUtil.getAppDir;
import static com.su.primeavto.util.FileUtil.saveToGallery;
import static com.su.primeavto.view.fragment.IDValidationFragment.PhotoType.DL_FRONT;
import static com.su.primeavto.view.fragment.IDValidationFragment.PhotoType.DL_SELFIE;
import static com.su.primeavto.view.fragment.IDValidationFragment.PhotoType.ID_BACK;
import static com.su.primeavto.view.fragment.IDValidationFragment.PhotoType.ID_FRONT;

public class IDValidationFragment extends BaseFragment implements EasyImage.Callbacks, PermissionHelper.Callback {

    public static final String TAG = IDValidationFragment.class.getSimpleName();
    private static final int CAMERA_REQUEST_CODE = 34964;
    private static AutoModel autoModel;

    private ViewGroup idFront, idBack, dlFront, dlSelfie;
    private ImageView imgIDFront, imgIDBack, imgDLFront, imgDISelfie;
    private LoadingButton btnSend;
    private View.OnClickListener onClickListener;
    private EasyImage easyImage;
    private String idFrontPath;
    private String idBackPath;
    private String dlFrontPath;
    private String dlSelfiePath;
    private CarViewModel carViewModel;

    public static IDValidationFragment newInstance(AutoModel autoModel) {
        IDValidationFragment fragment = new IDValidationFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CAR, autoModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onPermissionOk(int permissionType) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            autoModel = (AutoModel) getArguments().getSerializable(ARG_CAR);
        }
        carViewModel = provider.get(CarViewModel.class);
    }

    enum PhotoType {
        ID_FRONT("front"),
        ID_BACK("back"),
        DL_FRONT("dl"),
        DL_SELFIE("selfie");

        PhotoType(String value) {
            this.value = value;
        }

        private final String value;

    }

    PhotoType currentPhotoType;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(ID_FRONT.value, idFrontPath);
        outState.putString(ID_BACK.value, idBackPath);
        outState.putString(DL_FRONT.value, dlFrontPath);
        outState.putString(DL_SELFIE.value, dlSelfiePath);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        if (savedInstanceState == null) {
            easyImage = new EasyImage
                    .Builder(getActivity())
                    .setCopyImagesToPublicGalleryFolder(false)
                    .allowMultiple(true)
                    .build();
        } else {
            idFrontPath = savedInstanceState.getString(ID_FRONT.value);
            idBackPath = savedInstanceState.getString(ID_BACK.value);
            dlFrontPath = savedInstanceState.getString(DL_FRONT.value);
            dlSelfiePath = savedInstanceState.getString(DL_SELFIE.value);
            if (idFrontPath != null) {
                ImageViewCompat.setImageTintList(imgIDFront, null);
                PicassoHelper.setImagePhoto(imgIDFront, new File(idFrontPath));
            }
            if (idBackPath != null) {
                ImageViewCompat.setImageTintList(imgIDBack, null);
                PicassoHelper.setImagePhoto(imgIDBack, new File(idBackPath));
            }
            if (dlFrontPath != null) {
                ImageViewCompat.setImageTintList(imgIDFront, null);
                PicassoHelper.setImagePhoto(imgIDFront, new File(dlFrontPath));
            }
            if (dlSelfiePath != null) {
                ImageViewCompat.setImageTintList(imgDISelfie, null);
                PicassoHelper.setImagePhoto(imgDISelfie, new File(dlSelfiePath));
            }
        }
    }

    private void initViews(View view) {

        onClickListener = v -> {
            openSystemCamera();
            switch (v.getId()) {
                case R.id.layout_id_front:
                    currentPhotoType = ID_FRONT;
                    break;

                case R.id.layout_id_back:
                    currentPhotoType = PhotoType.ID_BACK;
                    break;

                case R.id.layout_dl_front:
                    currentPhotoType = PhotoType.DL_FRONT;
                    break;

                case R.id.layout_dl_selfie:
                    currentPhotoType = PhotoType.DL_SELFIE;
                    break;
            }
        };

        imgIDFront = view.findViewById(R.id.img_front);
        imgIDBack = view.findViewById(R.id.img_back);
        imgDLFront = view.findViewById(R.id.img_dl);
        imgDISelfie = view.findViewById(R.id.img_dl_selfie);

        idFront = view.findViewById(R.id.layout_id_front);
        idBack = view.findViewById(R.id.layout_id_back);
        dlFront = view.findViewById(R.id.layout_dl_front);
        dlSelfie = view.findViewById(R.id.layout_dl_selfie);
        btnSend = view.findViewById(R.id.btn_send);
        btnSend.setOnClickListener(v -> {
            sendDocs();
        });

        idFront.setOnClickListener(onClickListener);
        idBack.setOnClickListener(onClickListener);
        dlFront.setOnClickListener(onClickListener);
        dlSelfie.setOnClickListener(onClickListener);

        PermissionHelper.StorageWrite.handlePermission(getActivity(), this);
    }

    private void sendDocs() {
        if (isOnline()) {
            String phoneNumber = (String) PreferenceUtils.readStringPreference(getContext(),
                    Constants.PREF_KEY_PHONE, "");
            if (idFrontPath != null && idBackPath != null && dlSelfiePath != null && dlFrontPath != null) {
                List<File> files = new ArrayList<>();
                files.add(new File(idFrontPath));
                files.add(new File(idBackPath));
                files.add(new File(dlFrontPath));
                files.add(new File(dlSelfiePath));
                btnSend.startLoading();
                carViewModel.sendDocs("lkaliU&#$aloH", phoneNumber, files)
                        .observe(getViewLifecycleOwner(), success -> {
                            if (success) {
                                btnSend.loadingSuccessful();
                                Toast.makeText(getContext(), getString(R.string.autorization_message), Toast.LENGTH_SHORT).show();
                                replaceFragment(ConfigureToPayFragment.newInstance(autoModel));
                            } else {
                                btnSend.loadingFailed();
                                Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getContext(), getString(R.string.feal_all_fealds), Toast.LENGTH_SHORT).show();
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
            sendDocs();
            dialog.dismiss();
        });
        alertDialogBuilder.create().show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        easyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), this);
    }

    public IDValidationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_id_validation, container, false);
    }

    @Override
    public void onCanceled(@NotNull MediaSource mediaSource) {

    }

    @Override
    public void onImagePickerError(@NotNull Throwable throwable, @NotNull MediaSource mediaSource) {

    }

    @Override
    public void onMediaFilesPicked(@NotNull MediaFile[] mediaFiles, @NotNull MediaSource mediaSource) {
        File file;
        switch (currentPhotoType) {
            case ID_FRONT:
                saveToGallery(getContext(), mediaFiles[0].getFile(), "front.jpg");
                file = new File(getAppDir(), "front.jpg");
                idFrontPath = file.getPath();

                ImageViewCompat.setImageTintList(imgIDFront, null);
                PicassoHelper.setImagePhoto(imgIDFront, file);
                break;
            case ID_BACK:
                saveToGallery(getContext(), mediaFiles[0].getFile(), "back.jpg");
                file = new File(getAppDir(), "back.jpg");
                idBackPath = file.getPath();
                ImageViewCompat.setImageTintList(imgIDBack, null);
                PicassoHelper.setImagePhoto(imgIDBack, file);
                break;
            case DL_FRONT:
                saveToGallery(getContext(), mediaFiles[0].getFile(), "dl.jpg");
                file = new File(getAppDir(), "dl.jpg");
                dlFrontPath = file.getPath();
                ImageViewCompat.setImageTintList(imgDLFront, null);
                PicassoHelper.setImagePhoto(imgDLFront, file);
                break;
            case DL_SELFIE:
                saveToGallery(getContext(), mediaFiles[0].getFile(), "selfie.jpg");
                file = new File(getAppDir(), "selfie.jpg");
                dlSelfiePath = file.getPath();
                ImageViewCompat.setImageTintList(imgDISelfie, null);
                PicassoHelper.setImagePhoto(imgDISelfie, file);
                break;
        }
    }

    private void openSystemCamera() {
        String[] necessaryPermissions = new String[]{Manifest.permission.CAMERA};
        if (arePermissionsGranted(necessaryPermissions)) {
            easyImage.openCameraForImage(this);
        } else {
            requestPermissionsCompat(necessaryPermissions, CAMERA_REQUEST_CODE);
        }
    }

    public void requestPermissionsCompat(String[] permissions, int requestCode) {
        if (getActivity() != null)
            ActivityCompat.requestPermissions(getActivity(), permissions, requestCode);
    }

    public boolean arePermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            easyImage.openCameraForImage(this);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

}