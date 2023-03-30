package com.softbankrobotics.retaildemo.Executors;

import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.BaseQiChatExecutor;
import com.aldebaran.qi.sdk.object.conversation.Say;
import com.softbankrobotics.retaildemo.Fragments.CollectFragment;
import com.softbankrobotics.retaildemo.Fragments.EmailFragment;
import com.softbankrobotics.retaildemo.Fragments.MainMenuFragment;
import com.softbankrobotics.retaildemo.Fragments.ProductMapFragment;
import com.softbankrobotics.retaildemo.Fragments.ProductFragment;
import com.softbankrobotics.retaildemo.Fragments.ProductUpsellFragment;
import com.softbankrobotics.retaildemo.Fragments.ReturnMainFragment;
import com.softbankrobotics.retaildemo.Fragments.ReturnProductFragment;
import com.softbankrobotics.retaildemo.Fragments.ReturnReasonFragment;
import com.softbankrobotics.retaildemo.Fragments.ShowSOTFragment;
import com.softbankrobotics.retaildemo.Fragments.SplashScreenFragment;
import com.softbankrobotics.retaildemo.Fragments.AppDisplayFragment;
import com.softbankrobotics.retaildemo.MainActivity;


import java.util.List;

public class FragmentExecutor extends BaseQiChatExecutor {
    private final QiContext qiContext;
    private final MainActivity mainActivity;
    private String TAG = "MSI_FragmentExecutor";

    public FragmentExecutor(QiContext qiContext, MainActivity mainActivity) {
        super(qiContext);
        this.qiContext = qiContext;
        this.mainActivity = mainActivity;
    }

    @Override
    public void runWith(List<String> params) {
        if (params == null || params.isEmpty()) {
            return;
        }
        Say say = SayBuilder.with(qiContext) // Create the builder with the context.
                .withText("Hello human!") // Set the text to say.
                .build(); // Build the say action.
        say.run();
        String fragmentName = params.get(0);
        String data = "";
        if(params.size() == 2){
            data = params.get(1);
        }
        Log.d(TAG,"fragmentName :" +fragmentName);
        switch (fragmentName){
            case ("email"):
                mainActivity.setFragment(new EmailFragment());
                break;
            case ("selection"):
                Log.d(TAG,"executor selection fragment");
                if(data.equals("promotion")){
                    mainActivity.status.promotion = true;
                    mainActivity.status.gender = "MALE";
                    mainActivity.status.estimatedAge = 20;
                }
                mainActivity.setFragment(new ShowSOTFragment());
                break;
            case ("mainmenu"):
                mainActivity.setFragment(new MainMenuFragment());
                break;
            case ("relatedApps"):
                mainActivity.setFragment(new AppDisplayFragment());
                break;
            case ("map"):
                mainActivity.setFragment(new ProductMapFragment());
                break;
            case ("product"):
                mainActivity.setFragment(new ProductFragment());
                break;
//            case ("locker"):
//                CollectConfirmFragment ccf = (CollectConfirmFragment)mainActivity.getFragment();
//                if(ccf.getSigned()){
//                    mainActivity.setFragment(new CollectLockerFragment());
//                }
//                break;
            case ("upsell"):
                ProductFragment productFragment = (ProductFragment) mainActivity.getFragment();
                productFragment.onClickBuy();
                break;

            case ("returnprod"):
                mainActivity.setFragment(new ReturnProductFragment());
                break;
            case ("splashscreen"):
                mainActivity.setFragment(new SplashScreenFragment());
                break;
            case ("returnmain"):
                mainActivity.setFragment(new ReturnMainFragment());
                break;
            case ("returnreason"):
                mainActivity.setFragment(new ReturnReasonFragment());
                break;
            case ("collect"):
                mainActivity.setFragment(new CollectFragment());
                break;
            case ("product_context"):
                mainActivity.setFragment(new ProductFragment());
                break;
            case ("buy"):
                if(mainActivity.status.getSendEmail()){
                    mainActivity.setFragment(new EmailFragment());
                }else{
                    ProductUpsellFragment productUpsellFragment = (ProductUpsellFragment) mainActivity.getFragment();
                    if(data.equals("add")){
                        productUpsellFragment.setProductUpsell(true);
                    }else{
                        productUpsellFragment.setProductUpsell(false);

                    }
                    mainActivity.setFragment(new ProductMapFragment());
                }
                break;
            default:
                mainActivity.setFragment(new MainMenuFragment());
        }
    }

    @Override
    public void stop() {

    }
}