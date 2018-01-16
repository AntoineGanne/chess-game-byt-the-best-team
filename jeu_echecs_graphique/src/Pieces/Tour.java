package Pieces;

import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Tour extends Piece{
    public Tour(boolean blanc){
        super(blanc,"Tour","tourB.png","tourN.png");
    }

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){

        LinkedList<CaseG> casesPossibles = new LinkedList<>();

        //Verticalement
        int i =1;
        while(x+i<=7){
            if(plateau.getTabCases()[x+i][y].estVide() || (!plateau.getTabCases()[x+i][y].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x+i][y].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x+i][y].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x+i][y]);
            }
            if(!plateau.getTabCases()[x+i][y].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            i++;
        }
        i=1;
        while(x-i>=0){
            if(plateau.getTabCases()[x-i][y].estVide() || (!plateau.getTabCases()[x-i][y].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x-i][y].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x-i][y].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x-i][y]);
            }
            if(!plateau.getTabCases()[x-i][y].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            i++;
        }

        //Horizontalement
        int j =1;
        while(y+j<=7){
            if(plateau.getTabCases()[x][y+j].estVide() || (!plateau.getTabCases()[x][y+j].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x][y+j].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x][y+j].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x][y+j]);
            }
            if(!plateau.getTabCases()[x][y+j].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            j++;
        }
        j=1;
        while(y-j>=0){
            if(plateau.getTabCases()[x][y-j].estVide() || (!plateau.getTabCases()[x][y-j].estVide() && ((this.isEstBlanc() &&!plateau.getTabCases()[x][y-j].getPiece().isEstBlanc()) || (!this.isEstBlanc() &&plateau.getTabCases()[x][y-j].getPiece().isEstBlanc())))){
                    casesPossibles.add(plateau.getTabCases()[x][y-j]);
            }
            if(!plateau.getTabCases()[x][y-j].estVide())
                break; //on affiche pas les cases qui sont derrière une case occupée (la tour ne peut pas sauter par dessus une case)
            j++;
        }

        return casesPossibles;
    }
}
