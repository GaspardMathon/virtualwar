package virtualwar;

public class Deplacement extends Action{
	
	
	public Deplacement(Robot robot, Coordonnees direction){
		super(robot,direction);
	}
	
	public void setDeplacement(Robot robot, Coordonnees direction){
		this.robot = robot;
		this.direction = new Coordonnees(direction.getHauteur(),direction.getLargeur());
	}
	public void agit(){
		Coordonnees coordActuel = new Coordonnees(robot.getCoordonnees().getHauteur(),robot.getCoordonnees().getLargeur());
		robot.setCoordonnees(this.direction.modif(robot.getCoordonnees()));
		//On vide la case du robot
		robot.getVue().getPlateau().getGrille()[coordActuel.getHauteur()][coordActuel.getLargeur()].videRobot();
		//On le deplace sur sa nouvelle position
		robot.getVue().getPlateau().getGrille()[robot.getCoordonnees().getHauteur()][robot.getCoordonnees().getLargeur()].deplacerSur(robot);
		robot.setEnergie(robot.getEnergie()-Constante.COUTDEPLACEMENT);
	}
	
	public Coordonnees getDirection(){
		return this.direction;
	}
	

	public String toString() {
		return "Deplacement [robot=" + robot + ", direction=" + direction + "]";
	}
	

	
}
