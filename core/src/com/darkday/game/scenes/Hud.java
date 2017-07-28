package com.darkday.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.darkday.game.DarkDay;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer countKill;

    Label countKillLabel;

    public Hud(SpriteBatch sb) {
        countKill = 0;
        viewport = new FitViewport(DarkDay.V_WIDTH, DarkDay.V_HEITGH, new OrthographicCamera());

        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        countKillLabel = new Label(String.format("%03d", countKill), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(countKillLabel).expandX().padTop(10);
        table.add().expandX();
        table.add().expandX();
        table.add().expandX();
        stage.addActor(table);

    }


}
