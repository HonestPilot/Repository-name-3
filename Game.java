package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Game {

    private Player tom;
    private Monster[][] monsters;
    private Weapon[] weapons;
    private int score;

    Game() {
        tom = new Player(Screens.DialogName());

        monsters = new Monster[5][2];
        monsters[0][0] = new Monster("Воин-наемник орков", 10, 3, 1, 5, 4, 1, 2, 50, "У орков часто не защищены руки и ноги, зато голова с торсом - обязательно");
        monsters[0][1] = new Monster("Жук-ассасин", 10, 3, 1, 6, 3, 2, 1, 50, "Уязвимое место жуков - их лапки");
        monsters[1][0] = new Monster("Гарпия бездны", 20, 4, 2, 6, 8, 2, 4, 200, "У Гарпий достаточно сильные ноги, а к их телу тяжело подобраться. Однако, у них достаточно тонкие руки");
        monsters[1][1] = new Monster("Ликантроп", 20, 4, 2, 4, 8, 5, 3, 200, "Ликантропы не отличаются особо крепким черепом");
        monsters[2][0] = new Monster("Лорд Гоблинов", 30, 6, 3, 9, 5, 4, 3, 300, "Ликантропы не отличаются особо крепким черепом");
        monsters[2][1] = new Monster("Ведьма Мойра", 30, 6, 3, 1, 4, 6, 10, 300, "Атаковать руки и ноги ведьмы - непростая задача. Но, имея дело со столь хрупкой девушкой, стоит помнить, что голова у нее не защищена");
        monsters[3][0] = new Monster("Заклинатель вампиров", 50, 8, 4, 3, 10, 7, 3, 400, "У заклинателя вампиров крепкое мускулистое тело, но за долгие годы скитаний его ноги совсем ослабли");
        monsters[3][1] = new Monster("Кровожадный палач", 50, 8, 4, 5, 7, 8, 3, 400, "У заклинателя вампиров крепкое мускулистое тело, но за долгие годы скитаний его ноги совсем ослабли");
        monsters[4][0] = new Monster("Минотавр", 70, 10, 5, 15, 12, 5, 8, 1000, "Минотавр всегда имел крепкий череп и невероятно сильное тело. Однако, руки - его слабое место");
        monsters[4][1] = new Monster("Королева банши", 70, 10, 5, 15, 4, 14, 7, 1000, "Королева банши всегда защищает свое личико, а ее руки надежно охвачены потоками темной магии. Стоит попробовать атаковать ее прямо в живот");
        monsters[0][0].displayInfo();

        weapons = new Weapon[11];
        weapons[0] = new Weapon("Меч архангела", 3, 10, 40);
        weapons[1] = new Weapon("Меч волшебного тумана", 2, 12, 40);
        weapons[2] = new Weapon("Эльфийский меч", 1, 11, 60);
        weapons[3] = new Weapon("Катана", 4, 13, 50);
        weapons[4] = new Weapon("Меч кошмаров", 8, 13, 30);
        weapons[5] = new Weapon("Громовержец", 5, 14, 40);
        weapons[6] = new Weapon("Меч стихий", 6, 15, 70);
        weapons[7] = new Weapon("Драконий лук", 10, 18, 50);
        weapons[8] = new Weapon("Копье вечности", 10, 20, 60);
        weapons[9] = new Weapon("Клинок Убийца драконов", 15, 30, 50);
        weapons[10] = new Weapon("Экскалибур", 20, 35, 70);
        tom.weapon = weapons[0];
    }

    //methods
    public void Load_Game() throws Exception {
        SaveName();
        String File_name = tom.name;
        try {
            DataInputStream dos = new DataInputStream(new FileInputStream(File_name + ".bin"));
            try {
                this.tom.life_status = dos.readBoolean();
                this.tom.lvl = dos.readInt();
                this.tom.hp = dos.readInt();
                this.tom.max_hp = dos.readInt();
                this.tom.strength = dos.readInt();
                this.tom.name = dos.readUTF();
                int damage=dos.readInt();
                for (int i=0;i<=10;i++){
                    if (damage==weapons[i].max_damage){this.tom.Change_Weapon(weapons[i]);}
                }
                this.score = dos.readInt();
                this.tom.displayInfo();
            } catch (Throwable var11) {
                try {
                    dos.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }
                throw var11;
            }
            dos.close();
        } catch (IOException var12) {
            System.out.println(var12.getMessage());
        }
        if (tom.life_status == false) {
            Screens screen = new Screens();
            screen.MainScreen();
        }
    }

    public void Save_Game() throws Exception {
        String File_name = this.tom.name;
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(File_name + ".bin"));
            try {
                dos.writeBoolean(this.tom.life_status);
                dos.writeInt(this.tom.lvl);
                dos.writeInt(this.tom.hp);
                dos.writeInt(this.tom.max_hp);
                dos.writeInt(this.tom.strength);
                dos.writeUTF(this.tom.name);
                dos.writeInt(this.tom.Max_Damage_Weapon());
                dos.writeInt(this.score);
                System.out.println("File has been written");
            } catch (Throwable var6) {
                try {
                    dos.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            dos.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }

    }

    public void Play() throws Exception {

        while (tom.Player_LVL() < 5 && tom.life_status == true) {
            Open_Door();
            if (tom.Player_LVL() == 5) {
                Screens.DialogWin(tom.name, score);
                Screens screen = new Screens();
                screen.MainScreen();
                break;
            }
        }
    }


    private void Open_Door() throws Exception {
        Screens.DialogDoor();
        int door = (int) (Math.random() * 2);
        switch (door) {
            case 0:
                Fight_with_Monster();
                break;
            case 1:
                Loot();
                break;
            default:
                break;
        }
    }

    private int Set_Monster() {
        int monster_number = (int) (Math.random() * 2);
        return monster_number;
    }

    private void Upgrade() {
        switch (Screens.DialogUpgrade()) {
            case "Здоровье HP": {
                tom.Increase_HP();
            }
            break;
            case "Сила": {
                tom.Increase_Strength();
            }
            break;
        }
    }

    private void Fight_with_Monster() throws Exception {
        //выбор монстра на рандоме
        int monster_number = Set_Monster();
        int monster_lvl = tom.Player_LVL();
        //пока кто-то не умрет
        Screens.DialogAboutMonster(monsters[monster_lvl][monster_number].name, monsters[monster_lvl][monster_number].information);
        while (tom.Player_HP() > 0 && monsters[monster_lvl][monster_number].hp > 0) {
            //ход игрока
            String Action = Screens.DialogMonster(tom.displayInfo(), monsters[monster_lvl][monster_number].displayInfo());
            switch (Action) {
                case "Ударить в голову":
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Head);
                    break;
                case "Ранить тело":
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Body);
                    break;
                case "Сломать колени":
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Legs);
                    break;
                case "Укусить за руку":
                    monsters[monster_lvl][monster_number].Set_Monster_HP(tom.strength + weapons[0].Weapon_Damage() / monsters[monster_lvl][monster_number].Resist_Arms);
                    break;
                case "Выпить исцеляющее зелье": tom.Drink_Potion();if (tom.hp > tom.max_hp) { tom.hp = tom.max_hp; }
                    break;

            }
            //ход монстра
            tom.Set_Player_HP(monsters[monster_lvl][monster_number].strength);
            monsters[monster_lvl][monster_number].displayInfo();
            tom.displayInfo();
        }
        if (tom.Player_HP() <= 0) {
            Screens.DialogLose(tom.name, score);
            tom.Set_Player_Life_Status(false);
            Screens screen = new Screens();
            screen.MainScreen();
        }
        if (monsters[monster_lvl][monster_number].hp <= 0) {
            tom.Set_Player_Life_Status(true);
            score += monsters[monster_lvl][monster_number].score;
            Upgrade();
            tom.Set_Player_LVL();
            tom.displayInfo();
            CheckPoint();
        }
    }

    private void CheckPoint() throws Exception {
        String Check=Screens.DialogSaveOrNot();
        if (Check.equals("Сохранить игру и выйти")){
            AddSaveNames();
            Save_Game();
            tom.Set_Player_Life_Status(false);
            Screens screen = new Screens();
            screen.MainScreen();
        }
        else if (Check.equals("Выйти без сохранения")) {
            tom.Set_Player_Life_Status(false);
            Screens screen = new Screens();
            screen.MainScreen();
        }
        else {
             AddSaveNames();
             Save_Game();
        }
    }

    private void Loot() {
        String message = null;
        Weapon new_weapon;
        int max_score = 100, min_score = 20;
        int i = (int) (Math.random() * 11);
        new_weapon = weapons[i];
        if (new_weapon.max_damage > tom.weapon.max_damage) {
            tom.Change_Weapon(new_weapon);
            message = "Ты сменил оружие на более сильное";
        } else if (new_weapon.max_damage == tom.weapon.max_damage) {
            message = "Фи, то же самое оружие, что у тебя в руке";
        } else {
            message = "Твоё оружие лучше того, что ты нашел";
        }
        int diff = max_score - min_score;
        Random random = new Random();
        int new_score = random.nextInt(diff + 1);
        new_score += min_score;
        score += new_score;
        Screens.DialogLoot(tom.weapon.name, message, score);
        tom.displayInfo();
    }

    public void AddSaveNames() throws IOException{
        FileWriter writer = new FileWriter("SaveName.txt", true);
        String text = tom.name+'\n';
        writer.write(text);
        writer.flush();
    }

    public void SaveName() throws FileNotFoundException {
        Scanner NewScan = new Scanner(new File("SaveName.txt"));
        ArrayList<String> Name = new ArrayList<>();
        int NumberNames = 0;
        while (NewScan.hasNextLine()) {
            Name.add(NewScan.nextLine());
            NumberNames++;
        };
        boolean Checker=false;
        for (int i = 0; i < NumberNames; i++) {
            if((Name.get(i)).equals(tom.name)){
                tom.Set_Player_Life_Status(true);
                Checker=true;
            }
        }
        if(!Checker) {
            tom.Set_Player_Life_Status(false);
        }
    }
}