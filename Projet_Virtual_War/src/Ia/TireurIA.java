package Ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virtualwar.*;

public class TireurIA extends Robot {

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
	public TireurIA(Vue vue, int l ,int h,int equipe,int portee){
		super(vue,l,h,equipe,type,ENERGIEDEBASE);
		this.portee = portee;
	}
	
	
	public int getCoutAction(){
		return TireurIA.coutAction;
	}
	

	public int getCoutDeplacement(){
		return TireurIA.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return TireurIA.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return TireurIA.ENERGIEDEBASE;
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
	
	public Coordonnees choixMouvement(){
		int choix = new Random().nextInt(8);
		Coordonnees res = new Coordonnees(-1,-1);
		if(choix == 0){
			res = new Coordonnees(Constante.HAUT.getHauteur(),Constante.HAUT.getLargeur());
		}
		if(choix == 1){
			res = new Coordonnees(Constante.BAS.getHauteur(),Constante.BAS.getLargeur());

		}
		if(choix == 2){
			res =  new Coordonnees(Constante.GAUCHE.getHauteur(),Constante.GAUCHE.getLargeur());
		}
		if(choix == 3){
			res = new Coordonnees(Constante.DROITE.getHauteur(),Constante.DROITE.getLargeur());
		}
		if(choix == 4){
			res = new Coordonnees(Constante.DIAGHG.getHauteur(),Constante.DIAGHG.getLargeur());
		}
		if(choix == 5){
			res = new Coordonnees(Constante.DIAGHD.getHauteur(),Constante.DIAGHD.getLargeur());
		}
		if(choix == 6){
			res =  new Coordonnees(Constante.DIAGBG.getHauteur(),Constante.DIAGBG.getLargeur());
		}
		if(choix == 7){
			res =  new Coordonnees(Constante.DIAGBD.getHauteur(),Constante.DIAGBD.getLargeur());
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
				// Recupére l'information : est ce que la cellule contient un robot ?
				if(this.getVue().getPlateau().getGrille()[memoire.getHauteur()][memoire.getLargeur()].getContientrobot()){ 
					//Récupére l'information est ce que le robot est de l'équipe ennemi ?
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
	 * régénére l'énergie du tireur
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






	
	
	
	

