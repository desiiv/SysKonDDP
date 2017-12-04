package solution_4;

import exercise_4.AbstractSemaphore;

import java.util.concurrent.atomic.AtomicInteger;

public class NFSemaphore extends AbstractSemaphore {
  AtomicInteger ai = new AtomicInteger(0);


  @Override
  public void semWait() {
    while (ai.getAndIncrement() > 0) {
      //waiting
    }

  }

  @Override
  public void semSignal() {
    ai.lazySet(0);
  }
}
