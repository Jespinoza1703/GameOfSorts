package game.entities;

import game.draw.Sprite;

public abstract class Entity {

    public abstract Sprite draw();

    public abstract void update();

    public abstract void destroy();

    public abstract Sprite getSprite();

    public abstract void hit();
}
