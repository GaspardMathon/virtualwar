package virtualwar;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;


public class Joueur {
	private String nom;
	private int nbRobot;
	private int equipe;
	private List<Robot> listeRobot;
	private Scanner sc;
	private Scanner sc2;
	private Scanner sc3;
	private Scanner sc4;
	
	public Joueur(String nom,int nbRobot,int equipe){
		this.listeRobot = new ArrayList<>();
		this.nom = nom;
		this.nbRobot = nbRobot;
		this.equipe = equipe;
	}
	public boolean aPerdu(){
		if(nbRobot == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void ajouterTireur(Vue vue,int h,int l){
		listeRobot.add(new Tireur(vue,h,l,equipe));
	}
	public void ajouterPiegeur(Vue vue,int h,int l){
		listeRobot.add(new Piegeur(vue,h,l,equipe));
	}
	public void ajouterChar(Vue vue,int h,int l){
		listeRobot.add(new Char(vue,h,l,equipe));
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
	public String toString(){
		return this.getEquipe()+" "+this.getNom();
				
	}
	
	public Robot choisirInvocation(){
		boolean saisieOK = false;
		int robot = -1;
		System.out.print("Vos robos dans votre base : ");
		ArrayList<Robot> robotInvocable = new ArrayList<>();
		sc = new Scanner(System.in);
		for(Robot r : this.getListeRobot()){
			if(!r.getInvoque()){
				robotInvocable.add(r);
			}
		}
		for(Robot r : robotInvocable){
			System.out.print("  " + r.toString());
		}
		System.out.println("  Quel Robot voulez vous invoquer(ecrivez le numero du robot");
		while(!saisieOK){
			robot = sc.nextInt();
			if(robot>0 && robot <=robotInvocable.size()){
				saisieOK = true;
			}
		}
		return robotInvocable.get(robot-1);
	}
	//a finir pour coordener deja prise
	public void invoqueRobot(Robot r){
		sc2 = new Scanner(System.in);
		boolean ok = false;
		int i =-1,y=-1,x=-1,z=-1;
		
		System.out.println("Ou voulez vous placer votre Robot : ");
		if(this.equipe == 1){
			if(r.getVue().estDisponible(new Coordonnees(1,2))){
				System.out.println("Coordonnee possible : [1,2]");
			}
			if(r.getVue().estDisponible(new Coordonnees(2,1))){
				System.out.println("Coordonnee possible : [2,1]");
			}
			while(!ok){
				 i = sc2.nextInt();
				 y = sc2.nextInt();
				if((i>=1 && i<=2)&&(y>=1 && y<=2)){
					ok = true;
				}
			}
			r.getVue().getPlateau().getGrille()[i][y].deplacerSur(r);
			r.setCoordonnees(new Coordonnees(i,y));
			r.setInvoque(true);

		}else{
			x =r.getVue().getPlateau().getGrille().length-2;
			z= r.getVue().getPlateau().getGrille()[1].length-2;
			System.out.println(x+"       "+ z);
			if(r.getVue().estDisponible(new Coordonnees(x-1,z))){
				System.out.println("Coordonnee possible : ["+(x-1)+","+z+"]");
			}
			if(r.getVue().estDisponible(new Coordonnees(x,z-1))){
				System.out.println("Coordonnee possible : ["+x+","+(z-1)+"]");
			}
			while(!ok){
				i = sc2.nextInt();
				y = sc2.nextInt();
				if((i>=x-1&&i<=x)&&(y>=z-1 && y<=z)){
					ok = true;
				}
			}
			r.getVue().getPlateau().getGrille()[i][y].deplacerSur(r);
			r.setCoordonnees(new Coordonnees(i,y));
			r.setInvoque(true);
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
	public int choixActions(){
		sc3 = new Scanner(System.in);
		boolean saisieOK = false;
		int res = -1;
		System.out.println("Que voulez vous faire ce tour ci ?");
		
		if(!this.baseVide()){
			System.out.println(" 1-Invoquer un robot ?");
		}
		System.out.println("2-Deplacer un robot");
		System.out.println("3-Tirer avec un robot");
		while(!saisieOK){
			res = sc3.nextInt();
			if((this.baseVide() && res<4 && res>1)||(!this.baseVide() && res<4 && res>0)){
				saisieOK = true;
			}
		}
		return res;
	}
	
	public Robot choixRobot(){
		sc4 = new Scanner(System.in);
		ArrayList<Robot> robotInvoque = new ArrayList<>();
		int robot = -1;
		boolean saisieOK = false;
		
		for(Robot r : this.getListeRobot()){
			if(r.invoque){
				robotInvoque.add(r);
			}
		}
		System.out.print("Voici vos robots utilisable :  ");
		for(Robot r : robotInvoque){
			System.out.print("  "+r.toString());
		}
		System.out.println("Quel robot voulez vous utiliser ?");
		while(!saisieOK){
			robot = sc4.nextInt();
			if(robot>0 && robot <=robotInvoque.size()){
				saisieOK = true;
			}
		}
		return robotInvoque.get(robot-1);
 	}
}
