#include "CGFigure.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>

//
// FUNCI�N: CGFigure::~CGFigure()
//
// PROP�SITO: Destructor de la figura
//
CGFigure::~CGFigure()
{
	if (vertices != NULL) delete[] vertices;
	if (indexes != NULL) delete[] indexes;
	if (normals != NULL) delete[] normals;
	if (textures != NULL) delete[] textures;

	// Delete vertex buffer objects
	glDeleteBuffers(4, VBO);
	glDeleteVertexArrays(1, &VAO);
}

//
// FUNCI�N: CGFigure::InitBuffers()
//
// PROP�SITO: Crea el VAO y los VBO y almacena todos los datos
//            en la GPU.
//
void CGFigure::InitBuffers()
{
	// Create the Vertex Array Object
	glGenVertexArrays(1, &VAO);
	glBindVertexArray(VAO);

	// Create the Vertex Buffer Objects
	glGenBuffers(4, VBO);

	// Copy data to video memory
	// Vertex data
	int buffsize = sizeof(GLfloat) * numVertices * 3;
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, vertices, GL_STATIC_DRAW);

	// Normal data
	glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, normals, GL_STATIC_DRAW);

	// Texture coordinates
	buffsize = sizeof(GLfloat) * numVertices * 2;
	glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
	glBufferData(GL_ARRAY_BUFFER, buffsize, textures, GL_STATIC_DRAW);

	// Indexes
	buffsize = sizeof(GLushort) * numFaces * 3;
	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, VBO[INDEX_DATA]);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffsize, indexes, GL_STATIC_DRAW);

	delete[] vertices;
	delete[] indexes;
	delete[] normals;
	delete[] textures;

	vertices = NULL;
	indexes = NULL;
	normals = NULL;
	textures = NULL;

	glEnableVertexAttribArray(0); // Vertex position
	glBindBuffer(GL_ARRAY_BUFFER, VBO[VERTEX_DATA]);
	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 0, 0);

	glEnableVertexAttribArray(1); // Vertex normals
	glBindBuffer(GL_ARRAY_BUFFER, VBO[NORMAL_DATA]);
	glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 0, 0);

	glEnableVertexAttribArray(2); // Vertex textures
	glBindBuffer(GL_ARRAY_BUFFER, VBO[TEXTURE_DATA]);
	glVertexAttribPointer(2, 2, GL_FLOAT, GL_FALSE, 0, 0);

	location = glm::mat4(1.0f);
}

//
// FUNCI�N: CGFigure::SetMaterial(CGMaterial* m)
//
// PROP�SITO: Asigna el material de la figura
//
void CGFigure::SetMaterial(CGMaterial* mat)
{
	material = mat;
}

//
// FUNCI�N: CGFigure::ResetLocation()
//
// PROP�SITO: Asigna la posici�n inicial de la figura 
//
void CGFigure::ResetLocation()
{
	location = glm::mat4(1.0f);
}

//
// FUNCI�N: CGFigure::Translate(glm::vec3 t)
//
// PROP�SITO: A�ade un desplazamiento a la matriz de posici�n de la figura 
//
void CGFigure::Translate(glm::vec3 t)
{
	location = glm::translate(location, t);
}

//
// FUNCI�N: CGFigure::Rotate(GLfloat angle, glm::vec3 axis)
//
// PROP�SITO: A�ade una rotaci�n a la matriz de posici�n de la figura 
//
void CGFigure::Rotate(GLfloat angle, glm::vec3 axis)
{
	location = glm::rotate(location, glm::radians(angle), axis);
}

//
// FUNCI�N: CGFigure::Draw(CGShaderProgram * program, glm::mat4 projection, glm::mat4 view,  glm::mat4 shadowViewMatrix)
//
// PROP�SITO: Dibuja la figura
//
void CGFigure::Draw(CGShaderProgram* program, glm::mat4 projection, glm::mat4 view, glm::mat4 shadowViewMatrix)
{
	glm::mat4 mvp = projection * view * location;
	program->SetUniformMatrix4("MVP", mvp);
	program->SetUniformMatrix4("ViewMatrix", view);
	program->SetUniformMatrix4("ModelViewMatrix", view * location);
	program->SetUniformMatrix4("ShadowMatrix", shadowViewMatrix * location);
	material->SetUniforms(program);

	glBindVertexArray(VAO);
	glDrawElements(GL_TRIANGLES, numFaces * 3, GL_UNSIGNED_SHORT, NULL);
}

//
// FUNCI�N: CGFigure::DrawShadow(CGShaderProgram * program, glm::mat4 shadowMatrix)
//
// PROP�SITO: Dibuja la sombra de la figura
//
void CGFigure::DrawShadow(CGShaderProgram* program, glm::mat4 shadowMatrix)
{
	glm::mat4 mvp = shadowMatrix * location;
	program->SetUniformMatrix4("MVP", mvp);

	glBindVertexArray(VAO);
	glDrawElements(GL_TRIANGLES, numFaces * 3, GL_UNSIGNED_SHORT, NULL);
}


CGFigure::CGFigure()
{
	Pos = glm::vec3(0.0f, 0.0f, 0.0f);
	Dir = glm::vec3(0.0f, 1.0f, 0.0f);
	Up = glm::vec3(0.0f, 1.0f, 0.0f);
	Right = glm::vec3(1.0f, 0.0f, 0.0f);

	moveStep = 0.0f;
	upStep = 0.0f;
	turnStep = 1.0f;
	cosAngle = (GLfloat)cos(glm::radians(turnStep));
	sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

glm::mat4 CGFigure::ViewMatrix()
{
	return glm::lookAt(Pos, Pos - Dir, Up);
}

void CGFigure::SetPosition(GLfloat x, GLfloat y, GLfloat z)
{
	Pos = glm::vec3(x, y, z);
}

void CGFigure::SetDirection(glm::vec3 dir)
{
	Dir = dir;
	//Up = up;
	Right = glm::cross(Up, Dir);
}

void CGFigure::SetMoveStep(GLfloat step)
{
	moveStep = step;
}

void CGFigure::SetTurnStep(GLfloat step)
{
	turnStep = step;
	cosAngle = (GLfloat)cos(glm::radians(turnStep));
	sinAngle = (GLfloat)sin(glm::radians(turnStep));
}

glm::vec3 CGFigure::getRealPosition() {
	return glm::vec3(location[3][0], location[3][1], location[3][2]);
}

glm::vec3 CGFigure::getPosition()
{
	return Pos;
}

glm::vec3 CGFigure::getDirection()
{
	return Dir;
}

glm::vec3 CGFigure::getUpDirection()
{
	return Up;
}

GLfloat CGFigure::getMoveStep()
{
	return moveStep;
}

GLfloat CGFigure::getTurnStep()
{
	return turnStep;
}

void CGFigure::MoveFront()
{
	if (moveStep > 0.0f)
		moveStep -= 0.0008f;

	else if (moveStep < 0.0f)
		moveStep += 0.0008f;

	if (this->getRealPosition().y > 2.3f)
		upStep -= 0.005f;
	else if (upStep < 0.0f)
		upStep = -upStep * 0.8;

	Pos = glm::vec3(0.0f, 0.0f, 0.0f);
	Pos += glm::vec3(moveStep * Dir.x, upStep, moveStep * Dir.z);
	Translate(Pos);

}

void CGFigure::SetUpStep(GLfloat step)
{
	upStep = step;
}