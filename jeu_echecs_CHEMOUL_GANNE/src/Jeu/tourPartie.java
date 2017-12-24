package Jeu;

import java.util.*;

public class tourPartie {
    private int ligne, colonne;

    //Constructeur par défaut
    public tourPartie() {

    }

    //getteur de la ligne
    public int getLigne() {
        return this.ligne;
    }

    //getteur de la colonne
    public int getColonne() {
        return this.colonne;
    }

    //Saisie de la ligne et de la colonne de la pièce qu'il veut bouger
    public void saisieChoix() {
        Scanner sc = new Scanner(System.in);
        boolean isEntier = true;
        int l = 9, c = 9;

        while(l > 8 || l < 0) {
            do { //boucle pour tester si le joueur a bien saisie un nombre
                System.out.print("Veuillez saisir la ligne : ");
                isEntier = true;
                Scanner s = new Scanner(System.in);
                try{
                    l = s.nextInt();
                } catch (InputMismatchException e)
                {
                    System.out.println("La valeur saisie n'est pas un entier");
                    isEntier = false;
                }
            } while (isEntier != true);
        }
        this.ligne = l - 1;

        isEntier = true;
        while(c > 8 || c < 0) {
            do { //boucle pour tester si le joueur a bien saisie un nombre
                System.out.print("Veuillez saisir la colonne : ");
                isEntier = true;
                Scanner s2 = new Scanner(System.in);
                try{
                    c = s2.nextInt();
                } catch (InputMismatchException e)
                {
                    System.out.println("La valeur saisie n'est pas un entier");
                    isEntier = false;
                }
            } while (isEntier != true);
        }

        this.colonne = c - 1;
    }
}
