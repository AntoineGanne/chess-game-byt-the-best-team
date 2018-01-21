package JeuGraphique;

import Pieces.Piece;

import java.util.LinkedList;
import java.util.Random;

public class PartieG {
    private PlateauG plateauJeu;
    private JoueurG j1;
    private JoueurG j2;
    private int joueurActuel; //0 pour blanc, 1 pour noir
    private boolean finie;
    private LinkedList<TourPartieG> listeTourParties = new LinkedList<TourPartieG>();
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

    public LinkedList<TourPartieG> getListeTourParties() {
        return listeTourParties;
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
        if(this.plateauJeu.getCompteurToursSansPrises()==50){
            javax.swing.JOptionPane.showMessageDialog(null,"Partie nulle : 50 tours sans prise ont été joués");
            return true;
        }
        if(estEnEchecEtMatPartie())
            return true;
        else{
            if(this.getPlateauJeu().estEnEchec(true))
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi BLANC est en ECHEC.");
            if(this.getPlateauJeu().estEnEchec(false))
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi NOIR est en ECHEC.");
            if(estEnPat(true)){
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi BLANC est en PAT. Partie finie.");
                return true;
            }
            if(estEnPat(false)){
                javax.swing.JOptionPane.showMessageDialog(null,"Le Roi NOIR est en PAT. Partie finie.");
                return true;
            }

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

    public boolean estEnPat(boolean blanc){
        for(int i =0;i<this.plateauJeu.getTAILLE();i++){
            for(int j=0;j<this.plateauJeu.getTAILLE();j++){
                if(this.plateauJeu.getTabCases()[i][j].getPiece() != null ){
                    boolean couleurBlanc = this.plateauJeu.getTabCases()[i][j].getPiece().isEstBlanc();
                    boolean memeCouleur = (couleurBlanc && blanc) || (!couleurBlanc && !blanc);
                    if(memeCouleur && !this.plateauJeu.getTabCases()[i][j].tousDeplacementsMiseEnEchec(this.plateauJeu))
                        return false;
                }
            }
        }
        return true;
    }

    public void tourIA(int i, int j){
        this.setJoueurActuel(1);//necessaire pour traitement promotion
        //traitement du tour de l'IA
        TourPartieG t = new TourPartieG();
        LinkedList<CaseG> possibilites;
        CaseG[][] tabTemp = this.getPlateauJeu().getTabCases();
        Piece piece;
        int x,y;
        do{
            do{
                t.choixIA();
                x = t.getLigne();
                y = t.getColonne();
            }//la pièce choisie appartient bien au joueur et n'est pas null
            while ((tabTemp[x][y].getPiece()== null) || tabTemp[x][y].getPiece().isEstBlanc() || tabTemp[x][y].tousDeplacementsMiseEnEchec(this.plateauJeu));
            possibilites = tabTemp[x][y].getPiece().afficherPossibilitees(x,y,this.plateauJeu);
        }
        while(possibilites.size() == 0);
        //on choisi bien une pièce qui peut se déplacer (par exemple le roi s'il n'et pas encerclé)

        //CHOIX DU DEPLACEMENT PARMI LES POSSIBILITES
        int a ;
        Random rnd = new Random();
        int xFinal,yFinal;
        //Si le Roi de l'adversaire appartient aux déplacements on le mange
        do {//On choisie une nouvelle possibilité de déplacement tant que celle-ci met le Roi en echec
            a = rnd.nextInt(possibilites.size()) + 1;
            xFinal = possibilites.get(a-1).getX(); // a-1 car le joueur saisi entre [1,8] et non [0,7]
            yFinal = possibilites.get(a-1).getY();
        }while(this.plateauJeu.simulationDeplacement(tabTemp[x][y],xFinal,yFinal,tabTemp[x][y].getPiece().isEstBlanc()));

        //GESTION DU DEPLACEMENT
        String nom = this.plateauJeu.getTabCases()[x][y].getPiece().getNom();
        t.setLigneDeplacFinal(xFinal); //on rajoute ces informations de deplacement au TourPartie
        t.setColonneDeplacFinal(yFinal);
        this.plateauJeu.deplacerPiecePlateau(this.plateauJeu.getTabCases()[x][y],xFinal, yFinal);
        this.listeTourParties.add(t);

        //---------------Promotion
        if(derniereLigne(xFinal)
                && this.plateauJeu.getTabCases()[xFinal][yFinal].getPiece().getNom().contains("Pion")){
            this.plateauJeu.pionPromotion(xFinal,yFinal,true);
        }

        //---------------Prise en passant
        if(this.plateauJeu.priseEnPassant(i,j,true,false)) //C'est le joueur qui décide de prendre en passant le pion de l'IA
            this.setJoueurActuel((this.getJoueurActuel()==1)? 0:1);
    }

}
