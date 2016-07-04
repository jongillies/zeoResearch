package org.joda.time.tz;

import java.util.Set;
import org.joda.time.DateTimeZone;

public abstract interface Provider
{
  public abstract DateTimeZone getZone(String paramString);
  
  public abstract Set getAvailableIDs();
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\Provider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */