clear;
Color = [0 0 255];
rutaKNN = '../02_FaseEntrenamiento_CASO4_knn_LDA\01_CirculosTriangulos_Cuadrados\DatosGenerados\KNN.mat';
rutaLDA = '../02_FaseEntrenamiento_CASO4_knn_LDA\02_Circulos_Triangulos\DatosGenerados\LDA.mat';

load(rutaKNN);

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

    load(rutaKNN);
    Xi = Zimagen(i,espacioCcas);
    Yi =  funcion_knn(Xi, XTrain, YTrain, 5);
    posClase = find(valoresClases == Yi);

    %LDA
    if posClase == 1

        load(rutaLDA);
        Xi = Zimagen(i,espacioCcas);
        x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
        valor = eval(d12);

        if valor > 0
            r = ['Objeto: ' nombresProblemaOI.clases{1}];
        else
            r = ['Objeto: ' nombresProblemaOI.clases{2}];
        end

        figure,
        Ib = Ietiq == i;
        subplot(1,2,1)
        Io = funcion_visualiza(I,Ib, Color, false);
        imshow(Io);
        title(r);

        subplot(1,2,2)
        funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
        hold on,
        funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
        plot3(x1,x2,x3,'*g');

    elseif posClase == 2
       
        r = ['Objeto: ' nombresProblemaOI.clases{2}];
        
        figure,
        Ib = Ietiq == i;
        subplot(1,2,1)
        Io = funcion_visualiza(I,Ib, Color, false);
        imshow(Io);
        title(r);
    
        subplot(1,2,2)
        funcion_representa_datos(XTrain, YTrain,1:3,nombresProblemaOI);
        Xi = Zimagen(i,espacioCcas);
        x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
        plot3(x1,x2,x3,'*g');

    end

end


