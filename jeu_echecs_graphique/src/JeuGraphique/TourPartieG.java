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

    /**
     * Constructeur avec parametres.
     * @param l ligne
     * @param c colonne
     * @param lf ligne finale
     * @param lc colonne finale
     */
    public TourPartieG(int l,int c,int lf,int lc) {
        this.ligne = l;
        this.colonne = c;
        this.ligneDeplacFinal = lf;
        this.colonneDeplacFinal = lc;
    }

    /**
     * getteur de la ligne
     * @return int
     */
    public int getLigne() {
        return this.ligne;
    }

    /**
     * getteur de la colonne
     * @return int
     */
    public int getColonne() {
        return this.colonne;
    }

    /**
     * mutateur de la ligne
     * @param ligne int
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * mutateur de la colonne
     * @param colonne int
     */
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    /**
     * Met à jour la ligne du deplacement choisi
     * @param ligneDeplacFinal int
     */
    public void setLigneDeplacFinal(int ligneDeplacFinal) {
        this.ligneDeplacFinal = ligneDeplacFinal;
    }

    /**
     * Met à jour la colonne du deplacement choisi
     * @param colonneDeplacFinal int
     */
    public void setColonneDeplacFinal(int colonneDeplacFinal) {
        this.colonneDeplacFinal = colonneDeplacFinal;
    }

    /**
     * Gère le choix aleatoire de l'IA d'une ligne et d'une colonne
     */
    public void choixIA(){
        Random rd = new Random();
        int l=rd.nextInt(8);
        int c=rd.nextInt(8);
        this.ligne = l;
        this.colonne = c;
    }
}
