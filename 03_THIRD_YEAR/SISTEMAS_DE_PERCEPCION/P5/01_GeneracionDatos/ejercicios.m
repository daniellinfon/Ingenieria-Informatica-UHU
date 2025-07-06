clear, clc;
X = Z;


%% a)

dim = 3;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(X, Y, dim);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases;
nombresProblemaOI.simbolos = nombresProblema.simbolos;

espacioCcasRepresentacion = 1:length(espacioCcas);
X = X(:, espacioCcas);

%figure,
funcion_representa_datos(X, Y, espacioCcasRepresentacion, nombresProblemaOI)

save('DatosGenerados/conjunto_datos_ejecicioA.mat', 'X', 'Y', "espacioCcas", "nombresProblemaOI");

%% b)

codifClases = unique(Y);
clasesOI = [1 2]; %posiciones en el unique
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

%ranking

dim = 3;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

espacioCcasRepresentacion = 1:length(espacioCcas);
XoI = XoI(:, espacioCcas);

%figure,
funcion_representa_datos(XoI, YoI, espacioCcasRepresentacion, nombresProblemaOI)

save('DatosGenerados/conjunto_datos_ejecicioB.mat', 'XoI', 'YoI', "espacioCcas", "nombresProblemaOI");


%% c)

codifClases = unique(Y);
clasesOI = [1 3]; %posiciones en el unique
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

%ranking

dim = 3;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

espacioCcasRepresentacion = 1:length(espacioCcas);
XoI = XoI(:, espacioCcas);

%figure,
funcion_representa_datos(XoI, YoI, espacioCcasRepresentacion, nombresProblemaOI)

save('DatosGenerados/conjunto_datos_ejecicioC.mat', 'XoI', 'YoI', "espacioCcas", "nombresProblemaOI");


%% d)

codifClases = unique(Y);
clasesOI = [2 3]; %posiciones en el unique
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

%ranking

dim = 3;
[espacioCcas, JespacioCCas] = funcion_selecciona_vector_ccas(XoI, YoI, dim);

nombresProblemaOI = nombresProblema;
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = nombresProblema.clases(clasesOI);
nombresProblemaOI.simbolos = nombresProblema.simbolos(clasesOI);

espacioCcasRepresentacion = 1:length(espacioCcas);
XoI = XoI(:, espacioCcas);

%figure,
funcion_representa_datos(XoI, YoI, espacioCcasRepresentacion, nombresProblemaOI)

save('DatosGenerados/conjunto_datos_ejecicioD.mat', 'XoI', 'YoI', "espacioCcas", "nombresProblemaOI");


%% e)

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

nDescriptores = 22;
dim = 3;
comb = combnk(1:nDescriptores, dim);

nComb = size(comb, 1);
J = zeros(nComb, 1);
for i=1:nComb
    J(i) = indiceJ(XoI(:,comb(i,:)), YoI);
end

[Jord, ind] = max(J);
dim = 3;
espacioCcas = comb(ind,:);

nombresProblemaOI = [];
nombresProblemaOI.descriptores = nombresProblema.descriptores(espacioCcas);
nombresProblemaOI.clases = {"Circulo-Triangulo", "Cuadrado"};
nombresProblemaOI.simbolos = {"*r", "*b"};
espacioCcas_Representacion = 1:length(espacioCcas);
XoI = XoI(:, espacioCcas);
%figure
funcion_representa_datos(XoI, YoI, espacioCcas_Representacion, nombresProblemaOI);

save('DatosGenerados/conjunto_datos_ejecicioE.mat', 'XoI', 'YoI', "espacioCcas", "nombresProblemaOI");

