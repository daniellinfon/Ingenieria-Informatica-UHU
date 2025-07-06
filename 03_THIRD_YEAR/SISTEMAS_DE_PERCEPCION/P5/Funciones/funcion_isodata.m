function umbral = funcion_isodata(image)

    image = rgb2gray(image);
    
    % Estimar un valor de umbral inicial T como la media de la intensidad
    T = mean(image(:));
    
    % Inicializar la diferencia entre umbrales sucesivos
    diffT = inf;
    
    deltaT = 0.5;
    
    % Repetir hasta que la diferencia ΔT sea menor que el valor predefinido deltaT
    while diffT > deltaT
    
        % Agrupar píxeles en dos grupos según el umbral T
        G1 = image(image <= T);
        G2 = image(image > T);
    
        % Calcular las medias de intensidad m1 y m2
        m1 = mean(G1);
        m2 = mean(G2);
    
        % Calcular un nuevo valor de umbral T
        newT = (m1 + m2) / 2;
    
        % Calcular la diferencia entre los umbrales sucesivos
        diffT = abs(newT - T);
    
        % Actualizar el umbral T
        T = newT;
    
    end
    
    % El umbral final
    umbral = T;

end