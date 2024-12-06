% Cargamos Z-Y -> X=Z
clear;
addpath("../Funciones");
load("../01_GeneracionDatos/DatosGenerados\conjunto_datos_estandarizacion.mat");
X = Z;

% Generar XoI, YoI

XoI = X(:, 1:end-1); YoI = Y;

dim = 5;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

save("DatosGenerados\espacioCcas.mat", "espacioCcas");
