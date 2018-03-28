package entities;

public class PJDestiny2 {

	private enum Classes {
		TITAN, HUNTER, WARLOCK
	};

	private Classes type;
	private int light;

	public PJDestiny2(Classes type, int light) {
		this.type = type;
		this.light = light;
	}

	public Classes getType() {
		return type;
	}

	public void setType(Classes type) {
		this.type = type;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}

}
