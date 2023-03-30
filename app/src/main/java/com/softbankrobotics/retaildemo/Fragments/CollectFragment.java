package com.softbankrobotics.retaildemo.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.softbankrobotics.retaildemo.Barcode.BarcodeTrackerFactory;
import com.softbankrobotics.retaildemo.Camera.CameraSource;
import com.softbankrobotics.retaildemo.Camera.CameraSourcePreview;
import com.softbankrobotics.retaildemo.MainActivity;
import com.softbankrobotics.retaildemo.R;

import java.io.IOException;

public class CollectFragment extends Fragment {

    private static final String TAG = "MSI_CollectFragment";

    private MainActivity ma;
    private ImageView productMain;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        int fragmentId = R.layout.fragment_collect;
        this.ma = (MainActivity) getActivity();
        if(ma != null){
            Integer themeId = ma.getThemeId();
            if(themeId != null){
                final Context contextThemeWrapper = new ContextThemeWrapper(ma, themeId);
                LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
                return localInflater.inflate(fragmentId, container, false);
            }else{
                return inflater.inflate(fragmentId, container, false);
            }
        }else{
            Log.e(TAG, "could not get mainActivity, can't create fragment");
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        view.findViewById(R.id.button_home_confirm).setOnClickListener(v ->
                ma.setFragment(new MainMenuFragment()));
        productMain = view.findViewById(R.id.image_main);
    }

}
