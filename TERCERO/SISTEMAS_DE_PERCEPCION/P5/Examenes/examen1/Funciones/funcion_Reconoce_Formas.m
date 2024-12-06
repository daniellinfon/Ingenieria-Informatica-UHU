function funcion_Reconoce_Formas(nombre)

I = imread(nombre);

[Ietiq, N] = funcion_segmenta_imagen(I);

XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);

load("./01_GeneracionDatos\DatosGenerados\ConjuntoDatos\datos_estandarizacion.mat");

ZImagen = XImagen;

for i=1:size(XImagen,1)
    for j=1:22
        ZImagen(i,j) = (XImagen(i, j)-medias(j) / desviaciones(j));
    end
end

rutaKNN = './02_FaseEntrenamiento\01_CirculoCuadradoVSTrianguloKNN\DatosGenerados\KNN.mat';
load(rutaKNN);
valoresClases = unique(YTrain);
rutaLDA = './02_FaseEntrenamiento\02_CirculoVSCuadradoLDA\DatosGenerados\LDA.mat';
color = [0 0 255];

for i=1:N

    load(rutaKNN);
    Xi = ZImagen(i,espacioCcas);
    Yi =  funcion_knn(Xi, XTrain, YTrain, 5);
    posClase = find(valoresClases == Yi);

    %LDA
    if posClase == 1

        load(rutaLDA);
        Xi = ZImagen(i,espacioCcas);
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
        Io = funcion_visualiza(I,Ib, color, false);
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
        Io = funcion_visualiza(I,Ib, color, false);
        imshow(Io);
        title(r);
    
        subplot(1,2,2)
        funcion_representa_datos(XTrain, YTrain,1:3,nombresProblemaOI);
        Xi = ZImagen(i,espacioCcas);
        x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
        plot3(x1,x2,x3,'*g');

    end

end



end