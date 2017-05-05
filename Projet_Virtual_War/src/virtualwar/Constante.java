package virtualwar;
import java.util.HashMap;
import java.util.Scanner;

public class Constante {
	
	HashMap<Coordonnees,String> listeCoord;	
	
	//Liste des constante de saisie 
	static final int DEPLACEMENT = 0;
	static final int ENERGIE = 1;
	static final int COUTACTION = 2;
	static final int COUTDEPLACEMENT = 3;
	static final int DEGATTIR = 4;
	static final int DEGATMINE = 5;
	static final int AFFICHE = 6;
	static final int DEPLACEMENTS = 7;
	static final int HAUTEUR_MIN = 4;
	static final int HAUTEUR_MAX = 30;
	static final int LARGEUR_MIN = 5;
	static final int LARGEUR_MAX = 40;
	static final int ROBOTS_MAX = 5;
	static final int POURCENTAGE_OBSTACLE_MIN = 0;
	static final int POURCENTAGE_OBSTACLE_MAX = 90;
	
	
	//Liste des constante de deplacement
	static final Coordonnees DIAGBD = new Coordonnees(1,1);
	static final Coordonnees DIAGBG = new Coordonnees(1,-1);
	static final Coordonnees DIAGHD = new Coordonnees(-1,1);
	static final Coordonnees DIAGHG = new Coordonnees(-1,-1);
	static final Coordonnees GAUCHE = new Coordonnees(0,-1);
	static final Coordonnees DROITE = new Coordonnees(0,1);
	static final Coordonnees HAUT = new Coordonnees(-1,0);
	static final Coordonnees BAS = new Coordonnees(1,0);

	private static Scanner sc;

	private static Scanner sc2;
	
	//Hashmap de coordonnees : Associe pour une coordonnee un nom ex : (-1/1) = DIAGBD
	public HashMap<Coordonnees, String> setListeCoord(){
		listeCoord.put(BAS, "BAS");
		listeCoord.put(HAUT, "HAUT");
		listeCoord.put(GAUCHE, "GAUCHE");
		listeCoord.put(DROITE, "DROITE");
		listeCoord.put(DIAGHG, "DIAGONALE HAUT GAUCHE");
		listeCoord.put(DIAGHD, "DIAGONALE HAUT DROITE");
		listeCoord.put(DIAGBD, "DIAGONALE BAS DROITE");
		listeCoord.put(DIAGBG, "DIAGONALE BAS GAUCHE");
		
		return listeCoord;
	}
	
	/**
	 * Controle de saisie d'entier [min-max]
	 * Demande un nombre tant qu'il n'est pas valide.
	 * @param nbMin 
	 * @param nbMax 
	 * @return le nombre valide
	 */
	public static int saisieEntier(int nbMin, int nbMax){
		sc = new Scanner(System.in);
		String saisie ="";
		saisie = sc.next();
		boolean valide = false;
		char tmp;
		int nbLettre = 0;
		
		while(!valide){
			if( nbMin >= 0 && nbMin <= 9 && nbMax >= 0 && nbMax <= 9){
	            if(saisie.matches("[" + nbMin + "-" + nbMax + "]")){
	                valide=true;
	            }else{
	            	System.out.println("Saisie invalide, veulliez recommencer ");
	            	saisie = sc.next();
	            }
			}else if (nbMax > 9){
				do{
					do{
						nbLettre = 0;
						for(int cpt = 0; cpt < saisie.length(); cpt++){
							tmp = saisie.charAt(cpt);
							if(!Character.isDigit(tmp)){
								nbLettre = nbLettre + 1;
							}
						}
						if(nbLettre>0){
			            	System.out.println("Saisie invalide, veulliez recommencer ");
			            	saisie = sc.next();
			            }
					}while(nbLettre != 0);
					if(Integer.parseInt(saisie) >= nbMin && Integer.parseInt(saisie) <= nbMax){
						valide = true;
						
					}else{
		            	System.out.println("Saisie invalide, veulliez recommencer ");
		            	saisie = sc.next();
					}
				}while(!valide);
			}
		}
		return Integer.parseInt(saisie);
	}
	
	/**
	 * Controle de saisie sur la lettre qui definie le type de robot
	 * @return lettre valide
	 */
	
	public static char saisieTypeRobot(){
		sc2 = new Scanner(System.in);
		String saisie ="";
		boolean valide = false;
		saisie = sc2.next();
		do{
			if(saisie.length()>1){
				System.out.println("Saisie invalide, veulliez recommencer ");
				saisie = sc2.next();
			}else{
				if(saisie.charAt(0) =='t' || saisie.charAt(0) == 'T'){
					return 'T';
				}else if(saisie.charAt(0) =='c' || saisie.charAt(0) == 'C'){
					return 'C';
				}else if(saisie.charAt(0) =='p' || saisie.charAt(0) == 'P'){
					return 'P';
				}else{
					System.out.println("Saisie invalide, veulliez recommencer ");
					saisie = sc2.next();
				}
			}	
		}while(!valide);
		return ' ';
	}
	
	public static int saisieIntProtegee(Scanner sc){
		boolean saisieCorrecte = false;
		String nbRobot;
		int result = 0;
		do{
			nbRobot = sc.nextLine();
			if(nbRobot.equals("")){
				System.out.print("Il faut rentrer un nombre !\n");
			}
			for(int i =0;i<nbRobot.length();i++){
				if(nbRobot.charAt(i) <= '9' && nbRobot.charAt(i) >= '0'){
					result += nbRobot.charAt(i);
					saisieCorrecte = true;
				}else{
					System.out.print("Il faut rentrer un nombre !\n");
					saisieCorrecte = false;
					break;
				}
			}
			if(saisieCorrecte == true){
				result =  Integer.parseInt(nbRobot);
			}
		}while(!saisieCorrecte);
		return result;
	}
}
