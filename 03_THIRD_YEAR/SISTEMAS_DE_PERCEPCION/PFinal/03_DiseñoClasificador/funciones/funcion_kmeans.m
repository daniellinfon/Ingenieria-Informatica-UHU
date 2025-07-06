function idx = funcion_kmeans(XColor,numAgrup)
    numDatos = size(XColor,1);
    idx = zeros(numDatos,1);
    indSemillas = randsample(1:numDatos, numAgrup);
    c_ini = XColor(indSemillas,:);

    idx_ini = funcion_calcula_agrupacion(XColor,c_ini);
    hayCambio = true;

    while hayCambio
        hayCambio = false;

        centroides = funcion_calcula_centroides(XColor,idx_ini);
        idx = funcion_calcula_agrupacion(XColor, centroides);
        var = funcion_compara_matrices(idx,idx_ini);

        hayCambio = not(var);

        if hayCambio
            idx_ini = idx;
        end
    end
end

function idx = funcion_calcula_agrupacion(X,C)
    numDatos = size(X,1);
    numAgrup = size(C,1);
    idx = zeros(numDatos,1);
    matrizDist = zeros(numAgrup,numDatos);
    NP = X';
    for i=1:numAgrup
        p = C(i,:)';
        matrizDist(i,:) = calcula_distancia_punto_a_nube_puntos(p,NP);
    end

    for i=1:numDatos
        v = matrizDist(:,i);
        [vmin ind] = min(v);
        idx(i) = ind;
    end
end

function centroide = funcion_calcula_centroides(X, idx)
    
    numAgrup = max(idx);
    numDim = size(X,2);
    centroide = zeros(numAgrup,numDim);

    for i=1:numAgrup
        X_i = X(idx == i, :);
        centroide(i,:) = mean(X_i);
    end

end

function varLogica = funcion_compara_matrices(m1,m2)
    
    % comprobamos dimensiones
    
    [numFilas_m1 , numColumas_m1] = size(m1);
    [numFilas_m2 , numColumas_m2] = size(m2);
    
    if (numFilas_m1 ~= numFilas_m2) || (numColumas_m1 ~= numColumas_m2)
        varLogica = false; % por devolver algo en este caso
        
    else % sólo entra si las matrices  son iguales
        

        m1 = double(m1); % para asegurar que hacemos la diferencia en el  formato adecuado
        m2 = double(m2);

        dif = m1-m2;

        vMin = min(dif(:));
        vMax = max(dif(:));

        if vMin==vMax && vMin==0
            varLogica = true;
          
        else
            varLogica = false;
                       
        end
        
    end
end

