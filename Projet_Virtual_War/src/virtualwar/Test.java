package virtualwar;

import java.util.Random;
import java.util.Scanner;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Random r = new Random();
		boolean finDePartie = false,joueur = r.nextBoolean();
		Joueur joueurEnCour;
		int choix = -1;
		
		
		System.out.println("Bienvenue dans cette version prototype de VirtualWar");
		System.out.println("Veuillez entrer le nom du joueur 1");
		String nom1 = sc.next();
		System.out.println("Entrez le nombre de Robot par equipe");
		int nbRobot = sc.nextInt(5);
		Joueur J1 = new Joueur(nom1,nbRobot,1);
		System.out.println("Veuillez entrer le nom du joueur 2");
		String nom2 = sc.next();
		Joueur J2 = new Joueur(nom2,nbRobot,2);
		System.out.println("Entrez la taille de la map:");
		System.out.println("la hauteur :");
		int hauteur = sc.nextInt();
		System.out.println("la largeur :");
		int largeur = sc.nextInt();
		Plateau plat = new Plateau(hauteur,largeur);
		Vue Equipe1 = new Vue(plat,1);
		Vue Equipe2 = new Vue(plat,2);
		for(int copt = 1;copt < 3 ; copt++){
			for(int cpt = 1 ;cpt < nbRobot+1 ; cpt++){
				System.out.print("Joueur ");System.out.print(copt); System.out.print(" choisissez votre" );System.out.print(cpt);System.out.print("Robot");
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
		System.out.println(plat);
		while(!J1.aPerdu() && !J2.aPerdu()){
			if(joueur){
				joueurEnCour = J1;
			}else{
				joueurEnCour = J2;
			}
			System.out.println("Tour du joueur " + joueurEnCour.toString());
			choix = joueurEnCour.choixActions();
			
			if(choix == 1){
				joueurEnCour.invoqueRobot(joueurEnCour.choisirInvocation());
			}else if(choix == 2){
				joueurEnCour.choixRobot().deplacement();
			}else {
				System.out.println("ERREUR");
			}
			System.out.println(plat);
			joueur = !joueur;
		}


		
	}
}