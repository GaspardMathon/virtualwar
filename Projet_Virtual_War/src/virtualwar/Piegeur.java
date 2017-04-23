package virtualwar;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Piegeur extends Robot {

	private final static int ENERGIEDEBASE = 50;
	
	private static int deplacement = 1;
	private static int coutAction = 2;
	private static int coutDep = 2;
	private static int degatsSubis = 2;
	private static String type = "P";
	private static int portee = 1;
	
	public Piegeur(Vue vue, int l, int h, int equipe) {
		super(vue,l,h,equipe,type,50);
	}
	

	public int getEnergieDeBase(){
		return ENERGIEDEBASE;
	}
	
	private List<Coordonnees> liste;

	private Scanner sc;
	
	public  int getDeplacement() {
		return deplacement;
	}
	
	public int getPortee(){
		return portee;
	}
	
	public  int getCoutAction() {
	
		return coutAction;
	}
	
	public  int getDegatsSubis() {
		return degatsSubis;
	}
	public  String getType() {
		return type;
	}
	
	//on a modifier ton truc 
	public List<Coordonnees> getDeplacements(){
		this.liste = new ArrayList<>();
	
		for(int i = -1; i<2;i++){
			for(int j =-1 ; j<2;j++){
				if(this.getVue().estDisponible(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+j))){
					liste.add(new Coordonnees(this.getCoordonnees().getHauteur()+i,this.getCoordonnees().getLargeur()+j)); 
				}
			}
		}
		return liste;
	}
	
	public int getCoutDeplacement(){
		return coutDep;
	}
	
	public boolean estDans(Coordonnees c){
		boolean res = false;
		for(Coordonnees compt : this.getDeplacements()){
			if(c.getHauteur() == compt.getHauteur() && c.getLargeur() == compt.getLargeur()){
				return true;
			}
		
		}
		return res;
	}

	public Coordonnees choixMouvement(){
		String choix;
		boolean corect= false;
		sc = new Scanner(System.in);
		Coordonnees res = new Coordonnees(-1,-1);
		while(!corect){
			System.out.println("Dans quelle direction voulez vous vous deplacer ?");
			System.out.println("1.HAUT 2.BAS 3.GAUCHE 4.DROITE 5.DIAGHG 6.DIAGHD 7.DIAGBG 8.DIAGBD");
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
	
	public void deplacement(){
		boolean mouvement = false;
		Coordonnees c = new Coordonnees(-1,-1);
		if(this.getEnergie()< Constante.COUTDEPLACEMENT){
			System.out.println("Votre robot n'as pas assez d'energie pour se deplacer");
		}else{
			while(!mouvement){
				c = this.choixMouvement();
				Coordonnees test = new Coordonnees(this.getCoordonnees().getHauteur()+c.getHauteur(),this.getCoordonnees().getLargeur()+c.getLargeur());
				if(this.estDans(test)){
					mouvement = true;
				}
				
			}
			Deplacement d = new Deplacement(this,c);
			d.agit();
		}
	}
	
	public void attaque(){
		
	}
					
			
			
					
	}
	
