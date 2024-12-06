function  representa_esfera(centroide, Radio)
    [R,G,B] = sphere(100);

     % Matrices de puntos de una esfera centrada en el origen de radio unidad
     x = Radio*R(:)+centroide(1); y = Radio*G(:)+centroide(2); z = Radio*B(:)+centroide(3);
     plot3(x,y,z, '.g')
end

