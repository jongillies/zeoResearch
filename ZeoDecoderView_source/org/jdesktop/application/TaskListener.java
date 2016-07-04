package org.jdesktop.application;

import java.util.List;

public abstract interface TaskListener<T, V>
{
  public abstract void doInBackground(TaskEvent<Void> paramTaskEvent);
  
  public abstract void process(TaskEvent<List<V>> paramTaskEvent);
  
  public abstract void succeeded(TaskEvent<T> paramTaskEvent);
  
  public abstract void failed(TaskEvent<Throwable> paramTaskEvent);
  
  public abstract void cancelled(TaskEvent<Void> paramTaskEvent);
  
  public abstract void interrupted(TaskEvent<InterruptedException> paramTaskEvent);
  
  public abstract void finished(TaskEvent<Void> paramTaskEvent);
  
  public static class Adapter<T, V>
    implements TaskListener<T, V>
  {
    public void doInBackground(TaskEvent<Void> event) {}
    
    public void process(TaskEvent<List<V>> event) {}
    
    public void succeeded(TaskEvent<T> event) {}
    
    public void failed(TaskEvent<Throwable> event) {}
    
    public void cancelled(TaskEvent<Void> event) {}
    
    public void interrupted(TaskEvent<InterruptedException> event) {}
    
    public void finished(TaskEvent<Void> event) {}
  }
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\TaskListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */