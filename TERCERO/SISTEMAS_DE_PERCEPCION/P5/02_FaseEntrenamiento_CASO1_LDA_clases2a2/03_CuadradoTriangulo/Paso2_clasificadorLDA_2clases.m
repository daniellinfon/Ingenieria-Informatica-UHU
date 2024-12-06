clear;
addpath("../../Funciones");
load("../../01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizacion.mat");
load("DatosGenerados\espacioCcas.mat");
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

XoI = XoI(:,espacioCcas);
espacioCcasRepresentacion = 1:length(espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

%% calcula d1, d2, coeficiente..., funcion LDA nueva

[d1, d2, d12, coef_d12] = funcion_calcula_d1_d2_d12_LDA_2Clases_2_3_Dim(XoI, YoI);

%% representacion

%figure,
close all;
funcion_representa_datos(XoI, YoI, espacioCcasRepresentacion, nombresProblemaOI);
hold on,
funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
%fimplicit3();

save("DatosGenerados/LDA.mat", "d12", "coef_d12", "nombresProblemaOI","espacioCcas", "XoI", "YoI");


