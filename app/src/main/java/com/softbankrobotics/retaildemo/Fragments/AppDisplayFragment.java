package com.softbankrobotics.retaildemo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.content.res.Resources;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.object.human.Human;
import com.aldebaran.qi.sdk.object.humanawareness.HumanAwareness;
import com.softbankrobotics.retaildemo.MainActivity;
import com.softbankrobotics.retaildemo.R;

import java.util.List;

//Reference made from ReturnMainFragment
//OnclickListeners for buttons
public class AppDisplayFragment extends Fragment {

    private static final String TAG = "MSI_AppDisplayFragment";
    private MainActivity ma;
    private ImageView QRMain;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        this.ma = (MainActivity) getActivity();
        Log.d(TAG,"estimatedAge : "+ ma.status.estimatedAge);
        Log.d(TAG,"gender : " + ma.status.gender);

        int fragmentId = R.layout.fragment_applogo_display;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.button_home_selection).setOnClickListener(v -> ma.setFragment(new MainMenuFragment()));
        QRMain = view.findViewById(R.id.image_main);
    }

    public void onDestroy() {
        super.onDestroy();
    }
    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }
}