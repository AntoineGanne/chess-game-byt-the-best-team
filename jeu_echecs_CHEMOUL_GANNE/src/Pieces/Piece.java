package Pieces;
import Jeu.Case;

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

    /**
     *
     * @param xFinal coordonee finale x de la piece sur la grille
     * @param yFinal coordonee finale y de la piece sur la grille
     */
    public void deplacer(int xFinal, int yFinal){

    }

    public String getNom() {
        return nom;
    }

    public void mangerPiece(){
        this.estMange = true;
    }

    public Case[] afficherPossibilitees(int x, int y, Case[][] tabJeu){

        Case[] casesPossibles = new Case[25];
        return casesPossibles;
    }

}
