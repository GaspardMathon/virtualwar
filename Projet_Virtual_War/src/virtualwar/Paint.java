package virtualwar;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Paint extends Application {

	String tempo = "/home/infoetu/machtao/Bureau/LeJeu/";

	private int nbColonnes;
	private int nbLignes;
	private int nbObstacles;
	private boolean tourEquipe = true;
	private Cellule grille = new Cellule(3, 3);
	private Vue vueJ1 = new Vue(grille, true);
	private Vue vueJ2 = new Vue(grille, false);
	private Canvas canvas;
	private GraphicsContext gc;
	private ArrayList<RectangleRedit> clickCase = new ArrayList<RectangleRedit>();
	Coordonnees p = new Coordonnees();

	/*
	 * class ctrlPress implements EventHandler<KeyEvent> { public void
	 * handle(KeyEvent event) { if(event.isControlDown()){
	 * System.out.println("ok"); } } }
	 */
	class ButonVisualisationTerrainHander implements EventHandler<ActionEvent> {

		Spinner<Integer> reponseCol;
		Spinner<Integer> reponseLig;
		Spinner<Integer> reponseOb;

		ButonVisualisationTerrainHander(Spinner<Integer> reponseCol, Spinner<Integer> reponseLig,
				Spinner<Integer> reponseOb) {
			this.reponseCol = reponseCol;
			this.reponseLig = reponseLig;
			this.reponseOb = reponseOb;
		}

		public void handle(ActionEvent event) {
			nbColonnes = ((Integer) reponseCol.getValue()).intValue();
			nbLignes = ((Integer) reponseLig.getValue()).intValue();
			grille = new Grille(nbLignes, nbColonnes);
			nbObstacles = ((Integer) reponseOb.getValue()).intValue();
			// System.out.println("nbLigne: "+nbLignes+" \t nbColonne:
			// "+nbColonnes+" \t nbObstacles: "+nbObstacles);
			paintToCanvas(canvas);
		}
	}

	class ButtonEnregistrementHander implements EventHandler<ActionEvent> {

		private Stage stageInit;

		public ButtonEnregistrementHander(Stage stage) {
			stageInit = stage;
		}

		public void handle(ActionEvent event) {
			grille = new Grille(nbLignes, nbColonnes);
			grille.generateObstacles(nbObstacles);
			initBaseOnGrille();
			repaintCanvas();
			stageInit.close();
		}
	}

	class clickHander implements EventHandler<MouseEvent> {

		public void handle(MouseEvent event) {

			repaintCanvas();

			String ordre = "";

			boolean trouve = false;
			int cpt = 0;
			while (cpt < clickCase.size() && !trouve) {

				/*if(isInsideRectangle(clickCase.get(cpt), event)){
					System.out.println("je rentre dans la condition ! le click est dans le retancgle "+cpt);
				}else{
					System.out.println("la condition ne marche pas ! Pour le rectangle "+cpt);
				}*/

				if (isInsideRectangle(clickCase.get(cpt), event)) {
					/*
					 * Sert a chercher les bugs sur le click
					 * 
					 * System.out.println(event.getX()+" X :"+event.getY()+" Y"
					 *);
					 * System.out.println(clickCase.get(cpt).y+"x:"+clickCase.
					 * get(cpt).x+"y \t"
					 * +clickCase.get(cpt).height+"x:"+clickCase.get(cpt).height
					 * +"y");
					 * System.out.println(clickCase.get(cpt).indiceOfGrilleX+
					 * "x:"+clickCase.get(cpt).indiceOfGrilleY+"y");
					 */
					RectangleRedit rTarget = clickCase.get(cpt);
					Element e = grille.getCase(rTarget.indiceOfGrilleX, rTarget.indiceOfGrilleY).getElement();
					if (e != null) {
						char symbol = e.getSymbol();
						System.out.println(symbol);
						if (e instanceof Base) {
							Base b = (Base) e;
							if (b.joueur == tourEquipe) {
								if (b.joueur) {
									double i = 5;
									double j = 20;
									for (Element robot : b.getRobotsDispo()) {
										gc.fillText(robot.getSymbol() + "", i, j);
										j += 20;
									}
								} else {
									double i = canvas.getWidth() - 15;
									double j = canvas.getHeight() - 60;
									for (Element robot : b.getRobotsDispo()) {
										gc.fillText(robot.getSymbol() + "", i, j);
										j += 20;
									}
								}
								b.choixDeSortie(b.joueur);
								tourEquipe = !tourEquipe;
							}
						}
						if (e instanceof Tireur) {
							System.out.println("T");
							Tireur t = (Tireur) e;
							System.out.println(t.getJoueur());
							if (t.getJoueur() == tourEquipe) {
								ordre = Saisie.choixAction2(tourEquipe);
								if (ordre.equals("move")) {
									p = Saisie.choixCoordMooveTireur(t);
									if (p != null) {
										if (t.move(p)) {
											System.out.println("Tireur peut bouger !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas bouger !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								} else if (ordre.equals("shot")) {
									p = Saisie.choixCoordShotTireur(t);
									if (p != null) {
										if (t.shot(p)) {
											System.out.println("Tireur peut tirer !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas tirer !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								}
								tourEquipe = !tourEquipe;
							}
						}
						if (e instanceof Piegeur) {
							Piegeur p1 = (Piegeur) e;
							if (p1.getJoueur() == tourEquipe) {
								ordre = Saisie.choixActionPiegeur(tourEquipe);
								if (ordre.equals("move")) {
									p = Saisie.choixCoordMoovePiegeur(p1);
									if (p != null) {
										if (p1.move(p)) {
											System.out.println("Tireur peut bouger !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas bouger !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								} else if (ordre.equals("throw")) {
									p = Saisie.choixCoordThrowPiegeur(p1);
									if (p != null) {
										if (p1.throwMine(p)) {
											System.out.println("Tireur peut tirer !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas tirer !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								}
								tourEquipe = !tourEquipe;
							}
						}
						if (e instanceof Char) {
							Char c = (Char) e;
							if (c.getJoueur() == tourEquipe) {
								ordre = Saisie.choixAction2(tourEquipe);
								if (ordre.equals("move")) {
									p = Saisie.choixCoordMooveChar(c);
									if (p != null) {
										if (c.move(p)) {
											System.out.println("Tireur peut bouger !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas bouger !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								} else if (ordre.equals("shot")) {
									p = Saisie.choixCoordShotChar(c);
									if (p != null) {
										if (c.shot(p)) {
											System.out.println("Tireur peut tirer !" + p.getX() + "x " + p.getY() + "y");
										} else {
											System.out.println("Tireur ne peut pas tirer !" + p.getX() + "x " + p.getY() + "y");
										}
									}
								}
								tourEquipe = !tourEquipe;
							}

						}
					}
					trouve = true;
				}
				cpt++;
			}
			Base b1 = (Base) grille.getCase(0, 0).getElement();
			b1.healElementDispo();
			Base b2 = (Base) grille.getCase(grille.getCOLONNES() - 1, grille.getLIGNES() - 1).getElement();
			b2.healElementDispo(); // soigne les pion en base ...
			grille.afficher();
			repaintCanvas();
		}
	}

	public boolean isInsideRectangle(RectangleRedit r, MouseEvent event) {
		double coinHautGaucheX = r.x;
		double coinHautGaucheY = r.y;
		double coinBasGauche = r.y + r.height;
		double coinHautDroite = r.x + r.width;
		if (event.getX() > coinHautGaucheX && event.getY() > coinHautGaucheY && event.getX() < coinHautDroite
				&& event.getY() < coinBasGauche) {
			return true;
		}
		return false;
	}

	public void repaintCanvas() {
		System.out.println("equipe : " + tourEquipe);
		for (int x = 0; x < grille.getLIGNES(); x++) {
			for (int y = 0; y < grille.getCOLONNES(); y++) {
				RectangleRedit r = null;
				for (RectangleRedit tmp : clickCase) {
					if (tmp.indiceOfGrilleX == x && tmp.indiceOfGrilleY == y) {
						r = tmp;
					}
				}

				if (grille.getCase(x, y).getElement() != null) {
					char symbol = grille.getCase(x, y).getElement().getSymbol();
					if (symbol == 'B') {
						Base b = (Base) grille.getCase(x, y).getElement();
						if (b.joueur) {
							Image base1 = new Image((new File("images/baseJ1.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						} else {
							Image base1 = new Image((new File("images/baseJ2.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						}
					}
					if (symbol == 'T') {
						Tireur t = (Tireur) grille.getCase(x, y).getElement();
						if (t.getJoueur()) {
							Image base1 = new Image((new File("images/tireurJ1.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						} else {
							Image base1 = new Image((new File("images/tireurJ2.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						}
					}
					if (symbol == 'P') {
						Piegeur p = (Piegeur) grille.getCase(x, y).getElement();
						if (p.getJoueur()) {
							Image base1 = new Image((new File("images/piegeurJ1.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						} else {
							Image base1 = new Image((new File("images/piegeurJ2.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						}
					}
					if (symbol == 'C') {
						Char c = (Char) grille.getCase(x, y).getElement();
						if (c.getJoueur()) {
							Image base1 = new Image((new File("images/tankJ1.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						} else {
							Image base1 = new Image((new File("images/tankJ2.png")).toURI().toString());
							gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						}
					}
					if (symbol == 'M') {
						Mine m = (Mine) grille.getCase(x, y).getElement();
						if (tourEquipe == m.getEquipe()) {
							if (m.getEquipe()) {
								Image base1 = new Image((new File("images/mineJ11.png")).toURI().toString());
								gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
							} else {
								Image base1 = new Image((new File("images/mineJ2.png")).toURI().toString());
								gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
							}
						}
					}
					if (symbol == 'O') {
						Image base1 = new Image((new File("images/obstacle1.png")).toURI().toString());
						gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
					}
					/*if (symbol == 'X') {
						//if (symbol != 'B'|| symbol != 'T'|| symbol != 'P'|| symbol != 'C'|| symbol != 'M'|| symbol != 'O') {
						Image base1 = new Image((new File("images/obstacle1.png")).toURI().toString());
						gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
						gc.clearRect(r.x * 1.0, r.y * 1.0, r.width, r.height);
					}*/
				}
				else {
					Image base1 = new Image((new File("images/caseVide11.png")).toURI().toString());
					gc.drawImage(base1, r.x * 1.0, r.y * 1.0, r.width, r.height);
					//gc.clearRect(r.x * 1.0, r.y * 1.0, r.width, r.height);
				}				
			}
		}
	}

	public void clearCanvas() {
		gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
	}

	public void initBaseOnGrille() {
		Coord c1 = new Coord(0, 0);
		Base b1 = new Base(this.grille, c1, true);
		this.grille.getCase(c1.x, c1.y).setElement(b1);

		Coord c2 = new Coord(nbLignes - 1, nbColonnes - 1);
		Base b2 = new Base(this.grille, c2, false);
		this.grille.getCase(c2.x, c2.y).setElement(b2);

		/*
		 * Coord c3 = new Coord(1,1); Tireur t1 = new Tireur(true, grille, c3);
		 * this.grille.getCase(1, 1).setElement(t1); grille.afficherIdx();
		 */
	}

	public void paintToCanvas(Canvas canvas) {
		this.clearCanvas();
		Image img = new Image((new File("images/terrain33.png")).toURI().toString());
		gc.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());		
		final double tailleCol = (canvas.getWidth() * 0.90) / grille.getLIGNES();
		double y = canvas.getWidth() * 0.05;
		final double tailleLigne = (canvas.getHeight() * 0.90) / grille.getCOLONNES();
		double x = canvas.getHeight() * 0.05;
		this.clickCase = new ArrayList<RectangleRedit>();
		for (int idxCol = 0; idxCol < grille.getCOLONNES(); idxCol++) {
			x = canvas.getHeight() * 0.05;
			for (int idxl = 0; idxl < grille.getLIGNES(); idxl++) {
				this.clickCase.add(new RectangleRedit((int) y, (int) x, ((int) tailleCol), ((int) tailleLigne), idxl, idxCol));
				this.gc.strokeRect(x, y, tailleCol, tailleLigne);
				x += tailleCol;
			}
			y += tailleLigne;
		}
	}

	public Spinner<Integer> creerSpinner(int min, int max, int valInit, boolean editable) {
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max,
				valInit);
		Spinner<Integer> spinner = new Spinner<Integer>();
		spinner.setEditable(editable);
		spinner.setValueFactory(valueFactory);
		return spinner;
	}

	public void start(Stage stage) {
		VBox root = new VBox();
		this.canvas = new Canvas(820, 820);
		this.gc = canvas.getGraphicsContext2D();
		this.paintToCanvas(canvas);

		Stage stageInit = new Stage();
		VBox initialisationTerrain = new VBox();

		Label questionColonne = new Label("Combien de Colonne voulez-vous ?");
		Spinner<Integer> reponseQuestionColonne = creerSpinner(3, 100, 4, false);

		Label questionLigne = new Label("Combien de Ligne voulez-vous ?");
		Spinner<Integer> reponseQuestionLigne = creerSpinner(3, 100, 4, false);

		Label questionObstacle = new Label("Combien d'Obstalces voulez-vous ?");
		Spinner<Integer> reponseQuestionObstacle = creerSpinner(0, 50, 0, false);

		HBox button = new HBox();
		Button buttonVisualisation = new Button("Visualiser");
		buttonVisualisation.setOnAction(new ButonVisualisationTerrainHander(reponseQuestionColonne,	reponseQuestionLigne, reponseQuestionObstacle));
		Button buttonEnregistrement = new Button("Appliquer");
		buttonEnregistrement.setOnAction(new ButtonEnregistrementHander(stageInit));
		button.getChildren().addAll(buttonVisualisation, buttonEnregistrement);

		initialisationTerrain.getChildren().addAll(questionColonne, reponseQuestionColonne, questionLigne,
				reponseQuestionLigne, questionObstacle, reponseQuestionObstacle, button);

		Scene sceneInit = new Scene(initialisationTerrain);
		stageInit.setTitle("Virtual War");
		stageInit.setX(300D);
		stageInit.setY(300D);
		stageInit.setScene(sceneInit);
		stageInit.show();

		canvas.setOnMouseClicked(new clickHander());
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setTitle("Virtual War");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
