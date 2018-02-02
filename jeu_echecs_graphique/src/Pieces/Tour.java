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

        //parcour a la verticale
        for(int xOffset=-1;xOffset<=1;xOffset++)
        {
            ajouterPossibilitéesSelonUneDirection(xOffset,0,x,y,plateau,casesPossibles);
        }

        //parcour a l'horizontale
        for(int yOffset=-1;yOffset<=1;yOffset+=2)
        {
            ajouterPossibilitéesSelonUneDirection(0,yOffset,x,y,plateau,casesPossibles);
        }



        return casesPossibles;
    }
}
