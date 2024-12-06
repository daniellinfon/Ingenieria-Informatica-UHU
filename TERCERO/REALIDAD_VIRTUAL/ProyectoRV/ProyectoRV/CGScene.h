#pragma once

#include <GL/glew.h>
#include <glm/glm.hpp>
#include "CGShaderProgram.h"
#include "CGLight.h"
#include "CGMaterial.h"
#include "CGFigure.h"
#include "CGObject.h"

class CGScene {
public:
    CGScene();
    ~CGScene();
    void Draw(CGShaderProgram* program, glm::mat4 proj, glm::mat4 view, glm::mat4 shadowViewMatrix);
    void DrawShadow(CGShaderProgram* program, glm::mat4 shadowMatrix);
    CGObject* getCoche() const;
    CGFigure* getPelota() const;
    glm::mat4 GetLightViewMatrix();

private:
    CGFigure* ground;
    CGFigure* pared1;
    CGFigure* pared2;
    CGFigure* pared3;
    CGFigure* pared4;
    CGFigure* paredIzq;
    CGFigure* paredDer;
    CGFigure* paredMid;
    CGFigure* porteria;
    CGFigure* figPelota;
    CGObject* figCoche;
    CGMaterial* matg;
    CGMaterial* matParedes;
    CGMaterial* matParedes2;
    CGMaterial* matParedMid;
    CGMaterial* matPelota;
    CGMaterial* matCoche;
    CGMaterial* matPorteria;
    CGLight* light;
};
