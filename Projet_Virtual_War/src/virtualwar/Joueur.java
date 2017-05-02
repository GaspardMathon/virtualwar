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
	private static Scanner sc5;
	
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
		listeRobot.add(new Tireur(vue,h,l,equipe,1));
	}
	public void ajouterPiegeur(Vue vue,int h,int l){
		listeRobot.add(new Piegeur(vue,h,l,equipe));
	}
	public void ajouterChar(Vue vue,int h,int l){
		listeRobot.add(new Char(vue,h,l,equipe,2));
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
	
	public Robot choisirInvocation(){
		boolean saisieOK = false;
		int robot = -1;
		System.out.print("Vos robots dans votre base : ");
		ArrayList<Robot> robotInvocable = new ArrayList<>();
		sc4 = new Scanner(System.in);
		for(Robot r : this.getListeRobot()){
			if(!r.getInvoque()){
				robotInvocable.add(r);
			}
		}
		for(Robot r : robotInvocable){
			System.out.print("  " + r.toString());
		}
		System.out.println(" \n Quel  robot voulez vous invoquer : �crivez le numero du n-ieme robot");
		while(!saisieOK){
			robot = Constante.saisieIntProtegee(sc4);
			if(robot>0 && robot <=robotInvocable.size()){
				saisieOK = true;
			}
		}
		return robotInvocable.get(robot-1);
	}
	//a finir pour coordener deja prise
	public void invoqueRobot(Robot r){
		sc3 = new Scanner(System.in);
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
			if(r.getVue().estDisponible(new Coordonnees(2,2))){
				System.out.println("Coordonnee possible : [2,2]");
			}
			while(!ok){
				 i = Constante.saisieIntProtegee(sc3);
				 y = Constante.saisieIntProtegee(sc3);
				if((i>=1 && i<=2)&&(y>=1 && y<=2)&& !(i==1 && y==1)){
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
			if(r.getVue().estDisponible(new Coordonnees(x-1,z-1))){
				System.out.println("Coordonnee possible : ["+(x-1)+","+(z-1)+"]");
			}
			while(!ok){
				System.out.println("Entrez les coordonn�es : ");
				i = Constante.saisieIntProtegee(sc3);
				y = Constante.saisieIntProtegee(sc3);
				if((i>=x-1&&i<=x)&&(y>=z-1 && y<=z) && !(i ==x && y == z)){
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
	public boolean plateauVide(){
		boolean plateauVide = true;
		
		for(Robot r : this.getListeRobot()){
			if(r.getInvoque()){
				plateauVide = false;
			}
		}
		return plateauVide;
	}
	
	public int choixActions(){
		sc = new Scanner(System.in);
		boolean saisieOK = false;
		int res = -1;
		System.out.println("Que voulez vous faire ce tour ci ?");
		
		if(!this.baseVide()){
			System.out.println(" 1-Invoquer un robot ?");
		}
		if(!this.plateauVide()){
			System.out.println("2-Deplacer un robot");
			System.out.println("3-Attaquer/Miner avec un robot");
		}
		while(!saisieOK){
			res = Constante.saisieIntProtegee(sc);
			if((this.baseVide() && res<4 && res>1)||(!this.baseVide() && res<4 && res>0)){
				saisieOK = true;
			}
		}
		return res;
	}
	
	public Robot choixRobot(){
		sc2 = new Scanner(System.in);
		ArrayList<Robot> robotInvoque = new ArrayList<>();
		int robot = -1;
		boolean saisieOK = false;
		
		for(Robot r : this.getListeRobot()){
			if(r.invoque){
				robotInvoque.add(r);
			}
		}
		System.out.print("Voici vos robots utilisables :  ");
		for(Robot r : robotInvoque){
			System.out.print("  "+r.toString());
		}
		System.out.println("  Quel robot voulez vous utiliser ?");
		while(!saisieOK){
			robot = Constante.saisieIntProtegee(sc2);
			if(robot>0 && robot <=robotInvoque.size()){
				saisieOK = true;
			}
		}
		return robotInvoque.get(robot-1);
 	}
	
	public static String saisiePseudo(){
		sc5 = new Scanner(System.in);
		String nom ="";
		nom = sc5.next();
		boolean valide = false;
		while(!valide){
            if(nom.matches("^[a-zA-Z]*$")){
                valide=true;
            }else{
            	System.out.println("Saisie invalide, veulliez recommencer ");
            	nom = sc5.next();
            }
		}
		return nom;
	}
	
	/**
	 * Renvoie un string contenant le nom et l'quipe du joueur
	 */
	public String toString(){
    	return "" + this.nom + " (equipe n°" + this.equipe + ") ";
	}
	
	public void robotMort(){
		for(Robot r : this.getListeRobot()){
			if(r.getEnergie() <= 0){
				r.mortDuRobot();
				this.getListeRobot().remove(r);	
			}
		}
	}
}
