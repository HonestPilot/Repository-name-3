package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;

public class Screens{
    private Main main;

    public void MainScreen() {
        int bx = 180, by = 35;
        int wid = 540, hei =360;
        JFrame Window = new JFrame("Game"); //создание окна с названием "Game"
        Window.setPreferredSize(new Dimension(wid, hei)); //Размеры окна
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //закрытие окна - прекращение работы программы
        JPanel Panel = new JPanel(null); //задаём панель, на котором можно размещать другие элементы
        // Установка абсолютного позиционирования
        Window.setVisible(true); // видимость фигуры
        Panel.setBackground(Color.pink);
        Window.add(Panel); //добавляем панель к фрейму
        JButton button1 = new JButton("Начать игру");
        button1.setBounds(bx, by, 150, 35);
        Panel.add(button1);
        JButton button2 = new JButton("Загрузить игру");
        button2.setBounds(bx, by + 50, 150, 35);
        Panel.add(button2);
        JButton button3 = new JButton("Доска почёта");
        button3.setBounds(bx, by + 100, 150, 35);
        Panel.add(button3);
        JButton button4 = new JButton("Справка");
        button4.setBounds(bx, by + 150, 150, 35);
        Panel.add(button4);
        JButton button5 = new JButton("Выход");
        button5.setBounds(bx, by + 200, 150, 35);
        Panel.add(button5);
        Panel.setVisible(true);
        Window.pack(); //объединяем в пакет

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Window.setVisible(false);
                    main.Start_Game();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Window.setVisible(false);
                    main.Load_the_Game();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.Hall_of_Fame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DialogAboutGame();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.setVisible(false);
                System.exit(0);
            }
        });

    }
    public static String DialogName(){
        return showInputDialog("Имя игрока:");
            }
    public static String DialogDoor(){
       String[] door  = {"Первая дверь", "Вторая дверь"};
        Object result=showInputDialog(null, "Сделай правильный выбор, герой!","Двери",JOptionPane.PLAIN_MESSAGE,null,door,null);
        return String.valueOf(result);
    }
    public static String  DialogMonster(String PlayerInfo, String MonsterName){
         String[] punch  = {"Ударить по голове", "Ранить тело", "Сломать колени", "Укусить за руку", "Выписть исцеляющее зелье"};
        Object result=showInputDialog(null,"Настало время битвы! Противник: "+MonsterName+"!\nЧто будешь делать?\nСтатус героя: "+PlayerInfo,"Бой",JOptionPane.PLAIN_MESSAGE,null,punch,null);
         return String.valueOf(result);
    }

  public static void  DialogLoot(String Message, int Score){
      showMessageDialog(null,Message+ "\nЧисло очков: "+ Score);
  }

    public static String  DialogUpgrade(){
        String[] Choose  = {"Здоровье HP", "Сила"};
        Object result=showInputDialog(null,"Ты выжил! Выбери, что желаешь усилить!","Передышка",JOptionPane.PLAIN_MESSAGE,null,Choose,null);
        return String.valueOf(result);
    }

    public static void  DialogLose(String name, int score){
        showInputDialog(null,"Игра окончена, герой "+name+"!\nСчёт:"+score+" \nПоследние слова:");
    }

    public static void  DialogWin(String name, int score){
        showInputDialog(null,"Ты победил, герой "+name+"!\nСчёт:"+score+"\nСкажи пару слов на прощанье:\n");
    }
    public static void  DialogAboutMonster(String MonsterName, String MonsterInfo){
        showMessageDialog(null,"За этой дверью был " +MonsterName+ "\nЧисло очков: "+ MonsterInfo);
    }
    public static String  DialogHerosWords(String Name, String Message, int Score){
        String[] Texts={" однажды сказал: ", " просто подумал, что  ", " высек на камне: "," написал книгу о своём приключении\nпод названием:",", чей девиз : "};
        int i = (int)(Math.random()*5);
        String Sometext = Texts[i];
        String result = Name+ Sometext +Message+ "\nЧисло очков: "+ Score;
        showMessageDialog(null,result);
        return String.valueOf(result);
    }
    public static void  DialogAboutGame(){
        String Message = "Добро пожаловать в игру Подземелья! Каждый раунд на выбор тебе дается две двери:\n" +
                "за одной из них находятся сокровища (золотые монеты или оружие), а за другой тебя ждет битва с монстром!\n" +
                "Каждый монстр имеет свой уникальный запас здоровья, силу, и имеет разные уязвимые места. Для победы над монстром\n" +
                "тебе понадобится применить смекалку и свои стратегические способности. Каждый ход монстра можно ударить по одной\n" +
                "из четырех болевых точек. Или же можно выпить исцеляющее зелье, но это займет целый ход. При успешном поражении монстра\n" +
                "тебе будет предложен выбор: увеличить свой запас здоровья или усилить мощность удара. Также за каждый выигранный бой\n" +
                "повышается и твой уровень. Если же победа была одержана монстром, единственное, что тебе остается - не отчаиваться и\n" +
                "оставить парочку слов напоследок для доски почета. Всего в игре 5 уровней, и, чтобы пройти игру, нужно одолеть всех\n" +
                "монстров, повстречавшихся тебе на пути. Желаем удачи!";
        showMessageDialog(null,Message);
    }
}


