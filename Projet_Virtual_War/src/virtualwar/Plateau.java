package virtualwar;

import java.util.ArrayList;
import java.util.Random;

//le plateau est constitué d'une hauteur, d'une largeur et d'une grille de cellule sur laquelle se déroule la partie
public class Plateau {
	
	private int hauteur;
	private int largeur;
	private Cellule[][] grille;
	
	//Constructeur prenant deux entiers en paramètre, place deux bases opposées et des obstacles tout autour de la grille de jeu
	public Plateau(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.grille = new Cellule[hauteur+2][largeur+2];
		for(int i = 0; i < this.grille.length; i++){
			for(int y = 0; y < this.grille[0].length; y++){
				if((i==0)||(i == hauteur+1) || (y == 0) || (y == largeur+1)){
					grille[i][y] = new Case(i,y,true);
				}else{
					if(i==1 && y == 1){
						grille[i][y] = new Base(i,y,1);
					}else{
						if(i == hauteur && y == largeur){
							grille[i][y] = new Base(i,y,2);
						}else{
							grille[i][y] = new Case(i,y,false);
						}
					}
				}
			}
		}
	}
	
	//Retourne la hauteur du plateau
	public int getHauteur(){
		return this.hauteur;
	}
	//Retourne la largeur du plateau.
	public int getLargeur(){
		return this.largeur;
	}
	//Retourne la grille de Cellule du PLateau
	public Cellule[][] getGrille(){
		return this.grille;
	}
	//Remplace la hauteur par un entier donné en paramètre
	public void setHauteur(int i){
		this.hauteur = i;
	}
	//Remplace la largeur par un entier donné en paramètre
	public void setLageur(int i){
		this.largeur = i;
	}
	//Remplace la grille par un tableau de Cellule donné en paramètre
	public void setGrille(Cellule[][] grille){
		this.grille = grille;
	}
	
	/**
	 * Obtenir la liste des cellules sur lesquelles ont ne peut pas poser d'obstacle
	 * @return la listes des cellules bloqués
	 */
	public ArrayList<Cellule> caseBloque(){
		ArrayList<Cellule> listeBloque = new ArrayList<>();
		listeBloque.add(this.getGrille()[1][2]);
		listeBloque.add(this.getGrille()[2][1]);
		listeBloque.add(this.getGrille()[2][2]);
		listeBloque.add(this.getGrille()[this.getHauteur()-1][this.getLargeur()]);
		listeBloque.add(this.getGrille()[this.getHauteur()][this.getLargeur()-1]);		
		listeBloque.add(this.getGrille()[this.getHauteur()-1][this.getLargeur()-1]);
		//ajouter le chemin
		
		return listeBloque;

	}
	
	/**
	 * PLace un nombre des obstacles de façons aléatoire 
	 * @param chance INT pourcentage d'obstacle sur la carte
	 */
	public void setObstacles(int chance){
		Random r = new Random();
		int nbCase = this.getHauteur()*this.getLargeur() - this.caseBloque().size();
		int nbObstacle = nbCase *chance/100;
		int cpt = nbObstacle;
		while(cpt>0){
			int x = r.nextInt(this.getHauteur()+1);
			int y = r.nextInt(this.getLargeur()+1);
			if(!this.getGrille()[x][y].getObstacle() && !this.caseBloque().contains(this.getGrille()[x][y])){
				this.getGrille()[x][y].setObstacle(true);
				cpt--;
			}
		}
	}
}