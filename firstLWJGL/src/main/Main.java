package main;

import org.lwjgl.glfw.GLFW;

import engine.io.Input;
import engine.io.Window;

public class Main implements Runnable {
	public Thread game;
	public static Window window;
	public final int width = 1280;
	public final int height = 720;

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
		window.swapBuffers();
	}

	public static void main(String[] args) {
		new Main().start();

	}

}
