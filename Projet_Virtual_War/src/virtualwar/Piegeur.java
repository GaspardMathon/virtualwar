package virtualwar;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * La classe piegeur qui h�rite de la classe Robot cr�e un robot capable de se d�placer et de poser des mines
 * @author Cl�ment
 *
 */
public class Piegeur extends Robot{
	
	private final static int ENERGIEDEBASEP = 50;
	private static int coutAction = 2;
	private static int coutDeplacement = 2;
	private static int degatSubis = 2;
	private static String type = "P";
	private static int portee = 1;
	private int nbMines;
	
	/**
	 * Constructeur d'un piegeur 
	 * @param vue Vue du tireur en fonction de son �quipe
	 * @param l largeur pour les coordonn�es du Pi�geur
	 * @param h hauteur pour les coordonn�es du Pi�geur
	 * @param equipe Entier repr�sentant l'�quipe du Pi�geur
	 * @param nbMines Nombre de mine que poss�de le pi�geur
	 */
	public Piegeur(Vue vue, int l, int h, int equipe) {
		super(vue,l,h,equipe,type,50);
		this.nbMines = 10;
	}
	
	/**
	 * Obtenir le nombre de mines actuelles du robot
	 * @return le nombre de mines
	 */
	public int getNbMines(){
		return this.nbMines;
	}
	public int getCoutAction(){
		return Piegeur.coutAction;
	}
	

	public int getCoutDeplacement(){
		return Piegeur.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return Piegeur.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return Piegeur.ENERGIEDEBASEP;
	}
	/**
	 * Actualise le nombre de mines du pi�geur 
	 * @param i nouveau nombre de mines du pi�geur
	 */
	public void setNbMine(int i){
		this.nbMines = i;
	}
	public List<Coordonnees> getDeplacements(){
		ArrayList<Coordonnees> listedep = new ArrayList<>();
	
		for(int i = -1; i<2;i++){
			for(int j =-1 ; j<2;j++){
				if(this.getVue().estDisponible(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+j))){
					listedep.add(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+j)); 
				}
			}
		}
		return listedep;
	}
	
	@SuppressWarnings("resource")
	public Coordonnees choixMouvement(){
		String choix;
		boolean corect= false;
		Scanner sc = new Scanner(System.in);
		Coordonnees res = new Coordonnees(-1,-1);
		while(!corect){
			System.out.println("Dans quelle direction voulez vous vous deplacer ?");
			System.out.println("1.En HAUT 2.En BAS 3.En GAUCHE 4.En DROITE 5.En HAUT � GAUCHE 6.En HAUT � DROITE 7.En BAS � GAUCHE 8.En BAS � DROITE");
			choix = sc.nextLine();
			if(choix.equals("1")){
				res = new Coordonnees(Constante.HAUT.getHauteur(),Constante.HAUT.getLargeur());
				corect = true;
			}
			if(choix.equals("2")){
				res = new Coordonnees(Constante.BAS.getHauteur(),Constante.BAS.getLargeur());
				corect = true;

			}
			if(choix.equals("3")){
				res = new Coordonnees(Constante.GAUCHE.getHauteur(),Constante.GAUCHE.getLargeur());
				corect = true;

			}
			if(choix.equals("4")){
				res = new Coordonnees(Constante.DROITE.getHauteur(),Constante.DROITE.getLargeur());
				corect = true;

			}
			if(choix.equals("5")){
				res = new Coordonnees(Constante.DIAGHG.getHauteur(),Constante.DIAGHG.getLargeur());
				corect = true;

			}
			if(choix.equals("6")){
				res = new Coordonnees(Constante.DIAGHD.getHauteur(),Constante.DIAGHD.getLargeur());
				corect = true;

			}
			if(choix.equals("7")){
				res = new Coordonnees(Constante.DIAGBG.getHauteur(),Constante.DIAGBG.getLargeur());
				corect = true;

			}
			if(choix.equals("8")){
				res = new Coordonnees(Constante.DIAGBD.getHauteur(),Constante.DIAGBD.getLargeur());
				corect = true;
			}
		}
		return res;
	}

	

	public List<Coordonnees> getCibles(){
		ArrayList<Coordonnees> listeMinable = new ArrayList<>();
		for(int i = -Piegeur.portee; i < Piegeur.portee+1; i++){
			for(int y =-Piegeur.portee; y < Piegeur.portee+1; y++){
				if(this.getVue().estDisponible(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+y))){
					if(this.getVue().getPlateau().getGrille()[this.getCoordonnees().getHauteur()+i][this.getCoordonnees().getLargeur()+y].getMine() == 0 && this.getVue().getPlateau().getGrille()[this.getCoordonnees().getHauteur()+i][this.getCoordonnees().getLargeur()+y].getBase() == 0){
						listeMinable.add(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+y));
					}
				}
			}
		}
		return listeMinable;
	}
	
	@SuppressWarnings("resource")
	public Coordonnees choixCible(){
		Scanner sc = new Scanner(System.in);
		String choix = "";
		boolean choixOK = false;
		System.out.println("Voici les cibles que ce robot peut pi�ger : " + this.getCibles());
		while(!choixOK){
			System.out.println("Quelle cible voulez vous pi�ger ? (Entrez le num�ros des coordonn�es dans la liste)");
			choix = sc.nextLine();
			if(Integer.parseInt(choix) > 0 && Integer.parseInt(choix)<this.getCibles().size()+1){
				choixOK = true;
			}
		}
		return this.getCibles().get(Integer.parseInt(choix)-1);
	}

	public void attaque(){
		if(this.getEnergie()<this.getCoutAction()){
			System.out.println("Votre robot n'a pas assez d'�nergie pour attaquer");
		}else{
			this.setEnergie(this.getEnergie()-this.getCoutAction());
			Coordonnees choix = this.choixCible();
			this.getVue().getPlateau().getGrille()[choix.getHauteur()][choix.getLargeur()].setMine(this.getEquipe());
			this.setNbMine(this.getNbMines()-1);
		}
	}
	
	public void regenEnergie(){ 
		this.setEnergie(this.getEnergie()+2); 
		this.setNbMine(10); 
		}
}
	
