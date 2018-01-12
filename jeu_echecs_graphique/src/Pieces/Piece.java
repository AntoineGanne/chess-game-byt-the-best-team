package Pieces;
import JeuGraphique.CaseG;

import java.util.LinkedList;

public class Piece{
    private String nom;
    private boolean estBlanc;
    private boolean estMange;
    private String imageB;
    private String imageN;

    /**
     * 
     * @param blanc boolean si la piece est blanche
     */
    public Piece(boolean blanc, String nom, String imageB, String imageN) {
        this.estBlanc = blanc;
        this.estMange = false;
        this.nom = nom;
        this.imageB = imageB;
        this.imageN = imageN;
    }

    public Piece(Piece piece) {
        this.nom = piece.nom;
        this.estBlanc = piece.estBlanc;
        this.estMange = piece.estMange;
        this.imageB = piece.imageB;
        this.imageN = piece.imageN;
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

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, CaseG[][] tabJeu){
        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();
        return casesPossibles;
    }
}
