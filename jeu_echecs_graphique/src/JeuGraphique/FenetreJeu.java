package JeuGraphique;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FenetreJeu extends JFrame{
    //images
    private JLabel titre = new JLabel("Le Super Jeu d'Echecs");
    private JButton[][] damier = new JButton[8][8];
    private Color white = new Color(255,219,168);
    private Color black = new  Color(180, 145, 94) ;
    private JPanel echiquier;
    private JPanel menu;
    private JButton IA;
    private JButton deuxJoueurs;
    private JButton recommencer;
    private boolean partieACommencee;
    private PartieG partie;
    private boolean IAactive;

    public FenetreJeu(){
        super("Le Super Jeu d'Echecs !");
        this.partieACommencee = false;
        this.echiquier = new JPanel();
        menu = new JPanel();
        menu.setLayout(new GridLayout(8,1));
        Container contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        this.setSize(1000,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        ActionListener caseListener = new CaseListener(this);
        ActionListener buttonListener = new ButtonListener(this);
        echiquier.setLayout(new GridLayout(8,8));
        echiquier.setBorder(BorderFactory.createLineBorder(new Color(135, 73, 28),15));

        for(int i = 0; i<8;i++){
            for(int j=0;j<8;j++){
                damier[i][j] = new JButton();

                damier[i][j].setSize(100,100);
                if((i+j)%2 != 0)
                    damier[i][j].setBackground(black);
                else
                    damier[i][j].setBackground(white);
                echiquier.add(damier[i][j]);
                damier[i][j].addActionListener(caseListener);
                //initialiser pion image
            }
        }

        deuxJoueurs= new JButton("Jouer à 2");
        deuxJoueurs.addActionListener(buttonListener);
        IA = new JButton("Jouer contre l'IA");
        IA.addActionListener(buttonListener);
        recommencer = new JButton("Recommencer");
        recommencer.setBackground(Color.white);
        recommencer.addActionListener(buttonListener);
        deuxJoueurs.setBackground(Color.white);
        IA.setBackground(Color.white);
        //ajouter evenement
        titre.setBounds(100,50,200,25);
        titre.setFont(new Font("Consolas", Font.PLAIN,24));
        menu.add(titre);
        menu.add(this.deuxJoueurs);
        menu.add(this.IA);
        menu.add(this.recommencer);

        this.menu.setBackground(Color.lightGray);
        contenu.add(menu);
        contenu.add(echiquier);
        this.setContentPane(contenu);


        this.nouvellePartie();

    }

    public static void main(String[] args){
        FenetreJeu jeu = new FenetreJeu();
        jeu.setVisible(true);
    }

    public boolean isIAactive() {
        return IAactive;
    }

    public void setIAactive(boolean IAactive) {
        this.IAactive = IAactive;
    }

    public void mettreAJourDamier(){
        String nom;
        String couleur;

        for(int i =0;i<8;i++){
            for(int j=0;j<8;j++){
                if(this.partie.getPlateauJeu().getTabCases()[i][j].getPiece() !=null){
                    nom = this.partie.getPlateauJeu().getTabCases()[i][j].getPiece().getNom();
                    couleur = (this.partie.getPlateauJeu().getTabCases()[i][j].getPiece().isEstBlanc())? "B":"N";
                    String image="img/"+nom+couleur+".png";
                    damier[i][j].setIcon(new ImageIcon(image));
                }else{
                    damier[i][j].setIcon(null);
                }
            }
        }
    }


    public void enleverCouleur(){
        for(int i = 0; i<8;i++){
            for(int j=0;j<8;j++){
                if((i+j)%2 != 0)
                    damier[i][j].setBackground(black);
                else
                    damier[i][j].setBackground(white);
            }
        }
    }

    public void nouvellePartie(){
        this.partie = new PartieG();
        this.mettreAJourDamier();
    }

    public JButton[][] getDamier() {
        return damier;
    }

    public JButton getIA() {
        return IA;
    }

    public JButton getDeuxJoueurs() {
        return deuxJoueurs;
    }

    public boolean isPartieACommencee() {
        return partieACommencee;
    }

    public void setPartieACommencee(boolean partieACommencee) {
        this.partieACommencee = partieACommencee;
    }

    public PartieG getPartie() {
        return partie;
    }

    public JButton getRecommencer() { return recommencer; }
}
