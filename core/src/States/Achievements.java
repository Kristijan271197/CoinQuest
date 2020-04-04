package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Achievements extends State {
    private static final String ACHIEVEMENTS_PASSED = "achievementsPassed";
    private static final String ACHIEVEMENTS_PASSED_ACHIEVEMENT_PASSED = "lifetimeMetresAchievementPassed";
    private static final String ACHIEVEMENTS_PASSED_ATM_ACHIEVEMENT = "lifetimeMetresAtmAchievement";
    private static final String ADS_WATCHED_ACHIEVEMENT_PASSED = "adsWatchedAchievementPassed";
    public static final String ADS_WATCHED_ATM_ACHIEVEMENT = "adsWatchedAtmAchievement";
    private static final String CITY_ACHIEVEMENT_PASSED = "cityAchievementPassed";
    static final String CITY_ATM_ACHIEVEMENT = "cityAtmAchievement";
    private static final String COINS_ONE_GAME_ACHIEVEMENT_PASSED = "coinsOneGameAchievementPassed";
    static final String COINS_ONE_GAME_ATM_ACHIEVEMENT = "coinsOneGameAtmAchievement";
    private static final String DESERT_ACHIEVEMENT_PASSED = "desertAchievementPassed";
    static final String DESERT_ATM_ACHIEVEMENT = "desertAtmAchievement";
    private static final String HEAL_STONES_ACHIEVEMENT_PASSED = "healStonesAchievementPassed";
    public static final String HEAL_STONES_ATM_ACHIEVEMENT = "healStonesAtmAchievement";
    private static final String LIFETIME_COINS_COLLECTED_ACHIEVEMENT_PASSED = "lifetimeCoinsCollectedAchievementPassed";
    static final String LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT = "lifetimeCoinsCollectedAtmAchievement";
    private static final String LIFETIME_METRES_ACHIEVEMENT_PASSED = "lifetimeMetresAchievementPassed";
    static final String LIFETIME_METRES_ATM_ACHIEVEMENT = "lifetimeMetresAtmAchievement";
    private static final String METRES_WITHOUT_COINS_ACHIEVEMENT_PASSED = "metresWithoutCoinsAchievementPassed";
    public static final String METRES_WITHOUT_COINS_ATM_ACHIEVEMENT = "metresWithoutCoinsAtmAchievement";
    private static final String POWER_UPS_COLLECTED_ACHIEVEMENT_PASSED = "powerUpsCollectedAchievementPassed";
    static final String POWER_UPS_COLLECTED_ATM_ACHIEVEMENT = "powerUpsCollectedAtmAchievement";
    private static final String POWER_UPS_UPGRADED_ACHIEVEMENT_PASSED = "powerUpsUpgradedAchievementPassed";
    static final String POWER_UPS_UPGRADED_ATM_ACHIEVEMENT = "powerUpsUpgradedAtmAchievement";
    private static final String REVIVED_AFTER_DEATH_ACHIEVEMENT_PASSED = "revivedAfterDeathAchievementPassed";
    static final String REVIVED_AFTER_DEATH_ATM_ACHIEVEMENT = "revivedAfterDeathAtmAchievement";
    private static final String STAGES_PLAYED_ACHIEVEMENT_PASSED = "stagesPlayedAchievementPassed";
    static final String STAGES_PLAYED_ATM_ACHIEVEMENT = "stagesPlayedAtmAchievement";
    private static final String STAGE_COMPLETED_ACHIEVEMENT_PASSED = "stageCompletedAchievementPassed";
    static final String STAGE_COMPLETED_ATM_ACHIEVEMENT = "stageCompletedAtmAchievement";
    private static final String WHEELS_SPUN_ACHIEVEMENT_PASSED = "wheelsSpunAchievementPassed";
    static final String WHEELS_SPUN_ATM_ACHIEVEMENT = "wheelsSpunAtmAchievement";

    private TextureAtlas.AtlasRegion achievementCompleted;
    private TextureAtlas achievementsAtlas;
    private BitmapFont achievementsFont;
    private Image achievementsPage;
    private int achievementsPageNumber = 1;
    private int[] achievementsPassed = new int[]{10, 20, 50, 90};
    private int[] achievementsPassedRewards = new int[]{50, 100, 150, 200};
    private int[] adsWatched = new int[]{5, 10, 30, 50, 100};
    private int[] adsWatchedRewards = new int[]{50, 100, 150, 300, 500};
    private BitmapFont buyButtonTextFont;
    private int[] cityMetresPassed = new int[]{50, 100, 200, 500, 1000, 2000, 5000};
    private int[] cityMetresPassedRewards = new int[]{100, 200, 300, 50, 500, 1000, 5000};
    private TextureAtlas.AtlasRegion coin;
    private BitmapFont coinAndDiamondFont;
    private ImageButton.ImageButtonStyle coinsAchievementButtonStyle;
    private int[] coinsCollectedOneGame = new int[]{15, 40, 75, 100, 200, 500};
    private int[] coinsCollectedOneGameRewards = new int[]{100, 150, 200, 50, 500, 1000};
    private int coinsGlobal;
    private int[] desertMetresPassed = new int[]{50, 100, 200, 500, 1000, 2000, 5000};
    private int[] desertMetresPassedRewards = new int[]{100, 200, 300, 50, 500, 1000, 5000};
    private ImageButton.ImageButtonStyle diamondsAchievementButtonStyle;
    private int diamondsGlobal;
    private ImageButton fifthAchievementButton;
    private ImageButton firstAchievementButton;
    private ImageButton fourthAchievementButton;
    private int[] healStones = {1, 5, 10, 30, 50, 100, 300};
    private int[] healStonesRewards = {50, 100, 150, 25, 200, 250, 500};
    private int[] lifeTimeMetres = {1000, 2000, 5000, 10000, 20000, 50000};
    private int[] lifeTimeMetresRewards = {500, 1000, 2000, 3000, 5000, 10000};
    private int[] lifetimeCoinsCollected = {1000, 2000, 3000, 5000, 10000, 15000, 30000, 50000};
    private int[] lifetimeCoinsCollectedRewards = {25, 25, 50, 50, 75, 75, 100, 100};
    private int[] metresWithoutCoins = {20, 50, 80, 100, 150, 200};
    private int[] metresWithoutCoinsRewards = {100, 100, 100, 200, 300, 300};
    private MusicSounds musicSoundsObject;
    private int[] powerUpsCollected = {5, 10, 20, 50, 100, 200, 500};
    private int[] powerUpsCollectedRewards = {50, 100, 200, 50, 300, 500, 1000};
    private int[] powerUpsUpgraded = {1, 3, 7, 10, 15, 20, 25, 30, 35, 40, 45, 50};
    private int[] powerUpsUpgradedRewards = {100, 300, 500, 50, 1000, 1500, 2000, 50, 2000, 2500, 2500, 3000};
    private Preferences prefs;
    private int[] reviveAfterDeath = {1, 5, 10, 20, 50, 100};
    private int[] reviveAfterDeathRewards = {50, 100, 150, 25, 200, 500};
    private TextureAtlas.AtlasRegion ruby;
    private ImageButton secondAchievementButton;
    private Stage stage;
    private int[] stageCompleted = {1, 2};

    private int[] stageCompletedRewards = {100, 100};

    private int[] stagesPlayed = {5, 15, 50, 100, 200, 300, 500, 1000};

    private int[] stagesPlayedRewards = {100, 150, 200, 50, 300, 500, 1000, 2000};

    private ImageButton thirdAchievementButton;

    private int[] wheelsSpun = {1, 5, 10, 25, 50, 100, 150};

    private int[] wheelsSpunRewards = {100, 150, 300, 50, 500, 1000, 2000};

    public Achievements() {
        prefs = Gdx.app.getPreferences("prefs");
    }

    Achievements(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        musicSoundsObject = new MusicSounds(manager);
        achievementsAtlas = manager.get("achievements/achievements.atlas", TextureAtlas.class);
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas chooseStageAtlas = manager.get("choose_stage/choose_stage.atlas", TextureAtlas.class);
        prefs = Gdx.app.getPreferences("prefs");

        checkAchievementsPassed(prefs.getInteger("achievementsPassed", 0));

        coin = sharedAtlas.findRegion("coin");
        ruby = sharedAtlas.findRegion("diamond");
        achievementCompleted = achievementsAtlas.findRegion("achievement_completed");
        coinsGlobal = prefs.getInteger(Shop.COINS);
        diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        coinAndDiamondFont = manager.get("font/font_scale_07.fnt", BitmapFont.class);
        coinAndDiamondFont.setColor(Color.WHITE);
        coinAndDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));

        achievementsFont = manager.get("font/achievements_font.fnt", BitmapFont.class);
        achievementsFont.setColor(Color.WHITE);
        achievementsFont.getData().setScale(worldXToScreenX(0.4F), worldYToScreenY(0.4F));

        buyButtonTextFont = manager.get("font/achievements_button_font.fnt", BitmapFont.class);
        buyButtonTextFont.setColor(Color.BLACK);
        buyButtonTextFont.getData().setScale(worldXToScreenX(0.3F), worldYToScreenY(0.3F));

        achievementsPage = new Image(achievementsAtlas.findRegion("achievements_first_page"));
        achievementsPage.setSize(worldXToScreenX(480.0F), worldYToScreenY(730.0F));
        achievementsPage.setPosition(worldXToScreenX(10.0F), worldYToScreenY(135.0F));

        ImageButton.ImageButtonStyle achievementsUpStyle = new ImageButton.ImageButtonStyle();
        achievementsUpStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_up_button_unpressed")));
        achievementsUpStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_up_button_pressed")));

        ImageButton achievementsUpButton = new ImageButton(achievementsUpStyle);
        achievementsUpButton.setPosition(worldXToScreenX(220.0F), worldYToScreenY(895.0F));
        achievementsUpButton.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
        achievementsUpButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                achievementsPageNumber--;
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle achievementsDownStyle = new ImageButton.ImageButtonStyle();
        achievementsDownStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_down_button_unpressed")));
        achievementsDownStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_down_button_pressed")));

        ImageButton achievementsDownButton = new ImageButton(achievementsDownStyle);
        achievementsDownButton.setPosition(worldXToScreenX(220.0F), worldYToScreenY(45.0F));
        achievementsDownButton.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
        achievementsDownButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                achievementsPageNumber++;
                musicSoundsObject.playButtonClick();
            }
        });

        coinsAchievementButtonStyle = new ImageButton.ImageButtonStyle();
        coinsAchievementButtonStyle.up = new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_coin_button_unpressed")));
        coinsAchievementButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_coin_button_pressed")));

        diamondsAchievementButtonStyle = new ImageButton.ImageButtonStyle();
        diamondsAchievementButtonStyle.up = new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_diamonds_button_unpressed")));
        diamondsAchievementButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_diamonds_button_pressed")));

        firstAchievementButton = new ImageButton(coinsAchievementButtonStyle);
        firstAchievementButton.setPosition(worldXToScreenX(360.0F), worldYToScreenY(775.0F));
        firstAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        firstAchievementButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonReceiveCoins();
                if (achievementsPageNumber == 1) {
                    if (prefs.getInteger(CITY_ATM_ACHIEVEMENT, 0) == 3)
                        prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + cityMetresPassedRewards[prefs.getInteger(CITY_ATM_ACHIEVEMENT, 0)]);
                    else
                        prefs.putInteger(Shop.COINS, prefs.getInteger(Shop.COINS) + cityMetresPassedRewards[prefs.getInteger(CITY_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(CITY_ATM_ACHIEVEMENT, prefs.getInteger(CITY_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(CITY_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (achievementsPageNumber == 2) {
                    if (prefs.getInteger(HEAL_STONES_ATM_ACHIEVEMENT, 0) == 3)
                        prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + healStonesRewards[prefs.getInteger(HEAL_STONES_ATM_ACHIEVEMENT, 0)]);
                    else
                        prefs.putInteger(Shop.COINS, prefs.getInteger(Shop.COINS) + healStonesRewards[prefs.getInteger(HEAL_STONES_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(HEAL_STONES_ATM_ACHIEVEMENT, prefs.getInteger(HEAL_STONES_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(HEAL_STONES_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (achievementsPageNumber == 3) {
                    if (prefs.getInteger(STAGES_PLAYED_ATM_ACHIEVEMENT, 0) == 3)
                        prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + stagesPlayedRewards[prefs.getInteger(STAGES_PLAYED_ATM_ACHIEVEMENT, 0)]);
                     else
                        prefs.putInteger(Shop.COINS, prefs.getInteger(Shop.COINS) + stagesPlayedRewards[prefs.getInteger(STAGES_PLAYED_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(STAGES_PLAYED_ATM_ACHIEVEMENT, prefs.getInteger(STAGES_PLAYED_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(STAGES_PLAYED_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                }
                prefs.putInteger(ACHIEVEMENTS_PASSED, prefs.getInteger(ACHIEVEMENTS_PASSED, 0) + 1);
                prefs.flush();
                checkAchievementsPassed(prefs.getInteger(ACHIEVEMENTS_PASSED, 0));
            }
        });

        secondAchievementButton = new ImageButton(this.coinsAchievementButtonStyle);
        secondAchievementButton.setPosition(worldXToScreenX(360.0F), worldYToScreenY(620.0F));
        secondAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        secondAchievementButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonReceiveCoins();
                if (achievementsPageNumber == 1) {
                    if (prefs.getInteger(DESERT_ATM_ACHIEVEMENT, 0) == 3)
                        prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + desertMetresPassedRewards[prefs.getInteger(DESERT_ATM_ACHIEVEMENT, 0)]);
                     else
                        prefs.putInteger(Shop.COINS, prefs.getInteger(Shop.COINS) + desertMetresPassedRewards[prefs.getInteger(DESERT_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(DESERT_ATM_ACHIEVEMENT, prefs.getInteger(DESERT_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(DESERT_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (achievementsPageNumber == 2) {
                    prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + lifetimeCoinsCollectedRewards[prefs.getInteger(LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT, prefs.getInteger(LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(LIFETIME_COINS_COLLECTED_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (achievementsPageNumber == 3) {
                    if (prefs.getInteger(POWER_UPS_COLLECTED_ATM_ACHIEVEMENT, 0) == 3)
                        prefs.putInteger(Shop.DIAMONDS, prefs.getInteger(Shop.DIAMONDS) + powerUpsCollectedRewards[prefs.getInteger(POWER_UPS_COLLECTED_ATM_ACHIEVEMENT, 0)]);
                     else
                        prefs.putInteger(Shop.COINS, prefs.getInteger(Shop.COINS) + powerUpsCollectedRewards[prefs.getInteger(POWER_UPS_COLLECTED_ATM_ACHIEVEMENT, 0)]);
                    prefs.putInteger(POWER_UPS_COLLECTED_ATM_ACHIEVEMENT, prefs.getInteger(POWER_UPS_COLLECTED_ATM_ACHIEVEMENT) + 1);
                    prefs.putBoolean(POWER_UPS_COLLECTED_ACHIEVEMENT_PASSED, false);
                    prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                }
                prefs.putInteger(ACHIEVEMENTS_PASSED, prefs.getInteger(ACHIEVEMENTS_PASSED, 0) + 1);
                prefs.flush();
                checkAchievementsPassed(prefs.getInteger(ACHIEVEMENTS_PASSED, 0));
            }
        });

        imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
        this.thirdAchievementButton = imageButton4;
        imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(470.0F));
        this.thirdAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        this.thirdAchievementButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Achievements.this.musicSoundsObject.playButtonReceiveCoins();
                if (Achievements.this.achievementsPageNumber == 1) {
                    if (Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0) == 3) {
                        Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.coinsCollectedOneGameRewards[Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]);
                    } else {
                        Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.coinsCollectedOneGameRewards[Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]);
                    }
                    Achievements.this.prefs.putInteger("coinsOneGameAtmAchievement", Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("coinsOneGameAchievementPassed", false);
                    Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 2) {
                    Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.stageCompletedRewards[Achievements.this.prefs.getInteger("stageCompletedAtmAchievement", 0)]);
                    Achievements.this.prefs.putInteger("stageCompletedAtmAchievement", Achievements.this.prefs.getInteger("stageCompletedAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("stageCompletedAchievementPassed", false);
                    Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
                    Achievements.this.prefs.flush();
                    Achievements achievements1 = Achievements.this;
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 3) {
                    Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.adsWatchedRewards[Achievements.this.prefs.getInteger("adsWatchedAtmAchievement", 0)]);
                    Achievements.this.prefs.putInteger("adsWatchedAtmAchievement", Achievements.this.prefs.getInteger("adsWatchedAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("adsWatchedAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    Achievements achievements1 = Achievements.this;
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                }
                Achievements achievements = Achievements.this;
                achievements.checkAchievementsPassed(achievements.prefs.getInteger("achievementsPassed", 0));
            }
        });
        imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
        this.fourthAchievementButton = imageButton4;
        imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(320.0F));
        this.fourthAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        this.fourthAchievementButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Achievements.this.musicSoundsObject.playButtonReceiveCoins();
                if (Achievements.this.achievementsPageNumber == 1) {
                    if (Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0) == 3) {
                        Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.wheelsSpunRewards[Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]);
                    } else {
                        Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.wheelsSpunRewards[Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]);
                    }
                    Achievements.this.prefs.putInteger("wheelsSpunAtmAchievement", Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("wheelsSpunAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 2) {
                    if (Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) == 3) {
                        Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.reviveAfterDeathRewards[Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]);
                    } else {
                        Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.reviveAfterDeathRewards[Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]);
                    }
                    Achievements.this.prefs.putInteger("revivedAfterDeathAtmAchievement", Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("revivedAfterDeathAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 3) {
                    Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.lifeTimeMetresRewards[Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]);
                    Achievements.this.prefs.putInteger("lifetimeMetresAtmAchievement", Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("lifetimeMetresAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                }
            }
        });
        imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
        this.fifthAchievementButton = imageButton4;
        imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(170.0F));
        this.fifthAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        this.fifthAchievementButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Achievements.this.musicSoundsObject.playButtonReceiveCoins();
                if (Achievements.this.achievementsPageNumber == 1) {
                    if (Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 3 || Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 7) {
                        Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.powerUpsUpgradedRewards[Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]);
                    } else {
                        Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.powerUpsUpgradedRewards[Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]);
                    }
                    Achievements.this.prefs.putInteger("powerUpsUpgradedAtmAchievement", Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("powerUpsUpgradedAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 2) {
                    Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.metresWithoutCoinsRewards[Achievements.this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]);
                    Achievements.this.prefs.putInteger("metresWithoutCoinsAtmAchievement", Achievements.this.prefs.getInteger("metresWithoutCoinsAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("metresWithoutCoinsAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                } else if (Achievements.this.achievementsPageNumber == 3) {
                    Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.achievementsPassedRewards[Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]);
                    Achievements.this.prefs.putInteger("lifetimeMetresAtmAchievement", Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement") + 1);
                    Achievements.this.prefs.putBoolean("lifetimeMetresAchievementPassed", false);
                    Achievements.this.prefs.flush();
                    coinsGlobal = prefs.getInteger(Shop.COINS);
                    diamondsGlobal = prefs.getInteger(Shop.DIAMONDS);
                }
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
        imageButtonStyle2.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        imageButtonStyle2.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));
        ImageButton imageButton1 = new ImageButton(imageButtonStyle2);
        imageButton1.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton1.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Achievements.this.musicSoundsObject.playButtonClick();
                GameStateManager gameStateManager = gsm;
                gameStateManager.set(new MainMenu(gameStateManager, adsController, manager));
                Achievements.this.dispose();
            }
        });
        this.stage.addActor(bg);
        this.stage.addActor(this.achievementsPage);
        this.stage.addActor(achievementsUpButton);
        this.stage.addActor(achievementsDownButton);
        this.stage.addActor(imageButton1);
        this.stage.addActor(this.firstAchievementButton);
        this.stage.addActor(this.secondAchievementButton);
        this.stage.addActor(this.thirdAchievementButton);
        this.stage.addActor(this.fourthAchievementButton);
        this.stage.addActor(this.fifthAchievementButton);
        this.stage.getActors().get(2).setVisible(false);
        this.stage.getActors().get(5).setVisible(false);
        this.stage.getActors().get(6).setVisible(false);
        this.stage.getActors().get(7).setVisible(false);
        this.stage.getActors().get(8).setVisible(false);
        this.stage.getActors().get(9).setVisible(false);
    }

    private void checkAchievementsPassed(int paramInt) {
        if (paramInt >= this.achievementsPassed[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]) {
            this.prefs.putBoolean("lifetimeMetresAchievementPassed", true);
            this.prefs.flush();
        }
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void checkAdsWatched(int paramInt) {
        if (paramInt >= this.adsWatchedRewards[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]) {
            this.prefs.putBoolean("adsWatchedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkCityAchievement(float paramFloat) {
        if (paramFloat * 2.0F >= this.cityMetresPassed[this.prefs.getInteger("cityAtmAchievement", 0)]) {
            this.prefs.putBoolean("cityAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkCoinsOneGameAtmAchievement(int paramInt) {
        if (paramInt >= this.coinsCollectedOneGame[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]) {
            this.prefs.putBoolean("coinsOneGameAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkDesertAchievement(float paramFloat) {
        if (paramFloat * 2.0F >= this.desertMetresPassed[this.prefs.getInteger("desertAtmAchievement", 0)]) {
            this.prefs.putBoolean("desertAchievementPassed", true);
            this.prefs.flush();
        }
    }

    public void checkHealStones(int paramInt) {
        if (paramInt >= this.healStones[this.prefs.getInteger("healStonesAtmAchievement", 0)]) {
            this.prefs.putBoolean("healStonesAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkLifetimeCoinsCollected(int paramInt) {
        if (paramInt >= this.lifetimeCoinsCollected[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]) {
            this.prefs.putBoolean("lifetimeCoinsCollectedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkLifetimeMetres(float paramFloat) {
        if (paramFloat * 2.0F >= this.lifeTimeMetresRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]) {
            this.prefs.putBoolean("lifetimeMetresAchievementPassed", true);
            this.prefs.flush();
        }
    }

    public void checkMetresWithoutCoins(float paramFloat, int paramInt) {
        if (paramInt == 0 && paramFloat * 2.0F >= this.metresWithoutCoins[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]) {
            this.prefs.putBoolean("metresWithoutCoinsAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkPowerUpsCollected(int paramInt) {
        if (paramInt >= this.powerUpsCollected[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]) {
            this.prefs.putBoolean("powerUpsCollectedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkPowerUpsUpgraded(int paramInt) {
        if (paramInt >= this.powerUpsUpgraded[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]) {
            this.prefs.putBoolean("powerUpsUpgradedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkReviveAfterDeath(int paramInt) {
        if (paramInt >= this.reviveAfterDeath[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]) {
            this.prefs.putBoolean("revivedAfterDeathAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkStageCompleted(int paramInt) {
        if (paramInt >= this.stageCompleted[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]) {
            this.prefs.putBoolean("stageCompletedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkStagesPlayed(int paramInt) {
        if (paramInt >= this.stagesPlayed[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]) {
            this.prefs.putBoolean("stagesPlayedAchievementPassed", true);
            this.prefs.flush();
        }
    }

    void checkWheelsSpun(int paramInt) {
        if (paramInt >= this.wheelsSpun[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]) {
            this.prefs.putBoolean("wheelsSpunAchievementPassed", true);
            this.prefs.flush();
        }
    }

    public void dispose() {
        this.stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch paramSpriteBatch) {
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
        int i = this.achievementsPageNumber;
        if (i == 1) {
            this.stage.getActors().get(2).setVisible(false);
            this.achievementsPage.setDrawable(new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_first_page"))));
        } else if (i == 2) {
            this.stage.getActors().get(2).setVisible(true);
            this.stage.getActors().get(3).setVisible(true);
            this.achievementsPage.setDrawable(new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_second_page"))));
        } else if (i == 3) {
            this.stage.getActors().get(3).setVisible(false);
            this.achievementsPage.setDrawable(new TextureRegionDrawable(new TextureRegion(this.achievementsAtlas.findRegion("achievements_third_page"))));
        }
        paramSpriteBatch.begin();
        paramSpriteBatch.draw(this.coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        this.coinAndDiamondFont.draw(paramSpriteBatch, String.valueOf(this.coinsGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
        paramSpriteBatch.draw(this.ruby, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        this.coinAndDiamondFont.draw(paramSpriteBatch, String.valueOf(this.diamondsGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getBoolean("cityAchievementPassed", false)) {
                this.stage.getActors().get(5).setVisible(true);
            } else {
                this.stage.getActors().get(5).setVisible(false);
            }
        } else if (i == 2) {
            if (this.prefs.getBoolean("healStonesAchievementPassed", false)) {
                this.stage.getActors().get(5).setVisible(true);
            } else {
                this.stage.getActors().get(5).setVisible(false);
            }
        } else if (i == 3) {
            if (this.prefs.getBoolean("stagesPlayedAchievementPassed", false)) {
                this.stage.getActors().get(5).setVisible(true);
            } else {
                this.stage.getActors().get(5).setVisible(false);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("cityAtmAchievement", 0) == 3) {
                this.firstAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.firstAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 2) {
            if (this.prefs.getInteger("healStonesAtmAchievement", 0) == 3) {
                this.firstAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.firstAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 3) {
            if (this.prefs.getInteger("stagesPlayedAtmAchievement", 0) == 3) {
                this.firstAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.firstAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getBoolean("desertAchievementPassed", false)) {
                this.stage.getActors().get(6).setVisible(true);
            } else {
                this.stage.getActors().get(6).setVisible(false);
            }
        } else if (i == 2) {
            if (this.prefs.getBoolean("lifetimeCoinsCollectedAchievementPassed", false)) {
                this.stage.getActors().get(6).setVisible(true);
            } else {
                this.stage.getActors().get(6).setVisible(false);
            }
        } else if (i == 3) {
            if (this.prefs.getBoolean("powerUpsCollectedAchievementPassed", false)) {
                this.stage.getActors().get(6).setVisible(true);
            } else {
                this.stage.getActors().get(6).setVisible(false);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("desertAtmAchievement", 0) == 3) {
                this.secondAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.secondAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 2) {
            this.secondAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
        } else if (i == 3) {
            if (this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) == 3) {
                this.secondAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.secondAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getBoolean("coinsOneGameAchievementPassed", false)) {
                this.stage.getActors().get(7).setVisible(true);
            } else {
                this.stage.getActors().get(7).setVisible(false);
            }
        } else if (i == 2) {
            if (this.prefs.getBoolean("stageCompletedAchievementPassed", false)) {
                this.stage.getActors().get(7).setVisible(true);
            } else {
                this.stage.getActors().get(7).setVisible(false);
            }
        } else if (i == 3) {
            if (this.prefs.getBoolean("adsWatchedAchievementPassed", false)) {
                this.stage.getActors().get(7).setVisible(true);
            } else {
                this.stage.getActors().get(7).setVisible(false);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("coinsOneGameAtmAchievement", 0) == 3) {
                this.thirdAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.thirdAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 2) {
            this.thirdAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
        } else if (i == 3) {
            this.thirdAchievementButton.setStyle(this.coinsAchievementButtonStyle);
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getBoolean("wheelsSpunAchievementPassed", false)) {
                this.stage.getActors().get(8).setVisible(true);
            } else {
                this.stage.getActors().get(8).setVisible(false);
            }
        } else if (i == 2) {
            if (this.prefs.getBoolean("revivedAfterDeathAchievementPassed", false)) {
                this.stage.getActors().get(8).setVisible(true);
            } else {
                this.stage.getActors().get(8).setVisible(false);
            }
        } else if (i == 3) {
            if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false)) {
                this.stage.getActors().get(8).setVisible(true);
            } else {
                this.stage.getActors().get(8).setVisible(false);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("wheelsSpunAtmAchievement", 0) == 3) {
                this.fourthAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.fourthAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 2) {
            if (this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) == 3) {
                this.fourthAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.fourthAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 3) {
            this.fourthAchievementButton.setStyle(this.coinsAchievementButtonStyle);
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getBoolean("powerUpsUpgradedAchievementPassed", false)) {
                this.stage.getActors().get(9).setVisible(true);
            } else {
                this.stage.getActors().get(9).setVisible(false);
            }
        } else if (i == 2) {
            if (this.prefs.getBoolean("metresWithoutCoinsAchievementPassed", false)) {
                this.stage.getActors().get(9).setVisible(true);
            } else {
                this.stage.getActors().get(9).setVisible(false);
            }
        } else if (i == 3) {
            if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false)) {
                this.stage.getActors().get(9).setVisible(true);
            } else {
                this.stage.getActors().get(9).setVisible(false);
            }
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 3 || this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 7) {
                this.fifthAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
            } else {
                this.fifthAchievementButton.setStyle(this.coinsAchievementButtonStyle);
            }
        } else if (i == 2) {
            this.fifthAchievementButton.setStyle(this.coinsAchievementButtonStyle);
        } else if (i == 3) {
            this.fifthAchievementButton.setStyle(this.diamondsAchievementButtonStyle);
        }
        i = this.achievementsPageNumber;
        if (i == 1) {
            if (this.prefs.getInteger("cityAtmAchievement", 0) < 7) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.cityMetresPassed[this.prefs.getInteger("cityAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("cityAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.cityMetresPassedRewards[this.prefs.getInteger("cityAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("desertAtmAchievement", 0) < 7) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.desertMetresPassed[this.prefs.getInteger("desertAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("desertAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.desertMetresPassedRewards[this.prefs.getInteger("desertAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("coinsOneGameAtmAchievement", 0) < 6) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.coinsCollectedOneGame[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("coinsOneGameAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.coinsCollectedOneGameRewards[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("wheelsSpunAtmAchievement", 0) < 7) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.wheelsSpun[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("wheelsSpunAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.wheelsSpunRewards[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.powerUpsUpgraded[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("powerUpsUpgradedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.powerUpsUpgradedRewards[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
        } else if (i == 2) {
            if (this.prefs.getInteger("healStonesAtmAchievement", 0) < 7) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.healStones[this.prefs.getInteger("healStonesAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("healStonesAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.healStonesRewards[this.prefs.getInteger("healStonesAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0) < 8) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.lifetimeCoinsCollected[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("lifetimeCoinsCollectedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.lifetimeCoinsCollectedRewards[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("stageCompletedAtmAchievement", 0) < 2) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.stageCompleted[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("stageCompletedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.stageCompletedRewards[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) < 6) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.reviveAfterDeath[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("revivedAfterDeathAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.reviveAfterDeathRewards[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0) < 6) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.metresWithoutCoins[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("metresWithoutCoinsAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.metresWithoutCoinsRewards[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
        } else if (i == 3) {
            if (this.prefs.getInteger("stagesPlayedAtmAchievement", 0) < 8) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.stagesPlayed[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("stagesPlayedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.stagesPlayedRewards[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) < 7) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.powerUpsCollected[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("powerUpsCollectedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.powerUpsCollectedRewards[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("adsWatchedAtmAchievement", 0) < 5) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.adsWatched[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("adsWatchedAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.adsWatchedRewards[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("lifetimeMetresAtmAchievement", 0) < 6) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.lifeTimeMetres[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.lifeTimeMetresRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
            if (this.prefs.getInteger("lifetimeMetresAtmAchievement", 0) < 4) {
                this.achievementsFont.draw(paramSpriteBatch, String.valueOf(this.achievementsPassed[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
                if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false))
                    this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.achievementsPassedRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
            } else {
                paramSpriteBatch.draw(this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
            }
        }
        paramSpriteBatch.end();
    }

    public void update(float paramFloat) {
    }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\Achievements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */