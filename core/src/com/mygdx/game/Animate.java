package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animate {
    public static TextureRegion[] createMove(String path, String movement) {
        String[] action = movement.split(" ");
        TextureRegion[] frames = new TextureRegion[4];

        for (int i = 1; i <= 4; i++) {
            Texture texture = new Texture(Gdx.files.internal(path + action[0] + "/" +
                    movement + i + ".png"));
            frames[i - 1] = new TextureRegion(texture);
        }

        return frames;
    }

    public static Animation<TextureRegion> createAnimation(TextureRegion[] frames, float frameDuration) {
        return new Animation<TextureRegion>(frameDuration, frames);
    }
}
