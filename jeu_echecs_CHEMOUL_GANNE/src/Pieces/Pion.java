package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Pion extends Piece {

    public Pion(boolean blanc){
        super(blanc,"Pion");
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<>();
        System.out.println(x);
        System.out.println(y);
        //Pour chaque possibilité : si le pion n'est pas de la même couleur ou si la case est vide
        if(this.isEstBlanc() && x == 6) //si c'est un pion blanc et qu'il est à sa position de départ
            casesPossibles.add(tabJeu[x-2][y]);
        if(!this.isEstBlanc() && x == 1)
            casesPossibles.add(tabJeu[x+2][y]);
        if(this.isEstBlanc() && x-1 >= 0 && (tabJeu[x-1][y].estVide() || (!tabJeu[x-1][y].estVide() && !tabJeu[x-1][y].getPiece().isEstBlanc())))
            casesPossibles.add(tabJeu[x-1][y]);
        if(!this.isEstBlanc() && x+1 < 8 && (tabJeu[x+1][y].estVide() || (!tabJeu[x+1][y].estVide() && tabJeu[x+1][y].getPiece().isEstBlanc())))
            casesPossibles.add(tabJeu[x+1][y]);
        return casesPossibles;
    }
}
