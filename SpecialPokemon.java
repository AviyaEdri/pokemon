package pokemon;

import java.io.*;

public class SpecialPokemon extends Pokemon implements Serializable{
    private int specialAttack; // Special attack value

    public SpecialPokemon(String name,Type type,int attack,int defense,int health, int specialAttack) throws PokemonException {
        super(name, type, attack, defense, health); // Call the constructor of the superclass (Pokemon)

        if (specialAttack < 0) { // Check if special attack is less than 0
            throw new PokemonException(specialAttack); // Throw an exception if it is
        }
        this.specialAttack = specialAttack; // Otherwise, set it to the provided value
    }

    public SpecialPokemon(File file) throws PokemonException{
        BufferedReader reader = null; // Initialize the BufferedReader
        try {
            reader = new BufferedReader(new FileReader(file)); // Create a new BufferedReader to read the file
            String name = reader.readLine().split(": ")[1]; // Read the first line of the file
            String type = reader.readLine().split(": ")[1]; // Read the second line of the file
            String attack = reader.readLine().split(": ")[1]; // Read the third line of the file
            String defense =reader.readLine().split(": ")[1]; // Read the fourth line of the file
            String health = reader.readLine().split(": ")[1]; // Read the fifth line of the file
            String specialAttack = reader.readLine().split(": ")[1]; // Read the sixth line of the file


            this.setName(name); // Set the name of the Pokemon
            this.setType(new Type(type)); // Set the type of the Pokemon
            this.setAttack(Integer.parseInt(attack)); // Set the attack of the Pokemon
            this.setDefense(Integer.parseInt(defense)); // Set the defense of the Pokemon
            this.setHealth(Integer.parseInt(health)); // Set the health of the Pokemon

            if (Integer.parseInt(specialAttack) < 0) { // Check if special attack is less than 0
                throw new PokemonException(specialAttack); // Throw an exception if it is
            }
            this.specialAttack = Integer.parseInt(specialAttack); // Otherwise, set it to the provided value

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); // Print an error message if an exception occurs
            setDefaultValues(); // Set default values if an exception occurs
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage()); // Print an error message if an exception occurs
            setDefaultValues(); // Set default values if an exception occurs
        }
        finally {
            if (reader != null) {
                try {
                    reader.close(); // Close the BufferedReader
                } catch (IOException e) {
                    System.out.println("Error closing file: " + e.getMessage()); // Print an error message if an exception occurs
                }
            }
        }
    }

    /**
     * Sets default values for the Pokemon attributes if an exception occurs.
     */
    private void setDefaultValues() {
        try {
            this.setName("Unknown");
            this.setType(new Type("Water"));
            this.setAttack(1);
            this.setDefense(1);
            this.setHealth(1);
            this.specialAttack = 1;
        } catch (PokemonException ignored) {}
    }



    public int getSpecialAttack() {
        return specialAttack; // Return the special attack value
    }

    public int calcAttack(){
        return super.calcAttack() * specialAttack; // Calculate the attack value by multiplying the superclass attack with special attack
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()){
            return false; // If the object is null or not of the same class, return false
        }
        SpecialPokemon other = (SpecialPokemon) obj; // Cast the object to SpecialPokemon

        if (this.getName() == null || other.getName() == null) return false;
        if (this.getType() == null || other.getType() == null) return false;
        if (this.getType().getType() == null || other.getType().getType() == null) return false;

        if(this.getName().equals(other.getName()) && this.getType().getType().equals(other.getType().getType()) && (this.getAttack() == other.getAttack()) && (this.getDefense() == other.getDefense()) && (this.getHealth() == other.getHealth()) && (this.specialAttack == other.specialAttack)){
            return true; // If all attributes are equal, return true
        }
        return false; // Otherwise, return false
    }
    @Override
    public SpecialPokemon clone() {
        SpecialPokemon clone = null; // Create a new SpecialPokemon object with the same attributes
        try {
            clone = new SpecialPokemon(this.getName(),this.getType(),this.getAttack(),this.getDefense(),this.getHealth(),this.specialAttack);
            return clone; // Return the cloned object
        } catch (PokemonException e) {
            try {
                return new SpecialPokemon("Unknown", new Type("Water"), 0, 0, 1, 0); /* Return a default object if an exception occurs */
            } catch (PokemonException ex) {
                return null; // Return null if an exception occurs while creating the default object
            }
        }
    }

    public void saveToFile(File file){
        PrintWriter writer = null; // Initialize the PrintWriter
        try{
            writer = new PrintWriter(new FileWriter(file)); // Create a new PrintWriter to write to the file
            writer.println("Name: " + this.getName()); // Write the name of the Pokemon
            writer.println("Type: " + this.getType().getType()); // Write the type of the Pokemon
            writer.println("Attack: " + this.getAttack()); // Write the attack of the Pokemon
            writer.println("Defense: " + this.getDefense()); // Write the defense of the Pokemon
            writer.println("Health: " + this.getHealth()); // Write the health of the Pokemon
            writer.println("Special Attack: " + this.specialAttack); // Write the special attack of the Pokemon
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage()); // Print an error message if an exception occurs
        } finally {
            if (writer != null) {
                writer.close(); // Close the PrintWriter
            }
        }
    }
}
