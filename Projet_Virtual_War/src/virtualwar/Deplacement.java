package virtualwar;

/**
 * La classe Déplacement qui hérite de la classe action permet de faire déplacer un robot
 * @author Clément
 *
 */
public class Deplacement extends Action {
	
	/**
	 * Constructeur d'un déplacement
	 * @param robot Robot qui effetue le déplacement
	 * @param direction Coordonnées sur lesquelles il se déplace
	 */
	public Deplacement(Robot robot, Coordonnees direction){
		super(robot,direction);
	}
	
	public void agit(){
		Coordonnees coordActuel = new Coordonnees(robot.getCoordonnees().getHauteur(),robot.getCoordonnees().getLargeur());
		robot.setCoordonnees(this.direction.ajoute(robot.getCoordonnees()));
		robot.getVue().getPlateau().getGrille()[coordActuel.getHauteur()][coordActuel.getLargeur()].videRobot();
		robot.getVue().getPlateau().getGrille()[robot.getCoordonnees().getHauteur()][robot.getCoordonnees().getLargeur()].deplacerSur(robot);
		robot.setEnergie(robot.getEnergie()-Constante.COUTDEPLACEMENT);
	}
}