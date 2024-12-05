----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:42:19 12/08/2021 
-- Design Name: 
-- Module Name:    FC_P5 - Behavioral 
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
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity FC_P5 is
    Port ( D3 : in  STD_LOGIC;
           D2 : in  STD_LOGIC;
           D1 : in  STD_LOGIC;
           D0 : in  STD_LOGIC;
           R : out  STD_LOGIC;
           T : out  STD_LOGIC;
           C : out  STD_LOGIC;
           P : out  STD_LOGIC);
end FC_P5;

architecture Behavioral of FC_P5 is
   

	Signal entrada :  STD_LOGIC_VECTOR(3 downto 0);
    
	 
	Signal entrada_int :  INTEGER range 0 to 15;
	  
begin
    entrada <= D3&D2&D1&D0;
	 R <= '1' when entrada = "0100" else
          '1' when entrada = "0101" else
          '1' when entrada = "0110" else
          '1' when entrada = "0111" else
			 '1' when entrada = "1000" else
			 '1' when entrada = "1001" else
			 '1' when entrada = "1010" else
          '0';
   
	entrada_int <= CONV_INTEGER(entrada);
	 
	With entrada_int select
    T <= '1' When 3|6|9|12|15,
          '0' When others;
	 process(entrada_int,D3,D2,D1,D0)
    begin
        if entrada_int = 5 then C <= '1';
        elsif entrada_int = 10 then C <= '1';
        elsif entrada_int = 15 then C <= '1';
        else C <= '0'; 
        end if;

    end process;
	 process(entrada,D3,D2,D1,D0)
    begin 
    case entrada is
         when "0011" => P <= '1';
         when "0101" => P <= '1';
			when "0110" => P <= '1';
			when "1001" => P <= '1';
			when "1010" => P <= '1';
         when "1100" => P <= '1';
         when "1111" => P <= '1';
         when others => P <= '0';
         end case;
    end process;
	 
end Behavioral;

