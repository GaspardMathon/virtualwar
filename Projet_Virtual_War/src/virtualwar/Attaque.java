package virtualwar;
/**
 * La classe Attaque qui hérite de la classe Action permet d'efectuer un tir d'un Tireur ou d'un char sur un autre robot
 * @author Clément
 *
 */
public class Attaque extends Action {

	/**
	 * Constructeur d'une Attaque
	 * @param robot le Robot qui attaque 
	 * @param direction les Coordonnées de cible du robot 
	 */
	public Attaque(Robot robot, Coordonnees direction){
		super(robot,direction);
	}

	public void agit(){
		robot.setEnergie(robot.getEnergie()-robot.getCoutAction());
		Robot cible = robot.getVue().getPlateau().getGrille()[direction.getHauteur()][direction.getLargeur()].getContenu();
		cible.setEnergie(cible.getEnergie()-cible.getDegatSubis());
		if(cible.estMort()){
			cible.getVue().getPlateau().getGrille()[cible.getCoordonnees().getHauteur()][cible.getCoordonnees().getLargeur()].videRobot();
		}
	}			
}

