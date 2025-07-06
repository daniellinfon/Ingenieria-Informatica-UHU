function [vectorMedias, matrizCovarianza, probabilidadPriori] = funcion_ajusta_LDA (X,Y)

    p = size(X,2);
    valoresClases = unique(Y);
    k = length(valoresClases);
    
    matrizCovarianza = zeros(p,p);
    vectorMedias = zeros(p,k);
    probabilidadPriori = zeros(k);
    numDatos = size(X,1);
    
    for i=1:k
        foi = Y == valoresClases(i);
        XClase = X(foi,:);
        vectorMedias(:,i) = mean(XClase)'; %traspuesta
        matrizCovarianza = matrizCovarianza + (size(XClase,1)-1) * cov(XClase) ;
        probabilidadPriori(i) = size(XClase,1)/numDatos;
    
    end
    
    matrizCovarianza = matrizCovarianza / numDatos - K;
    
end