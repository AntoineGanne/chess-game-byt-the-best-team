package JeuGraphique;
import javax.swing.*;
import java.awt.*;

import Jeu.*;
import Pieces.*;

public class FenetreJeu extends JFrame{
    //images
    private JLabel titre = new JLabel("Le Super Jeu d'Echecs");
    private JButton[][] damier = new JButton[8][8];
    private Color colorBlack = Color.gray;
    private JPanel echiquier;
    private JPanel menu;
    private JButton IA;
    private JButton deuxJoueurs;

    private PartieGraphique partie;

    public FenetreJeu(){
        super("Le Super Jeu d'Echecs !");
        echiquier = new JPanel();
        menu = new JPanel();
        menu.setLayout(new GridLayout(8,1));
        Container contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        this.setSize(1000,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ButtonHandler buttonHandler = new ButtonHandler();
        echiquier.setLayout(new GridLayout(8,8));
        for(int i = 0; i<8;i++){
            for(int j=0;j<8;j++){
                damier[i][j] = new JButton();

                damier[i][j].setSize(100,100);
                if((i+j)%2 != 0)
                    damier[i][j].setBackground(colorBlack);
                echiquier.add(damier[i][j]);
                //damier[i][j].addActionListener(buttonHandler);
                //initialiser pion image
            }
        }

        deuxJoueurs= new JButton("Jouer à 2");
        IA = new JButton("Jouer contre l'IA");
        deuxJoueurs.setBackground(Color.white);
        IA.setBackground(Color.white);
        //ajouter evenement
        titre.setBounds(100,50,200,25);
        titre.setFont(new Font("Consolas", Font.PLAIN,24));
        menu.add(titre);
        menu.add(this.deuxJoueurs);
        menu.add(this.IA);

        //contenu.setLayout(GridLayout);
        this.menu.setBackground(Color.lightGray);
        contenu.add(menu);
        contenu.add(echiquier);
        this.setContentPane(contenu);

        /*this.demanderConfiguration();
        this.mettreAJourDamier();*/

    }

    public static void main(String[] args){
        FenetreJeu jeu = new FenetreJeu();
        jeu.setVisible(true);
    }

    public String demanderConfiguration(){
        JFileChooser dialogue = new JFileChooser();
        dialogue.showOpenDialog(null);
        return dialogue.getSelectedFile().getAbsolutePath();
    }

    public void mettreAJourDamier(){
        String nom;
        boolean couleur = true;
        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.partie.getPlateauJeu().getTabCases()[i][j].getPiece() !=null){
                    nom = this.partie.getPlateauJeu().getTabCases()[i][j].getPiece().getNom();
                    couleur = this.partie.getPlateauJeu().getTabCases()[i][j].getPiece().isEstBlanc();
                    switch(nom){
                        case "Cavalier" :
                            damier[i][j].setIcon(new ImageIcon("img/cavalierB.png"));
                            break;
                        case "Dame" :
                            damier[i][j].setIcon(new ImageIcon("img/reineB.png"));
                            break;
                        case "Roi" :
                            damier[i][j].setIcon(new ImageIcon("img/roiB.png"));
                            break;
                        case "Pion" :
                            damier[i][j].setIcon(new ImageIcon("img/pionB.png"));
                            break;
                        case "tour" :
                            damier[i][j].setIcon(new ImageIcon("img/tourB.png"));
                            break;
                        case "Fou" :
                            damier[i][j].setIcon(new ImageIcon("img/fouB.png"));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
