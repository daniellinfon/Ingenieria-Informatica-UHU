function [d1,d2, d12, coef_d12] = funcion_calcula_d1_d2_d12_LDA_2Clases_2_3_Dim (X,Y)

dim = size(X,2);

ValoresClases = unique(Y);
    
    XC1 = X(Y==ValoresClases(1),:);
    XC2 = X(Y==ValoresClases(2),:);
    
    M1 = mean(XC1)';
    M2 = mean(XC2)';
    
    mCov1 = cov(XC1);
    mCov2 = cov(XC2);
    
    N1 = size(XC1, 1);
    N2 = size(XC2, 1);
    numDatos = N1+N2;
    
    mCov = ((N1-1)*mCov1 + (N2-1)*mCov2)/(numDatos-2);


if dim == 2
    
    x1 = sym('x1', 'real');
    x2 = sym('x2', 'real');
    
    Xs = [x1; x2];
    
    d1 = expand(-(Xs-M1)' * pinv(mCov)*(Xs-M1)); vpa(d1,3);
    d2 = expand(-(Xs-M2)' * pinv(mCov)*(Xs-M2)); vpa(d2,3);
    
    d12 = d1 - d2; vpa(d12,3);

    x1 = 0; x2 = 0; C = eval(d12);
    x1 = 1; x2 = 0; A = eval(d12)-C;
    x1 = 0; x2 = 1; B = eval(d12)-C;

    coef_d12 = [A; B; C];

elseif dim == 3
    
    x1 = sym('x1', 'real');
    x2 = sym('x2', 'real');
    x3 = sym('x3', 'real');
   
    Xs = [x1; x2; x3];

    d1 = expand(-(Xs-M1)' * pinv(mCov)*(Xs-M1)); vpa(d1,3);
    d2 = expand(-(Xs-M2)' * pinv(mCov)*(Xs-M2)); vpa(d2,3);
    
    d12 = d1 - d2; vpa(d12,3);

    x1 = 0; x2 = 0; x3 = 0; D = eval(d12);
    x1 = 1; x2 = 0; x3 = 0; A = eval(d12)-D;
    x1 = 0; x2 = 1; x3 = 0; B = eval(d12)-D;
    x1 = 0; x2 = 0; x3 = 1; C = eval(d12)-D;

    coef_d12 = [A; B; C; D];

end

    fimplicit3(d12);

end