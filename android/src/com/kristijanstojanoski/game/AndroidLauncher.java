package com.kristijanstojanoski.game;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

import States.AdsController;
import States.MainClass;

public class AndroidLauncher extends AndroidApplication implements AdsController, PurchasesUpdatedListener {
    private String ITEM_SKU_AD_REMOVAL = "remove_ads";
    private String ITEM_SKU_FIFTY_DIAMONDS = "fifty_diamonds";
    private String ITEM_SKU_HUNDRED_DIAMONDS = "hundred_diamonds";
    private String ITEM_SKU_FIVE_HUNDRED_DIAMONDS = "five_hundred_diamonds";
    private String ITEM_SKU_THOUSAND_DIAMONDS = "thousand_diamonds";
    private String ITEM_SKU_T_REX = "t_rex";
    BillingClient billingClient;

    RewardedAd ad;
    InterstitialAd iAd;
    boolean adLoaded = false;
    boolean rewardReceived = false;
    boolean iAdClosed = false;
    boolean iAdLoaded = false;
    boolean adsRemoved = false;
    boolean diamondsReceived = false;
    boolean fiftyDiamonds = false;
    boolean hundredDiamonds = false;
    boolean fiveHundredDiamonds = false;
    boolean thousandDiamonds = false;
    boolean tRexBought = false;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        AndroidApplicationConfiguration androidApplicationConfiguration = new AndroidApplicationConfiguration();
        initialize(new MainClass(this), androidApplicationConfiguration);
        MobileAds.initialize(this, "ca-app-pub-4701446273186664~4690368835");
        loadRewardedVideoAd();
        loadInterstitialAd();

        billingClient = BillingClient.newBuilder(AndroidLauncher.this)
                .enablePendingPurchases()
                .setListener(this)
                .build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
//                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK)
//                    Toast.makeText(AndroidLauncher.this,"Successfully connected to client",Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(AndroidLauncher.this,"Failed to connect to the client",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onBillingServiceDisconnected() {
//                Toast.makeText(AndroidLauncher.this,"Disconnected from the client",Toast.LENGTH_SHORT).show();
            }
        });

        List<String> skuList = new ArrayList<>();
        skuList.add(ITEM_SKU_AD_REMOVAL);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
//                if(list != null && billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
////                    for(SkuDetails skuDetails : list){
////                        String sku = skuDetails.getSku();
////                        String price = skuDetails.getPrice();
////                    }
//                }
            }
        });

    }

    @Override
    public void removeAdsBuy() {
        purchaseItem(ITEM_SKU_AD_REMOVAL);
    }

    @Override
    public boolean getRemoveAdsPurchased() {
        return adsRemoved;
    }

    @Override
    public boolean getDiamondsReceived() {
        return diamondsReceived;
    }

    @Override
    public void setDiamondsReceived(boolean diamondsReceived) {
        this.diamondsReceived = diamondsReceived;
    }

    @Override
    public void fiftyDiamondsBuy() {
        purchaseItem(ITEM_SKU_FIFTY_DIAMONDS);
    }

    @Override
    public boolean getFiftyDiamonds() {
        return fiftyDiamonds;
    }

    @Override
    public void setFiftyDiamonds(boolean fiftyDiamonds) {
       this.fiftyDiamonds = fiftyDiamonds;
    }

    @Override
    public void hundredDiamondsBuy() {
        purchaseItem(ITEM_SKU_HUNDRED_DIAMONDS);
    }

    @Override
    public boolean getHundredDiamonds() {
        return hundredDiamonds;
    }

    @Override
    public void setHundredDiamonds(boolean hundredDiamonds) {
        this.hundredDiamonds = hundredDiamonds;
    }

    @Override
    public void fiveHundredDiamondsBuy() {
        purchaseItem(ITEM_SKU_FIVE_HUNDRED_DIAMONDS);
    }

    @Override
    public boolean getFiveHundredDiamonds() {
        return fiveHundredDiamonds;
    }

    @Override
    public void setFiveHundredDiamonds(boolean fiveHundredDiamonds) {
        this.fiveHundredDiamonds = fiveHundredDiamonds;
    }

    @Override
    public void thousandDiamondsBuy() {
        purchaseItem(ITEM_SKU_THOUSAND_DIAMONDS);
    }

    @Override
    public boolean getThousandDiamonds() {
        return thousandDiamonds;
    }

    @Override
    public void setThousandDiamonds(boolean thousandDiamonds) {
        this.thousandDiamonds = thousandDiamonds;
    }

    @Override
    public void tRexBuy() {
        purchaseItem(ITEM_SKU_T_REX);
    }

    @Override
    public boolean getTRexBought() {
        return tRexBought;
    }

    @Override
    public void setTRexBought(boolean tRexBought) {
        this.tRexBought = tRexBought;
    }

    public void loadRewardedVideoAd() {
        ad = new RewardedAd(this, "ca-app-pub-4701446273186664/9376746971");
        RewardedAdLoadCallback rewardedAdLoadCallback = new RewardedAdLoadCallback() {
            public void onRewardedAdFailedToLoad(int i) {
                super.onRewardedAdFailedToLoad(i);
                // Toast.makeText(AndroidLauncher.this, "Ad Failed to Load " + i, Toast.LENGTH_SHORT).show();
                setAdLoaded(false);
                loadRewardedVideoAd();
            }

            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
                //Toast.makeText(AndroidLauncher.this, "Ad Loaded ", Toast.LENGTH_SHORT).show();
                setAdLoaded(true);
            }
        };

        ad.loadAd((new AdRequest.Builder()).build(), rewardedAdLoadCallback);
    }

    public void showRewardedVideo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (ad.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            setRewardReceived(true);
                            setAdLoaded(false);
                            loadRewardedVideoAd();
                            //Toast.makeText(AndroidLauncher.this, "Reward" + rewardItem, Toast.LENGTH_SHORT).show();
                        }
                    };
                    ad.show(AndroidLauncher.this, adCallback);
                } else
                    loadRewardedVideoAd();
            }
        });
    }


    @Override
    public void loadInterstitialAd() {
        iAd = new InterstitialAd(this);
        iAd.setAdUnitId("ca-app-pub-4701446273186664/3588577131");
        iAd.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                loadInterstitialAd();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                iAdClosed = true;
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                iAdLoaded = true;
            }
        });
        iAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void showInterstitialAd() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (iAd.isLoaded()) {
                    iAd.show();
                    iAdLoaded = false;
                    loadInterstitialAd();
                } else
                    loadInterstitialAd();
            }
        });
    }


    @Override
    public boolean getInterstitialAdLoaded() {
        return iAdLoaded;
    }

    public boolean getAdLoaded() {
        return this.adLoaded;
    }

    public boolean getRewardReceived() {
        return this.rewardReceived;
    }

    public void setAdLoaded(boolean paramBoolean) {
        this.adLoaded = paramBoolean;
    }

    public void setRewardReceived(boolean paramBoolean) {
        this.rewardReceived = paramBoolean;
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
            for (Purchase purchase : list)
                handlePurchase(purchase);
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED)
            Toast.makeText(AndroidLauncher.this,"You did not not purchase the item",Toast.LENGTH_SHORT).show();
        else if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED && list != null){
            for (Purchase purchase : list){
                if(purchase.getSku().equals(ITEM_SKU_AD_REMOVAL)){
                    adsRemoved = true;
                    diamondsReceived = true;
                }
            }

        }

    }

    private void handlePurchase(Purchase purchase){
        if(purchase.getSku().equals(ITEM_SKU_AD_REMOVAL)) {
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            adsRemoved = true;
            diamondsReceived = true;
        } else if(purchase.getSku().equals(ITEM_SKU_FIFTY_DIAMONDS)){
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            fiftyDiamonds = true;
        } else if(purchase.getSku().equals(ITEM_SKU_HUNDRED_DIAMONDS)) {
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            hundredDiamonds = true;
        } else if(purchase.getSku().equals(ITEM_SKU_FIVE_HUNDRED_DIAMONDS)){
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            fiveHundredDiamonds = true;
        } else if (purchase.getSku().equals(ITEM_SKU_THOUSAND_DIAMONDS)){
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            thousandDiamonds = true;
        } else if (purchase.getSku().equals(ITEM_SKU_T_REX)){
            Toast.makeText(AndroidLauncher.this, "Item Purchased", Toast.LENGTH_SHORT).show();
            tRexBought = true;
        }

    }

    private void purchaseItem(String item){
        List<String> skuList = new ArrayList<>();
        skuList.add(item);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                if(list != null && billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                            .setSkuDetails(list.get(0))
                            .build();
                     billingClient.launchBillingFlow(AndroidLauncher.this,flowParams);
                }
            }
        });
    }
}
