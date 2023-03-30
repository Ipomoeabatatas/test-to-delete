package com.softbankrobotics.retaildemo.Executors;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.object.conversation.BaseQiChatExecutor;
import com.softbankrobotics.retaildemo.Fragments.CollectFragment;
import com.softbankrobotics.retaildemo.Fragments.ProductFragment;
import com.softbankrobotics.retaildemo.Fragments.ReturnMainFragment;
import com.softbankrobotics.retaildemo.Fragments.ShowSOTFragment;
import com.softbankrobotics.retaildemo.MainActivity;

import java.util.List;

public class StatusExecutor extends BaseQiChatExecutor {
    private final QiContext qiContext;
    private final MainActivity ma;
    private String TAG = "MSI_StatusExecutor";

    public StatusExecutor(QiContext qiContext, MainActivity mainActivity) {
        super(qiContext);
        this.qiContext = qiContext;
        this.ma = mainActivity;
    }

    @Override
    public void runWith(List<String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        String field = params.get(0);
        String value = "";
        if(params.size() == 2){
            value = params.get(1);
        }
        Log.d(TAG,"field : " + field + " value : "+ value);
        ShowSOTFragment selectionFragment;
        ProductFragment productFragment;
        ReturnMainFragment returnMainFragment;
        CollectFragment collectFragment;
        switch (field){
            case ("gender"):
                ma.status.setGender(value);
                selectionFragment = (ShowSOTFragment) ma.getFragment();

                break;
            case ("type"):
                selectionFragment = (ShowSOTFragment) ma.getFragment();

                break;
            case ("color"):
                productFragment = (ProductFragment) ma.getFragment();
                productFragment.setProductColor("\""+value+"\"");
                break;
            case ("check"):
                productFragment = (ProductFragment) ma.getFragment();
                productFragment.setProductSize(value);
                break;
            case ("clickreturn"):
                returnMainFragment = (ReturnMainFragment) ma.getFragment();
//                returnMainFragment.pressListViewButton(Integer.parseInt(value));
                break;
            case ("clickdemoreturn"):
                returnMainFragment = (ReturnMainFragment) ma.getFragment();
//                returnMainFragment.pressDemoTicketButton();
                break;
            case ("clickdemocollect"):
                collectFragment = (CollectFragment) ma.getFragment();

                break;
            case ("size_color"):
                productFragment = (ProductFragment) ma.getFragment();
                String size = value.split(" ")[0];
                String color = value.split(" ")[1];
                productFragment.setProductColor("\""+color+"\"");
                productFragment.setProductSize(size);
                Log.d(TAG,"size color executor done");
                break;
            case ("gender_type"):
                selectionFragment = (ShowSOTFragment) ma.getFragment();
                String gender = value.split(" ")[0];
                String type = value.split(" ")[1];
                ma.status.setGender(gender);

                break;
            default:
                break;
        }
    }

    @Override
    public void stop() {

    }
}