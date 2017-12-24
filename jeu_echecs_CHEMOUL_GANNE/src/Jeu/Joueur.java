package Jeu;

public class Joueur {
    private String pseudo;
    private int partiesGagnees;

    /**
     * Constructeur de la classe
     * @param pseudo nom du joueur
     */
    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.partiesGagnees = 0;
    }

    public void setPartiesGagnées(int partiesGagnees) {
        this.partiesGagnees = partiesGagnees;
    }

    public String getPseudo() {
        return pseudo;
    }
}
