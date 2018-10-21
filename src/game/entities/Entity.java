package game.entities;

import game.draw.Sprite;

public abstract class Entity {
    private Sprite sprite;

    public abstract Sprite draw();

    public abstract void update();

    public abstract void destroy();

    public Sprite getSprite() {
        return sprite;
    }
}
