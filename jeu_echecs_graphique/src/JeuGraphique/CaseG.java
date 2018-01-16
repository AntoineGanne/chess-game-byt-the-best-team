package JeuGraphique;

import Pieces.Piece;
import java.util.*;

public class CaseG {
    private int x;
    private int y;
    private Piece piece;

    /**
     * Constructeur par défaut de la classe
     */
    public CaseG(){

    }

    /**
     * Constructeur par copie de la classe
     */
    public CaseG(CaseG case1){
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
    public CaseG(int x, int y,Piece piece) {
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
     * @return vrai si la case est vide (si elle ne contient pas de pièce)
     */
    public boolean estVide(){
        return (this.piece == null ||  this.piece.isEstMange() ); //ou l'ancienne piece a été mangée ou la case est vide
    }

    public boolean appartientAPossibilites(LinkedList<CaseG> poss,int  x, int y, PlateauG plateau){
        if(poss.contains(plateau.getTabCases()[x][y])){
            return true;
        }
        return false;
    }

}
