package pokemon;

import pokemon.ataque.Ataque;
import pokemon.element.ElementPokemon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class    Pokemon {
    String namePokemon;
    short hp;
    TipoPokemon typePokemon;
    Ataque [] ataque = new Ataque[4];

    Pokemon[] pokemons = new Pokemon[3];
    private String charizard;
    ElementPokemon element = new ElementPokemon();

    public Pokemon() {

    }

    public enum TipoAtaque {
        FISICO, ESPACIAL ;
    };

    public enum TipoPokemon{
        Fuego, AGUA, TIERRA, PLANTA;
    }

    public Pokemon(String namePokemon, short HP, TipoPokemon typePokemon, Ataque[] ataque) {
        this.namePokemon = namePokemon;
        this.hp = hp;
        this.typePokemon = typePokemon;
        this.ataque = ataque;
    }

    public String getNamePokemon() {
        return namePokemon;
    }

    public void setNamePokemon(String namePokemon) {
        this.namePokemon = namePokemon;
    }

    public short getHP() {
        return hp;
    }

    public void setHP(short HP) {
        this.hp = HP;
    }

    public TipoPokemon getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(TipoPokemon typePokemon) {
        this.typePokemon = typePokemon;
    }

    public void menuPokemon(Scanner sc, String[] Trainers){
        System.out.println("como quieres jugar?: \n1. pokemones aleatorio\n2. crear tus propios pokemones " );
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1 -> {randomPokemon();}
            case 2 -> {createPokemon(sc);}
        }
    }

    public String randomTipoPokemon(){
        Random rand = new Random();
            return (TipoAtaque.values()[rand.nextInt(1,2)]).name();
    }


    public void createPokemon(Scanner sc){
        boolean flag = false;
        for (int i = 0; i < pokemons.length; i++) {
            pokemons[i] = new Pokemon();
            System.out.println("ingrese el nombre del pokemon "+ 1);
            pokemons[i].setNamePokemon(sc.nextLine());



            do {
                try {
                    System.out.println("ingrese el numero de vida del pokemon");
                    hp = sc.nextShort();
                    if(hp > 0 && hp <= 350){
                        pokemons[i].setHP(hp);
                        flag = true;
                    }else{
                        System.out.println("El numero de vida del pokemon no tiene la vida necesesaria o correspodida");
                        flag = false;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error, lo digitado no coicide", JOptionPane.ERROR_MESSAGE);
                    sc.nextLine();
                    flag = false;
                }
            }while(flag);

            System.out.println("ingrese el elemento del pokemon "+ pokemons[i].getNamePokemon());
            byte contadortipopokemon= 0;
            for(TipoAtaque tipoAtaque : TipoAtaque.values()){
                contadortipopokemon++;
                System.out.println(contadortipopokemon + " " +  tipoAtaque.name());
            }
            System.out.print("ingrese opcion: ");

            do{
                int opcion = sc.nextInt();
                switch(opcion){
                    case 1 -> {
                        pokemons[i].setTypePokemon(TipoPokemon.Fuego);
                        flag = false;
                    }
                    case 2 -> {
                        pokemons[i].setTypePokemon(TipoPokemon.AGUA);
                        flag = false;
                    }
                    case 3 -> {
                        pokemons[i].setTypePokemon(TipoPokemon.TIERRA);
                        flag = false;
                    }
                    case 4 -> {
                        pokemons[i].setTypePokemon(TipoPokemon.PLANTA);
                        flag = false;
                    }
                    default -> {
                        System.out.println("numero no valido");
                        flag = true;
                    }
                }

            }while(flag);

            System.out.println("forma de ataque : \n 1.crear tus propios ataques \n 2. hacer ataques aleatorio");
            int opcion = sc.nextInt();
            switch (opcion){
                case 1 -> {}
                case 2 -> {element.createAtaques(sc, ataque);}
            }

        }
    }

}
