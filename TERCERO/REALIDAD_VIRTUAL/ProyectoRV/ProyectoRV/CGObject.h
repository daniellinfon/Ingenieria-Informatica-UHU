#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "CGShaderProgram.h"
#include "CGPiece.h"

//
// CLASE: CGObject
//
// DESCRIPCIÓN: Clase abstracta que representa un objeto formado por varias piezas
// 
class CGObject
{
protected:
	glm::mat4 model;  // Model matrix

public:
	void ResetLocation();
	void Translate(glm::vec3 t);
	void Rotate(GLfloat angle, glm::vec3 axis);
	void SetLocation(glm::mat4 loc);
	glm::mat4 GetLocation();
	void Draw(CGShaderProgram* program, glm::mat4 projection, glm::mat4 view);
	void DrawShadow(CGShaderProgram* program, glm::mat4 shadowMatrix);
	void MoverHaciaDelante();
	void MoverHaciaDetras();
	void MoverHaciaIzq();
	void MoverHaciaDer();

	CGObject();

	glm::mat4 ViewMatrix();
	void SetPosition(GLfloat x, GLfloat y, GLfloat z);
	void SetDirection(GLfloat xD, GLfloat yD, GLfloat zD, GLfloat xU, GLfloat yU, GLfloat zU);
	void SetMoveStep(GLfloat step);
	glm::vec3 getRealPosition();
	glm::vec3 getPosition();
	glm::vec3 getDirection();
	glm::vec3 getForwardDirection();
	glm::vec3 getUpDirection();
	GLfloat getMoveStep();
	void MoveFront();
	glm::vec3 getRightDirection();

	virtual int GetNumPieces() = 0;
	virtual CGPiece* GetPiece(int i) = 0;

	glm::vec3 Pos;
	glm::vec3 Dir;
	glm::vec3 Up;
	glm::vec3 Right;

	GLfloat moveStep;
	GLfloat turnStep;
	GLfloat cosAngle;
	GLfloat sinAngle;

};

