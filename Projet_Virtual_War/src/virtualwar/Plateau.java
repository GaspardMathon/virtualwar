package virtualwar;

import java.util.ArrayList;
import java.util.Random;

//le plateau est constitu� d'une hauteur, d'une largeur et d'une grille de cellule sur laquelle se d�roule la partie
public class Plateau {
	
	private int hauteur;
	private int largeur;
	private Cellule[][] grille;
	
	//Constructeur prenant deux entiers en param�tre, place deux bases oppos�es et des obstacles tout autour de la grille de jeu
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
	//Remplace la hauteur par un entier donn� en param�tre
	public void setHauteur(int i){
		this.hauteur = i;
	}
	//Remplace la largeur par un entier donn� en param�tre
	public void setLageur(int i){
		this.largeur = i;
	}
	//Remplace la grille par un tableau de Cellule donn� en param�tre
	public void setGrille(Cellule[][] grille){
		this.grille = grille;
	}
	
	/**
	 * Obtenir la liste des cellules sur lesquelles ont ne peut pas poser d'obstacle
	 * @return la listes des cellules bloqu�s
	 */
	public ArrayList<Cellule> casesBloquees(){
		ArrayList<Cellule> listeBloque = new ArrayList<>();
		listeBloque.add(this.getGrille()[1][2]);
		listeBloque.add(this.getGrille()[2][1]);
		listeBloque.add(this.getGrille()[2][2]);
		listeBloque.add(this.getGrille()[this.getHauteur()-1][this.getLargeur()]);
		listeBloque.add(this.getGrille()[this.getHauteur()][this.getLargeur()-1]);		
		listeBloque.add(this.getGrille()[this.getHauteur()-1][this.getLargeur()-1]);
		//ajouter le chemin
		listeBloque.addAll(this.caseChemin());
		return listeBloque;

	}
	
	/**
	 * PLace un nombre des obstacles de fa�ons al�atoire 
	 * @param chance INT pourcentage d'obstacle sur la carte
	 */
	public void setObstacles(int chance){
		Random r = new Random();
		int nbCase = this.getHauteur()*this.getLargeur() - this.casesBloquees().size();
		int nbObstacle = nbCase *chance/100;
		int cpt = nbObstacle;
		while(cpt>0){
			int x = r.nextInt(this.getHauteur()+1);
			int y = r.nextInt(this.getLargeur()+1);
			if(!this.getGrille()[x][y].getObstacle() && !this.casesBloquees().contains(this.getGrille()[x][y])){
				this.getGrille()[x][y].setObstacle(true);
				cpt--;
			}
		}
	}
	
	public String toString() {
 		String affichage = "";
 		
 		for(int i = 0; i < this.grille.length; i++){
 			for(int y = 0; y < this.grille[0].length; y++){
 				affichage+="+---";
 			}
 			affichage+="+\n";
 			for(int y = 0; y < this.grille[0].length; y++){
 				affichage+="| "+ this.getGrille()[i][y].toString() + " ";
 			}
 			affichage += "|\n";
 		}
 		for(int y = 0; y < this.grille[0].length; y++){
 			affichage+="+---";
 		}
 		return affichage+"+";
 	}
	
	/**
	 * Obtenir la liste des cellules pour avoir un chemin qui rejoins les 2 bases
	 * @return la listes des cellules Chemin
	 */
	public ArrayList<Cellule> caseChemin(){
		ArrayList<Cellule> listeChemin = new ArrayList<>();
		for(int i =0;i < this.largeur/2;i++){
			listeChemin.add(this.getGrille()[1][i]);
		}
		for(int j=0;j <this.hauteur;j++){
			listeChemin.add(this.getGrille()[j][this.largeur/2]);
		}
		for(int k = this.largeur/2; k<this.largeur;k++ ){
			listeChemin.add(this.getGrille()[this.getHauteur()][k]);
		}
		return listeChemin;
	}
}