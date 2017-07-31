package com.darkday.game.entities.builder.bodyDef;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class StaticBodyBuilder extends BodiesBuilder {

    public StaticBodyBuilder(World world, Float positionX, Float positionY, Float shapeX, Float shapeY, Float density, boolean fixedRot) {
        super(world, positionX, positionY,shapeX, shapeY, density, fixedRot);
    }

    @Override
    public void buildDef() {
        bodyDef.type = BodyDef.BodyType.StaticBody;
    }

    @Override
    public void buildPos() {
        bodyDef.position.set(positionX, positionY);
        bodyDef.fixedRotation = this.fixedRot;
    }

    @Override
    public void buildInWorld() {
        body = world.createBody(bodyDef);

    }

    @Override
    public void buildShape() {

        shape.setAsBox(this.shapeX/2/32, this.shapeY/2/32);

    }

    @Override
    public void buildFixture() {
        this.fixtureDef.shape = shape;
        this.fixtureDef.density = density;

        Fixture fixture = body.createFixture(this.fixtureDef);
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
