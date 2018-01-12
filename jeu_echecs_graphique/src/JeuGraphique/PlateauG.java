package JeuGraphique;

import Pieces.*;

import Pieces.Cavalier;
import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.util.*;

import java.util.LinkedList;
import java.io.*;

public class PlateauG {
    private final int TAILLE;
    private CaseG[][] tabCases;
    private boolean roiBlancMort;
    private boolean roiNoirMort;
    private int compteurToursSansPrises; //La partie est finie si 50 coups sans prises ont été joués à la suite

    public PlateauG(int taille) {
        this.TAILLE = taille;
        this.tabCases = new CaseG[taille][taille];
        this.initialiserPlateau();
        this.roiBlancMort = false;
        this.roiNoirMort = false;
        this.compteurToursSansPrises = 0;
        this.demanderEtChargerFichier();
    }

    public PlateauG(PlateauG p) {
        this.TAILLE = p.getTAILLE();
        this.tabCases = new CaseG[p.getTAILLE()][p.getTAILLE()];
        for(int i = 0; i<TAILLE; i++){
            for(int j=0;j<TAILLE;j++)
                this.tabCases[i][j] = new CaseG(p.getTabCases()[i][j]);
        }
        this.roiBlancMort = p.isRoiBlancMort();
        this.roiNoirMort = p.isRoiNoirMort();
        this.compteurToursSansPrises = p.getCompteurToursSansPrises();
    }

    public String demanderConfiguration(){
        JFileChooser dialogue = new JFileChooser();
        dialogue.showOpenDialog(null);
        return dialogue.getSelectedFile().getAbsolutePath();
    }


    public int getTAILLE() {
        return TAILLE;
    }

    public int getCompteurToursSansPrises() {
        return compteurToursSansPrises;
    }

    public CaseG[][] getTabCases() {
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
        for(int i = 0;i<TAILLE;i++) {
            for (int j = 0; j < TAILLE; j++) {
                tabCases[i][j] = new CaseG(i,j,null);
            }
        }
    }

    public void demanderEtChargerFichier(){
        String ligne = "";
        String fichier = this.demanderConfiguration();
        String [] mot;

        BufferedReader ficTexte;
        try {
            ficTexte = new BufferedReader(new FileReader(new File(fichier)));
            if (ficTexte == null) {
                throw new FileNotFoundException("Fichier non trouvé : "
                        + fichier);
            }
            ligne = ficTexte.readLine();
            if (ligne != null) {
                mot = ligne.split (" ");

                //traitement du chargement
                boolean couleur = true;
                String lettre = "";
                String[] position;
                int a,b,i=0;

                boolean estPiece; //si on traite la pièce ou sa position
                Pion pion;
                Dame dame;
                Roi roi;
                Fou fou;
                Cavalier cavalier;
                Tour tour;
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
                                tabCases[b][a] = new CaseG(b,a, pion);
                            }
                            else if(lettre.contains("t")) //tower
                            {
                                tour = new Tour(couleur);
                                tabCases[b][a] = new CaseG(b,a, tour);
                            }
                            else if(lettre.contains("q")) //queen
                            {
                                dame = new Dame(couleur);
                                tabCases[b][a] = new CaseG(b,a, dame);
                            }
                            else if(lettre.contains("b")) //bishop
                            {
                                fou = new Fou(couleur);
                                tabCases[b][a] = new CaseG(b,a, fou);
                            }
                            else if(lettre.contains("k1") || lettre.contains("k2")) //knight
                            {
                                cavalier = new Cavalier(couleur);
                                tabCases[b][a] = new CaseG(b,a, cavalier);
                            }
                            else //king
                            {
                                roi = new Roi(couleur);
                                tabCases[b][a] = new CaseG(b,a, roi);
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

    }

    /**
     *
     * @param xFinal coordonee finale x de la piece sur la grille
     * @param yFinal coordonee finale y de la piece sur la grille
     */
    public void deplacerPiecePlateau(CaseG caseADeplacer, int xFinal, int yFinal){
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

    public boolean simulationDeplacement(CaseG caseADeplacer, int xFinal, int yFinal, boolean blanc){
        PlateauG plateauTemp = new PlateauG(this);
        int a = caseADeplacer.getX();
        int b = caseADeplacer.getY();

        //On effectue le déplacement
        plateauTemp.tabCases[xFinal][yFinal].setPiece(new Piece(plateauTemp.tabCases[a][b].getPiece())); //la nouvelle pièce de la case est la notre
        plateauTemp.tabCases[a][b].setPiece(null); //l'ancienne case n'a plus de pièce

        //System.out.println(plateauTemp.estEnEchec(xFinal,yFinal,blanc));
        return plateauTemp.estEnEchec(xFinal,yFinal,blanc);
    }
    /**
     *
     * @param x
     * @param y
     * @param blanc
     * @return vrai si le roi de la couleur donnée est en echec a la position (x,y) donnée
     */
    public boolean estEnEchec(int x, int y, boolean blanc){ //on pourra saisir les coordonnées des déplacement possibles du roi pour tester si il est en echec et mat
        LinkedList<CaseG> casesPossibles = new LinkedList<>();
        for(int i =0; i<TAILLE;i++){
            for(int j =0; j<TAILLE;j++){
                // on s'intéresse aux pièces adverses
                if((tabCases[i][j].getPiece() != null) && ((blanc)? !tabCases[i][j].getPiece().isEstBlanc():tabCases[i][j].getPiece().isEstBlanc())){
                    casesPossibles.clear();
                    casesPossibles = tabCases[i][j].getPiece().afficherPossibilitees(i,j,tabCases);
                    for(CaseG c:casesPossibles){
                        //si l'une des cases possibles pour l'adversaire est la position (x,y) donnée alors cette position est un échec
                        if(c.getX()==x && c.getY()==y) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * @param blanc
     * @return vrai si le roi de la couleur donnée est en echec
     */
    public boolean estEnEchec(boolean blanc){
        Vec2d posRoi=this.positionRoi(blanc);
        return estEnEchec((int)posRoi.x,(int)posRoi.y,blanc);
    }


    /**
     *
     * @param blanc
     * @return vrai si le roi de la couleur donnée est en echec et mat
     */
    public boolean estEnEchecEtMat(boolean blanc) {
        if (estEnEchec(blanc)) { //si le Roi est en echec on peut verifier si il est en echec et mat
            //penser a verifier si aucune autre piece ne peut suspendre son echec
            Vec2d position = positionRoi(blanc);
            if(position != null){
                int a = (int) position.x, b = (int) position.y;
                boolean resultat = true;
                for (int i = a - 1; i <= a + 1; ++i) {
                    for (int j = b - 1; j <= b + 1; ++j) {
                        if (i >= 0 && i < TAILLE && j >= 0 && j < TAILLE)
                            resultat = resultat && this.simulationDeplacement(this.tabCases[a][b], i, j, blanc);
                    }
                }
                return resultat;
            }
            return false;
        }
        return false;
    }

    /**
     *
     * @param blanc
     * @return un vecteur 2D contenant la position du Roi (de la couleur précisée par le boolean blanc) sur le plateau de jeu
     */
    public Vec2d positionRoi(boolean blanc){
        Vec2d position = null;
        for(int i =0; i<TAILLE;i++){
            for(int j =0; j<TAILLE;j++){
                if(tabCases[i][j].getPiece()!= null && Objects.equals(tabCases[i][j].getPiece().getNom(), "Roi") && (blanc ? tabCases[i][j].getPiece().isEstBlanc():!tabCases[i][j].getPiece().isEstBlanc())){
                    position = new Vec2d(i,j);
                    return position;
                }
            }
        }
        return position;
    }

    public void pionPromotion(int x, int y, boolean IA){
        System.out.println("PROMOTION POSSIBLE ! Votre pion est arrivé a la dernière rangée !");
        int choix = 0;
        boolean isEntier = false;
        boolean couleur;
        if(tabCases[x][y].getPiece() != null && tabCases[x][y].getPiece().isEstBlanc())
            couleur = true;
        else
            couleur = false;

        //Choix de la promotion
        if(IA){
            choix = (int)(Math.random()*4 + 1);
            System.out.println(choix);
        }else{
            System.out.println("Vous voulez promouvoir votre pion en :");
            System.out.println("1 = Dame");
            System.out.println("2 = Four");
            System.out.println("3 = Tour");
            System.out.println("4 = Cavalier");
            System.out.println("5 = Aucun de cas ci-contre.");
            while(choix > 5 || choix < 1) {
                do { //boucle pour tester si le joueur a bien saisie un nombre
                    System.out.print("Veuillez saisir le numéro de l'une des possibilités.");
                    isEntier = true;
                    Scanner scanner = new Scanner(System.in);
                    try{
                        choix = scanner.nextInt();
                    } catch (InputMismatchException e)
                    {
                        System.out.println("La valeur saisie n'est pas un entier");
                        isEntier = false;
                    }
                } while (isEntier != true);
            }
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