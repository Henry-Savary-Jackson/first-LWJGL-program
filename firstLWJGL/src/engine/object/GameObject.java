package engine.object;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Mesh;
import engine.graphics.Shader;
import engine.io.Input;
import engine.math.Vector3f;

public class GameObject {
	
	private Vector3f position, axis ,scale;
	private float angle;
	private Mesh mesh;
	private Shader shader;
	
	private float xSpeed = 0f;
	private float ySpeed = 0f;


	public GameObject(Vector3f position, Vector3f axis, Vector3f scale, Mesh mesh, Shader shader, float angle) {
		this.position = position;
		this.axis = axis;
		this.scale = scale;
		this.mesh = mesh;
		this.angle = angle;
		this.shader = shader;
	}
	
	public void create() {
		mesh.create();
		shader.create();
	}
	
	public void update() {
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
			xSpeed =-0.05f;
		} else if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
			xSpeed = 0.05f;
		}else {
			xSpeed = 0f;
		}
		
		if (Input.isKeyDown(GLFW.GLFW_KEY_DOWN)) {
			ySpeed =-0.05f;
		} else if (Input.isKeyDown(GLFW.GLFW_KEY_UP)) {
			ySpeed = 0.05f;
		}else {
			ySpeed = 0f;
		}
		
		if (Input.isKeyDown(GLFW.GLFW_KEY_A)) {
			angle -= 5f;
		} else if (Input.isKeyDown(GLFW.GLFW_KEY_D)) {
			angle += 5f;
		}
		position = Vector3f.addVectors(position, new Vector3f(xSpeed,ySpeed,0f));
	}


	public Vector3f getPosition() {
		return position;
	}
	
	public float getAngle() {
		return angle;
	}


	public Vector3f getAxis() {
		return axis;
	}


	public Vector3f getScale() {
		return scale;
	}


	public Mesh getMesh() {
		return mesh;
	}

	public Shader getShader() {
		return shader;
	}

}