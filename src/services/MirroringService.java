package services;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import util.MirroringTypes;

public class MirroringService {

	public static Image mirrorHorizontally(Image image, ProgressIndicator progressIndicator, MirroringTypes type) {
		int originalWidth = (int) Math.round(image.getWidth());
		int originalHeight = (int) Math.round(image.getHeight());

		WritableImage resultImage = new WritableImage(originalWidth, originalHeight);
		PixelWriter pixelWriter = resultImage.getPixelWriter();

		PixelReader pixelReader = image.getPixelReader();
		for (int row = 0; row < originalHeight; row++) {
			for (int column = 0; column < originalWidth; column++) {
				mirrorPixel(pixelReader, pixelWriter, type, row, column, originalHeight, originalWidth);
			}
			if (row >= originalHeight/5) {
				progressIndicator.setProgress(Double.valueOf(20));
			} else if (row >= 2*originalHeight/5) {
				progressIndicator.setProgress(Double.valueOf(40));
			} else if (row >= 3*originalHeight/5) {
				progressIndicator.setProgress(Double.valueOf(60));
			} else if (row >= 4*originalHeight/5) {
				progressIndicator.setProgress(Double.valueOf(80));
			}
		}
		return resultImage;
	}

	private static void mirrorPixel(PixelReader reader, PixelWriter writer, MirroringTypes type, int row, int column, int originalHeight, int originalWidth) {
		if (type == MirroringTypes.VERTICAL) {
			Color color = reader.getColor(column, row);
			writer.setColor(column, originalHeight - row - 1, color);
		} else if (type == MirroringTypes.HORIZONTAL) {
			Color color = reader.getColor(column, row);
			writer.setColor(originalWidth - column - 1, row, color);
		}
	}
}
