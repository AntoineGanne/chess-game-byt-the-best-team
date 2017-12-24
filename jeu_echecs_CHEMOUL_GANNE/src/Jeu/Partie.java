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
        afficherPartie();
       while(!this.finie) {
           tourPartie t = new tourPartie();
           System.out.println("Saisissez la ligne et la colonne de la pièce que vous voulez deplacer.");
           //verifier que la pièce lui appartient
           t.saisieChoix();
           int x = t.getLigne();
           int y = t.getColonne();
           Piece aBouger = new Piece(this.plateauJeu.getTabCases()[x][y].getPiece());
           this.plateauJeu.getTabCases()[x][y].afficherPossibilites(this.plateauJeu.getTabCases());





           this.listeTourParties.add(t);
           finie = true;
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


}
