package com.darkday.game.entities.builder.bodyDef;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class BodiesBuilder {

    protected BodyDef bodyDef;
    protected Body body;
    PolygonShape shape;
    FixtureDef fixtureDef;
    World world;
    Sprite sprite;
    Float x, y, density;

    public BodiesBuilder(Sprite sp, World world, Float x, Float y, Float density) {
        this.world = world;
        this.sprite = sp;
        this.x = x;
        this.y = y;
        this.density = density;

        bodyDef = new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

    }

    public abstract void buildDef();
    public abstract void buildPos();
    public abstract void buildInWorld();
    public abstract void buildShape();
    public abstract void buildFixture();
    public abstract void dispose();

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public Body getBody() {
        return body;
    }
}
