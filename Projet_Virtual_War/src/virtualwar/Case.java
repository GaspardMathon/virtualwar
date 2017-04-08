package virtualwar;

public class Case extends Cellule {
	
	//Construteur par defaut
		public Case(){};
		
		//Constructeur prenant deux entiers pour Coordonnees appelant le constructeur de Cellule
		public Case(int hauteur,int largeur){
			super(hauteur,largeur);
			
		}
		//Constructeur prenant deux entier et un boolean pour savoir si il y a un obstacle
		public Case(int hauteur,int largeur,boolean obstacle){
			super(hauteur,largeur,obstacle,false);
		}
		//ajoute un robot a la case une apres s'etre deplace sur celle ci
		public void deplacerSur(Robot robot){
			super.robot = robot;
			super.contientRobot = true;
		}
		
		//ajoute une base d'une equipe donne 
		public void ajoute(int equipe){
			this.base = equipe;
		}
		
		//vide le contenu de la case
		public void videCase(){
			super.base = 0;
			super.mine = 0;
			super.robot = null;
			super.image = null;
			super.obstacle = false;
		}

	
}