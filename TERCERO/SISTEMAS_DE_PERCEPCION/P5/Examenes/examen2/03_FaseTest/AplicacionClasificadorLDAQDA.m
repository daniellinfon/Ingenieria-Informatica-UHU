clear,clc, close all;
load("./01_GeneracionDatos\DatosGenerados\ConjuntoDatos\conjunto_datos_estandarizados.mat");
addpath("./Funciones/");
addpath("./Imagenes/");
ruta = "./ImagenesLetras/Test/";
formato = ".tif";

numImg = 7;

for i=1:numImg

    nombreImagen = ruta + nombresProblema.clases(i) + num2str(3) + formato;
    funcion_Reconoce_Letras(nombreImagen);

end