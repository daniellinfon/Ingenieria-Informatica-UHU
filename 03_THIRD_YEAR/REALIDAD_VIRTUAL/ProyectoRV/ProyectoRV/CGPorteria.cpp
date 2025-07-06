#include "CGPorteria.h"
#include <GL/glew.h>
#include "CGFigure.h"


CGPorteria::CGPorteria(GLfloat a, GLfloat p) {
    numFaces = 10;     // Number of faces (anteriormente 12)
    numVertices = 20;  // Number of vertices (anteriormente 24)

    GLfloat p_vertices[20][3] = {
        { +(4 * a), +a, +a }, // A0 // Positive X
        { +(4 * a), -a, +a }, // C0  
        { +(4 * a), -a, -p }, // C1  
        { +(4 * a), +a, -p }, // A1 

        { -a, +a, +a },       // B0 // Positive Y
        { +(4 * a), +a, +a }, // A0 
        { +(4 * a), +a, -p }, // A1 
        { -a, +a, -p },       // B1 

        {-a, -a, +a},         // D0 // Negative X
        { -a, +a, +a },       // B0
        { -a, +a, -p },       // B1  
        { -a, -a, -p },       // D1 

        { +(4 * a), -a, +a }, // C0 // Negative Y
        {-a, -a, +a},         // D0 
        { -a, -a, -p },       // D1
        { +(4 * a), -a, -p }, // C1 

        { +(4 * a), +a, +a }, // A0 // Positive Z
        { -a, +a, +a },       // B0 
        { -a, -a, +a },       // C0 
        { +(4 * a), -a, +a }, // D0 
    };

    GLfloat p_normals[20][3] = {
    { -1.0f, 0.0f, 0.0f },  // Positive X // 0
    { -1.0f, 0.0f, 0.0f },  // Positive X // 1
    { -1.0f, 0.0f, 0.0f },  // Positive X // 2
    { -1.0f, 0.0f, 0.0f },  // Positive X // 3
    { 0.0f, -1.0f, 0.0f },  // Positive Y // 4
    { 0.0f, -1.0f, 0.0f },  // Positive Y // 5
    { 0.0f, -1.0f, 0.0f },  // Positive Y // 6
    { 0.0f, -1.0f, 0.0f },  // Positive Y // 7
    { 1.0f, 0.0f, 0.0f },   // Negative X // 8
    { 1.0f, 0.0f, 0.0f },   // Negative X // 9
    { 1.0f, 0.0f, 0.0f },   // Negative X // 10
    { 1.0f, 0.0f, 0.0f },   // Negative X // 11
    { 0.0f, 1.0f, 0.0f },   // Negative Y // 12
    { 0.0f, 1.0f, 0.0f },   // Negative Y // 13
    { 0.0f, 1.0f, 0.0f },   // Negative Y // 14
    { 0.0f, 1.0f, 0.0f },   // Negative Y // 15
    { 0.0f, 0.0f, 1.0f },   // Positive Z // 16
    { 0.0f, 0.0f, 1.0f },   // Positive Z // 17
    { 0.0f, 0.0f, 1.0f },   // Positive Z // 18
    { 0.0f, 0.0f, 1.0f }    // Positive Z // 19
    };

    GLfloat p_textures[20][2] = {
    { 0.0f, 0.0f }, // Positive X
    { 1.0f, 0.0f },
    { 1.0f, 1.0f },
    { 0.0f, 1.0f },
    { 0.0f, 0.0f }, // Positive Y
    { 1.0f, 0.0f },
    { 1.0f, 1.0f },
    { 0.0f, 1.0f },
    { 1.0f, 0.0f }, // Negative X
    { 0.0f, 0.0f },
    { 0.0f, 1.0f },
    { 1.0f, 1.0f },
    { 0.0f, 0.0f }, // Negative Y
    { 1.0f, 0.0f },
    { 1.0f, 1.0f },
    { 0.0f, 1.0f },
    { 0.0f, 0.0f }, // Positive Z
    { 1.0f, 0.0f },
    { 1.0f, 1.0f },
    { 0.0f, 1.0f }
    };

    GLushort p_indexes[10][3] = {  // Array of indexes (anteriormente 12)
    { 2, 1, 0 },
    { 3, 2, 0 },
    { 6, 5, 4 },
    { 7, 6, 4 },
    { 10, 9, 8 },
    { 11, 10, 8 },
    { 14, 13, 12 },
    { 15, 14, 12 },
    { 18, 17, 16 },
    { 19, 18, 16 }
    };


    // Copia de los datos de vértices e índices
    vertices = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++)
            vertices[3 * i + j] = p_vertices[i][j];

    indexes = new GLushort[numFaces * 3];
    for (int i = 0; i < numFaces; i++)
        for (int j = 0; j < 3; j++)
            indexes[3 * i + j] = p_indexes[i][j];

    textures = new GLfloat[numVertices * 2];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 2; j++) textures[2 * i + j] = p_textures[i][j];

    normals = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++) normals[3 * i + j] = p_normals[i][j];

    InitBuffers();
}