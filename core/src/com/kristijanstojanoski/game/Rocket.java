package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Rocket {
  private int arrowStateGreen = 0;
  
  private int arrowStateRed = 0;
  
  private int arrowStateYellow = 0;
  
  private float greenHeight;
  
  private float lastArrowGreenTimer = 0.0F;
  
  private float lastArrowRedTimer = 0.0F;
  
  private float lastArrowYellowTimer = 0.0F;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private float redHeight;
  
  private TextureAtlas.AtlasRegion rocket;
  
  private TextureAtlas.AtlasRegion[] rocketArrowGreen = new TextureAtlas.AtlasRegion[3];
  
  private TextureAtlas.AtlasRegion[] rocketArrowRed = new TextureAtlas.AtlasRegion[3];
  
  private TextureAtlas.AtlasRegion[] rocketArrowYellow = new TextureAtlas.AtlasRegion[3];
  
  private int rocketsArrowX;
  
  private int rocketsArrowYGreen;
  
  private int rocketsArrowYRed;
  
  private int rocketsArrowYYellow;
  
  private ArrayList<Rectangle> rocketsRectangles = new ArrayList<Rectangle>();
  
  private ArrayList<Integer> rocketsXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> rocketsYs = new ArrayList<Integer>();
  
  private float yellowHeight;
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  public void dispose() {}
  
  void drawArrows(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, SpriteBatch paramSpriteBatch) {
    if (paramBoolean1)
      paramSpriteBatch.draw((TextureRegion)this.rocketArrowRed[this.arrowStateRed], this.rocketsArrowX, this.rocketsArrowYRed, worldXToScreenX(96.0F), worldYToScreenY(50.0F)); 
    if (paramBoolean2)
      paramSpriteBatch.draw((TextureRegion)this.rocketArrowYellow[this.arrowStateYellow], this.rocketsArrowX, this.rocketsArrowYYellow, worldXToScreenX(96.0F), worldYToScreenY(50.0F)); 
    if (paramBoolean3)
      paramSpriteBatch.draw((TextureRegion)this.rocketArrowGreen[this.arrowStateGreen], this.rocketsArrowX, this.rocketsArrowYGreen, worldXToScreenX(96.0F), worldYToScreenY(50.0F)); 
  }
  
  void drawRocket(SpriteBatch paramSpriteBatch, boolean paramBoolean, ShapeRenderer paramShapeRenderer, float paramFloat) {
    this.rocketsRectangles.clear();
    for (byte b = 0; b < this.rocketsXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.rocket, ((Integer)this.rocketsXs.get(b)).intValue(), ((Integer)this.rocketsYs.get(b)).intValue(), worldXToScreenX(145.0F), worldYToScreenY(50.0F));
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.rocketsXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(paramFloat) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.rocketsRectangles.add(new Rectangle(((Integer)this.rocketsXs.get(b)).intValue(), ((Integer)this.rocketsYs.get(b)).intValue(), worldXToScreenX(145.0F), worldYToScreenY(50.0F)));
    } 
  }
  
  float getGreenHeight() {
    return this.greenHeight;
  }
  
  float getLastArrowGreenTimer() {
    return this.lastArrowGreenTimer;
  }
  
  float getLastArrowRedTimer() {
    return this.lastArrowRedTimer;
  }
  
  float getLastArrowYellowTimer() {
    return this.lastArrowYellowTimer;
  }
  
  float getRedHeight() {
    return this.redHeight;
  }
  
  float getYellowHeight() {
    return this.yellowHeight;
  }
  
  void greenArrowState(float paramFloat) {
    float f = this.lastArrowGreenTimer;
    if (paramFloat - f >= 1.9F && paramFloat - f <= 2.0F) {
      this.arrowStateGreen = 2;
    } else {
      f = this.lastArrowGreenTimer;
      if (paramFloat - f >= 1.0F && paramFloat - f <= 1.1F) {
        this.arrowStateGreen = 1;
      } else {
        f = this.lastArrowGreenTimer;
        if (paramFloat - f >= 0.0F && paramFloat - f <= 0.2F)
          this.arrowStateGreen = 0; 
      } 
    } 
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.rocket = paramTextureAtlas.findRegion("rocket");
    byte b1 = 0;
    for (byte b2 = 3; b1 < 3; b2--) {
      this.rocketArrowRed[b1] = paramTextureAtlas.findRegion("rocket_arrow", b2);
      this.rocketArrowYellow[b1] = paramTextureAtlas.findRegion("rocket_arrow", b2);
      this.rocketArrowGreen[b1] = paramTextureAtlas.findRegion("rocket_arrow", b2);
      b1++;
    } 
    this.redHeight = makeRocketArrowRed();
    this.yellowHeight = makeRocketArrowYellow();
    this.greenHeight = makeRocketArrowGreen();
  }
  
  void makeRocket(Float paramFloat) {
    float f = paramFloat.floatValue();
    if (worldYToScreenY(50.0F) + f >= Gdx.graphics.getHeight()) {
      this.rocketsYs.add(Integer.valueOf((int)(f - worldXToScreenX(50.0F))));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.rocketsYs.add(Integer.valueOf((int)worldYToScreenY(70.0F)));
    } else {
      this.rocketsYs.add(Integer.valueOf((int)f));
    } 
    this.rocketsXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  float makeRocketArrowGreen() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (worldYToScreenY(50.0F) + f >= Gdx.graphics.getHeight()) {
      this.rocketsArrowYGreen = (int)(f - worldXToScreenX(50.0F));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.rocketsArrowYGreen = (int)worldYToScreenY(70.0F);
    } else {
      this.rocketsArrowYGreen = (int)f;
    } 
    this.rocketsArrowX = (int)(Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
    return f;
  }
  
  float makeRocketArrowRed() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (worldYToScreenY(50.0F) + f >= Gdx.graphics.getHeight()) {
      this.rocketsArrowYRed = (int)(f - worldXToScreenX(50.0F));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.rocketsArrowYRed = (int)worldYToScreenY(70.0F);
    } else {
      this.rocketsArrowYRed = (int)f;
    } 
    this.rocketsArrowX = (int)(Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
    return f;
  }
  
  float makeRocketArrowYellow() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (worldYToScreenY(50.0F) + f >= Gdx.graphics.getHeight()) {
      this.rocketsArrowYYellow = (int)(f - worldXToScreenX(50.0F));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.rocketsArrowYYellow = (int)worldYToScreenY(70.0F);
    } else {
      this.rocketsArrowYYellow = (int)f;
    } 
    this.rocketsArrowX = (int)(Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
    return f;
  }
  
  void redArrowState(float paramFloat) {
    float f = this.lastArrowRedTimer;
    if (paramFloat - f >= 1.9F && paramFloat - f <= 2.0F) {
      this.arrowStateRed = 2;
    } else {
      f = this.lastArrowRedTimer;
      if (paramFloat - f >= 1.0F && paramFloat - f <= 1.1F) {
        this.arrowStateRed = 1;
      } else {
        f = this.lastArrowRedTimer;
        if (paramFloat - f >= 0.0F && paramFloat - f <= 0.1F)
          this.arrowStateRed = 0; 
      } 
    } 
  }
  
  void resetRocketStats() {
    this.rocketsXs.clear();
    this.rocketsYs.clear();
    this.rocketsRectangles.clear();
    this.lastArrowRedTimer = 0.0F;
    this.lastArrowYellowTimer = 0.0F;
    this.lastArrowGreenTimer = 0.0F;
  }
  
  int rocketCollision(Rectangle paramRectangle, int paramInt, Preferences paramPreferences) {
    int i;
    byte b = 0;
    while (true) {
      i = paramInt;
      if (b < this.rocketsRectangles.size()) {
        if (Intersector.overlaps(paramRectangle, this.rocketsRectangles.get(b))) {
          paramPreferences.putBoolean("playerGround", true);
          paramPreferences.flush();
          i = 2;
          this.rocketsXs.remove(b);
          this.rocketsYs.remove(b);
          this.rocketsRectangles.remove(b);
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return i;
  }
  
  void setGreenHeight(float paramFloat) {
    this.greenHeight = paramFloat;
  }
  
  void setLastArrowGreenTimer(float paramFloat) {
    this.lastArrowGreenTimer = paramFloat;
  }
  
  void setLastArrowRedTimer(float paramFloat) {
    this.lastArrowRedTimer = paramFloat;
  }
  
  void setLastArrowYellowTimer(float paramFloat) {
    this.lastArrowYellowTimer = paramFloat;
  }
  
  void setRedHeight(float paramFloat) {
    this.redHeight = paramFloat;
  }
  
  void setYellowHeight(float paramFloat) {
    this.yellowHeight = paramFloat;
  }
  
  void yellowArrowState(float paramFloat) {
    float f = this.lastArrowYellowTimer;
    if (paramFloat - f >= 1.9F && paramFloat - f <= 2.0F) {
      this.arrowStateYellow = 2;
    } else {
      f = this.lastArrowYellowTimer;
      if (paramFloat - f >= 1.0F && paramFloat - f <= 1.1F) {
        this.arrowStateYellow = 1;
      } else {
        f = this.lastArrowYellowTimer;
        if (paramFloat - f >= 0.0F && paramFloat - f <= 0.2F)
          this.arrowStateYellow = 0; 
      } 
    } 
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Rocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */