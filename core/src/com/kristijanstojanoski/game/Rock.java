package com.kristijanstojanoski.game;

import States.MusicSounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Rock {
  private boolean firstRockHit = false;
  
  private float firstRockHitTimer = 0.0F;
  
  private float lastRockTimer = 0.0F;
  
  private MusicSounds musicSoundsObject;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private TextureAtlas.AtlasRegion rock;
  
  private float rockHeight = worldYToScreenY(50.0F);
  
  private ArrayList<Rectangle> rockRectangles = new ArrayList<Rectangle>();
  
  private float rockWidth = worldXToScreenX(69.0F);
  
  private ArrayList<Integer> rockXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> rockYs = new ArrayList<Integer>();
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  public void dispose() {}
  
  void drawRock(SpriteBatch paramSpriteBatch, boolean paramBoolean, ShapeRenderer paramShapeRenderer, float paramFloat) {
    this.rockRectangles.clear();
    for (byte b = 0; b < this.rockXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.rock, ((Integer)this.rockXs.get(b)).intValue(), ((Integer)this.rockYs.get(b)).intValue(), this.rockWidth, this.rockHeight);
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.rockXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(paramFloat) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.rockRectangles.add(new Rectangle(((Integer)this.rockXs.get(b)).intValue(), ((Integer)this.rockYs.get(b)).intValue(), this.rockWidth, this.rockHeight));
    } 
  }
  
  float getFirstRockHitTimer() {
    return this.firstRockHitTimer;
  }
  
  float getLastRockTimer() {
    return this.lastRockTimer;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas, int paramInt, AssetManager paramAssetManager) {
    if (paramInt == 1) {
      this.rock = paramTextureAtlas.findRegion("stone");
    } else if (paramInt == 2) {
      this.rock = paramTextureAtlas.findRegion("stone_desert");
    } 
    this.musicSoundsObject = new MusicSounds(paramAssetManager);
  }
  
  boolean isFirstRockHit() {
    return this.firstRockHit;
  }
  
  void makeRock() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (this.rockHeight + f >= Gdx.graphics.getHeight()) {
      this.rockYs.add(Integer.valueOf((int)(f - this.rockHeight)));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.rockYs.add(Integer.valueOf((int)worldYToScreenY(70.0F)));
    } else {
      this.rockYs.add(Integer.valueOf((int)f));
    } 
    this.rockXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  void resetRockStats() {
    this.rockXs.clear();
    this.rockYs.clear();
    this.rockRectangles.clear();
    this.lastRockTimer = 0.0F;
    this.firstRockHitTimer = 0.0F;
    this.firstRockHit = false;
  }
  
  void rockCollisionFirst(Rectangle paramRectangle, float paramFloat) {
    for (byte b = 0; b < this.rockRectangles.size(); b++) {
      if (Intersector.overlaps(this.rockRectangles.get(b), paramRectangle)) {
        this.musicSoundsObject.playRockHit();
        this.firstRockHit = true;
        this.firstRockHitTimer = paramFloat;
        this.rockXs.remove(b);
        this.rockYs.remove(b);
        this.rockRectangles.remove(b);
        break;
      } 
    } 
  }
  
  int rockCollisionSecond(Rectangle paramRectangle, int paramInt, Preferences paramPreferences) {
    int i;
    byte b = 0;
    while (true) {
      i = paramInt;
      if (b < this.rockRectangles.size()) {
        if (Intersector.overlaps(this.rockRectangles.get(b), paramRectangle)) {
          this.musicSoundsObject.playRockHit();
          paramPreferences.putBoolean("playerGround", true);
          paramPreferences.flush();
          i = 2;
          this.rockXs.remove(b);
          this.rockYs.remove(b);
          this.rockRectangles.remove(b);
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return i;
  }
  
  void setFirstRockHitFalse() {
    this.firstRockHit = false;
  }
  
  void setLastRockTimer(float paramFloat) {
    this.lastRockTimer = paramFloat;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Rock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */