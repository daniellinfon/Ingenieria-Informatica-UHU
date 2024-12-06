%% PASOS PARA LA GENERACION DE LOS DATOS EN CADA

clc, clear;
addpath("../Funciones");

% rutaImagen =  './MaterialFacilitado/ImagenesPractica5/Entrenamiento/';
% nombreImagen = 'Triangulo01.jpg';
% I = imread([rutaImagen nombreImagen]);
% imshow(I), figure, imhist(I);

%% 1.1.- BINARIZAR CON METODOLOGÍA DE SELECCIÓN 

%Ibin = I < 255*graythresh(I);
%imshow(Ibin);

%% 1.2.- ELIMINAR POSIBLES COMPONENTES CONECTADAS RUIDOSAS:
%COMPONENTE RUIDOSA

% COMPONENTES DE MENOS DEL 0.1% DEL NUMERO TOTAL DE PIXELES EN LA IMAGEN
% O NUMERO DE PIXELES MENOR AL ARE DEL OBJETO MAYOR /5
% SE DEBE CUMPLIR CUALQUIERA DE LAS DOS CONDICIONES
% Para ello, se debe programar la siguiente funcion:

%IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);

%% 1.3.- ETIQUETAR.
% Genera matriz etiquetada Ietiq y número N de agrupaciones conexas
% Estos pasos descritos en los puntos 1.1., 1.2. y 1.3. deben
% materializarse en la función: [Ietiq, N] = funcion_segmenta_imagen(I);

% [Ietiq, N] = funcion_segmenta_imagen(I);

%% 1.4.- CALCULAR TODOS LOS DESCRIPORES DE CADA AGRUPACIÓN CONEXA

% Genera Ximagen - matriz de N filas y 23 columnas (los 23 descriptores
% generados en el orden indicado en la práctica)

% XImagen = funcion_calcula_descriptores_imagen(Ietiq,N);

%% 1.5.- GENERAR Yimagen
% Genera Yimagen - matriz de N filas y 1 columna con la codificación
% empleada para la clase a la que pertenecen los objetos de la imagen
% GENENERACIÓN VARIABLE TIPO ESTRUCT nombresProblema (contiene los nombres 
%de los descriptores calculados y de las clases, así como los símbolos 
%utilizados en una representación gráfica):

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

save('DatosGenerados/conjunto_datos.mat', 'X', 'Y', "nombresProblema");







