package Listener;

import JeuGraphique.CaseG;
import JeuGraphique.FenetreJeu;
import JeuGraphique.TourPartieG;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CaseListener implements ActionListener {
    private FenetreJeu echec;
    private CaseG caseADeplac;
    private LinkedList<CaseG> poss;
    private boolean peutJouer=false;

    public CaseListener(FenetreJeu echec){
        this.echec = echec;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if(this.echec.isPartieACommencee()){
            if(!this.echec.getPartie().isFinie()){
                if(!this.peutJouer){
                    this.echec.enleverCouleur();
                }
                for(int i =0; i<8;i++){
                    for(int j=0;j<8;j++){
                        if(source == this.echec.getDamier()[i][j]){
                            if(this.peutJouer){
                                if(caseADeplac.appartientAPossibilites(poss,i,j)){
                                    this.echec.getPartie().getPlateauJeu().deplacerPiecePlateau(caseADeplac,i,j);
                                    this.echec.mettreAJourDamier();
                                    this.echec.enleverCouleur();
                                    this.traitements(i,j);
                                }
                            }else{
                                boolean couleur = this.echec.getPartie().getPlateauJeu().getTabCases()[i][j].getPiece().isEstBlanc();
                                if((couleur && this.echec.getPartie().getJoueurActuel()==0) || (!couleur && this.echec.getPartie().getJoueurActuel()==1))
                                {
                                    CaseG caseg = this.echec.getPartie().getPlateauJeu().getTabCases()[i][j];
                                    if(!caseg.estVide()){
                                        poss = caseg.getPiece().afficherPossibilitees(i,j,this.echec.getPartie().getPlateauJeu().getTabCases());
                                        if(poss.size()!=0){
                                            for(int k=0;k<poss.size();k++){
                                                this.echec.getDamier()[poss.get(k).getX()][poss.get(k).getY()].setBackground(Color.green);
                                            }
                                            caseADeplac = caseg;
                                            this.peutJouer= true;
                                        }
                                    }
                                }else{
                                    javax.swing.JOptionPane.showMessageDialog(null,"Ce n'est pas votre pion.");
                                }
                            }
                        }
                    }
                }
            }
        }else{
            javax.swing.JOptionPane.showMessageDialog(null,"Vous n'avez pas encore selectionné si vous vouliez jouer contre l'IA ou non.");
        }
    }

    public void traitements(int i,int j){
        //Tout les traitements

        //Promotion pion
        if(this.echec.getPartie().derniereLigne(i)
                && this.echec.getPartie().getPlateauJeu().getTabCases()[i][j].getPiece().getNom().contains("Pion")){
            this.echec.getPartie().getPlateauJeu().pionPromotion(i,j,false);
            this.echec.mettreAJourDamier();
        }
        this.peutJouer = false;

        if(this.echec.isIAactive()){
            this.echec.getPartie().setJoueurActuel(1);//necessaire pour traitement promotion
            //traitement du tour de l'IA



            //---------------Promotion
            if(this.echec.getPartie().derniereLigne(i)
                    && this.echec.getPartie().getPlateauJeu().getTabCases()[i][j].getPiece().getNom().contains("Pion")){
                this.echec.getPartie().getPlateauJeu().pionPromotion(i,j,true);
                this.echec.mettreAJourDamier();
            }
            //---------------
            this.echec.getPartie().setJoueurActuel(0);

        }else{
            this.echec.getPartie().setJoueurActuel((this.echec.getPartie().getJoueurActuel()==1)? 0:1);
        }
        //Fin de partie
        if(this.echec.getPartie().estFinie() || this.echec.getPartie().estEnEchecEtMatPartie()){
            this.echec.getPartie().setFinie(true);
            this.echec.setPartieACommencee(false);
        }
    }
}
