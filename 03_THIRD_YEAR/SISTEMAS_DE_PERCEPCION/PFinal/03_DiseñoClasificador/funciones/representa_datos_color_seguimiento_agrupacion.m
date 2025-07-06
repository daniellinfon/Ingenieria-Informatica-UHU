function representa_datos_color_seguimiento_agrupacion(X_i,X,Y)
    figure
    plot3(X_i(:, 1), X_i(:, 2), X_i(:, 3), '*b')
    hold on
    plot3(X(Y == 0, 1) ,X(Y == 0, 2),X(Y == 0, 3),'*r')

    axis([0 255 0 255 0 255])
    legend('Datos Azul Agrupacion', 'Datos Otros Colores')
    xlabel("Componente R"), ylabel("Componente G"), zlabel("Componente B");
end

