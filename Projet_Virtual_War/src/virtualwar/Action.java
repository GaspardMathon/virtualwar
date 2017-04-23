package virtualwar;
public abstract class Action {
	protected Robot robot;
	protected Coordonnees direction;
	
	public Robot getRobot(){
		return this.robot;
	}
	
	public Coordonnees getDirection(){
		return this.direction;
	}
	
	public Coordonnees getObjectif(){
		return null;
	}
	
	public Action(Robot robot, Coordonnees direction){
		this.robot = robot;
		this.direction = direction;
	}
	
	abstract void agit();
}