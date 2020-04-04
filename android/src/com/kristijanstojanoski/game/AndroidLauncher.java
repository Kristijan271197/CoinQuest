package com.kristijanstojanoski.game;

import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import States.MainClass;

public class AndroidLauncher extends AndroidApplication {
//  RewardedAd ad;
  
  boolean adLoaded = false;
  
  boolean rewardReceived = false;
  
//  private boolean haveNetworkConnection() {
//    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService("connectivity");
//    boolean bool1 = false;
//    NetworkInfo[] arrayOfNetworkInfo = new NetworkInfo[0];
//    if (connectivityManager != null)
//      arrayOfNetworkInfo = connectivityManager.getAllNetworkInfo();
//    int i = arrayOfNetworkInfo.length;
//    byte b = 0;
//    boolean bool2 = false;
//    boolean bool3;
//    for (bool3 = false; b < i; bool3 = bool5) {
//      NetworkInfo networkInfo = arrayOfNetworkInfo[b];
//      boolean bool4 = bool2;
//      if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
//        bool4 = bool2;
//        if (networkInfo.isConnected())
//          bool4 = true;
//      }
//      boolean bool5 = bool3;
//      if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) {
//        bool5 = bool3;
//        if (networkInfo.isConnected())
//          bool5 = true;
//      }
//      b++;
//      bool2 = bool4;
//    }
//    if (bool2 || bool3)
//      bool1 = true;
//    return bool1;
//  }
//
//  public boolean getAdLoaded() {
//    return this.adLoaded;
//  }
//
//  public boolean getRewardReceived() {
//    return this.rewardReceived;
//  }
//
//  public void loadRewardedVideoAd() {
//    this.ad = new RewardedAd((Context)this, "ca-app-pub-7630597585185906/4388758184");
//    RewardedAdLoadCallback rewardedAdLoadCallback = new RewardedAdLoadCallback() {
//        public void onRewardedAdFailedToLoad(int param1Int) {
//          super.onRewardedAdFailedToLoad(param1Int);
//          AndroidLauncher androidLauncher = AndroidLauncher.this;
//          StringBuilder stringBuilder = new StringBuilder();
//          stringBuilder.append("Ad Failed to Load ");
//          stringBuilder.append(param1Int);
//          Toast.makeText((Context)androidLauncher, stringBuilder.toString(), 0).show();
//          AndroidLauncher.this.setAdLoaded(false);
//          if (AndroidLauncher.this.haveNetworkConnection())
//            AndroidLauncher.this.loadRewardedVideoAd();
//        }
//
//        public void onRewardedAdLoaded() {
//          super.onRewardedAdLoaded();
//          Toast.makeText((Context)AndroidLauncher.this, "Ad Loaded ", 0).show();
//          AndroidLauncher.this.setAdLoaded(true);
//        }
//      };
//    this.ad.loadAd((new AdRequest.Builder()).build(), rewardedAdLoadCallback);
//  }
  
  protected void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    AndroidApplicationConfiguration androidApplicationConfiguration = new AndroidApplicationConfiguration();
    initialize(new MainClass(null), androidApplicationConfiguration);
//    MobileAds.initialize((Context)this, " ca-app-pub-3940256099942544~3347511713");
//    loadRewardedVideoAd();
  }
  
//  public void setAdLoaded(boolean paramBoolean) {
//    this.adLoaded = paramBoolean;
//  }
//
//  public void setRewardReceived(boolean paramBoolean) {
//    this.rewardReceived = paramBoolean;
//  }
//
//  public void showRewardedVideo() {
//    runOnUiThread(new _$$Lambda$AndroidLauncher$kgDJShkE8_vC5WpiC7y6Muth4MI(this));
//  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\AndroidLauncher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */