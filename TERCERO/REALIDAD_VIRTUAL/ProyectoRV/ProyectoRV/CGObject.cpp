#include "CGObject.h"
#include <GL/glew.h>
#include <FreeImage.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

//
// FUNCIÓN: CGObject::ResetLocation()
//
// PROPÓSITO: Asigna la posición inicial del objecto 
//
void CGObject::ResetLocation()
{
	model = glm::mat4(1.0f);
}

//
// FUNCIÓN: CGObject::SetLocation(glm::mat4 loc)
//
// PROPÓSITO: Asigna la posición del objecto 
//
void CGObject::SetLocation(glm::mat4 loc)
{
	model = loc;
}

//
// FUNCIÓN: CGObject::GetLocation()
//
// PROPÓSITO: Obtiene la posición del objecto 
//
glm::mat4 CGObject::GetLocation()
{
	return model;
}

//
// FUNCIÓN: CGObject::Translate(glm::vec3 t)
//
// PROPÓSITO: Añade un desplazamiento a la matriz de posición del objeto 
//
void CGObject::Translate(glm::vec3 t)
{
	model = glm::translate(model, t);
}

//
// FUNCIÓN: CGObject::Rotate(GLfloat angle, glm::vec3 axis)
//
// PROPÓSITO: Añade una rotación a la matriz de posición del objeto 
//
void CGObject::Rotate(GLfloat angle, glm::vec3 axis)
{
	model = glm::rotate(model, glm::radians(angle), axis);
}

//
// FUNCIÓN: CGObject::Draw(CGShaderProgram * program, glm::mat4 projection, glm::mat4 view)
//
// PROPÓSITO: Dibuja el objeto
//
void CGObject::Draw(CGShaderProgram* program, glm::mat4 projection, glm::mat4 view)
{
	int num = GetNumPieces();
	for (int i = 0; i < num; i++)
	{
		GetPiece(i)->Draw(program, projection, view, model);
	}
}

//
// FUNCIÓN: CGObject::Draw(CGShaderProgram * program, glm::mat4 projection, glm::mat4 view)
//
// PROPÓSITO: Dibuja el objeto
//
void CGObject::DrawShadow(CGShaderProgram* program, glm::mat4 shadowMatrix)
{
    int num = GetNumPieces();
    for (int i = 0; i < num; i++)
    {
        GetPiece(i)->DrawShadow(program, shadowMatrix * model);
    }
}

void CGObject::MoverHaciaDelante() {
	
    SetMoveStep(this->getMoveStep() + 0.2f);
}

void CGObject::MoverHaciaDetras() {
	
    SetMoveStep(this->getMoveStep() - 0.2f);
}

void CGObject::MoverHaciaIzq() {
	
	Rotate(2.0f, glm::vec3(0.0f, 0.0f, 1.0f));
}

void CGObject::MoverHaciaDer() {
	
	Rotate(-2.0f, glm::vec3(0.0f, 0.0f, 1.0f));
}

CGObject::CGObject()
{
	Pos = glm::vec3(0.0f, 0.0f, 0.0f);
	Dir = glm::vec3(0.0f, 1.0f, 0.0f);
	Up = glm::vec3(0.0f, 1.0f, 0.0f);
	Right = glm::vec3(1.0f, 0.0f, 0.0f);

	moveStep = 0.0f;
	turnStep = 1.0f;
	cosAngle = (GLfloat)cos(glm::radians(turnStep));
	sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

glm::mat4 CGObject::ViewMatrix()
{
    return glm::lookAt(Pos, Pos - Dir, Up);
}

void CGObject::SetPosition(GLfloat x, GLfloat y, GLfloat z)
{
    Pos = glm::vec3(x, y, z);
}

void CGObject::SetDirection(GLfloat xD, GLfloat yD, GLfloat zD, GLfloat xU, GLfloat yU, GLfloat zU)
{
    Dir = glm::vec3(xD, yD, zD);
    Up = glm::vec3(xU, yU, zU);
    Right = glm::cross(Up, Dir);
}

void CGObject::SetMoveStep(GLfloat step)
{
    moveStep = step;
}

glm::vec3 CGObject::getRealPosition() {
    return glm::vec3(model[3][0], model[3][1], model[3][2]);
}

glm::vec3 CGObject::getPosition()
{
    return Pos;
}

glm::vec3 CGObject::getDirection()
{
    return Dir;
}

glm::vec3 CGObject::getForwardDirection() {
    //return glm::normalize(glm::vec3(model[2][0], model[2][1], model[2][2]));
    return glm::normalize(glm::vec3(model[1][0], model[1][1], model[1][2]));
}


glm::vec3 CGObject::getRightDirection() {
    return glm::normalize(glm::vec3(model[0][0], model[0][1], model[0][2]));
}

glm::vec3 CGObject::getUpDirection()
{
    //return glm::normalize(glm::vec3(model[1][0], model[1][1], model[1][2]));
    return glm::normalize(glm::vec3(model[2][0], model[2][1], model[2][2]));
}

GLfloat CGObject::getMoveStep()
{
    return moveStep;
}

void CGObject::MoveFront()
{
    if (moveStep > 0.0f)
        moveStep -= 0.002f;
    else if (moveStep < 0.0f)
        moveStep += 0.002f;

    Pos = glm::vec3(0.0f, 0.0f, 0.0f);
    Pos += moveStep * Dir;
    Translate(Pos);
}


