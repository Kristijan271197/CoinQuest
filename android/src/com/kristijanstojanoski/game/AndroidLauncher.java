package com.kristijanstojanoski.game;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

import States.AdsController;
import States.MainClass;

public class AndroidLauncher extends AndroidApplication implements AdsController {
    RewardedAd ad;
    InterstitialAd iAd;
    boolean adLoaded = false;
    boolean rewardReceived = false;
    boolean iAdClosed = false;
    boolean iAdLoaded = false;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        AndroidApplicationConfiguration androidApplicationConfiguration = new AndroidApplicationConfiguration();
        initialize(new MainClass(this), androidApplicationConfiguration);
        MobileAds.initialize(this, "ca-app-pub-4701446273186664~4690368835");
        loadRewardedVideoAd();
        loadInterstitialAd();
    }

    public void loadRewardedVideoAd() {
        ad = new RewardedAd(this, "ca-app-pub-4701446273186664/9376746971");
        RewardedAdLoadCallback rewardedAdLoadCallback = new RewardedAdLoadCallback() {
            public void onRewardedAdFailedToLoad(int i) {
                super.onRewardedAdFailedToLoad(i);
               // Toast.makeText(AndroidLauncher.this, "Ad Failed to Load " + i, Toast.LENGTH_SHORT).show();
                setAdLoaded(false);
                if (haveNetworkConnection())
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
    public boolean getInterstitialAdClosed() {
        return iAdClosed;
    }

    @Override
    public boolean getInterstitialAdLoaded() {
        return iAdLoaded;
    }

    @Override
    public void setInterstitialAdClosed(boolean bool) {
        this.iAdClosed = bool;
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




    private boolean haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
