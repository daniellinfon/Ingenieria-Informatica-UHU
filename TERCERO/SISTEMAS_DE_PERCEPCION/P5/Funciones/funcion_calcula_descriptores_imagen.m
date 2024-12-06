function  XImagen = funcion_calcula_descriptores_imagen(Ietiq,N)

    XImagen = [];

    for i=1:N

        Ib_etiq = Ietiq == i;
        
        stats = regionprops(Ib_etiq, 'Circularity', 'Eccentricity', 'Solidity','Extent', 'EulerNumber' );
        Circ = cat(1, stats.Circularity);
        Ecc = cat(1, stats.Eccentricity);
        Sol = cat(1, stats.Solidity);
        Ext = cat(1, stats.Extent);

        ExtentInv = Funcion_Calcula_Extent_Inv_Rot(Ib_etiq);

        Hu = Funcion_Calcula_Hu(Ib_etiq);
        Hu = Hu';

        Fd = Funcion_Calcula_DF(Ib_etiq, 10);
        Fd = Fd';

        Euler = cat(1, stats.EulerNumber);

        XImagen = [XImagen; Circ, Ecc, Sol, Ext, ExtentInv, Hu, Fd, Euler];

    end

end