package Pieces;

import JeuGraphique.CaseG;

import java.util.LinkedList;

public class Roi extends Piece{
    private boolean positionInitiale = true;

    public Roi(boolean blanc){
        super(blanc,"Roi","roiB.png","roiN.png");
    }

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, CaseG[][] tabJeu){

        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();
        //Pour chaque possibilité : si le pion n'est pas de la même couleur ou si la case est vide
        for(int i = x-1;i<=x+1;i++){
            for(int j=y-1;j<=y+1;j++){
                //attention aux magic numbers: remplacer 8 par ~~ tabJeu.length ?
                if(i >=0 && j >=0 && i<8 && j<8 && !(i==x && j==y)){
                    //si le pion n'est pas de la même couleur ou si la case est vide
                    if((!tabJeu[i][j].estVide() && ((tabJeu[i][j].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[i][j].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[i][j].estVide()))
                        casesPossibles.add(tabJeu[i][j]);
                }
            }
        }


        //Ajouter Roque
        return casesPossibles;
    }

    public boolean isPositionInitiale() {
        return positionInitiale;
    }

    public void setPositionInitiale(boolean positionInitiale) {
        this.positionInitiale = positionInitiale;
    }
}