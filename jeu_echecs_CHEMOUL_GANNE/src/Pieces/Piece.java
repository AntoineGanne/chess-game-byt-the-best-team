package Pieces;
import Jeu.Case;

import java.util.LinkedList;

public class Piece {
    private String nom;
    private boolean estBlanc;
    private boolean estMange;

    /**
     * 
     * @param blanc boolean si la piece est blanche
     */
    public Piece(boolean blanc, String nom) {
        this.estBlanc = blanc;
        this.estMange = false;
        this.nom = nom;
    }
    public Piece(Piece piece) {
        this.nom = piece.nom;
        this.estBlanc = piece.estBlanc;
        this.estMange = piece.estMange;
    }

    public void setEstMange(boolean estMange) {
        this.estMange = estMange;
    }

    public boolean isEstMange() {
        return estMange;
    }

    public boolean isEstBlanc() {
        return estBlanc;
    }


    public String getNom() {
        return nom;
    }

    public void mangerPiece(){
        this.estMange = true;
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<Case>();
        return casesPossibles;
    }

}
