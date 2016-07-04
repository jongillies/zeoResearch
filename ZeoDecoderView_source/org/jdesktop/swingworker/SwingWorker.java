package org.jdesktop.swingworker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public abstract class SwingWorker<T, V>
  implements Future<T>, Runnable
{
  private static final int MAX_WORKER_THREADS = 10;
  private volatile int progress;
  private volatile StateValue state;
  private final FutureTask<T> future;
  private final PropertyChangeSupport propertyChangeSupport;
  private AccumulativeRunnable<V> doProcess;
  private AccumulativeRunnable<Integer> doNotifyProgressChange;
  private static final AccumulativeRunnable<Runnable> doSubmit = new DoSubmitAccumulativeRunnable(null);
  private static ExecutorService executorService = null;
  
  public SwingWorker()
  {
    Callable local1 = new Callable()
    {
      public T call()
        throws Exception
      {
        SwingWorker.this.setState(SwingWorker.StateValue.STARTED);
        return (T)SwingWorker.this.doInBackground();
      }
    };
    this.future = new FutureTask(local1)
    {
      protected void done()
      {
        SwingWorker.this.doneEDT();
        SwingWorker.this.setState(SwingWorker.StateValue.DONE);
      }
    };
    this.state = StateValue.PENDING;
    this.propertyChangeSupport = new SwingWorkerPropertyChangeSupport(this);
    this.doProcess = null;
    this.doNotifyProgressChange = null;
  }
  
  protected abstract T doInBackground()
    throws Exception;
  
  public final void run()
  {
    this.future.run();
  }
  
  protected final void publish(V... paramVarArgs)
  {
    synchronized (this)
    {
      if (this.doProcess == null) {
        this.doProcess = new AccumulativeRunnable()
        {
          public void run(List<V> paramAnonymousList)
          {
            SwingWorker.this.process(paramAnonymousList);
          }
          
          protected void submit()
          {
            SwingWorker.doSubmit.add(new Runnable[] { this });
          }
        };
      }
    }
    this.doProcess.add(paramVarArgs);
  }
  
  protected void process(List<V> paramList) {}
  
  protected void done() {}
  
  protected final void setProgress(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 100)) {
      throw new IllegalArgumentException("the value should be from 0 to 100");
    }
    if (this.progress == paramInt) {
      return;
    }
    int i = this.progress;
    this.progress = paramInt;
    if (!getPropertyChangeSupport().hasListeners("progress")) {
      return;
    }
    synchronized (this)
    {
      if (this.doNotifyProgressChange == null) {
        this.doNotifyProgressChange = new AccumulativeRunnable()
        {
          public void run(List<Integer> paramAnonymousList)
          {
            SwingWorker.this.firePropertyChange("progress", paramAnonymousList.get(0), paramAnonymousList.get(paramAnonymousList.size() - 1));
          }
          
          protected void submit()
          {
            SwingWorker.doSubmit.add(new Runnable[] { this });
          }
        };
      }
    }
    this.doNotifyProgressChange.add(new Integer[] { Integer.valueOf(i), Integer.valueOf(paramInt) });
  }
  
  public final int getProgress()
  {
    return this.progress;
  }
  
  public final void execute()
  {
    getWorkersExecutorService().execute(this);
  }
  
  public final boolean cancel(boolean paramBoolean)
  {
    return this.future.cancel(paramBoolean);
  }
  
  public final boolean isCancelled()
  {
    return this.future.isCancelled();
  }
  
  public final boolean isDone()
  {
    return this.future.isDone();
  }
  
  public final T get()
    throws InterruptedException, ExecutionException
  {
    return (T)this.future.get();
  }
  
  public final T get(long paramLong, TimeUnit paramTimeUnit)
    throws InterruptedException, ExecutionException, TimeoutException
  {
    return (T)this.future.get(paramLong, paramTimeUnit);
  }
  
  public final void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    getPropertyChangeSupport().addPropertyChangeListener(paramPropertyChangeListener);
  }
  
  public final void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener)
  {
    getPropertyChangeSupport().removePropertyChangeListener(paramPropertyChangeListener);
  }
  
  public final void firePropertyChange(String paramString, Object paramObject1, Object paramObject2)
  {
    getPropertyChangeSupport().firePropertyChange(paramString, paramObject1, paramObject2);
  }
  
  public final PropertyChangeSupport getPropertyChangeSupport()
  {
    return this.propertyChangeSupport;
  }
  
  public final StateValue getState()
  {
    if (isDone()) {
      return StateValue.DONE;
    }
    return this.state;
  }
  
  private void setState(StateValue paramStateValue)
  {
    StateValue localStateValue = this.state;
    this.state = paramStateValue;
    firePropertyChange("state", localStateValue, paramStateValue);
  }
  
  private void doneEDT()
  {
    Runnable local5 = new Runnable()
    {
      public void run()
      {
        SwingWorker.this.done();
      }
    };
    if (SwingUtilities.isEventDispatchThread()) {
      local5.run();
    } else {
      doSubmit.add(new Runnable[] { local5 });
    }
  }
  
  private static synchronized ExecutorService getWorkersExecutorService()
  {
    if (executorService == null)
    {
      ThreadFactory local6 = new ThreadFactory()
      {
        final AtomicInteger threadNumber = new AtomicInteger(1);
        
        public Thread newThread(Runnable paramAnonymousRunnable)
        {
          StringBuilder localStringBuilder = new StringBuilder("SwingWorker-pool-");
          localStringBuilder.append(System.identityHashCode(this));
          localStringBuilder.append("-thread-");
          localStringBuilder.append(this.threadNumber.getAndIncrement());
          Thread localThread = new Thread(paramAnonymousRunnable, localStringBuilder.toString());
          if (localThread.isDaemon()) {
            localThread.setDaemon(false);
          }
          if (localThread.getPriority() != 5) {
            localThread.setPriority(5);
          }
          return localThread;
        }
      };
      executorService = new ThreadPoolExecutor(0, 10, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue(), local6)
      {
        private final ReentrantLock pauseLock = new ReentrantLock();
        private final Condition unpaused = this.pauseLock.newCondition();
        private boolean isPaused = false;
        private final ReentrantLock executeLock = new ReentrantLock();
        
        public void execute(Runnable paramAnonymousRunnable)
        {
          this.executeLock.lock();
          try
          {
            this.pauseLock.lock();
            try
            {
              this.isPaused = true;
            }
            finally
            {
              this.pauseLock.unlock();
            }
            setCorePoolSize(10);
            super.execute(paramAnonymousRunnable);
            setCorePoolSize(0);
            this.pauseLock.lock();
            try
            {
              this.isPaused = false;
              this.unpaused.signalAll();
            }
            finally
            {
              this.pauseLock.unlock();
            }
          }
          finally
          {
            this.executeLock.unlock();
          }
        }
        
        protected void afterExecute(Runnable paramAnonymousRunnable, Throwable paramAnonymousThrowable)
        {
          super.afterExecute(paramAnonymousRunnable, paramAnonymousThrowable);
          this.pauseLock.lock();
          try
          {
            while (this.isPaused) {
              this.unpaused.await();
            }
          }
          catch (InterruptedException localInterruptedException) {}finally
          {
            this.pauseLock.unlock();
          }
        }
      };
    }
    return executorService;
  }
  
  private class SwingWorkerPropertyChangeSupport
    extends PropertyChangeSupport
  {
    SwingWorkerPropertyChangeSupport(Object paramObject)
    {
      super();
    }
    
    public void firePropertyChange(final PropertyChangeEvent paramPropertyChangeEvent)
    {
      if (SwingUtilities.isEventDispatchThread()) {
        super.firePropertyChange(paramPropertyChangeEvent);
      } else {
        SwingWorker.doSubmit.add(new Runnable[] { new Runnable()
        {
          public void run()
          {
            SwingWorker.SwingWorkerPropertyChangeSupport.this.firePropertyChange(paramPropertyChangeEvent);
          }
        } });
      }
    }
  }
  
  private static class DoSubmitAccumulativeRunnable
    extends AccumulativeRunnable<Runnable>
    implements ActionListener
  {
    private static final int DELAY = 33;
    
    protected void run(List<Runnable> paramList)
    {
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Runnable localRunnable = (Runnable)localIterator.next();
        localRunnable.run();
      }
    }
    
    protected void submit()
    {
      Timer localTimer = new Timer(33, this);
      localTimer.setRepeats(false);
      localTimer.start();
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      run();
    }
  }
  
  public static enum StateValue
  {
    PENDING,  STARTED,  DONE;
    
    private StateValue() {}
  }
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\swingworker\SwingWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */