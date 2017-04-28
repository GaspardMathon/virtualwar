package virtualwar;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe char qui hérite de la classe Robot créer un Robot qui ne peut se déplacer qu'en ligne sur 2 cases et de tirer 
 * @author salvadoc
 *
 */
public class Char extends Robot {
	
	private final static int ENERGIEDEBASEC = 60;
	private static int coutAction = 1;
	private static int coutDeplacement = 5;
	private static int degatSubis = 6;
	private static String type = "C";
	private int portee;
	
	/**
	 * Contructeur du Char
	 * @param vue vue du char en fonction de l'équipe
	 * @param l largeur de la coordonnée initial du char
	 * @param h hauteur de la coordonnée initial du char
	 * @param equipe équipe du char
	 * @param portee portée du char
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
	 * obtenir la coordonnée de tir possible dans une direction donnée  
	 * @param Direction dans laquelle on cherche un tir possible
	 * @return une coordonnée dans laquelle ont peut tirer
	 */
	public Coordonnees CoordsTir(Coordonnees Direction){
		int cpt = 0;
		Coordonnees memoire = new Coordonnees(this.getCoordonnees().getHauteur(),this.getCoordonnees().getLargeur());
		while(cpt < this.portee){
			memoire.ajoute(Direction);
			if(!this.getVue().estDisponible(memoire)){
				// Recupère l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].contientRobot){ 
					//Récupère l'information est ce que le robot est de l'équipe ennemi ?
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
	 * obtenir la liste des coordonnées pour lesquelles le tir est possible
	 * @param portee portee du Char
	 * @return liste des coordonnées ciblables
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
	
	public Coordonnees choixCible(){
		return null;
	}
	public void attaque(){
		if(this.getEnergie()<this.getCoutAction()){
			System.out.println("Votre robot n'a pas assez d'énergie pour attaquer");
		}else{
			Attaque a = new Attaque(this,this.choixCible());
			a.agit();
		}
		
	}

}

