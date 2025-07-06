--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:04:16 01/08/2022
-- Design Name:   
-- Module Name:   C:/Users/alber/Documentos/P7_FC/PRACTICA_7/TEST_PRACTICA_7.vhd
-- Project Name:  PRACTICA_7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: PRACTICA_7
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
USE ieee.std_logic_1164.ALL;
 
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--USE ieee.numeric_std.ALL;
 
ENTITY TEST_PRACTICA_7 IS
END TEST_PRACTICA_7;
 
ARCHITECTURE behavior OF TEST_PRACTICA_7 IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT PRACTICA_7
    PORT(
         E : IN  std_logic;
         S : OUT  std_logic;
         CLK : IN  std_logic;
         RS : IN  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal E : std_logic := '0';
   signal CLK : std_logic := '0';
   signal RS : std_logic := '0';

 	--Outputs
   signal S : std_logic;

   -- Clock period definitions
   constant CLK_period : time := 10 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: PRACTICA_7 PORT MAP (
          E => E,
          S => S,
          CLK => CLK,
          RS => RS
        );

   -- Clock process definitions
   CLK_process :process
   begin
		CLK <= '0';
		wait for CLK_period/2;
		CLK <= '1';
		wait for CLK_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      -- hold reset state for 100 ns.
      wait for 100 ns;	

      wait for CLK_period*10;

      -- insert stimulus here 

      wait;
   end process;

END;
