package Jeu;

import Pieces.Piece;

public class Case {
    private int x;
    private int y;
    private Piece piece;

    /**
     * Constructeur par défaut de la classe
     */
    public Case(){

    }

    /**
     * Constructeur par copie de la classe
     */
    public Case(Case case1){
        this.x = case1.x;
        this.y = case1.y;
        this.piece = case1.piece;
    }

    /**
     * Constructeur avec paramètres de la classe
     * @param x
     * @param y
     * @param piece
     */
    public Case(int x, int y,Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    //GETTER AND SETTER

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void afficherPossibilites(Case[][] tabJeu){
        Case[] poss;
        poss = this.piece.afficherPossibilitees(this.x,this.y, tabJeu);
        System.out.println("Possibilitées de déplacement");
        for(int i = 0; i<poss.length;i++){
            if(poss[i]!=null)
                System.out.println("Coordonnées : " +(poss[i].getX()+1) + "  " + (poss[i].getY()+1));
        }
    }

    public boolean estVide(){
        return (this.piece.isEstMange() || this.piece == null); //ou l'ancienne piece a été mangée ou la case est vide
    }
}
