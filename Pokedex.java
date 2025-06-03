package pokemon;

import java.io.Serializable;
import java.util.*;


public class Pokedex implements Iterable<Pokemon>, Serializable {
    private HashMap<String, Pokemon> pokemonMap; // HashMap to store Pokemon objects with their names as keys
    private MyForest<String> stringForest; // Forest to store evolution trees, if needed

    // Constructor to initialize the Pokedex - default constructor
    public Pokedex() {
        pokemonMap = new HashMap<>(); // Initialize the HashMap
        stringForest = new MyForest<>(); // Initialize the MyForest for evolution trees
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
        this.stringForest = new MyForest<>(other.stringForest); // Clone the MyForest for evolution trees
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

        String name = toAdd.getName(); // Get the name of the Pokemon to add
        stringForest.add(name); // Add the Pokemon as a new tree in the forest
    }

    @Override
    public Iterator<Pokemon> iterator() {
        Collection<Pokemon> pokemons = pokemonMap.values(); // Get the values (Pokemon objects) from the HashMap
        return pokemons.iterator(); // Return an iterator for the collection of Pokemon objects
    }

    public void addPokemon(Pokemon toAdd, String parent) throws PokemonException {
        if(toAdd == null){
            return; // If the Pokemon to add is null, do nothing
        }
        String name = toAdd.getName(); /// Get the name of the Pokemon to add

        if(pokemonMap.containsKey(name)) {
            throw new PokemonException(name); // If the Pokemon already exists, throw an exception
        }
        pokemonMap.put(name, toAdd.clone()); // Clone and add the Pokemon to the HashMap
        if (parent == null) {
            stringForest.add(name); // If parent is null, add the Pokemon as a new tree in the forest
        } else {
            if (!pokemonMap.containsKey(parent)) {
                throw new PokemonException(parent); // If the parent does not exist, throw an exception
            }
            stringForest.add(parent, name); // Add the Pokemon under the specified parent in the evolution tree
        }

    }

    public MyTree<String> getEvolutionTreeByName(String name){
        if (pokemonMap.containsKey(name) == false || name == null) {
            return null; // If the name is null or does not exist, return null
        }
        return stringForest.getTree(name); // Return the evolution tree associated with the name
    }
}
