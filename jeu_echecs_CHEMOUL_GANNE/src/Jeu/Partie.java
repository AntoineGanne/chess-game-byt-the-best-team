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

    public Partie(){
        this.plateauJeu = new Plateau(8);
        this.joueurActuel = 0;
    }

    public void moteurPartie(){
        initPartie();
        while(!this.finie) {
           afficherPartie();

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
               }
                while ((this.plateauJeu.getTabCases()[x][y].getPiece() == null) || !((this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 0) || (!this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 1)));
                //la pièce choisie appartient bien au joueur et n'est pas null

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

           //CHANGEMENT DU JOUEUR
           boolean blanc = false;
           if (joueurActuel == 1){
               blanc = true;
               joueurActuel = 0;
           }
           else
               joueurActuel = 1;

           //TEST ECHEC
           Vec2d position = plateauJeu.positionRoi(blanc);
           if(plateauJeu.estEnEchec((int) position.x, (int) position.y,blanc))
                System.out.println("Le Roi "+ ((blanc)? "blanc":"noir")+" est en ECHEC");

           //TEST FIN DE PARTIE
           if(this.estFinie()){
               finie = true;
           }

       }
    }

    //Initialisation de la partie
    public void initPartie() {
        Scanner num = new Scanner(System.in);
        System.out.println("Saisir le pseudo du premier joueur: ");
        String p1 = num.next();
        j1 = new Joueur(p1);
        System.out.println("Saisir le pseudo du second joueur: ");
        String p2 = num.next();
        j2 = new Joueur(p2);
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

}
