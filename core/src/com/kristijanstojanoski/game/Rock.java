package com.kristijanstojanoski.game;

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

import States.MusicSounds;

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

    void initializeValues(TextureAtlas mainGameAtlas, int stageNumber, AssetManager manager) {
        if (stageNumber == 1)
            rock = mainGameAtlas.findRegion("stone");
        else if (stageNumber == 2)
            rock = mainGameAtlas.findRegion("stone_desert");
        musicSoundsObject = new MusicSounds(manager);
    }

    void makeRock() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (rockHeight + height >= Gdx.graphics.getHeight())
            rockYs.add((int) (height - rockHeight));
        else if (height <= worldYToScreenY(70.0F))
            rockYs.add((int) worldYToScreenY(70.0F));
        else
            rockYs.add((int) height);
        rockXs.add(Gdx.graphics.getWidth());
    }

    void drawRock(SpriteBatch paramSpriteBatch, boolean pause, ShapeRenderer paramShapeRenderer, float rockSpeed) {
        this.rockRectangles.clear();
        for (byte b = 0; b < this.rockXs.size(); b++) {
            paramSpriteBatch.draw(this.rock, this.rockXs.get(b), this.rockYs.get(b), this.rockWidth, this.rockHeight);
            if (!pause) {
                ArrayList<Integer> arrayList = this.rockXs;
                arrayList.set(b, (int) (arrayList.get(b) - worldXToScreenX(rockSpeed) * Gdx.graphics.getDeltaTime() * 60.0F));
            }
            this.rockRectangles.add(new Rectangle(this.rockXs.get(b), this.rockYs.get(b), this.rockWidth, this.rockHeight));
        }
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

    float getFirstRockHitTimer() {
        return this.firstRockHitTimer;
    }

    float getLastRockTimer() {
        return this.lastRockTimer;
    }


    boolean isFirstRockHit() {
        return this.firstRockHit;
    }

    void setFirstRockHitFalse() {
        this.firstRockHit = false;
    }

    void setLastRockTimer(float paramFloat) {
        this.lastRockTimer = paramFloat;
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
    }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Rock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */