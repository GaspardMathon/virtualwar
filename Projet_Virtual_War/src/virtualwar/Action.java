package virtualwar;
/**
 * La classe Action est une classe abstraite qui défini un déplacement ou une attaque
 * @author Clément
 *
 */
abstract class Action {
	
	protected Robot robot;
	protected Coordonnees direction;
	
	/**
	 * Constructeur d'une action
	 * @param robot le Robot qui va effectué l'action
	 * @param direction les Coordonnées vers lesquelles est dirigée l'action
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
	 * @return les Coordonnées ciblée par l'action
	 */
	public Coordonnees getDirection(){
		return this.direction;
	}
	/**
	 * Effectue l'action, attaque la cible ou déplace le robot vers les coordonnées ciblées et dépense l'énergie du robot
	 */
	abstract void agit();
}