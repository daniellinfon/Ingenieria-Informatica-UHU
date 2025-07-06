function funcion_Reconoce_Letras2(nombreImagen)

    I = imread(nombreImagen);

    [Ietiq, N] = funcion_segmenta_imagen(I);

    XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);

    load("./01_GeneracionDatos\DatosGenerados\ConjuntoDatos\datos_estandarizacion.mat");
    load("./01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");


    ZImagen = XImagen;
    
    for i=1:size(XImagen,1)
        for j=1:22
            ZImagen(i,j) = (XImagen(i, j)-medias(j) / desviaciones(j));
        end
    end

    rutaLDA = "02_FaseEntrenamiento\ACDEFG-LDA\DatosGenerados\LDA.mat";
    rutaQDA = "02_FaseEntrenamiento\EFG-QDA\DatosGenerados\QDA.mat";
    rutaKNN = "02_FaseEntrenamiento\ACD-KNN\DatosGenerados\KNN.mat";
   
    color = [255 0 0];

    for i=1:N

        numEuler = ZImagen(i, end);

        if numEuler==-1 % b

            r = ['Letra: ' nombresProblema.clases{2}];

            figure,
            Ib = Ietiq == i;
            Io = funcion_visualiza(I,Ib, color, false);
            imshow(Io);
            title(r);

        else %todo lo que no sea b

            load(rutaLDA);

            Xi = ZImagen(i,espacioCcas);
            x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
            valor = eval(d12);
    
            if valor > 0

                load(rutaKNN);
                valoresClases = unique(YTrain); %dentro
                Xi = ZImagen(i,espacioCcas);
                Yi =  funcion_knn(Xi, XTrain, YTrain, 5);
                posClase = find(valoresClases == Yi);

                r = ['Letra: ' nombresProblemaOI.clases{posClase}];

                figure,
                Ib = Ietiq == i;
                Io = funcion_visualiza(I,Ib, color, false);
                imshow(Io);
                title(r);
        
                figure,
                funcion_representa_datos(XTrain, YTrain,1:3,nombresProblemaOI);
                hold on,
                plot3(x1,x2,x3,'*b');

            else

                load(rutaQDA)
                valoresClases = unique(YoI);

            Xi = ZImagen(i,espacioCcas);
            Yi = funcion_aplica_QDA(Xi, vectorMedias, matricesCovarianzas, probabilidadPriori, valoresClases);
            posClase = find(valoresClases == Yi);
            r = ['Letra: ' nombresProblemaOI.clases{posClase}];

            figure,
            Ib = Ietiq == i;
            Io = funcion_visualiza(I,Ib, color, false);
            imshow(Io);
            title(r);

            figure,
            funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
            Xi = ZImagen(i,espacioCcas);
            x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
            plot3(x1,x2,x3,'*g');
                
            end
    
            

        end

        %pause;
    end

end