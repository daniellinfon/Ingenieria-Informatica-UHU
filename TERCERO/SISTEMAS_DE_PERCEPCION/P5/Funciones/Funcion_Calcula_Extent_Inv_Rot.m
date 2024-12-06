function Extent = Funcion_Calcula_Extent_Inv_Rot(Matriz_Binaria)
    % Centra el objeto en la matriz binaria
    IbinCentrada = Funcion_Centra_Objeto(Matriz_Binaria);

    % Define el paso de rotación
    paso_rotacion = 5;

    % Inicializa el valor máximo de la extensión
    max_extent = 0;

    % Recorre los ángulos de rotación de 0 a 355 grados
    for angulo = 0:paso_rotacion:355
        % Rota la matriz centrada
        IRotada = imrotate(IbinCentrada, angulo);

        % Calcula el bounding box del objeto en la matriz rotada
        %stats = regionprops(IRotada, 'BoundingBox', 'Area');

        % Toma el primer objeto detectado (asumimos un solo objeto)
        %boundingBox = cat(1, stats.BoundingBox);
        %areaObjeto = cat(1, stats.Area);

        % Calcula el área del bounding box
        [f,c] = find(IRotada);
        fmin = min(f)-0.5;
        fmax = max(f)-0.5;
        cmin = min(c)-0.5;
        cmax = max(c)-0.5;
        ABB = (cmax - cmin)*(fmax-fmin);
        AObj = sum(IRotada(:));
        extent = AObj/ABB;

        % Actualiza el valor máximo de la extensión
        if extent > max_extent
            max_extent = extent;
        end
        
    end

    % Retorna la extensión máxima invariante a la rotación
    Extent = max_extent;
end
