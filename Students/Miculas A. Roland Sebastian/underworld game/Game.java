package underworld.main;

import java.util.Scanner;
import underworld.utils.*;
import underworld.models.*;

public class Game {
	private static int minerNextAction;
	private static int valuableResources = 0;
	private static boolean minerSlept = false;
	private static Scanner scanner = new Scanner(System.in); //done
	// TODO: Initialize the value of scanner so that you can
	//       use it to read from keyboard
	
	public static void main(String[] args) throws InterruptedException {
		if (Constants.MINER_BACKPACK_SIZE > Constants.MAP_RESOURCES_NUMBER) {
			System.out.println("[Game] You bastard! Your miner can carry more than the map can offer!");
			return;
		}
		System.out.println("[Game] Welcome to " + Constants.WORLD_NAME + "!");
		FileReader.printFileContent("rules.txt");
		// done
		// TODO: Print rules by calling method on FileReader utility class
		// 		 Use Constants class to get the path to file
		
		System.out.print("[Game] Please enter the name of our miner: ");
		String minerName = scanner.nextLine();
		
		//done
		// TODO: Read miner name and place it into a variable.. Look around for the variable name
		System.out.println("[Game] Miner '" + minerName + "' is getting ready to mine!");
		Thread.sleep(500);
		
		Miner miner = new Miner(minerName);
		//done
		// TODO: Create a new miner with the name you have just read
		System.out.println("[Game] Miner '" + minerName + "': Show me where to dig!");
		Thread.sleep(1000);
		
		Map map = new Map();
		//done
		// TODO: Create a new map and generate the resources
		
		System.out.println("[Game] You have " + Constants.NUMBER_OF_ROUNDS + " rounds to find as many valuable resources as possible! Good luck!");
		
		for (int i = 0; i < Constants.NUMBER_OF_ROUNDS; i++) {
			
			Thread.sleep(1000);
			System.out.println("[Game] ###");
			System.out.println("[Game] #Round number: " + (i + 1));
			System.out.println("[Game] ###");
			
			Thread.sleep(1000);
			map.ShowResourses();
			//done
			// TODO: Display the resources from the map
			
			Thread.sleep(1000);
			System.out.println("[Game] What should " + miner.getName() + " do next? Dig(1) or sleep(0)?");
			System.out.print("[Game] ");
			
			System.out.println("MinerNextAction : ");
			minerNextAction = scanner.nextInt();
			//done
			// TODO: Read an integer from the keyboard and update 
			//       the minerNextAction variable accordingly

			int resource;
			switch (minerNextAction) {
				case 0:
					i--;
					minerSlept = true;
					System.out.println("[Game] PSST! Our miner is sleeping..");
					Thread.sleep(2000);
					resource = miner.sleep();
					//System.out.println("Valoarea random :" + resource);
					//done
					// TODO: Make the miner sleep and update the resource variable with the
					// 	     value returned by the sleep method
					break;
				case 1:
					minerSlept = false;
					System.out.println("[Game] GOGOGO! Our miner is digging..");
					Thread.sleep(2000);
					resource = miner.dig();
					//System.out.println("Valoarea random :" + resource);
					//done
					// TODO: Make the miner dig and update the resource variable with the
					// 	     value returned by the sleep method
					break;
				default:
					resource = 0;
					break;
			}
			
			Thread.sleep(1000);
			
			if (map.consumeResource(resource)) {
				if (minerSlept) {
					System.out.println("[Game] BOO! A valuable resource decayed because you slept!");
				}
				else {
					valuableResources ++;
					System.out.println("[Game] JACKPOT! Valuable resource found!");
				}
			}
			else {
				if (minerSlept) {
					System.out.println("[Game] PHEW! You almost dug for a useless resource!");	
				}
				else {
					System.out.println("[Game] BADLUCK! Try sleeping next time!");	
				}			
			}
			
		}
		
		System.out.println("[Game] ####################################");
		System.out.println("[Game] GAME OVER! Your miner found " + valuableResources + "/" + Constants.MAP_RESOURCES_NUMBER + " valuable resources");

		scanner.close(); 
		
		return;
	}
}
