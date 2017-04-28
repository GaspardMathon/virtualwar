package virtualwar;
/**
 * La classe Action est une classe abstraite qui d�fini un d�placement ou une attaque
 * @author Cl�ment
 *
 */
abstract class Action {
	
	protected Robot robot;
	protected Coordonnees direction;
	
	/**
	 * Constructeur d'une action
	 * @param robot le Robot qui va effectu� l'action
	 * @param direction les Coordonn�es vers lesquelles est dirig�e l'action
	 */
	public Action(Robot robot, Coordonnees direction){
		this.robot = robot;
		this.direction = direction;
	}
	
	/**
	 * Obtenir le robot qui effectue l'action
	 * @return le robot qui effectue l'action
	 */
	public Robot getRobot(){
		return this.robot;
	}
	
	/**
	 * Obtenir la direction de l'action
	 * @return les Coordonn�es cibl�e par l'action
	 */
	public Coordonnees getDirection(){
		return this.direction;
	}
	/**
	 * Effectue l'action, attaque la cible ou d�place le robot vers les coordonn�es cibl�es et d�pense l'�nergie du robot
	 */
	abstract void agit();
}