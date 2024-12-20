clear, clc, close all;
addpath("./Funciones/");
load("01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");
load("02_FaseEntrenamiento\01_CirculoCuadradoVSTrianguloKNN\DatosGenerados\espacioCaracteristicas.mat")

X = Z;

% Generar XoI, YoI

XoI = []; YoI = [];

% Clase 1
XoIClase = []; YoIClase = [];
codifClases = unique(Y);
clasesOI = [1 2];
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
clasesOI = [3];
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

XTrain = XoI;
YTrain = YoI;

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = {"Circulo-Cuadrado", "Triangulo"};
nombresProblemaOI.simbolos = {"*r", "*b"};
espacioCcas_Representacion = 1:length(espacioCcas);

save(".\02_FaseEntrenamiento\01_CirculoCuadradoVSTrianguloKNN\DatosGenerados\KNN.mat", "XTrain", "YTrain", "espacioCcas", "nombresProblemaOI");
