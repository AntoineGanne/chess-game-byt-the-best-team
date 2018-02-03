package Pieces;

import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Tour extends Piece{
    public Tour(boolean blanc){
        super(blanc,"Tour");
    }

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){

        LinkedList<CaseG> casesPossibles = new LinkedList<>();

        //parcour a la verticale
        for(int xOffset=-1;xOffset<=1;xOffset++)
        {
            ajouterPossibiliteesSelonUneDirection(xOffset,0,x,y,plateau,casesPossibles);
        }

        //parcour a l'horizontale
        for(int yOffset=-1;yOffset<=1;yOffset+=2)
        {
            ajouterPossibiliteesSelonUneDirection(0,yOffset,x,y,plateau,casesPossibles);
        }



        return casesPossibles;
    }
}
