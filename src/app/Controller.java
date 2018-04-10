package app;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import util.MirrorHorizontallyService;
import util.MirrorVerticallyService;

import java.io.File;
import java.io.FileInputStream;

public class Controller {

	private String path;
	private String defaultPath;
	private String orientation;

	// elements from view
	@FXML
	Pane window;
	@FXML
	ProgressIndicator mirroringProgress;
	@FXML
	ToggleGroup defaultImages;
	@FXML
	ToggleGroup mirrorOrientation;
	@FXML
	TextField fileName;
	@FXML
	ImageView originalImage;
	@FXML
	ImageView resultImage;
	@FXML
	SplitPane splitPane;

	public void loadTigerImage() {
		this.defaultPath = "default_images/tiger.bmp";
		this.fileName.setText("");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadCarImage() {
		this.defaultPath = "default_images/car.bmp";
		this.fileName.setText("");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadDogImage() {
		this.defaultPath = "default_images/dog.bmp";
		this.fileName.setText("");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Alegeti imaginea");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("BMP files", "*.bmp"));
		File selectedImage = fileChooser.showOpenDialog(window.getScene().getWindow());
		this.path = selectedImage.getPath();
		this.fileName.setText(selectedImage.getName());
		try {
			this.originalImage.setImage(new Image(new FileInputStream(this.path)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void mirrorImage() {
		loadMirrorOrientation();
		this.mirroringProgress.setProgress(Double.valueOf(0));
		if (this.orientation.equals("horizontal")) {
			MirrorHorizontallyService service = new MirrorHorizontallyService(this.originalImage.getImage(), this.resultImage);
			mirroringProgress.visibleProperty().bind(service.runningProperty());
			service.restart();
		} else if (this.orientation.equals("vertical")) {
			MirrorVerticallyService service = new MirrorVerticallyService(this.originalImage.getImage(), this.resultImage);
			mirroringProgress.visibleProperty().bind(service.runningProperty());
			service.restart();
		}
	}

	public void saveResultImage() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Alegeti directorul");
		File selectedDirectory = directoryChooser.showDialog(window.getScene().getWindow());
	}

	private void loadMirrorOrientation() {
		RadioButton radioButton = (RadioButton) mirrorOrientation.getSelectedToggle();
		if (radioButton != null) {
			this.orientation = radioButton.getId();
		} else {
			this.orientation = "horizontal";
		}
	}
}
