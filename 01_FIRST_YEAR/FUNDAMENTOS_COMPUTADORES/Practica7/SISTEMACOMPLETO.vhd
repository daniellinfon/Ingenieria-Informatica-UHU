----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:45:45 01/09/2022 
-- Design Name: 
-- Module Name:    SISTEMACOMPLETO - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity SISTEMACOMPLETO is
	Port( RESET: in STD_LOGIC;
			CLK_50MHZ : in STD_LOGIC;
			CLK_CR : in STD_LOGIC;
			E : in STD_LOGIC;
			Q : out STD_LOGIC_VECTOR(1 downto 0);
			S : out STD_LOGIC);
end SISTEMACOMPLETO;

architecture Behavioral of SISTEMACOMPLETO is

component ANTIRREBOTE is
	GENERIC(SIMULAR: STD_LOGIC :='1');
	PORT (CLK_50MHZ, E: in STD_LOGIC; S: OUT STD_LOGIC);
END component;


component PRACTICA_7 is
	Port ( E : in  STD_LOGIC;
           S : out  STD_LOGIC;
           CLK : in  STD_LOGIC;
			  Q : out STD_LOGIC_VECTOR (1 downto 0);
           RS : in  STD_LOGIC);
	end component;
	
SIGNAL CLK_SR: STD_LOGIC;

begin

C1: PRACTICA_7 PORT MAP (
			E => E,
			CLK => CLK_CR,
			RS => RESET,
			Q => Q,
			S=> S
		);	
		
end Behavioral;

