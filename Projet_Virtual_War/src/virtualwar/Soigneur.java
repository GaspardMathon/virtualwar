package virtualwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe soigneur qui hérite de Robot crée un Soigneur capable de régénérer les points de vie des Robots alliés
 * @author Gaspard
 *
 */

public class Soigneur extends Robot{
	
	private final static int ENERGIEDEBASE = 30;
	private static int coutAction = 4;
	private static int coutDeplacement = 1;
	private static int degatSubis = 12;
	private static String type = "S";
	private int portee;
	
	/**
	 * Constructeur du tireur 
	 * @param vue Vue du robot en fonction de son equipe 
	 * @param l largueur pour les coordonnées du tireur
	 * @param h hauteur pour les coordonnées du tireur 
	 * @param equipe Equipe du tireur 
	 * @param portee portee du tireur
	 */
	
	public Soigneur(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASE);
		this.portee = portee;
	}
	
	
	public int getCoutAction(){
		return Soigneur.coutAction;
	}
	

	public int getCoutDeplacement(){
		return Soigneur.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return Soigneur.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return Soigneur.ENERGIEDEBASE;
	}
	
	/**
	 * obtenir la portee du Tireur
	 * @return la portee du Tireur
	 */
	public int getPortee(){
		return this.portee;
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
			System.out.println("1.En HAUT 2.En BAS 3.A GAUCHE 4.A DROITE 5.En HAUT à  GAUCHE 6.En HAUT à  DROITE 7.En BAS à  GAUCHE 8.En BAS à  DROITE");
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
	
	/**
	 * obtenir la coordonnée oà¹ le soin est possible dans une direction donnée  
	 * @param Direction dans laquelle on cherche un tir possible
	 * @return une coordonnée dans laquelle ont peut tirer
	 */
	public Coordonnees CoordsSoin(Coordonnees Direction){
		int cpt = 0;
		Coordonnees memoire = new Coordonnees(this.getCoordonnees().getHauteur(),this.getCoordonnees().getLargeur());
		while(cpt < this.portee){
			memoire.ajoute(Direction);
			if(!this.getVue().estDisponible(memoire)){
				// Recupà¨re l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].contientRobot){ 
					//Rà¯Â¿Â½cupà¯Â¿Â½re l'information est ce que le robot est de l'équipe alliée ?
					if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].getContenu().getEquipe() == this.getEquipe() ){
						return memoire;
					}
					else{
						return new Coordonnees(0,0);}
				}
				else{
					return new Coordonnees(0,0);}
			}
			cpt++;
		}
		return new Coordonnees(0,0); 
	}
	
	/**
	 * obtenir la liste des coordonnées pour lesquelles le soin est possible
	 * @param portee portee du Soigneur
	 * @return liste des coordonnées ciblables
	 */
	public ArrayList<Coordonnees> getCibles(){
		ArrayList <Coordonnees> listeSoin = new ArrayList<>();
		Coordonnees Coordnulle = new Coordonnees(0,0); // Coordonnées a refuser
		if(!this.CoordsSoin(Constante.BAS).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.BAS));
		}
		if(!this.CoordsSoin(Constante.HAUT).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.HAUT));
		}
		if(!this.CoordsSoin(Constante.DROITE).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.DROITE));
		}
		if(!this.CoordsSoin(Constante.GAUCHE).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.GAUCHE));
		}
		if(!this.CoordsSoin(Constante.DIAGBD).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.DIAGBD));
		}
		if(!this.CoordsSoin(Constante.DIAGBG).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.DIAGBG));
		}
		if(!this.CoordsSoin(Constante.DIAGHD).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.DIAGHD));
		}
		if(!this.CoordsSoin(Constante.DIAGHG).equals(Coordnulle)){
			listeSoin.add(CoordsSoin(Constante.DIAGHG));
		}
		return listeSoin;
	}
	
	@SuppressWarnings("resource")
	public Coordonnees choixCible(){
		Scanner sc = new Scanner(System.in);
		String choix = "";
		boolean choixOK = false;
		if(this.getCibles().isEmpty()){
			System.out.println("Vous n'avez pas de cible à  attaquer");
		}else{
			System.out.println("Voici les cibles potentielles de ce robot : " + this.getCibles());
			while(!choixOK){
				System.out.println("Quelle cible voulez-vous soigner ? (Entrez les numéros des deux coordonnées dans la liste)");
				choix = sc.nextLine();
				if(Integer.parseInt(choix) > 0 && Integer.parseInt(choix)<this.getCibles().size()+1){
					choixOK = true;
				}
			}
			return this.getCibles().get(Integer.parseInt(choix)-1);
		}
		return  this.getCoordonnees();
	}
	
	public void attaque(){
		if(this.getEnergie()<this.getCoutAction()){
			System.out.println("Votre robot n'a pas assez d'énergie pour attaquer");
		}else{
			Coordonnees c = this.choixCible();
			if(!c.equals(this.getCoordonnees())){
				Attaque a = new Attaque(this,c);
				a.soigne();
			}else{
				System.out.println("Votre soigneur cherche une cible et soigne dans le vide");
				this.setEnergie(this.getEnergie()-this.getCoutAction());
			}
		}
		
	}
	
	/**
	 * régénà¨re l'énergie du soigneur
	 */
	public void regenEnergie(){ 
		this.setEnergie(this.getEnergie()+2); 
	}
	
	public String toString(){
		String nom = "Soigneur de l'equipe "+this.getEquipe()+", ";
		if(this.getInvoque()){
			nom += this.getCoordonnees().toString()+", Vie : "+this.getEnergie()+"/"+ENERGIEDEBASE;
		}
		return nom;
	}

}
