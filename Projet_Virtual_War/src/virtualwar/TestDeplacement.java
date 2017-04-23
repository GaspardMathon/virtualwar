/*package virtualwar;

public class TestDeplacement {
	public static void main(String[] args){
		Plateau plat = new Plateau(10,10);
		Piegeur p = new Piegeur(new Vue(plat,1),2,4,1);
		plat.getGrille()[p.getCoordonnees().getHauteur()][p.getCoordonnees().getLargeur()].deplacerSur(p);
		System.out.println(p.getCoordonnees());
		Deplacement d = new Deplacement(p,Constante.BAS);
		System.out.println(d.toString());
		Action a = new Deplacement(p,Constante.BAS);
		System.out.println(a);
		for(int i = 0; i < 5;i++){
			System.out.println(plat);
			System.out.println(p.getEnergie());
			p.deplacement();
		}
			System.out.println(p.getDeplacements());
	}
}*/