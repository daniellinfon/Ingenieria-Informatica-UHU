% Cargamos Z-Y -> X=Z
clear;
addpath("../../Funciones");
load("../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizacion.mat");
X = Z;

% Generar XoI, YoI

clasesOI = [2 3];
codifClases = unique(Y);
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

dim = 3;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

save("DatosGenerados/espacioCcas.mat", "espacioCcas");
