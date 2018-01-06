package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Dame extends Piece {

    public Dame(boolean blanc){
        super(blanc,"Dame","dameB.png","dameN.png");
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<Case>();

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
                break; //on affiche pas les cases qui sont derrières une case occupée (la tour ne peut pas sauter par dessus une case)
            j++;
        }

        //DIAGONALES
        //Vers le bas à droite
        i =1;
        while(x+i<=7 && y+i<7){
            if(tabJeu[x+i][y+i].estVide() || (!tabJeu[x+i][y+i].estVide() && ((this.isEstBlanc() &&!tabJeu[x+i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+i][y].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x+i][y]);
            }
            if(!tabJeu[x+i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrières une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le haut à droite
        i=1;
        while(x-i>=0 && y+i<=7){
            if(tabJeu[x-i][y+i].estVide() || (!tabJeu[x-i][y+i].estVide() && ((this.isEstBlanc() &&!tabJeu[x-i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-i][y+i].getPiece().isEstBlanc())))){
                casesPossibles.add(tabJeu[x-i][y+i]);
            }
            if(!tabJeu[x-i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrières une case occupée (le fou ne peut pas sauter par dessus une case)
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
