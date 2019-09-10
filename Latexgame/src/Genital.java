
public class Genital {
	public EZImage picture;

	public int x, y;
	static final int SPEED = 5;

	// Constructor for creating a chicken.
	public Genital(int file, int posx, int posy) {

		String name = " ";
		switch (file) {
		case 0:
			name = "Penis_2.png";
			break;
		case 1:
			name = "Penis_2_2.png";
			break;
		case 2:
			name = "Penis_3.png";
			break;
		case 3:
			name = "Vulva_1.png";
			break;
		case 4:
			name = "Vulva_2.png";
			break;
		case 5: 
			name = "Vulva_3.png";
			break;
		
		}

		picture = EZ.addImage(name, posx, posy);
		picture.scaleBy(2);
		x = posx;
		y = posy;
	}

	// Accessor method to retrieve the position of the chicken.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Set the position of the chicken
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setImagePosition(x, y);
	}

	private void setImagePosition(int posx, int posy) {
		picture.translateTo(posx, posy);
	}

	// Methods for moving the chicken.
	public void moveLeft(int step) {
		x = x - step;
		setImagePosition(x, y);
	}

	public void moveRight(int step) {
		x = x + step;
		setImagePosition(x, y);
	}

	public void moveUp(int step) {
		y = y - step;
		setImagePosition(x, y);
	}

	public void moveDown(int step) {
		y = y + step;
		setImagePosition(x, y);
	}

	public void boost() {
		if (EZInteraction.wasKeyPressed('b')) {
			for (int i = 0; i < 20; i++) {
				x = x + SPEED;
				setImagePosition(x, y);
			}
		}
	}
	
	

	// Keyboard controls for moving the genital.
	public void ControlIt() {
		if (EZInteraction.isKeyDown('w')) {
			moveUp(SPEED);
		}
		if (EZInteraction.isKeyDown('a')) {
			moveLeft(SPEED);
		}
		if (EZInteraction.isKeyDown('s')) {
			moveDown(SPEED);
		}
		if (EZInteraction.isKeyDown('d')) {
			moveRight(SPEED);
		}
		boost();
	}
}
