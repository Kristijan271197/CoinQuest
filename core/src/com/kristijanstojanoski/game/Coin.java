package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import States.MusicSounds;

class Coin {
    private TextureAtlas.AtlasRegion coin;
    private int coinCount = 0;
    private float coinHeight = worldYToScreenY();
    private ArrayList<Rectangle> coinRectangles = new ArrayList<>();
    private float coinSpeed = worldXToScreenX(5.0F);
    private int coinTime;
    private float coinWidth = worldXToScreenX(40.0F);
    private ArrayList<Float> coinXs = new ArrayList<>();
    private ArrayList<Float> coinYs = new ArrayList<>();
    private float lastCoinTimer = 0.0F;
    private MusicSounds musicSoundsObject;
    private Random random = new Random(System.currentTimeMillis());


    void initializeValues(TextureAtlas mainGameAtlas, AssetManager manager) {
        coin = mainGameAtlas.findRegion("coin");
        coinTime = random.nextInt(2001) + 1000;
        musicSoundsObject = new MusicSounds(manager);
    }

    void drawCoin(SpriteBatch batch, boolean pause) {
        coinRectangles.clear();
        for (int i = 0; i < coinXs.size(); i++) {
            batch.draw(coin, coinXs.get(i), coinYs.get(i), coinWidth, coinHeight);
            if (!pause)
                coinXs.set(i, coinXs.get(i) - coinSpeed * Gdx.graphics.getDeltaTime() * 60.0F);
            coinRectangles.add(new Rectangle(coinXs.get(i), coinYs.get(i), coinWidth, coinHeight));
        }
    }

    void drawCoinMagnetized(SpriteBatch batch, float playerX, float playerY, float playerWidth, float playerHeight, boolean pause) {
        coinRectangles.clear();
        playerWidth = playerWidth / 2.0F - playerHeight / 2.0F;
        playerHeight = coinWidth / 2.0F - coinHeight / 2.0F;
        for (int i = 0; i < coinXs.size(); i++) {
            float f1 = playerX - playerWidth - coinXs.get(i) + playerHeight;
            float f2 = playerY - playerWidth - coinYs.get(i) + playerHeight;
            double d = f1;
            float f3 = f2 * f2;
            f1 = (float) (d / Math.sqrt((f1 * f1 + f3)));
            f2 = (float) (f2 / Math.sqrt((f1 * f1 + f3)));
            f3 = coinSpeed;
            if (!pause) {
                coinXs.set(i, coinXs.get(i) + f1 * f3);
                coinYs.set(i, coinYs.get(i) + f2 * f3);
            }
            batch.draw(coin, coinXs.get(i), coinYs.get(i), coinWidth, coinHeight);
            coinRectangles.add(new Rectangle(coinXs.get(i), coinYs.get(i), coinWidth, coinHeight));
        }
    }


    void coinCollision(Rectangle paramRectangle, boolean doubleCoins) {
        for (int i = 0; i < coinRectangles.size(); i++) {
            if (Intersector.overlaps(paramRectangle, coinRectangles.get(i))) {
                musicSoundsObject.playCoinCollect();
                coinRectangles.remove(i);
                coinXs.remove(i);
                coinYs.remove(i);
                if (doubleCoins) {
                    coinCount += 2;
                    break;
                } else
                    coinCount++;
                break;
            }
        }
    }


    void makeCoin() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (coinHeight + height >= Gdx.graphics.getHeight())
            coinYs.add(height - coinHeight);
         else
            coinYs.add(Math.max(height, worldYToScreenY()));
        coinXs.add((float) Gdx.graphics.getWidth());
    }

    void removeCoin() {
        for (int i = 0; i < coinXs.size(); i++) {
            if (coinXs.get(i) + coinWidth <= 0.0F) {
                coinXs.remove(i);
                coinYs.remove(i);
                coinRectangles.remove(i);
                break;
            }
        }
    }

    void resetCoinStats() {
        coinXs.clear();
        coinYs.clear();
        coinRectangles.clear();
        coinSpeed = worldXToScreenX(5.0F);
        lastCoinTimer = 0.0F;
    }

    public void dispose() {
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

    private float worldYToScreenY() {
        return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
    }
}
