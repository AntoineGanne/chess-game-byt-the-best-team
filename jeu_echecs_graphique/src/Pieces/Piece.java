package Pieces;
import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Piece{
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
