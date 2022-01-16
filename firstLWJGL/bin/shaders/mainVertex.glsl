#version 460 core

in vec3 position;
in vec3 colour;
in vec2 texturePos;

out vec3 fragmentColor;
out vec2 passTextureCoord;

uniform mat4 model;
uniform mat4 proj;

void main(){
	gl_Position = proj * model * vec4(position,1.0) ;
	fragmentColor = colour;
	passTextureCoord = texturePos;
}