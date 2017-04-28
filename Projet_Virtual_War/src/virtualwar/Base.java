package virtualwar;
//Une base est Cellule special qui permet d'invoquer les robot et de restaurer leur énergie
public class Base extends Cellule{
	
	//Constructeur par défault sans paramètre
	public Base(){};
	
	//Constructeur prenant en paramètre trois entiers pour les Coordonnées et l'équipe
	public Base(int hauteur, int largeur, int equipe){
		super(hauteur,largeur);
		super.base = equipe;
	}
	
	//ajoute un robot sur la base
	public void deplacerSur(Robot robot){
		super.robot = robot;
	}
	
	//restaure, au fil des tours, l'énergie du robot sur la Base
	public void regenEnergie(){
		if(this.getContientrobot()){
			if(this.getContenu().getEquipe() == this.getBase()){
				this.getContenu().setEnergie(this.getContenu().getEnergie()+2);
			}
		}
	}
	
	//vide la base et la transforme en cellue classique
	public void videCellule(){
		super.base = 0;
		super.mine = 0;
		super.robot = null;
	}

}


