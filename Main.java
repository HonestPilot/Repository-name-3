package com.company;

public class Main {
    public static void main(String[] args) throws Exception {
        Screens screen = new Screens();
        screen.MainScreen();
    }
    public static void Start_Game() throws Exception {
        Game game = new Game();
        game.Play();
    }

    public static void Load_the_Game()throws Exception {
        Game game = new Game();
        game.Load_Game();
        game.Play();
    }
}