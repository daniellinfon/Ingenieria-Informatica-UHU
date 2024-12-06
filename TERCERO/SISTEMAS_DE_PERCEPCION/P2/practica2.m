% 1. Lee la imagen del fichero "ImagenBinaria.tif". Comprueba que es una imagen binaria
% en la que están localizados los píxeles que integran los objetos que se muestran en la 
% imagen.

Img = imread("ImagenBinaria.tif")
imshow(Ib)

% 2. Genera una matriz binaria tipo double o logical donde: el valor 0 signifique píxel de 
% fondo; el valor 1 signifique píxel de objeto.

Ib = Img == 255

% 3. Genera una imagen en color, donde se visualice con un color diferente los objetos 
% presentes en la imagen (al haber 6 objetos, pueden utilizarse los colores primarios y 
% secundarios)

addpath("funciones")
[IEtiq, N] = funcion_etiquetar(logical(Img));
color = [255,0,0; 0,255,0; 0,0,255; 255,255,0; 255,0,255;0,255,255]
valores = unique(IEtiq(:));
for i=1:N
    Ib = IEtiq==i
    Img = funcion_visualiza(Img,Ib,color(i,:))
end
figure, imshow(Img)
close all
imtool(IEtiq)

% 4. Genera una imagen donde se localicen, a través de su centroide, los objetos de mayor 
% y menor área (ver documentación para la definición de área y centroide).

areas = funcion_calcula_areas(IEtiq,N)
centroides = funcion_calcula_centroides(IEtiq,N)
posMax = find(areas == max(areas))
posMin = find(areas == min(areas))
figure,imshow(Img)
hold on
plot(centroides(posMax,1),centroides(posMax,2),'*r')
plot(centroides(posMin,1),centroides(posMin,2),'*b')
title("ROJO-MAX Y AZUL-MIN")
close all

%5. Genera una imagen binaria donde sólo se visualicen los dos objetos de área mayor.

areasOrd = sort(areas)
IbFilt = funcion_filtra_objetos(Io,areasOrd(end-1))
figure,imshow(IbFilt)
