package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

class Spikes {
    private float lastSpikeDownTimer = 0.0F;
    private Random random = new Random(System.currentTimeMillis());
    private TextureAtlas.AtlasRegion spikeDown;
    private int spikeDownTime;
    private ArrayList<Rectangle> spikesDownRectanglesFirst = new ArrayList<>();
    private ArrayList<Rectangle> spikesDownRectanglesSecond = new ArrayList<>();
    private ArrayList<Rectangle> spikesDownRectanglesThird = new ArrayList<>();
    private ArrayList<Integer> spikesDownXs = new ArrayList<>();
    private ArrayList<Integer> spikesDownYs = new ArrayList<>();


    void initializeValues(TextureAtlas mainGameAtlas) {
        spikeDown = mainGameAtlas.findRegion("cactus");
        spikeDownTime = random.nextInt(6001) + 4000;
    }

    void makeSpikeDown() {
        spikesDownXs.add(Gdx.graphics.getWidth());
        spikesDownYs.add((int) worldYToScreenY(85.0F));
    }

    void drawSpikeDown(SpriteBatch batch, boolean pause) {
        spikesDownRectanglesFirst.clear();
        spikesDownRectanglesSecond.clear();
        spikesDownRectanglesThird.clear();
        for (int i = 0; i < spikesDownXs.size(); i++) {
            batch.draw(spikeDown, spikesDownXs.get(i), spikesDownYs.get(i), worldXToScreenX(112.0F), worldYToScreenY(250.0F));
            if (!pause)
                spikesDownXs.set(i, (int) (spikesDownXs.get(i) - worldXToScreenX(3.0F) * Gdx.graphics.getDeltaTime() * 60.0F));
            spikesDownRectanglesFirst.add(new Rectangle(spikesDownXs.get(i) + worldXToScreenX(8.0F), spikesDownYs.get(i) + worldYToScreenY(75.0F), worldXToScreenX(13.0F), worldYToScreenY(75.0F)));
            spikesDownRectanglesSecond.add(new Rectangle(spikesDownXs.get(i) + worldXToScreenX(85.0F), spikesDownYs.get(i) + worldYToScreenY(110.0F), worldXToScreenX(20.0F), worldYToScreenY(80.0F)));
            spikesDownRectanglesThird.add(new Rectangle(spikesDownXs.get(i) + worldXToScreenX(45.0F), spikesDownYs.get(i), worldXToScreenX(20.0F), worldYToScreenY(250.0F)));
        }
    }


    void resetSpikeDownStats() {
        spikesDownXs.clear();
        spikesDownYs.clear();
        spikesDownRectanglesFirst.clear();
        spikesDownRectanglesSecond.clear();
        spikesDownRectanglesThird.clear();
        lastSpikeDownTimer = 0.0F;
    }


    int spikeDownCollision(Rectangle paramRectangle, int gameState, Preferences prefs) {
        for (int i = 0; i < spikesDownRectanglesFirst.size(); i++) {
            if (Intersector.overlaps(paramRectangle, spikesDownRectanglesFirst.get(i))) {
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                spikesDownXs.remove(i);
                spikesDownYs.remove(i);
                spikesDownRectanglesFirst.remove(i);
                spikesDownRectanglesSecond.remove(i);
                spikesDownRectanglesThird.remove(i);
                break;
            } else if (Intersector.overlaps(paramRectangle, spikesDownRectanglesSecond.get(i))) {
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                spikesDownXs.remove(i);
                spikesDownYs.remove(i);
                spikesDownRectanglesFirst.remove(i);
                spikesDownRectanglesSecond.remove(i);
                spikesDownRectanglesThird.remove(i);
                break;
            } else if (Intersector.overlaps(paramRectangle, spikesDownRectanglesThird.get(i))) {
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                spikesDownXs.remove(i);
                spikesDownYs.remove(i);
                spikesDownRectanglesFirst.remove(i);
                spikesDownRectanglesSecond.remove(i);
                spikesDownRectanglesThird.remove(i);
                break;
            }
        }
        return gameState;
    }

    void setLastSpikeDownTimer(float paramFloat) {
        this.lastSpikeDownTimer = paramFloat;
    }

    void setSpikeDownTime(int paramInt) {
        this.spikeDownTime = paramInt;
    }

    float getLastSpikeDownTimer() {
        return this.lastSpikeDownTimer;
    }

    int getSpikeDownTime() {
        return this.spikeDownTime;
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
