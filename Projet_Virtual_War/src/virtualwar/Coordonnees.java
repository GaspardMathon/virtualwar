package virtualwar;
//La classe coordonnées permet de situer les differentes cellules du plateau et les differents robots
//Elle possede deux attributs entier : largeur et hauteur
public class Coordonnees {
	private int hauteur;
	private int largeur;
	
	//Unique constructeur prenant en paramêtre deux entiers :  hauteur et largeur
	public Coordonnees(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	//retourne l'attribut hauteur de la coordonnée
	public int getHauteur(){
		return this.hauteur;
	}
	
	//retourne l'attribut largeur de la coordonnée
	public int getLargeur(){
		return this.largeur;
	}
	
	//remplace la hauteur de la coordonnées par un entier donné en paramêtre
	public void setHauteur(int i){
		this.hauteur = i;
	}
	
	//remplace la largeur de la coordonnées par un entier donné en paramêtre
	public void setLargeur(int i){
		this.largeur = i;
	}
	
	//retourne VRAI si la coordonnée est égale à celle donnée en paramêtre
	public boolean equals(Coordonnees c){
		if(this.hauteur == c.getHauteur() && this.largeur == c.getLargeur()){
			return true;
		}
		else { return false;}
	}
	
	//ajoute la coordonnées actuelle à une autre coordonnée donnée en paramêtre
	public Coordonnees ajoute(Coordonnees c){
		this.hauteur += c.hauteur; 
		this.largeur += c.largeur;
		return this;
	}
	
	//renvoit une chaine de caractère représentant la coordonnée
	public String toString(){
		return "["+this.getHauteur()+","+this.getLargeur()+"]";
	}
}