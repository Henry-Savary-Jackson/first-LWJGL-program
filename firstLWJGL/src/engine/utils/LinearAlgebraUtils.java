package engine.utils;

import engine.math.Vector3f;

public class LinearAlgebraUtils {

	public static Vector3f addVertices(Vector3f v1, Vector3f v2 ) {
		return new Vector3f(v1.getX() + v2.getX(), v1.getY() + v2.getY(),v1.getZ() + v2.getZ());
	};
	
	public static Vector3f dotProduct(Vector3f v1, Vector3f v2 ) {
		return new Vector3f(v1.getX() * v2.getX(), v1.getY() * v2.getY(),v1.getZ() * v2.getZ());
	}
}
