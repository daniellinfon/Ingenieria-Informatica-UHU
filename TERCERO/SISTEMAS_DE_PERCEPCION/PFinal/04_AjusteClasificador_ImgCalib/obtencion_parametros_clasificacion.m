%% Cargar Datos
clear;
addpath("funciones");
ruta = '../03_DiseñoClasificador/VariablesGeneradas/';
load(ruta+"datos_multiples_esferas.mat");
addpath("funciones");
ruta = '../02_Extraer_Representar_Datos/VariablesGeneradas/';
load(ruta+"datosXY_sinOut.mat");
ruta = '../01_Generacion_del_Material/MaterialGenerado/';
load(ruta+"ImagenesEntrenamiento_Calibracion.mat");

%% Representar multipes esferas

centroides = datos_multiples_esferas(:,1:3);
radios = datos_multiples_esferas(:,4:6);

%Representar esferas

for i=1:size(radios,2)
    representa_datos_color_seguimiento_fondo(X,Y);
    hold on, representa_multiples_esferas(centroides, radios(:,i)), hold off;
    title(['Esfera de Radio: ' num2str(radios(i))]);

    disp("Pulsa una tecla para pasar al Radio "+i+"...");
    pause;
    close all;
end

%% Representa deteccion multiples esferas

centroides = datos_multiples_esferas(:,1:3);
radios = datos_multiples_esferas(:,4:6);

numImagenes = size(imagenes,4);
color = [0 255 0];

for i=1:numImagenes

    disp("Pulsa una tecla para pasar a la imagen "+i+"...");
    pause;
    close all;

    %Mostrar imagen original
    Io = imagenes(:,:,:,i);
    figure; subplot(2, 2, 1);
    imshow(Io);
    title(["Imagen "+  num2str(i)]);
    
    Ib1 = calcula_deteccion_multiples_esferas_en_imagen(Io, [centroides radios(:,1)]);
    Ib2 = calcula_deteccion_multiples_esferas_en_imagen(Io, [centroides radios(:,2)]);
    Ib3 = calcula_deteccion_multiples_esferas_en_imagen(Io, [centroides radios(:,3)]);

    subplot(2, 2, 2), I = funcion_visualiza(Io, Ib1, color);imshow(I);
    title('Radio 1');
    subplot(2, 2, 3), I = funcion_visualiza(Io, Ib2, color);imshow(I);
    title('Radio 2'); 
    subplot(2, 2, 4), I = funcion_visualiza(Io, Ib3, color);imshow(I);
    title('Radio Compromiso');

end

%Elegir radio
radioElegido = radios(3);

%% Obtener parametros

I=imagenes(:,:,:,8); %img con objeto mas alejado
ROI = roipoly(I);
numPix = sum(ROI(:));
variables = [1 0.8 1.2];

numImagenes = size(imagenes,4);
color = [0 255 0];
titulos = {'Radio 1' 'Radio 2' 'Radio Compromiso'};

for i=1:numImagenes

    disp("Pulsa una tecla para pasar a la imagen "+i+"...");
    pause;
    close all;

    %Mostrar imagen original
    Io = imagenes(:,:,:,i);
    figure; subplot(2, 2, 1);
    imshow(Io);
    title(["Imagen "+  num2str(i)]);
    Ib = calcula_deteccion_multiples_esferas_en_imagen(Io, [centroides radioElegido]);
    [Ietiq N] = bwlabel(Ib);

    for k=1:3
        if N>0
            Ib = bwareaopen(Ib,numPix*variables(k));
        end
        subplot(2, 2, k+1), I = funcion_visualiza(Io, Ib, color);imshow(I);
        title(titulos(k));
    end
end


% Elegir radio y numPix. 
% numPix es el original
datos_multiples_esferas(:,4:5) = [];
save("VariablesGeneradas\parametros_clasificador","datos_multiples_esferas","numPix");