package Pieces;

import JeuGraphique.CaseG;
import JeuGraphique.PlateauG;

import java.util.LinkedList;

public class Dame extends Piece {

    public Dame(boolean blanc){
        super(blanc,"Dame","dameB.png","dameN.png");
    }

    public LinkedList<CaseG> afficherPossibilitees(int x, int y, PlateauG plateau){

        LinkedList<CaseG> casesPossibles = new LinkedList<CaseG>();


        //on parcourt toutes les dirrections autours de (x,y)
        for(int xOffset=-1;xOffset<=1;xOffset++)
        {
            for(int yOffset=-1;yOffset<=1;yOffset++)
            {
                ajouterPossibilitéesSelonUneDirection(xOffset,yOffset,x,y,plateau,casesPossibles);
            }
        }

        return casesPossibles;
    }
}
