clear, clc, close all;
addpath("./Funciones/");
load("01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");
X = Z;
espacioCcas= [8 16 18];

% Generar XoI, YoI

XoI = []; YoI = [];

% Clase 1
XoIClase = []; YoIClase = [];
codifClases = unique(Y);
clasesOI = [1 3 4];
codifClasesOI = codifClases(clasesOI);
nClasesOI = length(clasesOI);
for i=1:nClasesOI
    fOI = Y == codifClasesOI(i); 
    XoI_i = X(fOI,:);

    XoIClase = [XoIClase; XoI_i];
    YoIClase = [YoIClase; ones(size(XoI_i, 1), 1)];

end

XoIClase1 = XoIClase;
YoIClase1 = YoIClase;

%%Clase 2
XoIClase = []; YoIClase = [];
codifClases = unique(Y);
clasesOI = [5 6 7];
codifClasesOI = codifClases(clasesOI);
nClasesOI = length(clasesOI);
for i=1:nClasesOI
    fOI = Y ==codifClasesOI(i); 
    XoI_i = X(fOI,:);

    XoIClase = [XoIClase; XoI_i];
    YoIClase = [YoIClase; 2*ones(size(XoI_i, 1), 1)];


end
XoIClase2 = XoIClase;
YoIClase2 = YoIClase;

% Union de las clases
XoI = [XoIClase1; XoIClase2];
YoI = [YoIClase1; YoIClase2];

XoI = XoI(:,espacioCcas);
espacioCcasRepresentacion = 1:length(espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = {"A-C-D", "E-F-G"};
nombresProblemaOI.simbolos = {"*r", "*b"};
espacioCcas_Representacion = 1:length(espacioCcas);

[d1, d2, d12, coef_d12] = funcion_calcula_d1_d2_d12_LDA_2Clases_2_3_Dim(XoI, YoI);

close all;
funcion_representa_datos(XoI, YoI, espacioCcasRepresentacion, nombresProblemaOI);
hold on,
funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
%fimplicit3();

save("./02_FaseEntrenamiento\ACDEFG-LDA\DatosGenerados\LDA.mat", "d12", "coef_d12", "nombresProblemaOI", "espacioCcas", "XoI", "YoI");