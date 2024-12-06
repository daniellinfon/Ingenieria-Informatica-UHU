function [IEtiq,N]  = funcion_etiquetar(Ib)
    % Imagen ampliada
    [numFilasIb, numColIb] = size(Ib);
    IEtiq = double(zeros(numFilasIb + 2, numColIb + 2));

    % Etiquetado inicial
    cont = 0;
    for i = 1:numFilasIb
        for j = 1:numColIb
            if Ib(i,j) == 1
                cont = cont + 1;
                IEtiq(i+1, j+1) = cont;
            end
        end
    end

    % Mientras haya cambios
    flagbin = true;
    while flagbin
        flagbin = false;
        % Pasada arriba-abajo
        for i = 2:numFilasIb+1
            for j = 2:numColIb+1
                if IEtiq(i,j) > 0
                    vecindad = [IEtiq(i-1,j-1:j+1), IEtiq(i,j-1:j)];
                    vecindad = vecindad(vecindad > 0);
                    vmin = min(vecindad);

                    if IEtiq(i,j) > vmin
                        IEtiq(i,j) = vmin;
                        flagbin = true;
                    end
                end
            end
        end

        % Pasada abajo-arriba
        for i = numFilasIb+1:-1:2
            for j = numColIb+1:-1:2
                if IEtiq(i,j) > 0
                    vecindad = [IEtiq(i+1,j-1:j+1), IEtiq(i,j:j+1)];
                    vecindad = vecindad(vecindad > 0);
                    vmin = min(vecindad);

                    if IEtiq(i,j) > vmin
                        IEtiq(i,j) = vmin;
                        flagbin = true;
                    end
                end
            end
        end
    end

    % Quitar borde
    IEtiq = IEtiq(2:numFilasIb+1, 2:numColIb+1);

    % Corregir etiquetas
    valores = unique(IEtiq(:));
    valores = valores(valores > 0);
    N = length(valores);

    for k = 1:N
        Ibin = IEtiq == valores(k);
        IEtiq(Ibin) = k;
    end
end

%bwlabel(ib)

