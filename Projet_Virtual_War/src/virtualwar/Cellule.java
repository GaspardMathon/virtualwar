package virtualwar;

//la classe arbstraite Cellule permet de représenter les différentes cellules du plateau
abstract public class Cellule {
	
	protected int mine;
	protected int base;
	protected Coordonnees coordonne;
	protected boolean obstacle;
	protected boolean contientRobot;
	protected Robot robot;

	//Constructeur par défault sans paramètre
	public Cellule(){};
	
	//Constructeur prenant deux entiers initialisant les Coordonnées
	public Cellule(int hauteur,int largeur){
		this.coordonne = new Coordonnees(hauteur,largeur);
		this.obstacle = false;
		this.contientRobot = false;
		this.mine = 0;
	}
	
	//Constructeur prenant en paramètre deux entiers pour les Coordonnées et deux booleans pour obstacle et contientRobot
	public Cellule(int hauteur, int largeur, boolean obstacle, boolean cr){
		this.coordonne = new Coordonnees(hauteur,largeur);
		this.obstacle = obstacle;
		this.contientRobot = cr;
		this.mine = 0;
	}
	
	//Retourne le numero de l'équipe si la Cellule est minée sinon retourne zéro
	public int getMine(){
		return this.mine;
	}
	
	//Retourne le numéro de l'équipe si la Cellule est une base sinon retourne zéro
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
	
	//Donne à l'attribut mine un entier correspondant à l'équipe de la mine
	public void setMine(int i){
		this.mine = i;
	}
	
	//Remplace la valeure de l'attribut Base par un entier donné en paramètre
	public void setBase(int i){
		this.base = i;
	}
	//Remplace la valeure de l'attribut Obstacle par un boolean donné en paramètre
	public void setObstacle(boolean b){
		this.obstacle = b;
	}
	
	//Remplace la valeur de l'attribut Contientrobot par un boolean donné en paramètre
	public void setContientrobot(boolean b){
		this.contientRobot = b;
	}
	
	//Remplace la valeur de l'attribut Robot par un Robot donné en paramètre
	public void setRobot(Robot r){
		this.robot = r;
	}
	
	//Enlève le robot présent sur la case
	public void videRobot(){
		this.robot = null;
		this.contientRobot = false;
	}
	
	//deplace un robot sur cette cellule
	abstract void deplacerSur(Robot robot);
	
	//vide completement la cellule 
	abstract void videCellule();
	
	//restaure, au fil des tours, l'énergie du robot sur la Cellule
	abstract void regenEnergie();
	
	//Retourne l'état de la Cellule sous forme de chaine de caratères
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