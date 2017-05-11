package graphique;

import virtualwar.*;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//le plateau est constitué d'une hauteur, d'une largeur et d'une grille de cellule sur laquelle se déroule la partie
public class PlateauG extends Application {
	
	Plateau plat = new Plateau(10,10);
	GridPane tableau;
	
	public void start(Stage stage){
		final String imageURI = new File("/home/infoetu/salvaoc/Images/moustache.png").toURI().toString();
		Image img = new Image(imageURI);
		ImageView imgvue = new ImageView();
		imgvue.setImage(img);
		imgvue.setFitHeight(100);
		imgvue.setFitWidth(100);
		
		HBox fenetre = new HBox();
		tableau = new GridPane();
		tableau.setMaxSize(500, 500);
		
		for(int i = 0; i < plat.getHauteur()+2;i++){
			for(int y = 0; y < plat.getLargeur()+2;y++){
				Label label =  new Label();
				label.setText(plat.getGrille()[i][y].toString());
				label.setStyle("-fx-font : 34px Verdana");
				tableau.add(label, y, i);
			}
		}
		
		fenetre.getChildren().add(tableau);
		Scene s = new Scene(fenetre,500,500);
		stage.setScene(s);
		stage.show();
	}
	

	
	public static void main(String[] args){
		Application.launch(args);
	}
	
}