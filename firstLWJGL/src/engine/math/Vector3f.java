package engine.math;

public class Vector3f {

	private float x, y, z;
	
	public Vector3f(float x , float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3f addVectors(Vector3f v1, Vector3f v2 ) {
		return new Vector3f(v1.getX() + v2.getX(), v1.getY() + v2.getY(),v1.getZ() + v2.getZ());
	}
	
	public void set(float x , float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	
}
