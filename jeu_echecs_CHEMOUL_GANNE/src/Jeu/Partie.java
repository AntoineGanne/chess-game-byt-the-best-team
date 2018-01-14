package Jeu;

import java.util.*;
import Pieces.Piece;
import com.sun.javafx.geom.Vec2d;


public class Partie {
    private Joueur j1;
    private Joueur j2;
    private int joueurActuel; //0 pour blanc, 1 pour noir
    private Plateau plateauJeu;
    private boolean finie;
    private LinkedList<Jeu.TourPartie> listeTourParties = new LinkedList<Jeu.TourPartie>();
    private boolean intelligenceArtificielle; //à 1 si le joueur veut jouer contre l'intelligenec artificielle

    /**
     * Constructeur de la classe
     */
    public Partie(){
        this.plateauJeu = new Plateau(8);
        this.joueurActuel = 0;
    }

    /**
     * Moteur de la partie
     */
    public void moteurPartie(){
        initPartie();
        if(this.estEnEchecEtMatPartie()){
            this.plateauJeu.affichageConsole();
            finie = true;
        }
        while(!this.finie) {
           afficherPartie();
           choixPieceEtDeplacement();
           //CHANGEMENT DU JOUEUR
           boolean blanc = (joueurActuel == 1)? true:false;
           joueurActuel =  (joueurActuel == 1)? 0:1;

           //TEST ECHEC
           if(plateauJeu.estEnEchec(blanc))
                System.out.println("Le Roi "+ ((blanc)? "blanc":"noir")+" est en ECHEC");

           //TEST FIN DE PARTIE
           if(this.estFinie())
               finie = true;
       }
    }

    /**
     * Dans cette fonction, le joueur choisie la case sur lauqelle se trouvela pièce qu'il souhaite déplacer.
     * Il choizit ensuite parmi les possibilités liées à cette pièce, celle qu'il préfère.
     */
    public void choixPieceEtDeplacement(){
        //CHOIX D'UNE PIECE A DEPLACER ET AFFICHAGE DES POSSIBILITES DE DEPLACEMENT
        int x, y;
        boolean IAjoue = (intelligenceArtificielle && joueurActuel==1)? true:false;
        Jeu.TourPartie t = new Jeu.TourPartie();
        LinkedList<Case> possibilites;
        Piece piece;
        do{
            do{
                if(this.plateauJeu.estEnEchec(false)) //Si le Roi est en echec il est obligatoire de le déplacer
                {   Vec2d posRoi = this.plateauJeu.positionRoi(false);
                    t.setLigne((int) posRoi.x);
                    t.setColonne((int) posRoi.y);
                }else if(this.plateauJeu.estEnEchec(true)){
                    System.out.println("Le roi blanc est en ECHEC, il doit être déplacé.");
                    Vec2d posRoi = this.plateauJeu.positionRoi(true);
                    t.setLigne((int) posRoi.x);
                    t.setColonne((int) posRoi.y);
                }else{
                    if(IAjoue){
                        t.choixIA();
                    }
                    else{
                        System.out.println("Saisissez la ligne et la colonne de la pièce que vous voulez deplacer. Et qui vous appartient !");
                        t.saisieChoix();
                    }
                }
                x = t.getLigne();
                y = t.getColonne();
                piece = this.plateauJeu.getTabCases()[x][y].getPiece();
            }//la pièce choisie appartient bien au joueur et n'est pas null
            while ((piece == null) || !((piece.isEstBlanc() && joueurActuel == 0) || (!piece.isEstBlanc() && joueurActuel == 1)));
            if(IAjoue)
                possibilites = this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases(),true);
            else
                possibilites = this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases(),false);
        }
        while(possibilites.size() == 0 || estEnEchecSiDeplace(possibilites,this.plateauJeu.getTabCases()[x][y],piece.isEstBlanc()));
        //on choisi bien une pièce qui peut se déplacer (par exemple le roi s'il n'et pas encerclé)
        //De plus parmi les possibilités de déplacement il en existe qui ne mettent pas le Roi en echec

         //CHOIX DU DEPLACEMENT PARMI LES POSSIBILITES
        int a ;
        if(IAjoue){
            Random rnd = new Random();
            do {//On choisie une nouvelle possibilité de déplacement tant que celle-ci met le Roi en echec
                a = rnd.nextInt(possibilites.size()) + 1;
            }while (this.plateauJeu.simulationDeplacement(this.plateauJeu.getTabCases()[x][y],possibilites.get(a-1).getX(),possibilites.get(a-1).getY(), piece.isEstBlanc()));
        }
        else{
            System.out.println("Saisissez le numéro de la possibilité que vous souhaitez appliquer");
            Scanner sc = new Scanner(System.in);
            a = sc.nextInt();
            while (a > possibilites.size() || a <= 0 || this.plateauJeu.simulationDeplacement(this.plateauJeu.getTabCases()[x][y],possibilites.get(a-1).getX(),possibilites.get(a-1).getY(), piece.isEstBlanc())) {
                a = sc.nextInt();
                System.out.println("Cette action n'est pas possible. " +
                        "Soit l'entier que vous entrez ne correspond pas aux possibilités, " +
                        "soit cela met votre Roi en echec." +
                        "Selectionnez une des " + possibilites.size() + " options");
            }
        }

        //GESTION DU DEPLACEMENT
        String nom = this.plateauJeu.getTabCases()[x][y].getPiece().getNom();
        if(!IAjoue)
            System.out.println("Vous deplacez votre " + nom + ".");
        int xFinal = possibilites.get(a-1).getX(); // a-1 car le joueur saisi entre [1,8] et non [0,7]
        int yFinal = possibilites.get(a-1).getY();
        t.setLigneDeplacFinal(xFinal); //on rajoute ces informations de deplacement au TourPartie
        t.setColonneDeplacFinal(yFinal);

        this.plateauJeu.deplacerPiecePlateau(this.plateauJeu.getTabCases()[x][y],xFinal, yFinal);
        this.listeTourParties.add(t);

        //----------------------------------
        //Test promotion
        if(derniereLigne(xFinal) && nom.contains("Pion"))
            this.plateauJeu.pionPromotion(xFinal,yFinal, IAjoue);
        //----------------------------------
    }

    /**
     * Initialisation de la partie
     */
    public void initPartie() {
        Scanner num = new Scanner(System.in);
        System.out.println("Saisir le pseudo du premier joueur: ");
        String p1 = num.next();
        j1 = new Joueur(p1);
        System.out.println("Saisir le pseudo du second joueur: ");
        String p2 = num.next();
        j2 = new Joueur(p2);
        plateauJeu.demanderFichier();
        intelligenceArtficielle();
    }

    public boolean derniereLigne(int x){
        boolean blanc = (joueurActuel == 0)? true:false;
        if(blanc)
            return x==0;
        else
            return x==7;
    }

    /**
     * Affiche l'état de la partie: tour, joueurs, grille
     */
    public void afficherPartie() {
        System.out.println(j1.getPseudo() + ": Blanc  " + j2.getPseudo() + ": Noir");
        System.out.println("Tour " + (this.listeTourParties.size()+1) +" :");
        String pseudo = (joueurActuel == 0)? j1.getPseudo():j2.getPseudo();
        System.out.println("C'est a " + pseudo + " de jouer.");
        plateauJeu.affichageConsole();
    }

    /**
     * Test si la partie est finie et gère l'affichage du gagnant
     * @return vrai si la partie est finie
     */
    public boolean estFinie(){
        if(this.plateauJeu.isRoiBlancMort()){
            //afichage gagnant
            System.out.println("Les Noirs ont gagnés ! Bravo !");
            return true;
        }
        if(this.plateauJeu.isRoiNoirMort()){
            System.out.println("Les Blancs ont gagnés ! Bravo !");
            //afichage gagnant
            return true;
        }
        if(estEnEchecEtMatPartie())
            return true;
        return false;
    }

    public boolean estEnEchecEtMatPartie(){
        if(this.plateauJeu.estEnEchecEtMat(true))
        {
            System.out.println("Le Roi Blanc est en ECHEC ET MAT");
            System.out.println("Les Noirs ont gagnés ! Bravo !");
            return true;
        }
        if(this.plateauJeu.estEnEchecEtMat(false))
        {
            System.out.println("Le Roi Noir est en ECHEC ET MAT");
            System.out.println("Les Blancs ont gagnés ! Bravo !");
            return true;
        }
        return false;
    }

    /**
     * permet de savoir si le joueur veut jouer contre l'IA et de mettre à jour en conséquant le booléen correspondant
     */
    public void intelligenceArtficielle(){
        int tmp = -1;
        boolean isEntier = true;
        Scanner scanner;
        do{
            System.out.println("Voulez vous jouer contre l'intelligence artificielle ? (1 = oui, 0 = non)");
            try{
                scanner = new Scanner(System.in);
                tmp = scanner.nextInt();
                isEntier = true;
            } catch (InputMismatchException e)
            {
                System.out.println("La valeur saisie n'est pas 0 ou 1");
                isEntier = false;
            }
        }while(isEntier != true && tmp !=  1 && tmp !=0);
        if(tmp == 1)
            this.intelligenceArtificielle = true;
        else
            this.intelligenceArtificielle = false;
    }

    /**
     * Cette focntion permet de determiner si un déplacement mais le Roi en echec.
     * @param possibilites
     * @param caseADeplac
     * @param blanc
     * @return
     */
    public boolean estEnEchecSiDeplace(LinkedList<Case> possibilites, Case caseADeplac,boolean blanc){
        if(possibilites.size() == 1){
            return this.plateauJeu.simulationDeplacement(caseADeplac,possibilites.get(0).getX(),possibilites.get(0).getY(),blanc);
        }
        for(int i=0;i<possibilites.size();i++){
            if(!this.plateauJeu.simulationDeplacement(caseADeplac,possibilites.get(0).getX(),possibilites.get(0).getY(),blanc))
                return false;
        }
        return false;
    }
}