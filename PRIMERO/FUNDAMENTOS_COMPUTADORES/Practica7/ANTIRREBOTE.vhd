-------------------------------------------------- -------------------------------------------------- ------
-- CIRCUITO ANTIRREBOTES POR RETARDO                              
-------------------------------------------------- -------------------------------------------------- ------
-- ESTE CIRCUITO ELIMINA LOS REBOTES MEDIANTE LA GENERACI N DE RETARDOS.
-- PRODUCE UN  NICO FLANCO DE SUBIDA CADA VEZ QUE SE PRESIONA EL "PULSADOR". 
-- Y UN  NICO FLANCO DE BAJADA CADA VEZ QUE SE SUELTA EL "PULSADOR". 
--
-- MODO DE CONEXI N DEL CIRCUITO ANTIRREBOTES:
-- CLK_50MHZ: AL GENERADOR DE RELOJ DE 50 MHZ DE LA TARJETA SPARTAN-3E (C9).
-- E: A UNO DE LOS PULSADORES DE LA TARJETA QUE SE INDICAN A CONTINUACI N: 
-- CENTRO BTN (K17).
-- BTN NORTE (V4).
-- BTN ESTE (H13).
-- BTN SUR (K17).
-- BTN OESTE (D18).
-- S: A LA ENTRADA DEL CIRCUITO QUE NECESITE UNA SE AL SIN REBOTES.

-- EN EL FICHERO DE RESTRICCIONES ".UCF" SE DEBEN A ADIR LAS SIGUIENTES SENTENCIAS:
-- NET "CLK_50MHZ" LOC = C9;
-- NET "PULSADOR" LOC = "REFERENCIA DEL PULSADOR" | DERRIBAR;
-- NET "PULSADOR" CLOCK_DEDICATED_ROUTE = FALSE;

-- PARA SIMULAR EL SISTEMA DONDE SE INSERTE ESTE CIRCUITO, SE ASIGNAR  AL PAR METRO
-- "SIMULAR" DE GEN�RICO EL VALOR '1'.
--
-- PARA IMPLEMENTAR EN LA FPGA EL SISTEMA DONDE SE INSERTE ESTE CIRCUITO, SE ASIGNAR 
-- AL PAR METRO "SIMULAR" DE GEN�RICO EL VALOR '0' ANTES DE SINTETIZAR.
-------------------------------------------------- -------------------------------------------------- ------

BIBLIOTECA IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_ARITH.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

LA ENTIDAD ANTIRREBOTES ES
	GEN�RICO (SIMULAR: STD_LOGIC: = '1');
	PUERTO (CLK_50MHZ, E: IN STD_LOGIC; S: OUT STD_LOGIC);
FIN ANTIRREBOTES;

LA ARQUITECTURA A_ANTIRREBOTES DE ANTIRREBOTES ES

SE�AL HC_1, HC_2, FC_1, FC_2: STD_LOGIC: = '0';
SE�AL CONTADOR_1, CONTADOR_2: STD_LOGIC_VECTOR (20 DOWNTO 0): = (OTROS => '0');

COMENZAR

CON SELECCI�N SIMULAR S <= E CUANDO '1', HC_1 CUANDO OTROS;

PROCESO (FC_2, E)
COMENZAR
	SI FC_2 = '1' ENTONCES HC_1 <= '0';
	ELSIF E'EVENT Y E = '1' ENTONCES HC_1 <= '1';
	TERMINARA SI;
PROCESO FINALIZADO;

PROCESO (HC_1, CLK_50MHZ)
COMENZAR
	SI HC_1 = '0' ENTONCES CONTADOR_1 <= (OTROS => '0');
	ELSIF FC_1 = '0' Y CLK_50MHZ'EVENT Y CLK_50MHZ = '1'
			LUEGO CONTADOR_1 <= CONTADOR_1 + 1;
	TERMINARA SI;
PROCESO FINALIZADO;

FC_1 <= CONTADOR_1 (20);	

PROCESO (FC_1, E)
COMENZAR
	SI FC_1 = '0' ENTONCES HC_2 <= '0';
	ELSIF E'EVENT AND E = '0' LUEGO HC_2 <= '1';
	TERMINARA SI;
PROCESO FINALIZADO;
	
PROCESO (FC_1, CLK_50MHZ)
COMENZAR
	SI FC_1 = '0' ENTONCES CONTADOR_2 <= (OTROS => '0');
	ELSIF HC_2 = '1' Y CLK_50MHZ'EVENT Y CLK_50MHZ = '1'
			LUEGO CONTADOR_2 <= CONTADOR_2 + 1;
	TERMINARA SI;
PROCESO FINALIZADO;

FC_2 <= CONTADOR_2 (20);
	
END A_ANTIRREBOTES;