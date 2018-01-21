package JeuGraphique;

import java.util.*;

public class TourPartieG {
    private int ligne, colonne; //du choix de la case ou se trouve la pièce que l'on souhaite deplacer
    private int ligneDeplacFinal, colonneDeplacFinal;
    //permettent de stocker le déplacement effectué (pour d'eventuelles statistiques)

    /**
     * Constructeur par défaut
     */
    public TourPartieG() {
        this.ligne = -1;
        this.colonne = -1;
        this.ligneDeplacFinal = -1;
        this.colonneDeplacFinal = -1;
    }

    public TourPartieG(int l,int c,int lf,int lc) {
        this.ligne = l;
        this.colonne = c;
        this.ligneDeplacFinal = lf;
        this.colonneDeplacFinal = lc;
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

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
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

    public void choixIA(){
        Random rd = new Random();
        int l=rd.nextInt(8);
        int c=rd.nextInt(8);
        this.ligne = l;
        this.colonne = c;
    }
}
