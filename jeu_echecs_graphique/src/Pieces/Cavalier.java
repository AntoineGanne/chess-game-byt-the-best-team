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

        for(int xOffset=-2;xOffset<=2;xOffset+=4){
            for(int yOffset=-1;yOffset<=1;yOffset+=2){
                int xTemp = x+xOffset;
                int yTemp = y+yOffset;
                if(xTemp>=0 && xTemp < 8 && yTemp>=0 && yTemp<8){
                    boolean b_estVide =plateau.getTabCases()[xTemp][yTemp].estVide();
                    if(b_estVide || positionDePionAdverse(xTemp,yTemp,plateau)){
                        casesPossibles.add(plateau.getTabCases()[xTemp][yTemp]);
                    }
                }

            }
        }

        for(int yOffset=-2;yOffset<=2;yOffset+=4){
            for(int xOffset=-1;xOffset<=1;xOffset+=2){
                int xTemp = x+xOffset;
                int yTemp = y+yOffset;
                if(xTemp>=0 && xTemp < 8 && yTemp>=0 && yTemp<8){
                    boolean b_estVide =plateau.getTabCases()[xTemp][yTemp].estVide();
                    if(b_estVide || positionDePionAdverse(xTemp,yTemp,plateau)){
                        casesPossibles.add(plateau.getTabCases()[xTemp][yTemp]);
                    }
                }

            }
        }


        return casesPossibles;
    }
}
