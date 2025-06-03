package pokemon;

import java.util.Comparator;

public class PokemonCalcAttackComparator implements Comparator<Pokemon>{

    @Override
    public int compare(Pokemon P1, Pokemon P2) {
        if (P1 == null && P2 == null) {
            return 0; // If either Pokemon is null, consider them equal
        }
        if(P1 == null) return -1; // If P1 is null, consider it less than P2
        if(P2 == null) return 1; // If P2 is null, consider it greater than P1
        return P1.calcAttack() - P2.calcAttack(); // Compare the calculated attack values
    }
}
