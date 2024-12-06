function h = funcion_histograma_v2(I)

    h = zeros(256,1)

    for nivel = 0:255        
         h(nivel + 1) = sum(sum(I == nivel));
    end
end