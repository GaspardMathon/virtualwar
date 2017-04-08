package virtualwar;

import java.util.List;

abstract class Robot {
	private int energie;
	private int equipe;
	private Coordonnees coordonnees;
	private Vue vue;
	private String type;
	boolean invoque;
		
	public Robot(Vue vue, int h, int l, int equipe,String type,int energie){
		this.vue = vue;
		this.equipe = equipe;
		this.coordonnees = new Coordonnees(h,l);
		this.type = type;
		this.energie = energie;
		this.invoque = false;
	}
		
	public boolean estSurBase(){
		return false;
	}
		
			public void setCoordonnees(Coordonnees c){
				this.coordonnees = c;
			}
			
			public int getEnergie(){
				return this.energie;
			}
			
			
			public int getEquipe(){
				return this.equipe;
			}
			
			public void setEnergie(int e){
				this.energie = e;
			}
			
			public Vue getVue(){
				return this.vue;
			}
			public String getType(){
				return this.type;
			}
			
			public Coordonnees getCoordonnees(){
				return this.coordonnees;
			}
			
			abstract public int getCoutAction();
			
			abstract public int getCoutDeplacement();
			
			abstract public int getDegatsSubis();
			
			
			abstract public void deplacement();
			
			abstract public List<Coordonnees> getDeplacements();
			
			public void setVue(Vue vue){
				this.vue = vue;
			}
			public boolean getInvoque(){
				return this.invoque;
			}
			public void setInvoque(boolean b){
				this.invoque = b;
			}
			public String toString(){
				String nom = type;
				if(this.getInvoque()){
					nom += this.getCoordonnees().toString()+", Vie :"+this.getEnergie();
				}
				return nom;
			}
			
			public void subitTir(){
				
			}
			
			public void subitMine(){
				
			}
		}