package graphique;

public class Menu1 {
	int nBRobots;
	int hauteur;
	int largeur;
	String nom1;
	String nom2;
	
	public Menu1(int nBRobots, int hauteur, int largeur, String nom1, String nom2) {
		this.nBRobots = nBRobots;
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.nom1 = nom1;
		this.nom2 = nom2;
	}
	public int getnBRobots() {
		return nBRobots;
	}
	public int getHauteur() {
		return hauteur;
	}
	public int getLargeur() {
		return largeur;
	}
	public String getNom1() {
		return nom1;
	}
	public String getNom2() {
		return nom2;
	}
	public void setnBRobots(int nBRobots) {
		this.nBRobots = nBRobots;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
	public void setNom1(String nom1) {
		this.nom1 = nom1;
	}
	public void setNom2(String nom2) {
		this.nom2 = nom2;
	}
	
	public String toString() {
		return "Données de jeu :  [nBRobots=" + nBRobots + ", hauteur=" + hauteur + ", largeur=" + largeur + ", nom1=" + nom1
				+ ", nom2=" + nom2 + "]";
	}
	
	
	
}
