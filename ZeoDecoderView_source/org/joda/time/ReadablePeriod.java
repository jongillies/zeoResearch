package org.joda.time;

public abstract interface ReadablePeriod
{
  public abstract PeriodType getPeriodType();
  
  public abstract int size();
  
  public abstract DurationFieldType getFieldType(int paramInt);
  
  public abstract int getValue(int paramInt);
  
  public abstract int get(DurationFieldType paramDurationFieldType);
  
  public abstract boolean isSupported(DurationFieldType paramDurationFieldType);
  
  public abstract Period toPeriod();
  
  public abstract MutablePeriod toMutablePeriod();
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract String toString();
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadablePeriod.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */