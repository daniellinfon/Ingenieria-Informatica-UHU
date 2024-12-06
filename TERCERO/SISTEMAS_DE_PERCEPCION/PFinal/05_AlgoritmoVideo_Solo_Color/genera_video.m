%% Cargar Datos
clear;
addpath("funciones");
addpath("variablesRequeridas");
ruta = 'variablesRequeridas/';
load(ruta+"parametros_clasificador.mat");
ruta = 'variablesRequeridas/';

aviobjI = VideoReader(ruta+"video_entrada.avi");
%get(aviobjI);

%%  IMPLEMENTACIÓN Y VISUALIZACIÓN DE ALGORITMO DE SEGUIMIENTO
NumeroFrames = aviobjI.Duration*aviobjI.FrameRate; % aviobjI.NumFrames
NumFilasFrame = aviobjI.Height;
NumeroColumnasFrame = aviobjI.Width;
FPS = aviobjI.FrameRate;

aviobjO = VideoWriter('video_salida_color.avi');
aviobjO.FrameRate = FPS; % El video tendra la misma tasa de frames
open(aviobjO)

aviobjI.CurrentTime=0; % Ponemos a 0

for i=1:NumeroFrames
    I = readFrame(aviobjI);
    %Detectar los pixeles de segumiento.
    Ib = calcula_deteccion_multiples_esferas_en_imagen(I, datos_multiples_esferas);
    Ib = bwareaopen(Ib,numPix);
    [Ietiq N] = bwlabel(Ib); %Ver agrupaciones
    
    if N>0

        stats = regionprops(Ietiq,'Centroid');
        centroides = cat(1, stats.Centroid);

        for i=1:size(centroides,1)

        x = cast(centroides(i,1), "uint8");
        y = cast(centroides(i,2), "uint8");

        % Cuadrado
        I(y-1:y+1,x-1:x+1, 2) = 255;  
        I(y-1:y+1,x-1:x+1, 1) = 0;
        I(y-1:y+1,x-1:x+1, 3) = 0;

        % % +
        % % Establecer la parte vertical de la cruz en verde
        % I(y-1:y+1, x, 1) = 0;    % Canal rojo
        % I(y-1:y+1, x, 2) = 255;  % Canal verde
        % I(y-1:y+1, x, 3) = 0;    % Canal azul
        % 
        % % Establecer la parte horizontal de la cruz en verde
        % I(y, x-1:x+1, 1) = 0;    % Canal rojo
        % I(y, x-1:x+1, 2) = 255;  % Canal verde
        % I(y, x-1:x+1, 3) = 0;    % Canal azul
        % 
        % % Establecer el punto central en verde
        % I(y, x, 1) = 0;    % Canal rojo
        % I(y, x, 2) = 255;  % Canal verde
        % I(y, x, 3) = 0;    % Canal azul


        end
        
        imshow(I);
    else
        imshow(I);
    end

    writeVideo(aviobjO,I);
end
close(aviobjO);

