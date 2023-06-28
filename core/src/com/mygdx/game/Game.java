package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private Character character;
    private List<Bullet> bullets;
    private float blockSpeed;
    private float stateTime = 0f;
    private float attackTimer = 0f;
    private float bulletTimer = 0f;
    private boolean moveUP;
    private boolean moveDOWN;
    private boolean moveLEFT;
    private boolean moveRIGHT;
    private boolean attack;

    @Override
    public void create() {
        Gdx.gl.glClearColor(0.2f, 0.53f, 0.56f, 1f);
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        character = new Character(100, 100, 0, "idle down");
        bullets = new ArrayList<>();

        blockSpeed = 1f;

        Gdx.input.setInputProcessor(this);
        moveUP = false;
        moveDOWN = false;
        moveLEFT = false;
        moveRIGHT = false;

        attack = false;
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;
        bulletTimer += delta;

        // Xóa mành hình và vẽ lại các đối tượng
        Gdx.gl.glClearColor(0.2f, 0.53f, 0.56f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (attack) {
            attackTimer += delta;
            String state = character.getMovement().split(" ")[1];
            TextureRegion[] createAttack;
            Animation<TextureRegion> animation;
                String movement = null;

            switch (state) {
                case "left":
                    movement = "attack left";
                    break;
                case "right":
                    movement = "attack right";
                    break;
                case "up":
                    movement = "attack up";
                    break;
                case "down":
                    movement = "attack down";
                    break;
            }

            if (character.getKey() == 1) {
                createAttack = Animate.createMove("pistol/", movement);
            } else if (character.getKey() == 2) {
                createAttack = Animate.createMove("sword_and_shield/", movement);
            } else {
                createAttack = Animate.createMove("without_weapon/",movement);
            }
            animation = Animate.createAnimation(createAttack, 0.15f);

            spriteBatch.begin();
            TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
            spriteBatch.draw(currentFrame, character.position.x, character.position.y,
                    Character.CHARACTER_WIDTH * 3f, Character.CHARACTER_HEIGHT * 3f);
            spriteBatch.end();

            if (attackTimer >= 0.6f) {
                attack = false;
                attackTimer = 0f;
            }
        } else {
            if (moveUP) {
                character.move(0, blockSpeed);
            } else if (moveDOWN) {
                character.move(0, - blockSpeed);
            }

            if (moveLEFT) {
                character.move(- blockSpeed, 0);
            } else if (moveRIGHT) {
                character.move(blockSpeed, 0);
            }

            for (Bullet bullet : bullets) {
                bullet.update();
                bullet.render(shapeRenderer);
            }

            spriteBatch.begin();
            character.update(delta);
            character.render(spriteBatch);
            spriteBatch.end();
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.NUM_1) {
            character.setKey(1);
        }

        if (keycode == Input.Keys.NUM_2) {
            character.setKey(2);
        }

        if (keycode == Input.Keys.NUM_3) {
            character.setKey(3);
        }

        if (keycode == Input.Keys.W) {
            if (!moveDOWN) {
                moveUP = true;
                if (moveLEFT) {
                    character.setMovement("walk left");
                } else if (moveRIGHT) {
                    character.setMovement("walk right");
                } else {
                    character.setMovement("walk up");
                }
            }
        }

        if (keycode == Input.Keys.S) {
            if (!moveUP) {
                moveDOWN = true;
                if (moveLEFT) {
                    character.setMovement("walk left");
                } else if (moveRIGHT) {
                    character.setMovement("walk right");
                } else {
                    character.setMovement("walk down");
                }
            }
        }

        if (keycode == Input.Keys.A) {
            if (!moveRIGHT) {
                moveLEFT = true;
                character.setMovement("walk left");
            }
        }

        if (keycode == Input.Keys.D) {
            if (!moveLEFT) {
                moveRIGHT = true;
                character.setMovement("walk right");
            }
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!moveDOWN) {
            if (keycode == Input.Keys.W) {
                moveUP = false;
                if (moveLEFT) {
                    character.setMovement("walk left");
                } else if (moveRIGHT) {
                    character.setMovement("walk right");
                } else {
                    character.setMovement("idle up");
                }
            }
        }

        if (!moveUP) {
            if (keycode == Input.Keys.S) {
                moveDOWN = false;
                if (moveLEFT) {
                    character.setMovement("walk left");
                } else if (moveRIGHT) {
                    character.setMovement("walk right");
                } else {
                    character.setMovement("idle down");
                }
            }
        }

        if (!moveRIGHT) {
            if (keycode == Input.Keys.A) {
                moveLEFT = false;
                if (moveUP) {
                    character.setMovement("walk up");
                } else if (moveDOWN) {
                    character.setMovement("walk down");
                } else {
                    character.setMovement("idle left");
                }
            }
        }

        if (!moveLEFT) {
            if (keycode == Input.Keys.D) {
                moveRIGHT = false;
                if (moveUP) {
                    character.setMovement("walk up");
                } else if (moveDOWN) {
                    character.setMovement("walk down");
                } else {
                    character.setMovement("idle right");
                }
            }
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            attack = true;
            if (character.getKey() == 1) {
                float mouseX = screenX;
                float mouseY = Gdx.graphics.getHeight() - screenY;
                Bullet bullet = new Bullet(character.position.x + 120, character.position.y + 88,
                        8,6, mouseX, mouseY);
                bullets.add(bullet);
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
