package pokemon;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.*;

public class OpenAssignment5Test {
    SpecialPokemon GetCharizard() {
        try {
            return new SpecialPokemon("Charizard", new Type("Fire"), 20, 30, 40, 50);
        } catch (PokemonException ignored) {
        }
        return null;
    }

    @Test(timeout = 2000)
    public void PokemonErrorNameExceptioninCtrTest_20P() {
        boolean eThrow = false;
        try {
            new Pokemon(null, new Type("Water"), 20, 30, 40);
        } catch (PokemonException e) {
            assertEquals("Problem with PokemonException", "bad name: Empty", e.getMessage());
            eThrow = true;
        }
        if (!eThrow) {
            Assert.fail("No exception was thrown");
        }
    }

    @Test(timeout = 2000)
    public void PokemonErrorNameExceptionInPokedexTest_20P() {
        boolean eThrow = false;
        Pokedex pokedex = new Pokedex();
        try {
            pokedex.getByName("Charmander");
        } catch (PokemonException e) {
            assertEquals("Problem with PokemonException", "bad name: Charmander", e.getMessage());
            eThrow = true;
        }
        if (!eThrow) {
            Assert.fail("No exception was thrown");
        }
    }

    @Test(timeout = 2000)
    public void PokemonErrorIntegerExceptionTest_15P() {
        boolean eThrow = false;
        try {
            new Pokemon("Pikachu", new Type("Electric"), -1, 10, 10);
        } catch (PokemonException e) {
            assertEquals("Problem with PokemonException", "bad parameter: -1", e.getMessage());
            eThrow = true;
        }
        if (!eThrow) {
            Assert.fail("No exception was thrown");
        }
    }

    @Test(timeout = 2000)
    public void SaveToTextFileTest_15P() throws PokemonException {
        SpecialPokemon charizard = GetCharizard();
        File testTxt = new File("charizard.text");
        charizard.saveToFile(testTxt);
        Assert.assertTrue("error on saveToTextfile", testTxt.exists());
    }

    @Test(timeout = 2000)
    public void ReadFromTextFileTest_15P() throws PokemonException {
        File testTxt = new File("test.txt");
        SpecialPokemon pokemon = new SpecialPokemon(testTxt);
        Assert.assertEquals("reading did not work", GetCharizard(), pokemon);
    }

    @Test(timeout = 2000)
    public void SerializePokedexTest_15P() {
        Pokedex pokedex = new Pokedex();
        Pokedex pokedex2 = null;
        try {
            pokedex.addPokemon(GetCharizard());
        } catch (PokemonException e) {
            Assert.fail("Should not fail adding a pokemon");
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pokemon/test.ser"))) {
            out.writeObject(pokedex);
        } catch (IOException e) {
            Assert.fail("File writing failed - Are you sure everything that needs to be Serializable is?");
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("pokemon/test.ser"))) {
            pokedex2 = (Pokedex) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Assert.fail("File reading failed");
        }
        Pokemon pokemon = null;
        try {
            pokemon = pokedex2.getByName("Charizard");
        } catch (PokemonException e) {
            Assert.fail("Charizard should be in read Pokedex");
        }

        Assert.assertEquals("reading did not work", GetCharizard(), pokemon);
    }

    @Test
    public void duplicatePokemonInPokedex_shouldThrowException() throws PokemonException {
        Pokedex pokedex = new Pokedex();
        Pokemon p = new SpecialPokemon("Pikachu", new Type("Electric"), 10, 10, 10, 2);
        try {
            pokedex.addPokemon(p);
            pokedex.addPokemon(p); // נסיון להוסיף שוב
            fail("Expected PokemonException for duplicate");
        } catch (PokemonException e) {
            assertEquals("bad name: Pikachu", e.getMessage());
        }
    }
    @Test
    public void getMissingPokemon_shouldThrowException() {
        Pokedex pokedex = new Pokedex();
        try {
            pokedex.getByName("MissingNo");
            fail("Expected PokemonException for missing name");
        } catch (PokemonException e) {
            assertEquals("bad name: MissingNo", e.getMessage());
        }
    }
    @Test
    public void invalidSpecialPokemon_shouldThrowExceptionOnSpecialAttack() {
        try {
            new SpecialPokemon("Darkrai", new Type("Dark"), 20, 30, 40, -5);
            fail("Expected PokemonException for negative special attack");
        } catch (PokemonException e) {
            assertEquals("bad parameter: -5", e.getMessage());
        }
    }
    @Test
    public void equalsIdenticalPokemon_shouldBeTrue() throws PokemonException {
        SpecialPokemon p1 = new SpecialPokemon("Zapdos", new Type("Electric"), 20, 20, 100, 3);
        SpecialPokemon p2 = new SpecialPokemon("Zapdos", new Type("Electric"), 20, 20, 100, 3);
        assertTrue(p1.equals(p2));
    }
    @Test
    public void loadPokemonFromValidFile_shouldMatchOriginal() throws Exception {
        SpecialPokemon original = new SpecialPokemon("Lugia", new Type("Psychic"), 25, 35, 120, 4);
        File file = new File("lugia.txt");
        original.saveToFile(file);

        SpecialPokemon loaded = new SpecialPokemon(file);
        assertEquals(original, loaded);
    }
    @Test
    public void emptyName_shouldThrowCorrectException() {
        try {
            new Pokemon("", new Type("Grass"), 10, 10, 10);
            fail("Expected exception for empty name");
        } catch (PokemonException e) {
            assertEquals("bad name: Empty", e.getMessage());
        }
    }

    @Test
    public void typeWithNullOrEmpty_shouldNotThrow() {
        try {
            Type t1 = new Type(null);
            Type t2 = new Type("");
            assertNotNull(t1.getType());
            assertNotNull(t2.getType());
        } catch (Exception e) {
            fail("Type constructor should handle null or empty without throwing");
        }
    }
    @Test
    public void getByName_caseInsensitive_shouldFailUnlessHandled() throws PokemonException {
        Pokedex pokedex = new Pokedex();
        Pokemon p = new Pokemon("Pikachu", new Type("Electric"), 10, 10, 10);
        try {
            pokedex.addPokemon(p);
            pokedex.getByName("pikachu"); // אות קטנה
            fail("Expected PokemonException due to case sensitivity");
        } catch (PokemonException e) {
            assertEquals("bad name: pikachu", e.getMessage());
        }
    }
    @Test
    public void clonedPokemon_shouldBeEqualToOriginal() throws PokemonException {
        SpecialPokemon original = new SpecialPokemon("Mewtwo", new Type("Psychic"), 50, 40, 120, 4);
        SpecialPokemon clone = original.clone();
        assertEquals("Clone should be equal to original", original, clone);
    }
    @Test
    public void nameWithSpacesOrSymbols_shouldBeHandledNormally() throws PokemonException {
        Pokemon p = new Pokemon("Mr. Mime!", new Type("Psychic"), 15, 10, 40);
        assertEquals("Mr. Mime!", p.getName());
    }
    @Test
    public void saveAndLoadNameWithSpaces_shouldPreserveName() throws Exception {
        SpecialPokemon p = new SpecialPokemon("Mr. Mime", new Type("Psychic"), 15, 15, 45, 3);
        File file = new File("mr_mime.txt");
        p.saveToFile(file);

        SpecialPokemon loaded = new SpecialPokemon(file);
        assertEquals("Mr. Mime", loaded.getName());
    }




}
