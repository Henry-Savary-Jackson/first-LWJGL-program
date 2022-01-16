package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class Window {
	public int width, height;
	private int[] windowX = new int[1];
	private int[] windowY = new int[1];
	public String title;
	private long window;
	public int frames;
	public long time;
	private Input input = new Input();
	private Vector3f rgbBackground = new Vector3f(0f,0.1f,0.35f);
	private GLFWWindowSizeCallback sizeCallBack;
	private boolean resized = false ;
	private boolean isFullScreen ;
	private Matrix4f projection;
	
	public Window(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		projection = Matrix4f.projection((float)this.width/ (float)this.height, 100.0f , 0.2f, 500.0f);
	}
	
	public void create() {
		if (!GLFW.glfwInit()) {
			System.err.println("ERROR: GFLW not initialized");
			return;
		} 
		
		window = GLFW.glfwCreateWindow(width, height, title, isFullScreen?GLFW.glfwGetPrimaryMonitor(): 0, 0);
		
		if (window == 0) {
			System.err.println("ERROR: window not created");
			return;
		}
		
		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		windowX[0] =  (videoMode.width()-width)/2;
		windowY[0]=  (videoMode.height()-height)/2;
	    GLFW.glfwSetWindowPos(window, windowX[0], windowY[0]);
	    GLFW.glfwMakeContextCurrent(window);
	    GL.createCapabilities();
	    GL11.glEnable(GL11.GL_DEPTH_TEST);
	    
	    GLFW.glfwSetKeyCallback(window,input.getKeyBoard());
	    GLFW.glfwSetMouseButtonCallback(window, input.getMouseButton());
	    GLFW.glfwSetCursorPosCallback(window, input.getCursorMove());
	    GLFW.glfwSetWindowSizeCallback(window, sizeCallBack);
	    GLFW.glfwSetScrollCallback(window, input.getMouseScroll());
	    
	    createCallbacks();
	    
	    GLFW.glfwShowWindow(window);
	    
	    GLFW.glfwSwapInterval(1);

		time = System.currentTimeMillis();
	}
	
	public void update() {
		if (resized)
		{
			GL11.glViewport(0,0,width,height);
			resized = false;
		}
		GL11.glClearColor(rgbBackground.getX(),rgbBackground.getY(), rgbBackground.getZ(), 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GLFW.glfwPollEvents();
		frames ++;
		if (System.currentTimeMillis() > time + 1000) {
			GLFW.glfwSetWindowTitle(window, String.format("%s, FPS:%d",title, frames));
			time = System.currentTimeMillis();
			frames = 0;
		}
	}
	
	private void createCallbacks() {
		sizeCallBack = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int w, int h) {
				width = w;
				height = h;
				resized = true;
			}
		};
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}
	
	public void destroy() {
		input.destroy();
		GLFW.glfwWindowShouldClose(window);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	public void setBackgroundColour(float r, float g, float b){
		rgbBackground = new Vector3f(r,g,b);
	}

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
		if (this.isFullScreen) {
			GLFW.glfwGetWindowPos(window, windowX, windowY);
			GLFW.glfwSetWindowMonitor(window, GLFW.glfwGetPrimaryMonitor(), 0, 0, width, height, 0);
		} else {
			GLFW.glfwSetWindowMonitor(window, 0, windowX[0], windowY[0], width, height, 0);
		}
		this.resized = true;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Matrix4f getProjection() {
		return projection;
	}

	public String getTitle() {
		return title;
	}

	public long getWindow() {
		return window;
	}
}
