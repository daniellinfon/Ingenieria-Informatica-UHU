%% Cargar Datos
clear;
addpath("funciones");
ruta = '../01_Generacion_del_Material/MaterialGenerado/';

load(ruta+"ImagenesEntrenamiento_Calibracion.mat");

%% Lectura de imagenes y extraccion de datos

numImagenes = size(imagenes,4);
datosAzul=[];
datosOtrosColores = [];

for i=1:numImagenes

    I = imagenes(:,:,:,i);

    R = I(:,:,1);
    G = I(:,:,2);
    B = I(:,:,3);

    disp("Imagen "+i);
    ROI = roipoly(I); 
    numPix = sum(ROI(:));

    % Sacar los valores de los píxeles seleccionados
    valores_R = R(ROI);
    valores_G = G(ROI);
    valores_B = B(ROI);
    
    if(i<6) %5 Primeras imagenes de fondo
        datosOtrosColores = [datosOtrosColores; ones(numPix, 1) * i, valores_R, valores_G, valores_B];
    else
        datosAzul = [datosAzul; ones(numPix, 1) * i, valores_R, valores_G, valores_B];
    end
end

YAzul = ones(size(datosAzul,1),1);
YOtrosColores = zeros(size(datosOtrosColores,1),1);

X = [datosAzul(:,2:4) ; datosOtrosColores(:,2:4)];
Y = [YAzul; YOtrosColores];

save("VariablesGeneradas\datosXY","X", "Y");


%% ELIMINACIÓN DE VALORES ATÍPICOS EN LOS DATOS DEL COLOR DE SEGUIMIENTO

posClaseInteres = 2;
posOut = funcion_detecta_outliers_clase_interes(X,Y,posClaseInteres);
X(posOut,:) = [];
Y(posOut) = [];
save("VariablesGeneradas\datosXY_sinOut","X", "Y");

%% Representar la informacion
close all;
load("VariablesGeneradas\datosXY_sinOut.mat");
representa_datos_color_seguimiento_fondo(X,Y);
