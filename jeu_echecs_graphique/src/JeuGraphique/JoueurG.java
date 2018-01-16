package JeuGraphique;

import javax.swing.*;

public class JoueurG {
    private String pseudo;
    private int partiesGagnees;
    private boolean aRoque=false;

    /**
     * Constructeur de la classe
     * @param pseudo nom du joueur
     */
    public JoueurG(String pseudo) {
        this.pseudo = pseudo;
        this.partiesGagnees = 0;
    }

    public JoueurG(int numero){
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Veuillez décliner l'identité du joueur "+numero,"Pseudo Joueur", JOptionPane.QUESTION_MESSAGE);
        this.pseudo = nom;
    }

    public void setPartiesGagnées(int partiesGagnees) {
        this.partiesGagnees = partiesGagnees;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
