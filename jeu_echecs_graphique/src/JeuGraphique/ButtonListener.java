package JeuGraphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import Jeu.*;
import Pieces.*;

public class ButtonListener implements ActionListener{
    private FenetreJeu echec;

    public ButtonListener(FenetreJeu echec){
        this.echec = echec;
    }

    public void actionPerformed(ActionEvent e)
    {
        /*JFrame frame = new JFrame("titre fenetre");
        JDialog d = new JDialog(frame, "Hello", true);
        d.setLocationRelativeTo(frame);
        d.setVisible(true);*/
        Object source = e.getSource();
        if(!echec.isPartieACommencee()){
            if(source == this.echec.getDeuxJoueurs())
            {
                this.echec.setPartieACommencee(true);
                this.echec.getPartie().setIntelligenceArtificielle(false);

            }else if(source == this.echec.getIA()){
                this.echec.setPartieACommencee(true);
                this.echec.getPartie().setIntelligenceArtificielle(true);
            }
        }
        for(int i =0; i<8;i++){
            for(int j=0;j<8;j++){
                if(source == this.echec.getDamier()[i][j]){
                    //affichage en couleurs des cases possbles etc...
                }
            }
        }
    }
}
