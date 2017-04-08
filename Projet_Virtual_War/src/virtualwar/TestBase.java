package virtualwar;

public class TestBase {



		/*public static void main(String[] args){
			Plateau plat = new Plateau(10,10);
			
			//Creation d'une base équipe 1
			Base b1 = new Base(1,1,1);
			//Creation d'une base équipe 1
			Base b2 = new Base(plat.getHauteur(),plat.getLargeur(),2);
			//Placement des 2 bases
			plat.getGrille()[b1.getCoordonnees().getHauteur()][b1.getCoordonnees().getLargeur()].deplacerSur(b1);
			plat.getGrille()[b2.getCoordonnees().getHauteur()][b2.getCoordonnees().getLargeur()].deplacerSur(b2);
			
			//Creation personnages
			Piegeur p = new Piegeur(new Vue(plat,1),4,4,1);
			Piegeur p1 = new Piegeur(new Vue(plat,1),5,6,1);
			Piegeur p2 = new Piegeur(new Vue(plat,1),5,5,1);
			Tireur t = new Tireur(new Vue(plat,1),5,4,2);
			//placement personnages
			plat.getGrille()[p.getCoordonnees().getHauteur()][p.getCoordonnees().getLargeur()].deplacerSur(p);
			plat.getGrille()[p1.getCoordonnees().getHauteur()][p1.getCoordonnees().getLargeur()].deplacerSur(p1);
			plat.getGrille()[p2.getCoordonnees().getHauteur()][p2.getCoordonnees().getLargeur()].deplacerSur(p2);
			plat.getGrille()[t.getCoordonnees().getHauteur()][t.getCoordonnees().getLargeur()].deplacerSur(t);
			System.out.println(plat.toString());
			
						
			//TEST DEPLACEMENT
			    //Liste deplacement possible + affichage
			    System.out.println(p.getDeplacements());
			    	
			    //Creation d'un deplacement
				Deplacement d = new Deplacement(p,Constante.BAS);
			
					//1er deplacemement + affichage
					d.agit();
					System.out.println(plat.toString());
					
				//Modification de direction
			 	d.setDeplacement(p, Constante.DROITE);
			 	
					//2eme deplacemement + affichage
					d.agit();
					System.out.println(plat.toString());
			


			System.out.println(t.getEnergie());
			t.setTirPossible(2);
			System.out.println(t.getListeTir());
			
			Attaque a= new Attaque(t,new Coordonnees(4,4));
			a.agit();
			//System.out.println(p.getEnergie());
			//System.out.println(plat.getGrille()[2][2].getContenu());
			//System.out.println(plat.getGrille()[2][2].getContientRobot());

			
			
			//System.out.println(plat.getGrille()[5][4].robot.toString());
		}		*/
}
