package Jeu;
import Pieces.Piece;

import Pieces.Dame;
import Pieces.Pion;
import Pieces.Roi;
import Pieces.Fou;
import Pieces.Tour;
import  Pieces.Cavalier;

public class Plateau {
    private int taille;
    private Case[][] tabCases;
    private boolean roiBlancMort;
    private boolean roiNoirMort;

    public Plateau(int taille) {
        this.taille = taille;
        this.tabCases = new Case[taille][taille];
        this.initialiserPlateau();
        this.roiBlancMort = false;
        this.roiNoirMort = false;
    }

    public Case[][] getTabCases() {
        return tabCases;
    }

    public boolean isRoiBlancMort() {
        return roiBlancMort;
    }

    public boolean isRoiNoirMort() {
        return roiNoirMort;
    }

    public void initialiserPlateau(){
        for(int i = 0;i<taille;i++) {
            for (int j = 0; j < taille; j++) {
                tabCases[i][j] = new Case(i,j,null);
            }
        }

                //Pieces blanches
        Pion pion1B = new Pion(true);
        Pion pion2B = new Pion(true);
        Pion pion3B = new Pion(true);
        Pion pion4B = new Pion(true);
        Pion pion5B = new Pion(true);
        Pion pion6B = new Pion(true);
        Pion pion7B = new Pion(true);
        Pion pion8B = new Pion(true);
        tabCases[6][0] = new Case(6,0,pion1B);
        tabCases[6][1] = new Case(6,1,pion2B);
        tabCases[6][2] = new Case(6,2,pion3B);
        tabCases[6][3] = new Case(6,3,pion4B);
        tabCases[6][4] = new Case(6,4,pion5B);
        tabCases[6][5] = new Case(6,5,pion6B);
        tabCases[6][6] = new Case(6,6,pion7B);
        tabCases[6][7] = new Case(6,7,pion8B);
        Dame dameB = new Dame(true);
        Roi roiB = new Roi(true);
        Fou fou1B = new Fou(true);
        Fou fou2B = new Fou(true);
        Cavalier cavalier1B = new Cavalier(true);
        Cavalier cavalier2B = new Cavalier(true);
        Tour tour1B = new Tour(true);
        Tour tour2B = new Tour(true);
        tabCases[7][0] = new Case(7,0,tour1B);
        tabCases[7][7] = new Case(7,7,tour2B);
        tabCases[7][1] = new Case(7,1,cavalier1B);
        tabCases[7][6] = new Case(7,6,cavalier2B);
        tabCases[7][2] = new Case(7,2,fou1B);
        tabCases[7][5] = new Case(7,5,fou2B);
        tabCases[7][3] = new Case(7,3,roiB);
        tabCases[7][4] = new Case(7,3,dameB);



        //Pieces noires
        Pion pion1N = new Pion(false);
        Pion pion2N = new Pion(false);
        Pion pion3N = new Pion(false);
        Pion pion4N = new Pion(false);
        Pion pion5N = new Pion(false);
        Pion pion6N = new Pion(false);
        Pion pion7N = new Pion(false);
        Pion pion8N = new Pion(false);
        tabCases[1][0] = new Case(1,0,pion1N);
        tabCases[1][1] = new Case(1,1,pion2N);
        tabCases[1][2] = new Case(1,2,pion3N);
        tabCases[1][3] = new Case(1,3,pion4N);
        tabCases[1][4] = new Case(1,4,pion5N);
        tabCases[1][5] = new Case(1,5,pion6N);
        tabCases[1][6] = new Case(1,6,pion7N);
        tabCases[1][7] = new Case(1,7,pion8N);
        Dame dameN = new Dame(false);
        Roi roiN = new Roi(false);
        Fou fou1N = new Fou(false);
        Fou fou2N= new Fou(false);
        Cavalier cavalier1N = new Cavalier(false);
        Cavalier cavalier2N = new Cavalier(false);
        Tour tour1N = new Tour(false);
        Tour tour2N = new Tour(false);
        tabCases[0][0] = new Case(0,0,tour1N);
        tabCases[0][7] = new Case(0,7,tour2N);
        tabCases[0][1] = new Case(0,1,cavalier1N);
        tabCases[0][6] = new Case(0,6,cavalier2N);
        tabCases[0][2] = new Case(0,2,fou1N);
        tabCases[0][5] = new Case(0,5,fou2N);
        tabCases[0][3] = new Case(0,3,roiN);
        tabCases[0][4] = new Case(0,3,dameN);

    }

    public void affichageConsole(){
        String couleur;
        System.out.println("  | 1  2  3  4  5  6  7  8");
        System.out.println("___________________________");
        for(int i = 0;i<taille;i++){
            System.out.print(i+1 + " | ");
            for(int j=0; j<taille; j++){
                if(tabCases[i][j].getPiece() == null)
                    System.out.print("__");
                else{
                    Piece temp = new Piece(tabCases[i][j].getPiece());
                    if(!temp.isEstMange()){ //on l'affiche uniquement si elle est pas mangée
                        if(temp.isEstBlanc())
                            couleur = "b";
                        else
                            couleur = "n";
                        switch(temp.getNom()){
                            case "Cavalier" :
                                System.out.print("C" + couleur);
                                break;
                            case "Dame" :
                                System.out.print("D" + couleur);
                                break;
                            case "Roi" :
                                System.out.print("R" + couleur);
                                break;
                            case "Pion" :
                                System.out.print("P" + couleur);
                                break;
                            case "tour" :
                                System.out.print("T" + couleur);
                                break;
                            case "Fou" :
                                System.out.print("F" + couleur);
                                break;
                            default:
                                System.out.print("__");
                                break;
                        }

                    }else
                        System.out.print("__");

                }
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    /**
     *
     * @param xFinal coordonee finale x de la piece sur la grille
     * @param yFinal coordonee finale y de la piece sur la grille
     */
    public void deplacerPiecePlateau(Case caseADeplacer, int xFinal, int yFinal){
        if(tabCases[xFinal][yFinal].getPiece()!= null) { //si la case du déplacement n'est pas vide
            System.out.println("Vous avez mangé une pièce de l'adversaire : " + tabCases[xFinal][yFinal].getPiece().getNom() + ".");
            tabCases[xFinal][yFinal].getPiece().setEstMange(true); //la pièce de la case est mangée
            //On regarde si c'est le Roi d'une des couleurs qui est mort
            if(tabCases[xFinal][yFinal].getPiece().getNom() == "Roi"){
                if(tabCases[xFinal][yFinal].getPiece().isEstBlanc())
                    this.roiBlancMort = true;
                else
                    this.roiNoirMort = true;
            }
        }
        tabCases[xFinal][yFinal].setPiece(caseADeplacer.getPiece()); //la nouvelle pièce de la case est la notre
        caseADeplacer.setPiece(null); //l'ancienne case n'a plus de pièce
    }

}
