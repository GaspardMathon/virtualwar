package virtualwar;
//la classe vue sert à l'affichage, elle a en paramètre le plateau et une équipe
public class Vue {
	
	private int equipe;
	private Plateau plateau;
	
	//Constructeur prenant en paramètre un plateau et une équipe
	public Vue(Plateau plateau, int equipe){
		this.plateau = plateau;
		this.equipe = equipe;
	}
	
	//retourne le plateau de jeu
	public Plateau getPlateau(){
		return this.plateau;
	}
	//retourne l'équipe de la vue
	public int getEquipe(){
		return this.equipe;
	}
	//Remplace le plateau par un plateau donné en paramètre
	public void setPlateau(Plateau p){
		this.plateau = p;
	}
	//Remplace l'équipe par un entier donné en paramètre
	public void setEquipe(int i){
		this.equipe = i;
	}
	
	//Renvois VRAI si la Cellule ne contient pas de Robot et n'est pas un obstacle sinon renvoit FAUX
	public boolean estDisponible(Coordonnees c){
		Cellule cel = this.plateau.getGrille()[c.getHauteur()][c.getLargeur()];
		if(!cel.obstacle  && !cel.contientRobot ){
			return true;
		}else{
			return false;
		}
	}
	
	//affiche le plateau en fonction de l'équipe(on ne voit pas les mines ennemies)
	public String toString() {
		String affichage = "";
		
		
		for(int i = 0; i < this.getPlateau().getGrille().length; i++){
			
			for(int y = 0; y < this.getPlateau().getGrille()[0].length; y++){
				affichage+="+---";
			}
			
			affichage+="+\n";
			
			for(int y = 0; y < this.getPlateau().getGrille()[0].length; y++){
				if(this.getEquipe() == this.getPlateau().getGrille()[i][y].getMine()){
					affichage += "| X ";
				}else{
					affichage+="| "+ this.getPlateau().getGrille()[i][y].toString() + " ";
				}
			}
			
			
			affichage += "|\n";
		}
		
		for(int y = 0; y < this.getPlateau().getGrille()[0].length; y++){
			affichage+="+---";
		}
		
		return affichage+"+";
	}
	
	
}