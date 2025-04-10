import entrenador.Entrenador;
import pokemon.Pokemon;
import pokemon.element.ElementPokemon;
import Batalla.Batalla;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializa los datos necesarios
        ElementPokemon.initializeData();

        // Crear entrenadores y configurar sus equipos
        Entrenador entrenador = new Entrenador();
        entrenador.setNameTrainer(sc);

        String nombreEntrenador1 = entrenador.getTrainers()[0];
        String nombreEntrenador2 = entrenador.getTrainers()[1];

        // Seleccionar equipos de Pokémon
        Pokemon[] equipo1 = {ElementPokemon.getPokemon()[0], ElementPokemon.getPokemon()[1], ElementPokemon.getPokemon()[2]};
        Pokemon[] equipo2 = {ElementPokemon.getPokemon()[3], ElementPokemon.getPokemon()[4], ElementPokemon.getPokemon()[5]};

        // Iniciar la batalla
        Batalla batalla = new Batalla();
        batalla.iniciarBatalla(nombreEntrenador1, equipo1, nombreEntrenador2, equipo2, sc);
    }
}