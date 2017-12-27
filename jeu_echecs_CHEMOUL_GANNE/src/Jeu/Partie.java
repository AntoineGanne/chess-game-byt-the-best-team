package Jeu;

import java.util.*;
import Pieces.Piece;

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
           int x, y;
           tourPartie t = new tourPartie();
           do {
               System.out.println("Saisissez la ligne et la colonne de la pièce que vous voulez deplacer. Et qui vous appartient !");
               t.saisieChoix();
               x = t.getLigne();
               y = t.getColonne();
           }
           while (!((this.plateauJeu.getTabCases()[x][y].getPiece() == null) ||(this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 0) || (!this.plateauJeu.getTabCases()[x][y].getPiece().isEstBlanc() && joueurActuel == 1)));
           //permet de verifier que la pièce chosie appartient bien au joueur


           LinkedList<Case> possibilités = this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases());

           System.out.println("Saisissez la possibilité que vous souhaitez appliquer");
           Scanner sc = new Scanner(System.in);
           int a = sc.nextInt();
           while (a > possibilités.size() || a <= 0) {//tant que la possibilité choisie n'est pas comprises dans celles renvoyées
               a = sc.nextInt();
               System.out.println("Cette action n'est pas possible, selectionnez une des " + possibilités.size() + "options");
           }
           //gestion du déplacement
           this.plateauJeu.deplacerPiecePlateau(this.plateauJeu.getTabCases()[x][y],possibilités.get(a-1).getX(), possibilités.get(a-1).getY());

           this.listeTourParties.add(t);
           if (joueurActuel == 1){
               joueurActuel = 0;
            }
           else
               joueurActuel = 1;
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

    //Affiche l'état de la partie: tour, joueurs, grille
    public void afficherPartie() {
        System.out.println(j1.getPseudo() + ": Blanc  " + j2.getPseudo() + ": Noir");
        if(this.joueurActuel == 0)
            System.out.println("C'est a " + j1.getPseudo() + " de jouer.");
        else
            System.out.println("C'est a " + j2.getPseudo() + " de jouer.");
        plateauJeu.affichageConsole();
    }

    //Test si la partie est finie et gère l'affichage du gagnant
    public boolean estFinie(){
        if(this.plateauJeu.getNbrBlanchesMangees() == 16){
            //afichage gagnant
            return true;
        }
        if(this.plateauJeu.getNbrNoiresMangees() == 16){
            //afichage gagnant
            return true;
        }

        return false;
    }

}
