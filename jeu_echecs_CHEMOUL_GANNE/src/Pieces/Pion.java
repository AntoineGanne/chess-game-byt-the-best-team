package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Pion extends Piece {

    public Pion(boolean blanc){
        super(blanc,"Pion","pionB.png","pionN.png");
    }

    @Override
    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<>();
        //Pour chaque possibilité : si le pion n'est pas de la même couleur ou si la case est vide
        if(this.isEstBlanc() && x == 6 && tabJeu[4][y].estVide() && tabJeu[5][y].estVide()) //si c'est un pion blanc et qu'il est à sa position de départ
            //il peut bouger que si il n'y pas de lettre devant
            casesPossibles.add(tabJeu[x-2][y]);
        if(!this.isEstBlanc() && x == 1  && tabJeu[3][y].estVide() && tabJeu[2][y].estVide())
            casesPossibles.add(tabJeu[x+2][y]);
        if(this.isEstBlanc() && x-1 >= 0 && tabJeu[x-1][y].estVide()) //il ne peut avancer tout droit que si la case est vide
            casesPossibles.add(tabJeu[x-1][y]);
        if(!this.isEstBlanc() && x+1 < 8 && tabJeu[x+1][y].estVide())
            casesPossibles.add(tabJeu[x+1][y]);
        //Gestion des prises en diagonale
        //La piece est blanche
        if(this.isEstBlanc() && x-1 >= 0 && y-1 >= 0 && !tabJeu[x-1][y-1].estVide() && !tabJeu[x-1][y-1].getPiece().isEstBlanc())
            casesPossibles.add(tabJeu[x-1][y-1]);
        if(this.isEstBlanc() && x-1 >= 0 && y+1 <= 7 && !tabJeu[x-1][y+1].estVide() && !tabJeu[x-1][y+1].getPiece().isEstBlanc())
            casesPossibles.add(tabJeu[x-1][y+1]);

        //La piece est noire
        if(!this.isEstBlanc() && x+1 <= 7 && y-1 >= 0 && !tabJeu[x+1][y-1].estVide() && tabJeu[x+1][y-1].getPiece().isEstBlanc())
            casesPossibles.add(tabJeu[x+1][y-1]);
        if(!this.isEstBlanc() && x+1 <= 7 && y+1 <= 7 && !tabJeu[x+1][y+1].estVide() && tabJeu[x+1][y+1].getPiece().isEstBlanc())
            casesPossibles.add(tabJeu[x+1][y+1]);
        return casesPossibles;
    }
}