package pokemon;

import java.io.Serializable;
import java.util.*;


public class Pokedex implements Iterable<Pokemon>, Serializable {
    private HashMap<String, Pokemon> pokemonMap; // HashMap to store Pokemon objects with their names as keys

    // Constructor to initialize the Pokedex - default constructor
    public Pokedex() {
        pokemonMap = new HashMap<>(); // Initialize the HashMap
    }
    // Constructor to initialize the Pokedex with a given Pokemon object - copy constructor
    public Pokedex(Pokedex other) {
        if (other == null) {
            other = new Pokedex(); // If the other Pokedex is null, create a new one
        }
        this.pokemonMap = new HashMap<>(); // Initialize the HashMap
        for (String name : other.pokemonMap.keySet()){
            Pokemon pokemon = other.pokemonMap.get(name); // Get the Pokemon object from the other Pokedex
            if (pokemon != null) {
                this.pokemonMap.put(name, pokemon.clone()); // Clone and add it to this Pokedex
            }
        }
    }

    public Pokemon getByName(String name) throws PokemonException {
        if (name == null || !pokemonMap.containsKey(name)) {
            throw new PokemonException(name); // If the name is null, throw an exception
        }
        return pokemonMap.get(name); // Return the Pokemon object associated with the name
    }

    public void addPokemon(Pokemon toAdd) throws PokemonException{
        if (toAdd == null) {
            return; // If the Pokemon to add is null, do nothing
        }
        if(pokemonMap.containsKey(toAdd.getName())){
            throw new PokemonException(toAdd.getName()); // If the Pokemon already exists, throw an exception
        }
        pokemonMap.put(toAdd.getName(), toAdd.clone()); // Clone and add the Pokemon to the HashMap
    }

    @Override
    public Iterator<Pokemon> iterator() {
        Collection<Pokemon> pokemons = pokemonMap.values(); // Get the values (Pokemon objects) from the HashMap
        return pokemons.iterator(); // Return an iterator for the collection of Pokemon objects
    }
}
