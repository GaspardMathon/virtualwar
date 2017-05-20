package Ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virtualwar.*;
public class FurtifIA extends Robot {
	
	private final static int ENERGIEDEBASEC = 25;
	private static int coutAction = 1;
	private static int coutDeplacement = 4;
	private static int degatSubis = 3;
	private static String type = "F";
	private int portee;
	
	/**
	 * Contructeur du FurtifIA
	 * @param vue vue du FurtifIA en fonction de l'�quipe
	 * @param l largeur de la coordonn�e initial du FurtifIA
	 * @param h hauteur de la coordonn�e initial du FurtifIA
	 * @param equipe �quipe du FurtifIA
	 * @param portee port�e du FurtifIA
	 */
	public FurtifIA(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASEC);
		this.portee = portee;
	}	

	
	public int getCoutAction(){
		return FurtifIA.coutAction;
	}
	

	public int getCoutDeplacement(){
		return FurtifIA.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return FurtifIA.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return FurtifIA.ENERGIEDEBASEC;
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
				// R�cupère l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].getContientrobot()){ 
					//R�cupère l'information est ce que le robot est de l'�quipe ennemi ?
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
	 * @param portee portee du FurtifIA
	 * @return liste des coordonn�es ciblables
	 */
	public ArrayList<Coordonnees> getCibles(){
		ArrayList <Coordonnees> listeTir = new ArrayList<>();
		Coordonnees Coordnulle = new Coordonnees(0,0); // Coordonn�es a refuser
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
			System.out.println("Votre robot n'a pas assez d'�nergie pour attaquer");
		}else{
			Attaque a = new Attaque(this,this.choixCible());
			a.agit();
		}
		
	}
	
	public String toString(){
		String nom = "FurtifIA de l'equipe "+this.getEquipe()+", ";
		if(this.getInvoque()){
			nom += this.getCoordonnees().toString()+", Vie : "+this.getEnergie();
		}
		return nom;
	}


	public void regenEnergie() {
		this.setEnergie(this.getEnergie()+2); 
	}
	
	
}