function centroides = funcion_calcula_centroides(IEtiq, N)
    centroides = zeros(N, 2); % Inicializa la matriz de centroides
    
    % Recorre cada etiqueta (de 1 a N) para calcular su centroide
    for i = 1:N
        % Encuentra los índices de los píxeles etiquetados con la etiqueta actual
        [filas, columnas] = find(IEtiq == i);
        
        centroides(i, 1) = mean(columnas); % Coordenada x
        centroides(i, 2) = mean(filas); % Coordenada y
    end
end
