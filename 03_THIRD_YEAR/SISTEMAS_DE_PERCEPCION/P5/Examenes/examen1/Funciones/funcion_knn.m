function YTest = funcion_knn (XTest, XTrain, YTrain, k)

valoresClase = unique(YTrain);
%numClases = length(valoresClase);
nTrain = size(XTrain,1);
nTest = size(XTest,1);
YTest = zeros(nTest,1);
NP = XTrain';
    
    for i=1:nTest
        % PASO 1
        P = XTest(i,:)';
        P_amp = repmat(P,1,nTrain);
        dist = sqrt(sum((P_amp - NP).^2));

        % PASO 2
        [dOrd, ind] = sort(dist);
        indK = ind(1:k); % k: número de "vecinos" más cercanos considerado
        
        % PASO 3
        clasesK = YTrain(indK);
        valoresClasesK = unique(clasesK);
        numClasesK = length(valoresClasesK);

        % PASO 4
        if numClasesK == 1
            YTest(i) = valoresClasesK;
        else
            conteoClasesK = zeros(1,numClasesK);
            for j=1:numClasesK
                valoresClasesK_j = valoresClasesK(j);
                conteoClasesK(j) = sum(clasesK == valoresClasesK_j);

            end
            posClasesKNN = find(conteoClasesK == max(conteoClasesK));
            
            % PASO 5
            if length(posClasesKNN)==1
                YTest(i) = valoresClasesK(posClasesKNN);
            else
                for x=1:k
                    clasesK_w = clasesK(x);
                    clasesKNN = valoresClasesK(posClasesKNN);
                    if ismember(clasesK_w, clasesKNN)
                        break;
                    end
                end
                YTest(i) = clasesK_w;
            end
        end

    end

end