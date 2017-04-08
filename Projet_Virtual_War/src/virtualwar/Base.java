package virtualwar;
public class Base extends Cellule{
private int equipe;
	
	public int getEquipe(){
		return this.equipe;
	}
	
	public void setEquipe(int e){
		this.equipe = e;
	}
	//Constructeurs
	public Base(){};
	public Base(int hauteur, int largeur, int equipe){
		super(hauteur,largeur);
		this.equipe = equipe;
		this.base = equipe;
	}
	//ajoute un robot sur la base
	public void deplacerSur(Robot robot){
		super.robot = robot;
	}

	//
	public void ajoute(int equipe){
		super.base = equipe;
	}
	
	//vide la case
	public void videCase(){
			super.base = 0;
			super.mine = 0;
			super.robot = null;
			super.image = null;
		}
	

}

