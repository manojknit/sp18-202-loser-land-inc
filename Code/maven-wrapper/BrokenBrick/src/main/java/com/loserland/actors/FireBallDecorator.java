package com.loserland.actors;
import greenfoot.*;
import com.loserland.worlds.*;

import static com.loserland.utils.Animation.directionRotate;

public class FireBallDecorator implements IBall, IBallDecorator {
    private BasicBall basicBall;

    public void action() {
        basicBall.action();
        if(!basicBall.onPaddle) {
            rotate();
        }
    }
    public void assemble(IBall basicBall) {
        this.basicBall = (BasicBall)basicBall;
        this.basicBall.setDecorator(this);
        this.basicBall.setRotation(0);
        this.basicBall.setGifImage("fireball.gif", 60);
    }

    public void moveBall() {
        if(!basicBall.onPaddle) {
            basicBall.setLocation(basicBall.changeX, basicBall.changeY, basicBall.speed);

            // Collision detection with paddle, brick and world edge
            basicBall.checkPaddleCollision();
            basicBall.checkWallCollision();
            basicBall.checkBrickCollision();
        }
    }

    public void brickCollision(Brick brick){
        basicBall.generatePowerSquare(brick);
        brick.effect();
    }


    public void checkBallMiss() {
        basicBall.checkBallMiss();
    }


    @Override
    public PowerSquareFactory.PowerType getCurrentPower() {
        return PowerSquareFactory.PowerType.FIRE_BALL;
    }

    public void makeSmoke()
    {
        basicBall.smokeTimingCount++;
        if(basicBall.getWorld() != null) {
            if (basicBall.smokeTimingCount % basicBall.smokeFrequency == 0){
                Smoke smoke = new Smoke();
                smoke.setImage("fire_smoke.png");
                basicBall.getWorld().addObject(smoke, basicBall.getX(), basicBall.getY());
            }
        }
    }

    public void rotate() {
        directionRotate(basicBall, basicBall.changeX, basicBall.changeY);
    }

    public Actor getBall() {
        return basicBall;
    }



}
