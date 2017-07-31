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
    Float shapeX, shapeY,positionX, positionY, density;
    boolean fixedRot;

    public BodiesBuilder(World world, Float positionX, Float positionY, Float shapeX, Float shapeY, Float density, boolean fixedRot) {
        this.world = world;
        this.positionX = positionX;
        this.positionY = positionY;
        this.shapeX = shapeX;
        this.shapeY = shapeY;
        this.density = density;
        this.fixedRot = fixedRot;


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
