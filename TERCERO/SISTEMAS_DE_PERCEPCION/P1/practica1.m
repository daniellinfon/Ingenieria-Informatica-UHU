%%Ejercicios
% 1. Con la instrucción imfinfo de Matlab obtén la siguiente información de la imagen "P1_1.jpg": 
% anchura en píxeles, altura en píxeles, tipo de imagen y profundidad de bit.

imfinfo("P1_1.jpg")

% anchura en píxeles: 500
% tipo de imagen: jpg
% profundidad de bit: 24

% 2. Lee la imagen y guárdala en una variable de Matlab de nombre Imagen1.

Imagen1 = imread('P1_1.jpg');

% 3. Visualiza esta imagen con la instrucción imtool y con la instrucción imshow. 
% Familiarízate con los entornos gráficos de salida de cada una de estas instrucciones.

imtool(Imgen1)
imshow(Imgen1)

% 4. Con la instrucción whos obtén la siguiente información de la variable Matlab Imagen1: tipo de dato y rango.

whos Imgen1
%tipo: uint8
%rango: 0-255

% 5. Utilizando la instrucción max, calcula el valor máximo de la variable Imagen1 (máximo nivel de intensidad).

max(Imgen1, [], "all")
max(Imgen1(:))

% 6. Calcula en Matlab la imagen complementaria de Imagen1, denominándola Imagen2. 
% Visualiza esta imagen y guárdala en un fichero de imagen del mismo formato que la imagen original empleando 
% la instrucción imwrite.

Imagen2 = 255 - Imgen1
imshow(Imagen2)
imwrite(Imagen2,'Imagen2.jpg');

% 7. Crea y visualiza una matriz, de nombre Imagen3, con los niveles de rojo de la imagen Imagen1. 
% Notar que esta nueva matriz es una imagen en niveles de gris.

Imagen3 = Imagen1(:,:,1)
imshow(Imagen3)

% 8. Utiliza la función imadjust con la configuración ImagenSalida=imadjust(ImagenEntrada,[],[],gamma) para, 
% mediante la modificación del parámetro gamma, obtener una imagen Imagen4 más clara (asignar gamma=0.5) 
% y una imagen Imagen5 más oscura (asignar gamma=1.5) que Imagen3. Visualiza estas imágenes y representa su histograma 
% mediante la función Matlab imhist.Interpreta cualitativamente los resultados de la operación realizada.

Imagen4 = imadjust(Imagen3, [], [], 0.5)
Imagen5 = imadjust(Imagen3, [], [], 1.5)
figure(3), imshow(Imagen3)
figure(4), imshow(Imagen4)
figure(5), imshow(Imagen5)

close all

imhist(Imagen4); figure; imhist(Imagen5)

% 9. Utiliza la función imabsdiff, para crear una nueva imagen Imagen6 que refleje la diferencia absoluta de Imagen4 e Imagen5. 
% Interpreta los resultados. Realiza la misma operación sin utilizar la función imabsdiff y comprueba que obtienes los 
% mismos resultados.Para ello, implementa y utiliza la siguiente función que permite saber si el contenido de 
% dos matrices de la misma dimensión es el mismo:
% varLogica = funcion_compara_matrices(M1, M2)
% donde varLogica es una variable lógica indica si M1 y M2 son iguales (valor true) o diferentes (valor false)

Imagen6 = imabsdiff(Imagen4, Imagen5)
imshow(Imagen6)

ImagenDiff = abs(double(Imagen5) - double(Imagen4))
Imagen7 = uint8(ImagenDiff)
addpath('funciones')
comprobar = funcion_compara_matrices(Imagen7, Imagen6)

% 10. Implementación de histograma de una imagen:

% a. Implementa una función que tenga como objetivo calcular el histograma de una imagen de intensidad I. 
% La función debe devolver un vector h con la información numérica del histograma. h = función_histograma(I)
% Deben implementarse dos versiones de la función: la primera, que realiza un recorrido por cada píxel de la imagen
% para generar el histograma; la segunda, que realiza un recorrido por cada posible nivel de gris que puede 
% estar presente en la imagen de entrada.
% b. Aplica la función anterior para generar y visualizar el histograma de la componente verde de la imagen de la práctica.

h1 = funcion_histograma_v1(double(Imagen1(:,:,2)))
imhist(Imagen1(:,:,2))

h2 = funcion_histograma_v2(double(Imagen1(:,:,2)))
imhist(Imagen1(:,:,2))

% c. Comprueba que obtienes los mismos resultados que genera la función Matlab imhist. La comprobación debe realizarse visualmente, 
% representando los histogramas en una misma gráfica, y numéricamente, utilizando la función funcion_compara_matrices.
h3 = imhist(Imagen1(:,:,2))

figure;
niveles_gris = 0:255;
plot(niveles_gris, h3, 'b');
hold on;
plot(niveles_gris, h2, 'r');
legend('funcion_histograma', 'imhist');
xlabel('Niveles de gris');
ylabel('Frecuencia');
title('Comparación de histogramas');

comprobar = funcion_compara_matrices(h1,h3);
