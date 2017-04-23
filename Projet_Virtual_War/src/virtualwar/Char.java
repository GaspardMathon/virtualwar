package virtualwar;

import java.util.List;
import java.util.ArrayList;

public class Char extends Robot {
	
	private final static int ENERGIEDEBASE = 60;
	private int energie;
	private static int deplacement = 2;
	private static int coutAction = 1;
	private static int coutDep = 5;
	private static int degatsSubis = 6;
	private static String type = "C";
	private static int portee = 10;
	private List<Coordonnees> liste;
	
	public Char(Vue vue, int l, int h, int equipe) {
		super(vue,l,h,equipe,type,ENERGIEDEBASE);
	}
	
	public int getEnergie(){
		return energie;
	}
	
	public int getEnergieDeBase(){
		return ENERGIEDEBASE;
	}
	public  int getDeplacement() {
		return deplacement;
	}
	
	public  int getCoutAction() {
		return coutAction;
	}
	public int getPortee(){
		return portee;
	}
	
	public  int getDegatsSubis(){
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

	public void deplacement() {
		
	}
	public void attaque(){
		
	}
}
