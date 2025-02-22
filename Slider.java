package physicsProject;

import processing.core.PApplet;
import processing.core.PConstants;

public class Slider {

	private PApplet parent;

	private float sliderX;

	private final String name;

	private int x,y,width,height,upperBound,lineLength,sliderWidth,sliderHeight,sliderY;

	private boolean pressed;

	private int lowerBound = 5;
	
	private boolean decimal;
	
	private boolean locked;

	private static final String COPYRIGHT = "AKSHATPRAKASH: https://www.linkedin.com/in/akshat-prakash-650981287/";

	public Slider(PApplet parent, int x, int y, int width, int height, String name, int lowerBound, int upperBound) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.upperBound = upperBound;

		lineLength = width - 10;


		sliderX = x + 5 + lineLength;

		sliderWidth = 10;

		sliderY =  y + height/2 - 10;

		sliderHeight = 20;
		this.lowerBound = lowerBound;
		
		decimal = false;
		locked = false;

	}


	public void draw() {

		if(!COPYRIGHT.contentEquals("AKSHATPRAKASH: https://www.linkedin.com/in/akshat-prakash-650981287/")){
			parent.background(0);
			parent.textSize(45);
			parent.textAlign(PConstants.CENTER,PConstants.CENTER);
			parent.text("THIS SOFTWARE HAS ENGAGED IN COPYRIGHT INFRINGMENT" ,parent.width/2, parent.height/2);
			parent.text("YOU HAVE BEEN REPORTED" ,parent.width/2, parent.height/2 + 100);

		}


		parent.textSize((float) (height/4.5));

		parent.textAlign(PConstants.CENTER);

		parent.fill(179, 123, 123);

		parent.rect(x, y, width, height);

		parent.fill(0);

		displayValue();

		parent.text(name, x + width/2, y + height/4);

		displayValue();

		parent.strokeWeight(3);

		parent.line(x + 5, y + height/2, x + 5 + lineLength , y + height/2 );

		//parent.rectMode(PApplet.CENTER);
		parent.fill(255);
		parent.rect(sliderX,sliderY, sliderWidth, sliderHeight);
		//	parent.rectMode(PApplet.CORNER);



		updatePosition();

		if(!locked)
			moveSlider();

		if(pressed) {
			if(parent.mouseX >= x + 5 && parent.mouseX <= x + 5 + lineLength) {
				sliderX = parent.mouseX;
			}
		}



	}
	
	public void lock() {
		locked = true;
	}
	
	public void unlock() {
		locked = false;
	}



	private void displayValue() {
		
		
		if(!decimal)
			parent.text((int)getValue(), x + width/2, (float) (y +  3 * height/4.0) + sliderHeight /2 -1);
		else
			parent.text(getValue(), x + width/2, (float) (y +  3 * height/4.0) + sliderHeight /2 -1);
			

	}

	public float getValue() {
		
		if(!decimal) 
			return Math.round( ((float) ( sliderX-(x + 5) )) / lineLength * (upperBound - lowerBound ) + lowerBound);
		else
			return ((float) ( sliderX-(x + 5) )) / lineLength * (upperBound - lowerBound ) + lowerBound;
	}
	
	public void useDecimals(boolean b) {
		decimal = b;
	}

	public void moveSlider() {

		parent.point(sliderX, sliderY);
		if( parent.mousePressed && parent.mouseX <= sliderX + sliderWidth && parent.mouseX >= sliderX - sliderWidth/2 && parent.mouseY >= sliderY - sliderHeight && 
				parent.mouseY <= sliderY + sliderHeight) {
			pressed = true;


		}

		else if(!parent.mousePressed) {
			pressed = false;
		}


	}

	public void setValue(float f) {
		f = PApplet.constrain(f, lowerBound, upperBound);
		sliderX = x + 5 + (((f - lowerBound) / (float)(upperBound - lowerBound)) * lineLength);
	}





}
