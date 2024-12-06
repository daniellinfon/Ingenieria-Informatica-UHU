function umbral = funcion_otsu(image)

image = rgb2gray(image);

h = imhist(image);

maxVarianza = 0;
valoresGris = 1:256;
valoresGris = valoresGris';

numPix = sum(h);
gMean = sum(h.*valoresGris)/numPix;
    
% Recorrer todos los posibles valores de umbral
for g = 2:255

    numPix1 = sum(h(1:g));
    numPix2 = sum(h((g+1):end));
        
    % Calcular la varianza entre clases
    if numPix1*numPix2==0
        varEntreClases = 0;
    else

        gMean1 = sum(h(1:g).*valoresGris(1:g))/numPix1;
        gMean2 = sum(h((g+1):end).*valoresGris((g+1):end))/numPix2;

        w1 = numPix1/numPix;
        w2 = numPix2/numPix;

        varEntreClases = w1*((gMean1-gMean)^2) + w2*((gMean2-gMean)^2);

    end

    % Verificar si es la varianza máxima
    if varEntreClases > maxVarianza
        umbral = g-1;
        maxVarianza = varEntreClases;
    end

end