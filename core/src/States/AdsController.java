package States;

public interface AdsController {
    boolean getAdLoaded();

    boolean getRewardReceived();

    void loadRewardedVideoAd();

    void setAdLoaded(boolean paramBoolean);

    void setRewardReceived(boolean paramBoolean);

    void showRewardedVideo();

    void loadInterstitialAd();

    void showInterstitialAd();

    boolean getInterstitialAdClosed();

    boolean getInterstitialAdLoaded();

    void setInterstitialAdClosed(boolean bool);
}
