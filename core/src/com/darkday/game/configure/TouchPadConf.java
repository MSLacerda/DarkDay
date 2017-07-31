package com.darkday.game.configure;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TouchPadConf {
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchpadBackground;
    private Drawable touchpadKnob;

    public void touchPadConf() {
        touchpadSkin = new Skin();
        touchpadSkin.add("touchpadBackground", new Texture("touchpadBackground.png"));
        touchpadSkin.add("touchKnob", new Texture("touchKnob.png"));
        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadBackground = touchpadSkin.getDrawable("touchpadBackground");
        touchpadKnob = touchpadSkin.getDrawable("touchKnob");

        touchpadStyle.background = touchpadBackground;
        touchpadStyle.knob = touchpadKnob;

        touchpad = new Touchpad(20, touchpadStyle);
        touchpad.setBounds(15, 15 ,150, 150);

    }

    public Touchpad getTouchpad () {
        return touchpad;
    }

}
