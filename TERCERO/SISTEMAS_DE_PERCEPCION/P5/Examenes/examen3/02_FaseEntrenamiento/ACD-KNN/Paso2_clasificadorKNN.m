clear, clc, close all;
addpath("./Funciones/");
load("01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");
load("02_FaseEntrenamiento\ACD-KNN\DatosGenerados\espacioCaracteristicas.mat");
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

XTrain = XoI(:, espacioCcas);
YTrain = YoI;

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

save("02_FaseEntrenamiento\ACD-KNN\DatosGenerados\KNN.mat", "XTrain", "YTrain", "espacioCcas", "nombresProblemaOI");