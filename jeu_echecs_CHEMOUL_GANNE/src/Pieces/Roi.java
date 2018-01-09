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
        /*
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
        */


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
}
