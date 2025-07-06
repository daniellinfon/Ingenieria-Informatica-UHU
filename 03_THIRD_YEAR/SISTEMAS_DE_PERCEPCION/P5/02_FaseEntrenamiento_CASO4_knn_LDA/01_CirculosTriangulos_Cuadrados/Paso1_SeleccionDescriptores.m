% Cargamos Z-Y -> X=Z
clear;
addpath("../../Funciones");
load("../../01_GeneracionDatos/DatosGenerados\conjunto_datos_estandarizacion.mat");
X = Z;


%% generar XoI YoI

% Generar XoI, YoI

XoI = []; YoI = [];

% Clase 1
XoIClase = []; YoIClase = [];
codifClases = unique(Y);
clasesOI = [1 3];
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
clasesOI = [2];
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

%ranking

dim = 4;

%% llamada a la funcion selec descriptores -> espacioCCas
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

%% guardo espacio ccas;

save("DatosGenerados\espacioCcas.mat", "espacioCcas");