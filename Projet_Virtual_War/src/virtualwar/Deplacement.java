package virtualwar;

/**
 * La classe D�placement qui h�rite de la classe action permet de faire d�placer un robot
 * @author Cl�ment
 *
 */
public class Deplacement extends Action {
	
	/**
	 * Constructeur d'un d�placement
	 * @param robot Robot qui effetue le d�placement
	 * @param direction Coordonn�es sur lesquelles il se d�place
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