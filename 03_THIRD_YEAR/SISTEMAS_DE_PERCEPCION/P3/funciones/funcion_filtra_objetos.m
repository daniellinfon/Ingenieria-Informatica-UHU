function IbFilt = funcion_filtra_objetos (Ib , numPix)

    IbFilt = zeros(size(Ib));
    Ib=logical(Ib);
    [Ietiq,N] = funcion_etiquetar(Ib);
    areas = funcion_calcula_areas(Ietiq,N);

    for i=1:N
        if(areas(i) >= numPix)
            IbFilt = IbFilt+(Ietiq==i);
        end
    end
end

%Otra forma

% IbFilt = Ib;
% posEliminar = find(areas<numPix);
% for i=1:N
%        etiqOI = posElimniar(i);
%        IbFilt(Ietiq==etiqOI)=0;
% end

% bwareaopen(ib,numoixel)