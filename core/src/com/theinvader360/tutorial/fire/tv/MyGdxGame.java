package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
    public ActionResolver actionResolver;
    public MenuScreen menuScreen;
    public GameScreen gameScreen;
    private boolean amazonDevice;
    private boolean fireTVDevice;

    public MyGdxGame(ActionResolver actionResolver, boolean amazonDevice, boolean fireTVDevice) {
        this.actionResolver = actionResolver;
        this.amazonDevice = amazonDevice;
        this.fireTVDevice = fireTVDevice;
    }

	@Override
	public void create() {
        Assets.load();
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        setScreen(menuScreen);
	}

	@Override
	public void dispose() {
        Assets.dispose();
        menuScreen.dispose();
        gameScreen.dispose();
	}

    public boolean isAmazonDevice() {
        return amazonDevice;
    }

    public boolean isFireTVDevice() {
        return fireTVDevice;
    }

}
