package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Dame extends Piece {

    public Dame(boolean blanc){
        super(blanc,"Dame");
    }

    public LinkedList<Case> afficherPossibilitees(int x, int y, Case[][] tabJeu){

        LinkedList<Case> casesPossibles = new LinkedList<Case>();


        //on parcoure toutes les dirrections autours de (x,y)
        for(int xOffset=-1;xOffset<=1;xOffset++)
        {
            for(int yOffset=-1;yOffset<=1;yOffset++)
            {
                if(!(xOffset==0 && yOffset==0)){
                    boolean pieceNonAtteinte = true;  //pour sortir de la boucle après avoir atteint une pièce
                    int xTemp = x+xOffset;
                    int yTemp = y+yOffset;
                    while(xTemp>=0 && xTemp < 8 && yTemp>=0 && yTemp<8 && pieceNonAtteinte){
                        //si la case est vide ou qu'elle contient une piece de la couleur opposée
                        boolean b_estVide =tabJeu[xTemp][yTemp].estVide();
                        if(b_estVide || (this.isEstBlanc() ? !tabJeu[xTemp][yTemp].getPiece().isEstBlanc() : tabJeu[xTemp][yTemp].getPiece().isEstBlanc())){
                            casesPossibles.add(tabJeu[xTemp][yTemp]);
                        }
                        if(!b_estVide) pieceNonAtteinte=false;
                        xTemp+=xOffset;
                        yTemp+=yOffset;
                    }
                }
            }
        }

        return casesPossibles;
    }
}
