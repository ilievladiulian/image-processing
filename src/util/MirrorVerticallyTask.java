package util;

import javafx.concurrent.Task;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class MirrorVerticallyTask extends Task<Image> {
	private Image originalImage;
	private ImageView resultImage;

	public MirrorVerticallyTask(Image originalImage, ImageView resultImage) {
		this.originalImage = originalImage;
		this.resultImage = resultImage;
	}

	private Image mirrorVertically(Image image) {
		int originalWidth = (int) Math.round(image.getWidth());
		int originalHeight = (int) Math.round(image.getHeight());

		WritableImage resultImage = new WritableImage(originalWidth, originalHeight);
		PixelWriter pixelWriter = resultImage.getPixelWriter();

		PixelReader pixelReader = image.getPixelReader();
		for (int row = 0; row < originalHeight; row++) {
			for (int column = 0; column < originalWidth; column++) {
				Color color = pixelReader.getColor(column, row);
				pixelWriter.setColor(column, originalHeight - row - 1, color);
			}
		}
		this.resultImage.setImage(resultImage);

		return resultImage;
	}

	@Override
	protected Image call() throws Exception {
		return mirrorVertically(originalImage);
	}
}
