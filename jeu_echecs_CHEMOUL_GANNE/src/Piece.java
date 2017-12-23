public class Piece {
    private boolean estBlanc;
    private boolean estMange;

    /**
     * 
     * @param blanc boolean si la piece est blanche
     * @param emplX coordonee initiale x de la piece sur la grille
     * @param emplY rdonee initiale y de la piece sur la grille
     */
    public Piece(boolean blanc, int emplX, int emplY) {
        this.estBlanc = blanc;
        this.estMange = false;
    }


    /**
     *
     * @param xFinal coordonee finale x de la piece sur la grille
     * @param yFinal coordonee finale y de la piece sur la grille
     */
    public void deplacer(int xFinal, int yFinal){

    }

    public Case[] testDeplacement(int xFinal, int yFinal){
       Case[] tabCases  = new Case[10];
       return tabCases; //juste le temps pour retourner qqch

    }
}
