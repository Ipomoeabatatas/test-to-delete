package com.softbankrobotics.retaildemo.Executors;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.object.conversation.BaseQiChatExecutor;
import com.softbankrobotics.retaildemo.MainActivity;

import java.util.List;

public class LanguageExecutor extends BaseQiChatExecutor {
    private final QiContext qiContext;
    private final MainActivity mainActivity;
    private String TAG = "MSI_LanguageExecutor";

    public LanguageExecutor(QiContext qiContext, MainActivity mainActivity) {
        super(qiContext);
        this.qiContext = qiContext;
        this.mainActivity = mainActivity;
    }

    @Override
    public void runWith(List<String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        String language = params.get(0);

        Log.d(TAG,"language :" +language);
        switch (language){
            case ("switch_to_english"):
                mainActivity.setLocale("en");
                break;
            case ("switch_to_french"):
                mainActivity.setLocale("fr");
                break;
        }
    }

    @Override
    public void stop() {

    }
}