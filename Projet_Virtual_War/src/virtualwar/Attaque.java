package virtualwar;
public class Attaque extends Action{
	private Robot robot;
	private Coordonnees visee;
	
	public Attaque(Robot robot, Coordonnees visee){
		super(robot, visee);
	}
	
	public void agit(){
		robot.setEnergie(robot.getEnergie()-robot.getCoutAction());
		Robot cible = robot.getVue().getPlateau().getGrille()[visee.getHauteur()][visee.getLargeur()].getRobot();
		cible.setEnergie(cible.getEnergie()-cible.getDegatsSubis());
		if(cible.getEnergie()<0){
			cible.getVue().getPlateau().getGrille()[cible.getCoordonnees().getHauteur()][cible.getCoordonnees().getLargeur()].videRobot();
		}			
	}
}