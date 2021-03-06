package com.company;

public class Player {
    //attributes
    String name;
    int lvl;
    int hp;
    int max_hp;
    int strength;
    Weapon weapon;
    boolean life_status;

    //constructor
    Player(String charactername){
        name=charactername;
        hp=20;
        lvl=0;
        max_hp=20;
        strength=1;
        life_status=true;
    }

    //methods
    public String displayInfo(){
        String SomeString= ("\nName: " + name+ " HP: "+ hp +" max HP: " +max_hp+ " Strength: " +strength+ "\nPlayer LvL: "+lvl+ " Armor Damage: "+weapon.Damage_Weapon());
       return SomeString;
    }

    public int Player_HP(){
        return(hp);
    }

    public int Player_LVL(){
        return(lvl);
    }

    public void Set_Player_HP(int a){
        hp=hp-a;
    }

    public void Set_Player_LVL(){
        lvl++;
    }

    public void Set_Player_Life_Status(boolean a){
        life_status=a;
    }

    public void Change_Weapon(Weapon weap){
        this.weapon=weap;
    }

    public void Drink_Potion(){
        hp=hp+10;
    }
    public void Increase_HP(){
        max_hp=max_hp+5;
    }
    public void Increase_Strength(){
        strength=strength+2;
    }

    public int Max_Damage_Weapon() { int damage= this.weapon.max_damage; return (damage);}
}

