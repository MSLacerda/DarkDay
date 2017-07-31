package com.darkday.game.entities.builder.bodyDef;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;


public class DynamicBodyBuilder extends BodiesBuilder {

    public DynamicBodyBuilder(World world, Float positionX, Float positionY, Float shapeX, Float shapeY, Float density, boolean fixedRot) {
        super(world, positionX, positionY,shapeX, shapeY, density, fixedRot);
    }

    @Override
    public void buildDef() {
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    @Override
    public void buildPos() {
        this.bodyDef.position.set(this.positionX, this.positionY);
        this.bodyDef.fixedRotation = this.fixedRot;
    }

    @Override
    public void buildInWorld() {
        this.body = this.world.createBody(bodyDef);
    }

    @Override
    public void buildShape() {
        this.shape.setAsBox(this.shapeX / 2 / 32, this.shapeY / 2 / 32);
    }

    @Override
    public void buildFixture() {
        this.fixtureDef.shape = shape;
        this.fixtureDef.density = density;
        Fixture fixture = this.body.createFixture(fixtureDef);
    }

    @Override
    public void dispose() {
        this.shape.dispose();
    }
}
