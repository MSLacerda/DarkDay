package com.darkday.game.scenes;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {

    public TmxMapLoader mapLoader;
    public TiledMap map;


    public Ground() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map_1.tmx");
    }
}
