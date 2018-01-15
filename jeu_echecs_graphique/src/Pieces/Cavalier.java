package Pieces;

import JeuGraphique.CaseG;

import java.util.LinkedList;

public class Cavalier extends Piece {

    public Cavalier(boolean blanc){
        super(blanc,"Cavalier","cavalierB.png","cavalierN.png");
    }



    public LinkedList<CaseG> afficherPossibilitees(int x, int y, CaseG[][] tabJeu){

        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();
        if(x-2 >= 0 && y-1 >= 0 && (tabJeu[x-2][y-1].estVide() || (!tabJeu[x-2][y-1].estVide() && ((this.isEstBlanc() &&!tabJeu[x-2][y-1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-2][y-1].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x-2][y-1]);
        }
        if(x-1 >= 0 && y-2 >= 0 && (tabJeu[x-1][y-2].estVide() || (!tabJeu[x-1][y-2].estVide() && ((this.isEstBlanc() &&!tabJeu[x-1][y-2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-1][y-2].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x-1][y-2]);
        }
        if(x+2 <= 7 && y-1 >= 0 && (tabJeu[x+2][y-1].estVide() || (!tabJeu[x+2][y-1].estVide() && ((this.isEstBlanc() &&!tabJeu[x+2][y-1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+2][y-1].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x+2][y-1]);
        }
        if(x-2 >= 0 && y+1 <= 7 && (tabJeu[x-2][y+1].estVide() || (!tabJeu[x-2][y+1].estVide() && ((this.isEstBlanc() &&!tabJeu[x-2][y+1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-2][y+1].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x-2][y+1]);
        }
        if(x-1 >= 0 && y+2 <= 7 && (tabJeu[x-1][y+2].estVide() || (!tabJeu[x-1][y+2].estVide() && ((this.isEstBlanc() &&!tabJeu[x-1][y+2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x-1][y+2].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x-1][y+2]);
        }
        if(x+2 <= 7 && y+1 <= 7 && (tabJeu[x+2][y+1].estVide() || (!tabJeu[x+2][y+1].estVide() && ((this.isEstBlanc() &&!tabJeu[x+2][y+1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+2][y+1].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x+2][y+1]);
        }
        if(x+1 <= 7 && y+2 <= 7 && (tabJeu[x+1][y+2].estVide() || (!tabJeu[x+1][y+2].estVide() && ((this.isEstBlanc() &&!tabJeu[x+1][y+2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+1][y+2].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x+1][y+2]);
        }
        if(x+1 >= 0 && y-2 >= 0 && (tabJeu[x+1][y-2].estVide() || (!tabJeu[x+1][y-2].estVide() && ((this.isEstBlanc() &&!tabJeu[x+1][y-2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&tabJeu[x+1][y-2].getPiece().isEstBlanc()))))){
            casesPossibles.add(tabJeu[x+1][y-2]);
        }
        return casesPossibles;
    }
}
