package virtualwar;

import java.util.List;



/**
 *Classe abstraite qui défini un Robot en général
 *@author Clément
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
	 * @param vue vue du robot en fonction de son équipe
	 * @param h  hauteur pour créer la coordonnée du Robot
	 * @param l  largeur pour créer la coordonnée du Robot
	 * @param equipe équipe du robot 
	 * @param type type du Robot(tireur, piègeur,char)
	 * @param energie du robot à sa création
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
	 * Obtenir l'énergie du robot
	 * @return l'énergie du robot
	 */
	public int getEnergie(){
		return this.energie;
	}
	
	/**
	 * Obtenir l'équipe du robot
	 * @return l'équipe du robot
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
	 * Dit si le Robot est invoqué
	 * @return le "invoque" du robot
	 */
	public boolean getInvoque(){
		return this.invoque;
	}
	
	/**
	 * Remplace l'énergie du robot
	 * @param i entier qui prend la place de l'énergie actuelle
	 */
	public void setEnergie(int i){
		this.energie = i;
	}
	
	/**
	 * Remplace l'équipe du robot
	 * @param i entier qui prend la place de l'équipe actuelle
	 */
	public void setEquipe(int i){
		this.equipe = i;
	}
	
	/**
	 * Remplace les coordonnées du robot
	 * @param c Coordonnées qui prend la place des Coordonnées actuelles
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
	 *Affiche l'état du robot : son type, ses coordonnées et sa vie
	 *@return nom String qui retourne l'état du robot
	 */
	abstract public String toString();
	
	/**
	 * Permet de savoir si une coordonnée fait partie des déplacement que le robot peut effectuer
	 * @param c Coordonnées testées
	 * @return un Boolean permettant de savoir si elle fait partie des déplacements
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
	
	public boolean estMort(){
		if(this.getEnergie()<=0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Obtenir le cout de l'action de ce robot
	 * @return le cout de l'action
	 */
	abstract public int getCoutAction();
	
	/**
	 * Obtenir le cout du déplacement de ce robot
	 * @return le cout du déplacement
	 */
	abstract public int getCoutDeplacement();
	
	/**
	 * Obtenir les dégats que subit le robot aprés une attaque
	 * @return le dégats subits
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
	 * @return la liste des coordonnées pour lesquelles le robot peut se déplacer
	 */
	abstract public List<Coordonnees> getDeplacements();
	
	/**
	 * Obtenir l'énergie initial du Robot
	 * @return l'énergie de base
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