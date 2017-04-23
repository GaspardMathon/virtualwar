package virtualwar;
public class Attaque extends Action{

	
	public Attaque(Robot robot, Coordonnees direction){
		super(robot,direction);
	}
	
	public void agit(){
		robot.setEnergie(robot.getEnergie()-robot.getCoutAction());
		Robot cible = robot.getVue().getPlateau().getGrille()[direction.getHauteur()][direction.getLargeur()].getRobot();
		cible.setEnergie(cible.getEnergie()-cible.getDegatsSubis());
		if(cible.getEnergie()<0){
			cible.getVue().getPlateau().getGrille()[cible.getCoordonnees().getHauteur()][cible.getCoordonnees().getLargeur()].videRobot();
		}			
	}
}
