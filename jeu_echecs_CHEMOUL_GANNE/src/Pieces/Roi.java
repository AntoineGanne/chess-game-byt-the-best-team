package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Roi extends Piece{

    public Roi(boolean blanc){
        super(blanc,"Roi","roiB.png","roiN.png");
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<Case>();
        //Pour chaque possibilité : si le pion n'est pas de la même couleur ou si la case est vide
        if(x-1 >= 0 && y-1 >= 0){
            if((!tabJeu[x-1][y-1].estVide() && (tabJeu[x-1][y-1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x-1][y-1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x-1][y-1].estVide())
                casesPossibles.add(tabJeu[x-1][y-1]);
        }
        if(x-1 >= 0){
            if((!tabJeu[x-1][y].estVide() && ((tabJeu[x-1][y].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x-1][y].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x-1][y].estVide()))
                casesPossibles.add(tabJeu[x-1][y]);
        }
        if(y-1 >= 0){
            if((!tabJeu[x][y-1].estVide() && ((tabJeu[x][y-1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x][y-1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x][y-1].estVide()))
                casesPossibles.add(tabJeu[x][y-1]);

        }
        if(x+1 <= 7 && y+1 <= 7){
            if((!tabJeu[x+1][y+1].estVide() && ((tabJeu[x+1][y+1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x+1][y+1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x+1][y+1].estVide()))
                casesPossibles.add(tabJeu[x+1][y+1]);
        }
        if(y+1 <= 7){
            if((!tabJeu[x][y+1].estVide() && ((tabJeu[x][y+1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x][y+1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x][y+1].estVide()))
                casesPossibles.add(tabJeu[x][y+1]);
        }
        if(x+1 <= 7){
            if((!tabJeu[x+1][y].estVide() && ((tabJeu[x+1][y].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x+1][y].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x+1][y].estVide()))
                casesPossibles.add(tabJeu[x+1][y]);
        }
        if(x-1 >= 0 && y+1 <= 7){
            if((!tabJeu[x-1][y+1].estVide() && ((tabJeu[x-1][y+1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x-1][y+1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x-1][y+1].estVide()))
                casesPossibles.add(tabJeu[x-1][y+1]);
        }
        if(x+1 <= 7 && y-1 >= 0){
            if((!tabJeu[x+1][y-1].estVide() && ((tabJeu[x+1][y-1].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x+1][y-1].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x+1][y-1].estVide()))
                casesPossibles.add(tabJeu[x+1][y-1]);
        }

        /*for(int i = -1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(x-i >=0 && y-i >=0 && y-i<=7 && x-i<=7){
                    if((!tabJeu[x-i][y-i].estVide() && ((tabJeu[x-i][y-i].getPiece().isEstBlanc() && !this.isEstBlanc()) || (!tabJeu[x-i][y-i].getPiece().isEstBlanc() && this.isEstBlanc())) || tabJeu[x-i][y-i].estVide()))
                        casesPossibles.add(tabJeu[x-i][y-i]);
                }
            }
        }*/

        //Ajouter Roque



        return casesPossibles;
    }
}
