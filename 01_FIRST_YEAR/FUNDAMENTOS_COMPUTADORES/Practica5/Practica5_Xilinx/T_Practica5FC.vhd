--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   17:47:15 12/08/2021
-- Design Name:   
-- Module Name:   C:/Users/Usuario/OneDrive/Escritorio/Practicas FC/Practica5FC/T_Practica5FC.vhd
-- Project Name:  Practica5FC
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: FC_P5
-- 
-- Dependencies:
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
--
-- Notes: 
-- This testbench has been automatically generated using types std_logic and
-- std_logic_vector for the ports of the unit under test.  Xilinx recommends
-- that these types always be used for the top-level I/O of a design in order
-- to guarantee that the testbench will bind correctly to the post-implementation 
-- simulation model.
--------------------------------------------------------------------------------
LIBRARY ieee;
use ieee.std_logic_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
-- USE ieee.numeric_std.ALL;
 
ENTITY T_Practica5FC IS
END T_Practica5FC;
 
ARCHITECTURE behavior OF T_Practica5FC IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT FC_P5
    PORT(
         D3 : IN  std_logic;
         D2 : IN  std_logic;
         D1 : IN  std_logic;
         D0 : IN  std_logic;
         R : OUT  std_logic;
         T : OUT  std_logic;
         C : OUT  std_logic;
         P : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal D3 : std_logic := '0';
   signal D2 : std_logic := '0';
   signal D1 : std_logic := '0';
   signal D0 : std_logic := '0';

 	--Outputs
   signal R : std_logic;
   signal T : std_logic;
   signal C : std_logic;
   signal P : std_logic;
	
		Signal entrada :  STD_LOGIC_VECTOR(3 downto 0);
	Signal entrada_int :  INTEGER range 0 to 15;
 
BEGIN
	entrada <= D3&D2&D1&D0;
	entrada_int <= CONV_INTEGER(entrada);
	-- Instantiate the Unit Under Test (UUT)
   uut: FC_P5 PORT MAP (
          D0 => D0,
          D1 => D1,
          D2 => D2,
          D3 => D3,
          R => R,
          T => T,
          C => C,
			 P => P
        );


   -- Stimulus process
   stim_proc: process
	
   begin		
		D3 <= '0';D2 <= '0';D1 <= '0';D0 <= '0';
		Wait for 10 ns;
		D3 <= '0';D2 <= '0';D1 <= '0';D0 <= '1';
		Wait for 10 ns;
		D3 <= '0';D2 <= '0';D1 <= '1';D0 <= '0';
		Wait for 10 ns;
		D3 <= '0';D2 <= '0';D1 <= '1';D0 <= '1';
		Wait for 10 ns;
		D3 <= '0';D2 <= '1';D1 <= '0';D0 <= '0';
		Wait for 10 ns;
		D3 <= '0';D2 <= '1';D1 <= '0';D0 <= '1';
		Wait for 10 ns;
		D3 <= '0';D2 <= '1';D1 <= '1';D0 <= '0';
		Wait for 10 ns;
		D3 <= '0';D2 <= '1';D1 <= '1';D0 <= '1';
		Wait for 10 ns;
		D3 <= '1';D2 <= '0';D1 <= '0';D0 <= '0';
		Wait for 10 ns;
		D3 <= '1';D2 <= '0';D1 <= '0';D0 <= '1';
		Wait for 10 ns;
		D3 <= '1';D2 <= '0';D1 <= '1';D0 <= '0';
		Wait for 10 ns;
		D3 <= '1';D2 <= '0';D1 <= '1';D0 <= '1';
		Wait for 10 ns;
		D3 <= '1';D2 <= '1';D1 <= '0';D0 <= '0';
		Wait for 10 ns;
		D3 <= '1';D2 <= '1';D1 <= '0';D0 <= '1';
		Wait for 10 ns;
		D3 <= '1';D2 <= '1';D1 <= '1';D0 <= '0';
		Wait for 10 ns;
		D3 <= '1';D2 <= '1';D1 <= '1';D0 <= '1';
		Wait for 10 ns;

      wait;
   end process;

   -- No clocks detected in port list. Replace <clock> below with 
   -- appropriate port name 
 
   --constant <clock>_period : time := 10 ns;
 
--BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
  -- uut: FC_P5 PORT MAP (
    --      D3 => D3,
    --      D2 => D2,
    --      D1 => D1,
    --      D0 => D0,
    --     R => R,
    --      T => T,
    --      C => C,
    --      P => P
    --    );

   -- Clock process definitions
  -- <clock>_process :process
  -- begin
	--	<clock> <= '0';
	--	wait for <clock>_period/2;
	--	<clock> <= '1';
	--	wait for <clock>_period/2;
   --end process;
 

   -- Stimulus process
   --stim_proc: process
   --begin		
      -- hold reset state for 100 ns.
   --   wait for 100 ns;	

   --   wait for <clock>_period*10;

      -- insert stimulus here 

    --  wait;
 --  end process;

END;
