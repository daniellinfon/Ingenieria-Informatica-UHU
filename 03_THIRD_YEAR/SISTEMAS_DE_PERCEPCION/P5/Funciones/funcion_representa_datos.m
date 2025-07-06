function funcion_representa_datos(X, Y, espacioCcas, nombresProblema)

    nD = nombresProblema.descriptores;
    nC = nombresProblema.clases;
    s = nombresProblema.simbolos;
    
    dim = length(espacioCcas);
    
    codifClases = unique(Y);
    numClases = length(codifClases);
    
    % figure, 
    hold on
    
    for i=1:numClases
        Xi = X(Y == codifClases(i), espacioCcas);
    
        if dim == 2
            plot(Xi(:,1), Xi(:,2), s{i});
        else
            plot3(Xi(:,1), Xi(:,2), Xi(:, 3), s{i});
        end
    end
    
    legend(nC);
    xlabel(nD(espacioCcas(1)));
    ylabel(nD(espacioCcas(2)));
    
    if dim == 3 
        zlabel(nD(espacioCcas(3)));
    end

end