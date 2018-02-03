package JeuGraphique;
import Listener.ButtonListener;
import Listener.CaseListener;
import com.sun.javafx.geom.Vec2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FenetreJeu extends JFrame{
    //images
    private JLabel titre = new JLabel("Le Super Jeu d'Echecs");
    private JButton[][] damier = new JButton[8][8];
    private Color white = new Color(250, 232, 218);
    private Color black = new  Color(180, 145, 94) ;
    private Color rouge = new Color(255, 146, 146);
    private JPanel echiquier;
    private JPanel menu;
    private JButton IA;
    private JButton deuxJoueurs;
    private JButton recommencer;
    private boolean partieACommencee;
    private PartieG partie;

    /**
     * Constructeur de la classe.
     */
    public FenetreJeu(){
        super("Le Super Jeu d'Echecs !");
        this.echiquier = new JPanel();
        this.menu = new JPanel();
        this.setSize(1000,700);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Boutons
        deuxJoueurs= new JButton("Jouer à 2 joueurs");
        IA = new JButton("Jouer contre l'IA");
        recommencer = new JButton("Recommencer");

        this.initialiserJeu();
    }

    /**
     * Initialise tous les composants de notre fenetre.
     */
    public void initialiserJeu(){
        this.partieACommencee = false;
        menu.setLayout(new GridLayout(8,1));
        ActionListener caseListener = new CaseListener(this);
        ActionListener buttonListener = new ButtonListener(this);


        //BOUTONS
        deuxJoueurs.addActionListener(buttonListener);
        IA.addActionListener(buttonListener);
        recommencer.addActionListener(buttonListener);
        recommencer.setBackground(Color.white);
        deuxJoueurs.setBackground(Color.white);
        IA.setBackground(Color.white);

        //ECHIQUIER
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

        //TITRE
        titre.setBounds(100,50,200,25);
        titre.setFont(new Font("Consolas", Font.PLAIN,24));

        //AJOUT AU PANEL
        this.menu.add(titre);
        this.menu.add(this.deuxJoueurs);
        this.menu.add(this.IA);
        this.menu.add(this.recommencer);
        this.menu.setBackground(new Color(250, 232, 218));

        Container contenu = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        contenu.add(menu);
        contenu.add(echiquier);
        this.setContentPane(contenu);

        //PARTIE
        this.nouvellePartie();

    }


    /**
     * Met a jour le damier en affichant l'image correspondant a la Piece de chaque case.
     */
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

    /**
     * Met en rouge le roi d'une couelur donnee selon si il est ene echec ou non.
     * @param blanc couleur du joueur
     */
    public void mettreRoiRouge(boolean blanc){//si il est en echec
        Vec2d posRoi = this.getPartie().getPlateauJeu().positionRoi(blanc);
        if(posRoi != null && this.getPartie().getPlateauJeu().estEnEchec(blanc))
            damier[(int)posRoi.x][(int)posRoi.y].setBackground(this.rouge);
    }

    /**
     * Enleve toutes les couleurs de notre damier.
     */
    public void enleverCouleur(){
        for(int i = 0; i<8;i++){
            for(int j=0;j<8;j++){
                if((i+j)%2 != 0)
                    damier[i][j].setBackground(black);
                else
                    damier[i][j].setBackground(white);
                damier[i][j].setText("");
            }
        }
    }

    /**
     * Initialise une nouvelle partie.
     */
    public void nouvellePartie(){
        this.partie = new PartieG();
        this.mettreAJourDamier();
        //Partie utile si on charge une configuaration
        this.mettreRoiRouge(true);
        this.mettreRoiRouge(false);
    }

    /**
     * Affiche les mots "Roque" sur le plateau lorsque le roi peut faire un roque.
     * @param blanc couleur du joueur
     * @param ligne ligne du Roi
     */
    public void afficherRoquePossible(boolean blanc, int ligne){
        if(this.getPartie().getPlateauJeu().petitRoquePossible(blanc)){
                damier[ligne][6].setBackground(Color.GREEN);
                damier[ligne][6].setText("Roque");
        }
        if(this.getPartie().getPlateauJeu().grandRoquePossible(blanc)){
            damier[ligne][2].setBackground(Color.GREEN);
            damier[ligne][2].setText("Roque");
        }
    }

    /**
     * Renvoie le damier de jeu.
     * @return boolean
     */
    public JButton[][] getDamier() {
        return damier;
    }

    /**
     * Retourne le bouton qui permet de mettre a jour si on joue contre l'IA.
     * @return boolean
     */
    public JButton getIA() {
        return IA;
    }

    /**
     * Retourne le bouton qui permet de mettre a jour si on joue contre un autre joueur.
     * @return boolean
     */
    public JButton getDeuxJoueurs() {
        return deuxJoueurs;
    }

    /**
     * Retourne l'etat de l'attribut partieACommence
     * @return boolean
     */
    public boolean isPartieACommencee() {
        return partieACommencee;
    }

    /**
     *
     * @param partieACommencee boolean
     */
    public void setPartieACommencee(boolean partieACommencee) {
        this.partieACommencee = partieACommencee;
    }

    /**
     *
     * @return PartieG
     */
    public PartieG getPartie() {
        return partie;
    }

    /**
     *
     * @return JButton
     */
    public JButton getRecommencer() { return recommencer; }

    /**
     * Methode principale
     * @param args String[]
     */
    public static void main(String[] args){
        FenetreJeu jeu = new FenetreJeu();
        jeu.setVisible(true);
    }

}
