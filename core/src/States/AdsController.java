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

    boolean getInterstitialAdLoaded();

    void removeAdsBuy();

    boolean getRemoveAdsPurchased();

    boolean getDiamondsReceived();

    void setDiamondsReceived(boolean diamondsReceived);

    void fiftyDiamondsBuy();

    boolean getFiftyDiamonds();

    void setFiftyDiamonds(boolean fiftyDiamonds);

    void hundredDiamondsBuy();

    boolean getHundredDiamonds();

    void setHundredDiamonds(boolean hundredDiamonds);

    void fiveHundredDiamondsBuy();

    boolean getFiveHundredDiamonds();

    void setFiveHundredDiamonds(boolean fiveHundredDiamonds);

    void thousandDiamondsBuy();

    boolean getThousandDiamonds();

    void setThousandDiamonds(boolean thousandDiamonds);

    void tRexBuy();

    boolean getTRexBought();

    void setTRexBought(boolean tRexBought);
}
