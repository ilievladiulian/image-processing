package app;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import services.MirroringService;
import util.MirroringTypes;
import util.TableEntry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.time.LocalDate;

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
	@FXML
	DatePicker date;
	@FXML
	TabPane imageDataPane;
	@FXML
	TableView<TableEntry> originalImageData;
	@FXML
	TableColumn<TableEntry, String> originalImageWidth;
	@FXML
	TableColumn<TableEntry, String> originalImageHeight;
	@FXML
	TableColumn<TableEntry, String> originalImageSize;
	@FXML
	TableView<TableEntry> resultImageData;
	@FXML
	TableColumn<TableEntry, String> resultImageWidth;
	@FXML
	TableColumn<TableEntry, String> resultImageHeight;
	@FXML
	TableColumn<TableEntry, String> resultImageSize;
	@FXML
	CheckBox showImageData;

	// on action event handlers
	public void loadTigerImage() {
		this.mirroringProgress.setVisible(false);
		this.defaultPath = "default_images/tiger.bmp";
		this.fileName.setText("tiger.bmp");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setImageData(this.originalImageData, this.originalImage.getImage(), new File(this.defaultPath).length());
	}

	public void loadCarImage() {
		this.mirroringProgress.setVisible(false);
		this.defaultPath = "default_images/car.bmp";
		this.fileName.setText("car.bmp");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setImageData(this.originalImageData, this.originalImage.getImage(), new File(this.defaultPath).length());
	}

	public void loadDogImage() {
		this.mirroringProgress.setVisible(false);
		this.defaultPath = "default_images/dog.bmp";
		this.fileName.setText("dog.bmp");
		try {
			Image image = new Image(new FileInputStream(this.defaultPath));
			this.originalImage.setImage(image);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		setImageData(this.originalImageData, this.originalImage.getImage(), new File(this.defaultPath).length());
	}

	public void loadImage() {
		this.mirroringProgress.setVisible(false);
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
		setImageData(this.originalImageData, this.originalImage.getImage(), selectedImage.length());
	}

	public void mirrorImage() {
		loadMirrorOrientation();
		this.mirroringProgress.setProgress(Double.valueOf(0));
		this.mirroringProgress.setVisible(true);
		MirroringTypes type = MirroringTypes.HORIZONTAL;
		if (this.orientation.equals("vertical")) {
			type = MirroringTypes.VERTICAL;
		}
		Image result = MirroringService.mirrorHorizontally(this.originalImage.getImage(), mirroringProgress, type);
		this.resultImage.setImage(result);
		BufferedImage bImage = SwingFXUtils.fromFXImage(this.resultImage.getImage(), null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		byte[] res = {};
		try {
			ImageIO.write(bImage, "png", s);
			res = s.toByteArray();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setImageData(this.resultImageData, this.resultImage.getImage(), Long.valueOf(res.length));
		this.mirroringProgress.setProgress(Double.valueOf(100));
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
		this.date.setValue(LocalDate.now());
		this.date.setDisable(true);
		this.date.setStyle("-fx-opacity: 1");
		this.date.getEditor().setStyle("-fx-opacity: 1");
		this.originalImageHeight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHeight()));
		this.originalImageWidth.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWidth()));
		this.originalImageSize.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSize()));
		this.resultImageHeight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHeight()));
		this.resultImageWidth.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWidth()));
		this.resultImageSize.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSize()));
	}

	public void setShowImageData() {
		boolean showData = this.showImageData.isSelected();
		if (showData) {
			this.imageDataPane.setVisible(true);
		} else {
			this.imageDataPane.setVisible(false);
		}
	}

	private void loadMirrorOrientation() {
		RadioButton radioButton = (RadioButton) mirrorOrientation.getSelectedToggle();
		if (radioButton != null) {
			this.orientation = radioButton.getId();
		} else {
			this.orientation = "horizontal";
		}
	}

	private void setImageData(TableView<TableEntry> tableView, Image image, Long lengthInBytes) {
		tableView.getItems().removeAll(tableView.getItems());
		DecimalFormat df = new DecimalFormat("#.###");
		String size = df.format((float) lengthInBytes/1024/1024);
		TableEntry entry = new TableEntry(String.valueOf(image.getWidth()), String.valueOf(image.getHeight()), size);
		ObservableList<TableEntry> tableEntries = FXCollections.observableArrayList(entry);
		tableView.getItems().addAll(tableEntries);
	}
}
