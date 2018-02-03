package Listener;

import java.awt.event.*;

import JeuGraphique.FenetreJeu;
import JeuGraphique.JoueurG;

public class ButtonListener implements ActionListener{
    private FenetreJeu echec;

    public ButtonListener(FenetreJeu echec){
        this.echec = echec;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(source == this.echec.getDeuxJoueurs())
        {
            this.echec.setPartieACommencee(true);
            this.echec.getPartie().setFinie(false);
            this.echec.getPartie().setIntelligenceArtificielle(false);
            this.echec.getPartie().setJ1(new JoueurG(1));
            this.echec.getPartie().setJ2(new JoueurG(2));
        }else if(source == this.echec.getIA()){
            this.echec.setPartieACommencee(true);
            this.echec.getPartie().setFinie(false);
            this.echec.getPartie().setIntelligenceArtificielle(true);
        }
        else if(source == this.echec.getRecommencer()){
            this.echec.getPartie().setFinie(false);
            this.echec.setPartieACommencee(false);
            this.echec.nouvellePartie();
            this.echec.enleverCouleur();
        }
    }
}
