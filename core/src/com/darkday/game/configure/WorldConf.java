package com.darkday.game.configure;

public class WorldConf {
    public static int PLAYER_VEL;

    public void confWorld(int velocity) {
        PLAYER_VEL = velocity;
    }

    public void setPlayerVel(int vel) {
        PLAYER_VEL = vel;
    }
}
