package com.kristijanstojanoski.game;

import States.MusicSounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Coin {
  private TextureAtlas.AtlasRegion coin;
  
  private Sound coinCollect;
  
  private int coinCount = 0;
  
  private float coinHeight = worldYToScreenY();
  
  private ArrayList<Rectangle> coinRectangles = new ArrayList<Rectangle>();
  
  private float coinSpeed = worldXToScreenX(5.0F);
  
  private int coinTime;
  
  private float coinWidth = worldXToScreenX(40.0F);
  
  private ArrayList<Float> coinXs = new ArrayList<Float>();
  
  private ArrayList<Float> coinYs = new ArrayList<Float>();
  
  private float lastCoinTimer = 0.0F;
  
  private MusicSounds musicSoundsObject;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private float worldYToScreenY() {
    return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
  }
  
  void coinCollision(Rectangle paramRectangle, boolean paramBoolean) {
    for (byte b = 0; b < this.coinRectangles.size(); b++) {
      if (Intersector.overlaps(paramRectangle, this.coinRectangles.get(b))) {
        this.musicSoundsObject.playCoinCollect();
        this.coinRectangles.remove(b);
        this.coinXs.remove(b);
        this.coinYs.remove(b);
        if (paramBoolean) {
          this.coinCount += 2;
          break;
        } 
        this.coinCount++;
        break;
      } 
    } 
  }
  
  public void dispose() {}
  
  void drawCoin(SpriteBatch paramSpriteBatch, boolean paramBoolean) {
    this.coinRectangles.clear();
    for (byte b = 0; b < this.coinXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.coin, ((Float)this.coinXs.get(b)).floatValue(), ((Float)this.coinYs.get(b)).floatValue(), this.coinWidth, this.coinHeight);
      if (!paramBoolean) {
        ArrayList<Float> arrayList = this.coinXs;
        arrayList.set(b, Float.valueOf(((Float)arrayList.get(b)).floatValue() - this.coinSpeed * Gdx.graphics.getDeltaTime() * 60.0F));
      } 
      this.coinRectangles.add(new Rectangle(((Float)this.coinXs.get(b)).floatValue(), ((Float)this.coinYs.get(b)).floatValue(), this.coinWidth, this.coinHeight));
    } 
  }
  
  void drawCoinMagnetized(SpriteBatch paramSpriteBatch, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean) {
    this.coinRectangles.clear();
    paramFloat3 = paramFloat3 / 2.0F - paramFloat4 / 2.0F;
    paramFloat4 = this.coinWidth / 2.0F - this.coinHeight / 2.0F;
    for (byte b = 0; b < this.coinXs.size(); b++) {
      float f1 = paramFloat1 - paramFloat3 - ((Float)this.coinXs.get(b)).floatValue() + paramFloat4;
      float f2 = paramFloat2 - paramFloat3 - ((Float)this.coinYs.get(b)).floatValue() + paramFloat4;
      double d = f1;
      float f3 = f2 * f2;
      f1 = (float)(d / Math.sqrt((f1 * f1 + f3)));
      f2 = (float)(f2 / Math.sqrt((f1 * f1 + f3)));
      f3 = this.coinSpeed;
      if (!paramBoolean) {
        ArrayList<Float> arrayList = this.coinXs;
        arrayList.set(b, Float.valueOf(((Float)arrayList.get(b)).floatValue() + f1 * f3));
        arrayList = this.coinYs;
        arrayList.set(b, Float.valueOf(((Float)arrayList.get(b)).floatValue() + f2 * f3));
      } 
      paramSpriteBatch.draw((TextureRegion)this.coin, ((Float)this.coinXs.get(b)).floatValue(), ((Float)this.coinYs.get(b)).floatValue(), this.coinWidth, this.coinHeight);
      this.coinRectangles.add(new Rectangle(((Float)this.coinXs.get(b)).floatValue(), ((Float)this.coinYs.get(b)).floatValue(), this.coinWidth, this.coinHeight));
    } 
  }
  
  int getCoinCount() {
    return this.coinCount;
  }
  
  int getCoinTime() {
    return this.coinTime;
  }
  
  float getLastCoinTimer() {
    return this.lastCoinTimer;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas, AssetManager paramAssetManager) {
    this.coin = paramTextureAtlas.findRegion("coin");
    this.coinTime = this.random.nextInt(2001) + 1000;
    this.coinCollect = (Sound)paramAssetManager.get("music/coin_collect.mp3", Sound.class);
    this.musicSoundsObject = new MusicSounds(paramAssetManager);
  }
  
  void makeCoin() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (this.coinHeight + f >= Gdx.graphics.getHeight()) {
      this.coinYs.add(Float.valueOf(f - this.coinHeight));
    } else {
      this.coinYs.add(Float.valueOf(Math.max(f, worldYToScreenY())));
    } 
    this.coinXs.add(Float.valueOf(Gdx.graphics.getWidth()));
  }
  
  void removeCoin() {
    for (byte b = 0; b < this.coinXs.size(); b++) {
      if (((Float)this.coinXs.get(b)).floatValue() + this.coinWidth <= 0.0F) {
        this.coinXs.remove(b);
        this.coinYs.remove(b);
        this.coinRectangles.remove(b);
        break;
      } 
    } 
  }
  
  void resetCoinStats() {
    this.coinXs.clear();
    this.coinYs.clear();
    this.coinRectangles.clear();
    this.coinSpeed = worldXToScreenX(5.0F);
    this.lastCoinTimer = 0.0F;
  }
  
  void setCoinCount() {
    this.coinCount = 0;
  }
  
  void setCoinSpeed(float paramFloat) {
    this.coinSpeed = paramFloat;
  }
  
  void setCoinTime(int paramInt) {
    this.coinTime = paramInt;
  }
  
  void setLastCoinTimer(float paramFloat) {
    this.lastCoinTimer = paramFloat;
  }
  
  float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Coin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */