package app;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import util.TableEntry;

import java.time.LocalDate;

import static jdk.nashorn.internal.objects.Global.Infinity;
import static jdk.nashorn.internal.objects.Global.load;

public class Configuration {

	public static Pane buildPane() {
		Pane anchorPane = new Pane();
		anchorPane.setPrefHeight(517.0);
		anchorPane.setPrefWidth(858.0);
		anchorPane.setMinHeight(-Infinity);
		anchorPane.setMinWidth(-Infinity);
		anchorPane.setMaxHeight(-Infinity);
		anchorPane.setMaxWidth(-Infinity);
		return anchorPane;
	}

	public static ProgressIndicator buildProgressIndicator() {
		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.setLayoutX(574.0);
		progressIndicator.setLayoutY(309.0);
		progressIndicator.setProgress(0.0);
		progressIndicator.setVisible(false);
		return progressIndicator;
	}

	public static ToggleButton buildTigerButton() {
		ToggleButton tigerButton = new ToggleButton();
		tigerButton.setLayoutX(14.0);
		tigerButton.setLayoutY(261.0);
		tigerButton.setMnemonicParsing(false);
		tigerButton.setPrefHeight(27.0);
		tigerButton.setPrefWidth(70.0);
		tigerButton.setText("Tigru");
		return tigerButton;
	}

	public static ToggleButton buildDogButton() {
		ToggleButton dogButton = new ToggleButton();
		dogButton.setLayoutX(233.0);
		dogButton.setLayoutY(261.0);
		dogButton.setMnemonicParsing(false);
		dogButton.setPrefHeight(27.0);
		dogButton.setPrefWidth(70.0);
		dogButton.setText("Caine");
		return dogButton;
	}

	public static ToggleButton buildCarButton() {
		ToggleButton carButton = new ToggleButton();
		carButton.setLayoutX(124.0);
		carButton.setLayoutY(261.0);
		carButton.setMnemonicParsing(false);
		carButton.setPrefHeight(27.0);
		carButton.setPrefWidth(70.0);
		carButton.setText("Masina");
		return carButton;
	}

	public static ToggleGroup buildDefaultImagesToggleGroup() {
		ToggleGroup defaultImages = new ToggleGroup();
		return defaultImages;
	}

	public static RadioButton buildHorizontalOrientationRadioButton() {
		RadioButton horizontalOrientation = new RadioButton();
		horizontalOrientation.setText("Orizontal");
		horizontalOrientation.setLayoutX(14.0);
		horizontalOrientation.setLayoutY(363.0);
		horizontalOrientation.setMnemonicParsing(false);
		horizontalOrientation.setId("horizontal");
		return horizontalOrientation;
	}

	public static RadioButton buildVerticalOrientationRadioButton() {
		RadioButton verticalOrientation = new RadioButton();
		verticalOrientation.setText("Vertical");
		verticalOrientation.setLayoutX(14.0);
		verticalOrientation.setLayoutY(331.0);
		verticalOrientation.setMnemonicParsing(false);
		verticalOrientation.setId("vertical");
		return verticalOrientation;
	}

	public static ToggleGroup buildMirrorOrientationToggleGroup() {
		ToggleGroup mirrorOrientation = new ToggleGroup();
		return mirrorOrientation;
	}

	public static TextField buildTextField() {
		TextField textField = new TextField();
		textField.setEditable(false);
		textField.setLayoutX(148.0);
		textField.setLayoutY(173.0);
		textField.setPrefWidth(155.0);
		textField.setPrefHeight(45.0);
		return textField;
	}

	public static ImageView buildOriginalImageView() {
		ImageView originalImageView = new ImageView();
		originalImageView.setFitHeight(230.0);
		originalImageView.setFitWidth(249.0);
		originalImageView.setPickOnBounds(true);
		originalImageView.setPreserveRatio(true);
		return originalImageView;
	}

	public static ImageView buildResultImageView() {

		ImageView resultImageView = new ImageView();
		resultImageView.setFitHeight(232.0);
		resultImageView.setFitWidth(249.0);
		resultImageView.setPickOnBounds(true);
		resultImageView.setPreserveRatio(true);
		return resultImageView;
	}

	public static AnchorPane buildLeftArea() {
		AnchorPane leftArea = new AnchorPane();
		leftArea.setMinHeight(0.0);
		leftArea.setMinWidth(0.0);
		leftArea.setPrefWidth(100.0);
		leftArea.setPrefHeight(160.0);
		return leftArea;
	}

	public static AnchorPane buildRightArea() {
		AnchorPane rightArea = new AnchorPane();
		rightArea.setMinHeight(0.0);
		rightArea.setMinWidth(0.0);
		rightArea.setPrefWidth(100.0);
		rightArea.setPrefHeight(160.0);
		return rightArea;
	}

	public static SplitPane buildSplitPane() {
		SplitPane splitPane = new SplitPane();
		splitPane.setDividerPosition(0, 0.5);
		splitPane.setLayoutX(336.0);
		splitPane.setLayoutY(64.0);
		splitPane.setPrefHeight(232.0);
		splitPane.setPrefWidth(508.0);
		return splitPane;
	}

	public static Hyperlink buildHyperlink() {
		Hyperlink hyperlink = new Hyperlink();
		hyperlink.setLayoutX(56.0);
		hyperlink.setLayoutY(482.0);
		hyperlink.setText("https://github.com/ilievladiulian/image-processing");
		return hyperlink;
	}

	public static DatePicker buildDatePicker() {
		DatePicker datePicker = new DatePicker();
		datePicker.setEditable(false);
		datePicker.setLayoutX(668.0);
		datePicker.setLayoutY(15.0);
		datePicker.setValue(LocalDate.now());
		datePicker.setDisable(true);
		datePicker.setStyle("-fx-opacity: 1");
		datePicker.getEditor().setStyle("-fx-opacity: 1");
		return datePicker;
	}

	public static TabPane buildTabPane() {
		TabPane tabPane = new TabPane();
		tabPane.setLayoutX(574.0);
		tabPane.setLayoutY(368.0);
		tabPane.setPrefHeight(84.0);
		tabPane.setPrefWidth(266.0);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
		return tabPane;
	}

	public static Tab buildOriginalImageTab() {
		Tab originalImageTab = new Tab();
		originalImageTab.setText("Imagine originala");
		return originalImageTab;
	}

	public static Tab buildResultImageTab() {
		Tab resultImageTab = new Tab();
		resultImageTab.setText("Imagine rezultat");
		return resultImageTab;
	}

	public static TableView<TableEntry> buildOriginalImageDataTable() {
		TableView<TableEntry> originalImageTable = new TableView<>();
		originalImageTable.setFixedCellSize(20.0);
		originalImageTable.setPrefHeight(55.0);
		originalImageTable.setPrefWidth(285.0);
		return originalImageTable;
	}

	public static TableColumn<TableEntry, String> buildOriginalImageWidthColumn() {
		TableColumn<TableEntry, String> originalImageWidth = new TableColumn<>();
		originalImageWidth.setPrefWidth(75.0);
		originalImageWidth.setText("Latime (px)");
		return originalImageWidth;
	}

	public static TableColumn<TableEntry, String> buildOriginalImageHeightColumn() {
		TableColumn<TableEntry, String> originalImageHeight = new TableColumn<>();
		originalImageHeight.setPrefWidth(88.0);
		originalImageHeight.setText("Inaltime (px)");
		return originalImageHeight;
	}

	public static TableColumn<TableEntry, String> buildOriginalImageSizeColumn() {
		TableColumn<TableEntry, String> originalImageSize = new TableColumn<>();
		originalImageSize.setPrefWidth(103.0);
		originalImageSize.setText("Dimensiune (MB)");
		return originalImageSize;
	}

	public static TableView<TableEntry> buildResultImageDataTable() {
		TableView<TableEntry> resultImageTable = new TableView<>();
		resultImageTable.setFixedCellSize(20.0);
		resultImageTable.setPrefHeight(200.0);
		resultImageTable.setPrefWidth(200.0);
		return resultImageTable;
	}

	public static TableColumn<TableEntry, String> buildResultImageWidthColumn() {
		TableColumn<TableEntry, String> resultImageWidth = new TableColumn<>();
		resultImageWidth.setPrefWidth(75.0);
		resultImageWidth.setText("Latime (px)");
		return resultImageWidth;
	}

	public static TableColumn<TableEntry, String> buildResultImageHeightColumn() {
		TableColumn<TableEntry, String> resultImageHeight = new TableColumn<>();
		resultImageHeight.setPrefWidth(75.0);
		resultImageHeight.setText("Inaltime (px)");
		return resultImageHeight;
	}

	public static TableColumn<TableEntry, String> buildResultImageSizeColumn() {
		TableColumn<TableEntry, String> resultImageSize = new TableColumn<>();
		resultImageSize.setPrefWidth(75.0);
		resultImageSize.setText("Dimensiune (MB)");
		return resultImageSize;
	}

	public static CheckBox buildCheckBox() {
		CheckBox checkBox = new CheckBox();
		checkBox.setLayoutX(382.0);
		checkBox.setLayoutY(402.0);
		checkBox.setMnemonicParsing(false);
		checkBox.setSelected(true);
		checkBox.setText("Afiseaza detalii despre imagine");
		return checkBox;
	}

	public static Button buildLoadImageButton() {
		Button loadImageButton = new Button();
		loadImageButton.setDefaultButton(true);
		loadImageButton.setLayoutY(173.0);
		loadImageButton.setLayoutX(14.0);
		loadImageButton.setMnemonicParsing(false);
		loadImageButton.setPrefWidth(127.0);
		loadImageButton.setPrefHeight(45.0);
		loadImageButton.setText("Incarca imagine");
		return loadImageButton;
	}

	public static Button buildSaveImageButton() {
		Button saveImageButton = new Button();
		saveImageButton.setDefaultButton(true);
		saveImageButton.setLayoutY(410.0);
		saveImageButton.setLayoutX(14.0);
		saveImageButton.setMnemonicParsing(false);
		saveImageButton.setPrefWidth(189.0);
		saveImageButton.setPrefHeight(45.0);
		saveImageButton.setText("Salveaza");
		return saveImageButton;
	}

	public static Label buildDateLabel() {
		Label dateLabel = new Label();
		dateLabel.setLayoutX(614.0);
		dateLabel.setLayoutY(19.0);
		dateLabel.setPrefHeight(17.0);
		dateLabel.setPrefWidth(38.0);
		dateLabel.setText("Data:");
		return dateLabel;
	}

	public static Label buildDefaultImagesLabel() {
		Label defaultImagesLabel = new Label();
		defaultImagesLabel.setLayoutX(14.0);
		defaultImagesLabel.setLayoutY(227.0);
		defaultImagesLabel.setPrefHeight(27.0);
		defaultImagesLabel.setPrefWidth(289.0);
		defaultImagesLabel.setText("Sau alegeti una dintre variantele urmatoare:");
		return defaultImagesLabel;
	}

	public static Label buildGithubLabel() {
		Label githubLabel = new Label();
		githubLabel.setLayoutX(14.0);
		githubLabel.setLayoutY(485.0);
		githubLabel.setText("Github:");
		return githubLabel;
	}

	public static Label buildOrientationLabel() {
		Label orientationLabel = new Label();
		orientationLabel.setLayoutX(14.0);
		orientationLabel.setLayoutY(296.0);
		orientationLabel.setText("Alegeti orientarea oglindirii:");
		return orientationLabel;
	}

	public static Label buildStoryLabel() {
		Label storyLabel = new Label();
		storyLabel.setLayoutX(14.0);
		storyLabel.setLayoutY(41.0);
		storyLabel.setPrefHeight(132.0);
		storyLabel.setPrefWidth(289.0);
		storyLabel.setText("Aplicatia isi propune sa oglindeasca o imagine selectata de utilizator, facand o transformare pixel cu pixel a imaginii sursa si salvand in directorul selectat de utilizator imaginea rezultata. Selectati imaginea din sistem:");
		storyLabel.setWrapText(true);
		return storyLabel;
	}

	public static Label buildTitleLabel() {
		Label titleLabel = new Label();
		titleLabel.setLayoutX(14.0);
		titleLabel.setLayoutY(14.0);
		titleLabel.setPrefHeight(27.0);
		titleLabel.setPrefWidth(289.0);
		titleLabel.setText("Oglindirea unei imagini");
		titleLabel.setTextAlignment(TextAlignment.JUSTIFY);
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setFont(new Font(15.0));
		return titleLabel;
	}

	public static Button buildMirroringButton() {
		Button mirroringButton = new Button();
		mirroringButton.setDefaultButton(true);
		mirroringButton.setLayoutX(130.0);
		mirroringButton.setLayoutY(326.0);
		mirroringButton.setMnemonicParsing(false);
		mirroringButton.setPrefHeight(60.0);
		mirroringButton.setPrefWidth(175.0);
		mirroringButton.setText("Oglindeste");
		return mirroringButton;
	}
}
