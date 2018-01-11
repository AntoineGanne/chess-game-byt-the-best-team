package Jeu;

import java.util.*;

public class tourPartie {
    private int ligne, colonne; //du choix de la case ou se trouve la pièce que l'on souhaite deplacer
    private int ligneDeplacFinal, colonneDeplacFinal;
    //permettent de stocker le déplacement effectué (pour d'eventuelles statistiques)

    /**
     * Constructeur par défaut
     */
    public tourPartie() {
        this.ligne = -1;
        this.colonne = -1;
        this.ligneDeplacFinal = -1;
        this.colonneDeplacFinal = -1;
    }

    /**
     * getteur de la ligne
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * getteur de la colonne
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * Met à jour la ligne du déplacement choisi
     */
    public void setLigneDeplacFinal(int ligneDeplacFinal) {
        this.ligneDeplacFinal = ligneDeplacFinal;
    }

    /**
     * Met à jour la colonne du déplacement choisi
     */
    public void setColonneDeplacFinal(int colonneDeplacFinal) {
        this.colonneDeplacFinal = colonneDeplacFinal;
    }

    //Saisie de la ligne et de la colonne de la pièce qu'il veut bouger
    public void saisieChoix() {
        Scanner sc = new Scanner(System.in);
        boolean isEntier = true;
        int l = 9, c = 9;

        while(l > 8 || l < 0) {
            do { //boucle pour tester si le joueur a bien saisie un nombre
                System.out.print("Veuillez saisir le numéro de la ligne : ");
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
        this.ligne = 8 - l; //sur le palteau les lignes sont numerotées de 8 a 1 et non 0 a 7

        boolean isLettre = true;
        String lettre = "";
        while(c > 8 || c < 0) {
            do { //boucle pour tester si le joueur a bien saisie un nombre
                System.out.print("Veuillez saisir la colonne : ");
                isLettre = true;
                Scanner s2 = new Scanner(System.in);
                try{
                    lettre = s2.nextLine();
                } catch (InputMismatchException e)
                {
                    System.out.println("La valeur saisie n'est pas une lettre");
                    isLettre = false;
                }
            } while (isLettre != true);
            c = lettre.toLowerCase().toCharArray()[0] - 'a' +1;
        }

        this.colonne = c - 1;
    }

    public void choixIA(){
        int l = (int)(Math.random()*7 + 1);
        int c = (int)(Math.random()*7 + 1);
        this.ligne = l;
        this.colonne = c;
    }
}
