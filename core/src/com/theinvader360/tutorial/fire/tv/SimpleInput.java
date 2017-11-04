package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class SimpleInput {

    public static boolean isInputMenuUp() {
        return Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    public static boolean isInputMenuDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public static boolean isInputMenuSelect() {
        return Gdx.input.isKeyPressed(Input.Keys.DPAD_CENTER) || Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }

    public static boolean isInputUp() {
        return (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 0.3f && Gdx.input.getX() < Gdx.graphics.getWidth() * 0.7f && Gdx.input.getY() < Gdx.graphics.getHeight() * 0.5f) ||
                Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) ||
                Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    public static boolean isInputDown() {
        return (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 0.3f && Gdx.input.getX() < Gdx.graphics.getWidth() * 0.7f && Gdx.input.getY() > Gdx.graphics.getHeight() * 0.5f) ||
                Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) ||
                Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public static boolean isInputLeft() {
        return (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 0.0f && Gdx.input.getX() < Gdx.graphics.getWidth() * 0.3f) ||
                Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) ||
                Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public static boolean isInputRight() {
        return (Gdx.input.isTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() * 0.7f && Gdx.input.getX() < Gdx.graphics.getWidth() * 1.0f) ||
                Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) ||
                Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

}
