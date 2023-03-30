package com.softbankrobotics.retaildemo.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.softbankrobotics.retaildemo.MainActivity;
import com.softbankrobotics.retaildemo.R;

public class ShowSOTFragment extends Fragment {

    private static final String TAG = "MSI_SelectionFragment";

    private MainActivity ma;
    private ImageView productMain;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        this.ma = (MainActivity) getActivity();
        Log.d(TAG,"estimatedAge : "+ ma.status.estimatedAge);
        Log.d(TAG,"gender : " + ma.status.gender);
        if(ma.status.promotion){
            ma.status.promotion = false;
            ma.getCurrentChatData().goToBookmarkNewTopic("init","productselection");
        }else{
            if(ma.status.estimatedAge == -1 || ma.status.gender.equals("unknown")){
                ma.getCurrentChatData().goToBookmarkNewTopic("init","productselection");
            }else{
                String type = "Sport";
                if(ma.status.estimatedAge > 50){
                    type = "Dress";
                }
                ma.setDynamicProductName("product_type",type);
                ma.getCurrentChatData().goToBookmarkNewTopic("init","productselection");
            }
        }
        int fragmentId = R.layout.fragment_show_sot;
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
        view.findViewById(R.id.button_home_selection).setOnClickListener(v -> ma.setFragment(new MainMenuFragment()));
        productMain = view.findViewById(R.id.image_main);
    }
}
