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
    private Music cityMusic;
    private Music desertMusic;

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
        cityMusic = manager.get("music/city_music.mp3", Music.class);
        desertMusic = manager.get("music/desert_music.mp3", Music.class);
        prefs = Gdx.app.getPreferences("prefs");
    }


    public void playBackgroundMusic() {
        if (prefs.getBoolean(Settings.MUSIC, true)) {
            backgroundMusic.setVolume(0.2F);
            backgroundMusic.play();
            backgroundMusic.setLooping(true);
        } else
            backgroundMusic.stop();

    }

    public void playBombSound() {
        if (prefs.getBoolean(Settings.SOUND, true))
            bombSound.play(1.0F);
    }

    void playButtonBuy() {
        if (prefs.getBoolean(Settings.SOUND, true))
            buttonBuy.play(0.5F);
    }

    public void playButtonClick() {
        if (prefs.getBoolean(Settings.SOUND, true))
            buttonClick.play(0.5F);
    }

    void playButtonReceiveCoins() {
        if (prefs.getBoolean(Settings.SOUND, true))
            buttonReceiveCoins.play(0.5F);
    }

    public void playCoinCollect() {
        if (prefs.getBoolean(Settings.SOUND, true))
            coinCollect.play(0.2F);
    }

    public void playJump() {
        if (prefs.getBoolean(Settings.SOUND, true))
            jump.play(0.25F);
    }

    public void playPlayerGround() {
        if (prefs.getBoolean(Settings.SOUND, true))
            playerGround.play(0.5F);
    }

    public void playRockHit() {
        if (prefs.getBoolean(Settings.SOUND, true))
            rockHit.play(0.5F);
    }

    void playWheelReward() {
        if (prefs.getBoolean(Settings.SOUND, true))
            wheelReward.play(0.15F);
    }

    void playWheelSpin() {
        if (prefs.getBoolean(Settings.SOUND, true)) {
            wheelSpin.setVolume(0.15F);
            wheelSpin.play();
        }
    }

    public void playCactusHit() {
        if (prefs.getBoolean(Settings.SOUND, true))
            cactusHit.play(1f);
    }

    public void playCollectibleCollected() {
        if (prefs.getBoolean(Settings.SOUND, true))
            collectibleCollected.play(1f);
    }

    void playEndgameSound() {
        if (prefs.getBoolean(Settings.SOUND, true))
            endGameSound.play(1f);
    }

    public void playRocketExplosion() {
        if (prefs.getBoolean(Settings.SOUND, true))
            rocketExplosion.play(1f);
    }

    public void playRocketLaunch() {
        if (prefs.getBoolean(Settings.SOUND, true))
            rocketLaunch.play(0.3f);
    }

    public void playCityMusic() {
        if (prefs.getBoolean(Settings.MUSIC, true)) {
            cityMusic.setLooping(true);
            cityMusic.setVolume(0.5f);
            cityMusic.play();
        } else
            cityMusic.stop();
    }

    public void playDesertMusic() {
        if (prefs.getBoolean(Settings.MUSIC, true)) {
            desertMusic.setLooping(true);
            desertMusic.setVolume(0.5f);
            desertMusic.play();
        } else
            desertMusic.stop();
    }

    public Music getDesertMusic() {
        return desertMusic;
    }

    public Music getCityMusic() {
        return cityMusic;
    }

    Music getBackgroundMusic() {
        return this.backgroundMusic;
    }

    Sound getWheelReward() {
        return this.wheelReward;
    }
}
