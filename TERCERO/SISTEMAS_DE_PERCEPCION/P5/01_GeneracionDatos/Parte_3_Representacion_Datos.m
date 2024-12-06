%% Representación de los datos en espacios de características dos a dos:
clear, close all;
addpath("../Funciones");
load("DatosGenerados\conjunto_datos_estandarizacion.mat");

numDescriptores = 23;

for i=1:2:numDescriptores-1
    espacioCcas = [i i+1];
    funcion_representa_datos(Z, Y, espacioCcas, nombresProblema);
end

%% Representación de histograma y diagrama de cajas de cada descriptor:

% Dado un conjunto de datos X-Y, y definidas las variables representativas
% del problema: numClases, codifClases, nombreClases, numDescriptores,
% nombreDescriptores
numClases = 3;
codifClases = unique(Y);
nombreClases = nombresProblema.clases;
nombreDescriptores = nombresProblema.descriptores;

for j=1:numDescriptores-1

    % % Valores máximo y mínimos para representar en la misma escala
    vMin = min(Z(:,j));
    vMax = max(Z(:,j));

    hFigure = figure; hold on
    bpFigure = figure; hold on

    for i=1:numClases

        Zij = Z(Y==codifClases(i),j); % datos clase i del descriptor j

        figure(hFigure)
        subplot(numClases,1,i), histogram(Zij),
        xlabel(nombreDescriptores{j})
        ylabel('Histograma')
        axis([ vMin vMax 0 numClases/4]) % inf escala automática eje y
        title(nombreClases{i})

        figure(bpFigure)
        subplot(1,numClases,i), boxplot(Zij)
        xlabel('Diagrama de Caja')
        ylabel(nombreDescriptores{j})
        axis([ 0 2 vMin vMax ])
        title(nombreClases{i})
    end
end
