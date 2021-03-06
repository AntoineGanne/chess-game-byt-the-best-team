package JeuGraphique;

import Pieces.*;

import Pieces.Cavalier;
import com.sun.istack.internal.NotNull;
import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.*;

import java.util.LinkedList;
import java.io.*;

public class PlateauG {
    private final int TAILLE;
    private CaseG[][] tabCases;
    private boolean roiBlancMort;
    private boolean roiNoirMort;
    private int compteurToursSansPrises; //La partie est finie si 50 coups sans prises ont été joués à la suite
    private boolean aRoqueBlanc=false;
    private boolean aRoqueNoir=false;


    /**
     * Constructeur principal de la classe PlateauG.
     * @param taille int
     */
    public PlateauG(int taille) {
        this.TAILLE = taille;
        this.tabCases = new CaseG[taille][taille];
        this.initialiserPlateau();
        this.roiBlancMort = false;
        this.roiNoirMort = false;
        this.compteurToursSansPrises = 0;
        this.chargerConfiguration();
    }

    /**
     * Constructeur par copie de la classe PlateauG.
     * @param p PlateauG
     */
    public PlateauG(@NotNull PlateauG p) {
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

    /**
     * Demande un fichier de configuration present sur l'ordinateur.
     * @return le nom du fichier
     */
    public String demanderConfiguration(){
        int retour;
        String workingDir = System.getProperty("user.dir");
        JFileChooser dialogue = new JFileChooser();
        dialogue.setCurrentDirectory(new File (workingDir));
        dialogue.getFileSystemView();
        do{
            dialogue.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter ff = new FileNameExtensionFilter("Fichiers texte", "txt");
            dialogue.setFileFilter(ff);
            retour = dialogue.showOpenDialog(null);
        }while(retour != JFileChooser.APPROVE_OPTION);

        return dialogue.getSelectedFile().getAbsolutePath();
    }

    //GETTER ET SETTER

    /**
     *
     * @return int
     */
    public int getTAILLE() {
        return TAILLE;
    }

    /**
     *
     * @return int
     */
    public int getCompteurToursSansPrises() {
        return compteurToursSansPrises;
    }

    /**
     *
     * @return CaseG[][]
     */
    public CaseG[][] getTabCases() {
        return tabCases;
    }

    /**
     *
     * @return boolean
     */
    public boolean isRoiBlancMort() {
        return roiBlancMort;
    }

    /**
     *
     * @return boolean
     */
    public boolean isRoiNoirMort() {
        return roiNoirMort;
    }

    /**
     * On initialises le plateau de jeu.
     */
    private void initialiserPlateau(){
        for(int i = 0;i<TAILLE;i++) {
            for (int j = 0; j < TAILLE; j++) {
                tabCases[i][j] = new CaseG(i,j,null);
            }
        }
    }

    /**
     * On charge le plateau avec les pieces inscrites sur le fichier de configuration.
     */
    private void chargerConfiguration(){
        String ligne = "";
        String fichier = this.demanderConfiguration();
       // String fichier = "configBase.txt";
        String [] mot;

        BufferedReader ficTexte;
        try {
            ficTexte = new BufferedReader(new FileReader(new File(fichier)));
            ligne = ficTexte.readLine();
            if (ligne != null) {
                mot = ligne.split (" ");

                //traitement du chargement
                boolean couleur = true;
                String lettre = "";
                String[] position;
                int a,b,i=0;
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gère le deplacement d'une piece sur le plateau et ce que cela implique (manger une pièce de l'adversaire, changer l'état de la pièce,..)
     * @param xFinal coordonee finale x de la piece sur la grille
     * @param yFinal coordonee finale y de la piece sur la grille
     * @param caseADeplacer CaseG
     */
    public void deplacerPiecePlateau(CaseG caseADeplacer, int xFinal, int yFinal){
        if(tabCases[xFinal][yFinal].getPiece()!= null) { //si la case du déplacement n'est pas vide
            System.out.println("Vous avez mangé une pièce de l'adversaire : " + tabCases[xFinal][yFinal].getPiece().getNom() + ".");
            this.compteurToursSansPrises = 0; //Une pièce a été mangée on remet le compteur à 0.

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
        caseADeplacer.getPiece().setPositionInitiale(false);
        caseADeplacer.setPiece(null); //l'ancienne case n'a plus de pièce

    }

    /**
     * Cette fonction permet de savoir si un deplacement quelconque met notre propre roi en echec.
     * @param caseADeplacer Case
     * @param xFinal ligne finale
     * @param yFinal colonne finale
     * @param blanc couleur du Joueur
     * @return boolean
     */
    public boolean simulationDeplacement(CaseG caseADeplacer, int xFinal, int yFinal, boolean blanc){
        PlateauG plateauTemp = new PlateauG(this);
        int a = caseADeplacer.getX();
        int b = caseADeplacer.getY();

        if(a != xFinal || b!=yFinal){ //si on ne déplace pas sur la même case
            //On effectue le déplacement
            plateauTemp.tabCases[xFinal][yFinal].setPiece(plateauTemp.tabCases[a][b].getPiece()); //la nouvelle pièce de la case est la notre
            plateauTemp.tabCases[a][b].setPiece(null); //l'ancienne case n'a plus de pièce
        }
        return plateauTemp.estEnEchec(blanc);
    }
    /**
     *
     * @param x ligne
     * @param y colonne
     * @param blanc couleur du Joueur
     * @return vrai si le roi de la couleur donnee est en echec a la position (x,y) donnee
     */
    public boolean estEnEchec(int x, int y, boolean blanc){ //on pourra saisir les coordonnées des déplacement possibles du roi pour tester si il est en echec et mat
        LinkedList<CaseG> casesPossibles = new LinkedList<>();
        for(int i =0; i<TAILLE;i++){
            for(int j =0; j<TAILLE;j++){
                // on s'intéresse aux pièces adverses
                if((tabCases[i][j].getPiece() != null) && ((blanc) != tabCases[i][j].getPiece().isEstBlanc())){
                    casesPossibles.clear();
                    casesPossibles = tabCases[i][j].getPiece().afficherPossibilitees(i,j,this);
                    if(tabCases[i][j].getPiece().getNom().equals("Pion"))
                    {
                        for(int k=0;k<casesPossibles.size();k++)
                        {
                            if(casesPossibles.get(k).getY()==j)
                                casesPossibles.remove(k);
                        }
                    }
                    if(casesPossibles.contains(tabCases[x][y]))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Retourne vrai si le roi de la couleur donnee est en echec
     * @param blanc couleur du Joueur
     * @return boolean
     */
    public boolean estEnEchec(boolean blanc){
        Vec2d posRoi=this.positionRoi(blanc);
        if(posRoi!= null){
            return estEnEchec((int)posRoi.x,(int)posRoi.y,blanc);
        }
        return true; // au cas ou il manque un Roi sur la configuration chargée (ne devrait pas manquer sauf erreur fichier)
    }


    /**
     * Retourne vrai si le roi de la couleur donnee est en echec et mat
     * @param blanc couleur du Joueur
     * @return boolean
     */
    public boolean estEnEchecEtMat(boolean blanc) {
        if(!this.isRoiBlancMort() && !this.isRoiNoirMort()){
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
        return false;
    }

    /**
     * Retourne un vecteur 2D contenant la position du Roi (de la couleur precisée par le boolean blanc) sur le plateau de jeu
     * @param blanc couleur du Joueur
     * @return Vec2d
     */
    public Vec2d positionRoi(boolean blanc){
        Vec2d position;
        for(int i =0; i<TAILLE;i++){
            for(int j =0; j<TAILLE;j++){
                if(tabCases[i][j].getPiece()!= null && Objects.equals(tabCases[i][j].getPiece().getNom(), "Roi") && (blanc == tabCases[i][j].getPiece().isEstBlanc())){
                    position = new Vec2d(i,j);
                    return position;
                }
            }
        }
        return null;
    }

    /**
     * Gere la promotion du pion lorsqu'il arrive en fin de ligne. Automatise pour l'IA.
     * @param x coordonnée x du pion
     * @param y coordonnée y du pion
     * @param IA si c'est au tour de l'IA
     */
    public void pionPromotion(int x, int y, boolean IA){
        String choix;
        boolean couleur;
        String[] promotion = {"Dame", "Fou", "Tour","Cavalier","Aucun"};
        couleur = tabCases[x][y].getPiece() != null && tabCases[x][y].getPiece().isEstBlanc();
        //Choix de la promotion
        if(IA){
            int c = (int)(Math.random()*4);
            choix = promotion[c];
        }else {
            JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
            String nom = (String) JOptionPane.showInputDialog(null,
                    "Vous souhaitez promouvoir votre pion en : ",
                    "PROMOTION !",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    promotion,
                    promotion[4]);
            choix = nom;
        }
        //Promotion
        switch (choix){
            case("Dame"):
                Dame dame = new Dame(couleur);
                tabCases[x][y].setPiece(dame);
                break;
            case("Fou"):
                Fou fou = new Fou(couleur);
                tabCases[x][y].setPiece(fou);
                break;
            case("Tour"):
                Tour tour = new Tour(couleur);
                tabCases[x][y].setPiece(tour);
                break;
            case("Cavalier"):
                Cavalier cavalier = new Cavalier(couleur);
                tabCases[x][y].setPiece(cavalier);
                break;
            default:
                break;
        }
    }

    /**
     * Retourne vrai si le petit Roque est possible pour le Roi.
     * @param blanc couleur du Joueur
     * @return boolean
     */
    public boolean petitRoquePossible(boolean blanc){
        Vec2d posRoi = positionRoi(blanc);
        int a = (int)posRoi.x;
        int b = (int)posRoi.y;
        boolean roquePasEncoreJoue = (blanc)? !this.aRoqueBlanc:!this.aRoqueNoir;
        if(tabCases[a][b].getPiece().isPositionInitiale())
        {
            int ligne = (blanc)? 7:0; //Le roque s'effectue sur la ligne de départ
            if(a == ligne && b == 4){
                for(int i=1;i<3;i++){
                    if(!this.getTabCases()[ligne][4+i].estVide() || simulationDeplacement(tabCases[a][b],ligne,4+i,blanc)){
                        return false;
                    }
                }
            }else{
                return false;
            }
            return ((roquePasEncoreJoue && this.getTabCases()[ligne][7].getPiece() != null) && this.getTabCases()[ligne][7].getPiece().getNom().equals("Tour") && this.getTabCases()[ligne][7].getPiece().isPositionInitiale());
        }
        return false;
    }

    /**
     * Retourne vrai si le grand Roque est possible pour le Roi.
     * @param blanc couleur du Joueur
     * @return boolean
     */
    public boolean grandRoquePossible(boolean blanc){
        Vec2d posRoi = positionRoi(blanc);
        int ligne = (blanc)? 7:0; //Le roque s'effectue sur la ligne de départ
        int a = (int)posRoi.x;
        int b = (int)posRoi.y;
        boolean roquePasEncoreJoue = (blanc)? !this.aRoqueBlanc:!this.aRoqueNoir;
        if(tabCases[a][b].getPiece().isPositionInitiale())
        {
            if(a == ligne && b == 4){
                for(int i=-1;i>=-4;i--){
                    if(i+4!=0 && !this.getTabCases()[ligne][4+i].estVide())
                        return false;
                }
            }else{
                return false;
            }
            return (roquePasEncoreJoue && this.getTabCases()[ligne][0].getPiece() != null) && this.getTabCases()[ligne][0].getPiece().getNom().contains("Tour") && this.getTabCases()[ligne][0].getPiece().isPositionInitiale();
        }else
            return false;
    }

    /**
     * Cette methode permet d'effectuer le Roque.
     * @param grand si c'est le grand Roque
     * @param blanc la couleur du Roi
     */
    public void effectuerRoque(boolean grand, boolean blanc){
        int ligne = (blanc)? 7:0;
        if(grand){
            deplacerPiecePlateau(this.getTabCases()[ligne][4],ligne,2); //On déplace le Roi
            deplacerPiecePlateau(this.getTabCases()[ligne][0],ligne,3); //On déplace la Tour
        }else{
            deplacerPiecePlateau(this.getTabCases()[ligne][4],ligne,6); //On déplace le Roi
            deplacerPiecePlateau(this.getTabCases()[ligne][7],ligne,5); //On déplace la Tour
        }
        if(blanc)
            this.aRoqueBlanc = true;
        else
            this.aRoqueNoir = true;

    }

    /**
     * Gestion de la prise en passant du pion quand il se deplace de 2 cases et que l'adversaire peut le prendre sur la diagonale.
     * @param i ligne
     * @param j colonne
     * @param blanc couleur du Joueur
     * @param IA vrai si l'IA est active
     * @return vrai si la prise en passant c'est effectuée
     */
    public boolean priseEnPassant(int i, int j, boolean blanc, boolean IA){
        boolean deuxCaseDepl = (blanc)? (i==4):(i==3);
        int option = -10,choix = -10;
        if(this.getTabCases()[i][j].getPiece()!= null && this.getTabCases()[i][j].getPiece().getNom().equals("Pion") && deuxCaseDepl){
            LinkedList<CaseG> possPionPrise= new LinkedList<>(); //pion de l'adversaire qui peuvent prendre en passant
            int ligne = (blanc)? 4:3;

            if(j+1 <= 7 && this.getTabCases()[ligne][j+1].getPiece() != null && this.getTabCases()[ligne][j+1].getPiece().getNom().equals("Pion")){
                boolean couleur1 = this.getTabCases()[ligne][j+1].getPiece().isEstBlanc();
                if((blanc) != couleur1)
                    possPionPrise.add(this.getTabCases()[ligne][j+1]);
            }
            if(j-1 >= 0 && this.getTabCases()[ligne][j-1].getPiece() != null && this.getTabCases()[ligne][j-1].getPiece().getNom().equals("Pion")){
                boolean couleur2 = this.getTabCases()[ligne][j-1].getPiece().isEstBlanc();
                if((blanc) != couleur2)
                    possPionPrise.add(this.getTabCases()[ligne][j-1]);
            }
            if(IA){
                Random randChoix = new Random();
                choix = randChoix.nextInt(2);
            }else{
                JOptionPane jop = new JOptionPane();
                //DEMANDE AU JOUEUR
                if(possPionPrise.size()!=0){
                    option = JOptionPane.showConfirmDialog(null, "Voulez-vous prendre le pion de l'adversaire en passant ?", "Prise en passant", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                }
            }
            if((option == 0 || choix == 0) && possPionPrise.size()!=0){
                Random rd = new Random();
                int l=rd.nextInt(possPionPrise.size());
                deplacerPiecePlateau(possPionPrise.get(l),(blanc)? i+1:i-1,j);
                this.getTabCases()[i][j].setPiece(null);
                return true;
            }
            else
                return false;
        }
        return false;
    }
}