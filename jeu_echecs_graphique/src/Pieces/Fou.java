package Pieces;

import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Fou extends Piece {

    public Fou(boolean blanc){
        super(blanc,"Fou","fouB.png","fouN.png");
    }


    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){

        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();

        //Vers le bas à droite
        int i =1;
        while(x+i<=7 && y+i<=7){
            if(plateau.getTabCases()[x+i][y+i].estVide() || (!plateau.getTabCases()[x+i][y+i].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+i][y+i].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x+i][y+i]);
            }
            if(!plateau.getTabCases()[x+i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le haut à droite
        i=1;
        while(x-i>=0 && y+i<=7){
            if(plateau.getTabCases()[x-i][y+i].estVide() || (!plateau.getTabCases()[x-i][y+i].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-i][y+i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-i][y+i].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x-i][y+i]);
            }
            if(!plateau.getTabCases()[x-i][y+i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le haut à gauche
        i=1;
        while(x-i>=0 && y-i>=0){
            if(plateau.getTabCases()[x-i][y-i].estVide() || (!plateau.getTabCases()[x-i][y-i].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-i][y-i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-i][y-i].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x-i][y-i]);
            }
            if(!plateau.getTabCases()[x-i][y-i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        //Vers le bas à droite
        i=1;
        while(x+i<=7 && y-i>=0){
            if(plateau.getTabCases()[x+i][y-i].estVide() || (!plateau.getTabCases()[x+i][y-i].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+i][y-i].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+i][y-i].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x+i][y-i]);
            }
            if(!plateau.getTabCases()[x+i][y-i].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (le fou ne peut pas sauter par dessus une case)
            i++;
        }

        return casesPossibles;
    }
}
