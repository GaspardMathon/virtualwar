package virtualwar;

import java.util.List;



/**
 *Classe abstraite qui d�fini un Robot en g�n�ral
 *@author Cl�ment
 */
abstract class Robot {
	
	private int energie;
	private int equipe;
	private Coordonnees coordonnees;
	private Vue vue;
	private String type;
	boolean invoque;
	
	/**
	 * Constructeur du robot
	 * @param vue vue du robot en fonction de son �quipe
	 * @param h  hauteur pour cr�er la coordonn�e du Robot
	 * @param l  largeur pour cr�er la coordonn�e du Robot
	 * @param equipe �quipe du robot 
	 * @param type type du Robot(tireur, pi�geur,char)
	 * @param energie du robot � sa cr�ation
	 */
	public Robot(Vue vue, int h, int l, int equipe,String type,int energie){
		this.vue = vue;
		this.equipe = equipe;
		this.coordonnees = new Coordonnees(h,l);
		this.type = type;
		this.energie = energie;
		this.invoque = false;
	}
	
	/**
	 * Obtenir l'�nergie du robot
	 * @return l'�nergie du robot
	 */
	public int getEnergie(){
		return this.energie;
	}
	
	/**
	 * Obtenir l'�quipe du robot
	 * @return l'�quipe du robot
	 */
	public int getEquipe(){
		return this.equipe;
	}
	
	/**
	 * Obtenir les coordonnes du Robot
	 * @return les coordonnees du Robot
	 */
	public Coordonnees getCoordonnees(){
		return this.coordonnees;
	}
	
	/**
	 * Obtenir la Vue du Robot
	 * @return la vue du robot
	 */
	public Vue getVue(){
		return this.vue;
	}
	
	/**
	 * Obtenir le Type du robot
	 * @return le Type du robot
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * dit si le Robot est invoqu�
	 * @return le "invoque" du robot
	 */
	public boolean getInvoque(){
		return this.invoque;
	}
	
	/**
	 * Remplace l'�nergie du robot
	 * @param i entier qui prend la place de l'�nergie actuelle
	 */
	public void setEnergie(int i){
		this.energie = i;
	}
	
	/**
	 * Remplace l'�quipe du robot
	 * @param i entier qui prend la place de l'�quipe actuelle
	 */
	public void setEquipe(int i){
		this.equipe = i;
	}
	
	/**
	 * Remplace les coordonn�es du robot
	 * @param c Coordonn�es qui prend la place des Coordonn�es actuelles
	 */
	public void setCoordonnees(Coordonnees c){
		this.coordonnees = c;
	}
	
	/**
	 * Remplace la vue du robot
	 * @param v vue qui prend la place de la vue actuelle
	 */
	public void setVue(Vue v){
		this.vue = v;
	}
	
	/**
	 * Remplace le type du robot
	 * @param s String qui prend la place du type actuel
	 */
	public void setType(String s){
		this.type = s;
	}
	
	/**
	 * remplace l'indication de l'invocation du robot
	 * @param b Boolean qui prend la place du "invoque" actuel
	 */
	public void setInvoque(boolean b){
		this.invoque = b;
	}

	/**
	 *Affiche l'�tat du robot : son type, ses coordonn�es et sa vie
	 *@return nom String qui retourne l'�tat du robot
	 */
	abstract public String toString();
	
	/**
	 * Permet de savoir si une coordonn�e fait partie des d�placement que le robot peut effectuer
	 * @param c Coordonn�es test�e
	 * @return un Boolean permettant de savoir si elle fait partie des d�placements
	 */
	public boolean estDans(Coordonnees c){
		boolean res = false;
		for(Coordonnees compt : this.getDeplacements()){
			if(compt.equals(c)){
				return true;
			}
		
		}
		return res;
	}
	
	public void mortDuRobot(){
		this.getVue().getPlateau().getGrille()[this.getCoordonnees().getHauteur()][this.getCoordonnees().getLargeur()].videRobot();
	}
	
	/**
	 * Obtenir le cout de l'action de ce robot
	 * @return le cout de l'action
	 */
	abstract public int getCoutAction();
	
	/**
	 * Obtenir le cout du d�placement de ce robot
	 * @return le cout du d�placement
	 */
	abstract public int getCoutDeplacement();
	
	/**
	 * Obtenir les d�gats que subit le robot apr�s une attaque
	 * @return le d�gats subits
	 */
	abstract public int getDegatSubis();
	
	/**
	 * deplace le robot
	 */
	public void deplacement(){
		boolean mouvement = false;
		Coordonnees c = new Coordonnees(-1,-1);
		if(this.getEnergie()< Constante.COUTDEPLACEMENT){
			System.out.println("Votre robot n'as pas assez d'energie pour se deplacer");
		}else{
			while(!mouvement){
				c = this.choixMouvement();
				Coordonnees test = new Coordonnees(this.getCoordonnees().getHauteur()+c.getHauteur(),this.getCoordonnees().getLargeur()+c.getLargeur());
				if(this.estDans(test)){
					mouvement = true;
				}
				
			}
			Deplacement d = new Deplacement(this,c);
			d.agit();
		}
	}
	
	/**
	 * fait attaquer le robot
	 */
	abstract public void attaque();
	
	/**
	 * Obtenir les deplacements effectuables par le robot
	 * @return la liste des coordonn�es pour lesquelles le robot peut se d�placer
	 */
	abstract public List<Coordonnees> getDeplacements();
	
	/**
	 * Obtenir l'�nergie initial du Robot
	 * @return l'�nergie de base
	 */
	abstract public int getEnergieDeBase();
	
	/**
	 * Demande à l'utilisateur la direction vers laquelle il souhaite déplacer le robot
	 * @return les Coordonnées vers lesquelles il veut aller
	 */
	abstract public Coordonnees choixMouvement();
	
	/**
	 * Obtenir la liste des cibles potentielles de l'attaque du robot
	 * @return une liste de Coordonnes qui sont les cibles
	 */
	abstract public List<Coordonnees> getCibles();
	
	/**
	 * Demande à l'utilisateur la cible sur laquelle il veut attaquer 
	 * @return les Coordonnée de la cible choisi
	 */
	abstract public Coordonnees choixCible();
	
	/**
     * Régénère l'énergie du robot si il est sur sa base
     */
    abstract public void regenEnergie();

	
}