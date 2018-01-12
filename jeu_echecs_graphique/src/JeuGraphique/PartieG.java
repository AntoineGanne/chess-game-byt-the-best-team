package JeuGraphique;

import Jeu.Joueur;

import java.util.LinkedList;

public class PartieG {
    private PlateauG plateauJeu;
    private Joueur j1;
    private Joueur j2;
    private int joueurActuel; //0 pour blanc, 1 pour noir
    private boolean finie;
    //private LinkedList<tourPartie> listeTourParties = new LinkedList<tourPartie>();
    private boolean intelligenceArtificielle; //à 1 si le joueur veut jouer contre l'intelligenec artificielle


    /**
     * Constructeur de la classe
     */
    public PartieG(){
        this.plateauJeu = new PlateauG(8);
        this.joueurActuel = 0;
    }


    public PlateauG getPlateauJeu() {
        return plateauJeu;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public void setJoueurActuel(int joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public boolean isFinie() {
        return finie;
    }

    public void setFinie(boolean finie) {
        this.finie = finie;
    }

    public boolean isIntelligenceArtificielle() {
        return intelligenceArtificielle;
    }

    public void setIntelligenceArtificielle(boolean intelligenceArtificielle) {
        this.intelligenceArtificielle = intelligenceArtificielle;
    }
}
