function umbral = funcion_min_entre_max(image)

    image = rgb2gray(image);
    
    % Calcular el histograma
    h = imhist(image);
    
    % Encontrar el primer pico máximo
    pico1 = find(h == max(h));
    
    % Calcular el segundo pico
    max_val = 0;
    pico2 = 0;
    
    for g = 1:256
    
        val = h(g)*((g - pico1)^2); % h(g) = numero de pixeles con ese nivel de gris
    
        if val > max_val
            max_val = val;
            pico2 = g;
        end
    
    end
    
    % Encontrar el mínimo entre los dos picos máximos
    if pico1 < pico2
        min_val = min(h(pico1:pico2));
    else
        min_val = min(h(pico2:pico1));
    end
    
    umbral = find(h == min_val);
    umbral = umbral-1;

end