package util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MirrorVerticallyService extends Service<Image> {
	private Image originalImage;
	private ImageView resultImage;

	public MirrorVerticallyService(Image image, ImageView resultImage) {
		this.originalImage = image;
		this.resultImage = resultImage;
	}

	@Override
	protected Task<Image> createTask() {
		return new MirrorVerticallyTask(this.originalImage, this.resultImage);
	}
}