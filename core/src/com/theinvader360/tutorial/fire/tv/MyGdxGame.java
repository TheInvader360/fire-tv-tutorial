package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
    public MenuScreen menuScreen;
    public GameScreen gameScreen;

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

}
