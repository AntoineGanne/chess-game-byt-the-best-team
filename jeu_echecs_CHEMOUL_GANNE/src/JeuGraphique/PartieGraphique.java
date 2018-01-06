package JeuGraphique;

import Jeu.Plateau;

public class PartieGraphique {
        private Plateau plateauJeu;

    public PartieGraphique(){
        plateauJeu = new Plateau(8);
    }

    public Plateau getPlateauJeu() {
        return plateauJeu;
    }
}
