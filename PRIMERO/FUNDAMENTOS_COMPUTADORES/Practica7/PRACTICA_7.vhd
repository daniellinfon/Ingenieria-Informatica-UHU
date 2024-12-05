----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:36:16 01/08/2022 
-- Design Name: 
-- Module Name:    PRACTICA_7 - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity PRACTICA_7 is
    Port ( E : in  STD_LOGIC;
           S : out  STD_LOGIC;
           CLK : in  STD_LOGIC;
			  Q : out STD_LOGIC_VECTOR (1 downto 0);
           RS : in  STD_LOGIC);
end PRACTICA_7;

architecture Behavioral of PRACTICA_7 is
	subtype mis_estados is std_logic_vector(1 downto 0);
	constant estado_A : mis_estados := "00";
	constant estado_B : mis_estados := "01";
	constant estado_C : mis_estados := "10";
	constant estado_D : mis_estados := "11";
	signal estado : mis_estados;
begin

EVOLUCION: process(RS, CLK)

	begin
	if RS = '1' then estado <= estado_A;
	elsif rising_edge (CLK) then
	case estado is
	
		when estado_A => if E = '1' then estado <= estado_B; end if;
		when estado_B => if E = '1' then estado <= estado_C; else estado <= estado_A; end if;
		when estado_C => if E = '0' then estado <= estado_D; end if;
		when estado_D => if E = '0' then estado <= estado_A; else estado <= estado_B; end if;
		when others =>
		end case;
	end if;
	end process;
	Q <= estado;
	
	SALIDAS: process (estado)
			begin
				case estado is
					when estado_A => S <= '0';
					when estado_B => S <= '0';
					when estado_C => S <= '0';
					when estado_D => S <= '1';
					when others => S <= '0';
				end case;
			end process;

					
		
end Behavioral;

