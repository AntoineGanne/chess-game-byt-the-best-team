package Pieces;

import Jeu.Case;

import java.util.LinkedList;

public class Dame extends Piece {

    public Dame(boolean blanc){
        super(blanc,"Dame","dameB.png","dameN.png");
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
        /*
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
        */



    }
}
