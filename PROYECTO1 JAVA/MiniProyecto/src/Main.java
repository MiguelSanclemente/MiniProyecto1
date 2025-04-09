import entrenador.Entrenador;
import pokemon.Pokemon;
import pokemon.ataque.Ataque;
import pokemon.element.ElementPokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializa los datos necesarios
        ElementPokemon.initializeData();

        Entrenador entrenador = new Entrenador();
        entrenador.setNameTrainer(sc);
    }
}