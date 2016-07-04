package graphs.common;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract interface Layer
{
  public abstract Rectangle2D getBoundingBox();
  
  public abstract void render(Graphics2D paramGraphics2D);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\graphs\common\Layer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */