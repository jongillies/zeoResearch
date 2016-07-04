package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;

public abstract interface PeriodConverter
  extends Converter
{
  public abstract void setInto(ReadWritablePeriod paramReadWritablePeriod, Object paramObject, Chronology paramChronology);
  
  public abstract PeriodType getPeriodType(Object paramObject);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\PeriodConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */