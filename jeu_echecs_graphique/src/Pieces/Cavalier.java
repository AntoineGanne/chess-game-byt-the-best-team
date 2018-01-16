package Pieces;

import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Cavalier extends Piece {

    public Cavalier(boolean blanc){
        super(blanc,"Cavalier","cavalierB.png","cavalierN.png");
    }



    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){

        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();

        if(x-2 >= 0 && y-1 >= 0 && (plateau.getTabCases()[x-2][y-1].estVide() || (!plateau.getTabCases()[x-2][y-1].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-2][y-1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-2][y-1].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x-2][y-1]);
        }
        if(x-1 >= 0 && y-2 >= 0 && (plateau.getTabCases()[x-1][y-2].estVide() || (!plateau.getTabCases()[x-1][y-2].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-1][y-2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-1][y-2].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x-1][y-2]);
        }
        if(x+2 <= 7 && y-1 >= 0 && (plateau.getTabCases()[x+2][y-1].estVide() || (!plateau.getTabCases()[x+2][y-1].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+2][y-1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+2][y-1].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x+2][y-1]);
        }
        if(x-2 >= 0 && y+1 <= 7 && (plateau.getTabCases()[x-2][y+1].estVide() || (!plateau.getTabCases()[x-2][y+1].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-2][y+1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-2][y+1].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x-2][y+1]);
        }
        if(x-1 >= 0 && y+2 <= 7 && (plateau.getTabCases()[x-1][y+2].estVide() || (!plateau.getTabCases()[x-1][y+2].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-1][y+2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-1][y+2].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x-1][y+2]);
        }
        if(x+2 <= 7 && y+1 <= 7 && (plateau.getTabCases()[x+2][y+1].estVide() || (!plateau.getTabCases()[x+2][y+1].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+2][y+1].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+2][y+1].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x+2][y+1]);
        }
        if(x+1 <= 7 && y+2 <= 7 && (plateau.getTabCases()[x+1][y+2].estVide() || (!plateau.getTabCases()[x+1][y+2].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+1][y+2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+1][y+2].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x+1][y+2]);
        }
        if(x+1 <= 7 && y-2 >= 0 && (plateau.getTabCases()[x+1][y-2].estVide() || (!plateau.getTabCases()[x+1][y-2].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+1][y-2].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+1][y-2].getPiece().isEstBlanc()))))){
            casesPossibles.add(plateau.getTabCases()[x+1][y-2]);
        }
        return casesPossibles;
    }
}
