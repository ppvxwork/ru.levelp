package org.levelup.lesson1.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Аннотация для допольнительного логгирования по методу.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
@SuppressWarnings("ALL")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging
{
}
