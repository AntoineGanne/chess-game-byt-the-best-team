package Listener;

import java.awt.event.*;

import JeuGraphique.FenetreJeu;

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
