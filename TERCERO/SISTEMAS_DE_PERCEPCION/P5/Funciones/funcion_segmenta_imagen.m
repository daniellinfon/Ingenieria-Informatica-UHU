function [Ietiq, N] = funcion_segmenta_imagen(I)

    %1.1
    Ibin = I < 255*graythresh(I);
    %imshow(Ibin);
    
    %1.2
    IbinFilt = funcion_elimina_regiones_ruidosas(Ibin);
    
    %1.3
    [Ietiq, N] = bwlabel(IbinFilt);

end