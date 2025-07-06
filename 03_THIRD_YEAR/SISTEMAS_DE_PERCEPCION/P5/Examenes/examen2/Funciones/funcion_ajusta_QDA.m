function [vectorMedias, matricesCovarianzas, probabilidadPriori] = funcion_ajusta_QDA (X,Y)

    p = size(X,2);
    valoresClases = unique(Y);
    k = length(valoresClases);
    
    matricesCovarianzas = zeros(p,p,k);
    vectorMedias = zeros(p,k);
    probabilidadPriori = zeros(k);
    numDatos = size(X,1);

    for i=1:k
    
        foi = Y == valoresClases(i);
        XClase = X(foi,:);
        vectorMedias(:,i) = mean(XClase)'; %traspuesta
        matricesCovarianzas(:,:,i) = cov(XClase);
        probabilidadPriori(i) = size(XClase,1)/numDatos;
    
    end
end