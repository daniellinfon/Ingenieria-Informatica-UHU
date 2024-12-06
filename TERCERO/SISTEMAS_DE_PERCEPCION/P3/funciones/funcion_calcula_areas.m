function areas = funcion_calcula_areas (IEtiq, N)

    areas = zeros(N, 1);
    for i=1:N
        Io = IEtiq == i;
        areas(i) = sum(Io(:));
    end
end