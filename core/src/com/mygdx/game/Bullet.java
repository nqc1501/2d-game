package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

public class Bullet extends Block {
    private float velocityX;
    private float velocityY;
    private final float bulletSpeed = 10;

    public Bullet(float x, float y, float width, float height, float mouseX, float mouseY) {
        super(x, y, width, height);

        float dx = mouseX - x;
        float dy = mouseY - y;
        float angle = MathUtils.atan2(dy, dx);

        this.velocityX = bulletSpeed * MathUtils.cos(angle);
        this.velocityY = bulletSpeed * MathUtils.sin(angle);
    }

    public void update() {
        x += velocityX;
        y += velocityY;
    }
}
