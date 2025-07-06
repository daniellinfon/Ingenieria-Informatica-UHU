%% EJERCICIO RELATIVO AL PROCEDIMIENTO DE SELECCIÓN DE ATRIBUTOS
addpath("../Funciones");
load("DatosGenerados\conjunto_datos_estandarizacion.mat");

X=Z;
XoI = [];
YoI = [];

codifClases = unique(Y);

%Clase 1
XoIC = [];
YoIC = [];
clasesOI = [1 3]; %Pos en el unique
codifClasesOI = codifClases(clasesOI);
numClasesOI = length(clasesOI);

for i=1:numClasesOI
    fOI = Y == codifClasesOI(i);
    XoI_i = X(fOI,:);
    YoI_i = Y(fOI);
    
    XoIC = [XoIC; XoI_i];
    YoIC = [YoIC; ones(size(XoI_i,1),1)]

end
XoIC1 = XoIC;
YoIC1 = YoIC;

%Clase 2
XoIC = [];
YoIC = [];
clasesOI = [2]; %Pos en el unique
codifClasesOI = codifClases(clasesOI);
numClasesOI = length(clasesOI);

for i=1:numClasesOI
    fOI = Y == codifClasesOI(i);
    XoI_i = X(fOI,:);
    YoI_i = Y(fOI);
    
    XoIC = [XoIC; XoI_i];
    YoIC = [YoIC; 2*ones(size(XoI_i,1),1)];

end
XoIC2 = XoIC;
YoIC2 = YoIC;

XoI = [XoIC1;XoIC2];
YoI = [YoIC1;YoIC2];

espacioCcas = [1 4 10];
XoI = X(:, espacioCcas);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = {'Circulo-Triangulo','Cuadrado'}; %nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = {'*r','*b'}; %nombresProblema.simbolos(clasesOI);

espacioCcas_representacion = 1:length(espacioCcas);
%figure
funcion_representa_datos(XoI, YoI, espacioCcas_representacion, nombresProblemaOI);

indiceEspacioCCa = indiceJ(XoI, YoI);

%% CREAR RANKING DE CARACTERISTICAS INDIVIDUALES PARA SEPARAR
clear;
addpath("../Funciones");
load("DatosGenerados\conjunto_datos_estandarizacion.mat");
% Las 3 clases
X=Z;
XoI = X;
YoI = Y;

nombresProblemaOI = nombresProblema;
numDescriptores = 22;
J = zeros(numDescriptores,1);

for i=1:numDescriptores
    J(i) = indiceJ(XoI(:,i),YoI);
end

[Jord, ind] = sort(J,'descend');
dim = 3;
espacioCcas = ind(1:dim);

XoI = XoI(:,espacioCcas);
espacioCcas_representacion = 1:length(espacioCcas);
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);

figure,
funcion_representa_datos(XoI, YoI, espacioCcas_representacion, nombresProblemaOI)

%% Combinaciones
clear;
addpath("../Funciones");
load("DatosGenerados\conjunto_datos_estandarizacion.mat");
% Las 3 clases
X=Z;
XoI = X;
YoI = Y;
nombresProblemaOI = nombresProblema;

numDescriptores = 22;
dim = 3;
comb = combnk(1:numDescriptores, dim);
J = zeros(numDescriptores,1);

for i=1:numDescriptores
    J(i) = indiceJ(XoI(:,comb(i,:)),YoI);
end

[Jord, ind] = max(J);
dim = 3;
espacioCcas = comb(ind,:);

XoI = XoI(:,espacioCcas);
espacioCcas_representacion = 1:length(espacioCcas);
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);

figure,
funcion_representa_datos(XoI, YoI, espacioCcas_representacion, nombresProblemaOI)