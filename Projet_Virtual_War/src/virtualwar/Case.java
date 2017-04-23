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
		//ajoute un robot a la case un Robot apres s'etre deplace sur celle ci, inflige des degat au robot si il marche sur une mine enemi
		public void deplacerSur(Robot robot){
			super.robot = robot;
			super.contientRobot = true;
			if(this.mine != 0 && this.mine != robot.getEquipe()){
				robot.setEnergie(robot.getEnergie()-Constante.DEGATMINE);
			}
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
			super.obstacle = false;
		}
		
		//restaure, au fil des tours, l'énergie du robot sur la Case
		public void regenEnergie(){
			if(this.getContientRobot()){
				this.getRobot().setEnergie(this.getRobot().getEnergie()+2);
			}
		}
	
}