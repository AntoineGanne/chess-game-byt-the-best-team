package Pieces;
import Jeu.Case;

import java.util.LinkedList;

public class Piece{
    private String nom;
    private boolean estBlanc;
    private enum nomPiece {ROI,DAME,CAVALIER,PION,TOUR,FOU};

    /**
     * 
     * @param blanc boolean si la piece est blanche
     */
    public Piece(boolean blanc, String nom) {
        this.estBlanc = blanc;
        this.nom = nom;
    }

    public Piece(Piece piece) {
        this.nom = piece.nom;
        this.estBlanc = piece.estBlanc;
    }

    public boolean isEstBlanc() {
        return estBlanc;
    }

    public String getNom() {
        return nom;
    }

    /**
     *
     * @param x emplacement x sur le plateau de jeu
     * @param y emplacement y sur le plateau de jeu
     * @param tabJeu
     * @return
     */
    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){
        LinkedList<Case> casesPossibles = new LinkedList<Case>();
        return casesPossibles;
    }
}
