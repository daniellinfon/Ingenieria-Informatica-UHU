function IbinF = funcion_elimina_regiones_ruidosas(Ibin);
% COMPONENTE RUIDOSA:
% COMPONENTES DE MENOS DEL 0.1% DEL NÚMERO TOTAL DE PÍXELES DE LA IMAGEN
% O NÚMERO DE PÍXELES MENOR AL AREA DEL OBJETO MAYOR /5
% SE DEBE CUMPLIR CUALQUIERA DE LAS DOS CONDICIONES

    porc = 0.1;
    
    [N, M] = size(Ibin);
    numPixIm = N*M;
    
    %length(Ibin(:));
    
    numPixMin =  round((porc/100)*numPixIm);
    IbinF = bwareaopen(Ibin, numPixMin);
    
    [Ietiq,N] = bwlabel(IbinF);
    
    if N>0
        stats = regionprops(Ietiq,'Area');
        areas = cat(1,stats.Area);
        numPixMin = round(max(areas)/5);
    
        IbinF  = bwareaopen(IbinF, numPixMin);
    
    end

end