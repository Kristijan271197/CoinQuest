package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class SpinningWheel extends State {
    private static final String SPIN_BUTTON_TIMER = "spinButtonTimer";
    private static final String WHEEL_MULTIPLIER = "wheelMultiplier";
    private static final String WHEEL_MULTIPLIER_DAY = "wheelMultiplierDay";
    private static final String WHEEL_MULTIPLIER_WEEK = "wheelMultiplierWeek";
    private static final String WHEEL_SPIN_MULTIPLIER = "wheelSpinMultiplier";
    private static final String WHEEL_SPUN_TIMES = "wheelSpunTimes";

    private BitmapFont buttonsTextFont;
    private Calendar calendarG;
    private Calendar calendarGPrevious;
    private TextureAtlas.AtlasRegion coin;
    private BitmapFont coinDiamondFont;
    private int coinGlobal;
    private boolean firstSpin;
    private boolean isCoins;
    private MusicSounds musicSoundsObject;
    private boolean onceSound = false;
    private Preferences prefs;
    private Random random;
    private long rewardWindowTime;
    private TextureAtlas.AtlasRegion ruby;
    private int rubyGlobal;
    private SequenceAction sequence;
    private boolean showRewardWindow;
    private long spinButtonTimeRemaining;
    private Image spinningWheelFront;
    private int spinningWheelReward;
    private BitmapFont spinsWindowTextFont;
    private Stage stage;
    private int wheelMultiplier;
    private int wheelMultiplierDay;
    private int wheelMultiplierWeek;
    private int wheelSpinMultiplier;

    SpinningWheel(GameStateManager paramGameStateManager, final AdsController adsController, final AssetManager manager) {
        super(paramGameStateManager);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //Achievements achievements = new Achievements();

        Date date = new Date();
        calendarG = new GregorianCalendar();
        calendarG.setTime(date);

        calendarGPrevious = new GregorianCalendar();
        calendarGPrevious.setTime(date);
        calendarGPrevious.add(Calendar.DATE, -1);

        musicSoundsObject = new MusicSounds(manager);

        TextureAtlas spinningWheelAtlas = manager.get("spinning_wheel/spinning_wheel.atlas", TextureAtlas.class);
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        TextureAtlas shopAtlas = manager.get("shop/shop.atlas", TextureAtlas.class);

        sequence = new SequenceAction();
        random = new Random(System.currentTimeMillis());

        prefs = Gdx.app.getPreferences("prefs");
        coinGlobal = prefs.getInteger(Shop.COINS);
        rubyGlobal = prefs.getInteger(Shop.DIAMONDS);
        spinButtonTimeRemaining = prefs.getLong(SPIN_BUTTON_TIMER);
        wheelMultiplier = prefs.getInteger(WHEEL_MULTIPLIER, 1);
        wheelMultiplierDay = prefs.getInteger(WHEEL_MULTIPLIER_DAY, calendarG.get(Calendar.DATE));
        wheelSpinMultiplier = prefs.getInteger(WHEEL_SPIN_MULTIPLIER, 0);
        wheelMultiplierWeek = prefs.getInteger(WHEEL_MULTIPLIER_WEEK, 1);
        firstSpin = true;
        if (prefs.getInteger("wheelsSpunAtmAchievement", 0) < 7) {
            //achievements.checkWheelsSpun(prefs.getInteger("wheelSpunTimes", 0));
        }

        coin = sharedAtlas.findRegion("coin");
        ruby = sharedAtlas.findRegion("diamond");

        coinDiamondFont = manager.get("font/font_scale_07.fnt", BitmapFont.class);
        coinDiamondFont.setColor(Color.WHITE);
        coinDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));

        buttonsTextFont = manager.get("font/font_scale_gray_1.fnt", BitmapFont.class);
        buttonsTextFont.setColor(Color.BLACK);
        buttonsTextFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        spinsWindowTextFont = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        spinsWindowTextFont.setColor(Color.WHITE);
        spinsWindowTextFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        Image[] multiplierRed = new Image[5];
        Image[] multiplierGreen = new Image[5];

        for (int i = 0; i < 5; i++) {
            multiplierRed[i] = new Image(spinningWheelAtlas.findRegion("multiplier_red"));
            multiplierGreen[i] = new Image(spinningWheelAtlas.findRegion("multiplier_green"));
        }

        for (int i = 0; i < 5; i++) {
            multiplierRed[i].setSize(worldXToScreenX(75.0F), worldYToScreenY(100.0F));
            multiplierRed[i].setPosition(worldXToScreenX(i * 75.0F + 62.5F), worldYToScreenY(255.0F));
            multiplierGreen[i].setSize(worldXToScreenX(75.0F), worldYToScreenY(100.0F));
            multiplierGreen[i].setPosition(worldXToScreenX(i * 75.0F + 62.5F), worldYToScreenY(255.0F));
        }

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        spinningWheelFront = new Image(spinningWheelAtlas.findRegion("spinning_wheel_front"));
        spinningWheelFront.setSize(worldXToScreenX(300.0F), worldXToScreenX(300.0F));
        spinningWheelFront.setPosition(worldXToScreenX(100.0F), worldYToScreenY(390.0F));
        spinningWheelFront.setOrigin(1);

        Image spinningWheelBack = new Image(spinningWheelAtlas.findRegion("spinning_wheel_back"));
        spinningWheelBack.setSize(worldXToScreenX(350.0F), worldXToScreenX(350.0F));
        spinningWheelBack.setPosition(worldXToScreenX(75.0F), worldYToScreenY(365.0F));

        Image spinningWheelArrow = new Image(spinningWheelAtlas.findRegion("spinning_wheel_arrow"));
        spinningWheelArrow.setSize(worldXToScreenX(50.0F), worldYToScreenY(90.0F));
        spinningWheelArrow.setPosition(worldXToScreenX(225.0F), worldYToScreenY(710.0F));

        ImageButton.ImageButtonStyle spinButtonStyle = new ImageButton.ImageButtonStyle();
        spinButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("long_button_unpressed")));
        spinButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("long_button_pressed")));

        ImageButton freeSpinButton = new ImageButton(spinButtonStyle);
        freeSpinButton.setPosition(worldXToScreenX(135.0F), worldYToScreenY(820.0F));
        freeSpinButton.setSize(worldXToScreenX(230.0F), worldYToScreenY(92.0F));
        freeSpinButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                spinButtonTimeRemaining = System.currentTimeMillis();
                spinWheel();
                prefs.putLong(SPIN_BUTTON_TIMER, System.currentTimeMillis());
                prefs.flush();
                musicSoundsObject.playWheelSpin();
            }
        });
        ImageButton spinButtonDiamonds = new ImageButton(spinButtonStyle);
        spinButtonDiamonds.setPosition(worldXToScreenX(135.0F), worldYToScreenY(105.0F));
        spinButtonDiamonds.setSize(worldXToScreenX(230.0F), worldYToScreenY(92.0F));
        spinButtonDiamonds.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (rubyGlobal >= 5) {
                    prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) - 5);
                    prefs.flush();
                    rubyGlobal = prefs.getInteger(Shop.DIAMONDS);
                    spinWheel();
                    musicSoundsObject.playWheelSpin();
                } else {
                    stage.getActors().get(3).setTouchable(Touchable.disabled);
                    stage.getActors().get(4).setTouchable(Touchable.disabled);
                    stage.getActors().get(7).setTouchable(Touchable.disabled);
                    stage.getActors().get(8).setVisible(true);
                    stage.getActors().get(9).setVisible(true);
                }
            }
        });

        Image congratulationsWindow = new Image(new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window"))));
        congratulationsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        congratulationsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(375.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButtonCongratulationsWindow = new ImageButton(xButtonStyle);
        xButtonCongratulationsWindow.setPosition(worldXToScreenX(420.0F), worldYToScreenY(490.0F));
        xButtonCongratulationsWindow.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButtonCongratulationsWindow.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                musicSoundsObject.getWheelReward().stop();
                gsm.set(new SpinningWheel(gsm, adsController, manager));
                dispose();
            }
        });

        Image spinningWheelMultiplier = new Image(new TextureRegionDrawable(new TextureRegion(spinningWheelAtlas.findRegion("spinning_wheel_multiplier"))));
        spinningWheelMultiplier.setSize(worldXToScreenX(375.0F), worldYToScreenY(100.0F));
        spinningWheelMultiplier.setPosition(worldXToScreenX(62.5F), worldYToScreenY(255.0F));

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));

        Image notEnoughDiamondsWindow = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("not_enough_diamonds_window"))));
        notEnoughDiamondsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        notEnoughDiamondsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton.ImageButtonStyle notEnoughDiamondsXButtonStyle = new ImageButton.ImageButtonStyle();
        notEnoughDiamondsXButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        notEnoughDiamondsXButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton notEnoughDiamondsXButton = new ImageButton(notEnoughDiamondsXButtonStyle);
        notEnoughDiamondsXButton.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
        notEnoughDiamondsXButton.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        notEnoughDiamondsXButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(3).setTouchable(Touchable.enabled);
                stage.getActors().get(4).setTouchable(Touchable.enabled);
                stage.getActors().get(7).setTouchable(Touchable.enabled);
                stage.getActors().get(8).setVisible(false);
                stage.getActors().get(9).setVisible(false);
                musicSoundsObject.playButtonClick();
            }
        });

        Image sureQuitWindow = new Image(sharedAtlas.findRegion("sure_quit_window"));
        sureQuitWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
        sureQuitWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));

        ImageButton.ImageButtonStyle notQuitButtonStyle = new ImageButton.ImageButtonStyle();
        notQuitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        notQuitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton notQuitButton = new ImageButton(notQuitButtonStyle);
        notQuitButton.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
        notQuitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        notQuitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(10).setVisible(false);
                stage.getActors().get(11).setVisible(false);
                stage.getActors().get(12).setVisible(false);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        quitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));

        ImageButton quitButton = new ImageButton(quitButtonStyle);
        quitButton.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
        quitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        quitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                Gdx.app.exit();
            }
        });

        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        backButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        backButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new MainMenu(gsm, adsController, manager));
                musicSoundsObject.playButtonClick();
                dispose();
            }
        });

        stage.addActor(bg);
        stage.addActor(spinningWheelBack);
        stage.addActor(spinningWheelFront);
        stage.addActor(freeSpinButton);
        stage.addActor(spinButtonDiamonds);
        stage.addActor(congratulationsWindow);
        stage.addActor(xButtonCongratulationsWindow);
        stage.addActor(backButton);
        stage.addActor(notEnoughDiamondsWindow);
        stage.addActor(notEnoughDiamondsXButton);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        for (int i = 0; i < 5; i++)
            stage.addActor(multiplierRed[i]);
        for (int i = 0; i < 5; i++)
            stage.addActor(multiplierGreen[i]);
        stage.addActor(spinningWheelMultiplier);
        stage.addActor(spinningWheelArrow);
        stage.getActors().get(5).setVisible(false);
        stage.getActors().get(6).setVisible(false);
        stage.getActors().get(8).setVisible(false);
        stage.getActors().get(9).setVisible(false);
        stage.getActors().get(10).setVisible(false);
        stage.getActors().get(11).setVisible(false);
        stage.getActors().get(12).setVisible(false);

        showGreenMultipliers();
    }



    public void dispose() {
        stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch batch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(10).setVisible(true);
            stage.getActors().get(11).setVisible(true);
            stage.getActors().get(12).setVisible(true);
        }

        if (calendarG.get(Calendar.WEEK_OF_YEAR) != wheelMultiplierWeek) {
            prefs.putInteger(WHEEL_MULTIPLIER, 1);
            prefs.putInteger(WHEEL_SPIN_MULTIPLIER, 0);
            prefs.putInteger(WHEEL_MULTIPLIER_WEEK, calendarG.get(Calendar.WEEK_OF_YEAR));
            prefs.flush();
            wheelMultiplier = prefs.getInteger(WHEEL_MULTIPLIER);
            wheelSpinMultiplier = prefs.getInteger(WHEEL_SPIN_MULTIPLIER);
            wheelMultiplierWeek = prefs.getInteger(WHEEL_MULTIPLIER_WEEK);
            showGreenMultipliers();
        } else if (calendarG.get(Calendar.DATE) != wheelMultiplierDay && calendarGPrevious.get(Calendar.DATE) != wheelMultiplierDay && firstSpin) {
            prefs.putInteger(WHEEL_MULTIPLIER, 1);
            prefs.putInteger(WHEEL_SPIN_MULTIPLIER, 0);
            prefs.flush();
            wheelMultiplier = prefs.getInteger(WHEEL_MULTIPLIER);
            wheelSpinMultiplier = prefs.getInteger(WHEEL_SPIN_MULTIPLIER);
            showGreenMultipliers();
        }

        int spinUntilNextMultiplier = 0;
        int wheelSpinMultiplierNumber = 0;
        if (wheelSpinMultiplier < 5) {
            this.prefs.putInteger(WHEEL_MULTIPLIER, 1);
            spinUntilNextMultiplier = wheelSpinMultiplier;
        } else if (wheelSpinMultiplier < 11) {
            this.prefs.putInteger(WHEEL_MULTIPLIER, 2);
            spinUntilNextMultiplier = 6;
            wheelSpinMultiplierNumber = wheelSpinMultiplier - 5;
        } else if (wheelSpinMultiplier < 18) {
            this.prefs.putInteger(WHEEL_MULTIPLIER, 3);
            spinUntilNextMultiplier = 7;
            wheelSpinMultiplierNumber = wheelSpinMultiplier - 11;
        } else if (wheelSpinMultiplier < 26) {
            this.prefs.putInteger(WHEEL_MULTIPLIER, 4);
            spinUntilNextMultiplier = 8;
            wheelSpinMultiplierNumber = wheelSpinMultiplier - 18;
        } else
            this.prefs.putInteger(WHEEL_MULTIPLIER, 5);
        prefs.flush();

        wheelMultiplier = prefs.getInteger(WHEEL_MULTIPLIER);
        showGreenMultipliers();

        long timeRemaining = (43200000L - (System.currentTimeMillis() - spinButtonTimeRemaining)) / 1000L / 60L;

        batch.begin();
        batch.draw(coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinDiamondFont.draw(batch, String.valueOf(coinGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
        batch.draw(ruby, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinDiamondFont.draw(batch, String.valueOf(rubyGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));

        if (System.currentTimeMillis() - spinButtonTimeRemaining >= 43200000L) {
            stage.getActors().get(3).setTouchable(Touchable.enabled);
            buttonsTextFont.draw(batch, "Free Spin", worldXToScreenX(185.0F), worldYToScreenY(880.0F));
        } else {
            stage.getActors().get(3).setTouchable(Touchable.disabled);
            buttonsTextFont.draw(batch, "Free Spin in:", worldXToScreenX(162.5F), worldYToScreenY(895.0F));
            buttonsTextFont.draw(batch, timeRemaining / 60L + "h:" + timeRemaining % 60L + "m", worldXToScreenX(235.0F), worldYToScreenY(865.0F), worldXToScreenX(30.0F), Align.center, false);
        }
        if (showRewardWindow && System.currentTimeMillis() - rewardWindowTime >= 7500L) {
            if (onceSound) {
                musicSoundsObject.playWheelReward();
                onceSound = false;
            }

            if (isCoins)
                spinsWindowTextFont.draw(batch, "You received " + spinningWheelReward * wheelMultiplier + " coins", worldXToScreenX(90.0F), worldYToScreenY(465.0F));
            else
                spinsWindowTextFont.draw(batch, "You received " + spinningWheelReward * wheelMultiplier + " diamonds", worldXToScreenX(90.0F), worldYToScreenY(465.0F));
            stage.getActors().get(5).setVisible(true);
            stage.getActors().get(6).setVisible(true);
        }
        if (wheelSpinMultiplier < 26)
            spinsWindowTextFont.draw(batch, "Spins until next multiplier " + wheelSpinMultiplierNumber + "/" + spinUntilNextMultiplier, worldXToScreenX(50.0F), worldYToScreenY(240.0F));

        batch.draw(ruby, worldXToScreenX(155.0F), worldYToScreenY(140.0F), worldXToScreenX(30.0F), worldYToScreenY(30.0F));
        buttonsTextFont.draw(batch, "5   Spin", worldXToScreenX(185.0F), worldYToScreenY(165.0F));
        batch.end();
    }

    public void update(float paramFloat) {
    }

    private void showGreenMultipliers() {
        for (int i = 0; i < 5; i++)
            stage.getActors().get(i + 18).setVisible(false);
        for (int i = 0; i < wheelMultiplier; i++)
            stage.getActors().get(i + 18).setVisible(true);
    }

    private void spinWheel() {
        int wheelChance = random.nextInt(100) + 1;
        int chanceNumber = random.nextInt(2) + 1;
        rewardWindowTime = System.currentTimeMillis();
        showRewardWindow = true;
        firstSpin = false;
        onceSound = true;

        prefs.putInteger(WHEEL_SPUN_TIMES, prefs.getInteger(WHEEL_SPUN_TIMES, 0) + 1);
        prefs.putInteger(WHEEL_MULTIPLIER_DAY, this.calendarG.get(Calendar.DATE));
        if (prefs.getInteger(WHEEL_SPIN_MULTIPLIER) != 26)
            prefs.putInteger(WHEEL_SPIN_MULTIPLIER, prefs.getInteger(WHEEL_SPIN_MULTIPLIER) + 1);
        prefs.flush();
        wheelMultiplier = prefs.getInteger("wheelMultiplier");

        if (wheelChance >= 1 && wheelChance <= 5) {
            prefs.putInteger(Shop.DIAMONDS, wheelMultiplier * 10 + prefs.getInteger(Shop.DIAMONDS));
            prefs.flush();
            spinningWheelReward = 10;
            isCoins = false;
            sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2190), 7.0F, Interpolation.fastSlow));
        } else if (wheelChance >= 6 && wheelChance <= 15) {
            prefs.putInteger(Shop.DIAMONDS, wheelMultiplier * 5 + prefs.getInteger(Shop.DIAMONDS));
            prefs.flush();
            spinningWheelReward = 5;
            isCoins = false;
            sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2280), 7.0F, Interpolation.fastSlow));
        } else if (wheelChance >= 16 && wheelChance <= 35) {
            prefs.putInteger(Shop.COINS, wheelMultiplier * 200 + prefs.getInteger(Shop.COINS));
            prefs.flush();
            spinningWheelReward = 200;
            isCoins = true;
            if (chanceNumber == 1)
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2325), 7.0F, Interpolation.fastSlow));
            else
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2415), 7.0F, Interpolation.fastSlow));
        } else if (wheelChance >= 36 && wheelChance <= 65) {
            prefs.putInteger(Shop.COINS, wheelMultiplier * 100 + prefs.getInteger(Shop.COINS));
            prefs.flush();
            spinningWheelReward = 100;
            isCoins = true;
            if (chanceNumber == 1)
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2370), 7.0F, Interpolation.fastSlow));
            else
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2460), 7.0F, Interpolation.fastSlow));
        } else if (wheelChance >= 66 && wheelChance <= 100) {
            prefs.putInteger(Shop.COINS, wheelMultiplier * 50 + prefs.getInteger(Shop.COINS));
            prefs.flush();
            spinningWheelReward = 50;
            isCoins = true;
            if (chanceNumber == 1)
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2145), 7.0F, Interpolation.fastSlow));
            else
                sequence.addAction(Actions.rotateBy((random.nextInt(36) + 2235), 7.0F, Interpolation.fastSlow));
        }
        spinningWheelFront.addAction(sequence);
        stage.getActors().get(3).setTouchable(Touchable.disabled);
        stage.getActors().get(4).setTouchable(Touchable.disabled);
        stage.getActors().get(7).setTouchable(Touchable.disabled);
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }
}