package Ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virtualwar.*;
public class CharIA extends Robot {
	
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
	public CharIA(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASEC);
		this.portee = portee;
	}	

	
	public int getCoutAction(){
		return CharIA.coutAction;
	}
	

	public int getCoutDeplacement(){
		return CharIA.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return CharIA.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return CharIA.ENERGIEDEBASEC;
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
		Coordonnees temp;
		Coordonnees temp2;
		for(Coordonnees direction : listedirection){
			temp = new Coordonnees(this.getCoordonnees().getHauteur(),this.getCoordonnees().getLargeur());
			temp.ajoute(direction);
			if(this.getVue().estDisponible(temp)){
				temp2 = new Coordonnees(temp.getHauteur(),temp.getLargeur());
				temp2.ajoute(direction);
				if(this.getVue().estDisponible(temp2)){
					listedep.add(temp2);
				}else{
					listedep.add(temp);
				}
			}
		}
		return listedep;
	}
	public Coordonnees choixMouvement(){
		Coordonnees res = new Coordonnees(-1,-1);
		int choix = new Random().nextInt(4)+1;
		if(choix == 1){
			res = new Coordonnees(Constante.HAUT.getHauteur(),Constante.HAUT.getLargeur());
		}
		if(choix == 2){
			res = new Coordonnees(Constante.BAS.getHauteur(),Constante.BAS.getLargeur());
		}
		if(choix == 3){
			res = new Coordonnees(Constante.GAUCHE.getHauteur(),Constante.GAUCHE.getLargeur());
		}
		if(choix == 4){
			res = new Coordonnees(Constante.DROITE.getHauteur(),Constante.DROITE.getLargeur());
		}
		
		return res;
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
				// Récupère l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].getContientrobot()){ 
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
		Coordonnees Coordnulle = new Coordonnees(0,0); // Coordonnées a refuser
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
		if(this.getCibles().isEmpty()){
			return this.getCoordonnees();
		}else{
			return this.getCibles().get(new Random().nextInt(this.getCibles().size()));
		}
	}
	public void attaque(){
		if(this.getEnergie()<this.getCoutAction()){
			System.out.println("Votre robot n'a pas assez d'énergie pour attaquer");
		}else{
			Attaque a = new Attaque(this,this.choixCible());
			a.agit();
		}
		
	}
	
	public String toString(){
		String nom = "Char de l'equipe "+this.getEquipe()+", ";
		if(this.getInvoque()){
			nom += this.getCoordonnees().toString()+", Vie : "+this.getEnergie();
		}
		return nom;
	}


	public void regenEnergie() {
		this.setEnergie(this.getEnergie()+2); 
	}
	
	
}