package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.ReadWritableInterval;

public abstract interface IntervalConverter
  extends Converter
{
  public abstract boolean isReadableInterval(Object paramObject, Chronology paramChronology);
  
  public abstract void setInto(ReadWritableInterval paramReadWritableInterval, Object paramObject, Chronology paramChronology);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\IntervalConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */