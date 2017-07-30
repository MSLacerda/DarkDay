package com.darkday.game.entities.builder.bodyDef;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BodyDirector {
    protected BodiesBuilder builder;


    public BodyDirector(BodiesBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder() {
        builder.buildDef();
        builder.buildPos();
        builder.buildInWorld();
        builder.buildShape();
        builder.buildFixture();
    }

    public Body getBody(){
        return builder.getBody();
    }

    public BodyDef getBodyDef() {
        return builder.getBodyDef();
    }

}
