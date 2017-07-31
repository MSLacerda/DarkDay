package com.darkday.game.screens;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkday.game.DarkDay;
import com.darkday.game.configure.ConfFacade;
import com.darkday.game.configure.ScreenConf;
import com.darkday.game.entities.B2dSteeringEntity;
import com.darkday.game.entities.Player;
import com.darkday.game.entities.builder.bodyDef.BodyDirector;
import com.darkday.game.entities.builder.bodyDef.DynamicBodyBuilder;
import com.darkday.game.entities.builder.bodyDef.StaticBodyBuilder;
import com.darkday.game.scenes.Ground;
import com.darkday.game.scenes.Hud;

public class PlayScreen implements Screen {

    private DarkDay game;
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Box2DDebugRenderer debugRenderer;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadskin;
    private RayHandler rayHandler;
    private PointLight ptLight;
    private B2dSteeringEntity entity, target;




    private Ground ground;
    private OrthogonalTiledMapRenderer renderer;

    private Hud hud;

    private Player player;

    private World world;

    private ConfFacade conf;

    private SpriteBatch spriteBatch;
    private Stage stage;


    private  BodyDirector builder;


    public PlayScreen(DarkDay game, SpriteBatch sb) {
        this.game = game;
        this.spriteBatch = sb;
        gamecam = new OrthographicCamera();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        conf = new ConfFacade();
        conf.initConfs(208, 400, 32, 10);

        touchpad = conf.getTouchPad();

        stage.addActor(touchpad);
        viewport = new ScreenViewport(gamecam);
        debugRenderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0f), true);

        builder = new BodyDirector(new DynamicBodyBuilder(world,viewport.getCamera().viewportWidth / 2 + 3, viewport.getCamera().viewportHeight /2 + 3, 32f , 32f, 1.0f, false ));
        builder.setBuilder();
        entity = new B2dSteeringEntity(builder.getBody(), 10);

        builder = new BodyDirector(new DynamicBodyBuilder(world, viewport.getCamera().viewportWidth / 2, viewport.getCamera().viewportHeight /2, 32f , 32f, 1.0f, true));
        builder.setBuilder();

        player = new Player(builder.getBody(), viewport ,touchpad);

        target = new B2dSteeringEntity(builder.getBody(), 10);


        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(.2f);
        rayHandler.setShadows(true);
        rayHandler.setBlurNum(1);

        ptLight = new PointLight(rayHandler, 150, Color.CYAN, 6, 0, 0);
        ptLight.attachToBody(builder.getBody());

        ptLight.setSoftnessLength(0f);


        for (int i = 0; i < 800; i++){
            builder = new BodyDirector(new StaticBodyBuilder(world, (float) Math.random() * 100, (float) Math.random() * 100, (float) Math.random() * 50, 128f, 1.0f, true));
            builder.setBuilder();
        }


        Arrive<Vector2> arriveSB = new Arrive<Vector2>(entity, target)
                .setTimeToTarget(0.01f)
                .setArrivalTolerance(2f)
                .setDecelerationRadius(10);
        entity.setBehavior(arriveSB);








    }

    @Override
    public void show() {

    }

    public void cameraUpdate(float delta){
        Vector3 position = gamecam.position;
        position.x = player.getBodyPosition().x * ScreenConf.PPM;
        position.y = player.getBodyPosition().y * ScreenConf.PPM;
        gamecam.position.set(position);

        gamecam.update();
    }


    public void update(float dt) {
        world.step(1.0f/60f, 6, 2);

        rayHandler.update();

        player.update();

        cameraUpdate(dt);
        rayHandler.setCombinedMatrix(gamecam.combined.cpy().scl(ScreenConf.PPM));

        entity.update(dt);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0.294f, 0.294f, 0.294f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
        debugRenderer.render(world, viewport.getCamera().combined.scl(32));
        rayHandler.render();
        stage.draw();


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
        world.dispose();
        debugRenderer.dispose();
        spriteBatch.dispose();
        rayHandler.dispose();
    }
}
