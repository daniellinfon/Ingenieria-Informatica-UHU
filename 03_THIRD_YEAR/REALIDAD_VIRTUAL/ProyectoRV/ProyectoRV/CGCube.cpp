#include "CGCube.h"
#include <GL/glew.h>
#include "CGFigure.h"

///
/// FUNCION: CGCube::CGCube(GLfloat s)
///
/// PROPÓSITO: Construye un cubo de lado'2*s'
///
CGCube::CGCube(GLfloat s)
{
    numFaces = 12; // Number of faces
    numVertices = 24; // Number of vertices


    GLfloat p_normals[24][3] = {
      { 1.0f, 0.0f, 0.0f }, // Positive X // 0
      { 1.0f, 0.0f, 0.0f }, // Positive X // 1
      { 1.0f, 0.0f, 0.0f }, // Positive X // 2
      { 1.0f, 0.0f, 0.0f }, // Positive X // 3
      { 0.0f, 1.0f, 0.0f }, // Positive Y // 4
      { 0.0f, 1.0f, 0.0f }, // Positive Y // 5
      { 0.0f, 1.0f, 0.0f }, // Positive Y // 6
      { 0.0f, 1.0f, 0.0f }, // Positive Y // 7
      { -1.0f, 0.0f, 0.0f }, // Negative X // 8
      { -1.0f, 0.0f, 0.0f }, // Negative X // 9
      { -1.0f, 0.0f, 0.0f }, // Negative X // 10
      { -1.0f, 0.0f, 0.0f }, // Negative X // 11
      { 0.0f, -1.0f, 0.0f }, // Negative Y // 12
      { 0.0f, -1.0f, 0.0f }, // Negative Y // 13
      { 0.0f, -1.0f, 0.0f }, // Negative Y // 14
      { 0.0f, -1.0f, 0.0f }, // Negative Y // 15
      { 0.0f, 0.0f, 1.0f }, // Positive Z // 16
      { 0.0f, 0.0f, 1.0f }, // Positive Z // 17
      { 0.0f, 0.0f, 1.0f }, // Positive Z // 18
      { 0.0f, 0.0f, 1.0f }, // Positive Z // 19
      { 0.0f, 0.0f, -1.0f }, // Negative Z // 20
      { 0.0f, 0.0f, -1.0f }, // Negative Z // 21
      { 0.0f, 0.0f, -1.0f }, // Negative Z // 22
      { 0.0f, 0.0f, -1.0f } // Negative Z // 23
    };

    GLfloat p_textures[24][2] = { // Array of texture coordinates
      { 0.0f, 0.0f }, // Positive X
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f },
      { 0.0f, 0.0f }, // Positive Y
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f },
      { 0.0f, 0.0f }, // Negative X
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f },
      { 0.0f, 0.0f }, // Negative Y
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f },
      { 0.0f, 0.0f }, // Positive Z
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f },
      { 0.0f, 0.0f }, // Negative Z
      { 1.0f, 0.0f },
      { 1.0f, 1.0f },
      { 0.0f, 1.0f }
    };

    GLfloat p_vertices[24][3] = {
      { +s, +s, +s }, // A0 // Positive X
      { +s, -s, +s }, // D0 
      { +s, -s, -s }, // D1 
      { +s, +s, -s }, // A1 

      { -s, +s, +s }, // B0 // Positive Y
      { +s, +s, +s }, // A0 
      { +s, +s, -s }, // A1 
      { -s, +s, -s }, // B1 

      { -s, -s, +s }, // C0 // Negative X
      { -s, +s, +s }, // B0 
      { -s, +s, -s }, // B1 
      { -s, -s, -s }, // C1

      { +s, -s, +s }, // D0 // Negative Y
      { -s, -s, +s }, // C0 
      { -s, -s, -s }, // C1
      { +s, -s, -s }, // D1 0

      { +s, +s, +s }, // A0 // Positive Z
      { -s, +s, +s }, // B0 
      { -s, -s, +s }, // C0 
      { +s, -s, +s }, // D0 0

      { +s, +s, -s }, // A1 // Negative Z
      { +s, -s, -s }, // D1 
      { -s, -s, -s }, // C1 
      { -s, +s, -s } // B1 
    };

    GLushort p_indexes[12][3] = { // Array of indexes
      { 0, 1, 2 },
      { 0, 2, 3 },
      { 4, 5, 6 },
      { 4, 6, 7 },
      { 8, 9, 10 },
      { 8, 10, 11 },
      { 12, 13, 14 },
      { 12, 14, 15 },
      { 16, 17, 18 },
      { 16, 18, 19 },
      { 20, 21, 22 },
      { 20, 22, 23 }
    };

    normals = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++) normals[3 * i + j] = p_normals[i][j];
    vertices = new GLfloat[numVertices * 3];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 3; j++) vertices[3 * i + j] = p_vertices[i][j];
    textures = new GLfloat[numVertices * 2];
    for (int i = 0; i < numVertices; i++)
        for (int j = 0; j < 2; j++) textures[2 * i + j] = p_textures[i][j];
    indexes = new GLushort[numFaces * 3];
    for (int i = 0; i < numFaces; i++)
        for (int j = 0; j < 3; j++) indexes[3 * i + j] = p_indexes[i][j];

    InitBuffers();
}
