/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamedevelopmentlatest;

/**
 *
 * @author Ziya
 */
public class GSpace {

    static float cameraBoom = 1;
    static int multSize = 2;
    static int objectSize = 16;
    private static Handler handler;
    private static Game game;
    private static Player player;
    private static HUD hud;
    private static Camera camera;
    private static Enemy enemy;

    public static Camera getCamera() {
        if (camera == null) {
            camera = new Camera();
        }
        return camera;
    }

    public static void setCamera(Camera camera) {
        GSpace.camera = camera;
    }

    public static HUD getHud() {
        if (hud == null) {
            hud = new HUD();
        }
        return hud;
    }

    public static HUD getHud(HUD hud) {
        GSpace.hud = hud;
        return GSpace.hud;
    }

    public static Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }

    public static Handler getHandler(Handler handler) {
        GSpace.handler = handler;
        return handler;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GSpace.game = game;
    }

    public static Player getPlayer() {
        if (player == null) {
            player = new Player(10, 10, ID.PLAYER);
            if (handler == null) {
                getHandler();
                handler.addObject(player);
            }
        }
        return player;
    }

    public static Player getPlayer(Player player) {
        if (player == null) {
            GSpace.player = player;
            if (handler == null) {
                getHandler();
            }
            handler.addObject(player);
        }

        return GSpace.player;
    }

}
