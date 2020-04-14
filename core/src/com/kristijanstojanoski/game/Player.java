package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;

import States.MusicSounds;
import States.Shop;

class Player {
    static final String PLAYER_GROUND = "playerGround";
    private MusicSounds musicSoundsObject;
    private int pause = 0;
    private AtlasRegion[] playerFaint;
    private int playerFaintState = 0;
    private float playerHeight = 0.0f;
    private float playerHeightRect = 0.0f;
    private AtlasRegion[] playerHurt;
    private Rectangle playerRectangle;
    private AtlasRegion[] playerRun;
    private int playerState = 0;
    private float playerWidth = 0.0f;
    private float playerWidthRect = 0.0f;
    private float playerX = 0.0f;
    private float playerXRect = 0.0f;
    private float playerY = 0.0f;
    private AtlasRegion tapToStart;
    private float velocity = 0.0f;

    public void initializeValues(AssetManager manager, Preferences prefs) {
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        TextureAtlas shopAtlas = manager.get("shop/shop.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);
        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
            playerRun = new AtlasRegion[43];
            playerFaint = new AtlasRegion[43];
            playerHurt = new AtlasRegion[43];
            for (int i = 0; i < 43; i++) {
                playerRun[i] = mainGameAtlas.findRegion("player_run", i);
                playerFaint[i] = mainGameAtlas.findRegion("player_faint", i);
                playerHurt[i] = mainGameAtlas.findRegion("player_hurt", i);
            }
            setParameters(100, 100, 155, 25, 60, 0);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER) {
            playerRun = new AtlasRegion[8];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[8];
            for (int i = 0; i < 8; i++) {
                playerRun[i] = shopAtlas.findRegion("robot_run", i);
                playerHurt[i] = shopAtlas.findRegion("robot_hurt", i);
            }
            for (int i = 0; i < 10; i++)
                playerFaint[i] = shopAtlas.findRegion("robot_faint", i);
            setParameters(65, 170, 170, 50, 60, 15);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER) {
            playerRun = new AtlasRegion[10];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[10];
            for (int i = 0; i < 10; i++) {
                playerRun[i] = shopAtlas.findRegion("knight_run", i);
                playerHurt[i] = shopAtlas.findRegion("knight_hurt", i);
                playerFaint[i] = shopAtlas.findRegion("knight_faint", i);
            }
            setParameters(85, 140, 170, 35, 60, 30);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER) {
            playerRun = new AtlasRegion[10];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[10];
            for (int i = 0; i < 10; i++) {
                playerRun[i] = shopAtlas.findRegion("cowboy_run", i);
                playerHurt[i] = shopAtlas.findRegion("cowboy_hurt", i);
                playerFaint[i] = shopAtlas.findRegion("cowboy_faint", i);
            }
            setParameters(85, 120, 145, 35, 60, 30);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER) {
            playerRun = new AtlasRegion[8];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[8];
            for (int i = 0; i < 10; i++)
                playerFaint[i] = shopAtlas.findRegion("cowgirl_faint", i);

            for (int i = 0; i < 8; i++) {
                playerRun[i] = shopAtlas.findRegion("cowgirl_run", i);
                playerHurt[i] = shopAtlas.findRegion("cowgirl_hurt", i);
            }
            setParameters(60, 170, 145, 35, 60, 30);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER) {
            playerRun = new AtlasRegion[10];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[10];
            for (int i = 0; i < 10; i++) {
                playerFaint[i] = shopAtlas.findRegion("ninja_male_faint", i);
                playerRun[i] = shopAtlas.findRegion("ninja_male_run", i);
                playerHurt[i] = shopAtlas.findRegion("ninja_male_hurt", i);
            }
            setParameters(90, 110, 140, 35, 60, 30);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER) {
            playerRun = new AtlasRegion[10];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[10];
            for (int i = 0; i < 10; i++) {
                playerFaint[i] = shopAtlas.findRegion("ninja_female_faint", i);
                playerRun[i] = shopAtlas.findRegion("ninja_female_run", i);
                playerHurt[i] = shopAtlas.findRegion("ninja_female_hurt", i);
            }
            setParameters(90, 110, 150, 35, 60, 30);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER) {
            playerRun = new AtlasRegion[10];
            playerFaint = new AtlasRegion[10];
            playerHurt = new AtlasRegion[10];
            for (int i = 0; i < 8; i++) {
                playerFaint[i] = shopAtlas.findRegion("dino_faint", i);
                playerRun[i] = shopAtlas.findRegion("dino_run", i);
                playerHurt[i] = shopAtlas.findRegion("dino_hurt", i);
            }
            setParameters(80, 190, 130, 35, 70, 30);
        }
        tapToStart = mainGameAtlas.findRegion("tap_to_start_finger");
    }


    void drawPlayerFaint(SpriteBatch batch, boolean pause, Preferences prefs) {
        if (!pause) {
            velocity = (worldYToScreenY(0.5f) * Gdx.graphics.getDeltaTime() * 60.0f) + velocity;
            playerY = playerY - velocity;

            if (playerY <= worldYToScreenY(75.0f)) {
                if (prefs.getBoolean(PLAYER_GROUND, false)) {
                    musicSoundsObject.playPlayerGround();
                    prefs.putBoolean(PLAYER_GROUND, false);
                    prefs.flush();
                }

                playerY = worldYToScreenY(75.0f);
                if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
                    if (playerFaintState < 42) {
                        playerFaintState = playerFaintState + 1;
                    }
                } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
                    playerFaintState(2, 9);
                else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
                    playerFaintState(3, 7);

            }

            if (playerY + playerHeight >= ((float) Gdx.graphics.getHeight())) {
                playerY = ((float) Gdx.graphics.getHeight()) - playerHeight;
            }
        }

        playerX = worldXToScreenX(0.0f);
        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER)
            batch.draw(playerFaint[playerFaintState], playerX, playerY, worldXToScreenX(200.0f), playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(85.0f) + playerX, playerY, playerWidth, playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
            batch.draw(playerFaint[playerFaintState], playerX - worldXToScreenX(5.0f), playerY, worldXToScreenX(90.0f) + playerWidth, playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(85.0f) + playerX, playerY, worldXToScreenX(90.0f) + playerWidth, playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(85.0f) + playerX, playerY, playerWidth - worldXToScreenX(10.0f), worldXToScreenX(15.0f) + playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(90.0f) + playerX, playerY, worldXToScreenX(35.0f) + playerWidth, worldXToScreenX(10.0f) + playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(90.0f) + playerX, playerY, worldXToScreenX(60.0f) + playerWidth, worldXToScreenX(20.0f) + playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
            batch.draw(playerFaint[playerFaintState], worldXToScreenX(80.0f) + playerX, playerY, playerWidth, playerHeight);

    }

    void drawPlayerRun(SpriteBatch batch, Shield shieldObject, Rock rockObject, boolean pause, Preferences prefs) {
        if (shieldObject.isHasShield())
            shieldObject.drawShield(batch, playerX, playerY, pause, prefs);

        if (rockObject.isFirstRockHit())
            batch.draw(playerHurt[playerState], playerX, playerY, playerWidth, playerHeight);
        else
            batch.draw(playerRun[playerState], playerX, playerY, playerWidth, playerHeight);

        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(25.0F), playerY, worldXToScreenX(60.0F), playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(50.0F), playerY, worldXToScreenX(60.0F), playerHeight - worldYToScreenY(15.0F));
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(35.0F), playerY, worldXToScreenX(60.0F), playerHeight - worldYToScreenY(30.0F));
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(35.0F), playerY, worldXToScreenX(60.0F), playerHeight);
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(60.0F), playerY, worldXToScreenX(60.0F), playerHeight - worldYToScreenY(10.0F));
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(30.0F), playerY, worldXToScreenX(60.0F), playerHeight - worldYToScreenY(2.0F));
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(30.0F), playerY, worldXToScreenX(60.0F), playerHeight - worldYToScreenY(5.0F));
        else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
            playerRectangle = new Rectangle(playerX + worldXToScreenX(35.0F), playerY, worldXToScreenX(70.0F), playerHeight);


        if (!pause) {
            if (Gdx.input.justTouched()) {
                if (!(Gdx.input.getX() < 0 || (float) Gdx.input.getX() > Gdx.graphics.getWidth() || Gdx.input.getY() < 0 || (float) Gdx.input.getY() >= Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 15.0F)) {
                    velocity = -worldYToScreenY(12.5F) * Gdx.graphics.getDeltaTime() * 60.0F;
                    musicSoundsObject.playJump();
                }
            }

            if (playerY + playerHeight >= (float) Gdx.graphics.getHeight())
                velocity = Gdx.graphics.getDeltaTime() * 1.0F * 60.0F;

            if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
                if (playerState < 42)
                    playerState = playerState + 1;
                else
                    playerState = 0;

            } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
                playerRunState(3, 7, 15);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
                playerRunState(2, 9, 30);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
                playerRunState(2, 9, 0);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
                playerRunState(3, 7, 10);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
                playerRunState(2, 9, 2);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
                playerRunState(2, 9, 5);
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
                playerRunState(3, 7, 0);

            velocity = (worldYToScreenY(0.5f) * Gdx.graphics.getDeltaTime() * 60.0f) + velocity;
            playerY = playerY - velocity;

            if (playerY <= worldYToScreenY(75.0F))
                playerY = worldYToScreenY(75.0F);
        }
    }

    void drawPlayerStart(SpriteBatch batch, Preferences prefs) {
        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
            playerX = worldXToScreenX(100.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER) {
            playerX = worldXToScreenX(65.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER) {
            playerX = worldXToScreenX(85.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER) {
            playerX = worldXToScreenX(85.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER) {
            playerX = worldXToScreenX(60.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER) {
            playerX = worldXToScreenX(90.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER) {
            playerX = worldXToScreenX(90.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER) {
            playerX = worldXToScreenX(80.0f);
            playerY = worldYToScreenY(600.0f);
        }

        batch.draw(playerRun[playerState], playerX, playerY, playerWidth, playerHeight);
        batch.draw(tapToStart, worldXToScreenX(250.0f), worldYToScreenY(425.0f), worldXToScreenX(150.0f), worldYToScreenY(150.0f));
    }

    void drawPlayerWin(SpriteBatch batch) {
        velocity = (worldYToScreenY(0.5f) * Gdx.graphics.getDeltaTime() * 60.0f) + velocity;
        playerY = playerY - velocity;

        if (playerY <= worldYToScreenY(75.0f)) {
            playerY = worldYToScreenY(75.0f);
            batch.draw(playerRun[playerState], playerX, playerY, playerWidth, playerHeight);
        } else
            batch.draw(playerRun[playerState], playerX, playerY, playerWidth, playerHeight);

        if (playerY + playerHeight >= ((float) Gdx.graphics.getHeight()))
            playerY = ((float) Gdx.graphics.getHeight()) - playerHeight;
    }


    void resetPlayerStats(Preferences prefs) {
        if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER) {
            playerX = worldXToScreenX(100.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER) {
            playerX = worldXToScreenX(65.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER) {
            playerX = worldXToScreenX(65.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER) {
            playerX = worldXToScreenX(85.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER) {
            playerX = worldXToScreenX(60.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER) {
            playerX = worldXToScreenX(90.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER) {
            playerX = worldXToScreenX(90.0f);
            playerY = worldYToScreenY(600.0f);
        } else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER) {
            playerX = worldXToScreenX(80.0f);
            playerY = worldYToScreenY(600.0f);
        }
        velocity = 0.0f;
        playerFaintState = 0;
    }


    private void playerFaintState(int faintPause, int faintState) {
        if (pause < faintPause)
            pause = pause + 1;
        else {
            pause = 0;
            if (playerFaintState < faintState)
                playerFaintState = playerFaintState + 1;
        }

    }


    private void playerRunState(int runPause, int runState, int playerY) {
        if (pause < runPause)
            pause = pause + 1;
        else {
            pause = 0;
            if (playerState < runState)
                playerState = playerState + 1;
            else
                playerState = 0;
        }

        if ((playerY + playerHeight) - worldYToScreenY((float) playerY) >= ((float) Gdx.graphics.getHeight()))
            velocity = Gdx.graphics.getDeltaTime() * 1.0f * 60.0f;

    }

    private void setParameters(int X, int width, int height, int xRect, int widthRect, int heightRect) {
        playerX = worldXToScreenX((float) X);
        playerY = worldYToScreenY((float) 600);
        playerWidth = worldXToScreenX((float) width);
        playerHeight = worldYToScreenY((float) height);
        playerXRect = playerX + worldXToScreenX((float) xRect);
        playerWidthRect = worldXToScreenX((float) widthRect);
        playerHeightRect = playerHeight - worldYToScreenY((float) heightRect);
    }

    private float worldXToScreenX(float f) {
        return (((float) Gdx.graphics.getWidth()) / 500.0f) * f;
    }

    private float worldYToScreenY(float f) {
        return (((float) Gdx.graphics.getHeight()) / 1000.0f) * f;
    }

    public void dispose() {
    }


    float getPlayerHeightRect() {
        return this.playerHeightRect;
    }


    Rectangle getPlayerRectangle() {
        return this.playerRectangle;
    }


    float getPlayerWidthRect() {
        return this.playerWidthRect;
    }


    float getPlayerXRect() {
        return this.playerXRect;
    }


    float getPlayerY() {
        return this.playerY;
    }

}
