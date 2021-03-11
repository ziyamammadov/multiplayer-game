/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author Ziya
 */
public class Spawn {

    private final Handler handler;
    private final Player player;
    private final Player secondPlayer;
    private int counter;
    private int counterCoin;
    private boolean isUEnemyExist;
    private boolean isSEnemyExist;
    private boolean isSecondSEnemyExist;

    private final Random random;

    public Spawn() {
        this.handler = SharedVariables.getHandler();
        this.player = SharedVariables.getPlayer();
        this.secondPlayer = SharedVariables.getSecondPlayer();
        this.random = new Random();
    }

    public void tick() {
        counter++;
        counterCoin++;
        boolean isDead = player.getHealth() <= 0 || secondPlayer.getHealth() <= 0;

        if (((player.getHealth() <= 20 && player.getHealth() > 0)
                || (secondPlayer.getHealth() <= 20 && secondPlayer.getHealth() > 0))
                && counter > 120 && !isDead) {
            handler.addObject(new Health(random.nextInt(1200), random.nextInt(800), ID.HEALTH));
            counter = 0;
        }
        if ((player.getPoint() == 10 || secondPlayer.getPoint() == 10) && !isUEnemyExist && !isDead) {
            handler.addObject(new UnpredictableEnemy(random.nextInt(1200), random.nextInt(800), ID.UNPREDICTABLE_ENEMY));
            isUEnemyExist = true;
        }
        if (player.getPoint() == 5 && !isSEnemyExist) {
            handler.addObject(new SmartEnemy(random.nextInt(1200), random.nextInt(800), ID.SMART_ENEMY, ID.FIRST_PLAYER));
            isSEnemyExist = true;
        }
        if (secondPlayer.getPoint() == 5 && !isSecondSEnemyExist) {
            handler.addObject(new SmartEnemy(random.nextInt(1200), random.nextInt(800), ID.SMART_ENEMY, ID.SECOND_PLAYER));
            isSecondSEnemyExist = true;
        }
        if (counterCoin == 300 && (player.getHealth() > 0 || secondPlayer.getHealth() > 0)) {
            handler.addObject(new Coin(random.nextInt(1200), random.nextInt(800), ID.COIN));
            counterCoin = 0;
        }
    }

}
