package org.joda.time;

public abstract interface ReadWritableInterval
  extends ReadableInterval
{
  public abstract void setInterval(long paramLong1, long paramLong2);
  
  public abstract void setInterval(ReadableInterval paramReadableInterval);
  
  public abstract void setInterval(ReadableInstant paramReadableInstant1, ReadableInstant paramReadableInstant2);
  
  public abstract void setChronology(Chronology paramChronology);
  
  public abstract void setStartMillis(long paramLong);
  
  public abstract void setStart(ReadableInstant paramReadableInstant);
  
  public abstract void setEndMillis(long paramLong);
  
  public abstract void setEnd(ReadableInstant paramReadableInstant);
  
  public abstract void setDurationAfterStart(ReadableDuration paramReadableDuration);
  
  public abstract void setDurationBeforeEnd(ReadableDuration paramReadableDuration);
  
  public abstract void setPeriodAfterStart(ReadablePeriod paramReadablePeriod);
  
  public abstract void setPeriodBeforeEnd(ReadablePeriod paramReadablePeriod);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadWritableInterval.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */