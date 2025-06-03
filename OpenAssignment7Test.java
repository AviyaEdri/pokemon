package pokemon;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

public class OpenAssignment7Test {
    @Test(timeout = 2000)
    public void forestAddRootTest_13P() {
        MyForest<String> forest = new MyForest<>();
        assertTrue(forest.add("Pikachu"));
    }

    @Test(timeout = 2000)
    public void forestAddChildTest_13P() {
        MyForest<String> forest = new MyForest<>();
        assertTrue(forest.add("Pikachu"));
        assertTrue(forest.add("Pikachu", "Raichu"));
    }

    @Test(timeout = 2000)
    public void forestRemoveTest_13P() {
        MyForest<String> forest = new MyForest<>();
        forest.add("Bulbasaur");
        forest.remove("Bulbasaur");
        assertFalse(forest.exists("Bulbasaur"));
    }

    @Test(timeout = 2000)
    public void forestRelatedTest_13P() {
        MyForest<String> forest = new MyForest<>();
        forest.add("Charmander");
        forest.add("Charmander", "Charmeleon");
        forest.areRelated("Charmander", "Charmeleon");
    }

    @Test(timeout = 2000)
    public void forestExistsTest_12P() {
        MyForest<String> forest = new MyForest<>();
        forest.add("Squirtle");
        assertFalse(forest.exists("Blastoise"));
    }

    @Test(timeout = 2000)
    public void forestGetTreeTest_12P() {
        MyForest<String> forest = new MyForest<>();
        forest.add("Eevee");
        MyTree<String> tree = forest.getTree("Eevee");
        assertNotNull(tree);
    }

    @Test(timeout = 2000)
    public void pokedexSerializePokedexTest_12P() {
        Pokedex pokedex = new Pokedex();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.ser"))) {
            out.writeObject(pokedex);
        } catch (IOException e) {
            Assert.fail("File writing failed - Are you sure everything that needs to be Serializable is?");
        }
    }

    @Test(timeout = 2000)
    public void pokedexGetPokemonEvolutionTreeTest_12P(){
        Pokedex pokedex = new Pokedex();
        try {
            SpecialPokemon pokemon = new SpecialPokemon("Charizard", new Type("Fire"), 20, 30, 40, 50);
            pokedex.addPokemon(pokemon);
        } catch (PokemonException e) {
            Assert.fail("No exception should be thrown");
        }
        MyTree<String> tree = pokedex.getEvolutionTreeByName("Charizard");
        Assert.assertNotNull("Should return Charizard tree", tree);
        Assert.assertEquals("Charizard should be root of tree", "Charizard", tree.getData());

    }

}
