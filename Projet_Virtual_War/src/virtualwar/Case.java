package virtualwar;
//Une Case est une cellule qui peut �tre un obstacle ou non, les robots se d�placent sur les cases

public class Case extends Cellule {
	
	//Construteur par defaut
		public Case(){};
		
		//Constructeur prenant deux entiers pour Coordonnees appelant le constructeur de Cellule
		public Case(int hauteur,int largeur){
			super(hauteur,largeur);
			
		}
		//Constructeur prenant deux entiers et un boolean pour savoir s'il y a un obstacle
		public Case(int hauteur,int largeur,boolean obstacle){
			super(hauteur,largeur,obstacle,false);
		}		

		//ajoute un robot � la case un Robot apr�s s'�tre d�place sur celle-ci, inflige des d�gats au robot s'il marche sur une mine ennemie
		public void deplacerSur(Robot robot){
			super.robot = robot;
			super.contientRobot = true;
			if(this.mine != 0 ){
				robot.setEnergie(robot.getEnergie()-Constante.DEGATMINE);
			}
		}

		//vide le contenu de la case
		public void videCellule(){
			super.base = 0;
			super.mine = 0;
			super.robot = null;
			super.obstacle = false;
		}
		
		//Restaure, au fil des tours, l'�nergie du robot sur sa Case
		public void regenEnergie(){
			if(this.getContientrobot()){
				if(this.getContenu().getEquipe() == this.getBase()){
					this.getContenu().setEnergie(this.getContenu().getEnergie()+2);
				}
			}
		}
		
	
}