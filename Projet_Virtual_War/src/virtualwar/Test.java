package virtualwar;

import java.util.Random;

public class Test {

	static Vue vueEnCour;

	public static void main(String[] args) {
		Random r = new Random();
		boolean joueur = r.nextBoolean();
		Joueur joueurEnCour;
		int choix = -1;
		System.out.println("Bienvenue dans cette version prototype de VirtualWar");
		
		//Recupére le nb de robot entré avec crtl saisie [1-5]
		System.out.print("Entrez le nombre de Robot par equipe (compris entre " + 1 + " et " + Constante.ROBOTS_MAX + ") : ");
		int nbRobot = Constante.saisieEntier(1,Constante.ROBOTS_MAX);
		
		//Recupére le nom joueur1 avec crtl saisie
		System.out.print("\nVeuillez entrer le nom du joueur 1 : ");
		String nom1 = Joueur.saisiePseudo();
			
		//Recupére le nom joueur2 avec crtl saisie
		String nom2 = "";
		System.out.print("\nVeuillez entrer le nom du joueur 2 : ");
		do{
		nom2 = Joueur.saisiePseudo();
		if(nom1.equals(nom2)){
			System.out.println("Votre nom ne peut pas être similaire à celui du joueur 1, veuillez rentrez un nom.");
		}
		} while(nom1.equals(nom2));
		
		//Creer les joueurs
		Joueur J1 = new Joueur(nom1,nbRobot,1);
		Joueur J2 = new Joueur(nom2,nbRobot,2);
		
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
		Vue Equipe2 = new Vue(plat,2);
		
		/*
		 * Possibilité de créer une méthode pour la saisie d'en dessous ?
		 */
		
		for(int copt = 1;copt < 3 ; copt++){
			for(int cpt = 1 ;cpt < nbRobot+1 ; cpt++){
				System.out.println("Joueur " + copt + " choisissez le type de votre robot n°" + cpt );
				System.out.println("Entrez T pour un Tireur , C pour un Char ou P pour un Piegeur");
					//Crtl de saisie : "t,T-c,C-p,P" accépté.
					String rep = "" + Constante.saisieTypeRobot();
					//Ajoute les robots
						if(copt == 1){
							if(rep.equals("T")){
								J1.ajouterTireur(Equipe1,0,0);
							}
							if(rep.equals("C")){
								J1.ajouterChar(Equipe1, 0, 0);
							}
							if( rep.equals("P")){
								J1.ajouterPiegeur(Equipe1, 0, 0);
							}
						}
						if(copt == 2){
							if(rep.equals("T")){
								J2.ajouterTireur(Equipe2,0,0);
							}
							if(rep.equals("C")){
								J2.ajouterChar(Equipe2, 0, 0);
							}
							if( rep.equals("P")){
								J2.ajouterPiegeur(Equipe2, 0, 0);
							}
						}
				}
				//Passe une ligne entre les 2 joueurs
				System.out.println("");
			}
				
	
		
	
	
	while(!J1.aPerdu() && !J2.aPerdu()){
		if(joueur){			            	
			joueurEnCour = J1;
			vueEnCour = Equipe1;
		}else{
			joueurEnCour = J2;
			vueEnCour = Equipe2;
		}
		System.out.println("Tour du joueur " + joueurEnCour.toString());
		System.out.println(vueEnCour.toString());
		System.out.println(joueurEnCour.getListeRobot());
		
		choix = joueurEnCour.choixActions();
		
		if(choix == 1){
			joueurEnCour.invoqueRobot(joueurEnCour.choisirInvocation());
		}else{
			if(choix == 2){
				joueurEnCour.choixRobot().deplacement();
			}else{
				if(choix == 3){
					joueurEnCour.choixRobot().attaque();
				}
			}
		}
		plat.getGrille()[1][1].regenEnergieBase();
		plat.getGrille()[plat.getHauteur()][plat.getLargeur()].regenEnergieBase();

		joueur = !joueur;
	}



}
}