package Pieces;

import Jeu.Case;

public class Roi extends Piece{

    public Roi(boolean blanc){
        super(blanc,"Roi");
    }

    public Case[] afficherPossibilitees(int x, int y, Case[][] tabJeu){

        Case[] casesPossibles = new Case[8];
        /*if(x-1 >= 0 && y-1 >= 0){
            if((!tabJeu[x-1][y-1].estVide() && (tabJeu[x-1][y-1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x-1][y-1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x-1][y-1].estVide())
                casesPossibles[0] = new Case(tabJeu[x-1][y-1]);
        }
        if(x-1 >= 0){
            if((!tabJeu[x-1][y].estVide() && (tabJeu[x-1][y].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x-1][y].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x-1][y].estVide())
                casesPossibles[1] = new Case(tabJeu[x-1][y]);
        }
        if(y-1 >= 0){
            if((!tabJeu[x][y-1].estVide() && (tabJeu[x][y-1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x][y-1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x][y-1].estVide())
                casesPossibles[2] = new Case(tabJeu[x][y-1]);

        }
        if(x+1 <= 7 && y+1 <= 7){
            if((!tabJeu[x+1][y+1].estVide() && (tabJeu[x+1][y+1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x+1][y+1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x+1][y+1].estVide())
                casesPossibles[3] = new Case(tabJeu[x+1][y+1]);
        }
        if(y+1 <= 7){
            if((!tabJeu[x][y+1].estVide() && (tabJeu[x][y+1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x][y+1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x][y+1].estVide())
                casesPossibles[4] = new Case(tabJeu[x][y+1]);
        }
        if(x+1 <= 7){
            if((!tabJeu[x+1][y].estVide() && (tabJeu[x+1][y].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x+1][y].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x+1][y].estVide())
                casesPossibles[5] = new Case(tabJeu[x+1][y]);
        }
        if(x-1 >= 0 && y+1 <= 7){
            if((!tabJeu[x-1][y+1].estVide() && (tabJeu[x-1][y+1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x-1][y+1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x-1][y+1].estVide())
                casesPossibles[6] = new Case(tabJeu[x-1][y+1]);
        }
        if(x+1 <= 7 && y-1 >= 0){
            if((!tabJeu[x+1][y-1].estVide() && (tabJeu[x+1][y-1].getPiece().isEstBlanc() && this.isEstBlanc()) || (!tabJeu[x+1][y-1].getPiece().isEstBlanc() && !this.isEstBlanc())) || tabJeu[x+1][y-1].estVide())
                casesPossibles[7] = new Case(tabJeu[x+1][y-1]);
        }*/
        return casesPossibles;
    }
}
