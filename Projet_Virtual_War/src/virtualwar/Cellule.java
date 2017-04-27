package virtualwar;

abstract public class Cellule {
	
	protected int mine = 0;
	protected int base = 0;
	protected String image;
	protected Coordonnees coordonne;
	protected Robot robot;
	protected boolean obstacle;
	protected boolean contientRobot;
	
	//Constructeur prenant en plus un boolean : obstacle
	public Cellule(int hauteur, int largeur, boolean obstacle, boolean cr){
		this.coordonne = new Coordonnees(hauteur,largeur);
		this.obstacle = obstacle;
		this.contientRobot = cr;
	}
	
	public boolean getContientRobot(){
		return this.contientRobot;
	}
	public Robot getRobot(){
		return this.robot;
	}
	
	
	//Constructeur par d�fault
	public Cellule(){};
	
	//Construteur prenant en compte deux entier servant de Coordonnes
	public Cellule(int hauteur,int largeur){
		this.coordonne = new Coordonnees(hauteur,largeur);
	}
	//Dit si la case est mine, si minee: renvoie l'�quipe
	public int contientMine(){
		return this.mine;
	}
	//Dit si la case est une base, si oui : renvoie la base 
	public int estBase(){
		return this.base;
	}
	//Retourne les coordonn�es de la Cellule
	public Coordonnees getCoordonnees(){
		return this.coordonne;
	}
	//Retounre le robot de la case
	public Robot getContenu(){
		return this.robot;
	}
	
	public void setObstacle(boolean b){
		this.obstacle = b;
	}
	
	public boolean getObstacle(){
		return this.obstacle;
	}
	
	
	
	//Retourne l'etat de la case sous forme de chaine de caratere **ATTENTION A FINIR AVEC LE ROBOT ! NEED LA CLASSE ROBOT**
	public String toString(){
		if(this.base != 0){
			return "B";
		}else{
			if(this.mine != 0 ){
				return "M";
			}else{
				if(this.obstacle){
					return "O";
				}else{
					if(!(this.robot == null)){
						return this.robot.getType();
					}else{
							return " ";
					}
				}
			}
		}
	}
	


	//Methode abstracte voir Base et case
	abstract void deplacerSur(Robot robot);
	abstract void ajoute(int equipe);
	abstract void videCase();
	abstract void regenEnergie();
	public void videRobot(){
		this.robot = null;
		this.contientRobot = false;
	}
}