package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {
    public GameScreen gameScreen;

	@Override
	public void create() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}

}
