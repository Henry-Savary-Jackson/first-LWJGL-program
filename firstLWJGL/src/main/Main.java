package main;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Material;
import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Shader;
import engine.graphics.Vertex;
import engine.io.Input;
import engine.io.Window;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public final int width = 1280;
	public final int height = 720;
	public Shader shader;
	public Renderer renderer ;
	public Mesh mesh= new Mesh(new Vertex[] {
			new Vertex(new Vector3f(-0.9f, 0.5f, 0.0f), new Vector3f(1.0f,1.0f,1.0f), new Vector2f(0.0f,0.0f) ),
			new Vertex(new Vector3f(-0.5f, -0.1f, 0.0f), new Vector3f(0f,1.0f,1.0f), new Vector2f(0.0f,1.0f) ),
			new Vertex(new Vector3f(0.25f, -0.4f, 0.0f), new Vector3f(1.0f,0f,1.0f), new Vector2f(1.0f,1.0f) ),
			new Vertex(new Vector3f(0.5f, 0.9f, 0.0f), new Vector3f(1.0f,1.0f,0f), new Vector2f(1.0f,0.0f) )
			
	}, new int[] {
			0,1,2,3,2,0
	}, 
	new Material("/textures/joyeux anniversaire.jpg")
	);

	public void start() {
		if (game == null)
			game = new Thread(this, "game loop");
		game.start();
	}

	public void init() {
		System.out.println("Starting...");
		window = new Window(width, height, "game");
		shader = new Shader("/shaders/mainVertex.glsl","/shaders/mainFragment.glsl");
		renderer = new Renderer(shader);
		window.setBackgroundColour(0, 0f,  0.33f);
		window.create();
		mesh.create();
		shader.create();
	}

	public void run() {
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11))
				window.setFullScreen(!window.isFullScreen());
		}
		close();
	}
	
	private void close() {
		window.destroy();
		mesh.destroy();
		shader.destroy();
	}

	private void update() {
		window.update();
		if (Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
			System.out.println(String.format("Left Click: X%f Y%f ", Input.getMouseX(), Input.getMouseY()));

	}

	private void render() {
		renderer.renderMesh(mesh);
		window.swapBuffers();
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
