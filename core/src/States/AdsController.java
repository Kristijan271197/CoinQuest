package States;

public interface AdsController {
  boolean getAdLoaded();
  
  boolean getRewardReceived();
  
  void loadRewardedVideoAd();
  
  void setAdLoaded(boolean paramBoolean);
  
  void setRewardReceived(boolean paramBoolean);
  
  void showRewardedVideo();
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\AdsController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */