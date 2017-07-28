package com.darkday.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkday.game.DarkDay;
import com.darkday.game.scenes.Hud;

public class PlayScreen implements Screen {

    private DarkDay game;
    private OrthographicCamera gamecam;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(DarkDay game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map_1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void show() {

    }


    public void handleInput(float dt){
        if(Gdx.input.isTouched())
            gamecam.position.x += 100 * dt;
    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        renderer.setView(gamecam);

    }

    @Override
    public void resize(int width, int height) {
        gamecam.viewportWidth = width;
        gamecam.viewportHeight = height;
        gamecam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
