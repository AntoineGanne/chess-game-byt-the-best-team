package JeuGraphique;

import javax.swing.*;

public class JoueurG{
    private String pseudo;

    /**
     * Constructeur de la classe
     * @param pseudo nom du joueur
     */
    public JoueurG(String pseudo) {
        this.pseudo = pseudo;
    }

    public JoueurG(int numero){
        JOptionPane jop = new JOptionPane();
        String nom = jop.showInputDialog(null, "Veuillez décliner l'identité du joueur "+numero,"Pseudo Joueur", JOptionPane.QUESTION_MESSAGE);
        this.pseudo = nom;
    }
}
