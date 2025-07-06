--------------------------------------------------------------------------------
-- Company: 
-- Engineer:
--
-- Create Date:   21:04:39 01/09/2022
-- Design Name:   
-- Module Name:   C:/Users/alber/Documentos/P7_FC/PRACTICA_7/TEST_SISTEMACOMPLETO.vhd
-- Project Name:  PRACTICA_7
-- Target Device:  
-- Tool versions:  
-- Description:   
-- 
-- VHDL Test Bench Created by ISE for module: SISTEMACOMPLETO
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
 
ENTITY TEST_SISTEMACOMPLETO IS
END TEST_SISTEMACOMPLETO;
 
ARCHITECTURE behavior OF TEST_SISTEMACOMPLETO IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT SISTEMACOMPLETO
    PORT(
         RESET : IN  std_logic;
         CLK_50MHZ : IN  std_logic;
         CLK_CR : IN  std_logic;
         E : IN  std_logic;
         Q : OUT  std_logic_vector(1 downto 0);
         S : OUT  std_logic
        );
    END COMPONENT;
    

   --Inputs
   signal RESET : std_logic := '0';
   signal CLK_50MHZ : std_logic := '0';
   signal CLK_CR : std_logic := '0';
   signal E : std_logic := '0';

 	--Outputs
   signal Q : std_logic_vector(1 downto 0);
   signal S : std_logic;

   -- Clock period definitions
   constant CLK_50MHZ_period : time := 20 ns;
   constant CLK_CR_period : time := 20 ns;
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: SISTEMACOMPLETO PORT MAP (
          RESET => RESET,
          CLK_50MHZ => CLK_50MHZ,
          CLK_CR => CLK_CR,
          E => E,
          Q => Q,
          S => S
        );
	
	-- RESET
	RESET_PROCESS : PROCESS
	BEGIN
		RESET <= '1';
		WAIT FOR CLK_50MHZ_period;
		RESET <= '0';
		WAIT FOR CLK_50MHZ_period*20;
		RESET <= '1';
		WAIT FOR CLK_50MHZ_period;
	END PROCESS;

   -- Clock process definitions
   CLK_50MHZ_process :process
   begin
		CLK_50MHZ <= '0';
		wait for CLK_50MHZ_period/2;
		CLK_50MHZ <= '1';
		wait for CLK_50MHZ_period/2;
   end process;
 
   CLK_CR_process :process
   begin
		CLK_CR <= '0';
		wait for CLK_CR_period/2;
		CLK_CR <= '1';
		wait for CLK_CR_period/2;
   end process;
 

   -- Stimulus process
   stim_proc: process
   begin		
      E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '1'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
		E <= '0'; WAIT FOR CLK_50MHZ_period;
   end process;

END;
