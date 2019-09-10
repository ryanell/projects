import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GenitalGame {

	// Constants for the game

	static final int MAX_SCREEN_X = 1910;
	static final int MAX_SCREEN_Y = 1150;
	static final int RUNNING = 0;
	static final int WINS = 1;
	static final int INFECTED = 2;
	static final int MENU = 3;
	static final int RAPE = 4;
	static final int OH_NO = 5;
	static final int INFECTEDM = 6;
	static final int END = 9;
	static final int MAX_GERMS = 7;
	static final int MAX_CONDOMS = 3;
	static final int MAX_YES = 3;
	static final int MAX_SEX = 2;

	public static void main(String[] args) {
		boolean play = true;
		while (play == true) {
			play();
		}
	}

	public static void play() {

		Random random = new Random();

		// At the start of the game, the chicken is in running state.
		int gameState = MENU;

		// Setup EZ graphics system.
		EZ.initialize(MAX_SCREEN_X, MAX_SCREEN_Y);

		// int gender = 0;
		int dick = 0;

		Color red = new Color(255, 0, 0);
		Color blue = new Color(124, 255, 250);
		Color green = new Color(0, 255, 10);
		Color black = new Color(0, 0, 0);
		// Color white = new Color(255, 255, 255);
		Color pink = new Color(255, 225, 135);
		Color blue2 = new Color(0, 0, 255);

		EZ.setBackgroundColor(pink);
//		EZImage menuBackground = EZ.addImage("menubackground.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);
		EZText title = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y / 3, "SEX SIMULATOR 2018", black, 50);
		EZText slogan = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y / 3 + 60, "don't be silly, wrap your willy", blue2,
				25);
		EZText credits = EZ.addText(MAX_SCREEN_X - 150, MAX_SCREEN_Y - 20, "Made by Kimberly Ogata and Ryan Ell", black,
				9);
		EZText instructions = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y - 120,
				"Press 'P' for a penis, or 'V' for a vagina", black, 20);
		instructions.setFont("emulogic.ttf");
		slogan.setFont("emulogic.ttf");
		title.setFont("emulogic.ttf");

		EZSound truckSound = EZ.addSound("bgm.wav");
		truckSound.loop();

		EZText move = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "use 'w' 'a' 's' 'd' to move", black, 20);
		move.setFont("emulogic.ttf");

		while (gameState == MENU) {

			// If I press and release my left mouse button, then....
			if (EZInteraction.wasKeyPressed(KeyEvent.VK_P)) {
				title.hide();
				instructions.hide();
				move.hide();
				slogan.hide();
				gameState = RUNNING;
				dick = random.nextInt(3);
				credits.hide();
			}
			if (EZInteraction.wasKeyPressed(KeyEvent.VK_V)) {
				title.hide();
				instructions.hide();
				move.hide();
				slogan.hide();
				gameState = RUNNING;
				dick = random.nextInt(3) + 3;
				credits.hide();
			}
			
			// Make sure to do this or else the graphics wonʻt refresh
			EZ.refreshScreen();
		}
		System.out.println("dick number = " + dick);
		
		// Draw the background.
		// EZImage gameBackground = EZ.addImage("sky.jpg", MAX_SCREEN_X / 2,
		// MAX_SCREEN_Y / 2);

		// Make a genital
		Genital myGenitals = new Genital(dick, 50, MAX_SCREEN_Y / 2);

		// Make germs, condoms and consent, and noconsent
		Obstacle germ[] = new Obstacle[MAX_GERMS];
		Obstacle condom[] = new Obstacle[MAX_CONDOMS];
		Obstacle consent[] = new Obstacle[MAX_YES];
		Obstacle sex[] = new Obstacle[MAX_SEX];
		Obstacle notconsent[] = new Obstacle[MAX_YES];

		for (int i = 0; i < MAX_GERMS; i++) {

			germ[i] = new Obstacle(MAX_SCREEN_X, MAX_SCREEN_Y, 0);

		}
		for (int i = 0; i < MAX_CONDOMS; i++) {

			condom[i] = new Obstacle(MAX_SCREEN_X, MAX_SCREEN_Y, 1);

		}
		for (int i = 0; i < MAX_YES; i++) {

			consent[i] = new Obstacle(MAX_SCREEN_X, MAX_SCREEN_Y, 2);
			notconsent[i] = new Obstacle(MAX_SCREEN_X, MAX_SCREEN_Y, 4);
		}
		for (int i = 0; i < MAX_SEX; i++) {

			sex[i] = new Obstacle(MAX_SCREEN_X, MAX_SCREEN_Y, 3);
		}

		boolean CONSENT = false;
		boolean CONDOM = false;

		EZText scoreboardConsent = EZ.addText("emulogic.ttf", MAX_SCREEN_X / 6, 60, "You don't have consent", red, 20);
		EZText scoreboardCondom = EZ.addText("emulogic.ttf", MAX_SCREEN_X / 6, 120, "You don't have a condom", red, 20);

		// Load sound effects
		EZSound chickenSound = EZ.addSound("squish.wav");

		EZSound crowSound = EZ.addSound("I Just Had Sex.wav");

		// This is the main game loop.
		while (gameState == RUNNING) {

			// Steer the Genitals
			myGenitals.ControlIt();

			if (myGenitals.getX() > MAX_SCREEN_X) {
				myGenitals.x = MAX_SCREEN_X;
			}
			if (myGenitals.getY() > MAX_SCREEN_Y) {
				myGenitals.y = MAX_SCREEN_Y;
			}
			if (myGenitals.getX() < 0) {
				myGenitals.x = 0;
			}
			if (myGenitals.getY() < 0) {
				myGenitals.y = 0;
			}

			// Check to see if Genitals has hit a germ.
			for (int i = 0; i < MAX_GERMS; i++) {

				// Move the germ.
				germ[i].move();

				// Check to see if the Genitals is touching the germ.
				if (CONDOM == false && ((germ[i].isInside(myGenitals.getX() - 16, myGenitals.getY() - 16))
						|| (germ[i].isInside(myGenitals.getX() + 16, myGenitals.getY() - 16))
						|| (germ[i].isInside(myGenitals.getX() - 16, myGenitals.getY() + 16))
						|| (germ[i].isInside(myGenitals.getX() + 16, myGenitals.getY() + 16)))) {

					int sick = random.nextInt(10);
					if (sick == 0) {
						truckSound.stop();
						gameState = INFECTED;
						chickenSound.play();
					}
				}

			}
		
			for (int i = 0; i < MAX_CONDOMS; i++) { //condoms touches genitals

				// Move the condoms.
				condom[i].move();

				// Check to see if the Genitals is touching the condom.
				if ((condom[i].isInside(myGenitals.getX() - 16, myGenitals.getY() - 16))
						|| (condom[i].isInside(myGenitals.getX() + 16, myGenitals.getY() - 16))
						|| (condom[i].isInside(myGenitals.getX() - 16, myGenitals.getY() + 16))
						|| (condom[i].isInside(myGenitals.getX() + 16, myGenitals.getY() + 16))) {
					scoreboardCondom.hide();
					scoreboardCondom = EZ.addText(MAX_SCREEN_X / 5, 100, "You have a condom!", green, 20);
					scoreboardCondom.setFont("emulogic.ttf");
					CONDOM = true;
				}

			}
			for (int i = 0; i < MAX_YES; i++) {

				// Move the condoms.
				consent[i].move();

				// Check to see if the Genitals is touching the consent.
				if ((consent[i].isInside(myGenitals.getX() - 16, myGenitals.getY() - 16))
						|| (consent[i].isInside(myGenitals.getX() + 16, myGenitals.getY() - 16))
						|| (consent[i].isInside(myGenitals.getX() - 16, myGenitals.getY() + 16))
						|| (consent[i].isInside(myGenitals.getX() + 16, myGenitals.getY() + 16))) {

					scoreboardConsent.hide();
					scoreboardConsent = EZ.addText(MAX_SCREEN_X / 5, 70, "You have consent!", green, 20);
					scoreboardConsent.setFont("emulogic.ttf");
					CONSENT = true;
				}

			}
			for (int i = 0; i < MAX_YES; i++) {

				// Move the condoms.
				notconsent[i].move();

				// Check to see if the Genitals is touching the notconsent.
				if ((notconsent[i].isInside(myGenitals.getX() - 16, myGenitals.getY() - 16))
						|| (notconsent[i].isInside(myGenitals.getX() + 16, myGenitals.getY() - 16))
						|| (notconsent[i].isInside(myGenitals.getX() - 16, myGenitals.getY() + 16))
						|| (notconsent[i].isInside(myGenitals.getX() + 16, myGenitals.getY() + 16))) {

					scoreboardConsent.hide();
					scoreboardConsent = EZ.addText(MAX_SCREEN_X / 6, 60, "You don't have consent", red, 20);
					scoreboardConsent.setFont("emulogic.ttf");
					CONSENT = false;
				}

			}
			for (int i = 0; i < MAX_SEX; i++) {

				// Move the condoms.
				sex[i].move();

				// Check to see if the Genitals is touching the sex.
				if ((sex[i].isInside(myGenitals.getX() - 16, myGenitals.getY() - 16))
						|| (sex[i].isInside(myGenitals.getX() + 16, myGenitals.getY() - 16))
						|| (sex[i].isInside(myGenitals.getX() - 16, myGenitals.getY() + 16))
						|| (sex[i].isInside(myGenitals.getX() + 16, myGenitals.getY() + 16))) {

					if (CONDOM == true && CONSENT == true) {
						gameState = WINS;

					}
					if (CONDOM == true && CONSENT == false) {
						gameState = RAPE;
					}
					if (CONSENT == true && CONDOM == false) {
						gameState = INFECTEDM;
					}
					if (CONSENT == false && CONDOM == false) {
						gameState = OH_NO;
					}

				}

			}

			EZ.refreshScreen();
		}
		if (gameState == RAPE) {
			EZ.addImage("black.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);

			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2 - 20,
					"That was rape! you didn't get consent!", red, 30);
//					EZText repeat = EZ.addText(MAX_SCREEN_X / 2, MAX_SCREEN_Y - 100, "Press 'R' to play again", blue,
//							20);
//					if (EZInteraction.wasKeyPressed(KeyEvent.VK_R)) {
//						gameState = MENU;
//					}
			truckSound.stop();
			chickenSound.play();
		}
		if (gameState == INFECTEDM) {
			int disease = random.nextInt(6);
			EZ.addImage("black.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);
			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2 - 45, "YOU JUST HAD CONSENSUAL SEX!", green,
					30);

			switch (disease) {
			case 0:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"but It started to burn when you pee and weird puss is coming out of your urethra", red, 25);
				break;
			case 1:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"but You notice bumps on and around your genitals", red, 25);
				break;
			case 2:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"but You notice an itchy red rash on and around your pubic hair", red, 30);
				break;
			case 3:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "and You seem fine. for now....", red,
						30);
				break;
			case 4:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "and You seem fine. for now....", red,
						30);
				break;
			case 5:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "and You seem fine. for now....", red,
						30);
				break;
			case 6:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "and You seem fine. for now....", red,
						30);

				break;
			}

			truckSound.stop();
			chickenSound.play();

		}
		if (gameState == INFECTED) {
			int disease = random.nextInt(2);
			EZ.addImage("black.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);

			switch (disease) {
			case 0:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"It started to burn when you pee and weird puss is coming out of your urethra", red, 20);
				break;
			case 1:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"You notice bumps on and around your genitals", red, 20);
				break;
			case 2:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"You notice an itchy red rash on and around your pubic hair", red, 20);
				break;

			}

			truckSound.stop();
			chickenSound.play();

		}

		if (gameState == WINS) {
			EZ.addImage("black.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);
			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "YOU JUST HAD SAFE, CONSENSUAL SEX!", green,
					30);
			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2 + 50, "CONGRATULATIONS!!", green, 30);
			truckSound.stop();
			crowSound.play();

			if (EZInteraction.wasKeyPressed(KeyEvent.VK_R)) {
				gameState = MENU;
			}
		}

		if (gameState == OH_NO) {
			EZ.addImage("black.png", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2);
			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2 - 50,
					"That was rape! you didn't get consent!", red, 30);
			int disease = random.nextInt(6);
			switch (disease) {
			case 0:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"and It has started to burn when you pee and weird puss is coming out of your urethra", red,
						19);
				break;
			case 1:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"and You notice bumps on and around your genitals", red, 20);
				break;
			case 2:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2,
						"and You notice an itchy red rash on and around your pubic hair", red, 20);
				break;
			case 3:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "but You seem fine. for now....", red,
						30);
				break;
			case 4:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "but You seem fine. for now....", red,
						30);
				break;
			case 5:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "but You seem fine. for now....", red,
						30);
				break;
			case 6:
				EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y / 2, "You seem fine. for now....", red, 30);
				break;
			}
//					

			if (EZInteraction.wasKeyPressed(KeyEvent.VK_R)) {
				gameState = MENU;
			}
			truckSound.stop();
			chickenSound.play();

		}
		while (gameState != END) {
			EZ.addText("emulogic.ttf", MAX_SCREEN_X / 2, MAX_SCREEN_Y - 100, "Press 'R' to play again", blue, 20);

			if (EZInteraction.wasKeyPressed(KeyEvent.VK_R)) {
				gameState = END;
				crowSound.stop();
				chickenSound.stop();

			}
		}

	}
}

// EZText repeat = EZ.addText(MAX_SCREEN_X, MAX_SCREEN_Y, "Press 'R' to play again", blue, 20);
//if(EZInteraction.wasKeyPressed(KeyEvent.VK_R)) {
//gameState = MENU;
//}
//
