package Jeu;

import Pieces.Piece;
import java.util.*;

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

    /**
     *
     * @param tabJeu
     * @return Liste des Cases possibles de déplacement selon la case courante
     */
    public LinkedList<Case> afficherPossibilites(Case[][] tabJeu){
        LinkedList<Case> poss;
        int a;
        char lettre;
        poss = this.piece.afficherPossibilitees(this.x,this.y, tabJeu);
        if(poss.size() != 0) {
            System.out.println("Possibilitées de déplacement");
            for(int i = 0; i<poss.size();i++){
                if(poss.get(i)!=null){
                    a = poss.get(i).getY()+1;
                    lettre = (char) (a + 'a' -1);
                    System.out.println("Coordonnées : " +(8 - poss.get(i).getX()) + "  " + lettre);
                }
            }
        }
        return poss;
    }

    /**
     *
     * @return vrai si la case est vide (si elle ne contient pas de pièce)
     */
    public boolean estVide(){
        return (this.piece == null ||  this.piece.isEstMange() ); //ou l'ancienne piece a été mangée ou la case est vide
    }

}
