function representa_datos_color_seguimiento_fondo(X,Y)
    figure
    plot3(X(Y == 1, 1) ,X(Y == 1, 2),X(Y == 1, 3),'*b')
    hold on
    plot3(X(Y == 0, 1) ,X(Y == 0, 2),X(Y == 0, 3),'*r')

    axis([0 255 0 255 0 255])
    legend('Datos Azul', 'Datos Otros Colores')
    xlabel("Componente R"), ylabel("Componente G"), zlabel("Componente B");
end

