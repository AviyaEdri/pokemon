package pokemon;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private int attack;
    private int defense;
    private int health;
    private Type type; //Pokemon.Type is a class that contains the type of the Pokemon.Pokemon

    //Default constructor
    public Pokemon(){
        this.name = "Magikarp";
        this.attack = 10;
        this.defense = 55;
        this.health = 20;
        this.type = new Type("Water"); //default type is Water

    }
    //Constructor with parameters
    public Pokemon(String name, Type type, int attack, int defense, int health) throws PokemonException {
        if(name == null || name.isEmpty()){ //if name is null or empty, set it to Pikachu{
            throw new PokemonException(name); //if name is null, throw an exception

        }
        if(attack < 0){ //if attack is less than 0, set it to 0
            throw new PokemonException(attack); //throw an exception
        }
        if(defense < 0){ //if defense is less than 0, set it to 0
            throw new PokemonException(defense); //throw an exception
        }
        if(health < 1){ //if health is less than 1, set it to 1
            throw new PokemonException(health); //throw an exception
        }
        if(type == null){ //if type is null, set it to Water
            this.type = new Type("Water");
        }
        else {
            this.type = type; //if type is not null, set it to the type passed in
        }
        this.name = name; //if name is not null, set it to the name passed in
        this.attack = attack; //if attack is not less than 0, set it to the attack passed in
        this.defense = defense; //if defense is not less than 0, set it to the defense passed in
        this.health = health; //if lifePoints is not less than 1, set it to the lifePoints passed in
    }
    //Copy constructor
    public Pokemon(Pokemon other){
        if(other == null){ //if other is null, set it to a new Pokemon.Pokemon object
            other = new Pokemon();
        }
        if(other.name == null){
            this.name = "Pikachu"; //if name is null, set it to Pikachu
        }
        else {
            this.name = other.name; //if name is not null, set it to the name passed in
        }
        if(other.attack < 0){ //if attack is less than 0, set it to 0
            this.attack = 0;
        }
        else {
            this.attack = other.attack; //if attack is not less than 0, set it to the attack passed in
        }
        if(other.defense < 0){ //if defense is less than 0, set it to 0
            this.defense = 0;
        }
        else {
            this.defense = other.defense; //if defense is not less than 0, set it to the defense passed in
        }
        if(other.health < 1){ //if health is less than 1, set it to 1
            this.health = 1; //health cannot be less than 1
        }
        else {
            this.health = other.health; //if lifePoints is not less than 1, set it to the lifePoints passed in
        }
        if(other.type == null){ //if type is null, set it to Water
            this.type = new Type("Water");
        }
        else {
            this.type = other.type; //if type is not null, set it to the type passed in
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PokemonException {
        if(name == null || name.isEmpty()){ //if name is null, set it to Pikachu
            throw new PokemonException(name); //throw an exception
        }
        this.name = name; //if name is not null, set it to the name passed in
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        if(type == null){ //if type is null, set it to Water
            type = new Type("Water");
        }
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) throws PokemonException {
        if(attack < 0){ //if attack is less than 0, set it to 0
            throw new PokemonException(attack); //throw an exception
        }
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) throws PokemonException{
        if(defense < 0){ //if defense is less than 0, set it to 0
            throw new PokemonException(defense); //throw an exception
        }
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) throws PokemonException {
        if(health < 1){ //if health is less than 1, set it to 1
            throw new PokemonException(health); //throw an exception
        }
        this.health = health; //set lifePoints to the value passed in
    }

    public int calcAttack(){
        return attack;
    }
    @Override
    public boolean equals(Object obj) {
        if(obj == null || this.getClass() != obj.getClass()) { //if obj is null, return false
            return false;
        }
        Pokemon other = (Pokemon) obj; //cast obj to Pokemon
        if(this.name.equals(other.name) && this.attack == other.attack && this.defense == other.defense && this.health == other.health && this.type.getType().equals(other.type.getType())){ //if all attributes are equal, return true
            return true;
        }
        return false; //if any attribute is not equal, return false
    }
    @Override
    public Pokemon clone()  {
        Pokemon clone = new Pokemon(); //create a new Pokemon.Pokemon object
        clone.name = this.name; //set the name of the clone to the name of the original
        clone.attack = this.attack; //set the attack of the clone to the attack of the original
        clone.defense = this.defense; //set the defense of the clone to the defense of the original
        clone.health = this.health; //set the health of the clone to the health of the original
        clone.type = this.type; //set the type of the clone to the type of the original
        return clone; //return the cloned Pokemon object
    }
}
