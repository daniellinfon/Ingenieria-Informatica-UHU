% 1.- Utilizando la función de Matlab subplot, muestre en una misma ventana tipo figure la 
% imagen capturada y 3 imágenes que resalten, sobre la imagen original, aquellos píxeles cuya 
% intensidad sea mayor que un determinado umbral (asigne para esas 3 imágenes, valores de 
% umbral: 70, 140 y 210, respectivamente). La intensidad de un píxel se calculará como la 
% media de los niveles de gris de las componentes roja, verde y azul.

video=videoinput('winvideo',1,'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat=inf;
video.FrameGrabInterval=3;

Io = getsnapshot(video);
umbrales = [70, 140, 210];

figure; subplot(2, 2, 1);
imshow(Io);
title('Imagen Original');
%axis off;

for i = 1:length(umbrales)
    umbral = umbrales(i);
    intensidades = mean(Io, 3);
    
    % Resaltar los píxeles cuya intensidad sea mayor que el umbral
    img_resaltada = Io;
    img_resaltada(intensidades <= umbral) = 0;  
    
    % Mostrar la imagen resaltada
    subplot(2, 2, i+1);
    imshow(img_resaltada);
    title(['Umbral = ' num2str(umbral)]);
    axis off;
end

%%

% 2.- Para cada una de las imágenes generadas en el apartado anterior:
%  Visualice sobre la imagen original las 5 agrupaciones mayores de píxeles conectados.
%  Localice a través de su centroide las agrupaciones anteriores y, en otro color, el 
% centroide de la mayor agrupación para distinguirla

Io = getsnapshot(video);
umbrales = [70, 140, 210];
numAgrup = 5;

figure;
subplot(2, 2, 1);
imshow(Io);
title('Imagen Original');

for i = 1:length(umbrales)
    umbral = umbrales(i);
    intensidades = mean(Io, 3);
    %intensidades = (double(Io(:,:,1)) + double(Io(:,:,2)) + double(Io(:,:,3))) / 3;

    
    % Resaltar los píxeles cuya intensidad sea mayor que el umbral
    img_resaltada = Io;
    Ib = intensidades > umbral;
     
    [Ietiq, N] = bwlabel(Ib);
    
    if N > 0
        stats = regionprops(Ietiq, 'Area', 'Centroid');
        a = cat(1, stats.Area);
        c = cat(1, stats.Centroid);
        [areas_ord, ind] = sort(a, 'descend');
        
        if N < numAgrup
            numAgrup = N;
        end
        
        cOI = c(ind(1:numAgrup), :);
        centX = cOI(:, 1);
        centY = cOI(:, 2);
        numPix = areas_ord(numAgrup);
        Ib = bwareaopen(Ib, numPix);
        Ic = funcion_visualiza(img_resaltada, Ib, [255, 255, 0]);
    else
        Ic = img_resaltada;
        centX = 1;
        centY = 1;
    end
    
    % Mostrar la imagen resaltada
    subplot(2, 2, i + 1);
    imshow(Ic),hold on;
    plot(centX, centY, 'r*');
    plot(centX(1), centY(1), 'b*');
    title(['Umbral = ' num2str(umbral)]);
    
end

%%

% Visualice una secuencia de video que muestre:
% 3.- La escena inicialmente oscurecida y aclarándose progresivamente (utilizar la instrucción 
% imadjust y valores de gamma entre 0 y 4 con pasos de 0.05).

% datos=imaqhwinfo
% datos=imaqhwinfo('winvideo')
% datos.DeviceInfo(1)
% datos.DeviceInfo(1).SupportedFormats
%preview(video)

video=videoinput('winvideo',1,'YUY2_320x240');
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat=inf;
video.FrameGrabInterval=3;

start(video)
gamma = 4:-0.5:0;
for i=1:length(gamma)
    I = getdata(video,1);
    I= imadjust(I,[],[],gamma(i));
    %imshow(I);
    writeVideo(aviObj_O, I);

end
stop(video)

%%

% 4.- Todos los píxeles que tengan una intensidad mayor que un determinado umbral. Asignar 
% inicialmente el valor 0 a este umbral e ir aumentándolo progresivamente con pasos de unidad 
% hasta el 255. 
video.ReturnedColorSpace = 'grayscale';
start(video)
for i=0:255
    I = getdata(video,1);
    ib = I <= i;
    I(ib) = 0;
    imshow(I);
    %funcion_visualiza(I,ib,[255,0,0],true);
end
stop(video)
close all;

%%
% 5.1.- Las diferencias que se producen entre los distintos frames de intensidad que captura la 
% webcam (utilizar la instrucción imabsdiff para hacer la diferencia entre el frame actual y el 
% adquirido previamente).

video.ReturnedColorSpace = 'grayscale';

start(video)
frame_ant = getdata(video,1);

while(video.FramesAcquired<100)
    frame_act = getdata(video,1);
    I = imabsdiff(frame_ant, frame_act);
    imshow(I);
    frame_ant = frame_act;
end

stop(video)

%%
% 5.2.- Los píxeles cuyas diferencias de intensidad son significativas (considerar un umbral de 
% 100 para establecer de diferencias de intensidad significativas).

umbral = 100
video.ReturnedColorSpace = 'grayscale';

start(video)
frame_ant = getdata(video,1);

while(video.FramesAcquired<50)
    frame_act = getdata(video,1);
    I = imabsdiff(frame_ant, frame_act);
    mov_sig = (I > umbral);
    frame_ant = frame_act;
    subplot(1,3,1), imshow(frame_act);
    subplot(1,3,2), imshow(I);
    subplot(1,3,3), imshow(mov_sig);
end

stop(video)

%%
% 5.3.- El seguimiento de la agrupación mayor de píxeles que presenta una diferencia de 
% intensidad significativa. El seguimiento debe visualizarse a través de un punto rojo situado en 
% el centroide de la agrupación.

umbral = 100
start(video)
frame_ant = getdata(video,1);

while(video.FramesAcquired<100)
    frame_act = getdata(video,1);
    I = imabsdiff(frame_ant, frame_act);
    mov_sig = (I > umbral);

    [Ietiq, N] = bwlabel(mov_sig);

    if N>0
        stats = regionprops(Ietiq,'Area','Centroid');

        areas = cat(1,stats.Area);
        centroides = cat(1, stats.Centroid);

        [areas_ord indices]= sort(areas,'descend');
        coord_x = centroides(indices(1),1);
        coord_y = centroides(indices(1),2);
    else
        coord_x = 1;
        coord_y = 1;
    end

    frame_ant = frame_act;
    subplot(1,2,1), imshow(frame_act); hold on, plot(coord_x,coord_y,'*r'),hold off;
    subplot(1,2,2), imshow(mov_sig); hold on, plot(coord_x,coord_y,'*r'),hold off;
    
end

stop(video)
