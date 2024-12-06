%% JUSTIFICACION ESTANDARIZACION
clear;
load("DatosGenerados\conjunto_datos.mat");

medias = mean(X);
desviaciones = std(X);
desviaciones(desviaciones == 0) = eps;
Z = X;

for i=1:size(X,1)
    for j=1:22
        Z(i,j) = (X(i, j)-medias(j) / desviaciones(j));
    end
end

save('DatosGenerados/conjunto_datos_estandarizacion.mat', 'Z', 'Y', "nombresProblema");
save('DatosGenerados/datos_estandarizacion.mat', 'medias', 'desviaciones');