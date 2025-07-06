clear, clc, close all;
addpath("./Funciones/");
load("01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");
load("02_FaseEntrenamiento\EFG-QDA\DatosGenerados\espacioCaracteristicas.mat");
X = Z;

%% generar XoI YoI

codifClases = unique(Y);
clasesOI = [5 6 7]; %posiciones en el unique
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

XoI = XoI(:, espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

[vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA (XoI,YoI);

save("02_FaseEntrenamiento\EFG-QDA\DatosGenerados\QDA.mat", "vectorMedias", "matricesCovarianzas", "probabilidadPriori", "nombresProblemaOI", "espacioCcas", "XoI", "YoI");