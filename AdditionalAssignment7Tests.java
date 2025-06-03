
package pokemon;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdditionalAssignment7Tests {

    @Test
    public void testAddToNonexistentParent() {
        MyForest<String> forest = new MyForest<>();
        assertFalse(forest.add("MissingParent", "Eevee"));
    }

    @Test
    public void testAddDuplicateElement() {
        MyForest<String> forest = new MyForest<>();
        assertTrue(forest.add("Pikachu"));
        assertFalse(forest.add("Pikachu")); // כפול
    }

    @Test
    public void testRemoveRoot() {
        MyForest<String> forest = new MyForest<>();
        forest.add("Mew");
        assertTrue(forest.remove("Mew"));
        assertFalse(forest.exists("Mew"));
    }

    @Test
    public void testRemoveChild() {
        MyForest<String> forest = new MyForest<>();
        forest.add(null, "Bulbasaur");
        forest.add("Bulbasaur", "Ivysaur");
        forest.add("Ivysaur", "Venusaur");

        assertTrue(forest.remove("Ivysaur"));
        assertFalse(forest.exists("Ivysaur"));
        assertFalse(forest.exists("Venusaur"));
        assertTrue(forest.exists("Bulbasaur"));
    }

    @Test
    public void testAreRelated() {
        MyForest<String> forest = new MyForest<>();
        forest.add(null, "Abra");
        forest.add("Abra", "Kadabra");
        forest.add("Kadabra", "Alakazam");

        assertTrue(forest.areRelated("Abra", "Kadabra"));
        assertTrue(forest.areRelated("Kadabra", "Abra"));
        assertTrue(forest.areRelated("Abra", "Alakazam"));
        assertFalse(forest.areRelated("Abra", "Pikachu"));
    }

    @Test
    public void testGetTree() {
        MyForest<String> forest = new MyForest<>();
        forest.add(null, "Squirtle");
        forest.add("Squirtle", "Wartortle");
        forest.add("Wartortle", "Blastoise");

        MyTree<String> tree = forest.getTree("Blastoise");
        assertNotNull(tree);
        assertEquals("Squirtle", tree.getData());
    }

    @Test
    public void testAddPokemonWithParent() throws PokemonException {
        Pokedex pokedex = new Pokedex();
        pokedex.addPokemon(new Pokemon("Charmander", new Type("Fire"), 10, 10, 10));
        pokedex.addPokemon(new Pokemon("Charmeleon", new Type("Fire"), 20, 20, 20), "Charmander");

        MyTree<String> tree = pokedex.getEvolutionTreeByName("Charmeleon");
        assertNotNull(tree);
        assertTrue(tree.exists("Charmander"));
    }

    @Test
    public void testGetTreeForNonexistentPokemon() {
        Pokedex pokedex = new Pokedex();
        MyTree<String> tree = pokedex.getEvolutionTreeByName("Missingmon");
        assertNull(tree);
    }

    @Test
    public void testFullEvolutionTree() throws PokemonException {
        Pokedex pokedex = new Pokedex();
        pokedex.addPokemon(new Pokemon("Gastly", new Type("Ghost"), 10, 10, 10));
        pokedex.addPokemon(new Pokemon("Haunter", new Type("Ghost"), 20, 20, 20), "Gastly");
        pokedex.addPokemon(new Pokemon("Gengar", new Type("Ghost"), 30, 30, 30), "Haunter");

        MyTree<String> tree = pokedex.getEvolutionTreeByName("Gengar");
        assertEquals("Gastly", tree.getData());
        assertTrue(tree.exists("Haunter"));
        assertTrue(tree.exists("Gengar"));
    }
}
