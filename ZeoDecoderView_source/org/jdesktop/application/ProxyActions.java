package org.jdesktop.application;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE})
public @interface ProxyActions
{
  String[] value() default {};
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\ProxyActions.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */