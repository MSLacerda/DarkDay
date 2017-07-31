package com.darkday.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkday.game.configure.ScreenConf;
import com.darkday.game.configure.WorldConf;

public class Player {


    private Vector2 velocity = new Vector2();
    private Viewport viewport;
    private Touchpad touchpad;

    private Body body;

    Vector2 temp = new Vector2();

    public Player(Body body, Viewport viewport, Touchpad touchpad) {
        this.touchpad = touchpad;
        this.body = body;
        this.viewport = viewport;
    }


    public void update() {
        int x =0, y= 0;
        this.body.setLinearVelocity(this.touchpad.getKnobPercentX() * WorldConf.PLAYER_VEL, this.touchpad.getKnobPercentY() * WorldConf.PLAYER_VEL);
    }

    public Vector2 getBodyPosition() {
        return this.body.getPosition();
    }
}
