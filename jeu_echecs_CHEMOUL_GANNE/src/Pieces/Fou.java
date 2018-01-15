package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Fou extends Piece {

    public Fou(boolean blanc){
        super(blanc,"Fou");
    }


    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<Case>();

        //Vers le bas à droite
        int i =1;
        while(x+i<=7 && y+i<=7){
            if(tabJeu[x+i][y+i].estVide() || (!tabJeu[x+i][y+i].estVide() && ((this.isEstBlanc() &&!tabJeu[x+i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+i][y+i].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x+i][y+i]);
            }
            if(!tabJeu[x+i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le haut à droite
        i=1;
        while(x-i>=0 && y+i<=7){
            if(tabJeu[x-i][y+i].estVide() || (!tabJeu[x-i][y+i].estVide() && ((this.isEstBlanc() &&!tabJeu[x-i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-i][y+i].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x-i][y+i]);
            }
            if(!tabJeu[x-i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le haut à gauche
        i=1;
        while(x-i>=0 && y-i>=0){
            if(tabJeu[x-i][y-i].estVide() || (!tabJeu[x-i][y-i].estVide() && ((this.isEstBlanc() &&!tabJeu[x-i][y-i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-i][y-i].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x-i][y-i]);
            }
            if(!tabJeu[x-i][y-i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le bas à droite
        i=1;
        while(x+i<=7 && y-i>=0){
            if(tabJeu[x+i][y-i].estVide() || (!tabJeu[x+i][y-i].estVide() && ((this.isEstBlanc() &&!tabJeu[x+i][y-i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+i][y-i].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x+i][y-i]);
            }
            if(!tabJeu[x+i][y-i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        return casesPossibles;
    }
}
