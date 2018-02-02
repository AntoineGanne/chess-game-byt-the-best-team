package Pieces;
import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public abstract class Piece{
    private String nom;
    private boolean estBlanc;
    private boolean estMange;
    private boolean positionInitiale = true;

    /**
     * 
     * @param blanc boolean si la piece est blanche
     */
    public Piece(boolean blanc, String nom, String imageB, String imageN) {
        this.estBlanc = blanc;
        this.estMange = false;
        this.nom = nom;
    }

    public Piece(Piece piece) {
        this.nom = piece.nom;
        this.estBlanc = piece.estBlanc;
        this.estMange = piece.estMange;
    }

    /**
     * fonction utilisée par les pieces Dame,Fou et Tour
     * ajoute a la liste casesPossibles les deplacements possible de la piece a la position (x,y)
     * selon la direction donnée par directionX et direction Y
     * @param directionX
     * @param directionY
     * @param x
     * @param y
     * @param plateau
     * @param casesPossibles
     */
    protected void ajouterPossibilitéesSelonUneDirection(int directionX, int directionY,int x,int y,PlateauG plateau, LinkedList<CaseG> casesPossibles)
    {
        if(!(directionX==0 && directionY==0)){
            boolean pieceNonAtteinte = true;  //pour sortir de la boucle après avoir atteint une pièce
            int xTemp = x+directionX;
            int yTemp = y+directionY;
            while(xTemp>=0 && xTemp < 8 && yTemp>=0 && yTemp<8 && pieceNonAtteinte){
                //si la case est vide ou qu'elle contient une piece de la couleur opposée
                boolean b_estVide =plateau.getTabCases()[xTemp][yTemp].estVide();
                if(b_estVide || positionDePionAdverse(xTemp,yTemp,plateau)){
                    casesPossibles.add(plateau.getTabCases()[xTemp][yTemp]);
                }
                if(!b_estVide) pieceNonAtteinte=false;
                xTemp+=directionX;
                yTemp+=directionY;
            }
        }
    }

    /**
     * sert a tester la position donnée durant le calcul des deplacements possibles
     * @warning ne doit pas etre appelée sur une case ne contenant pas de piece
     * @param x
     * @param y
     * @param plateau
     * @return vrai si la position donnée est occupée par un pion adverse
     */
    protected boolean positionDePionAdverse(int x,int y,PlateauG plateau)
    {
        return (this.isEstBlanc() ? !plateau.getTabCases()[x][y].getPiece().isEstBlanc() : plateau.getTabCases()[x][y].getPiece().isEstBlanc());
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

    public boolean isPositionInitiale() {
        return positionInitiale;
    }

    public void setPositionInitiale(boolean positionInitiale) {
        this.positionInitiale = positionInitiale;
    }

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){
        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();
        return casesPossibles;
    }
}
