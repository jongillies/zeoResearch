package org.jdesktop.swingworker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;

abstract class AccumulativeRunnable<T>
  implements Runnable
{
  private List<T> arguments = null;
  
  protected abstract void run(List<T> paramList);
  
  public final void run()
  {
    run(flush());
  }
  
  public final synchronized void add(T... paramVarArgs)
  {
    int i = 1;
    if (this.arguments == null)
    {
      i = 0;
      this.arguments = new ArrayList();
    }
    Collections.addAll(this.arguments, paramVarArgs);
    if (i == 0) {
      submit();
    }
  }
  
  protected void submit()
  {
    SwingUtilities.invokeLater(this);
  }
  
  private final synchronized List<T> flush()
  {
    List localList = this.arguments;
    this.arguments = null;
    return localList;
  }
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\swingworker\AccumulativeRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */