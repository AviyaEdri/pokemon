package pokemon;

import java.util.Comparator;

public class PokemonTypeComparator implements Comparator<Pokemon>{

    @Override
    public int compare(Pokemon p1, Pokemon p2) {
        if (p1 == null && p2 == null) {
            return 0; // If both Pokemon are null, consider them equal
        }
        return p1.getType().compareTo(p2.getType()); // Compare the types of the Pokemon
    }
}
