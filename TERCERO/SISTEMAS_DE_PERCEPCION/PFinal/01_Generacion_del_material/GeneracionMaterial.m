%% Grabar video

clear;
video=videoinput('winvideo',1,'YUY2_320x240'); %
set(video, 'LoggingMode', 'memory')
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat=inf; % disparos continuados
preview(video)
fpsMaximoWebCam = 30;
video.FrameGrabInterval=3;
fpsTrabajoWebCam = round(fpsMaximoWebCam/video.FrameGrabInterval);
preview(video);
resolucion = video.videoResolution;
numFilas = resolucion(2);
numColumnas = resolucion(1);

aviobj = VideoWriter('MaterialGenerado/video_entrada.avi', 'Uncompressed AVI');
aviobj.FrameRate = fpsTrabajoWebCam;
duracionGrabacion = 15; % duracion en segundos
numFramesGrabacion = duracionGrabacion*aviobj.FrameRate;

disp("Empieza la grabacion");

open(aviobj)
start(video)

for i=1:numFramesGrabacion
 I=getdata(video,1); % captura un frame guardado en memoria.
 imshow(I);
 writeVideo(aviobj, I);
end

stop(video)
close(aviobj);

%% Captar imagenes

clear;
video=videoinput('winvideo',1,'YUY2_320x240'); %
video.ReturnedColorSpace = 'rgb';
video.TriggerRepeat=inf; % disparos continuados
preview(video);
numImagenes = 10;

%Sacar captura
imagenes = zeros(240, 320, 3, numImagenes, 'uint8'); %Inicializar
for i=1:numImagenes
    disp("Pulsa una tecla para capturar la imagen "+i+"...");
    pause;  
    I = getsnapshot(video);
    imagenes(:, :, :, i) = I;
end

save("MaterialGenerado\ImagenesEntrenamiento_Calibracion","imagenes");



