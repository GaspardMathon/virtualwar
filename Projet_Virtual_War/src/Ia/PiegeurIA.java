package Ia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virtualwar.*;
public class PiegeurIA extends Robot{
	

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
	public PiegeurIA(Vue vue, int l, int h, int equipe) {
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
		return PiegeurIA.coutAction;
	}
	

	public int getCoutDeplacement(){
		return PiegeurIA.coutDeplacement;
	}
	

	public int getDegatSubis(){
		return PiegeurIA.degatSubis;
	}
	
	public int getEnergieDeBase(){
		return PiegeurIA.ENERGIEDEBASEP;
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

	

	public List<Coordonnees> getCibles(){
		ArrayList<Coordonnees> listeMinable = new ArrayList<>();
		for(int i = -PiegeurIA.portee; i < PiegeurIA.portee+1; i++){
			for(int y =-PiegeurIA.portee; y < PiegeurIA.portee+1; y++){
				if(this.getVue().estDisponible(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+y))){
					if(this.getVue().getPlateau().getGrille()[this.getCoordonnees().getHauteur()+i][this.getCoordonnees().getLargeur()+y].getMine() == 0 && this.getVue().getPlateau().getGrille()[this.getCoordonnees().getHauteur()+i][this.getCoordonnees().getLargeur()+y].getBase() == 0){
						listeMinable.add(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+y));
					}
				}
			}
		}
		return listeMinable;
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
	
	public String toString(){
		String nom = "Pi�geur(s) de l'equipe "+this.getEquipe()+", ";
		if(this.getInvoque()){
			nom += this.getCoordonnees().toString()+", Vie : "+this.getEnergie()+", nb mine(s) :" + this.getNbMines();
		}
		return nom;
	}
	
	
	
}
