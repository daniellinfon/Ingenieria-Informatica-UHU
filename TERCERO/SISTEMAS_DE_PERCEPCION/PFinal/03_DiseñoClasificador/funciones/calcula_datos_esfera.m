function datosEsfera = calcula_datos_esfera(XColor, XFondo)
    
    datosEsfera = zeros(1,6);
    
    centroideColor = mean(XColor);

    Rc = centroideColor(1);
    Gc = centroideColor(2); 
    Bc = centroideColor(3);

    centroideColor = centroideColor';
    
    datosEsfera(1) = Rc;
    datosEsfera(2) = Gc;
    datosEsfera(3) = Bc;

    XColor = XColor';
    XFondo = XFondo';

    vDistColor = calcula_distancia_punto_a_nube_puntos(centroideColor,XColor);
    vDistFondo = calcula_distancia_punto_a_nube_puntos(centroideColor,XFondo);

    r1 = max(vDistColor);
    r2 = min(vDistFondo);
    r12 = (r1+r2)/2;

    datosEsfera(4) = r1;
    datosEsfera(5) = r2;
    datosEsfera(6) = r12;

end

