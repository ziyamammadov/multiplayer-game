/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Ziya
 */
public class SharedVariables {

    private static Handler handler;
    private static Game game;
    private static Player player;
    private static Player secondPlayer;

    public static Player getPlayerByID(ID id) {
        return id == ID.SECOND_PLAYER ? secondPlayer : player;
    }

    public static Player getSecondPlayer() {
        return secondPlayer;
    }

    public static void setSecondPlayer(Player secondPlayer) {
        SharedVariables.secondPlayer = secondPlayer;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void setHandler(Handler handler) {
        SharedVariables.handler = handler;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        SharedVariables.game = game;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        SharedVariables.player = player;
    }
}
