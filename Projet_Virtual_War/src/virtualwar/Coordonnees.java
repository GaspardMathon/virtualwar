package virtualwar;
//La classe coordonn�es permet de situer les differentes cellules du plateau et les differents robots
//Elle possede deux attributs entier : largeur et hauteur
public class Coordonnees {
	private int hauteur;
	private int largeur;
	
	//Unique constructeur prenant en param�tre deux entiers :  hauteur et largeur
	public Coordonnees(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	//retourne l'attribut hauteur de la Coordonn�e
	public int getHauteur(){
		return this.hauteur;
	}
	
	//retourne l'attribut largeur de la Coordonn�
	public int getLargeur(){
		return this.largeur;
	}
	
	//remplace la hauteur de la Coordonn�es par un entier donn� en param�tre
	public void setHauteur(int i){
		this.hauteur = i;
	}
	
	//remplace la largeur de la Coordonn�es par un entier donn� en param�tre
	public void setLargeur(int i){
		this.largeur = i;
	}
	
	//retourne VRAI si la coordonn�e est �gale � celle donn�e en param�tre
	public boolean equals(Coordonnees c){
		if(this.hauteur == c.getHauteur() && this.largeur == c.getLargeur()){
			return true;
		}
		else { return false;}
	}
	
	//ajoute la coordonn�es actuelle � une autre coordonn�e donn�e en param�tre
	public Coordonnees ajoute(Coordonnees c){
		this.hauteur += c.hauteur; 
		this.largeur += c.largeur;
		return this;
	}
	
	//renvoit une chaine de caract�re repr�sentant la coordonn�e
	public String toString(){
		return "["+this.getHauteur()+","+this.getLargeur()+"]";
	}
}