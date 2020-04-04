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
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class Shop extends State {
    private static final int ABILITIES_BACKGROUND = 5;
    private static final String ABILITY_COIN_RUSH_COST = "abilityCoinRushCost";
    private static final String ABILITY_MAGNET_COST = "abilityMagnetCost";
    private static final String ABILITY_SHIELD_COST = "abilityShieldCost";
    private static final String ABILITY_SPAWN_RATE_COINS_COST = "abilitySpawnRateCoinsCost";
    private static final String ABILITY_SPAWN_RATE_COST = "abilitySpawnRateCost";
    static final String COINS = "coins";
    public static final String COIN_RUSH_UPGRADED = "coinRushUpgraded";
    private static final int COSTUMES_BACKGROUND = 6;
    private static final String COSTUME_SELECTED = "costumeSelected";
    public static final String COSTUME_SELECTED_GAME = "costumeSelectedGame";
    private static final String COWBOY_BOUGHT = "cowboyBought";
    public static final int COWBOY_NUMBER = 3;
    private static final String COWGIRL_BOUGHT = "cowgirlBought";
    public static final int COWGIRL_NUMBER = 4;
    private static final String DINO_BOUGHT = "dinoBought";
    public static final int DINO_NUMBER = 7;
    public static final String DOUBLE_COINS = "doubleCoins";
    private static final String KNIGHT_BOUGHT = "knightBought";
    public static final int KNIGHT_NUMBER = 2;
    public static final String MAGNET_UPGRADED = "magnetUpgraded";
    private static final String NINJA_FEMALE_BOUGHT = "ninjaFemaleBought";
    public static final int NINJA_FEMALE_NUMBER = 6;
    private static final String NINJA_MALE_BOUGHT = "ninjaMaleBought";
    public static final int NINJA_MALE_NUMBER = 5;
    private static final String PLAYER_BOUGHT = "playerBought";
    public static final int PLAYER_NUMBER = 0;
    private static final String POWER_UPS_UPGRADED_TIMES = "powerUpsUpgradedTimes";
    private static final String ROBOT_BOUGHT = "robotBought";
    public static final int ROBOT_NUMBER = 1;
    public static final String RUBY = "ruby";
    public static final String SHIELD_UPGRADED = "shieldUpgraded";
    public static final String SPAWN_RATE_COINS_UPGRADED = "spawnRateCoinsUpgraded";
    public static final String SPAWN_RATE_UPGRADED = "spawnRateUpgraded";
    private static final int UPGRADE_SHIELD_BUTTON = 7;

    private int abilityCoinRushCost;
    private int abilityMagnetCost;
    private ArrayList<AbilityPoint> abilityPointsCoinRush;
    private ArrayList<AbilityPoint> abilityPointsMagnet;
    private ArrayList<AbilityPoint> abilityPointsShield;
    private ArrayList<AbilityPoint> abilityPointsSpawnRate;
    private ArrayList<AbilityPoint> abilityPointsSpawnRateCoins;
    private int abilityShieldCost;
    private int abilitySpawnRateCoinsCost;
    private int abilitySpawnRateCost;
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

    private TextureAtlas.AtlasRegion xNotEnoughMoneyButton;

    Shop(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);
        prefs = Gdx.app.getPreferences("prefs");

        //Achievements achievementsObject = new Achievements();
        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        shopAtlas = manager.get("shop/shop.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);

        quitWindowTexture = sharedAtlas.findRegion("sure_quit_window");
        notQuitButtonTexture = sharedAtlas.findRegion("congratulations_window_x_button_unpressed");
        quitButtonTexture = mainGameAtlas.findRegion("story_continue_button_unpressed");
        xNotEnoughMoneyButton = sharedAtlas.findRegion("congratulations_window_x_button_unpressed");
        notEnoughMoneyWindow = shopAtlas.findRegion("not_enough_coins_window");

        coinGlobal = prefs.getInteger("coins");
        rubyGlobal = prefs.getInteger("ruby");
        shieldUpgraded = prefs.getInteger("shieldUpgraded", 0);
        abilityShieldCost = prefs.getInteger("abilityShieldCost", 500);
        magnetUpgraded = prefs.getInteger("magnetUpgraded", 0);
        abilityMagnetCost = prefs.getInteger("abilityMagnetCost", 500);
        coinRushUpgraded = prefs.getInteger("coinRushUpgraded", 0);
        abilityCoinRushCost = prefs.getInteger("abilityCoinRushCost", 500);
        spawnRateUpgraded = prefs.getInteger("spawnRateUpgraded", 0);
        abilitySpawnRateCost = prefs.getInteger("abilitySpawnRateCost", 500);
        spawnRateCoinsUpgraded = prefs.getInteger("spawnRateCoinsUpgraded", 0);
        abilitySpawnRateCoinsCost = prefs.getInteger("abilitySpawnRateCoinsCost", 500);


        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        costumeNames[0] = shopAtlas.findRegion("player_costume_name");
        costumeNames[1] = shopAtlas.findRegion("robot_costume_name");
        costumeNames[2] = shopAtlas.findRegion("knight_costume_name");
        costumeNames[3] = shopAtlas.findRegion("cowboy_costume_name");
        costumeNames[4] = shopAtlas.findRegion("cowgirl_costume_name");
        costumeNames[5] = shopAtlas.findRegion("ninja_male_costume_name");
        costumeNames[6] = shopAtlas.findRegion("ninja_female_costume_name");
        costumeNames[7] = shopAtlas.findRegion("dino_costume_name");
        costumeCosts[0] = mainGameAtlas.findRegion("transparent");
        costumeCosts[1] = shopAtlas.findRegion("robot_costume_cost");
        costumeCosts[2] = shopAtlas.findRegion("knight_costume_cost");
        costumeCosts[3] = shopAtlas.findRegion("cowboy_costume_cost");
        costumeCosts[4] = shopAtlas.findRegion("cowgirl_costume_cost");
        costumeCosts[5] = shopAtlas.findRegion("ninja_male_costume_cost");
        costumeCosts[6] = shopAtlas.findRegion("ninja_female_costume_cost");
        costumeCosts[7] = shopAtlas.findRegion("dino_costume_cost");
        costumeImages[0] = shopAtlas.findRegion("player_costume");
        costumeImages[1] = shopAtlas.findRegion("robot_costume");
        costumeImages[2] = shopAtlas.findRegion("knight_costume");
        costumeImages[3] = shopAtlas.findRegion("cowboy_costume");
        costumeImages[4] = shopAtlas.findRegion("cowgirl_costume");
        costumeImages[5] = shopAtlas.findRegion("ninja_male_costume");
        costumeImages[6] = shopAtlas.findRegion("ninja_female_costume");
        costumeImages[7] = shopAtlas.findRegion("dino_costume");
        prefs.putBoolean("playerBought", true);
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

        ImageButton.ImageButtonStyle imageButtonStyle5 = new ImageButton.ImageButtonStyle();
        imageButtonStyle5.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("shop_buy_button_unpressed")));
        imageButtonStyle5.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("shop_buy_button_pressed")));
        this.shield = mainGameAtlas.findRegion("shield_collectible");
        this.abilityPointsShield = new ArrayList<>();
        byte b;
        for (b = 0; b < 10; b++)
            this.abilityPointsShield.add(new AbilityPoint(this.shopAtlas));
        for (b = 0; b < this.shieldUpgraded; b++)
            this.abilityPointsShield.get(b).setFilled();
        ImageButton imageButton3 = new ImageButton(imageButtonStyle5);
        imageButton3.setPosition(worldXToScreenX(350.0F), worldYToScreenY(665.0F));
        imageButton3.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        imageButton3.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (Shop.this.coinGlobal >= Shop.this.abilityShieldCost) {
                    Shop.this.musicSoundsObject.playButtonBuy();
                    Shop.this.prefs.putInteger("coins", Shop.this.coinGlobal - Shop.this.abilityShieldCost);
                    Shop.this.prefs.putInteger("shieldUpgraded", Shop.this.shieldUpgraded + 1);
                    Shop.this.prefs.putInteger("abilityShieldCost", Shop.this.abilityShieldCost + 500);
                    Shop.this.prefs.putInteger("powerUpsUpgradedTimes", Shop.this.prefs.getInteger("powerUpsUpgradedTimes") + 1);
                    Shop.this.prefs.flush();
                    Shop shop = Shop.this;
                    Shop.access$702(shop, shop.prefs.getInteger("coins"));
                    shop = Shop.this;
                    Shop.access$1002(shop, shop.prefs.getInteger("shieldUpgraded", 0));
                    shop = Shop.this;
                    Shop.access$802(shop, shop.prefs.getInteger("abilityShieldCost", 500));
                    for (param1Int1 = 0; param1Int1 < Shop.this.shieldUpgraded; param1Int1++)
                        Shop.this.abilityPointsShield.get(param1Int1).setFilled();
                    Shop.this.drawAbilityElements();
                } else {
                    Shop.access$402(Shop.this, true);
                    Shop.this.stage.getActors().get(20).setVisible(true);
                    Shop.this.disableButtonsWindow();
                }
                if (Shop.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(Shop.this.prefs.getInteger("powerUpsUpgradedTimes", 0));
            }
        });
        this.magnet = mainGameAtlas.findRegion("coin_magnet");
        this.abilityPointsMagnet = new ArrayList<AbilityPoint>();
        for (b = 0; b < 10; b++)
            this.abilityPointsMagnet.add(new AbilityPoint(this.shopAtlas));
        for (b = 0; b < this.magnetUpgraded; b++)
            this.abilityPointsMagnet.get(b).setFilled();
        ImageButton imageButton8 = new ImageButton(imageButtonStyle5);
        imageButton8.setPosition(worldXToScreenX(350.0F), worldYToScreenY(530.0F));
        imageButton8.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        imageButton8.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (Shop.this.coinGlobal >= Shop.this.abilityMagnetCost) {
                    Shop.this.musicSoundsObject.playButtonBuy();
                    Shop.this.prefs.putInteger("coins", Shop.this.coinGlobal - Shop.this.abilityMagnetCost);
                    Shop.this.prefs.putInteger("magnetUpgraded", Shop.this.magnetUpgraded + 1);
                    Shop.this.prefs.putInteger("abilityMagnetCost", Shop.this.abilityMagnetCost + 500);
                    Shop.this.prefs.putInteger("powerUpsUpgradedTimes", Shop.this.prefs.getInteger("powerUpsUpgradedTimes") + 1);
                    Shop.this.prefs.flush();
                    Shop shop = Shop.this;
                    Shop.access$702(shop, shop.prefs.getInteger("coins"));
                    shop = Shop.this;
                    Shop.access$1402(shop, shop.prefs.getInteger("magnetUpgraded", 0));
                    shop = Shop.this;
                    Shop.access$1302(shop, shop.prefs.getInteger("abilityMagnetCost", 500));
                    for (param1Int1 = 0; param1Int1 < Shop.this.magnetUpgraded; param1Int1++)
                        Shop.this.abilityPointsMagnet.get(param1Int1).setFilled();
                    Shop.this.drawAbilityElements();
                } else {
                    Shop.access$402(Shop.this, true);
                    Shop.this.stage.getActors().get(20).setVisible(true);
                    Shop.this.disableButtonsWindow();
                }
                if (Shop.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(Shop.this.prefs.getInteger("powerUpsUpgradedTimes", 0));
            }
        });
        this.coinRush = mainGameAtlas.findRegion("coin_rush");
        this.abilityPointsCoinRush = new ArrayList<AbilityPoint>();
        for (b = 0; b < 10; b++)
            this.abilityPointsCoinRush.add(new AbilityPoint(this.shopAtlas));
        for (b = 0; b < this.coinRushUpgraded; b++)
            this.abilityPointsCoinRush.get(b).setFilled();
        ImageButton imageButton9 = new ImageButton(imageButtonStyle5);
        imageButton9.setPosition(worldXToScreenX(350.0F), worldYToScreenY(395.0F));
        imageButton9.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        imageButton9.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (Shop.this.coinGlobal >= Shop.this.abilityCoinRushCost) {
                    Shop.this.musicSoundsObject.playButtonBuy();
                    Shop.this.prefs.putInteger("coins", Shop.this.coinGlobal - Shop.this.abilityCoinRushCost);
                    Shop.this.prefs.putInteger("coinRushUpgraded", Shop.this.coinRushUpgraded + 1);
                    Shop.this.prefs.putInteger("abilityCoinRushCost", Shop.this.abilityCoinRushCost + 500);
                    Shop.this.prefs.putInteger("powerUpsUpgradedTimes", Shop.this.prefs.getInteger("powerUpsUpgradedTimes") + 1);
                    Shop.this.prefs.flush();
                    Shop shop = Shop.this;
                    Shop.access$702(shop, shop.prefs.getInteger("coins"));
                    shop = Shop.this;
                    Shop.access$1702(shop, shop.prefs.getInteger("coinRushUpgraded", 0));
                    shop = Shop.this;
                    Shop.access$1602(shop, shop.prefs.getInteger("abilityCoinRushCost", 500));
                    for (param1Int1 = 0; param1Int1 < Shop.this.coinRushUpgraded; param1Int1++)
                        Shop.this.abilityPointsCoinRush.get(param1Int1).setFilled();
                    Shop.this.drawAbilityElements();
                } else {
                    Shop.access$402(Shop.this, true);
                    Shop.this.stage.getActors().get(20).setVisible(true);
                    Shop.this.disableButtonsWindow();
                }
                if (Shop.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(Shop.this.prefs.getInteger("powerUpsUpgradedTimes", 0));
            }
        });
        this.spawnRate = this.shopAtlas.findRegion("pickup_spawn_icon");
        this.abilityPointsSpawnRate = new ArrayList<AbilityPoint>();
        for (b = 0; b < 10; b++)
            this.abilityPointsSpawnRate.add(new AbilityPoint(this.shopAtlas));
        for (b = 0; b < this.spawnRateUpgraded; b++)
            this.abilityPointsSpawnRate.get(b).setFilled();
        ImageButton imageButton10 = new ImageButton(imageButtonStyle5);
        imageButton10.setPosition(worldXToScreenX(350.0F), worldYToScreenY(260.0F));
        imageButton10.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        imageButton10.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (Shop.this.coinGlobal >= Shop.this.abilitySpawnRateCost) {
                    Shop.this.musicSoundsObject.playButtonBuy();
                    Shop.this.prefs.putInteger("coins", Shop.this.coinGlobal - Shop.this.abilitySpawnRateCost);
                    Shop.this.prefs.putInteger("spawnRateUpgraded", Shop.this.spawnRateUpgraded + 1);
                    Shop.this.prefs.putInteger("abilitySpawnRateCost", Shop.this.abilitySpawnRateCost + 500);
                    Shop.this.prefs.putInteger("powerUpsUpgradedTimes", Shop.this.prefs.getInteger("powerUpsUpgradedTimes") + 1);
                    Shop.this.prefs.flush();
                    Shop shop = Shop.this;
                    Shop.access$702(shop, shop.prefs.getInteger("coins"));
                    shop = Shop.this;
                    Shop.access$2002(shop, shop.prefs.getInteger("spawnRateUpgraded", 0));
                    shop = Shop.this;
                    Shop.access$1902(shop, shop.prefs.getInteger("abilitySpawnRateCost", 500));
                    for (param1Int1 = 0; param1Int1 < Shop.this.spawnRateUpgraded; param1Int1++)
                        Shop.this.abilityPointsSpawnRate.get(param1Int1).setFilled();
                    Shop.this.drawAbilityElements();
                } else {
                    Shop.access$402(Shop.this, true);
                    Shop.this.stage.getActors().get(20).setVisible(true);
                    Shop.this.disableButtonsWindow();
                }
                if (Shop.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(Shop.this.prefs.getInteger("powerUpsUpgradedTimes", 0));
            }
        });
        this.spawnRateCoins = this.shopAtlas.findRegion("coin_spawn_icon");
        this.abilityPointsSpawnRateCoins = new ArrayList<AbilityPoint>();
        for (b = 0; b < 10; b++)
            this.abilityPointsSpawnRateCoins.add(new AbilityPoint(this.shopAtlas));
        for (b = 0; b < this.spawnRateCoinsUpgraded; b++)
            this.abilityPointsSpawnRateCoins.get(b).setFilled();
        ImageButton imageButton7 = new ImageButton(imageButtonStyle5);
        imageButton7.setPosition(worldXToScreenX(350.0F), worldYToScreenY(125.0F));
        imageButton7.setSize(worldXToScreenX(110.0F), worldYToScreenY(40.0F));
        imageButton7.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (Shop.this.coinGlobal >= Shop.this.abilitySpawnRateCoinsCost) {
                    Shop.this.musicSoundsObject.playButtonBuy();
                    Shop.this.prefs.putInteger("coins", Shop.this.coinGlobal - Shop.this.abilitySpawnRateCoinsCost);
                    Shop.this.prefs.putInteger("spawnRateCoinsUpgraded", Shop.this.spawnRateCoinsUpgraded + 1);
                    Shop.this.prefs.putInteger("abilitySpawnRateCoinsCost", Shop.this.abilitySpawnRateCoinsCost + 500);
                    Shop.this.prefs.putInteger("powerUpsUpgradedTimes", Shop.this.prefs.getInteger("powerUpsUpgradedTimes") + 1);
                    Shop.this.prefs.flush();
                    Shop shop = Shop.this;
                    Shop.access$702(shop, shop.prefs.getInteger("coins"));
                    shop = Shop.this;
                    Shop.access$2302(shop, shop.prefs.getInteger("spawnRateCoinsUpgraded", 0));
                    shop = Shop.this;
                    Shop.access$2202(shop, shop.prefs.getInteger("abilitySpawnRateCoinsCost", 500));
                    for (param1Int1 = 0; param1Int1 < Shop.this.spawnRateCoinsUpgraded; param1Int1++)
                        Shop.this.abilityPointsSpawnRateCoins.get(param1Int1).setFilled();
                    Shop.this.drawAbilityElements();
                } else {
                    Shop.access$402(Shop.this, true);
                    Shop.this.stage.getActors().get(20).setVisible(true);
                    Shop.this.disableButtonsWindow();
                }
                if (Shop.this.prefs.getInteger("powerUpsUpgradedAtmAchievement", 0) < 12)
                    achievementsObject.checkPowerUpsUpgraded(Shop.this.prefs.getInteger("powerUpsUpgradedTimes", 0));
            }
        });
        Image image2 = new Image(this.shopAtlas.findRegion("costumes_window"));
        image2.setSize(worldXToScreenX(480.0F), worldYToScreenY(700.0F));
        image2.setPosition(worldXToScreenX(10.0F), worldYToScreenY(100.0F));
        ImageButton.ImageButtonStyle imageButtonStyle6 = new ImageButton.ImageButtonStyle();
        imageButtonStyle6.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costumes_button")));
        imageButtonStyle6.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costumes_button")));
        ImageButton imageButton11 = new ImageButton(imageButtonStyle6);
        imageButton11.setPosition(worldXToScreenX(190.0F), worldYToScreenY(790.0F));
        imageButton11.setSize(worldXToScreenX(120.0F), worldYToScreenY(120.0F));
        imageButton11.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.access$002(Shop.this, false);
                Shop.access$102(Shop.this, false);
                Shop.this.drawAbilityElements();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        Image image6 = new Image(this.costumeNames[0]);
        this.costumeName = image6;
        image6.setSize(worldXToScreenX(210.0F), worldYToScreenY(75.0F));
        this.costumeName.setPosition(worldXToScreenX(145.0F), worldYToScreenY(675.0F));
        image6 = new Image(this.costumeCosts[0]);
        this.costumeCost = image6;
        image6.setSize(worldXToScreenX(120.0F), worldYToScreenY(50.0F));
        this.costumeCost.setPosition(worldXToScreenX(190.0F), worldYToScreenY(575.0F));
        image6 = new Image(this.costumeImages[0]);
        this.costumeImage = image6;
        image6.setSize(worldXToScreenX(120.0F), worldYToScreenY(200.0F));
        this.costumeImage.setPosition(worldXToScreenX(185.0F), worldYToScreenY(325.0F));
        ImageButton.ImageButtonStyle imageButtonStyle7 = new ImageButton.ImageButtonStyle();
        imageButtonStyle7.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_left_button_unpressed")));
        imageButtonStyle7.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_left_button_pressed")));
        ImageButton imageButton12 = new ImageButton(imageButtonStyle7);
        imageButton12.setPosition(worldXToScreenX(30.0F), worldYToScreenY(412.5F));
        imageButton12.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton12.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.prefs.putInteger("costumeSelected", Shop.this.prefs.getInteger("costumeSelected") - 1);
                Shop.this.prefs.flush();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle8 = new ImageButton.ImageButtonStyle();
        imageButtonStyle8.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_right_button_unpressed")));
        imageButtonStyle8.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_right_button_pressed")));
        ImageButton imageButton13 = new ImageButton(imageButtonStyle8);
        imageButton13.setPosition(worldXToScreenX(395.0F), worldYToScreenY(412.5F));
        imageButton13.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton13.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.prefs.putInteger("costumeSelected", Shop.this.prefs.getInteger("costumeSelected") + 1);
                Shop.this.prefs.flush();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle9 = new ImageButton.ImageButtonStyle();
        this.costumeBuyButtonStyle = imageButtonStyle9;
        imageButtonStyle9.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_buy_button_unpressed")));
        this.costumeBuyButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_buy_button_pressed")));
        imageButtonStyle9 = new ImageButton.ImageButtonStyle();
        this.costumeSelectButtonStyle = imageButtonStyle9;
        imageButtonStyle9.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_select_button_unpressed")));
        this.costumeSelectButtonStyle.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_select_button_pressed")));
        ImageButton imageButton14 = new ImageButton(this.costumeBuyButtonStyle);
        this.costumeBuySelectButton = imageButton14;
        imageButton14.setPosition(worldXToScreenX(90.0F), worldYToScreenY(150.0F));
        this.costumeBuySelectButton.setSize(worldXToScreenX(320.0F), worldYToScreenY(125.0F));
        this.costumeBuySelectButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.musicSoundsObject.playButtonClick();
                if (Shop.this.prefs.getInteger("costumeSelected") == 0) {
                    if (Shop.this.prefs.getBoolean("playerBought")) {
                        Shop.this.prefs.putInteger("costumeSelectedGame", 0);
                        Shop.this.prefs.flush();
                    }
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 1) {
                    Shop.this.costumeBuySelectButton("robotBought", 1, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 2) {
                    Shop.this.costumeBuySelectButton("knightBought", 2, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 3) {
                    Shop.this.costumeBuySelectButton("cowboyBought", 3, "coins", 1000);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 4) {
                    Shop.this.costumeBuySelectButton("cowgirlBought", 4, "coins", 1000);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 5) {
                    Shop.this.costumeBuySelectButton("ninjaMaleBought", 5, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 6) {
                    Shop.this.costumeBuySelectButton("ninjaFemaleBought", 6, "ruby", 500);
                } else {
                    Shop.this.prefs.getInteger("costumeSelected");
                }
            }
        });
        Image image7 = new Image(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_selected"))));
        image7.setSize(worldXToScreenX(425.0F), worldYToScreenY(125.0F));
        image7.setPosition(worldXToScreenX(35.5F), worldYToScreenY(150.0F));
        Image image8 = new Image(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_buy_sure_window"))));
        image8.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        image8.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));
        imageButton14 = new ImageButton(xButtonStyle);
        imageButton14.setPosition(worldXToScreenX(120.0F), worldYToScreenY(420.0F));
        imageButton14.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton14.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.stage.getActors().get(21).setVisible(false);
                Shop.this.stage.getActors().get(22).setVisible(false);
                Shop.this.stage.getActors().get(23).setVisible(false);
                Shop.this.enableButtonsWindow();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle10 = new ImageButton.ImageButtonStyle();
        imageButtonStyle10.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        imageButtonStyle10.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));
        ImageButton imageButton16 = new ImageButton(imageButtonStyle10);
        imageButton16.setPosition(worldXToScreenX(305.0F), worldYToScreenY(420.0F));
        imageButton16.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton16.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.musicSoundsObject.playButtonBuy();
                if (Shop.this.prefs.getInteger("costumeSelected") == 1) {
                    Shop.this.costumeBuyButton("robotBought", 1, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 2) {
                    Shop.this.costumeBuyButton("knightBought", 2, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 3) {
                    Shop.this.costumeBuyButton("cowboyBought", 3, "coins", 1000);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 4) {
                    Shop.this.costumeBuyButton("cowgirlBought", 4, "coins", 1000);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 5) {
                    Shop.this.costumeBuyButton("ninjaMaleBought", 5, "ruby", 500);
                } else if (Shop.this.prefs.getInteger("costumeSelected") == 6) {
                    Shop.this.costumeBuyButton("ninjaFemaleBought", 6, "ruby", 500);
                } else {
                    Shop.this.prefs.getInteger("costumeSelected");
                }
                Shop.this.stage.getActors().get(21).setVisible(false);
                Shop.this.stage.getActors().get(22).setVisible(false);
                Shop.this.stage.getActors().get(23).setVisible(false);
                Shop.this.enableButtonsWindow();
            }
        });
        Image image9 = new Image(this.shopAtlas.findRegion("currency_window"));
        image9.setSize(worldXToScreenX(480.0F), worldYToScreenY(700.0F));
        image9.setPosition(worldXToScreenX(10.0F), worldYToScreenY(100.0F));
        ImageButton.ImageButtonStyle imageButtonStyle11 = new ImageButton.ImageButtonStyle();
        imageButtonStyle11.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("currency_button")));
        imageButtonStyle11.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("currency_button")));
        ImageButton imageButton17 = new ImageButton(imageButtonStyle11);
        imageButton17.setPosition(worldXToScreenX(320.0F), worldYToScreenY(790.0F));
        imageButton17.setSize(worldXToScreenX(120.0F), worldYToScreenY(120.0F));
        imageButton17.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.access$002(Shop.this, false);
                Shop.access$102(Shop.this, true);
                Shop.this.drawAbilityElements();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle12 = new ImageButton.ImageButtonStyle();
        imageButtonStyle12.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("100_diamonds_button_unpressed")));
        imageButtonStyle12.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("100_diamonds_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle13 = new ImageButton.ImageButtonStyle();
        imageButtonStyle13.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("300_diamonds_button_unpressed")));
        imageButtonStyle13.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("300_diamonds_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle14 = new ImageButton.ImageButtonStyle();
        imageButtonStyle14.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("500_diamonds_button_unpressed")));
        imageButtonStyle14.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("500_diamonds_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle15 = new ImageButton.ImageButtonStyle();
        imageButtonStyle15.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("1500_diamonds_button_unpressed")));
        imageButtonStyle15.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("1500_diamonds_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle16 = new ImageButton.ImageButtonStyle();
        imageButtonStyle16.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("099_button_unpressed")));
        imageButtonStyle16.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("099_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle17 = new ImageButton.ImageButtonStyle();
        imageButtonStyle17.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("249_button_unpressed")));
        imageButtonStyle17.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("249_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle18 = new ImageButton.ImageButtonStyle();
        imageButtonStyle18.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("399_button_unpressed")));
        imageButtonStyle18.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("399_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle19 = new ImageButton.ImageButtonStyle();
        imageButtonStyle19.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("1499_button_unpressed")));
        imageButtonStyle19.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("1499_button_pressed")));
        ImageButton.ImageButtonStyle imageButtonStyle20 = new ImageButton.ImageButtonStyle();
        imageButtonStyle20.up = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("2499_button_unpressed")));
        imageButtonStyle20.down = new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("2499_button_pressed")));
        ImageButton imageButton18 = new ImageButton(imageButtonStyle12);
        imageButton18.setPosition(worldXToScreenX(350.0F), worldYToScreenY(737.0F));
        imageButton18.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton18.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.checkBuyCoins(100, 0);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton19 = new ImageButton(imageButtonStyle13);
        imageButton19.setPosition(worldXToScreenX(350.0F), worldYToScreenY(669.0F));
        imageButton19.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton19.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.checkBuyCoins(300, 1);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton27 = new ImageButton(imageButtonStyle14);
        imageButton27.setPosition(worldXToScreenX(350.0F), worldYToScreenY(599.0F));
        imageButton27.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton27.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.checkBuyCoins(500, 2);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton21 = new ImageButton(imageButtonStyle15);
        imageButton21.setPosition(worldXToScreenX(350.0F), worldYToScreenY(529.0F));
        imageButton21.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton21.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.checkBuyCoins(1500, 3);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton20 = new ImageButton(imageButtonStyle14);
        imageButton20.setPosition(worldXToScreenX(350.0F), worldYToScreenY(459.0F));
        imageButton20.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton20.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.checkBuyCoins(500, 4);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton22 = new ImageButton(imageButtonStyle16);
        imageButton22.setPosition(worldXToScreenX(350.0F), worldYToScreenY(388.0F));
        imageButton22.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton22.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });
        ImageButton imageButton23 = new ImageButton(imageButtonStyle17);
        imageButton23.setPosition(worldXToScreenX(350.0F), worldYToScreenY(321.0F));
        imageButton23.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton23.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });
        ImageButton imageButton24 = new ImageButton(imageButtonStyle18);
        imageButton24.setPosition(worldXToScreenX(350.0F), worldYToScreenY(251.0F));
        imageButton24.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton24.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });
        ImageButton imageButton25 = new ImageButton(imageButtonStyle19);
        imageButton25.setPosition(worldXToScreenX(350.0F), worldYToScreenY(181.0F));
        imageButton25.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton25.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });
        ImageButton imageButton26 = new ImageButton(imageButtonStyle20);
        imageButton26.setPosition(worldXToScreenX(350.0F), worldYToScreenY(114.0F));
        imageButton26.setSize(worldXToScreenX(110.0F), worldYToScreenY(45.0F));
        imageButton26.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });
        Image image10 = new Image(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("costume_buy_sure_window"))));
        image10.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        image10.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));
        ImageButton imageButton28 = new ImageButton(xButtonStyle);
        imageButton28.setPosition(worldXToScreenX(120.0F), worldYToScreenY(420.0F));
        imageButton28.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton28.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.stage.getActors().get(35).setVisible(false);
                Shop.this.stage.getActors().get(36).setVisible(false);
                Shop.this.stage.getActors().get(37).setVisible(false);
                Shop.this.enableButtonsWindow();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton imageButton15 = new ImageButton(imageButtonStyle10);
        imageButton15.setPosition(worldXToScreenX(305.0F), worldYToScreenY(420.0F));
        imageButton15.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton15.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.musicSoundsObject.playButtonBuy();
                if (Shop.this.currencySelected == 0) {
                    Shop.this.buyCoins(1000, 100);
                } else if (Shop.this.currencySelected == 1) {
                    Shop.this.buyCoins(5000, 300);
                } else if (Shop.this.currencySelected == 2) {
                    Shop.this.buyCoins(10000, 500);
                } else if (Shop.this.currencySelected == 3) {
                    Shop.this.buyCoins(50000, 1500);
                } else if (Shop.this.currencySelected == 4) {
                    Shop.this.prefs.putInteger("ruby", Shop.this.prefs.getInteger("ruby") - 500);
                    Shop.this.prefs.putBoolean("doubleCoins", true);
                    Shop.this.prefs.flush();
                    Shop.this.drawAbilityElements();
                }
                Shop.this.stage.getActors().get(35).setVisible(false);
                Shop.this.stage.getActors().get(36).setVisible(false);
                Shop.this.stage.getActors().get(37).setVisible(false);
                Shop.this.enableButtonsWindow();
            }
        });
        Image image11 = new Image(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("not_enough_diamonds_window"))));
        image11.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        image11.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));
        ImageButton imageButton5 = new ImageButton(xButtonStyle);
        imageButton5.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
        imageButton5.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        imageButton5.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.stage.getActors().get(38).setVisible(false);
                Shop.this.stage.getActors().get(39).setVisible(false);
                Shop.this.enableButtonsWindow();
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle21 = new ImageButton.ImageButtonStyle();
        imageButtonStyle21.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        imageButtonStyle21.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));
        ImageButton imageButton29 = new ImageButton(imageButtonStyle21);
        imageButton29.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        imageButton29.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton29.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.musicSoundsObject.playButtonClick();
                Shop.this.prefs.putInteger("costumeSelected", Shop.this.prefs.getInteger("costumeSelectedGame"));
                Shop.this.prefs.flush();
                GameStateManager gameStateManager = gsm;
                gameStateManager.set(new MainMenu(gameStateManager, adsController, manager));
                Shop.this.dispose();
            }
        });
        Image image1 = new Image(sharedAtlas.findRegion("sure_quit_window"));
        image1.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
        image1.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));
        ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
        imageButtonStyle1.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        imageButtonStyle1.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));
        ImageButton imageButton1 = new ImageButton(imageButtonStyle1);
        imageButton1.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
        imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton1.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.stage.getActors().get(40).setVisible(false);
                Shop.this.stage.getActors().get(41).setVisible(false);
                Shop.this.stage.getActors().get(42).setVisible(false);
                Shop.access$3002(Shop.this, false);
                Shop.this.musicSoundsObject.playButtonClick();
            }
        });
        ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
        imageButtonStyle2.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        imageButtonStyle2.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));
        ImageButton imageButton2 = new ImageButton(imageButtonStyle2);
        imageButton2.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
        imageButton2.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        imageButton2.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                Shop.this.musicSoundsObject.playButtonClick();
                Gdx.app.exit();
            }
        });
        this.stage.addActor(bg);
        this.stage.addActor(powerUpsButton);
        this.stage.addActor(imageButton11);
        this.stage.addActor(imageButton29);
        this.stage.addActor(imageButton17);
        this.stage.addActor(powerUpsWindow);
        this.stage.addActor(image2);
        this.stage.addActor(imageButton3);
        this.stage.addActor(imageButton8);
        this.stage.addActor(imageButton9);
        this.stage.addActor(imageButton10);
        this.stage.addActor(imageButton7);
        this.stage.addActor(this.costumeName);
        this.stage.addActor(this.costumeCost);
        this.stage.addActor(this.costumeImage);
        this.stage.addActor(imageButton12);
        this.stage.addActor(imageButton13);
        this.stage.addActor(this.costumeBuySelectButton);
        this.stage.addActor(image7);
        this.stage.addActor(this.notEnoughCoinsDiamondsWindow);
        this.stage.addActor(xButtonNotEnoughMoney);
        this.stage.addActor(image8);
        this.stage.addActor(imageButton14);
        this.stage.addActor(imageButton16);
        this.stage.addActor(image9);
        this.stage.addActor(imageButton18);
        this.stage.addActor(imageButton19);
        this.stage.addActor(imageButton27);
        this.stage.addActor(imageButton21);
        this.stage.addActor(imageButton22);
        this.stage.addActor(imageButton23);
        this.stage.addActor(imageButton24);
        this.stage.addActor(imageButton25);
        this.stage.addActor(imageButton26);
        this.stage.addActor(imageButton20);
        this.stage.addActor(image10);
        this.stage.addActor(imageButton15);
        this.stage.addActor(imageButton28);
        this.stage.addActor(image11);
        this.stage.addActor(imageButton5);
        this.stage.addActor(image1);
        this.stage.addActor(imageButton1);
        this.stage.addActor(imageButton2);
        this.stage.getActors().get(15).setVisible(false);
        this.stage.getActors().get(16).setVisible(false);
        this.stage.getActors().get(17).setVisible(false);
        this.stage.getActors().get(18).setVisible(false);
        this.stage.getActors().get(19).setVisible(false);
        this.stage.getActors().get(20).setVisible(false);
        this.stage.getActors().get(21).setVisible(false);
        this.stage.getActors().get(22).setVisible(false);
        this.stage.getActors().get(23).setVisible(false);
        this.stage.getActors().get(24).setVisible(false);
        this.stage.getActors().get(25).setVisible(false);
        this.stage.getActors().get(26).setVisible(false);
        this.stage.getActors().get(27).setVisible(false);
        this.stage.getActors().get(28).setVisible(false);
        this.stage.getActors().get(29).setVisible(false);
        this.stage.getActors().get(30).setVisible(false);
        this.stage.getActors().get(31).setVisible(false);
        this.stage.getActors().get(32).setVisible(false);
        this.stage.getActors().get(33).setVisible(false);
        this.stage.getActors().get(34).setVisible(false);
        this.stage.getActors().get(35).setVisible(false);
        this.stage.getActors().get(36).setVisible(false);
        this.stage.getActors().get(37).setVisible(false);
        this.stage.getActors().get(38).setVisible(false);
        this.stage.getActors().get(39).setVisible(false);
        this.stage.getActors().get(40).setVisible(false);
        this.stage.getActors().get(41).setVisible(false);
        this.stage.getActors().get(42).setVisible(false);
        this.drawAbilities = true;
        drawAbilityElements();
    }

    private void buyCoins(int paramInt1, int paramInt2) {
        Preferences preferences = this.prefs;
        preferences.putInteger("ruby", preferences.getInteger("ruby") - paramInt2);
        preferences = this.prefs;
        preferences.putInteger("coins", preferences.getInteger("coins") + paramInt1);
        this.prefs.flush();
        this.coinGlobal = this.prefs.getInteger("coins");
        this.rubyGlobal = this.prefs.getInteger("ruby");
        disableButtonsWindow();
    }

    private void checkBuyCoins(int paramInt1, int paramInt2) {
        if (this.prefs.getInteger("ruby") >= paramInt1) {
            this.stage.getActors().get(35).setVisible(true);
            this.stage.getActors().get(36).setVisible(true);
            this.stage.getActors().get(37).setVisible(true);
            this.currencySelected = paramInt2;
        } else {
            this.notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("not_enough_diamonds_window"))));
            this.stage.getActors().get(38).setVisible(true);
            this.stage.getActors().get(39).setVisible(true);
        }
        disableButtonsWindow();
    }

    private void costumeBuyButton(String paramString1, int paramInt1, String paramString2, int paramInt2) {
        this.prefs.putBoolean(paramString1, true);
        this.prefs.putInteger("costumeSelectedGame", paramInt1);
        Preferences preferences = this.prefs;
        preferences.putInteger(paramString2, preferences.getInteger(paramString2) - paramInt2);
        this.prefs.flush();
        this.coinGlobal = this.prefs.getInteger("coins");
        this.rubyGlobal = this.prefs.getInteger("ruby");
    }

    private void costumeBuySelectButton(String paramString1, int paramInt1, String paramString2, int paramInt2) {
        if (this.prefs.getBoolean(paramString1, false)) {
            this.prefs.putInteger("costumeSelectedGame", paramInt1);
            this.prefs.flush();
        } else if (this.prefs.getInteger(paramString2) >= paramInt2) {
            disableButtonsWindow();
            this.stage.getActors().get(21).setVisible(true);
            this.stage.getActors().get(22).setVisible(true);
            this.stage.getActors().get(23).setVisible(true);
        } else {
            disableButtonsWindow();
            if (paramString2.equals("coins")) {
                this.notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("not_enough_coins_window"))));
            } else {
                this.notEnoughCoinsDiamondsWindow.setDrawable(new TextureRegionDrawable(new TextureRegion(this.shopAtlas.findRegion("not_enough_diamonds_window"))));
            }
            this.stage.getActors().get(19).setVisible(true);
            this.stage.getActors().get(20).setVisible(true);
        }
    }

    private void costumeSelected(String paramString, int paramInt) {
        if (this.prefs.getBoolean(paramString, false)) {
            this.costumeBuySelectButton.setStyle(this.costumeSelectButtonStyle);
            this.stage.getActors().get(13).setVisible(false);
        } else {
            this.costumeBuySelectButton.setStyle(this.costumeBuyButtonStyle);
            this.stage.getActors().get(13).setVisible(true);
        }
        if (this.prefs.getInteger("costumeSelectedGame", 0) == paramInt) {
            this.stage.getActors().get(17).setVisible(false);
            this.stage.getActors().get(18).setVisible(true);
        } else {
            this.stage.getActors().get(17).setVisible(true);
            this.stage.getActors().get(18).setVisible(false);
        }
    }

    private void disableButtonsWindow() {
        this.stage.getActors().get(1).setTouchable(Touchable.disabled);
        this.stage.getActors().get(2).setTouchable(Touchable.disabled);
        this.stage.getActors().get(3).setTouchable(Touchable.disabled);
        this.stage.getActors().get(4).setTouchable(Touchable.disabled);
        this.stage.getActors().get(7).setTouchable(Touchable.disabled);
        this.stage.getActors().get(8).setTouchable(Touchable.disabled);
        this.stage.getActors().get(9).setTouchable(Touchable.disabled);
        this.stage.getActors().get(10).setTouchable(Touchable.disabled);
        this.stage.getActors().get(11).setTouchable(Touchable.disabled);
        this.stage.getActors().get(15).setTouchable(Touchable.disabled);
        this.stage.getActors().get(16).setTouchable(Touchable.disabled);
        this.stage.getActors().get(17).setTouchable(Touchable.disabled);
        this.stage.getActors().get(25).setTouchable(Touchable.disabled);
        this.stage.getActors().get(26).setTouchable(Touchable.disabled);
        this.stage.getActors().get(27).setTouchable(Touchable.disabled);
        this.stage.getActors().get(28).setTouchable(Touchable.disabled);
        this.stage.getActors().get(29).setTouchable(Touchable.disabled);
        this.stage.getActors().get(30).setTouchable(Touchable.disabled);
        this.stage.getActors().get(31).setTouchable(Touchable.disabled);
        this.stage.getActors().get(32).setTouchable(Touchable.disabled);
        this.stage.getActors().get(33).setTouchable(Touchable.disabled);
        this.stage.getActors().get(34).setTouchable(Touchable.disabled);
        this.stage.getActors().get(35).setTouchable(Touchable.disabled);
    }

    private void drawAbilityElements() {
        if (this.drawAbilities) {
            this.stage.getActors().get(5).setVisible(true);
            if (this.shieldUpgraded != 10) {
                this.stage.getActors().get(7).setVisible(true);
            } else {
                this.stage.getActors().get(7).setVisible(false);
            }
            if (this.magnetUpgraded != 10) {
                this.stage.getActors().get(8).setVisible(true);
            } else {
                this.stage.getActors().get(8).setVisible(false);
            }
            if (this.coinRushUpgraded != 10) {
                this.stage.getActors().get(9).setVisible(true);
            } else {
                this.stage.getActors().get(9).setVisible(false);
            }
            if (this.spawnRateUpgraded != 10) {
                this.stage.getActors().get(10).setVisible(true);
            } else {
                this.stage.getActors().get(10).setVisible(false);
            }
            if (this.spawnRateCoinsUpgraded != 10) {
                this.stage.getActors().get(11).setVisible(true);
            } else {
                this.stage.getActors().get(11).setVisible(false);
            }
            this.stage.getActors().get(6).setVisible(false);
            this.stage.getActors().get(12).setVisible(false);
            this.stage.getActors().get(13).setVisible(false);
            this.stage.getActors().get(14).setVisible(false);
            this.stage.getActors().get(15).setVisible(false);
            this.stage.getActors().get(16).setVisible(false);
            this.stage.getActors().get(17).setVisible(false);
            this.stage.getActors().get(18).setVisible(false);
            this.stage.getActors().get(24).setVisible(false);
            this.stage.getActors().get(25).setVisible(false);
            this.stage.getActors().get(26).setVisible(false);
            this.stage.getActors().get(27).setVisible(false);
            this.stage.getActors().get(28).setVisible(false);
            this.stage.getActors().get(29).setVisible(false);
            this.stage.getActors().get(30).setVisible(false);
            this.stage.getActors().get(31).setVisible(false);
            this.stage.getActors().get(32).setVisible(false);
            this.stage.getActors().get(33).setVisible(false);
            this.stage.getActors().get(34).setVisible(false);
        } else if (this.drawCurrency) {
            this.stage.getActors().get(24).setVisible(true);
            this.stage.getActors().get(25).setVisible(true);
            this.stage.getActors().get(26).setVisible(true);
            this.stage.getActors().get(27).setVisible(true);
            this.stage.getActors().get(28).setVisible(true);
            this.stage.getActors().get(29).setVisible(true);
            this.stage.getActors().get(30).setVisible(true);
            this.stage.getActors().get(31).setVisible(true);
            this.stage.getActors().get(32).setVisible(true);
            this.stage.getActors().get(33).setVisible(true);
            if (this.prefs.getBoolean("doubleCoins", false)) {
                this.stage.getActors().get(34).setVisible(false);
            } else {
                this.stage.getActors().get(34).setVisible(true);
            }
        } else {
            this.stage.getActors().get(5).setVisible(false);
            this.stage.getActors().get(6).setVisible(true);
            this.stage.getActors().get(7).setVisible(false);
            this.stage.getActors().get(8).setVisible(false);
            this.stage.getActors().get(9).setVisible(false);
            this.stage.getActors().get(10).setVisible(false);
            this.stage.getActors().get(11).setVisible(false);
            this.stage.getActors().get(12).setVisible(true);
            this.stage.getActors().get(13).setVisible(true);
            this.stage.getActors().get(14).setVisible(true);
            this.stage.getActors().get(24).setVisible(false);
            this.stage.getActors().get(25).setVisible(false);
            this.stage.getActors().get(26).setVisible(false);
            this.stage.getActors().get(27).setVisible(false);
            this.stage.getActors().get(28).setVisible(false);
            this.stage.getActors().get(29).setVisible(false);
            this.stage.getActors().get(30).setVisible(false);
            this.stage.getActors().get(31).setVisible(false);
            this.stage.getActors().get(32).setVisible(false);
            this.stage.getActors().get(33).setVisible(false);
            this.stage.getActors().get(34).setVisible(false);
        }
    }

    private void drawAllAbilities(SpriteBatch paramSpriteBatch) {
        for (byte b = 0; b < 10; b++) {
            float f = (b * 20 + 150);
            drawShieldAbility(paramSpriteBatch, f, b);
            drawMagnetAbility(paramSpriteBatch, f, b);
            drawCoinRushAbility(paramSpriteBatch, f, b);
            drawSpawnRateAbility(paramSpriteBatch, f, b);
            drawSpawnRateCoinsAbility(paramSpriteBatch, f, b);
        }
    }

    private void drawCoinRushAbility(SpriteBatch paramSpriteBatch, float paramFloat, int paramInt) {
        if (this.abilityPointsCoinRush.get(paramInt).isFilled()) {
            paramSpriteBatch.draw(this.abilityPointsCoinRush.get(paramInt).getAbilityPoint(1), worldXToScreenX(paramFloat), worldYToScreenY(395.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            paramSpriteBatch.draw(this.abilityPointsCoinRush.get(paramInt).getAbilityPoint(0), worldXToScreenX(paramFloat), worldYToScreenY(395.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawMagnetAbility(SpriteBatch paramSpriteBatch, float paramFloat, int paramInt) {
        if (this.abilityPointsMagnet.get(paramInt).isFilled()) {
            paramSpriteBatch.draw(this.abilityPointsMagnet.get(paramInt).getAbilityPoint(1), worldXToScreenX(paramFloat), worldYToScreenY(530.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            paramSpriteBatch.draw(this.abilityPointsMagnet.get(paramInt).getAbilityPoint(0), worldXToScreenX(paramFloat), worldYToScreenY(530.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawShieldAbility(SpriteBatch paramSpriteBatch, float paramFloat, int paramInt) {
        if (this.abilityPointsShield.get(paramInt).isFilled()) {
            paramSpriteBatch.draw(this.abilityPointsShield.get(paramInt).getAbilityPoint(1), worldXToScreenX(paramFloat), worldYToScreenY(665.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            paramSpriteBatch.draw(this.abilityPointsShield.get(paramInt).getAbilityPoint(0), worldXToScreenX(paramFloat), worldYToScreenY(665.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawSpawnRateAbility(SpriteBatch paramSpriteBatch, float paramFloat, int paramInt) {
        if (this.abilityPointsSpawnRate.get(paramInt).isFilled()) {
            paramSpriteBatch.draw(this.abilityPointsSpawnRate.get(paramInt).getAbilityPoint(1), worldXToScreenX(paramFloat), worldYToScreenY(260.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            paramSpriteBatch.draw(this.abilityPointsSpawnRate.get(paramInt).getAbilityPoint(0), worldXToScreenX(paramFloat), worldYToScreenY(260.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void drawSpawnRateCoinsAbility(SpriteBatch paramSpriteBatch, float paramFloat, int paramInt) {
        if (this.abilityPointsSpawnRateCoins.get(paramInt).isFilled()) {
            paramSpriteBatch.draw(this.abilityPointsSpawnRateCoins.get(paramInt).getAbilityPoint(1), worldXToScreenX(paramFloat), worldYToScreenY(125.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        } else {
            paramSpriteBatch.draw(this.abilityPointsSpawnRateCoins.get(paramInt).getAbilityPoint(0), worldXToScreenX(paramFloat), worldYToScreenY(125.0F), worldXToScreenX(10.0F), worldYToScreenY(40.0F));
        }
    }

    private void enableButtonsWindow() {
        this.stage.getActors().get(1).setTouchable(Touchable.enabled);
        this.stage.getActors().get(2).setTouchable(Touchable.enabled);
        this.stage.getActors().get(3).setTouchable(Touchable.enabled);
        this.stage.getActors().get(4).setTouchable(Touchable.enabled);
        this.stage.getActors().get(7).setTouchable(Touchable.enabled);
        this.stage.getActors().get(8).setTouchable(Touchable.enabled);
        this.stage.getActors().get(9).setTouchable(Touchable.enabled);
        this.stage.getActors().get(10).setTouchable(Touchable.enabled);
        this.stage.getActors().get(11).setTouchable(Touchable.enabled);
        this.stage.getActors().get(15).setTouchable(Touchable.enabled);
        this.stage.getActors().get(16).setTouchable(Touchable.enabled);
        this.stage.getActors().get(17).setTouchable(Touchable.enabled);
        this.stage.getActors().get(25).setTouchable(Touchable.enabled);
        this.stage.getActors().get(26).setTouchable(Touchable.enabled);
        this.stage.getActors().get(27).setTouchable(Touchable.enabled);
        this.stage.getActors().get(28).setTouchable(Touchable.enabled);
        this.stage.getActors().get(29).setTouchable(Touchable.enabled);
        this.stage.getActors().get(30).setTouchable(Touchable.enabled);
        this.stage.getActors().get(31).setTouchable(Touchable.enabled);
        this.stage.getActors().get(32).setTouchable(Touchable.enabled);
        this.stage.getActors().get(33).setTouchable(Touchable.enabled);
        this.stage.getActors().get(34).setTouchable(Touchable.enabled);
        this.stage.getActors().get(35).setTouchable(Touchable.enabled);
    }

    private void setCostumeParameters(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7) {
        this.costumeName.setDrawable(new TextureRegionDrawable(new TextureRegion(this.costumeNames[paramInt1])));
        this.costumeName.setSize(worldXToScreenX(paramInt2), worldYToScreenY(75.0F));
        this.costumeName.setPosition(worldXToScreenX(paramInt3), worldYToScreenY(675.0F));
        this.costumeCost.setDrawable(new TextureRegionDrawable(new TextureRegion(this.costumeCosts[paramInt1])));
        this.costumeCost.setSize(worldXToScreenX(paramInt4), worldYToScreenY(50.0F));
        this.costumeCost.setPosition(worldXToScreenX(paramInt5), worldYToScreenY(575.0F));
        this.costumeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(this.costumeImages[paramInt1])));
        this.costumeImage.setSize(worldXToScreenX(paramInt6), worldYToScreenY(200.0F));
        this.costumeImage.setPosition(worldXToScreenX(paramInt7), worldYToScreenY(325.0F));
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

    public void render(SpriteBatch paramSpriteBatch) {
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            this.stage.getActors().get(40).setVisible(true);
            this.stage.getActors().get(41).setVisible(true);
            this.stage.getActors().get(42).setVisible(true);
            this.drawQuitGame = true;
        }
        if (!this.drawAbilities) {
            if (this.prefs.getInteger("costumeSelected", 0) == 0) {
                this.stage.getActors().get(15).setVisible(false);
                setCostumeParameters(0, 210, 145, 120, 190, 120, 190);
                costumeSelected("playerBought", 0);
            } else {
                this.stage.getActors().get(15).setVisible(true);
            }
            if (this.prefs.getInteger("costumeSelected") == 1) {
                setCostumeParameters(1, 175, 162, 130, 185, 206, 147);
                costumeSelected("robotBought", 1);
            }
            if (this.prefs.getInteger("costumeSelected") == 2) {
                setCostumeParameters(2, 255, 123, 130, 185, 196, 152);
                costumeSelected("knightBought", 2);
            }
            if (this.prefs.getInteger("costumeSelected") == 3) {
                setCostumeParameters(3, 123, 188, 135, 182, 130, 185);
                costumeSelected("cowboyBought", 3);
            }
            if (this.prefs.getInteger("costumeSelected") == 4) {
                setCostumeParameters(4, 213, 143, 135, 182, 124, 188);
                costumeSelected("cowgirlBought", 4);
            }
            if (this.prefs.getInteger("costumeSelected") == 5) {
                setCostumeParameters(5, 160, 170, 130, 185, 193, 153);
                costumeSelected("ninjaMaleBought", 5);
            }
            if (this.prefs.getInteger("costumeSelected") == 6) {
                setCostumeParameters(6, 150, 185, 130, 185, 198, 151);
                costumeSelected("ninjaFemaleBought", 6);
            }
            if (this.prefs.getInteger("costumeSelected") == 7) {
                this.stage.getActors().get(16).setVisible(false);
                setCostumeParameters(7, 155, 172, 114, 193, 289, 105);
                costumeSelected("dinoBought", 7);
            } else {
                this.stage.getActors().get(16).setVisible(true);
            }
        }
        paramSpriteBatch.begin();
        paramSpriteBatch.draw(this.coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        this.coinAndDiamondFont.draw(paramSpriteBatch, String.valueOf(this.coinGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
        paramSpriteBatch.draw(this.diamond, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
        this.coinAndDiamondFont.draw(paramSpriteBatch, String.valueOf(this.rubyGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));
        if (this.drawAbilities) {
            paramSpriteBatch.draw(this.shield, worldXToScreenX(50.0F), worldYToScreenY(672.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (this.shieldUpgraded != 10)
                this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.abilityShieldCost), worldXToScreenX(380.0F), worldYToScreenY(695.0F), worldXToScreenX(30.0F), 8, false);
            this.textFont.draw(paramSpriteBatch, "Shield Duration", worldXToScreenX(150.0F), worldYToScreenY(775.0F));
            BitmapFont bitmapFont1 = this.smallerTextFont;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Increase duration by ");
            int i = this.shieldUpgraded;
            if (i != 10)
                i++;
            stringBuilder2.append(i * 10);
            stringBuilder2.append("%");
            bitmapFont1.draw(paramSpriteBatch, stringBuilder2.toString(), worldXToScreenX(150.0F), worldYToScreenY(735.0F));
            paramSpriteBatch.draw(this.magnet, worldXToScreenX(50.0F), worldYToScreenY(537.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (this.magnetUpgraded != 10)
                this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.abilityMagnetCost), worldXToScreenX(380.0F), worldYToScreenY(560.0F), worldXToScreenX(30.0F), 8, false);
            this.textFont.draw(paramSpriteBatch, "Magnet Duration", worldXToScreenX(150.0F), worldYToScreenY(640.0F));
            bitmapFont1 = this.smallerTextFont;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Increase duration by ");
            i = this.magnetUpgraded;
            if (i != 10)
                i++;
            stringBuilder2.append(i * 10);
            stringBuilder2.append("%");
            bitmapFont1.draw(paramSpriteBatch, stringBuilder2.toString(), worldXToScreenX(150.0F), worldYToScreenY(600.0F));
            paramSpriteBatch.draw(this.coinRush, worldXToScreenX(50.0F), worldYToScreenY(402.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (this.coinRushUpgraded != 10)
                this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.abilityCoinRushCost), worldXToScreenX(380.0F), worldYToScreenY(425.0F), worldXToScreenX(30.0F), 8, false);
            this.textFont.draw(paramSpriteBatch, "Coin Rush Duration", worldXToScreenX(150.0F), worldYToScreenY(505.0F));
            BitmapFont bitmapFont2 = this.smallerTextFont;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Increase duration by ");
            i = this.coinRushUpgraded;
            if (i != 10)
                i++;
            stringBuilder1.append(i * 10);
            stringBuilder1.append("%");
            bitmapFont2.draw(paramSpriteBatch, stringBuilder1.toString(), worldXToScreenX(150.0F), worldYToScreenY(465.0F));
            paramSpriteBatch.draw(this.spawnRate, worldXToScreenX(50.0F), worldYToScreenY(267.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (this.spawnRateUpgraded != 10)
                this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.abilitySpawnRateCost), worldXToScreenX(380.0F), worldYToScreenY(290.0F), worldXToScreenX(30.0F), 8, false);
            this.textFont.draw(paramSpriteBatch, "Pickup Spawn", worldXToScreenX(150.0F), worldYToScreenY(370.0F));
            bitmapFont2 = this.smallerTextFont;
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Spawn ");
            i = this.spawnRateUpgraded;
            if (i != 10)
                i++;
            stringBuilder1.append(i * 10);
            stringBuilder1.append("% more frequently");
            bitmapFont2.draw(paramSpriteBatch, stringBuilder1.toString(), worldXToScreenX(150.0F), worldYToScreenY(330.0F));
            paramSpriteBatch.draw(this.spawnRateCoins, worldXToScreenX(50.0F), worldYToScreenY(132.5F), worldXToScreenX(75.0F), worldYToScreenY(85.0F));
            if (this.spawnRateCoinsUpgraded != 10)
                this.buyButtonTextFont.draw(paramSpriteBatch, String.valueOf(this.abilitySpawnRateCoinsCost), worldXToScreenX(380.0F), worldYToScreenY(155.0F), worldXToScreenX(30.0F), 8, false);
            this.textFont.draw(paramSpriteBatch, "Coin Spawn", worldXToScreenX(150.0F), worldYToScreenY(235.0F));
            bitmapFont2 = this.smallerTextFont;
            stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Spawn ");
            i = this.spawnRateCoinsUpgraded;
            if (i != 10)
                i++;
            stringBuilder1.append(i * 10);
            stringBuilder1.append("% more frequently");
            bitmapFont2.draw(paramSpriteBatch, stringBuilder1.toString(), worldXToScreenX(150.0F), worldYToScreenY(195.0F));
            drawAllAbilities(paramSpriteBatch);
            if (this.drawNotEnoughMoneyWindow) {
                paramSpriteBatch.draw(this.notEnoughMoneyWindow, worldXToScreenX(10.0F), worldYToScreenY(410.0F), worldXToScreenX(480.0F), worldYToScreenY(180.0F));
                paramSpriteBatch.draw(this.xNotEnoughMoneyButton, worldXToScreenX(420.0F), worldYToScreenY(525.0F), worldXToScreenX(50.0F), worldYToScreenY(50.0F));
            }
            if (this.drawQuitGame) {
                paramSpriteBatch.draw(this.quitWindowTexture, worldXToScreenX(10.0F), worldYToScreenY(395.0F), worldXToScreenX(480.0F), worldYToScreenY(210.0F));
                paramSpriteBatch.draw(this.notQuitButtonTexture, worldXToScreenX(116.0F), worldYToScreenY(405.0F), worldXToScreenX(75.0F), worldYToScreenY(75.0F));
                paramSpriteBatch.draw(this.quitButtonTexture, worldXToScreenX(307.0F), worldYToScreenY(405.0F), worldXToScreenX(75.0F), worldYToScreenY(75.0F));
            }
        }
        paramSpriteBatch.end();
    }

    public void update(float paramFloat) {
    }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\Shop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */