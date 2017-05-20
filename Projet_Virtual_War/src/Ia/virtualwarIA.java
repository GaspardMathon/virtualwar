package Ia;

import java.util.Random;

import virtualwar.*;

public class virtualwarIA {
	static Vue vueEnCour;

	public static void main(String[] args) {
		Random r = new Random();
		boolean joueur = r.nextBoolean();
		int choix = -1;
		Constante.titreAleatoire();

		//Recupére le nb de robot entré avec crtl saisie [1-5]
		System.out.print("Entrez le nombre de Robot par equipe (compris entre " + 1 + " et " + Constante.ROBOTS_MAX + ") : ");
		int nbRobot = Constante.saisieEntier(1,Constante.ROBOTS_MAX);

		//Recupére le nom joueur1 avec crtl saisie
		System.out.print("\nVeuillez entrer votre nom : ");
		String nom1 = Joueur.saisiePseudo();



		//Creer les joueurs
		Joueur J1 = new Joueur(nom1,nbRobot,1);
		IntelligenceArtificielle ia = new IntelligenceArtificielle("BOT Steeve", nbRobot,2);

		System.out.println("\nEntrez la taille de la carte:");
		//Recupére la hauteur avec ctrl saisie [3-20]
		System.out.println("Hauteur (min " +Constante.HAUTEUR_MIN +", max " + Constante.HAUTEUR_MAX+ " ) :");
		int hauteur = Constante.saisieEntier(Constante.HAUTEUR_MIN,Constante.HAUTEUR_MAX);
		//Recupére la hauteur avec ctrl saisie [3-20]
		System.out.println("Largeur (min "+ Constante.LARGEUR_MIN+", max "+Constante.LARGEUR_MAX+") :");
		int largeur = Constante.saisieEntier(Constante.LARGEUR_MIN,Constante.LARGEUR_MAX);

		//Creation carte
		Plateau plat = new Plateau(hauteur,largeur);

		int pourcentageObstacle;
		System.out.print("\nChoississez un pourcentage d'obstacle : ");
		pourcentageObstacle = Constante.saisieEntier(Constante.POURCENTAGE_OBSTACLE_MIN,Constante.POURCENTAGE_OBSTACLE_MAX);
		plat.setObstacles(pourcentageObstacle);

		Vue Equipe1 = new Vue(plat,1);
		Vue VueIA = new Vue(plat,2);

		/*
		 * Possibilité de créer une méthode pour la saisie d'en dessous ?
		 */

		for(int cpt = 1 ;cpt < nbRobot+1 ; cpt++){
			System.out.println("\nJoueur " + 1 + " choisissez le type de votre robot n°" + cpt );
			System.out.println("Entrez T pour un Tireur , C pour un Char, P pour un Piegeur, S pour un Soigneur ou F pour un Furtif");
			//Crtl de saisie : "t,T-c,C-p,P" accépté.
			String rep = "" + Constante.saisieTypeRobot();
			//Ajoute les robot
			if(rep.equals("T")){
				J1.ajouterTireur(Equipe1,0,0);
			}
			if(rep.equals("S")){
				J1.ajouterSoigneur(Equipe1,0,0);
			}
			if(rep.equals("F")){
				J1.ajouterFurtif(Equipe1,0,0);
			}
			if(rep.equals("C")){
				J1.ajouterChar(Equipe1, 0, 0);
			}
			if( rep.equals("P")){
				J1.ajouterPiegeur(Equipe1, 0, 0);
			}
		}		
		ia.choixEquipe(VueIA);


		//Passe une ligne entre les 2 joueurs
		System.out.println("");






		while(!J1.aPerdu() && !ia.aPerdu()){
			if(joueur){
				System.out.println("Tour du joueur " + J1.toString());
				System.out.println(Equipe1.toString());
				System.out.println(J1.getListeRobot() + "\n");

				choix = J1.choixActions();

				if(choix == 1){
					J1.invoqueRobot(J1.choisirInvocation());
				}else{
					if(choix == 2){
						J1.choixRobot().deplacement();
					}else{
						if(choix == 3){
							J1.choixRobot().attaque();
						}
					}
				}
			}else{
				System.out.println("Tour du joueur " + ia.toString());
				
				choix = ia.choixAction();
				if(choix == 1){
					ia.invoqueRobot(ia.choisirInvocation());
					System.out.println("L'ia choisi d'invoquer un robot");

				}else{
					if(choix == 2){
						ia.choixRobot().deplacement();
						System.out.println("L'ia choisi de deplacer un robot");

					}else{
						if(choix == 3){
							ia.choixRobot().attaque();
							System.out.println("L'ia choisi d'attaquer");

						}
					}
				}
				
			}
				plat.getGrille()[1][1].regenEnergieBase();
				plat.getGrille()[plat.getHauteur()][plat.getLargeur()].regenEnergieBase();

				joueur = !joueur;
			}



		}
	}

