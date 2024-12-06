#pragma once

#include <GL/glew.h>
#include "CGShaderProgram.h"
#include "CGScene.h"
#include "CGSkybox.h"
#include "CGCamera.h"

class CGModel
{
public:
	void initialize(int w, int h);
	void finalize();
	void render();
	void update();
	void key_pressed(int key);
	void resize(int w, int h);

private:
	CGShaderProgram* sceneProgram;
	CGShaderProgram* skyboxProgram;
	CGShaderProgram* shadowProgram;
	CGScene* scene;
	CGCamera* camera;
	CGSkybox* skybox;
	glm::mat4 projection;

	GLsizei wndWidth;
	GLsizei wndHeight;
	GLuint shadowFBO;
	GLuint depthTexId;
	GLuint offsetTexId;
	bool fueraCoche = false;
	bool fueraBalon = false;

	bool InitShadowMap();
	void CameraConstraints();
	void BuildOffsetTex(int texSize, int samplesU, int samplesV);
	float jitter();
	void colocarCamara();
	void CarConstraints();
	void BallConstraints();
	void impactoCochePelota();
};
