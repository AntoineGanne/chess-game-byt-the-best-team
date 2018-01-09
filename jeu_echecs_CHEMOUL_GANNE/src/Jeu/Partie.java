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
    private LinkedList<tourPartie> listeTourParties = new LinkedList<tourPartie>();
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
        while(!this.finie) {
           afficherPartie();
           if(intelligenceArtificielle) {
               if (joueurActuel == 1) {
                   choixPieceEtDeplacementIA();
               }else
                   choixPieceEtDeplacement();
           }else{
               choixPieceEtDeplacement();
           }
           //CHANGEMENT DU JOUEUR
           boolean blanc = false;
           if (joueurActuel == 1){
               blanc = true;
               joueurActuel = 0;
           }
           else{
               blanc =false;
               joueurActuel = 1;
           }


           //TEST ECHEC
           if(plateauJeu.estEnEchec(blanc))
                System.out.println("Le Roi "+ ((blanc)? "blanc":"noir")+" est en ECHEC");

           //TEST FIN DE PARTIE
           if(this.estFinie()){
               finie = true;
           }

       }
    }

    /**
     * Dans cette fonction, le joueur choisie la case sur lauqelle se trouvela pièce qu'il souhaite déplacer.
     * Il choizit ensuite parmi les possibilités liées à cette pièce, celle qu'il préfère.
     */
    public void choixPieceEtDeplacement(){
        //CHOIX D'UNE PIECE A DEPLACER ET AFFICHAGE DES POSSIBILITES DE DEPLACEMENT
        int x, y;
        tourPartie t = new tourPartie();
        LinkedList<Case> possibilites = new LinkedList<>();
        do{
            do{
                System.out.println("Saisissez la ligne et la colonne de la pièce que vous voulez deplacer. Et qui vous appartient !");
                t.saisieChoix();
                x = t.getLigne();
                y = t.getColonne();
            }//la pièce choisie appartient bien au joueur et n'est pas null
            while ((this.plateauJeu.getTabCases()[x][y].getPiece() == null) || !((this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 0) || (!this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 1)));
            possibilites = this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases());
        }
        while(possibilites.size() == 0); //on choisi bien une pièce qui peut se déplacer (par exemple le roi s'il n'et pas encerclé)

        //CHOIX DU DEPLACEMENT PARMI LES POSSIBILITES
        System.out.println("Saisissez la possibilité que vous souhaitez appliquer");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        while (a > possibilites.size() || a <= 0) {//tant que la possibilité choisie n'est pas comprises dans celles renvoyées
            a = sc.nextInt();
            System.out.println("Cette action n'est pas possible, selectionnez une des " + possibilites.size() + " options");
        }

        //GESTION DU DEPLACEMENT
        System.out.println("Vous deplacez votre " + this.plateauJeu.getTabCases()[x][y].getPiece().getNom() + ".");
        int xFinal = possibilites.get(a-1).getX(); // a-1 car le joueur saisi entre [1,8] et non [0,7]
        int yFinal = possibilites.get(a-1).getY();
        t.setLigneDeplacFinal(xFinal); //on rajoute ces informations de deplacement au tourPartie
        t.setColonneDeplacFinal(yFinal);

        this.plateauJeu.deplacerPiecePlateau(this.plateauJeu.getTabCases()[x][y],xFinal, yFinal);

        this.listeTourParties.add(t);
        //----------------------------------
        //Test promotion
        //if(derniereLigne())
        //    this.plateauJeu.pionPromotion(xFinal,yFinal);
        //----------------------------------
    }

    /**
     * Cette classe gère le tour de l'intelligence artificielle.
     * Si l'IA est en echec, on gère le déplacment du roi.
     * Sinon on choisit une case au hazard qui lui appartient, puis une possibilité de déplacement au hazard.
     */
    public void choixPieceEtDeplacementIA(){
        int x, y,poss;
        Piece piece;
        tourPartie t = new tourPartie();
        LinkedList<Case> possibilites;
        t.choixIA();
        do{
            do{
                t.choixIA();
                x = t.getLigne();
                y = t.getColonne();
                //piece = new Piece(this.plateauJeu.getTabCases()[x][y].getPiece());
                piece = this.plateauJeu.getTabCases()[x][y].getPiece();
            }
            while ((piece == null) || !((piece.isEstBlanc() && joueurActuel == 0) || (!piece.isEstBlanc() && joueurActuel == 1)));
            possibilites = this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases());
        }
        while(possibilites.size() == 0); //on choisi bien une pièce qui peut se déplacer (par exemple le roi s'il n'et pas encerclé)
        Random rnd = new Random();
        poss = rnd.nextInt(possibilites.size()-1);

        System.out.println("Vous deplacez votre " + piece.getNom() + ".");
        int xFinal = possibilites.get(poss).getX();
        int yFinal = possibilites.get(poss).getY();
        t.setLigneDeplacFinal(xFinal); //on rajoute ces informations de deplacement au tourPartie
        t.setColonneDeplacFinal(yFinal);

        this.plateauJeu.deplacerPiecePlateau(this.plateauJeu.getTabCases()[x][y],xFinal, yFinal);

        this.listeTourParties.add(t);
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
        plateauJeu.demanderEtChargerFichier();
        intelligenceartficielle();
    }

    /**
     * Affiche l'état de la partie: tour, joueurs, grille
     */
    public void afficherPartie() {
        System.out.println(j1.getPseudo() + ": Blanc  " + j2.getPseudo() + ": Noir");
        System.out.println("Tour " + (this.listeTourParties.size()+1) +" :");
        if(this.joueurActuel == 0)
            System.out.println("C'est a " + j1.getPseudo() + " de jouer.");
        else
            System.out.println("C'est a " + j2.getPseudo() + " de jouer.");
        plateauJeu.affichageConsole();
    }

    /**
     * Test si la partie est finie et gère l'affichage du gagnant
     * @return vrai si la partie est finie
     */
    public boolean estFinie(){
        /*if(this.plateauJeu.estEnEchecEtMat(true))
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
        }*/
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
        return false;
    }

    /**
     * permet de savoir si le joueur veut jouer contre l'IA et de mettre à jour en conséquant le booléen correspondant
     */
    public void intelligenceartficielle(){
        int tmp = -1;
        boolean isEntier = true;
        Scanner scanner = new Scanner(System.in);
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
}
