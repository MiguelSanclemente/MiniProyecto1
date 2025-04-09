package entrenador;

import pokemon.Pokemon;

import java.util.Scanner;

public class Entrenador {
    String[] Trainers = new String[2];
    static int contarTrainer;

    public String getNameTrainer() {
        return "entrenador "+ Trainers[contarTrainer];
    }

    public String toString(){
        return "entrenador "+ Trainers[contarTrainer];
    }

    public void setNameTrainer(Scanner sc) {
        Pokemon pokemon = new Pokemon();
        for (int i = 0; i < Trainers.length; i++) {
            System.out.println("Ingrese el nombre del entrenador " + String.format("%02d", i + 1));
            Trainers[i] = sc.nextLine();
            pokemon.menuPokemon(sc, Trainers);
        }
    }
}
