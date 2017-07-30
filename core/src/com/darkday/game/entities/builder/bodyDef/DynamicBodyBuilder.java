package com.darkday.game.entities.builder.bodyDef;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class DynamicBodyBuilder extends BodiesBuilder {

    public DynamicBodyBuilder(Sprite sp, World world, Float x, Float y, Float density) {
        super(sp, world, x, y, density);
    }

    @Override
    public void buildDef() {
        bodyDef.type = BodyDef.BodyType.DynamicBody;
    }

    @Override
    public void buildPos() {
        bodyDef.position.set(sprite.getX(), sprite.getY());
    }

    @Override
    public void buildInWorld() {
        body = world.createBody(bodyDef);
    }

    @Override
    public void buildShape() {
        shape.setAsBox(x, y);
    }

    @Override
    public void buildFixture() {
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        Fixture fixture = body.createFixture(fixtureDef);
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
