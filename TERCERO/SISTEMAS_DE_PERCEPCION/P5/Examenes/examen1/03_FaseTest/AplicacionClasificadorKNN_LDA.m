clear,clc;

addpath("./Funciones/");
addpath("./Imagenes/");
ruta = "./Imagenes/Test/Test";
formato = ".jpg";

numImg = 2;

for i=1:numImg

    nombreImagen = ruta + num2str(i) + formato;
    funcion_Reconoce_Formas(nombreImagen);

end