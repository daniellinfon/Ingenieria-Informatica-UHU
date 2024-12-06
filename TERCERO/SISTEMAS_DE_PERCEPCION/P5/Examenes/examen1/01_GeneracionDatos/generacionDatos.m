%% PASOS PARA LA GENERACION DE LOS DATOS EN CADA

clc, clear;
addpath("../Funciones/");

numImgClase = 2;
numClases = 3;

X = [];
Y = [];

nombreDescriptores = {'Circularidad','Excentricidad', ' Solidez_CHull(Solidity)', ' Extension_BBox(Extent)', 'Extension_BBox(Invariante Rotacion)' , 'Hu1', 'Hu2', 'Hu3', 'Hu4', 'Hu5', 'Hu6', 'Hu7', 'DF1', 'DF2', 'DF3', 'DF4', 'DF5', 'DF6', 'DF7', 'DF8', 'DF9', 'DF10', 'NumEuler'};
% nombreClases:

%nombreClases  = {'Circulo','Triangulo','Cuadrado'}; %mirar si son corchetes o llaves

nombreClases{1} = "Circulo";
nombreClases{2} = "Cuadrado";
nombreClases{3} = "Triangulo";

% simboloClases: símbolos y colores para representar las muestras de cada clase
simbolosClases{1} = '*r';
simbolosClases{2} = '*b';
simbolosClases{3} = '*y';
% ------------------------------------

ruta = "../Imagenes/Entrenamiento/";
formato = ".jpg";

for i=1: numClases
    
    for j = 1: numImgClase

        nombreImagen = ruta + nombreClases(i) + num2str(j, '%02d') + formato;

        I = imread(nombreImagen);

        [IEtiq, N] = funcion_segmenta_imagen(I);
        %imtool(IEtiq);

        if N > 0

            XImagen = funcion_calcula_descriptores_imagen(IEtiq, N);
            YImagen = i*ones(N,1);% N es figura por imagen

        else

            XImagen = [];
            YImagen = [];

        end

        X = [X; XImagen];
        Y = [Y; YImagen]; 

    end
end


nombresProblema = [];
nombresProblema.descriptores = nombreDescriptores;
nombresProblema.clases = nombreClases;
nombresProblema.simbolos = simbolosClases;

save('./01_GeneracionDatos/DatosGenerados/conjunto_datos.mat', 'X', 'Y', "nombresProblema");

%% JUSTIFICACION ESTANDARIZACION

medias = mean(X);
desviaciones = std(X);
desviaciones(desviaciones == 0) = eps;
Z = X;

for i=1:size(X,1)
    for j=1:22
        Z(i,j) = (X(i, j)-medias(j) / desviaciones(j));
    end
end

save('./01_GeneracionDatos/DatosGenerados/conjunto_datos_estandarizados.mat', 'Z', 'Y', "nombresProblema");
save('./01_GeneracionDatos/DatosGenerados/datos_estandarizacion.mat', "medias", "desviaciones");
