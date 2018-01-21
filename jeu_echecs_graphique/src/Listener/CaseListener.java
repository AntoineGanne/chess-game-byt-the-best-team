package Listener;

import JeuGraphique.CaseG;
import JeuGraphique.FenetreJeu;
import JeuGraphique.PlateauG;
import JeuGraphique.TourPartieG;
import com.sun.javafx.geom.Vec2d;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import Pieces.*;

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
        PlateauG plateauTemp = this.echec.getPartie().getPlateauJeu();
        Object source = e.getSource();
        if(this.echec.isPartieACommencee()){
            if(!this.echec.getPartie().estFinie()){
                if(!this.peutJouer){
                    this.echec.enleverCouleur(); //Enlève les couleurs du tour précendent
                }
                this.echec.mettreRoiRouge(true); //Met en rouge la case du Roi blanc si il est en echec
                this.echec.mettreRoiRouge(false); //Respectivement du Roi noir.
                for(int i =0; i<8;i++){ //On parcours ici le damier de JButton
                    for(int j=0;j<8;j++){
                        if(source == this.echec.getDamier()[i][j]){
                            if(this.peutJouer){ //Si on a déjà selectionné la case a déplacer
                                int a = caseADeplac.getX();
                                int b = caseADeplac.getY();
                                boolean blanc = caseADeplac.getPiece().isEstBlanc();
                                if(caseADeplac.appartientAPossibilites(poss,i,j,plateauTemp)){
                                    //Si la case choisie comme déplacement appartient aux possibilités
                                    if(!plateauTemp.simulationDeplacement(caseADeplac,i,j,blanc)){
                                        plateauTemp.deplacerPiecePlateau(caseADeplac,i,j);
                                        this.echec.mettreAJourDamier();
                                        this.echec.enleverCouleur();
                                        this.traitements(i,j);
                                    }
                                    else{
                                        javax.swing.JOptionPane.showMessageDialog(null,"Si vous déplacez cette pièce vous laissez ou mettez votre Roi en echec.");
                                        this.peutJouer = false;
                                        caseADeplac = null;
                                    }
                                }else if(this.echec.getDamier()[i][j].getText().equals("Roque")){//Cas particulier car le Roque n'appartient pas aux possibilités
                                    plateauTemp.effectuerRoque((j==2),blanc);
                                    this.echec.getDamier()[(blanc)?7:0][j].setText("");//On enlève le mot Roque
                                }
                            }else{
                                if(plateauTemp.getTabCases()[i][j].getPiece()!=null){
                                    boolean couleur = plateauTemp.getTabCases()[i][j].getPiece().isEstBlanc();
                                    if((couleur && this.echec.getPartie().getJoueurActuel()==0) || (!couleur && this.echec.getPartie().getJoueurActuel()==1))
                                    {
                                        CaseG caseg;
                                        caseg = plateauTemp.getTabCases()[i][j];
                                        poss = caseg.getPiece().afficherPossibilitees(i,j,plateauTemp);
                                        if(caseg.tousDeplacementsMiseEnEchec(plateauTemp)){
                                            javax.swing.JOptionPane.showMessageDialog(null,"Si vous bougez cette pièce votre Roi reste ou sera mis en danger.");
                                            this.peutJouer = false;
                                            caseg = null;
                                        }else if(!caseg.estVide()){
                                            if(poss.size()!=0){
                                                for(int k=0;k<poss.size();k++){
                                                    this.echec.getDamier()[poss.get(k).getX()][poss.get(k).getY()].setBackground(Color.green);
                                                }
                                                CaseG caseTemp = plateauTemp.getTabCases()[i][j];
                                                if(caseTemp.getPiece().getNom().equals("Roi")){
                                                    this.echec.afficherRoquePossible(caseTemp.getPiece().isEstBlanc(),i);
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
            }
        }else{
            javax.swing.JOptionPane.showMessageDialog(null,"Pas de partie en cours. Selectionnez si vous souhaitez jouer contre l'IA ou non.");
        }
    }

    public void traitements(int i,int j){
        PlateauG plateauTemp = this.echec.getPartie().getPlateauJeu();
        //TRAITEMENTS
        //Promotion pion
        if(this.echec.getPartie().derniereLigne(i)
                && plateauTemp.getTabCases()[i][j].getPiece().getNom().contains("Pion")){
            plateauTemp.pionPromotion(i,j,false);
        }
        //Pion en passant
        if(plateauTemp.priseEnPassant(i,j,(this.echec.getPartie().getJoueurActuel()==1)? false:true))
            this.echec.getPartie().setJoueurActuel((this.echec.getPartie().getJoueurActuel()==1)? 0:1);

        //On ajoute le tour effectué dans la liste de tours joués de la partie en cours
        TourPartieG tour = new TourPartieG(caseADeplac.getX(),caseADeplac.getY(),i,j);
        this.echec.getPartie().getListeTourParties().add(tour);

        //Test de fin de partie
        if(this.echec.getPartie().estFinie() || this.echec.getPartie().estEnEchecEtMatPartie()){
            this.echec.getPartie().setFinie(true);
            this.echec.setPartieACommencee(false);
        }
        this.echec.mettreAJourDamier();
        this.echec.mettreRoiRouge(true);
        this.echec.mettreRoiRouge(false);
        this.peutJouer = false;

        //Tour de l'IA
        if(this.echec.getPartie().isIntelligenceArtificielle()){
            this.echec.getPartie().setJoueurActuel(1);
            this.echec.getPartie().tourIA(i,j);
            this.echec.mettreAJourDamier();
            this.echec.getPartie().setJoueurActuel(0);
            //Test de fin de partie
            if(this.echec.getPartie().estFinie() || this.echec.getPartie().estEnEchecEtMatPartie()){
                this.echec.getPartie().setFinie(true);
                this.echec.setPartieACommencee(false);
            }
        }else{
            this.echec.getPartie().setJoueurActuel((this.echec.getPartie().getJoueurActuel()==1)? 0:1);
        }
    }
}

