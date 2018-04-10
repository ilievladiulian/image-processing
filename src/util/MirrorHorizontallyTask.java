package util;

import javafx.concurrent.Task;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class MirrorHorizontallyTask extends Task<Image> {
	private Image originalImage;
	private ImageView resultImage;

	public MirrorHorizontallyTask(Image originalImage, ImageView resultImage) {
		this.originalImage = originalImage;
		this.resultImage = resultImage;
	}

	private Image mirrorHorizontally(Image image) {
		int originalWidth = (int) Math.round(image.getWidth());
		int originalHeight = (int) Math.round(image.getHeight());

		WritableImage resultImage = new WritableImage(originalWidth, originalHeight);
		PixelWriter pixelWriter = resultImage.getPixelWriter();

		PixelReader pixelReader = image.getPixelReader();
		for (int row = 0; row < originalHeight; row++) {
			for (int column = 0; column < originalWidth; column++) {
				Color color = pixelReader.getColor(column, row);
				pixelWriter.setColor(originalWidth - column - 1, row, color);
			}
		}

		this.resultImage.setImage(resultImage);

		return resultImage;
	}

	@Override
	protected Image call() throws Exception {
		return mirrorHorizontally(originalImage);
	}
}
