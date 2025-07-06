function h = funcion_histograma_v1(I)

    [filas, columnas] = size(I);
    h = zeros(256,1)

    for i = 1:filas
        for j = 1:columnas
            intensidad = I(i, j);
            h(intensidad + 1) = h(intensidad + 1) + 1;
        end
    end
end

