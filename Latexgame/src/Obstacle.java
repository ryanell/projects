import java.util.Random;

public class Obstacle {

	private EZImage obstaclePicture;
	private int x, y;
	private int speed;
	private int maxScreenX, maxScreenY;
	private int type;
	private String filename;

	// Constructor for obstacle.
	public Obstacle(int maxX, int maxY, int type) {

		this.filename = " ";
		maxScreenX = maxY;
		maxScreenY = maxY;
		this.type = type;
		init();
	}

	public void init() {

		Random randomGenerator = new Random();
		if (type == 0) { // germs

			int germ = randomGenerator.nextInt(5);
			switch (germ) {
			case 0:
				filename = "Germ_Thing_1.png";
				break;
			default:
				filename = "Germ_Thing_1.png";

//			case 1:
//				filename = "germ2.png";
//				break;
//			case 2:
//				filename = "germ3.png";
//				break;
//			case 3:
//				filename = "germ4.png";
//				break;
//			case 4:
//				filename = "germ5.png";
//				break;
//			case 5:
//				filename = "germ6.png";
//				break;
			}

		}
		if (type == 1) { // condoms

			int germ = randomGenerator.nextInt(5);
			switch (germ) {
			case 0:
				filename = "Pixel_Condom_COLORED.png";
				break;
			case 1:
				filename = "Pixel_Condom_COLORED_2.png";
				break;
			case 2:
				filename = "Pixel_Condom_COLORED_3.png";
				break;
			case 3:
				filename = "Pixel_Condom_COLORED_4.png";
				break;
			case 4:
				filename = "Pixel_Condom_COLORED_5.png";
				break;
			}

		}
		if (type == 2) { // consent

			int germ = randomGenerator.nextInt(2);
			switch (germ) {
			case 0:
				filename = "Consent_1.png";
				break;
			case 1:
				filename = "Consent_2.png";
			
//			case 1:
//				filename = "sure.png";
//				break;
//			case 2:
//				filename = "ohyeah.png";
//				break;
//			case 3:
//				filename = "yeah.png";
//				break;
			}

		}
		if (type == 3) { // sex

			int germ = randomGenerator.nextInt(11);
			switch (germ) {
			case 0:
				filename = "Lips_1.png";
				break;
			case 1:
				filename = "Butt_1.png";
				break;
			case 2:
				filename = "Butt_2.png";
				break;
			case 3:
				filename = "Butt_3.png";
				break;
			default:
				filename = "Butt_3.png";

			case 4:
				filename = "Vulva_1.png";
				break;
			case 5:
				filename = "Reverse_Penis_2.png";
				break;
			case 6:
				filename = "Reverse_Penis_3.png";
				break;
			case 7:
				filename = "Reverse_Penis_22.png";
				break;
			case 8:
				filename = "Vulva_2.png";
				break;
			case 9:
				filename = "Vulva_3.png";
				break;
			case 10:
				filename = "Lips_2.png";
				break;
			}
		}
		if (type == 4) {
			int germ = randomGenerator.nextInt(3);
			switch (germ) {
			case 0:
				filename = "Not_Consent_1.png";
				break;
			case 1:
				filename = "Not_Consent_2.png";
				break;
			case 2:
				filename = "Not_Consent_3.png";
				break;

			}
		}
		obstaclePicture = EZ.addImage(filename, -300, -300);
		obstaclePicture.scaleBy(2);

		if (type == 0) { // germs
			// figure out how many multiples of trucks fit on the screen vertically
			int multiples = maxScreenY / obstaclePicture.getHeight();

			// Generate a random number using that multiple to calculate the position on the
			// screen.
			int ranY = randomGenerator.nextInt(multiples) * obstaclePicture.getHeight();
			setPosition(maxScreenX * 2, ranY);
			int spd = randomGenerator.nextInt(6) + 1;
			speed = spd;
			
		}
		if (type == 1) { // condoms
			// figure out how many multiples of trucks fit on the screen vertically
			int multiples = maxScreenY / obstaclePicture.getHeight();

			// Generate a random number using that multiple to calculate the position on the
			// screen.
			int ranY = randomGenerator.nextInt(multiples) * obstaclePicture.getHeight();
			setPosition(maxScreenX * 2, ranY);
			int spd = randomGenerator.nextInt(6) + 1;
			speed = spd;
		}
		if (type == 2) { // consent
			// figure out how many multiples of trucks fit on the screen vertically
			int multiples = maxScreenY / obstaclePicture.getHeight();

			// Generate a random number using that multiple to calculate the position on the
			// screen.
			int ranY = randomGenerator.nextInt(multiples) * obstaclePicture.getHeight();
			setPosition(maxScreenX * 2, ranY);
			int spd = randomGenerator.nextInt(6) + 1;
			speed = spd;
		}
		if (type == 3 || type == 4) { // sex
			// figure out how many multiples of trucks fit on the screen vertically
			int multiples = maxScreenY / obstaclePicture.getHeight();

			// Generate a random number using that multiple to calculate the position on the
			// screen.
			int ranY = randomGenerator.nextInt(multiples) * obstaclePicture.getHeight();
			setPosition(maxScreenX * 2, ranY);
			int spd = randomGenerator.nextInt(6) + 1;
			speed = spd;
		}

	}

	// Accessor function to get the X and Y position of the obstacle.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Set the position of the obstacle.
	public void setPosition(int posx, int posy) {
		x = posx;
		y = posy;
		setObstacleImagePosition(x, y);
	}

	// Set the image position of the obstacle.
	private void setObstacleImagePosition(int posx, int posy) {
		obstaclePicture.translateTo(posx, posy);
	}

	// Move the obstacle.
	public void move() {
		x = x - speed;
		setPosition(x, y);
		if (x < -40) {
			init();
		}
	}

	// Check to see if a point is inside an obstacle.
	public boolean isInside(int posx, int posy) {
		return obstaclePicture.isPointInElement(posx, posy);
	}
}
