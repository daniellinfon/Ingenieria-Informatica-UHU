package si2024.daniellinfonyealu.p01;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_01_exe {

	public static void main(String[] args) throws IOException {

		String p0 = "si2024.daniellinfonyealu.p01.Practica_01_Agente";

		// Load available games
		String spGamesCollection = "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);

		// Game settings
		boolean visuals = false;
		int seed = new Random().nextInt();

		// Game and level to play
		int gameIdx = 18;
		int levelIdx = 0; // level names from 0 to 4 (game_lvlN.txt).

		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);

		// 1. This starts a game, in a level, played by a human.
		// ArcadeMachine.playOneGame(game, level1, null, seed);

		File f = new File("ScoreGP");
		if (f.exists()) {
			f.delete();
		}

		f = new File("WinrateGP");
		if (f.exists()) {
			f.delete();
		}

		FileWriter Fscore = new FileWriter("ScoreGP");
		FileWriter Fwin = new FileWriter("WinrateGP");

		PrintWriter outScore = new PrintWriter(Fscore);
		PrintWriter outWin = new PrintWriter(Fwin);

		// 2. This plays a game in a level by the controller.
		double[] array;
		int win = 0;
		double score = 0;
		int intentos = 1;
		for (int j = 0; j < 5; j++) {
			levelIdx = j;
			level1 = game.replace(gameName, gameName + "_lvl" + levelIdx);
			for (int i = 0; i < intentos; i++) {
				array = ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);
				seed = new Random().nextInt();
				if (array[0] == 1)
					win++;
				score += array[1];
			}
			outScore.println(levelIdx + "\t" + score/intentos);
			outWin.println(levelIdx + "\t" + win);
			score = 0;
			win = 0;
		}
		
		outScore.close();
		outWin.close();

		File comparacionScore = new File("comparacionScore.gp");
		try {// If the file already exists, delete it..
			comparacionScore.delete();
		} catch (Exception e) {}

		FileWriter out1 = new FileWriter("comparacionScore.gp");
		PrintWriter outSc = new PrintWriter(out1);

		outSc.print("set title " + "\"" + "MEDIA DE PUNTOS POR NIVEL" + "\"" + "\n");
		outSc.println("set grid"); // Agregar cuadrícula al gráfico
		outSc.print("set xlabel " + "\"Niveles\"" + "\n");
		outSc.print("set ylabel " + "\"Score\"" + "\n");
		outSc.println("set xrange [0:4]");
		outSc.println("set xtics 0,1,4");
		outSc.println("set yrange [0:20]");
		outSc.println("set ytics 0,1,20");
		outSc.println("plot \"ScoreGP\" with linespoints linecolor \"red\"");

		outSc.close();// It's done, closing document.
		
		Runtime.getRuntime().exec("graficas/gnuplot -persist comparacionScore.gp");
		
		Scanner input = new Scanner(System.in);
        System.out.println("Presione una tecla para continuar...");
        input.next();
        
		File comparacionWin = new File("comparacionWin.gp");
		try {// If the file already exists, delete it..
			comparacionScore.delete();
		} catch (Exception e) {}

		FileWriter out2= new FileWriter("comparacionWin.gp");
		PrintWriter outW = new PrintWriter(out2);

		outW.print("set title " + "\"" + "Winrate" + "\"" + "\n");
		outW.println("set grid"); // Agregar cuadrícula al gráfico
		outW.print("set xlabel " + "\"Niveles\"" + "\n");
		outW.print("set ylabel " + "\"Victorias (%)\"" + "\n");
		outW.println("set xrange [0:4]");
		outW.println("set xtics 0,1,4");
		outW.println("set yrange [0:100]");
		outW.println("set ytics 0,5,100");
		outW.println("plot \"WinrateGP\" with linespoints linecolor \"blue\"");

		outW.close();// It's done, closing document.
		
		Runtime.getRuntime().exec("graficas/gnuplot -persist comparacionWin.gp");
		

	

		// ArcadeMachine.runOneGame(game, level1, visuals, p0, null, seed, 0);

		System.exit(0);

	}

	/*
	 * public static double test_level(int lvl) {
	 * 
	 * String p0 = "si2024.daniellinfonyealu.p01.Practica_01_Agente"; //Load
	 * available games String spGamesCollection = "examples/all_games_sp.csv";
	 * String[][] games = Utils.readGames(spGamesCollection); int gameIdx = 18;
	 * String gameName = games[gameIdx][1]; String game = games[gameIdx][0]; String
	 * level1 = game.replace(gameName, gameName + "_lvl" + lvl); Random rng = new
	 * Random(); // ArcadeMachine.playOneGame(game, level1, null, 33);
	 * 
	 * int ganadas = 0; int score = 0; for (int i = 0; i < 1; i++) { // var seed =
	 * -1168181290; int seed = rng.nextInt(); double[] cosos =
	 * ArcadeMachine.runOneGame(game, level1, false, p0, null, seed, 0); if
	 * (cosos[0] != 0.0) { score += (int) cosos[1]; ganadas++; } else { } }
	 * System.out.println("WR en el nivel " + lvl + ": " + ganadas + "%");
	 * System.out.println("Sc en el nivel " + lvl + ": " + score / 1); return score;
	 * }
	 * 
	 * public static void main(String[] args) { int total_points = 0; for (int i =
	 * 0; i <5; i++) { total_points += (int) test_level(i);
	 * System.out.println("\n"); } System.out.println("Media: " + total_points);
	 * System.exit(0);
	 * 
	 * }
	 */
}
