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
     * @param case1 case a copier
     */
    public CaseG(CaseG case1){
        this.x = case1.x;
        this.y = case1.y;
        this.piece = case1.piece;
    }

    /**
     * Constructeur avec paramètres de la classe
     * @param x ligne
     * @param y colonne
     * @param piece Piece
     */
    public CaseG(int x, int y,Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    //GETTER AND SETTER

    /**
     * Retourne la valeur de x.
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Modifie la valeur de x.
     * @param x ligne
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retourne la valeur de y.
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * Modifier la valeur de y.
     * @param y int
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retourne la Piece se trouvant sur la case.
     * @return Piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Modifie la valeur de la Piece sur la case.
     * @param piece Piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     *
     * @return vrai si la case est vide (si elle ne contient pas de pièce)
     */
    public boolean estVide(){
        return (this.piece == null); //ou l'ancienne piece a été mangée ou la case est vide
    }

    /**
     * Cette methode retourne vrai si une position (x,y) sur un plateau donne appartient à une liste de possibilites de deplacement donnee.
     * @param poss possibilites
     * @param x ligne
     * @param y colonne
     * @param plateau Plateau de jeu
     * @return boolean
     */
    public boolean appartientAPossibilites(LinkedList<CaseG> poss,int  x, int y, PlateauG plateau){
        if(poss.contains(plateau.getTabCases()[x][y])){
            return true;
        }
        return false;
    }

    /**
     * Test si tous les deplacements d'une piece sont susceptibles de mettre ou de laisser le Roi en echec.
     * @param plateau Plateau
     * @return boolean
     */
    public boolean tousDeplacementsMiseEnEchec(PlateauG plateau){
        LinkedList<CaseG> poss = this.getPiece().afficherPossibilitees(this.getX(),this.getY(),plateau);
        for(int i=0;i<poss.size();i++){
            if(!plateau.simulationDeplacement(this,poss.get(i).getX(),poss.get(i).getY(),this.getPiece().isEstBlanc()))
                return false;
        }
        return true;
    }

}
