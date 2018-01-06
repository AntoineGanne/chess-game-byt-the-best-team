package Jeu;
import Pieces.*;

import Pieces.Cavalier;
import com.sun.javafx.geom.Vec2d;
import java.util.*;

import java.util.LinkedList;
import java.io.*;

public class Plateau {
    private int taille;
    private Case[][] tabCases;
    private boolean roiBlancMort;
    private boolean roiNoirMort;
    private int compteurToursSansPrises; //La partie est finie si 50 coups sans prises ont été joués à la suite

    public Plateau(int taille) {
        this.taille = taille;
        this.tabCases = new Case[taille][taille];
        this.initialiserPlateau();
        this.roiBlancMort = false;
        this.roiNoirMort = false;
        this.compteurToursSansPrises = 0;
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

    /**
     * On crée toutes les pièces du jeu d'echec et on les initialises sur le plateau de jeu.
     */
    public void initialiserPlateau(){
        for(int i = 0;i<taille;i++) {
            for (int j = 0; j < taille; j++) {
                tabCases[i][j] = new Case(i,j,null);
            }
        }
    }

    public void demanderEtChargerFichier(){
        String ligne = "";
        String fichier = "";
        String [] mot;
        Scanner clavier = new Scanner(
                System.in);

        System.out.println("Quel est le nom de votre fichier de configuration ?");
        fichier = clavier.nextLine();
        BufferedReader ficTexte;
        try {
            ficTexte = new BufferedReader(new FileReader(new File(fichier)));
            if (ficTexte == null) {
                throw new FileNotFoundException("Fichier non trouvé : "
                        + fichier);
            }
            ligne = ficTexte.readLine();
            if (ligne != null) {
                System.out.println(ligne);
                mot = ligne.split (" ");

                //traitement du chargement
                boolean couleur = true;
                String lettre = "";
                String[] position;
                int a;
                int b;

                boolean estPiece; //si on traite la pièce ou sa position
                Pion pion;
                Dame dame;
                Roi roi;
                Fou fou;
                Cavalier cavalier;
                Tour tour;
                int i =0;
                System.out.println(mot.length);
                while(i<mot.length){

                    if(mot[i].contains("white")){
                        couleur=true;
                        i++;
                    }
                    else if(mot[i].contains("black")){
                        couleur=false;
                        i++;
                    }
                    else{
                        lettre = mot[i];
                        position = mot[i+1].split("");
                        if(position.length == 2){
                            a= position[0].toCharArray()[0] - 'a';
                            b = 8-Integer.parseInt(position[1]);

                            if(lettre.contains("p")){ //pawn
                                pion = new Pion(couleur);
                                tabCases[b][a] = new Case(b,a, pion);
                            }
                            else if(lettre.contains("t")) //tower
                            {
                                tour = new Tour(couleur);
                                tabCases[b][a] = new Case(b,a, tour);
                            }
                            else if(lettre.contains("q")) //queen
                            {
                                dame = new Dame(couleur);
                                tabCases[b][a] = new Case(b,a, dame);
                            }
                            else if(lettre.contains("b")) //bishop
                            {
                                fou = new Fou(couleur);
                                tabCases[b][a] = new Case(b,a, fou);
                            }
                            else if(lettre.contains("k1") || lettre.contains("k2")) //knight
                            {
                                cavalier = new Cavalier(couleur);
                                tabCases[b][a] = new Case(b,a, cavalier);
                            }
                            else //king
                            {
                                roi = new Roi(couleur);
                                tabCases[b][a] = new Case(b,a, roi);
                            }
                        }
                        i=i+2;
                    }

                }
                }
            ficTexte.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Affichage de notre plateau de jeu avec les pièces respectives sur la console.
     */
    public void affichageConsole(){
        String couleur;
        System.out.println("  | A  B  C  D  E  F  G  H");
        System.out.println("___________________________");
        for(int i = 0;i<taille;i++){
            System.out.print(8-i + " | ");
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
            this.compteurToursSansPrises = 0; //Une pièce a été mangée on remet le compteur à 0.
            tabCases[xFinal][yFinal].getPiece().setEstMange(true); //la pièce de la case est mangée
            //On regarde si c'est le Roi d'une des couleurs qui est mort
            if(tabCases[xFinal][yFinal].getPiece().getNom() == "Roi"){
                if(tabCases[xFinal][yFinal].getPiece().isEstBlanc())
                    this.roiBlancMort = true;
                else
                    this.roiNoirMort = true;
            }
        }else
            this.compteurToursSansPrises++;
        tabCases[xFinal][yFinal].setPiece(caseADeplacer.getPiece()); //la nouvelle pièce de la case est la notre
        caseADeplacer.setPiece(null); //l'ancienne case n'a plus de pièce
    }

    /**
     *
     * @param x
     * @param y
     * @param blanc
     * @return vrai si le roi de la couleur donnée est en echec
     */
    public boolean estEnEchec(int x, int y, boolean blanc){ //on pourra saisir les coordonnées des déplacement possibles du roi pour tester si il est en echec et mat

        LinkedList<Case> casesPossibles = new LinkedList<>();

        //On crée temporairement une case qui contient touts les paramètres de notre roi blanc
        Roi roiB = new Roi(blanc); //la pièce
        Case temporaireRoi = new Case(x,y, roiB);

        for(int i =0; i<8;i++){
            for(int j =0; j<8;j++){
                if((tabCases[i][j].getPiece() != null) && (blanc ? !tabCases[i][j].getPiece().isEstBlanc():tabCases[i][j].getPiece().isEstBlanc())){
                    casesPossibles.clear();
                    casesPossibles = tabCases[i][j].getPiece().afficherPossibilitees(i,j,tabCases);

                    if(casesPossibles.contains(temporaireRoi)){
                        //si l'une des pièces de l'adversaire peut manger le roi
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param blanc
     * @return vrai si le roi de la couleur donnée est en echec et mat
     */
    public boolean estEnEchecEtMat(boolean blanc){
        Vec2d position = this.positionRoi(blanc);
        return estEnEchec((int)position.x+1,(int) position.y+1,blanc)
             && estEnEchec((int)position.x,(int) position.y+1,blanc)
             && estEnEchec((int)position.x-1,(int) position.y+1,blanc)
             && estEnEchec((int)position.x+1,(int) position.y,blanc)
             && estEnEchec((int)position.x-1,(int) position.y,blanc)
             && estEnEchec((int)position.x+1,(int) position.y-1,blanc)
             && estEnEchec((int)position.x,(int) position.y-1,blanc)
             && estEnEchec((int)position.x-1,(int) position.y-1,blanc);
    }


    /**
     *
     * @param blanc
     * @return un vecteur 2D contenant la position du Roi sur l eplateau de jeu
     */
    public Vec2d positionRoi(boolean blanc){
        Vec2d position = new Vec2d(-1,-1);
        for(int i =0; i<8;i++){
            for(int j =0; j<8;j++){
                if(tabCases[i][j].getPiece()!= null && tabCases[i][j].getPiece().getNom()=="Roi" && (blanc ? !tabCases[i][j].getPiece().isEstBlanc():tabCases[i][j].getPiece().isEstBlanc())){
                    position = new Vec2d(i,j);
                    return position;
                }
            }
        }
        return position;
    }

    public void pionPromotion(int x, int y){
        System.out.println("PROMOTION POSSIBLE ! Votre pion est arrivé a la dernière rangée !");
        System.out.println("Vous voulez promouvoir votre pion en :");
        System.out.println("1 = Dame");
        System.out.println("2 = Four");
        System.out.println("3 = Tour");
        System.out.println("4 = Cavalier");
        System.out.println("5 = Aucun de cas ci-contre.");
        int choix = 0;
        boolean isEntier = false;
        boolean couleur;
        if(tabCases[x][y].getPiece() != null && tabCases[x][y].getPiece().isEstBlanc())
            couleur = true;
        else
            couleur = false;

        //Choix de la promotion
        while(choix > 5 || choix < 1) {
            do { //boucle pour tester si le joueur a bien saisie un nombre
                System.out.print("Veuillez saisir le numéro d'une des possibilités.");
                isEntier = true;
                Scanner scanner = new Scanner(System.in);
                try{
                    choix = scanner.nextInt();
                } catch (InputMismatchException e)
                {
                    System.out.println("La valeur saisie n'est pas un entier");
                    isEntier = false;
                }
                scanner.close();
            } while (isEntier != true);
        }

        //Promotion
        switch (choix){
            case(1):
                Dame dame = new Dame(couleur);
                tabCases[x][y].setPiece(dame);
                break;
            case(2):
                Fou fou = new Fou(couleur);
                tabCases[x][y].setPiece(fou);
                break;
            case(3):
                Tour tour = new Tour(couleur);
                tabCases[x][y].setPiece(tour);
                break;
            case(4):
                Cavalier cavalier = new Cavalier(couleur);
                tabCases[x][y].setPiece(cavalier);
                break;
            case(5):
                break;
        }


    }
}
