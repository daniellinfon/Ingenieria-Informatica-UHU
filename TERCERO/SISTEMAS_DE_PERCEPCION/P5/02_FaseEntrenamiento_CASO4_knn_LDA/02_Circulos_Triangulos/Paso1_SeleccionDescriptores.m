% Cargamos Z-Y -> X=Z
clear;
addpath("../../Funciones");
load("../../01_GeneracionDatos/DatosGenerados\conjunto_datos_estandarizacion.mat");
X = Z;

%% generar XoI YoI

codifClases = unique(Y);
clasesOI = [1 3]; %posiciones en el unique
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

save("DatosGenerados\espacioCcas.mat", "espacioCcas");