package virtualwar;
//Une base est Cellule special qui permet d'invoquer les robot et de restaurer leur �nergie
public class Base extends Cellule{
	
	//Constructeur par d�fault sans param�tre
	public Base(){};
	
	//Constructeur prenant en param�tre trois entiers pour les Coordonn�es et l'�quipe
	public Base(int hauteur, int largeur, int equipe){
		super(hauteur,largeur);
		super.base = equipe;
	}
	
	//ajoute un robot sur la base
	public void deplacerSur(Robot robot){
		super.robot = robot;
		super.contientRobot = true;
	}
	
	
	//vide la base et la transforme en cellue classique
	public void videCellule(){
		super.base = 0;
		super.mine = 0;
		super.robot = null;
	}

}


