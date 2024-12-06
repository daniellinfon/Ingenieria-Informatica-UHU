package si2024.daniellinfonyealu.p04;

import java.util.Random;

import tools.Utils;
import tracks.ArcadeMachine;

public class Practica_04_exe {

	public static double test_level(int lvl) {

		String p0 = "si2024.daniellinfonyealu.p04.Practica_04_Agente";
		// Load available games
		String spGamesCollection = "examples/all_games_sp.csv";
		String[][] games = Utils.readGames(spGamesCollection);
		int gameIdx = 45;
		String gameName = games[gameIdx][1];
		String game = games[gameIdx][0];
		String level1 = game.replace(gameName, gameName + "_lvl" + lvl);
		Random rng = new Random();
		//ArcadeMachine.playOneGame(game, level1, null, 33);

		int ticks = 0;
		for (int i = 0; i < 1; i++) {
			int seed = rng.nextInt();
			double[] cosos = ArcadeMachine.runOneGame(game, level1, false, p0, null, seed, 0);
			if (cosos[0] != 0.0) {
				ticks += (int) cosos[2];
			} else {
			}
		}
		//System.out.println("WR en el nivel " + lvl + ": " + ganadas + "%");
		System.out.println("Tics en el nivel " + lvl + ": " + ticks / 1);
		return ticks;
	}

	public static void main(String[] args) {
		int total_points = 0;
		for (int i = 0; i < 5; i++) {
			total_points += (int) test_level(i);
			System.out.println("\n");
		}
		System.out.println("Media: " + total_points);
		System.exit(0);

	}
}
