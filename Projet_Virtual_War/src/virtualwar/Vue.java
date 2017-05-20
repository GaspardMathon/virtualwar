package virtualwar;
//la classe vue sert à  l'affichage, elle a en paramêtre le plateau et une équipe
public class Vue {
	
	private int equipe;
	private Plateau plateau;
	
	//Constructeur prenant en paramêtre un plateau et une équipe
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
	//Remplace le plateau par un plateau donné en paramêtre
	public void setPlateau(Plateau p){
		this.plateau = p;
	}
	//Remplace l'équipe par un entier donné en paramêtre
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
		int y = 0;
		String affichage = "    ";
		
		for(int l = 0; l <= (this.getPlateau().getGrille()[0].length)-1; l++){
			if(l<9){
				affichage+="  " + l + " ";
			}else{
				affichage+="  " + l;
			}
			
		}
		
		affichage+="\n";
		
		for(int i = 0; i < this.getPlateau().getGrille().length; i++){
			affichage+="    ";
			for(int k = 0; k < this.getPlateau().getGrille()[0].length; k++){
				affichage+="+---";
			}
			if(y<10){
				affichage+="+\n " + y + "  ";
				y++;
			}else{
				affichage+="+\n " + y + " ";
				y++;
			}
			
			for(int z = 0; z < this.getPlateau().getGrille()[0].length; z++){
				if(this.getEquipe() == this.getPlateau().getGrille()[i][z].getMine()){
					affichage += "| X ";
				}else{
					affichage+= "| "+ this.getPlateau().getGrille()[i][z].toString() + " ";
				}
			}
			
			
			affichage += "|\n";
		}
		
		affichage+="    ";
		for(int f = 0; f < this.getPlateau().getGrille()[0].length; f++){
			affichage+="+---";
		}
		
		return affichage+"+";
	}
	
	
}