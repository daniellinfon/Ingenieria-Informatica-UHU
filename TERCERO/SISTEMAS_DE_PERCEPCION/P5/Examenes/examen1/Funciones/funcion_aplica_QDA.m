function [YQDA,d] = funcion_aplica_QDA (X, vectorMedias, matricesCovarianzas, probabilidadPriori, valoresClases)

    N = size(X,1);
    YQDA = zeros(N,1);
    k = length(valoresClases);
    d = zeros(N,k);

    for i=1:N
        Xi = X(i,:)';
        for j=1:k
            M=vectorMedias(:,j);
            Mcov = matricesCovarianzas(:,:,j);
            Pp = probabilidadPriori(j);
            d(i,j) = -0.5*(Xi-M)' * pinv(Mcov)*(Xi-M) - 0.5*log(det(Mcov)) + log(Pp); 
            vpa(d(i,j),3);
        end
        [~, ind] = max(d(i,:));
        YQDA(i) = valoresClases(ind); 
    end
    
end