package virtualwar;
public class Vue {
	private int equipe;
	private Plateau plateau;
	
	public int getEquipe(){
		return this.equipe;
	}
	
	public Vue(Plateau plateau, int equipe){
		this.plateau = plateau;
		this.equipe = equipe;
	}
	
	public Plateau getPlateau(){
		return this.plateau;
	}
	
	public void setRobot(Robot robot, Coordonnees c){
		
	}
	
	public void videCase(Robot robot, Coordonnees c){
		
	}
	
	public void ajoute(Coordonnees c, int equipe){
		
	}
	
	public void subitTir(Coordonnees c){
		
	}
	
	public boolean estDisponible(Coordonnees c){
		Cellule cel = this.plateau.getGrille()[c.getHauteur()][c.getLargeur()];
		if(!cel.obstacle  && !cel.contientRobot ){
			return true;
			
		}else{
			return false;
		}
	}
	
	/*public int getContenu(Coordonnees c){
		
	}
	
	public boolean estBase(Robot robot){
		
	}
	
	public boolean estOK(Coordonnees c){
		
	}*/
	
}