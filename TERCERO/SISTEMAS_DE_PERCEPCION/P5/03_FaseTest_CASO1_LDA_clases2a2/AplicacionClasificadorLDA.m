clear;
Color = [0 0 255];
rutaCircCuad = '../02_FaseEntrenamiento_CASO1_LDA_clases2a2\01_CirculoCuadrado\DatosGenerados/';
rutaCircTri = '../02_FaseEntrenamiento_CASO1_LDA_clases2a2\02_CirculoTriangulo\DatosGenerados/';
rutaCuadTri = '../02_FaseEntrenamiento_CASO1_LDA_clases2a2\03_CuadradoTriangulo\DatosGenerados/';
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
        Zimagen(i,j) = (XImagen(i, j)-medias(j) / desviaciones(j));
    end
end

for i=1:N

    % LDA: Circ - Cuad
    load([rutaCircCuad 'LDA.mat']);

    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    valorCirCuad = eval(d12);

    % LDA: Circ - Triangulo
    load([rutaCircTri 'LDA.mat']);

    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    valorCirTri = eval(d12);

    % LDA: Cuad - Triangulo
    load([rutaCuadTri 'LDA.mat']);

    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    valorCuadTri = eval(d12);

    if valorCirCuad > 0 & valorCirTri > 0
        r = 'Objeto Circulo';
    elseif valorCirCuad < 0 & valorCuadTri > 0
        r = 'Objeto Cuadrado';
    else
        r = 'Objeto Triangulo';
    end

    figure,
    Ib = Ietiq == i;
    subplot(2,2,1)
    Io = funcion_visualiza(I,Ib, Color, false);
    imshow(Io);
    title(r);

    subplot(2,2,2)
    load([rutaCircCuad 'LDA.mat']);
    funcion_representa_datos(XoI, YoI, 1:3,nombresProblemaOI);
    hold on,
    funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    plot3(x1,x2,x3,'*g');

    subplot(2,2,3)
    load([rutaCircTri 'LDA.mat']);
    funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
    hold on,
    funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    plot3(x1,x2,x3,'*g');

    subplot(2,2,4)
    load([rutaCuadTri 'LDA.mat']);
    funcion_representa_datos(XoI, YoI,1:3,nombresProblemaOI);
    hold on,
    funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, XoI);
    Xi = Zimagen(i,espacioCcas);
    x1 = Xi(1); x2 = Xi(2); x3 = Xi(3);
    plot3(x1,x2,x3,'*g');
   

end


