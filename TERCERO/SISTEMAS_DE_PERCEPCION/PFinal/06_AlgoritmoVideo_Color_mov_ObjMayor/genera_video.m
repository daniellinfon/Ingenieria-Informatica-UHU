%% Cargar Datos
clear;
addpath("funciones");
ruta = '../04_AjusteClasificador_ImgCalib/VariablesGeneradas/';
load(ruta+"parametros_clasificador.mat");
ruta = '../01_Generacion_del_material/MaterialGenerado/';

aviobjI = VideoReader(ruta+"video_entrada.avi");
%get(aviobjI);

%%  IMPLEMENTACIÓN Y VISUALIZACIÓN DE ALGORITMO DE SEGUIMIENTO

NumeroFrames = aviobjI.Duration*aviobjI.FrameRate; % aviobjI.NumFrames
% NumFilasFrame = aviobjI.Height;
% NumeroColumnasFrame = aviobjI.Width;
FPS = aviobjI.FrameRate;

aviobjO = VideoWriter('video_salida_mov.avi');
aviobjO.FrameRate = FPS; % El video tendra la misma tasa de frames
open(aviobjO)

aviobjI.CurrentTime=0; % Ponemos a 0

umbral = 70;
frame_ant = readFrame(aviobjI);

for i=2:NumeroFrames

    %Movimiento fondo
    intensidadesAnt = mean(frame_ant,3);
    frame_act = readFrame(aviobjI);
    intensidades = mean(frame_act, 3);
    I = imabsdiff(intensidades,intensidadesAnt);
    mov_sig = (I > umbral);
    I = funcion_visualiza(frame_act, mov_sig,[255,255,0]);

    %Movimiento Objeto
    intensidadesAnt = calcula_deteccion_multiples_esferas_en_imagen(frame_ant, datos_multiples_esferas);
    intensidades = calcula_deteccion_multiples_esferas_en_imagen(frame_act, datos_multiples_esferas);
    Ib2 = imabsdiff(intensidades,intensidadesAnt);
    I = funcion_visualiza(I, Ib2,[0,0,255]);

    [Ietiq N] = bwlabel(intensidades); %Ver agrupaciones
    
    if N>0
  
        stats = regionprops(Ietiq,'Area','Centroid');

        areas = cat(1,stats.Area);
        centroides = cat(1, stats.Centroid);

        %Nos quedamos el centroide mayor
        [areas_ord, indices]= sort(areas,'descend');
        x = cast(centroides(indices(1),1), "uint8");
        y = cast(centroides(indices(1),2), "uint8");

        %Pinto de verde el objeto
        I = funcion_visualiza(I, intensidades,[0,255,0]);

        % Cuadrado
        I(y-1:y+1,x-1:x+1, 2) = 0;  
        I(y-1:y+1,x-1:x+1, 1) = 255;
        I(y-1:y+1,x-1:x+1, 3) = 0;
    end
    imshow(I);
    writeVideo(aviobjO,I);
    frame_ant = frame_act;
end
close(aviobjO);

