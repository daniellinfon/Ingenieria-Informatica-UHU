function funcion_Reconoce_Letras(nombreImagen)

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

    rutaLDA = "02_FaseEntrenamiento\AD-LDA\DatosGenerados\LDA.mat";
    rutaQDA = "02_FaseEntrenamiento\CEFG-QDA\DatosGenerados\QDA.mat"
   
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

        elseif numEuler == 0 %a-d

            load(rutaLDA);

            Xi = ZImagen(i,espacioCcas);
            x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
            valor = eval(d12);
    
            if valor > 0
                r = ['Letra: ' nombresProblemaOI.clases{1}];
            else
                r = ['Letra: ' nombresProblemaOI.clases{2}];
            end
    
            figure,
            Ib = Ietiq == i;
            Io = funcion_visualiza(I,Ib, color, false);
            imshow(Io);
            title(r);
    
            figure,
            funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
            hold on,
            funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
            plot3(x1,x2,x3,'*b');

        elseif numEuler == 1

            load(rutaQDA);
            valoresClases = unique(YoI);

            Xi = ZImagen(i,espacioCcas);
            Yi = funcion_aplica_QDA(Xi, vectorMedias, matricesCovarianzas, probabilidadPriori, valoresClases);
            posClase = find(valoresClases == Yi);
            r = ['Objeto ' nombresProblemaOI.clases{posClase}];

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

        pause;
    end

end