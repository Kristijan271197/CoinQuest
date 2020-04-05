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

class CoinMagnet {
    private TextureAtlas.AtlasRegion coinMagnetCollectible;
    private ArrayList<Rectangle> coinMagnetCollectibleRectangles = new ArrayList<>();
    private ArrayList<Integer> coinMagnetCollectibleXs = new ArrayList<>();
    private ArrayList<Integer> coinMagnetCollectibleYs = new ArrayList<>();
    private float coinMagnetHeight = worldYToScreenY();
    private float coinMagnetOnTimer = 0.0F;
    private float coinMagnetWidth = worldXToScreenX(70.0F);
    private boolean hasCoinMagnet = false;
    private Random random = new Random(System.currentTimeMillis());

    void initializeValues(TextureAtlas mainGameAtlas) {
        coinMagnetCollectible = mainGameAtlas.findRegion("coin_magnet");
    }

    void drawCoinMagnetCollectible(SpriteBatch batch, boolean pause) {
        coinMagnetCollectibleRectangles.clear();
        for (int i = 0; i < coinMagnetCollectibleXs.size(); i++) {
            batch.draw(coinMagnetCollectible, coinMagnetCollectibleXs.get(i), coinMagnetCollectibleYs.get(i), coinMagnetWidth, coinMagnetHeight);
            if (!pause)
                coinMagnetCollectibleXs.set(i, (int) (coinMagnetCollectibleXs.get(i) - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F));
            coinMagnetCollectibleRectangles.add(new Rectangle(coinMagnetCollectibleXs.get(i), coinMagnetCollectibleYs.get(i), coinMagnetWidth, coinMagnetHeight));
        }
    }

    void coinMagnetCollision(Rectangle paramRectangle, float coinMagnetTimer, Preferences prefs) {
        for (int i = 0; i < coinMagnetCollectibleRectangles.size(); i++) {
            if (Intersector.overlaps(paramRectangle, coinMagnetCollectibleRectangles.get(i))) {
                prefs.putInteger(EndGame.POWER_UPS_COLLECTED, prefs.getInteger(EndGame.POWER_UPS_COLLECTED, 0) + 1);
                prefs.flush();
                hasCoinMagnet = true;
                coinMagnetOnTimer = coinMagnetTimer;
                coinMagnetCollectibleRectangles.remove(i);
                coinMagnetCollectibleXs.remove(i);
                coinMagnetCollectibleYs.remove(i);
                break;
            }
        }
    }

    void makeCoinMagnet() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (coinMagnetHeight + height >= Gdx.graphics.getHeight())
            coinMagnetCollectibleYs.add((int) (height - coinMagnetHeight));
        else if (height <= worldYToScreenY())
            coinMagnetCollectibleYs.add((int) worldYToScreenY());
        else
            coinMagnetCollectibleYs.add((int) height);
        coinMagnetCollectibleXs.add(Gdx.graphics.getWidth());
    }

    void resetCoinMagnetState() {
        coinMagnetCollectibleXs.clear();
        coinMagnetCollectibleYs.clear();
        coinMagnetCollectibleRectangles.clear();
        coinMagnetOnTimer = 0.0F;
        hasCoinMagnet = false;
    }

    public void dispose() {
    }

    float getCoinMagnetOnTimer() {
        return this.coinMagnetOnTimer;
    }

    boolean isHasCoinMagnet() {
        return this.hasCoinMagnet;
    }

    void setHasCoinMagnet() {
        this.hasCoinMagnet = false;
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY() {
        return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
    }

}
