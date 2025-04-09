package pokemon;

import pokemon.ataque.Ataque;
import pokemon.element.ElementPokemon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;


public class    Pokemon {
    String namePokemon;
    short hp;
    TipoPokemon typePokemon;
    Ataque [] ataque = new Ataque[4];

    Pokemon[] pokemons = new Pokemon[3];
    ElementPokemon element = new ElementPokemon();
    private Pokemon[] pokemon;

    public Pokemon() {

    }

    public enum TipoAtaque {
        FISICO, ESPACIAL ;
    };

    public enum TipoPokemon{
        Fuego(new String[]{"PLANTA"}),
        AGUA(new String[]{"FUEGO"}),
        TIERRA(new String[]{"ELECTRICO"}),
        PLANTA(new String[]{"AGUA", "ELECTRICO", "TIERRA"}),
        ELECTRICO(new String[]{"AGUA"});

        public String[] strong;
        TipoPokemon(String[] strong){
            this.strong = strong;
        }

        String[] getStrong(){
            return strong;
        }

    }

    public Pokemon(String namePokemon, short hp, TipoPokemon typePokemon, Ataque[] ataque) {
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

    public Pokemon[] getPokemon() {
        return pokemon;
    }

    public void menuPokemon(Scanner sc, String[] Trainers){
        System.out.println("como quieres jugar?: \n1. pokemones aleatorio\n2. crear tus propios pokemones " );
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
            case 1 -> {randomPokemon();}
            case 2 -> {createPokemon(sc);}
        }
    }
 
    // Implementación del método randomPokemon 
    private void randomPokemon() {
        try {
            System.out.println("Generando un equipo aleatorio...");
            Pokemon[] randomTeam = randomPokemonTeam(3); 

            System.out.println("Equipo generado:");
            for (Pokemon p : randomTeam) {
                System.out.println(p.getNamePokemon() + " - Tipo: " + p.getTypePokemon());
            }
        } catch (Exception e) {
            System.out.println("Error al generar el equipo aleatorio: " + e.getMessage());
        }
    }

    // Método para generar un equipo aleatorio de Pokémon
    public Pokemon[] randomPokemonTeam(int teamSize) {
        ElementPokemon.initializeData();

        
        Pokemon[] allPokemons = (Pokemon[]) element.getPokemon();
        if (teamSize > allPokemons.length) {
            throw new IllegalArgumentException("El tamaño del equipo no puede ser mayor que el número de Pokémon disponibles.");
        }

        Pokemon[] team = new Pokemon[teamSize];
        List<Pokemon> availablePokemons = new ArrayList<>(Arrays.asList(allPokemons));
        Random random = new Random();

        for (int i = 0; i < teamSize; i++) {
            int randomIndex = random.nextInt(availablePokemons.size());
            team[i] = availablePokemons.remove(randomIndex);
        }

        return team;
    }

    public String randomTipoPokemon(){
        Random rand = new Random();
            return (TipoAtaque.values()[rand.nextInt(1,2)]).name();
    }


    public void createPokemon(Scanner sc) {
        boolean flag = false;
        for (int i = 0; i < pokemons.length; i++) {
            pokemons[i] = new Pokemon();
            System.out.println("Ingrese el nombre del Pokémon " + (i + 1) + ":");
            pokemons[i].setNamePokemon(sc.nextLine());

            do {
                try {
                    System.out.println("Ingrese el número de vida del Pokémon: (1hp-350hp)");
                    hp = sc.nextShort();
                    if (hp > 0 && hp <= 350) {
                        pokemons[i].setHP(hp);
                        flag = false;
                    } else {
                        System.out.println("El número de vida del Pokémon no es válido. Debe estar entre 1 y 350.");
                        flag = true;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error, lo digitado no coincide", JOptionPane.ERROR_MESSAGE);
                    sc.nextLine();
                    flag = true;
                }
            } while (flag);

            System.out.println("Ingrese el tipo del Pokémon " + pokemons[i].getNamePokemon() + ":");
            byte contadorTipoPokemon = 0;
            for (TipoPokemon tipoPokemon : TipoPokemon.values()) {
                contadorTipoPokemon++;
                System.out.println(contadorTipoPokemon + ". " + tipoPokemon.name());
            }
            System.out.print("Ingrese opción: ");

            do {
                int opcion = sc.nextInt();
                switch (opcion) {
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
                    case 5 -> {
                        pokemons[i].setTypePokemon(TipoPokemon.ELECTRICO);
                        flag = false;
                    }
                    default -> {
                        System.out.println("Número no válido. Intente de nuevo.");
                        flag = true;
                    }
                }
            } while (flag);

            System.out.println("Forma de ataque: \n1. Crear tus propios ataques \n2. Hacer ataques aleatorios");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    Ataque[] ataquesPersonalizados = crearAtaquesPersonalizados(sc);
                    pokemons[i].ataque = ataquesPersonalizados;
                    System.out.println("Ataques personalizados asignados:");
                    for (Ataque ataque : ataquesPersonalizados) {
                        System.out.println("- " + ataque.getNameAtaque());
                    }
                }
                case 2 -> {
                    // Obtener ataques aleatorios del tipo correspondiente
                    Ataque[] ataquesAleatorios = obtenerAtaquesAleatorios(pokemons[i].getTypePokemon());
                    pokemons[i].ataque = ataquesAleatorios;
                    System.out.println("Ataques asignados aleatoriamente:");
                    for (Ataque ataque : ataquesAleatorios) {
                        System.out.println("- " + ataque.getNameAtaque());
                    }
                }
            }
        }
    }

    // Método para obtener 4 ataques aleatorios del tipo correspondiente
    private Ataque[] obtenerAtaquesAleatorios(TipoPokemon tipoPokemon) {
        Ataque[] ataquesDisponibles;
        switch (tipoPokemon) {
            case Fuego -> ataquesDisponibles = ElementPokemon.getFireMoves();
            case AGUA -> ataquesDisponibles = ElementPokemon.getWaterMoves();
            case TIERRA -> ataquesDisponibles = ElementPokemon.getGroundMoves();
            case PLANTA -> ataquesDisponibles = ElementPokemon.getGrassMoves();
            case ELECTRICO -> ataquesDisponibles = ElementPokemon.getElectricMoves();
            default -> throw new IllegalArgumentException("Tipo de Pokémon no válido");
        }

        if (ataquesDisponibles == null || ataquesDisponibles.length < 4) {
            throw new IllegalStateException("No hay suficientes ataques disponibles para este tipo de Pokémon.");
        }

        List<Ataque> listaAtaques = new ArrayList<>(Arrays.asList(ataquesDisponibles));
        Collections.shuffle(listaAtaques); // Mezcla los ataques
        return listaAtaques.subList(0, 4).toArray(new Ataque[4]); // Selecciona los primeros 4
    }

    private Ataque[] crearAtaquesPersonalizados(Scanner sc) {
        Ataque[] ataques = new Ataque[4];
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingrese el nombre del ataque " + (i + 1) + ":");
            String nombreAtaque = sc.nextLine();

            System.out.println("Seleccione el tipo de ataque:");
            System.out.println("1. FISICO");
            System.out.println("2. ESPACIAL");
            int tipoOpcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea
            Pokemon.TipoAtaque tipoAtaque = (tipoOpcion == 1) ? Pokemon.TipoAtaque.FISICO : Pokemon.TipoAtaque.ESPACIAL;

            int poder;
            do {
                if (tipoAtaque == Pokemon.TipoAtaque.FISICO) {
                    System.out.println("Ingrese el poder del ataque físico (máximo 100):");
                } else {
                    System.out.println("Ingrese el poder del ataque especial (máximo 150):");
                }
                poder = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea

                if ((tipoAtaque == Pokemon.TipoAtaque.FISICO && poder > 0 && poder <= 100) ||
                    (tipoAtaque == Pokemon.TipoAtaque.ESPACIAL && poder > 0 && poder <= 150)) {
                    break;
                } else {
                    System.out.println("El poder del ataque no es válido. Intente de nuevo.");
                }
            } while (true);

            ataques[i] = new Ataque(nombreAtaque, tipoAtaque, (byte) poder);
        }
        return ataques;
    }
}
