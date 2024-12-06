clear, clc, close all;
addpath("./Funciones/");
load("01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");

X = Z;

%% generar XoI YoI

codifClases = unique(Y);
clasesOI = [1 3 4]; %posiciones en el unique
codifClases = codifClases(clasesOI);
numClasesOI = length(clasesOI);

XoI = []; YoI = [];

for i=1:numClasesOI
    fOI = Y==codifClases(i);
    XoI_i = X(fOI,:);
    YoI_i = Y(fOI);
    XoI = [XoI; XoI_i];
    YoI = [YoI; YoI_i];
end

%ranking

dim = 3;

%% llamada a la funcion selec descriptores -> espacioCCas

[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

%% guardo espacio ccas;

save("02_FaseEntrenamiento\ACD-KNN\DatosGenerados\espacioCaracteristicas.mat", "espacioCcas");