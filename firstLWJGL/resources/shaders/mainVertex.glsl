#version 460 core

in vec3 position;
in vec3 colour;
in vec2 texturePos;

out vec3 fragmentColor;
out vec2 passTextureCoord;

uniform float scale;

void main(){
	gl_Position = vec4(position,1.0) * vec4(scale,scale, scale, 1.0);
	fragmentColor = colour;
	passTextureCoord = texturePos;
}