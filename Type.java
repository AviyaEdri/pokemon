package pokemon;

import java.io.Serializable;
import java.util.Set;

public class Type implements Comparable<Type>, Serializable {
    private String type; // Type name
    private static final Set<String> VALID_TYPES = Set.of("Fire", "Water", "Grass", "Electric"); // Valid types

    public Type(String type) {
        if (type == null) {
            this.type = "Water"; // Default type
        } else {
            this.type = type; // Set the type to the provided type name
        }
    }

    public String getType() {
        return type; // Return a new Type object with the same type name
    }

    @Override
    public int compareTo(Type other) {
        if(other == null || other.type == null || this.type == null){
            return 0; // If the other Type object is null or its type is null, return 0
        }
        String thisType = this.type;
        String otherType = other.type;

        if (!VALID_TYPES.contains(thisType) || !VALID_TYPES.contains(otherType)) {
            return 0; // If either type is not valid, return 0
        }
        if(thisType.equals(otherType)){
            return 0; // If both types are equal, return 0
        }

        switch (thisType) {
            case "Fire":
                switch (otherType){
                    case "Water": return -1; // Fire is weak against Water
                    case "Grass": return 1; // Fire is strong against Grass
                    case "Electric": return 1; // Fire is strong against Electric
                    default: return 0; // Default case
                }
                case "Water":
                    switch (otherType){
                        case "Fire": return 1; // Water is strong against Fire
                        case "Grass": return -1; // Water is weak against Grass
                        case "Electric": return -1; // Water is weak against Electric

                        default: return 0; // Default case
                    }
                case "Grass":
                    switch (otherType){
                        case "Fire": return -1; // Grass is weak against Fire
                        case "Water": return 1; // Grass is strong against Water
                        case "Electric": return 1; // Grass is strong against Electric
                        default: return 0; // Default case
                    }
                case "Electric":
                    switch (otherType){
                        case "Fire": return -1; // Electric is weak against Fire
                        case "Water": return 1; // Electric is strong against Water
                        case "Grass": return -1; // Electric is weak against Grass
                        default: return 0; // Default case
                    }
                default:
                    return 0; // Default case for unknown types
        }
    }
}