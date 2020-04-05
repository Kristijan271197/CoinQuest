package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import States.EndGame;

class CoinRush {
    private boolean coinRush = false;
    private boolean coinRushEnd = true;
    private float coinRushHeight = worldYToScreenY();
    private float coinRushOnTimer = 0.0F;
    private float coinRushWidth = worldXToScreenX(70.0F);
    private Random random = new Random(System.currentTimeMillis());
    private TextureAtlas.AtlasRegion speedCoinCollectible;
    private ArrayList<Rectangle> speedCoinCollectibleRectangles = new ArrayList<>();
    private ArrayList<Integer> speedCoinCollectibleXs = new ArrayList<>();
    private ArrayList<Integer> speedCoinCollectibleYs = new ArrayList<>();


    void initializeValues(TextureAtlas mainGameAtlas) {
        speedCoinCollectible = mainGameAtlas.findRegion("coin_rush");
    }

    void drawSpeedCoinCollectible(SpriteBatch batch, boolean pause) {
        speedCoinCollectibleRectangles.clear();
        for (int i = 0; i < speedCoinCollectibleXs.size(); i++) {
            batch.draw(speedCoinCollectible, speedCoinCollectibleXs.get(i), speedCoinCollectibleYs.get(i), coinRushWidth, coinRushHeight);
            if (!pause)
                speedCoinCollectibleXs.set(i, (int) (speedCoinCollectibleXs.get(i) - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F));
            speedCoinCollectibleRectangles.add(new Rectangle(speedCoinCollectibleXs.get(i), speedCoinCollectibleYs.get(i), coinRushWidth, coinRushHeight));
        }
    }

    void speedCoinCollision(Rectangle paramRectangle, float coinRushTimer, Preferences prefs) {
        for (int i = 0; i < speedCoinCollectibleRectangles.size(); i++) {
            if (Intersector.overlaps(paramRectangle, speedCoinCollectibleRectangles.get(i))) {
                prefs.putInteger(EndGame.POWER_UPS_COLLECTED, prefs.getInteger(EndGame.POWER_UPS_COLLECTED, 0) + 1);
                prefs.flush();
                coinRush = true;
                coinRushEnd = true;
                coinRushOnTimer = coinRushTimer;
                speedCoinCollectibleRectangles.remove(i);
                speedCoinCollectibleXs.remove(i);
                speedCoinCollectibleYs.remove(i);
                break;
            }
        }
    }

    void makeSpeedCoin() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (coinRushHeight + height >= Gdx.graphics.getHeight())
            speedCoinCollectibleYs.add((int) (height - coinRushHeight));
        else if (height <= worldYToScreenY())
            speedCoinCollectibleYs.add((int) worldYToScreenY());
        else
            speedCoinCollectibleYs.add((int) height);
        speedCoinCollectibleXs.add(Gdx.graphics.getWidth());
    }

    void resetCoinRushStats() {
        speedCoinCollectibleXs.clear();
        speedCoinCollectibleYs.clear();
        speedCoinCollectibleRectangles.clear();
        coinRush = false;
        coinRushEnd = true;
    }


    void setCoinRushEnd(boolean paramBoolean) {
        this.coinRushEnd = paramBoolean;
    }

    void setCoinRushFalse() {
        this.coinRush = false;
    }

    float getCoinRushOnTimer() {
        return this.coinRushOnTimer;
    }

    boolean isCoinRush() {
        return this.coinRush;
    }

    boolean isCoinRushEnd() {
        return this.coinRushEnd;
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY() {
        return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
    }

    public void dispose() {
    }
}
