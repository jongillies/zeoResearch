package org.joda.time;

public abstract interface ReadableDuration
  extends Comparable
{
  public abstract long getMillis();
  
  public abstract Duration toDuration();
  
  public abstract Period toPeriod();
  
  public abstract int compareTo(Object paramObject);
  
  public abstract boolean isEqual(ReadableDuration paramReadableDuration);
  
  public abstract boolean isLongerThan(ReadableDuration paramReadableDuration);
  
  public abstract boolean isShorterThan(ReadableDuration paramReadableDuration);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract String toString();
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadableDuration.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */