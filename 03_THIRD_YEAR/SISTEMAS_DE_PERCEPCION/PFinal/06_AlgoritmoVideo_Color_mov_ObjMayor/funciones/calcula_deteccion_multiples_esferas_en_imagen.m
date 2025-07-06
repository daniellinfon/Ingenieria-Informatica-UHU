function Ib = calcula_deteccion_multiples_esferas_en_imagen(I, centroides_radios)
    
    numDatos = size(centroides_radios,1);
    Ib = false(size(I(:,:,1)));
    
    for i=1:numDatos
        Ib_i = calcula_deteccion_1esfera_en_imagen(I,centroides_radios(i,:));
        Ib = Ib | Ib_i;
    end
    
end

function Ib_deteccion_por_distancia = calcula_deteccion_1esfera_en_imagen(I, centro_radio)
    
    Rc = centro_radio(1);
    Gc = centro_radio(2);
    Bc = centro_radio(3);
    r = centro_radio(4);

    Ib_deteccion_por_distancia = false(size(I,1),size(I,2));

    for f=1:size(I,1)
        for c=1:size(I,2)
            R = double(I(f,c,1));G=double(I(f,c,2));B=double(I(f,c,3));
            if r >=  sqrt((R-Rc).^2 + (G-Gc).^2 + (B-Bc).^2)
                Ib_deteccion_por_distancia(f,c) = true;
            end
        end
    end
    
end