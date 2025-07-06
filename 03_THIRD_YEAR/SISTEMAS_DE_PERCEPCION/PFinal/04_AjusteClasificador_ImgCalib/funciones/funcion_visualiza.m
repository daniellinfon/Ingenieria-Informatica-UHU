function Io = funcion_visualiza(Ii, Ib, Color, flagRepresenta)
    % Verifica si flagRepresenta se ha pasado como argumento
    if nargin < 4
        flagRepresenta = false; % Valor por defecto si no se especifica
    end
    
    % Verifica si la imagen de entrada es en escala de grises o color
    if size(Ii, 3) == 1
        % Si es en escala de grises
        R = Ii;
        G = Ii;
        B = Ii;
    else
        % Si es en color
        R = Ii(:,:,1);
        G = Ii(:,:,2);
        B = Ii(:,:,3);
    end
    
        R(Ib) = Color(1); % Canal rojo
        G(Ib) = Color(2); % Canal verde
        B(Ib) = Color(3); % Canal azul
 

    Io = cat(3, R, G, B); % Construye la imagen de salida
    
    
    % Si flagRepresenta es verdadero, muestra la imagen resultante en una ventana
    if flagRepresenta
        figure;
        imshow(Io);
        title('Imagen de salida');
    end
end

