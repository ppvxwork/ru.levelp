package org.levelup.lesson1.lessons;

import org.levelup.lesson1.logging.Logging;

/**
 * <p>Интерфейс занятия.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
public interface Lesson
{
	String getShortName();
	String getFullName();
	String getDescription();
	String toString();
}
