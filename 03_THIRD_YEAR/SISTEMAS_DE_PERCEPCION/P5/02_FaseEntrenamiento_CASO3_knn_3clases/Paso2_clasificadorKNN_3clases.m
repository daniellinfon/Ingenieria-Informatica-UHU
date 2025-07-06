clear;
addpath("../Funciones");
load("../01_GeneracionDatos/DatosGenerados\conjunto_datos_estandarizacion.mat");
load("DatosGenerados\espacioCcas.mat");
X = Z;

% Generar XoI, YoI

YoI = Y;
XoI = X(:,espacioCcas);
espacioCcasRepresentacion = 1:length(espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases;
nombresProblemaOI.simbolos = nombresProblema.simbolos;

save("DatosGenerados\KNN.mat", "XoI", "YoI","nombresProblemaOI","espacioCcas");


