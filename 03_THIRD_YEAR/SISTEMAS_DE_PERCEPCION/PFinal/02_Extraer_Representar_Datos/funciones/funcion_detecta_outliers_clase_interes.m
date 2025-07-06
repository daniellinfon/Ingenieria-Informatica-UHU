function pos_outliers = funcion_detecta_outliers_clase_interes(X,Y,posClaseInteres)

    valores = unique(Y);
    XoI = X(Y==valores(posClaseInteres),:);
    
    pos_outliers = [];

    for i=1:3 %Para cada canal

        x = XoI(:,i);
        
        % close all, histogram(x), figure, boxplot(x)
        
        xOrd = sort(x);
        numValores = length(x);
        
        % Calcular valor del Cuartil 1
        posQ1 = round(0.25*numValores);
        Q1 = xOrd(posQ1);
        
        % Calcular valor del Cuartil 3
        posQ3 = round(0.75*numValores);
        Q3 = xOrd(posQ3);
        
        % RI = Rango Intercuartilico
        RI = Q3-Q1;
        
        vMinAceptado = Q1-1.5*RI;
        vMaxAceptado = Q3+1.5*RI;
        
        out = X(:,i)<vMinAceptado | X(:,i)>vMaxAceptado;
        out = out & Y==valores(posClaseInteres);
        
        pos_outliers = [pos_outliers; find(out)];

    end
    
    pos_outliers = unique(sort(pos_outliers));

end