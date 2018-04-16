package util;

public class TableEntry {
	private String width;
	private String height;
	private String size;

	public TableEntry(String width, String height, String size) {
		this.width = width;
		this.size = size;
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
