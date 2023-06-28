package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Character {
    TextureRegion[] moveFrames;
    Animation<TextureRegion> animation;
    private static final float FRAME_RATE = 0.15f;
    private float stateTime = 0f;
    public static final float CHARACTER_WIDTH = 64f;
    public static final float CHARACTER_HEIGHT = 64f;
    private static final float scale = 3f;
    private int key;
    private String movement;
    public Vector2 position;
    public Rectangle bounds;

    public Character(float x, float y, int key, String movement) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, CHARACTER_WIDTH * scale, CHARACTER_HEIGHT * scale);
        this.key = key;
        this.movement = movement;
        if (key == 1) {
            moveFrames = Animate.createMove("pistol/", movement);
        } else if (key == 2) {
            moveFrames = Animate.createMove("sword_and_shield/", movement);
        } else {
            moveFrames = Animate.createMove("without_weapon/",movement);
        }
        animation = Animate.createAnimation(moveFrames, FRAME_RATE);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;

        if (key == 1) {
            moveFrames = Animate.createMove("pistol/", movement);
        } else if (key == 2) {
            moveFrames = Animate.createMove("sword_and_shield/", movement);
        } else {
            moveFrames = Animate.createMove("without_weapon/",movement);
        }
        animation = Animate.createAnimation(moveFrames, FRAME_RATE);
    }

    public void render(SpriteBatch spriteBatch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        spriteBatch.draw(currentFrame, position.x, position.y,
                CHARACTER_WIDTH * scale, CHARACTER_HEIGHT * scale);
    }

    public void move(float deltaX, float deltaY) {
        position.x += deltaX;
        position.y += deltaY;
        bounds.setPosition(position);
    }
}
