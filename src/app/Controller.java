package app;

import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import util.MirrorHorizontallyService;
import util.MirrorVerticallyService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
	@FXML
	Hyperlink gitHubLink;

	public void loadTigerImage() {
		this.defaultPath = "default_images/tiger.bmp";
		this.fileName.setText("tiger.bmp");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadCarImage() {
		this.defaultPath = "default_images/car.bmp";
		this.fileName.setText("car.bmp");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadDogImage() {
		this.defaultPath = "default_images/dog.bmp";
		this.fileName.setText("dog.bmp");
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
		String fileName = selectedDirectory.getPath() + "/" + this.fileName.getText().substring(0, this.fileName.getText().length() - 4)
				+ "_mirrored_" + this.orientation + "ly.bmp";
		BufferedImage bImage = SwingFXUtils.fromFXImage(this.resultImage.getImage(), null);
		try {
			ImageIO.write(bImage, "png", new File(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void initialize() {
		this.gitHubLink.setOnAction(e -> {
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI(this.gitHubLink.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
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
