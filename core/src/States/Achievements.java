//package States;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Preferences;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//public class Achievements extends State {
//  private static final String ACHIEVEMENTS_PASSED = "achievementsPassed";
//
//  private static final String ACHIEVEMENTS_PASSED_ACHIEVEMENT_PASSED = "lifetimeMetresAchievementPassed";
//
//  private static final String ACHIEVEMENTS_PASSED_ATM_ACHIEVEMENT = "lifetimeMetresAtmAchievement";
//
//  private static final String ADS_WATCHED_ACHIEVEMENT_PASSED = "adsWatchedAchievementPassed";
//
//  public static final String ADS_WATCHED_ATM_ACHIEVEMENT = "adsWatchedAtmAchievement";
//
//  private static final String CITY_ACHIEVEMENT_PASSED = "cityAchievementPassed";
//
//  static final String CITY_ATM_ACHIEVEMENT = "cityAtmAchievement";
//
//  private static final String COINS_ONE_GAME_ACHIEVEMENT_PASSED = "coinsOneGameAchievementPassed";
//
//  static final String COINS_ONE_GAME_ATM_ACHIEVEMENT = "coinsOneGameAtmAchievement";
//
//  private static final String DESERT_ACHIEVEMENT_PASSED = "desertAchievementPassed";
//
//  static final String DESERT_ATM_ACHIEVEMENT = "desertAtmAchievement";
//
//  private static final String HEAL_STONES_ACHIEVEMENT_PASSED = "healStonesAchievementPassed";
//
//  public static final String HEAL_STONES_ATM_ACHIEVEMENT = "healStonesAtmAchievement";
//
//  private static final String LIFETIME_COINS_COLLECTED_ACHIEVEMENT_PASSED = "lifetimeCoinsCollectedAchievementPassed";
//
//  static final String LIFETIME_COINS_COLLECTED_ATM_ACHIEVEMENT = "lifetimeCoinsCollectedAtmAchievement";
//
//  private static final String LIFETIME_METRES_ACHIEVEMENT_PASSED = "lifetimeMetresAchievementPassed";
//
//  static final String LIFETIME_METRES_ATM_ACHIEVEMENT = "lifetimeMetresAtmAchievement";
//
//  private static final String METRES_WITHOUT_COINS_ACHIEVEMENT_PASSED = "metresWithoutCoinsAchievementPassed";
//
//  public static final String METRES_WITHOUT_COINS_ATM_ACHIEVEMENT = "metresWithoutCoinsAtmAchievement";
//
//  private static final String POWER_UPS_COLLECTED_ACHIEVEMENT_PASSED = "powerUpsCollectedAchievementPassed";
//
//  static final String POWER_UPS_COLLECTED_ATM_ACHIEVEMENT = "powerUpsCollectedAtmAchievement";
//
//  private static final String POWER_UPS_UPGRADED_ACHIEVEMENT_PASSED = "powerUpsUpgradedAchievementPassed";
//
//  static final String POWER_UPS_UPGRADED_ATM_ACHIEVEMENT = "powerUpsUpgradedAtmAchievement";
//
//  private static final String REVIVED_AFTER_DEATH_ACHIEVEMENT_PASSED = "revivedAfterDeathAchievementPassed";
//
//  static final String REVIVED_AFTER_DEATH_ATM_ACHIEVEMENT = "revivedAfterDeathAtmAchievement";
//
//  private static final String STAGES_PLAYED_ACHIEVEMENT_PASSED = "stagesPlayedAchievementPassed";
//
//  static final String STAGES_PLAYED_ATM_ACHIEVEMENT = "stagesPlayedAtmAchievement";
//
//  private static final String STAGE_COMPLETED_ACHIEVEMENT_PASSED = "stageCompletedAchievementPassed";
//
//  static final String STAGE_COMPLETED_ATM_ACHIEVEMENT = "stageCompletedAtmAchievement";
//
//  private static final String WHEELS_SPUN_ACHIEVEMENT_PASSED = "wheelsSpunAchievementPassed";
//
//  static final String WHEELS_SPUN_ATM_ACHIEVEMENT = "wheelsSpunAtmAchievement";
//
//  private TextureAtlas.AtlasRegion achievementCompleted;
//
//  private TextureAtlas achievementsAtlas;
//
//  private BitmapFont achievementsFont;
//
//  private Image achievementsPage;
//
//  private int achievementsPageNumber = 1;
//
//  private int[] achievementsPassed = new int[] { 10, 20, 50, 90 };
//
//  private int[] achievementsPassedRewards = new int[] { 50, 100, 150, 200 };
//
//  private int[] adsWatched = new int[] { 5, 10, 30, 50, 100 };
//
//  private int[] adsWatchedRewards = new int[] { 50, 100, 150, 300, 500 };
//
//  private BitmapFont buyButtonTextFont;
//
//  private int[] cityMetresPassed = new int[] { 50, 100, 200, 500, 1000, 2000, 5000 };
//
//  private int[] cityMetresPassedRewards = new int[] { 100, 200, 300, 50, 500, 1000, 5000 };
//
//  private TextureAtlas.AtlasRegion coin;
//
//  private BitmapFont coinAndDiamondFont;
//
//  private ImageButton.ImageButtonStyle coinsAchievementButtonStyle;
//
//  private int[] coinsCollectedOneGame = new int[] { 15, 40, 75, 100, 200, 500 };
//
//  private int[] coinsCollectedOneGameRewards = new int[] { 100, 150, 200, 50, 500, 1000 };
//
//  private int coinsGlobal;
//
//  private int[] desertMetresPassed = new int[] { 50, 100, 200, 500, 1000, 2000, 5000 };
//
//  private int[] desertMetresPassedRewards = new int[] { 100, 200, 300, 50, 500, 1000, 5000 };
//
//  private ImageButton.ImageButtonStyle diamondsAchievementButtonStyle;
//
//  private int diamondsGlobal;
//
//  private ImageButton fifthAchievementButton;
//
//  private ImageButton firstAchievementButton;
//
//  private ImageButton fourthAchievementButton;
//
//  private int[] healStones = new int[] { 1, 5, 10, 30, 50, 100, 300 };
//
//  private int[] healStonesRewards = new int[] { 50, 100, 150, 25, 200, 250, 500 };
//
//  private int[] lifeTimeMetres = new int[] { 1000, 2000, 5000, 10000, 20000, 50000 };
//
//  private int[] lifeTimeMetresRewards = new int[] { 500, 1000, 2000, 3000, 5000, 10000 };
//
//  private int[] lifetimeCoinsCollected = new int[] { 1000, 2000, 3000, 5000, 10000, 15000, 30000, 50000 };
//
//  private int[] lifetimeCoinsCollectedRewards = new int[] { 25, 25, 50, 50, 75, 75, 100, 100 };
//
//  private int[] metresWithoutCoins = new int[] { 20, 50, 80, 100, 150, 200 };
//
//  private int[] metresWithoutCoinsRewards = new int[] { 100, 100, 100, 200, 300, 300 };
//
//  private MusicSounds musicSoundsObject;
//
//  private int[] powerUpsCollected = new int[] { 5, 10, 20, 50, 100, 200, 500 };
//
//  private int[] powerUpsCollectedRewards = new int[] { 50, 100, 200, 50, 300, 500, 1000 };
//
//  private int[] powerUpsUpgraded = new int[] {
//      1, 3, 7, 10, 15, 20, 25, 30, 35, 40,
//      45, 50 };
//
//  private int[] powerUpsUpgradedRewards = new int[] {
//      100, 300, 500, 50, 1000, 1500, 2000, 50, 2000, 2500,
//      2500, 3000 };
//
//  private Preferences prefs;
//
//  private int[] reviveAfterDeath = new int[] { 1, 5, 10, 20, 50, 100 };
//
//  private int[] reviveAfterDeathRewards = new int[] { 50, 100, 150, 25, 200, 500 };
//
//  private TextureAtlas.AtlasRegion ruby;
//
//  private ImageButton secondAchievementButton;
//
//  private Stage stage;
//
//  private int[] stageCompleted = new int[] { 1, 2 };
//
//  private int[] stageCompletedRewards = new int[] { 100, 100 };
//
//  private int[] stagesPlayed = new int[] { 5, 15, 50, 100, 200, 300, 500, 1000 };
//
//  private int[] stagesPlayedRewards = new int[] { 100, 150, 200, 50, 300, 500, 1000, 2000 };
//
//  private ImageButton thirdAchievementButton;
//
//  private int[] wheelsSpun = new int[] { 1, 5, 10, 25, 50, 100, 150 };
//
//  private int[] wheelsSpunRewards = new int[] { 100, 150, 300, 50, 500, 1000, 2000 };
//
//  public Achievements() {
//    this.prefs = Gdx.app.getPreferences("prefs");
//  }
//
//  Achievements(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
//    super(gsm);
//    this.stage = new Stage((Viewport)new ScreenViewport());
//    Gdx.input.setInputProcessor((InputProcessor)this.stage);
//    this.musicSoundsObject = new MusicSounds(manager);
//    this.achievementsAtlas = (TextureAtlas)manager.get("achievements/achievements.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas1 = (TextureAtlas)manager.get("shared/shared.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas2 = (TextureAtlas)manager.get("choose_stage/choose_stage.atlas", TextureAtlas.class);
//    Preferences preferences = Gdx.app.getPreferences("prefs");
//    this.prefs = preferences;
//    checkAchievementsPassed(preferences.getInteger("achievementsPassed", 0));
//    this.coin = textureAtlas1.findRegion("coin");
//    this.ruby = textureAtlas1.findRegion("diamond");
//    this.achievementCompleted = this.achievementsAtlas.findRegion("achievement_completed");
//    this.coinsGlobal = this.prefs.getInteger("coins");
//    this.diamondsGlobal = this.prefs.getInteger("ruby");
//    Image image1 = new Image((TextureRegion)textureAtlas1.findRegion("menu_background"));
//    image1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    image1.setPosition(0.0F, 0.0F);
//    BitmapFont bitmapFont = (BitmapFont)manager.get("font/font_scale_07.fnt", BitmapFont.class);
//    this.coinAndDiamondFont = bitmapFont;
//    bitmapFont.setColor(Color.WHITE);
//    this.coinAndDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));
//    bitmapFont = (BitmapFont)manager.get("font/achievements_font.fnt", BitmapFont.class);
//    this.achievementsFont = bitmapFont;
//    bitmapFont.setColor(Color.WHITE);
//    this.achievementsFont.getData().setScale(worldXToScreenX(0.4F), worldYToScreenY(0.4F));
//    bitmapFont = (BitmapFont)manager.get("font/achievements_button_font.fnt", BitmapFont.class);
//    this.buyButtonTextFont = bitmapFont;
//    bitmapFont.setColor(Color.BLACK);
//    this.buyButtonTextFont.getData().setScale(worldXToScreenX(0.3F), worldYToScreenY(0.3F));
//    Image image2 = new Image((TextureRegion)this.achievementsAtlas.findRegion("achievements_first_page"));
//    this.achievementsPage = image2;
//    image2.setSize(worldXToScreenX(480.0F), worldYToScreenY(730.0F));
//    this.achievementsPage.setPosition(worldXToScreenX(10.0F), worldYToScreenY(135.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle1.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("choose_stage_up_button_unpressed")));
//    imageButtonStyle1.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("choose_stage_up_button_pressed")));
//    ImageButton imageButton3 = new ImageButton(imageButtonStyle1);
//    imageButton3.setPosition(worldXToScreenX(220.0F), worldYToScreenY(895.0F));
//    imageButton3.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
//    imageButton3.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.access$010(Achievements.this);
//            Achievements.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("choose_stage_down_button_unpressed")));
//    imageButtonStyle3.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("choose_stage_down_button_pressed")));
//    ImageButton imageButton2 = new ImageButton(imageButtonStyle3);
//    imageButton2.setPosition(worldXToScreenX(220.0F), worldYToScreenY(45.0F));
//    imageButton2.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
//    imageButton2.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.access$008(Achievements.this);
//            Achievements.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    this.coinsAchievementButtonStyle = imageButtonStyle3;
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_coin_button_unpressed")));
//    this.coinsAchievementButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_coin_button_pressed")));
//    imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    this.diamondsAchievementButtonStyle = imageButtonStyle3;
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_diamonds_button_unpressed")));
//    this.diamondsAchievementButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_diamonds_button_pressed")));
//    ImageButton imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
//    this.firstAchievementButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(775.0F));
//    this.firstAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
//    this.firstAchievementButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonReceiveCoins();
//            if (Achievements.this.achievementsPageNumber == 1) {
//              if (Achievements.this.prefs.getInteger("cityAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.cityMetresPassedRewards[Achievements.this.prefs.getInteger("cityAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.cityMetresPassedRewards[Achievements.this.prefs.getInteger("cityAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("cityAtmAchievement", Achievements.this.prefs.getInteger("cityAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("cityAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 2) {
//              if (Achievements.this.prefs.getInteger("healStonesAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.healStonesRewards[Achievements.this.prefs.getInteger("healStonesAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.healStonesRewards[Achievements.this.prefs.getInteger("healStonesAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("healStonesAtmAchievement", Achievements.this.prefs.getInteger("healStonesAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("healStonesAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 3) {
//              if (Achievements.this.prefs.getInteger("stagesPlayedAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.stagesPlayedRewards[Achievements.this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.stagesPlayedRewards[Achievements.this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("stagesPlayedAtmAchievement", Achievements.this.prefs.getInteger("stagesPlayedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("stagesPlayedAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            }
//            Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
//            Achievements.this.prefs.flush();
//            Achievements achievements = Achievements.this;
//            achievements.checkAchievementsPassed(achievements.prefs.getInteger("achievementsPassed", 0));
//          }
//        });
//    imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
//    this.secondAchievementButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(620.0F));
//    this.secondAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
//    this.secondAchievementButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonReceiveCoins();
//            if (Achievements.this.achievementsPageNumber == 1) {
//              if (Achievements.this.prefs.getInteger("desertAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.desertMetresPassedRewards[Achievements.this.prefs.getInteger("desertAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.desertMetresPassedRewards[Achievements.this.prefs.getInteger("desertAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("desertAtmAchievement", Achievements.this.prefs.getInteger("desertAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("desertAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 2) {
//              Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.lifetimeCoinsCollectedRewards[Achievements.this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("lifetimeCoinsCollectedAtmAchievement", Achievements.this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("lifetimeCoinsCollectedAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 3) {
//              if (Achievements.this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.powerUpsCollectedRewards[Achievements.this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.powerUpsCollectedRewards[Achievements.this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("powerUpsCollectedAtmAchievement", Achievements.this.prefs.getInteger("powerUpsCollectedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("powerUpsCollectedAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            }
//            Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
//            Achievements.this.prefs.flush();
//            Achievements achievements = Achievements.this;
//            achievements.checkAchievementsPassed(achievements.prefs.getInteger("achievementsPassed", 0));
//          }
//        });
//    imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
//    this.thirdAchievementButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(470.0F));
//    this.thirdAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
//    this.thirdAchievementButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonReceiveCoins();
//            if (Achievements.this.achievementsPageNumber == 1) {
//              if (Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.coinsCollectedOneGameRewards[Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.coinsCollectedOneGameRewards[Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("coinsOneGameAtmAchievement", Achievements.this.prefs.getInteger("coinsOneGameAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("coinsOneGameAchievementPassed", false);
//              Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 2) {
//              Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.stageCompletedRewards[Achievements.this.prefs.getInteger("stageCompletedAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("stageCompletedAtmAchievement", Achievements.this.prefs.getInteger("stageCompletedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("stageCompletedAchievementPassed", false);
//              Achievements.this.prefs.putInteger("achievementsPassed", Achievements.this.prefs.getInteger("achievementsPassed", 0) + 1);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 3) {
//              Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.adsWatchedRewards[Achievements.this.prefs.getInteger("adsWatchedAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("adsWatchedAtmAchievement", Achievements.this.prefs.getInteger("adsWatchedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("adsWatchedAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements1 = Achievements.this;
//              Achievements.access$402(achievements1, achievements1.prefs.getInteger("coins"));
//              achievements1 = Achievements.this;
//              Achievements.access$502(achievements1, achievements1.prefs.getInteger("ruby"));
//            }
//            Achievements achievements = Achievements.this;
//            achievements.checkAchievementsPassed(achievements.prefs.getInteger("achievementsPassed", 0));
//          }
//        });
//    imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
//    this.fourthAchievementButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(320.0F));
//    this.fourthAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
//    this.fourthAchievementButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonReceiveCoins();
//            if (Achievements.this.achievementsPageNumber == 1) {
//              if (Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.wheelsSpunRewards[Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.wheelsSpunRewards[Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("wheelsSpunAtmAchievement", Achievements.this.prefs.getInteger("wheelsSpunAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("wheelsSpunAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 2) {
//              if (Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) == 3) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.reviveAfterDeathRewards[Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.reviveAfterDeathRewards[Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("revivedAfterDeathAtmAchievement", Achievements.this.prefs.getInteger("revivedAfterDeathAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("revivedAfterDeathAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 3) {
//              Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.lifeTimeMetresRewards[Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("lifetimeMetresAtmAchievement", Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("lifetimeMetresAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            }
//          }
//        });
//    imageButton4 = new ImageButton(this.coinsAchievementButtonStyle);
//    this.fifthAchievementButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(360.0F), worldYToScreenY(170.0F));
//    this.fifthAchievementButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
//    this.fifthAchievementButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonReceiveCoins();
//            if (Achievements.this.achievementsPageNumber == 1) {
//              if (Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 3 || Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 7) {
//                Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.powerUpsUpgradedRewards[Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]);
//              } else {
//                Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.powerUpsUpgradedRewards[Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]);
//              }
//              Achievements.this.prefs.putInteger("powerUpsUpgradedAtmAchievement", Achievements.this.prefs.getInteger("powerUpsUpgradedAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("powerUpsUpgradedAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 2) {
//              Achievements.this.prefs.putInteger("coins", Achievements.this.prefs.getInteger("coins") + Achievements.this.metresWithoutCoinsRewards[Achievements.this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("metresWithoutCoinsAtmAchievement", Achievements.this.prefs.getInteger("metresWithoutCoinsAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("metresWithoutCoinsAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            } else if (Achievements.this.achievementsPageNumber == 3) {
//              Achievements.this.prefs.putInteger("ruby", Achievements.this.prefs.getInteger("ruby") + Achievements.this.achievementsPassedRewards[Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]);
//              Achievements.this.prefs.putInteger("lifetimeMetresAtmAchievement", Achievements.this.prefs.getInteger("lifetimeMetresAtmAchievement") + 1);
//              Achievements.this.prefs.putBoolean("lifetimeMetresAchievementPassed", false);
//              Achievements.this.prefs.flush();
//              Achievements achievements = Achievements.this;
//              Achievements.access$402(achievements, achievements.prefs.getInteger("coins"));
//              achievements = Achievements.this;
//              Achievements.access$502(achievements, achievements.prefs.getInteger("ruby"));
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("back_button_unpressed")));
//    imageButtonStyle2.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("back_button_pressed")));
//    ImageButton imageButton1 = new ImageButton(imageButtonStyle2);
//    imageButton1.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
//    imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton1.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Achievements.this.musicSoundsObject.playButtonClick();
//            GameStateManager gameStateManager = gsm;
//            gameStateManager.set(new MainMenu(gameStateManager, adsController, manager));
//            Achievements.this.dispose();
//          }
//        });
//    this.stage.addActor((Actor)image1);
//    this.stage.addActor((Actor)this.achievementsPage);
//    this.stage.addActor((Actor)imageButton3);
//    this.stage.addActor((Actor)imageButton2);
//    this.stage.addActor((Actor)imageButton1);
//    this.stage.addActor((Actor)this.firstAchievementButton);
//    this.stage.addActor((Actor)this.secondAchievementButton);
//    this.stage.addActor((Actor)this.thirdAchievementButton);
//    this.stage.addActor((Actor)this.fourthAchievementButton);
//    this.stage.addActor((Actor)this.fifthAchievementButton);
//    ((Actor)this.stage.getActors().get(2)).setVisible(false);
//    ((Actor)this.stage.getActors().get(5)).setVisible(false);
//    ((Actor)this.stage.getActors().get(6)).setVisible(false);
//    ((Actor)this.stage.getActors().get(7)).setVisible(false);
//    ((Actor)this.stage.getActors().get(8)).setVisible(false);
//    ((Actor)this.stage.getActors().get(9)).setVisible(false);
//  }
//
//  private void checkAchievementsPassed(int paramInt) {
//    if (paramInt >= this.achievementsPassed[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]) {
//      this.prefs.putBoolean("lifetimeMetresAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  private float worldXToScreenX(float paramFloat) {
//    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
//  }
//
//  private float worldYToScreenY(float paramFloat) {
//    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
//  }
//
//  public void checkAdsWatched(int paramInt) {
//    if (paramInt >= this.adsWatchedRewards[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("adsWatchedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkCityAchievement(float paramFloat) {
//    if (paramFloat * 2.0F >= this.cityMetresPassed[this.prefs.getInteger("cityAtmAchievement", 0)]) {
//      this.prefs.putBoolean("cityAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkCoinsOneGameAtmAchievement(int paramInt) {
//    if (paramInt >= this.coinsCollectedOneGame[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]) {
//      this.prefs.putBoolean("coinsOneGameAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkDesertAchievement(float paramFloat) {
//    if (paramFloat * 2.0F >= this.desertMetresPassed[this.prefs.getInteger("desertAtmAchievement", 0)]) {
//      this.prefs.putBoolean("desertAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  public void checkHealStones(int paramInt) {
//    if (paramInt >= this.healStones[this.prefs.getInteger("healStonesAtmAchievement", 0)]) {
//      this.prefs.putBoolean("healStonesAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkLifetimeCoinsCollected(int paramInt) {
//    if (paramInt >= this.lifetimeCoinsCollected[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("lifetimeCoinsCollectedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkLifetimeMetres(float paramFloat) {
//    if (paramFloat * 2.0F >= this.lifeTimeMetresRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]) {
//      this.prefs.putBoolean("lifetimeMetresAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  public void checkMetresWithoutCoins(float paramFloat, int paramInt) {
//    if (paramInt == 0 && paramFloat * 2.0F >= this.metresWithoutCoins[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]) {
//      this.prefs.putBoolean("metresWithoutCoinsAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkPowerUpsCollected(int paramInt) {
//    if (paramInt >= this.powerUpsCollected[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("powerUpsCollectedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkPowerUpsUpgraded(int paramInt) {
//    if (paramInt >= this.powerUpsUpgraded[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("powerUpsUpgradedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkReviveAfterDeath(int paramInt) {
//    if (paramInt >= this.reviveAfterDeath[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]) {
//      this.prefs.putBoolean("revivedAfterDeathAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkStageCompleted(int paramInt) {
//    if (paramInt >= this.stageCompleted[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("stageCompletedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkStagesPlayed(int paramInt) {
//    if (paramInt >= this.stagesPlayed[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]) {
//      this.prefs.putBoolean("stagesPlayedAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  void checkWheelsSpun(int paramInt) {
//    if (paramInt >= this.wheelsSpun[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]) {
//      this.prefs.putBoolean("wheelsSpunAchievementPassed", true);
//      this.prefs.flush();
//    }
//  }
//
//  public void dispose() {
//    this.stage.dispose();
//  }
//
//  public void handleInput() {}
//
//  public void render(SpriteBatch paramSpriteBatch) {
//    this.stage.act(Gdx.graphics.getDeltaTime());
//    this.stage.draw();
//    int i = this.achievementsPageNumber;
//    if (i == 1) {
//      ((Actor)this.stage.getActors().get(2)).setVisible(false);
//      this.achievementsPage.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_first_page"))));
//    } else if (i == 2) {
//      ((Actor)this.stage.getActors().get(2)).setVisible(true);
//      ((Actor)this.stage.getActors().get(3)).setVisible(true);
//      this.achievementsPage.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_second_page"))));
//    } else if (i == 3) {
//      ((Actor)this.stage.getActors().get(3)).setVisible(false);
//      this.achievementsPage.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)this.achievementsAtlas.findRegion("achievements_third_page"))));
//    }
//    paramSpriteBatch.begin();
//    paramSpriteBatch.draw((TextureRegion)this.coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
//    this.coinAndDiamondFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinsGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
//    paramSpriteBatch.draw((TextureRegion)this.ruby, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
//    this.coinAndDiamondFont.draw((Batch)paramSpriteBatch, String.valueOf(this.diamondsGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getBoolean("cityAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(5)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(5)).setVisible(false);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getBoolean("healStonesAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(5)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(5)).setVisible(false);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getBoolean("stagesPlayedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(5)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(5)).setVisible(false);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("cityAtmAchievement", 0) == 3) {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getInteger("healStonesAtmAchievement", 0) == 3) {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getInteger("stagesPlayedAtmAchievement", 0) == 3) {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.firstAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getBoolean("desertAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(6)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(6)).setVisible(false);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getBoolean("lifetimeCoinsCollectedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(6)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(6)).setVisible(false);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getBoolean("powerUpsCollectedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(6)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(6)).setVisible(false);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("desertAtmAchievement", 0) == 3) {
//        this.secondAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.secondAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 2) {
//      this.secondAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//    } else if (i == 3) {
//      if (this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) == 3) {
//        this.secondAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.secondAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getBoolean("coinsOneGameAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(7)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(7)).setVisible(false);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getBoolean("stageCompletedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(7)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(7)).setVisible(false);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getBoolean("adsWatchedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(7)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(7)).setVisible(false);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("coinsOneGameAtmAchievement", 0) == 3) {
//        this.thirdAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.thirdAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 2) {
//      this.thirdAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//    } else if (i == 3) {
//      this.thirdAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getBoolean("wheelsSpunAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(8)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(8)).setVisible(false);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getBoolean("revivedAfterDeathAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(8)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(8)).setVisible(false);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(8)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(8)).setVisible(false);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("wheelsSpunAtmAchievement", 0) == 3) {
//        this.fourthAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.fourthAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) == 3) {
//        this.fourthAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.fourthAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 3) {
//      this.fourthAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getBoolean("powerUpsUpgradedAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(9)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(9)).setVisible(false);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getBoolean("metresWithoutCoinsAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(9)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(9)).setVisible(false);
//      }
//    } else if (i == 3) {
//      if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false)) {
//        ((Actor)this.stage.getActors().get(9)).setVisible(true);
//      } else {
//        ((Actor)this.stage.getActors().get(9)).setVisible(false);
//      }
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 3 || this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) == 7) {
//        this.fifthAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//      } else {
//        this.fifthAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//      }
//    } else if (i == 2) {
//      this.fifthAchievementButton.setStyle((Button.ButtonStyle)this.coinsAchievementButtonStyle);
//    } else if (i == 3) {
//      this.fifthAchievementButton.setStyle((Button.ButtonStyle)this.diamondsAchievementButtonStyle);
//    }
//    i = this.achievementsPageNumber;
//    if (i == 1) {
//      if (this.prefs.getInteger("cityAtmAchievement", 0) < 7) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.cityMetresPassed[this.prefs.getInteger("cityAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("cityAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.cityMetresPassedRewards[this.prefs.getInteger("cityAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("desertAtmAchievement", 0) < 7) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.desertMetresPassed[this.prefs.getInteger("desertAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("desertAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.desertMetresPassedRewards[this.prefs.getInteger("desertAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("coinsOneGameAtmAchievement", 0) < 6) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinsCollectedOneGame[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("coinsOneGameAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinsCollectedOneGameRewards[this.prefs.getInteger("coinsOneGameAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("wheelsSpunAtmAchievement", 0) < 7) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.wheelsSpun[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("wheelsSpunAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.wheelsSpunRewards[this.prefs.getInteger("wheelsSpunAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.powerUpsUpgraded[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("powerUpsUpgradedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.powerUpsUpgradedRewards[this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//    } else if (i == 2) {
//      if (this.prefs.getInteger("healStonesAtmAchievement", 0) < 7) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.healStones[this.prefs.getInteger("healStonesAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("healStonesAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.healStonesRewards[this.prefs.getInteger("healStonesAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0) < 8) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.lifetimeCoinsCollected[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("lifetimeCoinsCollectedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.lifetimeCoinsCollectedRewards[this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("stageCompletedAtmAchievement", 0) < 2) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.stageCompleted[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("stageCompletedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.stageCompletedRewards[this.prefs.getInteger("stageCompletedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) < 6) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.reviveAfterDeath[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("revivedAfterDeathAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.reviveAfterDeathRewards[this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0) < 6) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.metresWithoutCoins[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("metresWithoutCoinsAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.metresWithoutCoinsRewards[this.prefs.getInteger("metresWithoutCoinsAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//    } else if (i == 3) {
//      if (this.prefs.getInteger("stagesPlayedAtmAchievement", 0) < 8) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.stagesPlayed[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("stagesPlayedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.stagesPlayedRewards[this.prefs.getInteger("stagesPlayedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(810.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(795.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) < 7) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.powerUpsCollected[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(660.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("powerUpsCollectedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.powerUpsCollectedRewards[this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(655.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(640.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("adsWatchedAtmAchievement", 0) < 5) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.adsWatched[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(510.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("adsWatchedAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.adsWatchedRewards[this.prefs.getInteger("adsWatchedAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(505.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(490.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("lifetimeMetresAtmAchievement", 0) < 6) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.lifeTimeMetres[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(360.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.lifeTimeMetresRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(355.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(340.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//      if (this.prefs.getInteger("lifetimeMetresAtmAchievement", 0) < 4) {
//        this.achievementsFont.draw((Batch)paramSpriteBatch, String.valueOf(this.achievementsPassed[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(295.0F), worldYToScreenY(210.0F), worldXToScreenX(30.0F), 1, false);
//        if (this.prefs.getBoolean("lifetimeMetresAchievementPassed", false))
//          this.buyButtonTextFont.draw((Batch)paramSpriteBatch, String.valueOf(this.achievementsPassedRewards[this.prefs.getInteger("lifetimeMetresAtmAchievement", 0)]), worldXToScreenX(395.0F), worldYToScreenY(205.0F), worldXToScreenX(30.0F), 8, false);
//      } else {
//        paramSpriteBatch.draw((TextureRegion)this.achievementCompleted, worldXToScreenX(295.0F), worldYToScreenY(190.0F), worldXToScreenX(50.0F), worldYToScreenY(40.0F));
//      }
//    }
//    paramSpriteBatch.end();
//  }
//
//  public void update(float paramFloat) {}
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\Achievements.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */