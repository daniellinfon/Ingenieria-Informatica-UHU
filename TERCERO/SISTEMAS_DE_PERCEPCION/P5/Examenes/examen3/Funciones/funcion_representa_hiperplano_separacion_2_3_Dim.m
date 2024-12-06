function funcion_representa_hiperplano_separacion_2_3_Dim(coef_d12, X)

if length(coef_d12) == 3

    a = coef_d12(1);
    b = coef_d12(2);
    c = coef_d12(3);

    % Establecer los límites de los ejes
    x1min = min(X(:, 1));
    x1max = max(X(:, 1));
    x2min = min(X(:, 2));
    x2max = max(X(:, 2));

    paso1 = (x1max - x1min) / 100;  % Puedes ajustar el valor según sea necesario
    paso2 = (x2max - x2min) / 100;  % Puedes ajustar el valor según sea necesario

    [x1Plano, x2Plano] = meshgrid(x1min:paso1:x1max,x2min:paso2:x2max);

    plot(x1Plano, x2Plano);

    xlim([x_min, x_max]);
    ylim([y_min, y_max]);
    xlabel('X1');
    ylabel('X2');
    %grid on;
    %hold off;

elseif length(coef_d12) == 4

    a = coef_d12(1);
    b = coef_d12(2);
    c = coef_d12(3);
    d = coef_d12(4);

    x1min = min(X(:, 1));
    x1max = max(X(:, 1));
    x2min = min(X(:, 2));
    x2max = max(X(:, 2));

    paso1 = (x1max - x1min) / 100;  % Puedes ajustar el valor según sea necesario
    paso2 = (x2max - x2min) / 100;  % Puedes ajustar el valor según sea necesario

    [x1Plano, x2Plano] = meshgrid(x1min:paso1:x1max,x2min:paso2:x2max);

    x3Plano = -(a*x1Plano + b*x2Plano + d) / (c + eps);  % eps se utiliza para evitar división por cero

    surf(x1Plano, x2Plano, x3Plano);

    xlabel('x1');
    ylabel('x2');
    zlabel('x3');
    %grid on;
    %hold off;

end

end 