package main;

import org.lwjgl.glfw.GLFW;

import engine.graphics.Mesh;
import engine.graphics.Renderer;
import engine.graphics.Vertex;
import engine.io.Input;
import engine.io.Window;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public final int width = 1280;
	public final int height = 720;
	
	public Renderer renderer =  new Renderer();;
	public Mesh mesh= new Mesh(new Vertex[] {
			new Vertex(-0.5f, 1f, 0.0f),
			new Vertex(0.5f, 0.8f, 0.0f),
			new Vertex(0.8f, -0.97f, 0.0f),
			new Vertex(-0.4f, -0.8f, 0.0f)
			
	}, new int[] {
			0,1,2,
			0,3,2
	});

	public void start() {
		if (game == null)
			game = new Thread(this, "game loop");
		game.start();
	}

	public void init() {
		System.out.println("Starting...");
		window = new Window(width, height, "game");
		window.setBackgroundColour(0, 0f,  0.33f);
		window.create();
		mesh.create();
	}

	public void run() {
		init();
		while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
			update();
			render();
			if (Input.isKeyDown(GLFW.GLFW_KEY_F11))
				window.setFullScreen(!window.isFullScreen());
		}
		window.destroy();
	}

	private void update() {
		window.update();
		if (Input.isMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
			System.out.println(String.format("Left Click: X%f Y%f ", Input.getScrollX(), Input.getScrollY()));

	}

	private void render() {
		renderer.renderMesh(mesh);
		window.swapBuffers();
		
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
