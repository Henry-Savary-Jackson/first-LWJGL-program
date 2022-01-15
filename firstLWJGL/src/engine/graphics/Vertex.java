package engine.graphics;

import engine.math.Vector2f;
import engine.math.Vector3f;

public class Vertex {
	private Vector3f pos, colour;
	private Vector2f  textureCoords  ;
	
	public Vertex(Vector3f position, Vector3f colour, Vector2f textureCoords) {
		pos = position;
		this.colour = colour;
		this.textureCoords = textureCoords;
	}

	public Vector3f getPos() {
		return pos;
	}
	
	public Vector3f getColour(){
		return colour;
	}

	public Vector2f getTextureCoords() {
		return textureCoords;
	}
}
