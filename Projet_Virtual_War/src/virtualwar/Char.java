package virtualwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * la classe char qui h�rite de la classe Robot cr�er un Robot qui ne peut se d�placer qu'en ligne sur 2 cases et de tirer 
 * @author salvadoc
 *
 */
public class Char extends Robot {
	
	private final static int ENERGIEDEBASEC = 60;
	private static int coutAction = 1;
	private static int coutDeplacement = 5;
	private static int degatSubis = 6;
	private static String type = "C";
	private int portee = 10;
	
	/**
	 * Contructeur du Char
	 * @param vue vue du char en fonction de l'�quipe
	 * @param l largeur de la coordonn�e initial du char
	 * @param h hauteur de la coordonn�e initial du char
	 * @param equipe �quipe du char
	 * @param portee port�e du char
	 */
	public Char(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASEC);
		this.portee = portee;
	}	

	
	public int getCoutAction(){
		return Char.coutAction;
	}
	

	public int getCoutDeplacement(){
		return Char.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return Char.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return Char.ENERGIEDEBASEC;
	}
	
	public int getPortee(){
		return this.portee;
	}
	
	public List<Coordonnees> getDeplacements(){
		ArrayList<Coordonnees> listedep = new ArrayList<>();
		ArrayList<Coordonnees> listedirection = new ArrayList<>();
		listedirection.add(Constante.HAUT);
		listedirection.add(Constante.BAS);
		listedirection.add(Constante.DROITE);
		listedirection.add(Constante.GAUCHE);
		
		for(Coordonnees direction : listedirection){
			if(this.getVue().estDisponible(this.getCoordonnees().ajoute(direction).ajoute(direction))){
				listedep.add(this.getCoordonnees().ajoute(direction).ajoute(direction));
			}else{
				if(this.getVue().estDisponible(this.getCoordonnees().ajoute(direction))){
					listedep.add(this.getCoordonnees().ajoute(direction));
				}
			}
		}
		return listedep;
	}
	public Coordonnees choixMouvement(){
		return null;
	}

	
	/**
	 * obtenir la coordonn�e de tir possible dans une direction donn�e  
	 * @param Direction dans laquelle on cherche un tir possible
	 * @return une coordonn�e dans laquelle ont peut tirer
	 */
	public Coordonnees CoordsTir(Coordonnees Direction){
		int cpt = 0;
		Coordonnees memoire = new Coordonnees(this.getCoordonnees().getHauteur(),this.getCoordonnees().getLargeur());
		while(cpt < this.portee){
			memoire.ajoute(Direction);
			if(!this.getVue().estDisponible(memoire)){
				// Recup�re l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].contientRobot){ 
					//R�cup�re l'information est ce que le robot est de l'�quipe ennemi ?
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
	 * obtenir la liste des coordonn�es pour lesquelles le tir est possible
	 * @param portee portee du Char
	 * @return liste des coordonn�es ciblables
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
				System.out.println("Quelle cible voulez vous attaquer ? (Entrez le numéros des coordonn�es dans la liste)");
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
			System.out.println("Votre robot n'a pas assez d'�nergie pour attaquer");
		}else{
			Attaque a = new Attaque(this,this.choixCible());
			a.agit();
		}
		
	}

}

