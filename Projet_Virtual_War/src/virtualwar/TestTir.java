/*
package virtualwar;

public class TestTir {
	public static void main(String[] args){
		Plateau plat = new Plateau(10,20);
		Vue v1 = new Vue(plat,1);
		Vue v2 = new Vue(plat,2);
		
		Tireur t = new Tireur(v1,5,10,1,1);
		plat.getGrille()[t.getCoordonnees().getHauteur()][t.getCoordonnees().getLargeur()].deplacerSur(t);
		t.setInvoque(true);
		
		Piegeur cible1 = new Piegeur(v2,4,11,2);
		plat.getGrille()[cible1.getCoordonnees().getHauteur()][cible1.getCoordonnees().getLargeur()].deplacerSur(cible1);
		cible1.setInvoque(true);
		
		Piegeur cible2 = new Piegeur(v2,5,9,1);
		plat.getGrille()[cible2.getCoordonnees().getHauteur()][cible2.getCoordonnees().getLargeur()].deplacerSur(cible2);
		cible2.setInvoque(true);
		
		System.out.println(plat);
		t.Tir();
		System.out.println(cible1);
	}
}*/