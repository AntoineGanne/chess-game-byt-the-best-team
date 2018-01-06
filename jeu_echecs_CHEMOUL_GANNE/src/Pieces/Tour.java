package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Tour extends Piece{

    public Tour(boolean blanc){
        super(blanc,"tour","tourB.png","tourN.png");
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<>();

        //Verticalement
        int i =1;
        while(x+i<=7){
            if(tabJeu[x+i][y].estVide() || (!tabJeu[x+i][y].estVide() && ((this.isEstBlanc() &&!tabJeu[x+i][y].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+i][y].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x+i][y]);
            }
            if(!tabJeu[x+i][y].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            i++;
        }
        i=1;
        while(x-i>=0){
            if(tabJeu[x-i][y].estVide() || (!tabJeu[x-i][y].estVide() && ((this.isEstBlanc() &&!tabJeu[x-i][y].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-i][y].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x-i][y]);
            }
            if(!tabJeu[x-i][y].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            i++;
        }

        //Horizontalement
        int j =1;
        while(y+j<=7){
            if(tabJeu[x][y+j].estVide() || (!tabJeu[x][y+j].estVide() && ((this.isEstBlanc() &&!tabJeu[x][y+j].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x][y+j].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x][y+j]);
            }
            if(!tabJeu[x][y+j].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            j++;
        }
        j=1;
        while(y-j>=0){
            if(tabJeu[x][y-j].estVide() || (!tabJeu[x][y-j].estVide() && ((this.isEstBlanc() &&!tabJeu[x][y-j].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x][y-j].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x][y-j]);
            }
            if(!tabJeu[x][y-j].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            j++;
        }

        return casesPossibles;
    }
}
