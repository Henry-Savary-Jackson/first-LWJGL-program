#version 460 core

in vec3 fragmentColor;
in vec2 passTextureCoord;

out vec4 outputColor;

uniform sampler2D tex;

void main(){
	outputColor = texture(tex, passTextureCoord) * (vec4(fragmentColor,1.0) );
}