package util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MirrorHorizontallyService extends Service<Image> {
	private Image originalImage;
	private ImageView resultImage;

	public MirrorHorizontallyService(Image image, ImageView resultImage) {
		this.originalImage = image;
		this.resultImage = resultImage;
	}

	@Override
	protected Task<Image> createTask() {
		return new MirrorHorizontallyTask(this.originalImage, this.resultImage);
	}
}