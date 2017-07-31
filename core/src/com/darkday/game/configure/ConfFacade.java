package com.darkday.game.configure;

import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class ConfFacade {
    protected ScreenConf screen;
    protected TouchPadConf touchPadConf;
    protected WorldConf worldConf;

    public void initConfs(int v_height, int v_width, int ppm, int playerVel) {
        screen = new ScreenConf();
        screen.screenConf(v_height, v_width, ppm);

        touchPadConf = new TouchPadConf();
        touchPadConf.touchPadConf();

        worldConf = new WorldConf();
        worldConf.confWorld(playerVel);
    }

    public Touchpad getTouchPad() {
        return touchPadConf.getTouchpad();
    }

    public void setVelocityPlayer(int vel) {
        worldConf.setPlayerVel(vel);
    }
}
