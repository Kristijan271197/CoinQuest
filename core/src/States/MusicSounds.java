package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicSounds {
    private Music backgroundMusic;
    private Sound bombSound;
    private Sound buttonBuy;
    private Sound buttonClick;
    private Sound buttonReceiveCoins;
    private Sound coinCollect;
    private Sound jump;
    private Sound playerGround;
    private Preferences prefs;
    private Sound rockHit;
    private Sound wheelReward;
    private Music wheelSpin;
    private Sound cactusHit;
    private Sound collectibleCollected;
    private Sound endGameSound;
    private Sound rocketExplosion;
    private Sound rocketLaunch;

    public MusicSounds(AssetManager manager) {
        buttonClick = manager.get("music/button_click.mp3", Sound.class);
        buttonReceiveCoins = manager.get("music/receive_coins.mp3", Sound.class);
        buttonBuy = manager.get("music/button_buy.mp3", Sound.class);
        coinCollect = manager.get("music/coin_collect.mp3", Sound.class);
        rockHit = manager.get("music/rock_hit.mp3", Sound.class);
        bombSound = manager.get("music/bomb_explode.mp3", Sound.class);
        playerGround = manager.get("music/player_ground.mp3", Sound.class);
        jump = manager.get("music/jump.mp3", Sound.class);
        wheelReward = manager.get("music/wheel_reward.mp3", Sound.class);
        wheelSpin = manager.get("music/wheel_of_fortune.mp3", Music.class);
        backgroundMusic = manager.get("music/fun_adventure.ogg", Music.class);
        cactusHit = manager.get("music/cactus_hit.mp3", Sound.class);
        collectibleCollected = manager.get("music/collectible_collected.mp3", Sound.class);
        endGameSound = manager.get("music/endgame_sound.mp3", Sound.class);
        rocketExplosion = manager.get("music/rocket_explosion.mp3", Sound.class);
        rocketLaunch = manager.get("music/rocket_launch.mp3", Sound.class);
        prefs = Gdx.app.getPreferences("prefs");
    }


    public void playBackgroundMusic() {
        if (prefs.getBoolean(Settings.MUSIC)) {
            backgroundMusic.setVolume(0.2F);
            backgroundMusic.play();
            backgroundMusic.setLooping(true);
        } else
            backgroundMusic.stop();

    }

    public void playBombSound() {
        if (prefs.getBoolean(Settings.SOUND))
            bombSound.play(1.0F);
    }

    void playButtonBuy() {
        if (prefs.getBoolean(Settings.SOUND))
            buttonBuy.play(0.5F);
    }

    public void playButtonClick() {
        if (prefs.getBoolean(Settings.SOUND))
            buttonClick.play(0.5F);
    }

    void playButtonReceiveCoins() {
        if (prefs.getBoolean(Settings.SOUND))
            buttonReceiveCoins.play(0.5F);
    }

    public void playCoinCollect() {
        if (prefs.getBoolean(Settings.SOUND))
            coinCollect.play(0.2F);
    }

    public void playJump() {
        if (prefs.getBoolean(Settings.SOUND))
            jump.play(0.25F);
    }

    public void playPlayerGround() {
        if (prefs.getBoolean(Settings.SOUND))
            playerGround.play(0.5F);
    }

    public void playRockHit() {
        if (prefs.getBoolean(Settings.SOUND))
            rockHit.play(0.5F);
    }

    void playWheelReward() {
        if (prefs.getBoolean(Settings.SOUND))
            wheelReward.play(0.15F);
    }

    void playWheelSpin() {
        if (prefs.getBoolean(Settings.SOUND)) {
            wheelSpin.setVolume(0.15F);
            wheelSpin.play();
        }
    }

    public void playCactusHit() {
        if (prefs.getBoolean(Settings.SOUND))
            cactusHit.play(1f);
    }

    public void playCollectibleCollected() {
        if (prefs.getBoolean(Settings.SOUND))
            collectibleCollected.play(1f);
    }

    void playEndgameSound() {
        if (prefs.getBoolean(Settings.SOUND))
            endGameSound.play(1f);
    }

    public void playRocketExplosion() {
        if (prefs.getBoolean(Settings.SOUND))
            rocketExplosion.play(1f);
    }

    public void playRocketLaunch() {
        if (prefs.getBoolean(Settings.SOUND))
            rocketLaunch.play(0.3f);
    }

    Music getBackgroundMusic() {
        return this.backgroundMusic;
    }

    Sound getWheelReward() {
        return this.wheelReward;
    }
}
