package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.ReadablePeriod;

public abstract interface PeriodPrinter
{
  public abstract int calculatePrintedLength(ReadablePeriod paramReadablePeriod, Locale paramLocale);
  
  public abstract int countFieldsToPrint(ReadablePeriod paramReadablePeriod, int paramInt, Locale paramLocale);
  
  public abstract void printTo(StringBuffer paramStringBuffer, ReadablePeriod paramReadablePeriod, Locale paramLocale);
  
  public abstract void printTo(Writer paramWriter, ReadablePeriod paramReadablePeriod, Locale paramLocale)
    throws IOException;
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\PeriodPrinter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */