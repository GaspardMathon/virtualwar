package virtualwar;
public class Coordonnees {
	/*			 (-1,0)
	 * 				|	
	 * 				|
	 * 				|
	 *(0,-1)--------+---------(0,1)
	 * 				|
	 * 				|
	 * 				|
	 * 			  (1,0)
	 */
	private int hauteur;
	private int largeur;
	
	public Coordonnees(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur= largeur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public String toString() {
		return "Coordonnees ["+this.hauteur+","+this.largeur+"]";
	}
	
	public Coordonnees ajout(Coordonnees c){
		return new Coordonnees(c.hauteur, c.largeur);
	}
	public Coordonnees modif(Coordonnees c){
		this.hauteur += c.hauteur; 
		this.largeur += c.largeur;
		return this;
	}
	public boolean equals(Coordonnees c){
		if(this.hauteur == c.getHauteur() && this.largeur == c.getLargeur()){
			return true;
		}
		else { return false;}
	}
	
}
