package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        //Start_Game();
        Screens screen = new Screens();
        screen.MainScreen();
       // Load_the_Game();
    }

    public static void Start_Game() throws Exception {
        Game game = new Game();
        game.Play();
    }

    public static void Save_the_Game() throws Exception{
        Game game = new Game();
        game.Save_Game();
        game.Play();
    }

    public static void Load_the_Game()throws Exception {
        Game game = new Game();
        game.Load_Game();
        game.Play();
    }
    
    public static void Hall_of_Fame() {

    }
}



