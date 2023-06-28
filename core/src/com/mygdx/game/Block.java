package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block {
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public Block(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void update() {
        // update position or implement logic for block
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height, Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW);
        shapeRenderer.end();
    }

    public void move(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
    }
}
