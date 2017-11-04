package com.theinvader360.tutorial.fire.tv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
    private static final int WORLD_WIDTH = 16;
    private static final int WORLD_HEIGHT = 9;
    private static final float MOVE_TIME = 0.3F;
    private MyGdxGame game;
    private Camera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private float moveTimer;
    private int headX;
    private int headY;
    private int headPreviousX;
    private int headPreviousY;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();
    private Direction direction;
    private boolean directionSet;
    private boolean appleAvailable;
    private int appleX;
    private int appleY;
    private enum Direction { UP, DOWN, LEFT, RIGHT }

    private class BodyPart {
        public int x;
        public int y;
        public BodyPart(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public GameScreen(final MyGdxGame game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set((float)WORLD_WIDTH / 2, (float)WORLD_HEIGHT / 2, 0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        startNewGame();
    }

    private void startNewGame() {
        moveTimer = MOVE_TIME;
        headX = 0;
        headY = 4;
        headPreviousX = headX - 1;
        headPreviousY = headY;
        bodyParts.clear();
        bodyParts.add(new BodyPart(headX - 3, headY));
        bodyParts.add(new BodyPart(headX - 2, headY));
        bodyParts.add(new BodyPart(headX - 1, headY));
        direction = Direction.RIGHT;
        directionSet = false;
        appleAvailable = false;
        appleX = 0;
        appleY = 0;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        queryInput();
        updateSnake(delta);
        checkAppleCollision();
        tryPlaceApple();
        draw();
    }

    private void queryInput() {
        if (SimpleInput.isInputUp()) tryUpdateDirection(Direction.UP);
        if (SimpleInput.isInputDown()) tryUpdateDirection(Direction.DOWN);
        if (SimpleInput.isInputLeft()) tryUpdateDirection(Direction.LEFT);
        if (SimpleInput.isInputRight()) tryUpdateDirection(Direction.RIGHT);
    }

    private void tryUpdateDirection(Direction newDirection) {
        if (!directionSet && direction != newDirection) {
            directionSet = true;
            if (newDirection == Direction.UP && direction != Direction.UP && direction != Direction.DOWN) direction = Direction.UP;
            if (newDirection == Direction.DOWN && direction != Direction.DOWN && direction != Direction.UP) direction = Direction.DOWN;
            if (newDirection == Direction.LEFT && direction != Direction.LEFT && direction != Direction.RIGHT) direction = Direction.LEFT;
            if (newDirection == Direction.RIGHT && direction != Direction.RIGHT && direction != Direction.LEFT) direction = Direction.RIGHT;
        }
    }

    private void updateSnake(float delta) {
        moveTimer -= delta;
        if (moveTimer <= 0) {
            moveTimer = MOVE_TIME;
            moveSnake();
            checkSnakeCollision();
            directionSet = false;
        }
    }

    private void moveSnake() {
        headPreviousX = headX;
        headPreviousY = headY;
        if (direction == Direction.UP) {
            headY++;
            if (headY >= WORLD_HEIGHT) headY = 0;
        }
        if (direction == Direction.DOWN) {
            headY--;
            if (headY < 0) headY = WORLD_HEIGHT - 1;
        }
        if (direction == Direction.LEFT) {
            headX--;
            if (headX < 0) headX = WORLD_WIDTH - 1;
        }
        if (direction == Direction.RIGHT) {
            headX++;
            if (headX >= WORLD_WIDTH) headX = 0;
        }
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            bodyPart.x = headPreviousX;
            bodyPart.y = headPreviousY;
            bodyParts.add(bodyPart);
        }
    }

    private void checkSnakeCollision() {
        for (BodyPart bodyPart : bodyParts) if (bodyPart.x == headX && bodyPart.y == headY) game.setScreen(game.menuScreen);
    }

    private void checkAppleCollision() {
        if (appleAvailable && appleX == headX && appleY == headY) {
            BodyPart bodyPart = new BodyPart(headX, headY);
            bodyParts.insert(0, bodyPart);
            appleAvailable = false;
        }
    }

    private void tryPlaceApple() {
        if (!appleAvailable) {
            appleX = MathUtils.random(WORLD_WIDTH - 1);
            appleY = MathUtils.random(WORLD_HEIGHT - 1);
            appleAvailable = true;
            if (appleX == headX && appleY == headY) appleAvailable = false;
            for (BodyPart bodyPart : bodyParts) if (appleX == bodyPart.x && appleY == bodyPart.y) appleAvailable = false;
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setProjectionMatrix(camera.projection);
        renderer.setTransformMatrix(camera.view);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BROWN);
        renderer.rect(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        renderer.setColor(Color.OLIVE);
        for (BodyPart bodyPart : bodyParts) renderer.rect(bodyPart.x, bodyPart.y, 1, 1);
        renderer.setColor(Color.FOREST);
        renderer.rect(headX, headY, 1, 1);
        renderer.setColor(Color.SCARLET);
        renderer.rect(appleX, appleY, 1, 1);
        renderer.end();
    }

}
