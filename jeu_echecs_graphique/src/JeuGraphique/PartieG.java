package JeuGraphique;

public class PartieG {
    private PlateauG plateauJeu;
    private JoueurG j1;
    private JoueurG j2;
    private int joueurActuel; //0 pour blanc, 1 pour noir
    private boolean finie;
    //private LinkedList<TourPartieG> listeTourParties = new LinkedList<TourPartieG>();
    private boolean intelligenceArtificielle; //à 1 si le joueur veut jouer contre l'intelligenec artificielle


    /**
     * Constructeur de la classe
     */
    public PartieG(){
        this.plateauJeu = new PlateauG(8);
        this.j1 = new JoueurG(1);
        this.j2 = new JoueurG(2);
        this.joueurActuel = 0;
    }


    public PlateauG getPlateauJeu() {
        return plateauJeu;
    }

    public int getJoueurActuel() {
        return joueurActuel;
    }

    public void setJoueurActuel(int joueurActuel) {
        this.joueurActuel = joueurActuel;
    }

    public boolean isFinie() {
        return finie;
    }

    public void setFinie(boolean finie) {
        this.finie = finie;
    }

    public boolean isIntelligenceArtificielle() {
        return intelligenceArtificielle;
    }

    public void setIntelligenceArtificielle(boolean intelligenceArtificielle) {
        this.intelligenceArtificielle = intelligenceArtificielle;
    }

    public boolean derniereLigne(int x){
        boolean blanc = (joueurActuel == 0)? true:false;
        if(blanc)
            return x==0;
        else
            return x==7;
    }

    public boolean estFinie(){
        if(this.plateauJeu.isRoiBlancMort()){
            javax.swing.JOptionPane.showMessageDialog(null,"Les Noirs ont gagnés ! Bravo !");
            return true;
        }
        if(this.plateauJeu.isRoiNoirMort()){
            javax.swing.JOptionPane.showMessageDialog(null,"Les Blancs ont gagnés ! Bravo !");
            //afichage gagnant
            return true;
        }
        if(estEnEchecEtMatPartie())
            return true;
        else{
            if(this.getPlateauJeu().estEnEchec(true))
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi BLANC est en ECHEC.");

            if(this.getPlateauJeu().estEnEchec(false))
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi NOIR est en ECHEC.");

        }
        return false;
    }

    public boolean estEnEchecEtMatPartie(){
        if(this.plateauJeu.estEnEchecEtMat(true))
        {
            javax.swing.JOptionPane.showMessageDialog(null,"Le Roi Blanc est en ECHEC ET MAT! Les Noirs ont gagnés ! Bravo !");
            return true;
        }
        if(this.plateauJeu.estEnEchecEtMat(false))
        {
            javax.swing.JOptionPane.showMessageDialog(null,"Le Roi Noir est en ECHEC ET MAT! Les Blancs ont gagnés ! Bravo !");
            return true;
        }
        return false;
    }

    public void tourIA(){
        TourPartieG t = new TourPartieG();
        t.choixIA();

    }

}
