package engine.graphics;

import engine.math.Vector3f;

public class Vertex {
	private Vector3f pos, colour;
	
	public Vertex(Vector3f position, Vector3f colour) {
		pos = position;
		this.colour = colour;
	}

	public Vector3f getPos() {
		return pos;
	}
	
	public Vector3f getColour(){
		return colour;
	}

}
