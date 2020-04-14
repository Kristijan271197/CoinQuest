package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    private ArrayList<Rectangle> rockRectangles = new ArrayList<>();
    private float rockWidth = worldXToScreenX(69.0F);
    private ArrayList<Integer> rockXs = new ArrayList<>();
    private ArrayList<Integer> rockYs = new ArrayList<>();

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

    void drawRock(SpriteBatch batch, boolean pause, float rockSpeed) {
        rockRectangles.clear();
        for (int i = 0; i < rockXs.size(); i++) {
            batch.draw(rock, rockXs.get(i), rockYs.get(i), rockWidth, rockHeight);
            if (!pause)
                rockXs.set(i, (int) (rockXs.get(i) - worldXToScreenX(rockSpeed) * Gdx.graphics.getDeltaTime() * 60.0F));
            rockRectangles.add(new Rectangle(rockXs.get(i), rockYs.get(i), rockWidth, rockHeight));
        }
    }


    void resetRockStats() {
        rockXs.clear();
        rockYs.clear();
        rockRectangles.clear();
        lastRockTimer = 0.0F;
        firstRockHitTimer = 0.0F;
        firstRockHit = false;
    }

    void rockCollisionFirst(Rectangle paramRectangle, float paramFloat) {
        for (int i = 0; i < rockRectangles.size(); i++) {
            if (Intersector.overlaps(rockRectangles.get(i), paramRectangle)) {
                musicSoundsObject.playRockHit();
                firstRockHit = true;
                firstRockHitTimer = paramFloat;
                rockXs.remove(i);
                rockYs.remove(i);
                rockRectangles.remove(i);
                break;
            }
        }
    }

    int rockCollisionSecond(Rectangle paramRectangle, int gameState, Preferences prefs) {
        for (int i = 0; i < rockRectangles.size(); i++) {
            if (Intersector.overlaps(this.rockRectangles.get(i), paramRectangle)) {
                musicSoundsObject.playRockHit();
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                rockXs.remove(i);
                rockYs.remove(i);
                rockRectangles.remove(i);
                break;
            }
        }
        return gameState;
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
