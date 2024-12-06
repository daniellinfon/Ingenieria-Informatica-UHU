function IbinFilt = funcion_elimina_regiones_ruidosas(Ibin)
porc = 0.1;

[N,M] = size(Ibin);
numPixIm = N*M;

length(Ibin(:));

numPixMinimo = round((porc/100)*numPixIm);

IbinFilt = bwareaopen(Ibin, numPixMinimo);

[Ietiq, N] = bwlabel(IbinFilt);

if N>0
    
    stats = regionprops(Ietiq, 'Area');
    areas = cat(1, stats.Area);
    numPixMinimo = round(max(areas)/5);

    IbinFilt = bwareaopen(IbinFilt,numPixMinimo);

end

