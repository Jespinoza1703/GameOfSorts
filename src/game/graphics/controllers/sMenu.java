package game.graphics.controllers;

import java.io.IOException;

public class sMenu extends sScene{

    @Override
    protected void initialize() throws IOException {

    }

    @Override
    void pressed_return() throws IOException {
        Interface.close();
    }
}
