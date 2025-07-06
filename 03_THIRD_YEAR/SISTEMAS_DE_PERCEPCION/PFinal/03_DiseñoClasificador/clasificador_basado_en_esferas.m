clear;
addpath("funciones");
ruta = '../02_Extraer_Representar_Datos/VariablesGeneradas/';
load(ruta+"datosXY_sinOut.mat");
ruta = '../01_Generacion_del_Material/MaterialGenerado/';
load(ruta+"ImagenesEntrenamiento_Calibracion.mat");


%% Generacion y representacion en el espacio
posClaseInteres = 2;
valores = unique(Y);
FoI = (Y==valores(posClaseInteres));
XColor = double(X(FoI,:));

posClaseInteres = 1;
valores = unique(Y);
FoI = (Y==valores(posClaseInteres));
XFondo = double(X(FoI,:));

datosEsfera = calcula_datos_esfera(XColor, XFondo)
centroides = datosEsfera(1:3);
radios = datosEsfera(4:6);

%Representar esferas
for i=1:length(radios)
    representa_datos_color_seguimiento_fondo(X,Y);
    hold on, representa_esfera(centroides, radios(i)), hold off;
    title(['Esfera de Radio: ' num2str(radios(i))]);
    disp("Pulsa una tecla para pasar al Radio "+i+"...");
    pause;
    close all;
end

%% Mostrar imagenes con pixeles esferas
numImagenes = size(imagenes,4);
color = [255 0 0];
for i=1:numImagenes

    disp("Pulsa una tecla para pasar a la imagen "+i+"...");
    pause;
    close all;
 
    %Mostrar imagen original
    Io = imagenes(:,:,:,i);
    figure; subplot(2, 2, 1);
    imshow(Io);
    title(["Imagen "+  num2str(i)]);

    Ib1 = calcula_deteccion_1esfera_en_imagen(Io, [centroides radios(1)]);
    Ib2 = calcula_deteccion_1esfera_en_imagen(Io, [centroides radios(2)]);
    Ib3 = calcula_deteccion_1esfera_en_imagen(Io, [centroides radios(3)]);

    subplot(2, 2, 2), I = funcion_visualiza(Io, Ib1, color); imshow(I);
    title('Radio 1');
    subplot(2, 2, 3), I = funcion_visualiza(Io, Ib2, color);imshow(I);
    title('Radio 2');
    subplot(2, 2, 4), I = funcion_visualiza(Io, Ib3, color);imshow(I);
    title('Radio Compromiso');

end

%% Calcular datos de multiples esferas

numAgrup = 3;
idx = funcion_kmeans(XColor,numAgrup);

%idx = kmeans(XColor,numAgrup);
%unique(idx);

datos_multiples_esferas = zeros(numAgrup,6);

for i=1:numAgrup
    X_i = XColor(idx == i, :);
    
    datosEsfera = calcula_datos_esfera(X_i, XFondo);
    datos_multiples_esferas(i,:) = datosEsfera;
end


save("VariablesGeneradas\datos_multiples_esferas","datos_multiples_esferas");






