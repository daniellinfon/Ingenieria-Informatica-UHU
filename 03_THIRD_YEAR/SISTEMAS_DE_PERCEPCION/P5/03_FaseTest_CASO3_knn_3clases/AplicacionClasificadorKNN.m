clear;
Color = [0 0 255];
ruta = '../02_FaseEntrenamiento_CASO3_knn_3clases\DatosGenerados\KNN.mat';
load(ruta);
addpath('../Funciones');

rutaImagen =  '../Imagenes/Test/';
nombreImagen = 'Test1.jpg';
I = imread([rutaImagen nombreImagen]);
%imshow(I), figure, imhist(I);

[Ietiq, N] = funcion_segmenta_imagen(I);

XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);

load("../01_GeneracionDatos\DatosGenerados\datos_estandarizacion.mat");
Zimagen = XImagen;

for i=1:size(XImagen,1)
    for j=1:22
        Zimagen(i,j) = (XImagen(i,j)-medias(j) / desviaciones(j));
    end
end

XTrain = XoI;
YTrain = YoI;
valoresClases = unique(YTrain);

for i=1:N

    Xi = Zimagen(i,espacioCcas);
    Yi =  funcion_knn(Xi, XTrain, YTrain, 5);
    posClase = find(valoresClases == Yi);
    r = ['Objeto ' nombresProblemaOI.clases{posClase}];

    figure,
    Ib = Ietiq == i;
    subplot(1,2,1)
    Io = funcion_visualiza(I,Ib, Color, false);
    imshow(Io);
    title(r);

    subplot(1,2,2)
    funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    plot3(x1,x2,x3,'*g');

end


