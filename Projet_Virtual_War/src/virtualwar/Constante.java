package virtualwar;
import java.util.HashMap;

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
	
	
	//Liste des constante de deplacement
	static final Coordonnees DIAGBD = new Coordonnees(1,1);
	static final Coordonnees DIAGBG = new Coordonnees(1,-1);
	static final Coordonnees DIAGHD = new Coordonnees(-1,1);
	static final Coordonnees DIAGHG = new Coordonnees(-1,-1);
	static final Coordonnees GAUCHE = new Coordonnees(0,-1);
	static final Coordonnees DROITE = new Coordonnees(0,1);
	static final Coordonnees HAUT = new Coordonnees(-1,0);
	static final Coordonnees BAS = new Coordonnees(1,0);
	
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
	
	
}
