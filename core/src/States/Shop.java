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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class Shop extends State {
    public static final String COINS = "coins";
    public static final String DIAMONDS = "ruby";

    private static final String ABILITY_SHIELD_COST = "abilityShieldCost";
    private static final String ABILITY_MAGNET_COST = "abilityMagnetCost";
    private static final String ABILITY_COIN_RUSH_COST = "abilityCoinRushCost";
    private static final String ABILITY_SPAWN_RATE_COST = "abilitySpawnRateCost";
    private static final String ABILITY_SPAWN_RATE_COINS_COST = "abilitySpawnRateCoinsCost";
    public static final String SHIELD_UPGRADED = "shieldUpgraded";
    public static final String MAGNET_UPGRADED = "magnetUpgraded";
    public static final String COIN_RUSH_UPGRADED = "coinRushUpgraded";
    public static final String SPAWN_RATE_UPGRADED = "spawnRateUpgraded";
    public static final String SPAWN_RATE_COINS_UPGRADED = "spawnRateCoinsUpgraded";
    private static final String COSTUME_SELECTED = "costumeSelected";
    public static final String COSTUME_SELECTED_GAME = "costumeSelectedGame";

    public static final int PLAYER_NUMBER = 0;
    public static final int ROBOT_NUMBER = 1;
    public static final int KNIGHT_NUMBER = 2;
    public static final int COWBOY_NUMBER = 3;
    public static final int COWGIRL_NUMBER = 4;
    public static final int NINJA_MALE_NUMBER = 5;
    public static final int NINJA_FEMALE_NUMBER = 6;
    public static final int DINO_NUMBER = 7;
    private static final String PLAYER_BOUGHT = "playerBought";
    private static final String ROBOT_BOUGHT = "robotBought";
    private static final String KNIGHT_BOUGHT = "knightBought";
    private static final String COWBOY_BOUGHT = "cowboyBought";
    private static final String COWGIRL_BOUGHT = "cowgirlBought";
    private static final String NINJA_MALE_BOUGHT = "ninjaMaleBought";
    private static final String NINJA_FEMALE_BOUGHT = "ninjaFemaleBought";
    private static final String DINO_BOUGHT = "dinoBought";

    public static final String DOUBLE_COINS = "doubleCoins";
    private static final String POWER_UPS_UPGRADED_TIMES = "powerUpsUpgradedTimes";


    private int abilityCoinRushCost;
    private int abilityMagnetCost;
    private int abilityShieldCost;
    private int abilitySpawnRateCoinsCost;
    private int abilitySpawnRateCost;
    private ArrayList<AbilityPoint> abilityPointsCoinRush;
    private ArrayList<AbilityPoint> abilityPointsMagnet;
    private ArrayList<AbilityPoint> abilityPointsShield;
    private ArrayList<AbilityPoint> abilityPointsSpawnRate;
    private ArrayList<AbilityPoint> abilityPointsSpawnRateCoins;
    private BitmapFont buyButtonTextFont;
    private TextureAtlas.AtlasRegion coin;
    private BitmapFont coinAndDiamondFont;
    private int coinGlobal;
    private TextureAtlas.AtlasRegion coinRush;
    private int coinRushUpgraded;
    private ImageButton.ImageButtonStyle costumeBuyButtonStyle;
    private ImageButton costumeBuySelectButton;
    private Image costumeCost;
    private TextureAtlas.AtlasRegion[] costumeCosts = new TextureAtlas.AtlasRegion[8];
    private Image costumeImage;
    private TextureAtlas.AtlasRegion[] costumeImages = new TextureAtlas.AtlasRegion[8];
    private Image costumeName;
    private TextureAtlas.AtlasRegion[] costumeNames = new TextureAtlas.AtlasRegion[8];
    private ImageButton.ImageButtonStyle costumeSelectButtonStyle;
    private int currencySelected;
    private TextureAtlas.AtlasRegion diamond;
    private boolean drawAbilities = false;
    private boolean drawCurrency = false;
    private boolean drawNotEnoughMoneyWindow = false;
    private boolean drawQuitGame;
    private TextureAtlas.AtlasRegion magnet;
    private int magnetUpgraded;
    private MusicSounds musicSoundsObject;
    private Image notEnoughCoinsDiamondsWindow;
    private TextureAtlas.AtlasRegion notEnoughMoneyWindow;
    private TextureAtlas.AtlasRegion notQuitButtonTexture;
    private Preferences prefs;
    private TextureAtlas.AtlasRegion quitButtonTexture;
    private TextureAtlas.AtlasRegion quitWindowTexture;
    private int rubyGlobal;
    private TextureAtlas.AtlasRegion shield;
    private int shieldUpgraded;
    private TextureAtlas shopAtlas;
    private BitmapFont smallerTextFont;
    private TextureAtlas.AtlasRegion spawnRate;
    private TextureAtlas.AtlasRegion spawnRateCoins;
    private int spawnRateCoinsUpgraded;
    private int spawnRateUpgraded;
    private Stage stage;
    private BitmapFont textFont;
    private Achievements achievementsObject;
    private TextureAtlas.AtlasRegion xNotEnoughMoneyButton;

    Shop(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);
        prefs = Gdx.app.getPreferences("prefs");

        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        shopAtlas = manager.get("shop/shop.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);

        achievementsObject = new Achievements();

        quitWindowTexture = sharedAtlas.findRegion("sure_quit_window");
        notQuitButtonTexture = sharedAtlas.findRegion("congratulations_window_x_button_unpressed");
        quitButtonTexture = mainGameAtlas.findRegion("story_continue_button_unpressed");
        xNotEnoughMoneyButton = sharedAtlas.findRegion("congratulations_window_x_button_unpressed");
        notEnoughMoneyWindow = shopAtlas.findRegion("not_enough_coins_window");

        coinGlobal = prefs.getInteger(COINS);
        rubyGlobal = prefs.getInteger(DIAMONDS);
        shieldUpgraded = prefs.getInteger(SHIELD_UPGRADED, 0);
        abilityShieldCost = prefs.getInteger(ABILITY_SHIELD_COST, 500);
        magnetUpgraded = prefs.getInteger(MAGNET_UPGRADED, 0);
        abilityMagnetCost = prefs.getInteger(ABILITY_MAGNET_COST, 500);
        coinRushUpgraded = prefs.getInteger(COIN_RUSH_UPGRADED, 0);
        abilityCoinRushCost = prefs.getInteger(ABILITY_COIN_RUSH_COST, 500);
        spawnRateUpgraded = prefs.getInteger(SPAWN_RATE_UPGRADED, 0);
        abilitySpawnRateCost = prefs.getInteger(ABILITY_SPAWN_RATE_COST, 500);
        spawnRateCoinsUpgraded = prefs.getInteger(SPAWN_RATE_COINS_UPGRADED, 0);
        abilitySpawnRateCoinsCost = prefs.getInteger(ABILITY_SPAWN_RATE_COINS_COST, 500);


        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        costumeNames[PLAYER_NUMBER] = shopAtlas.findRegion("player_costume_name");
        costumeNames[ROBOT_NUMBER] = shopAtlas.findRegion("robot_costume_name");
        costumeNames[KNIGHT_NUMBER] = shopAtlas.findRegion("knight_costume_name");
        costumeNames[COWBOY_NUMBER] = shopAtlas.findRegion("cowboy_costume_name");
        costumeNames[COWGIRL_NUMBER] = shopAtlas.findRegion("cowgirl_costume_name");
        costumeNames[NINJA_MALE_NUMBER] = shopAtlas.findRegion("ninja_male_costume_name");
        costumeNames[NINJA_FEMALE_NUMBER] = shopAtlas.findRegion("ninja_female_costume_name");
        costumeNames[DINO_NUMBER] = shopAtlas.findRegion("dino_costume_name");
        costumeCosts[PLAYER_NUMBER] = mainGameAtlas.findRegion("transparent");
        costumeCosts[ROBOT_NUMBER] = shopAtlas.findRegion("robot_costume_cost");
        costumeCosts[KNIGHT_NUMBER] = shopAtlas.findRegion("knight_costume_cost");
        costumeCosts[COWBOY_NUMBER] = shopAtlas.findRegion("cowboy_costume_cost");
        costumeCosts[COWGIRL_NUMBER] = shopAtlas.findRegion("cowgirl_costume_cost");
        costumeCosts[NINJA_MALE_NUMBER] = shopAtlas.findRegion("ninja_male_costume_cost");
        costumeCosts[NINJA_FEMALE_NUMBER] = shopAtlas.findRegion("ninja_female_costume_cost");
        costumeCosts[DINO_NUMBER] = shopAtlas.findRegion("dino_costume_cost");
        costumeImages[PLAYER_NUMBER] = shopAtlas.findRegion("player_costume");
        costumeImages[ROBOT_NUMBER] = shopAtlas.findRegion("robot_costume");
        costumeImages[KNIGHT_NUMBER] = shopAtlas.findRegion("knight_costume");
        costumeImages[COWBOY_NUMBER] = shopAtlas.findRegion("cowboy_costume");
        costumeImages[COWGIRL_NUMBER] = shopAtlas.findRegion("cowgirl_costume");
        costumeImages[NINJA_MALE_NUMBER] = shopAtlas.findRegion("ninja_male_costume");
        costumeImages[NINJA_FEMALE_NUMBER] = shopAtlas.findRegion("ninja_female_costume");
        costumeImages[DINO_NUMBER] = shopAtlas.findRegion("dino_costume");
        prefs.putBoolean(PLAYER_BOUGHT, true);
        prefs.flush();

        coin = sharedAtlas.findRegion("coin");
        diamond = sharedAtlas.findRegion("diamond");


        coinAndDiamondFont = manager.get("font/font_scale_07.fnt", BitmapFont.class);
        coinAndDiamondFont.setColor(Color.WHITE);
        coinAndDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));

        textFont = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        textFont.setColor(Color.WHITE);
        textFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        smallerTextFont = manager.get("font/font_scale_065.fnt", BitmapFont.class);
        smallerTextFont.setColor(Color.WHITE);
        smallerTextFont.getData().setScale(worldXToScreenX(0.65F), worldYToScreenY(0.65F));

        buyButtonTextFont = manager.get("font/font_scale_09.fnt", BitmapFont.class);
        buyButtonTextFont.setColor(Color.BLACK);
        buyButtonTextFont.getData().setScale(worldXToScreenX(0.9F), worldYToScreenY(0.9F));

        Image powerUpsWindow = new Image(shopAtlas.findRegion("power_ups_window"));
        powerUpsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(700.0F));
        powerUpsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(100.0F));

        ImageButton.ImageButtonStyle powerUpsButtonStyle = new ImageButton.ImageButtonStyle();
        powerUpsButtonStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("power_ups_button")));
        powerUpsButtonStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("power_ups_button")));

        ImageButton powerUpsButton = new ImageButton(powerUpsButtonStyle);
        powerUpsButton.setPosition(worldXToScreenX(60.0F), worldYToScreenY(790.0F));
        powerUpsButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(120.0F));
        powerUpsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                drawAbilities = true;
                drawCurrency = false;
                Shop.this.drawAbilityElements();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });

        notEnoughCoinsDiamondsWindow = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("not_enough_diamonds_window"))));
        notEnoughCoinsDiamondsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        notEnoughCoinsDiamondsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButtonNotEnoughMoney = new ImageButton(xButtonStyle);
        xButtonNotEnoughMoney.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
        xButtonNotEnoughMoney.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButtonNotEnoughMoney.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                drawNotEnoughMoneyWindow = false;
                stage.getActors().get(19).setVisible(false);
                stage.getActors().get(20).setVisible(false);
                enableButtonsWindow();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle buyButtonStyle = new ImageButton.ImageButtonStyle();
        buyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("shop_buy_button_unpressed")));
        buyButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("shop_buy_button_pressed")));

        shield = mainGameAtlas.findRegion("shield_collectible");
        abilityPointsShield = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            abilityPointsShield.add(new AbilityPoint(shopAtlas));

        for (int i = 0; i < shieldUpgraded; i++)
            abilityPointsShield.get(i).setFilled();

        ImageButton buyShieldButton = new ImageButton(buyButtonStyle);
        buyShieldButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(665.0F));
        buyShieldButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        buyShieldButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (coinGlobal >= abilityShieldCost) {
                    musicSoundsObject.playButtonBuy();
                    prefs.putInteger(COINS, coinGlobal - abilityShieldCost);
                    prefs.putInteger(SHIELD_UPGRADED, shieldUpgraded + 1);
                    prefs.putInteger(ABILITY_SHIELD_COST, abilityShieldCost + 500);
                    prefs.putInteger(POWER_UPS_UPGRADED_TIMES, prefs.getInteger(POWER_UPS_UPGRADED_TIMES) + 1);
                    prefs.flush();
                    coinGlobal = prefs.getInteger(COINS);
                    shieldUpgraded = prefs.getInteger(SHIELD_UPGRADED, 0);
                    abilityShieldCost = prefs.getInteger(ABILITY_SHIELD_COST, 500);
                    for (int i = 0; i < Shop.this.shieldUpgraded; i++)
                        abilityPointsShield.get(i).setFilled();
                    drawAbilityElements();
                } else {
                    drawNotEnoughMoneyWindow = true;
                    stage.getActors().get(20).setVisible(true);
                    disableButtonsWindow();
                }

                if (prefs.getInteger(Achievements.POWER_UPS_UPGRADED_ATM_ACHIEVEMENT, 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(prefs.getInteger(POWER_UPS_UPGRADED_TIMES, 0));
            }
        });

        magnet = mainGameAtlas.findRegion("coin_magnet");
        abilityPointsMagnet = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            abilityPointsMagnet.add(new AbilityPoint(shopAtlas));

        for (int i = 0; i < this.magnetUpgraded; i++)
            abilityPointsMagnet.get(i).setFilled();

        ImageButton buyMagnetButton = new ImageButton(buyButtonStyle);
        buyMagnetButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(530.0F));
        buyMagnetButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        buyMagnetButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (coinGlobal >= abilityMagnetCost) {
                    musicSoundsObject.playButtonBuy();
                    prefs.putInteger(COINS, coinGlobal - abilityMagnetCost);
                    prefs.putInteger(MAGNET_UPGRADED, magnetUpgraded + 1);
                    prefs.putInteger(ABILITY_MAGNET_COST, abilityMagnetCost + 500);
                    prefs.putInteger(POWER_UPS_UPGRADED_TIMES, prefs.getInteger(POWER_UPS_UPGRADED_TIMES) + 1);
                    prefs.flush();

                    coinGlobal = prefs.getInteger(COINS);
                    magnetUpgraded = prefs.getInteger(MAGNET_UPGRADED, 0);
                    abilityMagnetCost = prefs.getInteger(ABILITY_MAGNET_COST, 500);
                    for (int i = 0; i < magnetUpgraded; i++)
                        abilityPointsMagnet.get(i).setFilled();
                    drawAbilityElements();
                } else {
                    drawNotEnoughMoneyWindow = true;
                    stage.getActors().get(20).setVisible(true);
                    disableButtonsWindow();
                }
                if (prefs.getInteger(Achievements.POWER_UPS_UPGRADED_ATM_ACHIEVEMENT, 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(prefs.getInteger(POWER_UPS_UPGRADED_TIMES, 0));
            }
        });

        coinRush = mainGameAtlas.findRegion("coin_rush");
        abilityPointsCoinRush = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            abilityPointsCoinRush.add(new AbilityPoint(shopAtlas));

        for (int i = 0; i < this.coinRushUpgraded; i++)
            abilityPointsCoinRush.get(i).setFilled();

        ImageButton buyCoinRushButton = new ImageButton(buyButtonStyle);
        buyCoinRushButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(395.0F));
        buyCoinRushButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        buyCoinRushButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (coinGlobal >= abilityCoinRushCost) {
                    musicSoundsObject.playButtonBuy();
                    prefs.putInteger(COINS, coinGlobal - abilityCoinRushCost);
                    prefs.putInteger(COIN_RUSH_UPGRADED, coinRushUpgraded + 1);
                    prefs.putInteger(ABILITY_COIN_RUSH_COST, abilityCoinRushCost + 500);
                    prefs.putInteger(POWER_UPS_UPGRADED_TIMES, prefs.getInteger(POWER_UPS_UPGRADED_TIMES) + 1);
                    prefs.flush();
                    coinGlobal = prefs.getInteger(COINS);
                    coinRushUpgraded = prefs.getInteger(COIN_RUSH_UPGRADED, 0);
                    abilityCoinRushCost = prefs.getInteger(ABILITY_COIN_RUSH_COST, 500);
                    for (int i = 0; i < coinRushUpgraded; i++)
                        abilityPointsCoinRush.get(i).setFilled();
                    drawAbilityElements();
                } else {
                    drawNotEnoughMoneyWindow = true;
                    stage.getActors().get(20).setVisible(true);
                    disableButtonsWindow();
                }
                if (prefs.getInteger(Achievements.POWER_UPS_UPGRADED_ATM_ACHIEVEMENT, 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(prefs.getInteger(POWER_UPS_UPGRADED_TIMES, 0));
            }
        });

        spawnRate = shopAtlas.findRegion("pickup_spawn_icon");
        abilityPointsSpawnRate = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            abilityPointsSpawnRate.add(new AbilityPoint(shopAtlas));

        for (int i = 0; i < this.spawnRateUpgraded; i++)
            abilityPointsSpawnRate.get(i).setFilled();

        ImageButton buySpawnRateButton = new ImageButton(buyButtonStyle);
        buySpawnRateButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(260.0F));
        buySpawnRateButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        buySpawnRateButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (coinGlobal >= abilitySpawnRateCost) {
                    musicSoundsObject.playButtonBuy();
                    prefs.putInteger(COINS, coinGlobal - abilitySpawnRateCost);
                    prefs.putInteger(SPAWN_RATE_UPGRADED, spawnRateUpgraded + 1);
                    prefs.putInteger(ABILITY_SPAWN_RATE_COST, abilitySpawnRateCost + 500);
                    prefs.putInteger(POWER_UPS_UPGRADED_TIMES, prefs.getInteger(POWER_UPS_UPGRADED_TIMES) + 1);
                    prefs.flush();
                    coinGlobal = prefs.getInteger(COINS);
                    spawnRateUpgraded = prefs.getInteger(SPAWN_RATE_UPGRADED, 0);
                    abilitySpawnRateCost = prefs.getInteger(ABILITY_SPAWN_RATE_COST, 500);
                    for (int i = 0; i < spawnRateUpgraded; i++)
                        abilityPointsSpawnRate.get(i).setFilled();
                    drawAbilityElements();
                } else {
                    drawNotEnoughMoneyWindow = true;
                    stage.getActors().get(20).setVisible(true);
                    disableButtonsWindow();
                }
                if (prefs.getInteger(Achievements.POWER_UPS_UPGRADED_ATM_ACHIEVEMENT, 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(prefs.getInteger(POWER_UPS_UPGRADED_TIMES, 0));
            }
        });

        spawnRateCoins = shopAtlas.findRegion("coin_spawn_icon");
        abilityPointsSpawnRateCoins = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            abilityPointsSpawnRateCoins.add(new AbilityPoint(shopAtlas));

        for (int i = 0; i < spawnRateCoinsUpgraded; i++)
            abilityPointsSpawnRateCoins.get(i).setFilled();

        ImageButton buySpawnRateCoinsButton = new ImageButton(buyButtonStyle);
        buySpawnRateCoinsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(125.0F));
        buySpawnRateCoinsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        buySpawnRateCoinsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (coinGlobal >= abilitySpawnRateCoinsCost) {
                    musicSoundsObject.playButtonBuy();
                    prefs.putInteger(COINS, coinGlobal - abilitySpawnRateCoinsCost);
                    prefs.putInteger(SPAWN_RATE_COINS_UPGRADED, spawnRateCoinsUpgraded + 1);
                    prefs.putInteger(ABILITY_SPAWN_RATE_COINS_COST, abilitySpawnRateCoinsCost + 500);
                    prefs.putInteger(POWER_UPS_UPGRADED_TIMES, prefs.getInteger(POWER_UPS_UPGRADED_TIMES) + 1);
                    prefs.flush();
                    coinGlobal = prefs.getInteger(COINS);
                    spawnRateCoinsUpgraded = prefs.getInteger(SPAWN_RATE_COINS_UPGRADED, 0);
                    abilitySpawnRateCoinsCost = prefs.getInteger(ABILITY_SPAWN_RATE_COINS_COST, 500);
                    for (int i = 0; i < spawnRateCoinsUpgraded; i++)
                        abilityPointsSpawnRateCoins.get(i).setFilled();
                    drawAbilityElements();
                } else {
                    drawNotEnoughMoneyWindow = true;
                    stage.getActors().get(20).setVisible(true);
                    disableButtonsWindow();
                }
                if (prefs.getInteger(Achievements.POWER_UPS_UPGRADED_ATM_ACHIEVEMENT, 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(prefs.getInteger(POWER_UPS_UPGRADED_TIMES, 0));
            }
        });

        Image costumesWindow = new Image(this.shopAtlas.findRegion("costumes_window"));
        costumesWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(700.0F));
        costumesWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(100.0F));

        ImageButton.ImageButtonStyle costumesButtonStyle = new ImageButton.ImageButtonStyle();
        costumesButtonStyle.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costumes_button")));
        costumesButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costumes_button")));

        ImageButton costumesButton = new ImageButton(costumesButtonStyle);
        costumesButton.setPosition(worldXToScreenX(190.0F), worldYToScreenY(790.0F));
        costumesButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(120.0F));
        costumesButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                drawAbilities = false;
                drawCurrency = false;
                drawAbilityElements();
                musicSoundsObject.playButtonClick();
            }
        });

        costumeName = new Image(costumeNames[0]);
        costumeName.setSize(worldXToScreenX(210.0F), worldYToScreenY(75.0F));
        costumeName.setPosition(worldXToScreenX(145.0F), worldYToScreenY(675.0F));

        costumeCost = new Image(costumeCosts[0]);
        costumeCost.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        costumeCost.setPosition(worldXToScreenX(190.0F), worldYToScreenY(575.0F));

        costumeImage = new Image(costumeImages[0]);
        costumeImage.setSize(worldXToScreenX(120.0F), worldYToScreenY(200.0F));
        costumeImage.setPosition(worldXToScreenX(185.0F), worldYToScreenY(325.0F));

        ImageButton.ImageButtonStyle costumeLeftButtonStyle = new ImageButton.ImageButtonStyle();
        costumeLeftButtonStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_left_button_unpressed")));
        costumeLeftButtonStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_left_button_pressed")));

        ImageButton costumeLeftButton = new ImageButton(costumeLeftButtonStyle);
        costumeLeftButton.setPosition(worldXToScreenX(30.0F), worldYToScreenY(412.5F));
        costumeLeftButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        costumeLeftButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                prefs.putInteger(COSTUME_SELECTED, prefs.getInteger(COSTUME_SELECTED) - 1);
                prefs.flush();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle costumeRightButtonStyle = new ImageButton.ImageButtonStyle();
        costumeRightButtonStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_right_button_unpressed")));
        costumeRightButtonStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_right_button_pressed")));

        ImageButton costumeRigthButton = new ImageButton(costumeRightButtonStyle);
        costumeRigthButton.setPosition(worldXToScreenX(395.0F), worldYToScreenY(412.5F));
        costumeRigthButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        costumeRigthButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                prefs.putInteger(COSTUME_SELECTED, prefs.getInteger(COSTUME_SELECTED) + 1);
                prefs.flush();
                musicSoundsObject.playButtonClick();
            }
        });

        costumeBuyButtonStyle = new ImageButton.ImageButtonStyle();
        costumeBuyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_buy_button_unpressed")));
        costumeBuyButtonStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_buy_button_pressed")));

        costumeSelectButtonStyle = new ImageButton.ImageButtonStyle();
        costumeSelectButtonStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_select_button_unpressed")));
        costumeSelectButtonStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_select_button_pressed")));

        costumeBuySelectButton = new ImageButton(costumeBuyButtonStyle);
        costumeBuySelectButton.setPosition(worldXToScreenX(90.0F), worldYToScreenY(150.0F));
        costumeBuySelectButton.setSize(worldXToScreenX(320.0F), worldYToScreenY(125.0F));
        costumeBuySelectButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                if (prefs.getInteger(COSTUME_SELECTED) == PLAYER_NUMBER) {
                    if (prefs.getBoolean(PLAYER_BOUGHT)) {
                        prefs.putInteger(COSTUME_SELECTED_GAME, 0);
                        prefs.flush();
                    }
                } else if (prefs.getInteger(COSTUME_SELECTED) == ROBOT_NUMBER)
                    costumeBuySelectButton(ROBOT_BOUGHT, ROBOT_NUMBER, DIAMONDS, 500);
                else if (prefs.getInteger(COSTUME_SELECTED) == KNIGHT_NUMBER)
                    costumeBuySelectButton(KNIGHT_BOUGHT, KNIGHT_NUMBER, DIAMONDS, 500);
                else if (prefs.getInteger(COSTUME_SELECTED) == COWBOY_NUMBER)
                    costumeBuySelectButton(COWBOY_BOUGHT, COWBOY_NUMBER, COINS, 1000);
                else if (prefs.getInteger(COSTUME_SELECTED) == COWGIRL_NUMBER) {
                    costumeBuySelectButton(COWGIRL_BOUGHT, COWGIRL_NUMBER, COINS, 1000);
                } else if (prefs.getInteger(COSTUME_SELECTED) == NINJA_MALE_NUMBER) {
                    costumeBuySelectButton(NINJA_MALE_BOUGHT, NINJA_MALE_NUMBER, DIAMONDS, 500);
                } else if (prefs.getInteger(COSTUME_SELECTED) == NINJA_FEMALE_NUMBER) {
                    costumeBuySelectButton(NINJA_FEMALE_BOUGHT, NINJA_FEMALE_NUMBER, DIAMONDS, 500);
                } else if (prefs.getInteger(COSTUME_SELECTED) == DINO_NUMBER) {

                }
            }
        });

        Image costumeSelected = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_selected"))));
        costumeSelected.setSize(worldXToScreenX(425.0F), worldYToScreenY(125.0F));
        costumeSelected.setPosition(worldXToScreenX(35.5F), worldYToScreenY(150.0F));

        Image costumeBuySureWindow = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_buy_sure_window"))));
        costumeBuySureWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        costumeBuySureWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton notBuyCostumeButton = new ImageButton(xButtonStyle);
        notBuyCostumeButton.setPosition(worldXToScreenX(120.0F), worldYToScreenY(420.0F));
        notBuyCostumeButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        notBuyCostumeButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(21).setVisible(false);
                stage.getActors().get(22).setVisible(false);
                stage.getActors().get(23).setVisible(false);
                enableButtonsWindow();
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle buyCostumeButtonStyle = new ImageButton.ImageButtonStyle();
        buyCostumeButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        buyCostumeButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton buyCostumeButton = new ImageButton(buyCostumeButtonStyle);
        buyCostumeButton.setPosition(worldXToScreenX(305.0F), worldYToScreenY(420.0F));
        buyCostumeButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        buyCostumeButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonBuy();
                if (prefs.getInteger(COSTUME_SELECTED) == ROBOT_NUMBER)
                    costumeBuyButton(ROBOT_BOUGHT, ROBOT_NUMBER, DIAMONDS, 500);
                else if (prefs.getInteger(COSTUME_SELECTED) == KNIGHT_NUMBER)
                    costumeBuyButton(KNIGHT_BOUGHT, KNIGHT_NUMBER, DIAMONDS, 500);
                else if (prefs.getInteger(COSTUME_SELECTED) == COWBOY_NUMBER)
                    costumeBuyButton(COWBOY_BOUGHT, COWBOY_NUMBER, COINS, 1000);
                else if (prefs.getInteger(COSTUME_SELECTED) == COWGIRL_NUMBER) {
                    costumeBuyButton(COWGIRL_BOUGHT, COWGIRL_NUMBER, COINS, 1000);
                } else if (prefs.getInteger(COSTUME_SELECTED) == NINJA_MALE_NUMBER) {
                    costumeBuyButton(NINJA_MALE_BOUGHT, NINJA_MALE_NUMBER, DIAMONDS, 500);
                } else if (prefs.getInteger(COSTUME_SELECTED) == NINJA_FEMALE_NUMBER) {
                    costumeBuyButton(NINJA_FEMALE_BOUGHT, NINJA_FEMALE_NUMBER, DIAMONDS, 500);
                } else if (prefs.getInteger(COSTUME_SELECTED) == DINO_NUMBER) {

                }
                stage.getActors().get(21).setVisible(false);
                stage.getActors().get(22).setVisible(false);
                stage.getActors().get(23).setVisible(false);
                enableButtonsWindow();
            }
        });

        Image currencyWindow = new Image(shopAtlas.findRegion("currency_window"));
        currencyWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(700.0F));
        currencyWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(100.0F));

        ImageButton.ImageButtonStyle currencyButtonStyle = new ImageButton.ImageButtonStyle();
        currencyButtonStyle.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("currency_button")));
        currencyButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("currency_button")));

        ImageButton currencyButton = new ImageButton(currencyButtonStyle);
        currencyButton.setPosition(worldXToScreenX(320.0F), worldYToScreenY(790.0F));
        currencyButton.setSize(worldXToScreenX(120.0F), worldYToScreenY(120.0F));
        currencyButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                drawAbilities = false;
                drawCurrency = true;
                drawAbilityElements();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle buy1000CoinsStyle = new ImageButton.ImageButtonStyle();
        buy1000CoinsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("100_diamonds_button_unpressed")));
        buy1000CoinsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("100_diamonds_button_pressed")));

        ImageButton.ImageButtonStyle buy5000CoinsStyle = new ImageButton.ImageButtonStyle();
        buy5000CoinsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("300_diamonds_button_unpressed")));
        buy5000CoinsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("300_diamonds_button_pressed")));

        ImageButton.ImageButtonStyle buy10000CoinsStyle = new ImageButton.ImageButtonStyle();
        buy10000CoinsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("500_diamonds_button_unpressed")));
        buy10000CoinsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("500_diamonds_button_pressed")));

        ImageButton.ImageButtonStyle buy50000CoinsStyle = new ImageButton.ImageButtonStyle();
        buy50000CoinsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("1500_diamonds_button_unpressed")));
        buy50000CoinsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("1500_diamonds_button_pressed")));

        ImageButton.ImageButtonStyle removeAds10DiamondsStyle = new ImageButton.ImageButtonStyle();
        removeAds10DiamondsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("099_button_unpressed")));
        removeAds10DiamondsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("099_button_pressed")));

        ImageButton.ImageButtonStyle buy50DiamondsStyle = new ImageButton.ImageButtonStyle();
        buy50DiamondsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("249_button_unpressed")));
        buy50DiamondsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("249_button_pressed")));

        ImageButton.ImageButtonStyle buy100DiamondsStyle = new ImageButton.ImageButtonStyle();
        buy100DiamondsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("399_button_unpressed")));
        buy100DiamondsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("399_button_pressed")));

        ImageButton.ImageButtonStyle buy500DiamondsStyle = new ImageButton.ImageButtonStyle();
        buy500DiamondsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("1499_button_unpressed")));
        buy500DiamondsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("1499_button_pressed")));

        ImageButton.ImageButtonStyle buy1000DiamondsStyle = new ImageButton.ImageButtonStyle();
        buy1000DiamondsStyle.up = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("2499_button_unpressed")));
        buy1000DiamondsStyle.down = new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("2499_button_pressed")));

        ImageButton buy1000Coins = new ImageButton(buy1000CoinsStyle);
        buy1000Coins.setPosition(worldXToScreenX(350.0F), worldYToScreenY(737.0F));
        buy1000Coins.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy1000Coins.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                checkBuyCoins(100, 0);
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton buy5000Coins = new ImageButton(buy5000CoinsStyle);
        buy5000Coins.setPosition(worldXToScreenX(350.0F), worldYToScreenY(669.0F));
        buy5000Coins.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy5000Coins.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                checkBuyCoins(300, 1);
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton buy10000Coins = new ImageButton(buy10000CoinsStyle);
        buy10000Coins.setPosition(worldXToScreenX(350.0F), worldYToScreenY(599.0F));
        buy10000Coins.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy10000Coins.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                checkBuyCoins(500, 2);
                musicSoundsObject.playButtonClick();
            }
        });
        ImageButton buy50000Coins = new ImageButton(buy50000CoinsStyle);
        buy50000Coins.setPosition(worldXToScreenX(350.0F), worldYToScreenY(529.0F));
        buy50000Coins.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy50000Coins.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                checkBuyCoins(1500, 3);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton buyDoubleCoins = new ImageButton(buy10000CoinsStyle);
        buyDoubleCoins.setPosition(worldXToScreenX(350.0F), worldYToScreenY(459.0F));
        buyDoubleCoins.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buyDoubleCoins.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                checkBuyCoins(500, 4);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton removeAdsButton = new ImageButton(removeAds10DiamondsStyle);
        removeAdsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(388.0F));
        removeAdsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        removeAdsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton buy50DiamondsButton = new ImageButton(buy50DiamondsStyle);
        buy50DiamondsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(321.0F));
        buy50DiamondsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy50DiamondsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton buy100DiamondsButton = new ImageButton(buy100DiamondsStyle);
        buy100DiamondsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(251.0F));
        buy100DiamondsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy100DiamondsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton buy500DiamondsButton = new ImageButton(buy500DiamondsStyle);
        buy500DiamondsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(181.0F));
        buy500DiamondsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy500DiamondsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton buy1000DiamondsButton = new ImageButton(buy1000DiamondsStyle);
        buy1000DiamondsButton.setPosition(worldXToScreenX(350.0F), worldYToScreenY(114.0F));
        buy1000DiamondsButton.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        buy1000DiamondsButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        Image diamondsBuySureWindow = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("costume_buy_sure_window"))));
        diamondsBuySureWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        diamondsBuySureWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton diamondsNotBuyButton = new ImageButton(xButtonStyle);
        diamondsNotBuyButton.setPosition(worldXToScreenX(120.0F), worldYToScreenY(420.0F));
        diamondsNotBuyButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        diamondsNotBuyButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(35).setVisible(false);
                stage.getActors().get(36).setVisible(false);
                stage.getActors().get(37).setVisible(false);
                enableButtonsWindow();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton diamondsBuyButton = new ImageButton(buyCostumeButtonStyle);
        diamondsBuyButton.setPosition(worldXToScreenX(305.0F), worldYToScreenY(420.0F));
        diamondsBuyButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        diamondsBuyButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonBuy();
                if (currencySelected == 0) {
                    buyCoins(1000, 100);
                } else if (currencySelected == 1) {
                    buyCoins(5000, 300);
                } else if (currencySelected == 2) {
                    buyCoins(10000, 500);
                } else if (currencySelected == 3) {
                    buyCoins(50000, 1500);
                } else if (currencySelected == 4) {
                    prefs.putInteger(DIAMONDS, prefs.getInteger(DIAMONDS) - 500);
                    prefs.putBoolean(DOUBLE_COINS, true);
                    prefs.flush();
                    drawAbilityElements();
                }
                stage.getActors().get(35).setVisible(false);
                stage.getActors().get(36).setVisible(false);
                stage.getActors().get(37).setVisible(false);
                enableButtonsWindow();
            }
        });

        Image notEnoughDiamondsWindow = new Image(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("not_enough_diamonds_window"))));
        notEnoughDiamondsWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        notEnoughDiamondsWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));

        ImageButton xButtonDiamondsWindow = new ImageButton(xButtonStyle);
        xButtonDiamondsWindow.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
        xButtonDiamondsWindow.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButtonDiamondsWindow.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(38).setVisible(false);
                stage.getActors().get(39).setVisible(false);
                enableButtonsWindow();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));

        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        backButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        backButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                prefs.putInteger(COSTUME_SELECTED, prefs.getInteger(COSTUME_SELECTED_GAME));
                prefs.flush();
                gsm.set(new MainMenu(gsm, adsController, manager));
                dispose();
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
                stage.getActors().get(40).setVisible(false);
                stage.getActors().get(41).setVisible(false);
                stage.getActors().get(42).setVisible(false);
                drawQuitGame = false;
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        quitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

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

        stage.addActor(bg);
        stage.addActor(powerUpsButton);
        stage.addActor(costumesButton);
        stage.addActor(backButton);
        stage.addActor(currencyButton);
        stage.addActor(powerUpsWindow);
        stage.addActor(costumesWindow);
        stage.addActor(buyShieldButton);
        stage.addActor(buyMagnetButton);
        stage.addActor(buyCoinRushButton);
        stage.addActor(buySpawnRateButton);
        stage.addActor(buySpawnRateCoinsButton);
        stage.addActor(costumeName);
        stage.addActor(costumeCost);
        stage.addActor(costumeImage);
        stage.addActor(costumeLeftButton);
        stage.addActor(costumeRigthButton);
        stage.addActor(costumeBuySelectButton);
        stage.addActor(costumeSelected);
        stage.addActor(notEnoughCoinsDiamondsWindow);
        stage.addActor(xButtonNotEnoughMoney);
        stage.addActor(costumeBuySureWindow);
        stage.addActor(notBuyCostumeButton);
        stage.addActor(buyCostumeButton);
        stage.addActor(currencyWindow);
        stage.addActor(buy1000Coins);
        stage.addActor(buy5000Coins);
        stage.addActor(buy10000Coins);
        stage.addActor(buy50000Coins);
        stage.addActor(removeAdsButton);
        stage.addActor(buy50DiamondsButton);
        stage.addActor(buy100DiamondsButton);
        stage.addActor(buy500DiamondsButton);
        stage.addActor(buy1000DiamondsButton);
        stage.addActor(buyDoubleCoins);
        stage.addActor(diamondsBuySureWindow);
        stage.addActor(diamondsBuyButton);
        stage.addActor(diamondsNotBuyButton);
        stage.addActor(notEnoughDiamondsWindow);
        stage.addActor(xButtonDiamondsWindow);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        stage.getActors().get(15).setVisible(false);
        stage.getActors().get(16).setVisible(false);
        stage.getActors().get(17).setVisible(false);
        stage.getActors().get(18).setVisible(false);
        stage.getActors().get(19).setVisible(false);
        stage.getActors().get(20).setVisible(false);
        stage.getActors().get(21).setVisible(false);
        stage.getActors().get(22).setVisible(false);
        stage.getActors().get(23).setVisible(false);
        stage.getActors().get(24).setVisible(false);
        stage.getActors().get(25).setVisible(false);
        stage.getActors().get(26).setVisible(false);
        stage.getActors().get(27).setVisible(false);
        stage.getActors().get(28).setVisible(false);
        stage.getActors().get(29).setVisible(false);
        stage.getActors().get(30).setVisible(false);
        stage.getActors().get(31).setVisible(false);
        stage.getActors().get(32).setVisible(false);
        stage.getActors().get(33).setVisible(false);
        stage.getActors().get(34).setVisible(false);
        stage.getActors().get(35).setVisible(false);
        stage.getActors().get(36).setVisible(false);
        stage.getActors().get(37).setVisible(false);
        stage.getActors().get(38).setVisible(false);
        stage.getActors().get(39).setVisible(false);
        stage.getActors().get(40).setVisible(false);
        stage.getActors().get(41).setVisible(false);
        stage.getActors().get(42).setVisible(false);
        drawAbilities = true;
        drawAbilityElements();
    }


    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
        this.stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch batch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(40).setVisible(true);
            stage.getActors().get(41).setVisible(true);
            stage.getActors().get(42).setVisible(true);
            drawQuitGame = true;
        }

        if (!drawAbilities) {
            if (prefs.getInteger(COSTUME_SELECTED, 0) == PLAYER_NUMBER) {
                stage.getActors().get(15).setVisible(false);
                setCostumeParameters(0, 210, 145, 120, 190, 120, 190);
                costumeSelected(PLAYER_BOUGHT, PLAYER_NUMBER);
            } else
                this.stage.getActors().get(15).setVisible(true);

            if (prefs.getInteger(COSTUME_SELECTED) == ROBOT_NUMBER) {
                setCostumeParameters(1, 175, 162, 130, 185, 206, 147);
                costumeSelected(ROBOT_BOUGHT, ROBOT_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == KNIGHT_NUMBER) {
                setCostumeParameters(2, 255, 123, 130, 185, 196, 152);
                costumeSelected(KNIGHT_BOUGHT, KNIGHT_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == COWBOY_NUMBER) {
                setCostumeParameters(3, 123, 188, 135, 182, 130, 185);
                costumeSelected(COWBOY_BOUGHT, COWBOY_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == COWGIRL_NUMBER) {
                setCostumeParameters(4, 213, 143, 135, 182, 124, 188);
                costumeSelected(COWGIRL_BOUGHT, COWGIRL_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == NINJA_MALE_NUMBER) {
                setCostumeParameters(5, 160, 170, 130, 185, 193, 153);
                costumeSelected(NINJA_MALE_BOUGHT, NINJA_MALE_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == NINJA_FEMALE_NUMBER) {
                setCostumeParameters(6, 150, 185, 130, 185, 198, 151);
                costumeSelected(NINJA_FEMALE_BOUGHT, NINJA_FEMALE_NUMBER);
            }

            if (this.prefs.getInteger(COSTUME_SELECTED) == DINO_NUMBER) {
                this.stage.getActors().get(16).setVisible(false);
                setCostumeParameters(7, 155, 172, 114, 193, 289, 105);
                costumeSelected(DINO_BOUGHT, DINO_NUMBER);
            } else
                this.stage.getActors().get(16).setVisible(true);

        }
        batch.begin();
        batch.draw(coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinAndDiamondFont.draw(batch, String.valueOf(this.coinGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
        batch.draw(diamond, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        coinAndDiamondFont.draw(batch, String.valueOf(this.rubyGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));

        if (drawAbilities) {
            batch.draw(shield, worldXToScreenX(50.0F), worldYToScreenY(672.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (shieldUpgraded != 10)
                buyButtonTextFont.draw(batch, String.valueOf(abilityShieldCost), worldXToScreenX(380.0F), worldYToScreenY(695.0F), worldXToScreenX(30.0F), Align.left, false);
            textFont.draw(batch, "Shield Duration", worldXToScreenX(150.0F), worldYToScreenY(775.0F));
            smallerTextFont.draw(batch, "Increase duration by " + shieldUpgraded * 10 + "%", worldXToScreenX(150.0F), worldYToScreenY(735.0F));

            batch.draw(magnet, worldXToScreenX(50.0F), worldYToScreenY(537.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (magnetUpgraded != 10)
                buyButtonTextFont.draw(batch, String.valueOf(abilityMagnetCost), worldXToScreenX(380.0F), worldYToScreenY(560.0F), worldXToScreenX(30.0F), Align.left, false);
            textFont.draw(batch, "Magnet Duration", worldXToScreenX(150.0F), worldYToScreenY(640.0F));
            smallerTextFont.draw(batch, "Increase duration by " + magnetUpgraded * 10 + "%", worldXToScreenX(150.0F), worldYToScreenY(600.0F));

            batch.draw(coinRush, worldXToScreenX(50.0F), worldYToScreenY(402.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (coinRushUpgraded != 10)
                buyButtonTextFont.draw(batch, String.valueOf(abilityCoinRushCost), worldXToScreenX(380.0F), worldYToScreenY(425.0F), worldXToScreenX(30.0F), Align.left, false);
            textFont.draw(batch, "Coin Rush Duration", worldXToScreenX(150.0F), worldYToScreenY(505.0F));
            smallerTextFont.draw(batch, "Increase duration by " + coinRushUpgraded * 10 + "%", worldXToScreenX(150.0F), worldYToScreenY(465.0F));

            batch.draw(spawnRate, worldXToScreenX(50.0F), worldYToScreenY(267.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (spawnRateUpgraded != 10)
                buyButtonTextFont.draw(batch, String.valueOf(abilitySpawnRateCost), worldXToScreenX(380.0F), worldYToScreenY(290.0F), worldXToScreenX(30.0F), Align.left, false);
            textFont.draw(batch, "Pickup Spawn", worldXToScreenX(150.0F), worldYToScreenY(370.0F));
            smallerTextFont.draw(batch, "Spawn " + spawnRateUpgraded * 10 + "% more frequently", worldXToScreenX(150.0F), worldYToScreenY(330.0F));

            batch.draw(spawnRateCoins, worldXToScreenX(50.0F), worldYToScreenY(132.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (spawnRateCoinsUpgraded != 10)
                buyButtonTextFont.draw(batch, String.valueOf(abilitySpawnRateCoinsCost), worldXToScreenX(380.0F), worldYToScreenY(155.0F), worldXToScreenX(30.0F), Align.left, false);
            textFont.draw(batch, "Coin Spawn", worldXToScreenX(150.0F), worldYToScreenY(235.0F));
            smallerTextFont.draw(batch, "Spawn " + spawnRateCoinsUpgraded * 10 + "% more frequently", worldXToScreenX(150.0F), worldYToScreenY(195.0F));
            drawAllAbilities(batch);

            if (drawNotEnoughMoneyWindow) {
                batch.draw(notEnoughMoneyWindow, worldXToScreenX(10.0F), worldYToScreenY(410.0F), worldXToScreenX(480.0F), worldYToScreenY(180.0F));
                batch.draw(xNotEnoughMoneyButton, worldXToScreenX(420.0F), worldYToScreenY(525.0F), worldXToScreenX(50.0F), worldYToScreenY(50.0F));
            }
            if (drawQuitGame) {
                batch.draw(quitWindowTexture, worldXToScreenX(10.0F), worldYToScreenY(395.0F), worldXToScreenX(480.0F), worldYToScreenY(210.0F));
                batch.draw(notQuitButtonTexture, worldXToScreenX(116.0F), worldYToScreenY(405.0F), worldXToScreenX(75.0F), worldYToScreenY(75.0F));
                batch.draw(quitButtonTexture, worldXToScreenX(307.0F), worldYToScreenY(405.0F), worldXToScreenX(75.0F), worldYToScreenY(75.0F));
            }
        }
        batch.end();
    }

    private void drawAllAbilities(SpriteBatch batch) {
        for (int i = 0; i < 10; i++) {
            float f = (i * 20 + 150);
            drawShieldAbility(batch, f, i);
            drawMagnetAbility(batch, f, i);
            drawCoinRushAbility(batch, f, i);
            drawSpawnRateAbility(batch, f, i);
            drawSpawnRateCoinsAbility(batch, f, i);
        }
    }

    private void drawShieldAbility(SpriteBatch batch, float xCoordinate, int abilityIndex) {
        if (abilityPointsShield.get(abilityIndex).isFilled()) {
            batch.draw(abilityPointsShield.get(abilityIndex).getAbilityPoint(1), worldXToScreenX(xCoordinate), worldYToScreenY(665.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            batch.draw(abilityPointsShield.get(abilityIndex).getAbilityPoint(0), worldXToScreenX(xCoordinate), worldYToScreenY(665.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawMagnetAbility(SpriteBatch batch, float xCoordinate, int abilityIndex) {
        if (abilityPointsMagnet.get(abilityIndex).isFilled()) {
            batch.draw(abilityPointsMagnet.get(abilityIndex).getAbilityPoint(1), worldXToScreenX(xCoordinate), worldYToScreenY(530.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            batch.draw(abilityPointsMagnet.get(abilityIndex).getAbilityPoint(0), worldXToScreenX(xCoordinate), worldYToScreenY(530.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawCoinRushAbility(SpriteBatch batch, float xCoordinate, int abilityIndex) {
        if (abilityPointsCoinRush.get(abilityIndex).isFilled()) {
            batch.draw(abilityPointsCoinRush.get(abilityIndex).getAbilityPoint(1), worldXToScreenX(xCoordinate), worldYToScreenY(395.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            batch.draw(abilityPointsCoinRush.get(abilityIndex).getAbilityPoint(0), worldXToScreenX(xCoordinate), worldYToScreenY(395.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawSpawnRateAbility(SpriteBatch batch, float xCoordinate, int abilityIndex) {
        if (abilityPointsSpawnRate.get(abilityIndex).isFilled()) {
            batch.draw(abilityPointsSpawnRate.get(abilityIndex).getAbilityPoint(1), worldXToScreenX(xCoordinate), worldYToScreenY(260.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            batch.draw(abilityPointsSpawnRate.get(abilityIndex).getAbilityPoint(0), worldXToScreenX(xCoordinate), worldYToScreenY(260.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawSpawnRateCoinsAbility(SpriteBatch batch, float xCoordinate, int abilityIndex) {
        if (abilityPointsSpawnRateCoins.get(abilityIndex).isFilled()) {
            batch.draw(abilityPointsSpawnRateCoins.get(abilityIndex).getAbilityPoint(1), worldXToScreenX(xCoordinate), worldYToScreenY(125.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            batch.draw(abilityPointsSpawnRateCoins.get(abilityIndex).getAbilityPoint(0), worldXToScreenX(xCoordinate), worldYToScreenY(125.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }


    private void drawAbilityElements() {
        if (drawAbilities) {
            stage.getActors().get(5).setVisible(true);
            if (shieldUpgraded != 10)
                stage.getActors().get(7).setVisible(true);
            else
                stage.getActors().get(7).setVisible(false);

            if (magnetUpgraded != 10)
                stage.getActors().get(8).setVisible(true);
            else
                stage.getActors().get(8).setVisible(false);

            if (coinRushUpgraded != 10)
                stage.getActors().get(9).setVisible(true);
            else
                stage.getActors().get(9).setVisible(false);

            if (spawnRateUpgraded != 10)
                stage.getActors().get(10).setVisible(true);
            else
                stage.getActors().get(10).setVisible(false);

            if (this.spawnRateCoinsUpgraded != 10)
                stage.getActors().get(11).setVisible(true);
            else
                stage.getActors().get(11).setVisible(false);

            stage.getActors().get(6).setVisible(false);
            stage.getActors().get(12).setVisible(false);
            stage.getActors().get(13).setVisible(false);
            stage.getActors().get(14).setVisible(false);
            stage.getActors().get(15).setVisible(false);
            stage.getActors().get(16).setVisible(false);
            stage.getActors().get(17).setVisible(false);
            stage.getActors().get(18).setVisible(false);
            stage.getActors().get(24).setVisible(false);
            stage.getActors().get(25).setVisible(false);
            stage.getActors().get(26).setVisible(false);
            stage.getActors().get(27).setVisible(false);
            stage.getActors().get(28).setVisible(false);
            stage.getActors().get(29).setVisible(false);
            stage.getActors().get(30).setVisible(false);
            stage.getActors().get(31).setVisible(false);
            stage.getActors().get(32).setVisible(false);
            stage.getActors().get(33).setVisible(false);
            stage.getActors().get(34).setVisible(false);
        } else if (drawCurrency) {
            stage.getActors().get(24).setVisible(true);
            stage.getActors().get(25).setVisible(true);
            stage.getActors().get(26).setVisible(true);
            stage.getActors().get(27).setVisible(true);
            stage.getActors().get(28).setVisible(true);
            stage.getActors().get(29).setVisible(true);
            stage.getActors().get(30).setVisible(true);
            stage.getActors().get(31).setVisible(true);
            stage.getActors().get(32).setVisible(true);
            stage.getActors().get(33).setVisible(true);
            if (prefs.getBoolean(DOUBLE_COINS, false))
                stage.getActors().get(34).setVisible(false);
            else
                stage.getActors().get(34).setVisible(true);
        } else {
            stage.getActors().get(5).setVisible(false);
            stage.getActors().get(6).setVisible(true);
            stage.getActors().get(7).setVisible(false);
            stage.getActors().get(8).setVisible(false);
            stage.getActors().get(9).setVisible(false);
            stage.getActors().get(10).setVisible(false);
            stage.getActors().get(11).setVisible(false);
            stage.getActors().get(12).setVisible(true);
            stage.getActors().get(13).setVisible(true);
            stage.getActors().get(14).setVisible(true);
            stage.getActors().get(24).setVisible(false);
            stage.getActors().get(25).setVisible(false);
            stage.getActors().get(26).setVisible(false);
            stage.getActors().get(27).setVisible(false);
            stage.getActors().get(28).setVisible(false);
            stage.getActors().get(29).setVisible(false);
            stage.getActors().get(30).setVisible(false);
            stage.getActors().get(31).setVisible(false);
            stage.getActors().get(32).setVisible(false);
            stage.getActors().get(33).setVisible(false);
            stage.getActors().get(34).setVisible(false);
        }
    }

    public void update(float paramFloat) {
    }

    private void buyCoins(int coins, int diamonds) {
        prefs.putInteger(DIAMONDS, prefs.getInteger(DIAMONDS) - diamonds);
        prefs.putInteger(COINS, prefs.getInteger(COINS) + coins);
        prefs.flush();
        coinGlobal = this.prefs.getInteger(COINS);
        rubyGlobal = this.prefs.getInteger(DIAMONDS);
        disableButtonsWindow();
    }

    private void checkBuyCoins(int cost, int currency) {
        if (prefs.getInteger(DIAMONDS) >= cost) {
            stage.getActors().get(35).setVisible(true);
            stage.getActors().get(36).setVisible(true);
            stage.getActors().get(37).setVisible(true);
            currencySelected = currency;
        } else {
            this.notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("not_enough_diamonds_window"))));
            this.stage.getActors().get(38).setVisible(true);
            this.stage.getActors().get(39).setVisible(true);
        }
        disableButtonsWindow();
    }

    private void costumeBuyButton(String characterBought, int characterNumber, String coinsRubies, int cost) {
        prefs.putBoolean(characterBought, true);
        prefs.putInteger(COSTUME_SELECTED_GAME, characterNumber);
        prefs.putInteger(coinsRubies, prefs.getInteger(coinsRubies) - cost);
        prefs.flush();
        coinGlobal = this.prefs.getInteger("coins");
        rubyGlobal = this.prefs.getInteger("ruby");
    }

    private void costumeBuySelectButton(String characterBought, int characterNumber, String coinsRubies, int cost) {
        if (prefs.getBoolean(characterBought, false)) {
            prefs.putInteger(COSTUME_SELECTED_GAME, characterNumber);
            prefs.flush();
        } else if (prefs.getInteger(coinsRubies) >= cost) {
            disableButtonsWindow();
            stage.getActors().get(21).setVisible(true);
            stage.getActors().get(22).setVisible(true);
            stage.getActors().get(23).setVisible(true);
        } else {
            disableButtonsWindow();
            if (coinsRubies.equals(COINS)) {
                notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("not_enough_coins_window"))));
            } else {
                notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(shopAtlas.findRegion("not_enough_diamonds_window"))));
            }
            stage.getActors().get(19).setVisible(true);
            stage.getActors().get(20).setVisible(true);
        }
    }

    private void costumeSelected(String characterBought, int characterNumber) {
        if (prefs.getBoolean(characterBought, false)) {
            costumeBuySelectButton.setStyle(costumeSelectButtonStyle);
            stage.getActors().get(13).setVisible(false);
        } else {
            costumeBuySelectButton.setStyle(costumeBuyButtonStyle);
            stage.getActors().get(13).setVisible(true);
        }

        if (prefs.getInteger(COSTUME_SELECTED_GAME, 0) == characterNumber) {
            stage.getActors().get(17).setVisible(false);
            stage.getActors().get(18).setVisible(true);
        } else {
            stage.getActors().get(17).setVisible(true);
            stage.getActors().get(18).setVisible(false);
        }
    }

    private void setCostumeParameters(int characterNumber, int xNameSize, int xNamePosition, int xCostSize, int xCostPosition, int xImageSize, int xImagePosition) {
        costumeName.setDrawable(new TextureRegionDrawable(new TextureRegion(costumeNames[characterNumber])));
        costumeName.setSize(worldXToScreenX(xNameSize), worldYToScreenY(75.0F));
        costumeName.setPosition(worldXToScreenX(xNamePosition), worldYToScreenY(675.0F));
        costumeCost.setDrawable(new TextureRegionDrawable(new TextureRegion(costumeCosts[characterNumber])));
        costumeCost.setSize(worldXToScreenX(xCostSize), worldYToScreenY(50.0F));
        costumeCost.setPosition(worldXToScreenX(xCostPosition), worldYToScreenY(575.0F));
        costumeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(costumeImages[characterNumber])));
        costumeImage.setSize(worldXToScreenX(xImageSize), worldYToScreenY(200.0F));
        costumeImage.setPosition(worldXToScreenX(xImagePosition), worldYToScreenY(325.0F));
    }

    private void enableButtonsWindow() {
        stage.getActors().get(1).setTouchable(Touchable.enabled);
        stage.getActors().get(2).setTouchable(Touchable.enabled);
        stage.getActors().get(3).setTouchable(Touchable.enabled);
        stage.getActors().get(4).setTouchable(Touchable.enabled);
        stage.getActors().get(7).setTouchable(Touchable.enabled);
        stage.getActors().get(8).setTouchable(Touchable.enabled);
        stage.getActors().get(9).setTouchable(Touchable.enabled);
        stage.getActors().get(10).setTouchable(Touchable.enabled);
        stage.getActors().get(11).setTouchable(Touchable.enabled);
        stage.getActors().get(15).setTouchable(Touchable.enabled);
        stage.getActors().get(16).setTouchable(Touchable.enabled);
        stage.getActors().get(17).setTouchable(Touchable.enabled);
        stage.getActors().get(25).setTouchable(Touchable.enabled);
        stage.getActors().get(26).setTouchable(Touchable.enabled);
        stage.getActors().get(27).setTouchable(Touchable.enabled);
        stage.getActors().get(28).setTouchable(Touchable.enabled);
        stage.getActors().get(29).setTouchable(Touchable.enabled);
        stage.getActors().get(30).setTouchable(Touchable.enabled);
        stage.getActors().get(31).setTouchable(Touchable.enabled);
        stage.getActors().get(32).setTouchable(Touchable.enabled);
        stage.getActors().get(33).setTouchable(Touchable.enabled);
        stage.getActors().get(34).setTouchable(Touchable.enabled);
        stage.getActors().get(35).setTouchable(Touchable.enabled);
    }


    private void disableButtonsWindow() {
        stage.getActors().get(1).setTouchable(Touchable.disabled);
        stage.getActors().get(2).setTouchable(Touchable.disabled);
        stage.getActors().get(3).setTouchable(Touchable.disabled);
        stage.getActors().get(4).setTouchable(Touchable.disabled);
        stage.getActors().get(7).setTouchable(Touchable.disabled);
        stage.getActors().get(8).setTouchable(Touchable.disabled);
        stage.getActors().get(9).setTouchable(Touchable.disabled);
        stage.getActors().get(10).setTouchable(Touchable.disabled);
        stage.getActors().get(11).setTouchable(Touchable.disabled);
        stage.getActors().get(15).setTouchable(Touchable.disabled);
        stage.getActors().get(16).setTouchable(Touchable.disabled);
        stage.getActors().get(17).setTouchable(Touchable.disabled);
        stage.getActors().get(25).setTouchable(Touchable.disabled);
        stage.getActors().get(26).setTouchable(Touchable.disabled);
        stage.getActors().get(27).setTouchable(Touchable.disabled);
        stage.getActors().get(28).setTouchable(Touchable.disabled);
        stage.getActors().get(29).setTouchable(Touchable.disabled);
        stage.getActors().get(30).setTouchable(Touchable.disabled);
        stage.getActors().get(31).setTouchable(Touchable.disabled);
        stage.getActors().get(32).setTouchable(Touchable.disabled);
        stage.getActors().get(33).setTouchable(Touchable.disabled);
        stage.getActors().get(34).setTouchable(Touchable.disabled);
        stage.getActors().get(35).setTouchable(Touchable.disabled);
    }

}