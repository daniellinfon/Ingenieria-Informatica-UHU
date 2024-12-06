#include "CGScene.h"
#include <GL/glew.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include "CGShaderProgram.h"
#include "CGFigure.h"
#include "CGLight.h"
#include "CGMaterial.h"
#include "CGCube.h"
#include "CGSphere.h"
#include "CGGround.h"
#include "CGSlotCar.h"
#include "CGPorteria.h"
#include "resource.h"

//
// FUNCIÓN: CGScene::CGScene()
//
// PROPÓSITO: Construye el objeto que representa la escena
//
CGScene::CGScene()
{
    glm::vec3 Ldir = glm::vec3(1.0f, -0.8f, -1.0f);
    Ldir = glm::normalize(Ldir);
    light = new CGLight();
    light->SetLightDirection(Ldir);
    light->SetAmbientLight(glm::vec3(0.2f, 0.2f, 0.2f));
    light->SetDifusseLight(glm::vec3(0.8f, 0.8f, 0.8f));
    light->SetSpecularLight(glm::vec3(1.0f, 1.0f, 1.0f));

    //Materiales
    matg = new CGMaterial();
    matg->SetAmbientReflect(3.0f, 3.0f, 3.0f);
    matg->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matg->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matg->SetShininess(16.0f);
    matg->InitTexture(IDR_IMAGE3);

    matParedes = new CGMaterial();
    matParedes->SetAmbientReflect(5.0f, 5.0f, 5.0f);
    matParedes->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matParedes->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matParedes->SetShininess(16.0f);
    matParedes->InitTexture(IDR_IMAGE1);

    matParedes2 = new CGMaterial();
    matParedes2->SetAmbientReflect(5.0f, 5.0f, 5.0f);
    matParedes2->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matParedes2->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matParedes2->SetShininess(16.0f);
    matParedes2->InitTexture(IDR_IMAGE2);

    matParedMid = new CGMaterial();
    matParedMid->SetAmbientReflect(4.0f, 4.0f, 4.0f);
    matParedMid->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matParedMid->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matParedMid->SetShininess(16.0f);
    matParedMid->InitTexture("textures/paredMid.jpg");

    matPelota = new CGMaterial();
    matPelota->SetAmbientReflect(2.0f, 2.0f, 2.0f);
    matPelota->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matPelota->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matPelota->SetShininess(16.0f);
    matPelota->InitTexture("textures/ball2.jpg");

    matPorteria = new CGMaterial();
    matPorteria->SetAmbientReflect(3.0f, 3.0f, 3.0f);
    matPorteria->SetDifusseReflect(1.0f, 1.0f, 1.0f);
    matPorteria->SetSpecularReflect(0.8f, 0.8f, 0.8f);
    matPorteria->SetShininess(16.0f);
    matPorteria->InitTexture("textures/porteria.jpeg");

    //Suelo
    ground = new CGGround(40.0f, 100.0f);
    ground->SetMaterial(matg);

    //Pared 1
    pared1 = new CGGround(15.0f, 100.0f);
    pared1->Translate(glm::vec3(-40.0f, 15.0f, 0.0f));
    pared1->Rotate(-90.0f, glm::vec3(0.0f, 0.0f, 1.0f));
    pared1->SetMaterial(matParedes);

    //Pared 2
    pared2 = new CGGround(15.0f, 100.0f);
    pared2->Translate(glm::vec3(40.0f, 15.0f, 0.0f));
    pared2->Rotate(90.0f, glm::vec3(0.0f, 0.0f, 1.0f));
    pared2->SetMaterial(matParedes);


    //Pared 3
    pared3 = new CGGround(40.0f, 15.0f);
    pared3->Translate(glm::vec3(0.0f, 15.0f, 100.0f));
    pared3->Rotate(-90.0f, glm::vec3(1.0f, 0.0f, 0.0f));
    pared3->SetMaterial(matParedes2);

    //Porteria
    porteria = new CGPorteria(8.0f, 10.0f);
    porteria->Translate(glm::vec3(12.0f, 8.0f, -110.0f));
    porteria->Rotate(180.0f, glm::vec3(0.0f, 1.0f, 0.0f));
    porteria->SetMaterial(matPorteria);

    //Paredes Porteria
    paredIzq = new CGGround(10.0f, 15.0f);
    paredIzq->Translate(glm::vec3(-30.0f, 15.0f, -100.0f));
    paredIzq->Rotate(90.0f, glm::vec3(1.0f, 0.0f, 0.0f));
    paredIzq->SetMaterial(matParedes2);

    paredDer = new CGGround(10.0f, 15.0f);
    paredDer->Translate(glm::vec3(30.0f, 15.0f, -100.0f));
    paredDer->Rotate(90.0f, glm::vec3(1.0f, 0.0f, 0.0f));
    paredDer->SetMaterial(matParedes2);

    paredMid = new CGGround(20.0f,7.0f);
    paredMid->Translate(glm::vec3(0.0f, 23.0f, -100.0f));
    paredMid->Rotate(90.0f, glm::vec3(1.0f, 0.0f, 0.0f));
    paredMid->SetMaterial(matParedMid);

    //Coche
    figCoche = new CGSlotCar();
    figCoche->Rotate(-90.0f, glm::vec3(1.0f, 0.0f, 0.0f));
    figCoche->Translate(glm::vec3(0.0f, -50.0f, 0.0f));

    //Pelota
    figPelota = new CGSphere(20, 40, 2.3f);
    figPelota->SetMaterial(matPelota);
    figPelota->Translate(glm::vec3(0.0f, 2.3f, 0.0f));


   
}

//
// FUNCIÓN: CGScene3:~CGScene()
//
// PROPÓSITO: Destruye el objeto que representa la escena
//
CGScene::~CGScene()
{
    delete ground;
    delete pared1;
    delete pared2;
    delete pared3;
    delete pared4;
    delete paredIzq;
    delete paredDer;
    delete paredMid;
    delete figPelota;
    delete figCoche;
    delete porteria;
    delete light;
    delete matg;
    delete matPelota;
    delete matParedes;
    delete matParedes2;
    delete matParedMid;
    delete matCoche;
    delete matPorteria;
}

//
// FUNCIÓN: CGScene::Draw()
//
// PROPÓSITO: Dibuja la escena
//
void CGScene::Draw(CGShaderProgram* program, glm::mat4 proj, glm::mat4 view, glm::mat4 shadowViewMatrix)
{
    light->SetUniforms(program);

    ground->Draw(program, proj, view, shadowViewMatrix);
    pared1->Draw(program, proj, view, shadowViewMatrix);
    pared2->Draw(program, proj, view, shadowViewMatrix);
    pared3->Draw(program, proj, view, shadowViewMatrix);
    paredIzq->Draw(program, proj, view, shadowViewMatrix);
    paredDer->Draw(program, proj, view, shadowViewMatrix);
    paredMid->Draw(program, proj, view, shadowViewMatrix);
    porteria->Draw(program, proj, view, shadowViewMatrix);
    figPelota->Draw(program, proj, view, shadowViewMatrix);
    figCoche->Draw(program, proj, view);
}

//
// FUNCIÓN: CGScene::DrawShadow()
//
// PROPÓSITO: Dibuja las sombras de la escena
//
void CGScene::DrawShadow(CGShaderProgram* program, glm::mat4 shadowMatrix)
{
    figPelota->DrawShadow(program, shadowMatrix);
    figCoche->DrawShadow(program, shadowMatrix);
    porteria->DrawShadow(program, shadowMatrix);
    pared1->DrawShadow(program, shadowMatrix);
    pared2->DrawShadow(program, shadowMatrix);
    pared3->DrawShadow(program, shadowMatrix);
    paredIzq->DrawShadow(program, shadowMatrix);
    paredDer->DrawShadow(program, shadowMatrix);
    paredMid->DrawShadow(program, shadowMatrix);
    
}

//
// FUNCIÓN: CGScene::GetLightViewMatrix()
//
// PROPÓSITO: Obtiene la matriz de posicionamiento de la luz
//
glm::mat4 CGScene::GetLightViewMatrix()
{
    glm::vec3 Zdir = -(light->GetLightDirection());
    glm::vec3 Up = glm::vec3(0.0f, 1.0f, 0.0f);
    glm::vec3 Xdir = glm::normalize(glm::cross(Up, Zdir));
    glm::vec3 Ydir = glm::cross(Zdir, Xdir);
    glm::vec3 Zpos = 150.0f * Zdir;
    glm::vec3 Center = glm::vec3(0.0f, 0.0f, 0.0f);

    glm::mat4 view = glm::lookAt(Zpos, Center, Ydir);
    return view;
}

CGObject* CGScene::getCoche() const {
    return figCoche;
}

CGFigure* CGScene::getPelota() const {
    return figPelota;
}