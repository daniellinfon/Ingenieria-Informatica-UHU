function vDistancias = calcula_distancia_punto_a_nube_puntos(P, NP)

 numDatos = size(NP,2);
 vDistancias = zeros(1,numDatos);
 A = P;

 for i=1:numDatos
     B=NP(:,i);

    %Distancia entre 2 puntos
    vDistancias(i) = sqrt(sum((A-B).^2));
 end

end

%% Otra opcion
 % PAmp = repmat(P,1,numDatos);
 % vDistancias = sqrt(sum((PAmp-NP).^2));