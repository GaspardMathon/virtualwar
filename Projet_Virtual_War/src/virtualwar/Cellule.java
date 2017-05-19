package virtualwar;

//la classe arbstraite Cellule permet de repr�senter les diff�rentes cellules du plateau
abstract public class Cellule {
	
	protected int mine;
	protected int base;
	protected Coordonnees coordonne;
	protected boolean obstacle;
	boolean contientRobot;
	protected Robot robot;

	//Constructeur par d�fault sans param�tre
	public Cellule(){};
	
	//Constructeur prenant deux entiers initialisant les Coordonn�es
	public Cellule(int hauteur,int largeur){
		this.coordonne = new Coordonnees(hauteur,largeur);
		this.obstacle = false;
		this.contientRobot = false;
		this.mine = 0;
	}
	
	//Constructeur prenant en param�tre deux entiers pour les Coordonn�es et deux booleans pour obstacle et contientRobot
	public Cellule(int hauteur, int largeur, boolean obstacle, boolean cr){
		this.coordonne = new Coordonnees(hauteur,largeur);
		this.obstacle = obstacle;
		this.contientRobot = cr;
		this.mine = 0;
	}
	
	//Retourne le numero de l'�quipe si la Cellule est min�e sinon retourne z�ro
	public int getMine(){
		return this.mine;
	}
	
	//Retourne le num�ro de l'�quipe si la Cellule est une base sinon retourne z�ro
	public int getBase(){
		return this.base;
	}
	//Retourne VRAI si la Cellule est un obstacle, sinon retourne faux
	public boolean getObstacle(){
		return this.obstacle;
	}
	
	//retourne VRAI si la Cellule contient un robot,sinon retourne faux 
	public boolean getContientrobot(){
		return this.contientRobot;
	}
	
	//Retourne le contenu de la Cellule
	public Robot getContenu(){
		return this.robot;
	}
	
	//Donne � l'attribut mine un entier correspondant � l'�quipe de la mine
	public void setMine(int i){
		this.mine = i;
	}
	
	//Remplace la valeure de l'attribut Base par un entier donn� en param�tre
	public void setBase(int i){
		this.base = i;
	}
	//Remplace la valeure de l'attribut Obstacle par un boolean donn� en param�tre
	public void setObstacle(boolean b){
		this.obstacle = b;
	}
	
	//Remplace la valeur de l'attribut Contientrobot par un boolean donn� en param�tre
	public void setContientrobot(boolean b){
		this.contientRobot = b;
	}
	
	//Remplace la valeur de l'attribut Robot par un Robot donn� en param�tre
	public void setRobot(Robot r){
		this.robot = r;
	}
	
	//Enl�ve le robot pr�sent sur la case
	public void videRobot(){
		this.robot = null;
		this.contientRobot = false;
	}
	
	//deplace un robot sur cette cellule
	public abstract void deplacerSur(Robot robot);
	
	//vide completement la cellule 
	abstract void videCellule();
	
	//régénère l'énergie du robot si il est sur sa base
	public void regenEnergieBase(){
		if(this.getContientrobot()){
			if(this.getContenu().getEquipe() == this.getBase()){ 
				robot.regenEnergie();

			} 
		} 
	}
	
	//Retourne l'�tat de la Cellule sous forme de chaine de carat�res
	public String toString(){
		if(this.base != 0){
			return "B";
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


