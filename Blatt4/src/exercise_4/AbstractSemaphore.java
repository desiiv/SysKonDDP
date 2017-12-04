package exercise_4;

abstract public class AbstractSemaphore {
	
	/**
	 * wait Methode der Semaphore.
	 */
	abstract public void semWait();
	
	
	/**
	 * signal Methode der Semaphore.
	 */
	abstract public void semSignal();
	
}
