Jeu d'Echec
Auteurs : Antoine GANNE, Léa CHEMOUL
Langage : Java


-----------------------------------------
Compatibilités 

JDK 8

-----------------------------------------
Initialisation

Le programme demande un fichier de configuration au lancement de l'application. 
Si toutefois vous souhaitez eviter de chercher à chaque lancement votre configuration vous pouvez :
- Ouvrir le fichier src/PlateauG.java
- Modifier à la ligne 4 de la fonction "demanderEtChargerFichier()" la valeur de "fichier = 'config.txt';"

-----------------------------------------
Architecture

/jeu_echecs_graphique 	/img Contient les images des pièces noires et blanches du plateau
						/src 
						
						
						
-----------------------------------------
Compilation et execution

- Choix 1 : ouvrir un terminal, se placer dans le repertoir du projet /jeu_echecs_graphique, taper la commande "java -jar jeu_echecs_graphique.jar"
- Choix 2 : cliquer sur jeu_echecs_graphique.jar
- Choix 3: compiler les sources dans un IDE.					