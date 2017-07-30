package com.darkday.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkday.game.DarkDay;
import com.darkday.game.entities.Player;
import com.darkday.game.scenes.Ground;
import com.darkday.game.scenes.Hud;

public class PlayScreen implements Screen {

    private DarkDay game;
    private OrthographicCamera gamecam;
    private Viewport viewport;


    private Ground ground;
    private OrthogonalTiledMapRenderer renderer;

    private Hud hud;

    private Player player;

    public PlayScreen(DarkDay game) {
        this.game = game;
        gamecam = new OrthographicCamera();
        viewport = new FitViewport(DarkDay.V_WIDTH, DarkDay.V_HEITGH, gamecam);
        ground = new Ground();
        renderer = new OrthogonalTiledMapRenderer(ground.map);
        player = new Player(new Sprite(new Texture("player.png")));

        hud = new Hud(game.batch);

    }

    @Override
    public void show() {

    }


    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            System.out.println(Gdx.input.getY() + " | ");
            if (Gdx.input.getX() > viewport.getScreenWidth() / 2)

                if (Gdx.input.getY() < viewport.getScreenHeight() / 2){

                    gamecam.position.y += 100*dt;
                }else {
                    gamecam.position.x += 100*dt;
                }
            else{
                gamecam.position.x -= 100*dt;
                if (Gdx.input.getY() < viewport.getScreenHeight() / 2){
                    System.out.println(Gdx.input.getY() + " | ");
                    gamecam.position.y += 100*dt;
                }else{
                    gamecam.position.x -= 100*dt;
                }
            }
        }


    }

    public void update(float dt) {
        handleInput(dt);
        gamecam.update();

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        renderer.setView(gamecam);
        renderer.getBatch().begin();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        player.draw(renderer.getBatch());
        renderer.getBatch().end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
        ground.map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
        hud.stage.dispose();
    }
}
