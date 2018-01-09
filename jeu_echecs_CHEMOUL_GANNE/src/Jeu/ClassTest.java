package Jeu;

import java.io.Console;

/**
 * Created by antoine on 09/01/2018.
 */
public class ClassTest {
    public static void main(String[] args){
        //Partie partie = new Partie();
        Plateau damier =new Plateau(8);
        damier.demanderEtChargerFichier();
        damier.affichageConsole();
        System.out.println(damier.positionRoi(true));
        System.out.println(damier.estEnEchec(4,4,true));
        System.out.println(damier.estEnEchecEtMat(true));

    }
}
