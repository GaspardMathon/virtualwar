package Ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virtualwar.*;

public class IntelligenceArtificielle {
	
	private String nom;
	private int nbRobot;
	private int equipe;
	private List<Robot> listeRobot;
	Random r;
	
	public IntelligenceArtificielle(String nom,int nbRobot,int equipe){
		this.listeRobot = new ArrayList<>();
		this.nom = nom;
		this.nbRobot = nbRobot;
		this.equipe = equipe;
		this.r = new Random();
	}
	
	public boolean aPerdu(){
		for(Robot r : listeRobot){
			if(!r.estMort()){
				return false;
			}
		}
		System.out.println("L'intelligence artificiel "+this.getEquipe()+" "+this.getNom()+" n'a plus de robot et a perdu la partie !");
		return true;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNbRobot() {
		return nbRobot;
	}
	public void setNbRobot(int nbRobot) {
		this.nbRobot = nbRobot;
	}
	public int getEquipe() {
		return equipe;
	}
	public void setEquipe(int equipe) {
		this.equipe = equipe;
	}
	public List<Robot> getListeRobot() {
		return listeRobot;
	}
	public void setListeRobot(List<Robot> listeRobot) {
		this.listeRobot = listeRobot;
	}
	
	public void ajouterTireur(Vue vue,int h,int l){
		listeRobot.add(new TireurIA(vue,h,l,equipe,1));
	}
	public void ajouterPiegeur(Vue vue,int h,int l){
		listeRobot.add(new PiegeurIA(vue,h,l,equipe));
	}
	public void ajouterChar(Vue vue,int h,int l){
		listeRobot.add(new CharIA(vue,h,l,equipe,10));
	}
	
	public Robot choisirInvocation(){
		int robot = -1;
		ArrayList<Robot> robotInvocable = new ArrayList<>();
		for(Robot r : this.getListeRobot()){
			if(!r.getInvoque()){
				robotInvocable.add(r);
			}
		}
		robot = r.nextInt(robotInvocable.size());
		return robotInvocable.get(robot);
	}
	
	public boolean invoqueRobot(Robot r){
		boolean res = false;
		int i =-1,y=-1,x=-1,z=-1;
		
		if(this.equipe == 1){
			if(r.getVue().estDisponible(new Coordonnees(1,2))){
				i=1;
				y=2;
				res=true;
			}else{
				if(r.getVue().estDisponible(new Coordonnees(2,1))){
					i=2;
					y=1;
					res=true;
				}else{
					if(r.getVue().estDisponible(new Coordonnees(2,2))){
						i=2;
						y=2;
						res=true;
					}else{
						res = false;
					}
				}
			}
			
			if(res){
				r.getVue().getPlateau().getGrille()[i][y].deplacerSur(r);
				r.setCoordonnees(new Coordonnees(i,y));
				r.setInvoque(true);
			}
			return res;
		}else{
			x =r.getVue().getPlateau().getGrille().length-2;
			z= r.getVue().getPlateau().getGrille()[1].length-2;
			if(r.getVue().estDisponible(new Coordonnees(x-1,z))){
				i = x-1;
				y =z;
				res = true;
			}else{
				if(r.getVue().estDisponible(new Coordonnees(x,z-1))){
					i = x;
					y =z-1;
					res = true;
				}else{
					if(r.getVue().estDisponible(new Coordonnees(x-1,z-1))){
						i = x-1;
						y =z-1;
						res = true;
					}else{
						res = false;
					}
				}
			}
			if(res){
				r.getVue().getPlateau().getGrille()[i][y].deplacerSur(r);
				r.setCoordonnees(new Coordonnees(i,y));
				r.setInvoque(true);
			}
			return res;
			
		}
		
		

		
	
	}
	
	public boolean baseVide(){
		boolean baseVide = true;
		
		for(Robot r : this.getListeRobot()){
			if(!r.getInvoque()){
				baseVide=false;
			}
		}
		return baseVide;
	}
	public boolean plateauVide(){
		boolean plateauVide = true;
		
		for(Robot r : this.getListeRobot()){
			if(r.getInvoque()){
				plateauVide = false;
			}
		}
		return plateauVide;
	}
	
	public int choixAction(){
		if(this.plateauVide()){
			return 1;
		}else{
			if(this.baseVide()){
				return r.nextInt(2)+2;
			}else{
				return r.nextInt(3)+1;
			}
		}
	}
	
	public Robot choixRobot(){
		ArrayList<Robot> robotInvoque = new ArrayList<>();
		int robot = -1;
		
		for(Robot r : this.getListeRobot()){
			if(r.getInvoque()){
				robotInvoque.add(r);
			}
		}
		robot = r.nextInt(robotInvoque.size());
		return robotInvoque.get(robot);
 	}
	
	/**
	 * Creer la composition de l'ï¿½quipe
	 * @param vue vue de l'équipe
	 */
	public void choixEquipe(Vue vue){
		if(this.getNbRobot()==1){
			this.ajouterTireur(vue, 0, 0);
		}else{
			if(this.getNbRobot()%2 == 0){
				for(int i = 0; i < this.getNbRobot()/2;i++){
					this.ajouterTireur(vue, 0, 0);
				}
				for(int i = 0; i < this.getNbRobot()/2;i++){
					this.ajouterPiegeur(vue, 0, 0);
				}
			}else{
				for(int i = 0; i < this.getNbRobot()/2;i++){
					this.ajouterTireur(vue, 0, 0);
				}
				for(int i = 0; i < this.getNbRobot()/2;i++){
					this.ajouterPiegeur(vue, 0, 0);
				}
				this.ajouterChar(vue, 0,0);
			}
		}
		
	}
	
	public String toString(){
    	return "" + this.nom + " (equipe nï¿½" + this.equipe + ") ";
	}


}



