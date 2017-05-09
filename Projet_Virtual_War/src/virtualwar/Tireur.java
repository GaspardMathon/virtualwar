package virtualwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe tireur qui hérite de Robot crée un Tireur capable de se déplacer et d'attaquer des Robots énemies
 * @author Clément
 *
 */
public class Tireur extends Robot {
	
	private final static int ENERGIEDEBASE = 40;
	private static int coutAction = 2;
	private static int coutDeplacement = 1;
	private static int degatSubis = 3;
	private static String type = "T";
	private int portee;
	
	/**
	 * Constructeur du tireur 
	 * @param vue Vue du robot en fonction de son equipe 
	 * @param l largueur pour les coordonnées du tireur
	 * @param h hauteur pour les coordonnées du tireur 
	 * @param equipe Equipe du tireur 
	 * @param portee portee du tireur
	 */
	public Tireur(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASE);
		this.portee = portee;
	}
	
	
	public int getCoutAction(){
		return Tireur.coutAction;
	}
	

	public int getCoutDeplacement(){
		return Tireur.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return Tireur.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return Tireur.ENERGIEDEBASE;
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
			System.out.println("1.En HAUT 2.En BAS 3.En GAUCHE 4.En DROITE 5.En HAUT à GAUCHE 6.En HAUT à DROITE 7.En BAS à GAUCHE 8.En BAS à DROITE");
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
	 * obtenir la coordonnï¿½e de tir possible dans une direction donnï¿½e  
	 * @param Direction dans laquelle on cherche un tir possible
	 * @return une coordonnï¿½e dans laquelle ont peut tirer
	 */
	public Coordonnees CoordsTir(Coordonnees Direction){
		int cpt = 0;
		Coordonnees memoire = new Coordonnees(this.getCoordonnees().getHauteur(),this.getCoordonnees().getLargeur());
		while(cpt < this.portee){
			memoire.ajoute(Direction);
			if(!this.getVue().estDisponible(memoire)){
				// Recupï¿½re l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].contientRobot){ 
					//Rï¿½cupï¿½re l'information est ce que le robot est de l'ï¿½quipe ennemi ?
					if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].getContenu().getEquipe() != this.getEquipe() ){
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
	 * obtenir la liste des coordonnï¿½es pour lesquelles le tir est possible
	 * @param portee portee du Tireur
	 * @return liste des coordonnï¿½es ciblables
	 */
	public ArrayList<Coordonnees> getCibles(){
		ArrayList <Coordonnees> listeTir = new ArrayList<>();
		Coordonnees Coordnulle = new Coordonnees(0,0); // Coordonnees a refuser
		if(!this.CoordsTir(Constante.BAS).equals(Coordnulle)){
			listeTir.add(CoordsTir(Constante.BAS));
		}
		if(!this.CoordsTir(Constante.HAUT).equals(Coordnulle)){
			listeTir.add(CoordsTir(Constante.HAUT));
		}
		if(!this.CoordsTir(Constante.DROITE).equals(Coordnulle)){
			listeTir.add(CoordsTir(Constante.DROITE));
		}
		if(!this.CoordsTir(Constante.GAUCHE).equals(Coordnulle)){
			listeTir.add(CoordsTir(Constante.GAUCHE));
		}
		return listeTir;
	}
	

	
	
	
	@SuppressWarnings("resource")
	public Coordonnees choixCible(){
		Scanner sc = new Scanner(System.in);
		String choix = "";
		boolean choixOK = false;
		if(this.getCibles().isEmpty()){
			System.out.println("Vous n'avez pas de cible à attaquer");
		}else{
			System.out.println("Voici les cibles potentielles de ce robot : " + this.getCibles());
			while(!choixOK){
				System.out.println("Quelle cible voulez vous attaquer ? (Entrez le numéros des coordonnées dans la liste)");
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
				a.agit();
			}else{
				System.out.println("Votre tireur cherche une cible et tire dans le vide");
				this.setEnergie(this.getEnergie()-this.getCoutAction());
			}
		}
		
	}
	/**
	 * régénère l'énergie du tireur
	 */
	public void regenEnergie(){ 
		this.setEnergie(this.getEnergie()+2); 
	}
	
	public String toString(){
		String nom = "Tireur de l'equipe "+this.getEquipe()+", ";
		if(this.getInvoque()){
			nom += this.getCoordonnees().toString()+", Vie : "+this.getEnergie();
		}
		return nom;
	}
	
	
	
}