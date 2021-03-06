package com.loserland.actors;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Write a description of class com.loserland.actors.LargePaddle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class PowerSquare extends Actor{
    // declare classes and booleans
    protected Paddle paddle;
    private boolean move = true;
    private boolean shouldPause = false;
    /**
     * Act - do whatever the com.loserland.actors.LargePaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act()
    {
        if(shouldPause) return;
        //checks collision with each act
        checkCollision();
    }

    public void checkCollision() {
        paddle = (Paddle) getOneIntersectingObject(Paddle.class);
        if(paddle != null) {
            launchPower();
            // stop falling
            move = false;
            // remove powerup from world
            getWorld().removeObject(this);
        }
        if(move) {
            // fall down if didn't touch paddle
            fall();
        }
    }

    // powerup movement method
    public void fall() {
        if(getY() < getWorld().getHeight()-10) {
            // freefall if doens't collide with objects
            setLocation(getX(), getY()+3);
        }
        else {
            // if reaches bottom of world, remove object
            getWorld().removeObject(this);
        }
    }

    public void pause() {
        shouldPause = true;
    }

    public void resume() {
        shouldPause = false;
    }

    abstract void launchPower();
}
