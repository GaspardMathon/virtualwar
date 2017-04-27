package virtualwar;

import java.util.Random;
import java.util.Scanner;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Random r = new Random();
		boolean joueur = r.nextBoolean();
		Joueur joueurEnCour;
		int choix = -1;
		
		
		System.out.println("Bienvenue dans cette version prototype de VirtualWar");
		System.out.print("Entrez le nombre de Robot par equipe : ");
		int nbRobot = sc.nextInt(5);
		System.out.print("\nVeuillez entrer le nom du joueur 1 : ");
		String nom1 = sc.next();
		Joueur J1 = new Joueur(nom1,nbRobot,1);
		System.out.print("\nVeuillez entrer le nom du joueur 2 : ");
		String nom2 = sc.next();
		Joueur J2 = new Joueur(nom2,nbRobot,2);
		System.out.println("Entrez la taille de la map : ");
		System.out.print("la hauteur : ");
		int hauteur = sc.nextInt();
		System.out.print("\nla largeur : ");
		int largeur = sc.nextInt();
		Plateau plat = new Plateau(hauteur,largeur);
		plat.setObstacles(plat.choixChanceObstacle());
		Vue Equipe1 = new Vue(plat,1);
		Vue Equipe2 = new Vue(plat,2);
		for(int copt = 1;copt < 3 ; copt++){
			for(int cpt = 1 ;cpt < nbRobot+1 ; cpt++){
				System.out.print("\nJoueur ");System.out.print(copt); System.out.print(" choisissez vos robots : " );
				System.out.println("Entrez T pour un Tireur , C pour un Char ou P pour un Piegeur");
				boolean correct = false;
				while(!correct){
					String rep = sc.next();
					if(rep.equals("T") || rep.equals("C") || rep.equals("P")){
						correct = true;
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
					else{ System.out.println("Erreur dans l'entree veuillez reessayer");}
				}
			}
				
		}
		System.out.println("Voici le plateau de jeu : \n" + plat);
		while(!J1.aPerdu() || !J2.aPerdu()){
			if(joueur){
				joueurEnCour = J1;
			}else{
				joueurEnCour = J2;
			}
			System.out.println("Tour du joueur " + joueurEnCour.toString());
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
			plat.getGrille()[1][1].regenEnergie();
			plat.getGrille()[plat.getHauteur()][plat.getLargeur()].regenEnergie();
			System.out.println("Voici le plateau de jeu : \n" + plat);
			System.out.println(joueurEnCour.getListeRobot());
			joueur = !joueur;
		}


		
	}
}