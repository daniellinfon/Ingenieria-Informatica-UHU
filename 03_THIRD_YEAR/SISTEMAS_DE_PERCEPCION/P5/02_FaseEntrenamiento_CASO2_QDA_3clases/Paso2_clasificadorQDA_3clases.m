clear;
addpath("../Funciones");
load("../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizacion.mat");
load("DatosGenerados\espacioCcas.mat");
X = Z;

%% generar XoI YoI

codifClases = unique(Y);

XoI = X(:, espacioCcas); YoI = Y;

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);

%% calcula d1, d2, coeficiente..., funcion LDA nueva

[vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA(XoI,YoI);

save("DatosGenerados/QDA.mat", "vectorMedias", "matricesCovarianzas","probabilidadPriori", "nombresProblemaOI","espacioCcas", "XoI", "YoI");

