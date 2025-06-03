package pokemon;
/**
 * PokemonException is a custom exception class that extends the Exception class.
 * It is used to handle specific exceptions related to Pokemon operations.
 */
public class PokemonException extends Exception{
    public PokemonException(String pokemonName){
        super(getMessage(pokemonName)); // Call the constructor of the superclass (Exception) with a custom message
    }
    /**
     * This method generates a custom message based on the provided Pokemon name.
     * @param pokemonName The name of the Pokemon.
     * @return A custom message indicating the issue with the Pokemon name.
     */
    private static String getMessage(String pokemonName) {
        if(pokemonName == null || pokemonName.isEmpty()){
            return "bad name: Empty"; // Return a custom message if the name is null or empty
        }
        else {
            return "bad name: " + pokemonName; // Return a custom message with the provided name
        }
    }

    public PokemonException(int parameter){
        super("bad parameter: " + parameter); // Call the constructor of the superclass (Exception) with a custom message
    }
}
