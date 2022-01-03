package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private static double mouseX, mouseY;
	private static double scrollX, scrollY;
	
	private GLFWKeyCallback keyBoard;
	private GLFWCursorPosCallback cursorMove;
	private GLFWMouseButtonCallback mouseButton;
	private GLFWScrollCallback mouseScroll;
	
	public Input() {
		keyBoard = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scanCode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_RELEASE) ;
			}
		};
		
		cursorMove = new GLFWCursorPosCallback() {
			@Override
			public void invoke(long window, double xPos, double yPos) {
				mouseX = xPos;
				mouseY = yPos;
			}
		};
		mouseButton = new GLFWMouseButtonCallback() {
			@Override
			public void invoke(long window, int button, int action, int mods) {
				buttons[button] =  (action != GLFW.GLFW_RELEASE);
			}
		};
		
		mouseScroll = new GLFWScrollCallback() {

			@Override
			public void invoke(long window, double X, double Y) {
				scrollX += X;
				scrollY += Y;
			}
		};
	}
	
	public static boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public static boolean isMouseButtonDown(int button) {
		return buttons[button];
	}
	
	public void destroy() {
		keyBoard.free();
		cursorMove.free();
		mouseButton.free();
		mouseScroll.free();
	}

	public static double getMouseX() {
		return mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}

	public GLFWKeyCallback getKeyBoard() {
		return keyBoard;
	}

	public GLFWCursorPosCallback getCursorMove() {
		return cursorMove;
	}

	public GLFWMouseButtonCallback getMouseButton() {
		return mouseButton;
	}

	public static boolean[] getKeys() {
		return keys;
	}

	public static boolean[] getButtons() {
		return buttons;
	}

	public static double getScrollX() {
		return scrollX;
	}

	public static double getScrollY() {
		return scrollY;
	}

	public GLFWScrollCallback getMouseScroll() {
		return mouseScroll;
	}
}