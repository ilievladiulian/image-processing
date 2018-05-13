package app;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

public class AppController {
	private String path;
	private String defaultPath;
	private String orientation;

	// elements from view
	Pane window;
	ProgressIndicator mirroringProgress;
	Button loadImageButton;
	ToggleButton tigerImage;
	ToggleButton dogImage;
	ToggleButton carImage;
	ToggleGroup defaultImages;
	RadioButton horizontalOrientation;
	RadioButton verticalOrientation;
	ToggleGroup mirrorOrientation;
	TextField fileName;
	ImageView originalImage;
	ImageView resultImage;
	SplitPane splitPane;
	AnchorPane leftArea;
	AnchorPane rightArea;
	Hyperlink gitHubLink;
	DatePicker date;
	TabPane imageDataPane;
	Tab originalImageTab;
	Tab resultImageTab;
	TableView<TableEntry> originalImageData;
	TableColumn<TableEntry, String> originalImageWidth;
	TableColumn<TableEntry, String> originalImageHeight;
	TableColumn<TableEntry, String> originalImageSize;
	TableView<TableEntry> resultImageData;
	TableColumn<TableEntry, String> resultImageWidth;
	TableColumn<TableEntry, String> resultImageHeight;
	TableColumn<TableEntry, String> resultImageSize;
	CheckBox showImageData;
	Button saveImageButton;
	Label defaultImagesLabel;
	Label titleLabel;
	Label storyLabel;
	Label orientationLabel;
	Label githubLabel;
	Label dateLabel;
	Button mirroringButton;

	public Pane buildScene() {
		this.window = Configuration.buildPane();
		this.loadImageButton = Configuration.buildLoadImageButton();
		this.mirroringProgress = Configuration.buildProgressIndicator();
		this.tigerImage = Configuration.buildTigerButton();
		this.dogImage = Configuration.buildDogButton();
		this.carImage = Configuration.buildCarButton();
		this.defaultImages = Configuration.buildDefaultImagesToggleGroup();
		this.horizontalOrientation = Configuration.buildHorizontalOrientationRadioButton();
		this.verticalOrientation = Configuration.buildVerticalOrientationRadioButton();
		this.mirrorOrientation = Configuration.buildMirrorOrientationToggleGroup();
		this.fileName = Configuration.buildTextField();
		this.originalImage = Configuration.buildOriginalImageView();
		this.resultImage = Configuration.buildResultImageView();
		this.leftArea = Configuration.buildLeftArea();
		this.rightArea = Configuration.buildRightArea();
		this.splitPane = Configuration.buildSplitPane();
		this.gitHubLink = Configuration.buildHyperlink();
		this.date = Configuration.buildDatePicker();
		this.imageDataPane = Configuration.buildTabPane();
		this.originalImageTab = Configuration.buildOriginalImageTab();
		this.resultImageTab = Configuration.buildResultImageTab();
		this.originalImageData = Configuration.buildOriginalImageDataTable();
		this.originalImageWidth = Configuration.buildOriginalImageWidthColumn();
		this.originalImageHeight = Configuration.buildOriginalImageHeightColumn();
		this.originalImageSize = Configuration.buildOriginalImageSizeColumn();
		this.resultImageData = Configuration.buildResultImageDataTable();
		this.resultImageHeight = Configuration.buildResultImageWidthColumn();
		this.resultImageWidth = Configuration.buildResultImageHeightColumn();
		this.resultImageSize = Configuration.buildResultImageSizeColumn();
		this.showImageData = Configuration.buildCheckBox();
		this.saveImageButton = Configuration.buildSaveImageButton();
		this.dateLabel = Configuration.buildDateLabel();
		this.defaultImagesLabel = Configuration.buildDefaultImagesLabel();
		this.githubLabel = Configuration.buildGithubLabel();
		this.orientationLabel = Configuration.buildOrientationLabel();
		this.storyLabel = Configuration.buildStoryLabel();
		this.titleLabel = Configuration.buildTitleLabel();
		this.mirroringButton = Configuration.buildMirroringButton();

		configureOnActionEvent();
		configureTables();
		configureNodeTree();

		return this.window;
	}

	private void configureOnActionEvent() {
		this.gitHubLink.setOnAction(event -> openGithubLink());
		this.loadImageButton.setOnAction(event -> loadImage());
		this.tigerImage.setOnAction(event -> loadTigerImage());
		this.dogImage.setOnAction(event -> loadDogImage());
		this.carImage.setOnAction(event -> loadCarImage());
		this.mirroringButton.setOnAction(event -> mirrorImage());
		this.saveImageButton.setOnAction(event -> saveResultImage());
		this.showImageData.setOnAction(event -> toggleShowImageData());
	}

	private void openGithubLink() {
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
	}

	private void toggleShowImageData() {
		boolean showData = this.showImageData.isSelected();
		if (showData) {
			this.imageDataPane.setVisible(true);
		} else {
			this.imageDataPane.setVisible(false);
		}
	}

	private void saveResultImage() {
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

	private void mirrorImage() {
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

	private void loadCarImage() {
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

	private void loadDogImage() {
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

	private void loadTigerImage() {
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

	private void loadImage() {
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

	private void configureNodeTree() {
		this.originalImageTab.setContent(this.originalImageData);
		this.resultImageTab.setContent(this.resultImageData);
		this.imageDataPane.getTabs().add(this.originalImageTab);
		this.imageDataPane.getTabs().add(this.resultImageTab);
		this.leftArea.getChildren().add(this.originalImage);
		this.rightArea.getChildren().add(this.resultImage);
		this.splitPane.getItems().add(this.leftArea);
		this.splitPane.getItems().add(this.rightArea);
		this.tigerImage.setToggleGroup(this.defaultImages);
		this.dogImage.setToggleGroup(this.defaultImages);
		this.carImage.setToggleGroup(this.defaultImages);
		this.horizontalOrientation.setToggleGroup(this.mirrorOrientation);
		this.verticalOrientation.setToggleGroup(this.mirrorOrientation);

		this.window.getChildren().addAll(this.mirroringProgress, this.carImage, this.tigerImage, this.dogImage, this.horizontalOrientation, this.verticalOrientation,
				this.fileName, this.splitPane, this.gitHubLink, this.date, this.imageDataPane, this.showImageData, this.loadImageButton, this.saveImageButton,
				this.dateLabel, this.defaultImagesLabel, this.githubLabel, this.orientationLabel, this.storyLabel, this.titleLabel, this.mirroringButton);
	}

	private void configureTables() {
		this.originalImageHeight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHeight()));
		this.originalImageWidth.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWidth()));
		this.originalImageSize.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSize()));
		this.resultImageHeight.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHeight()));
		this.resultImageWidth.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getWidth()));
		this.resultImageSize.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSize()));
		this.originalImageData.getColumns().add(this.originalImageHeight);
		this.originalImageData.getColumns().add(this.originalImageWidth);
		this.originalImageData.getColumns().add(this.originalImageSize);
		this.resultImageData.getColumns().add(this.resultImageHeight);
		this.resultImageData.getColumns().add(this.resultImageWidth);
		this.resultImageData.getColumns().add(this.resultImageSize);
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
