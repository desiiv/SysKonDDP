package main_4;

import solution_4.NFSemaphore;
import java.util.concurrent.ThreadLocalRandom;

public class SemaphoreTest implements Runnable {
	
	private static int sharedInt = 0;
	private static NFSemaphore sem = new NFSemaphore();
	
	/*
	 * Test der Semaphore mittels Zähler auf einer static Variable.
	 */
	public void run() {
		while (sharedInt < 40) {
			
			sem.semWait();
			
			if (sharedInt < 40) {
				
				System.out.println(sharedInt);
				sharedInt++;
	
				int i1 = sharedInt;
	
				// mindestens zwei Million Operationen auf der geteilten Variable.
				int numberOfRandomOps = ThreadLocalRandom.current().nextInt(1000000,100000000);
				for(int i=0; i<numberOfRandomOps; i++) {
					sharedInt=sharedInt + 100;
				}
				for(int i=0; i<numberOfRandomOps; i++) {
					sharedInt = sharedInt - 100;
				}
				
				if (sharedInt != i1) {
					System.out.println("ERROR!  Semaphore funktioniert nicht!");
				}
			}
			sem.semSignal();
		}
	}

	
	public static void main(String[] args) throws InterruptedException {
		final SemaphoreTest semTest = new SemaphoreTest();
		
		Thread t1 = new Thread(semTest);
		Thread t2 = new Thread(semTest);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("Final Value: " + sharedInt);
		
	}

}
