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

        //on parcourt les directions en diagonale
        for(int xOffset=-1;xOffset<=1;xOffset+=2)
        {
            for(int yOffset=-1;yOffset<=1;yOffset+=2)
            {
                ajouterPossibilitéesSelonUneDirection(xOffset,yOffset,x,y,plateau,casesPossibles);
            }
        }
        return casesPossibles;
    }
}
