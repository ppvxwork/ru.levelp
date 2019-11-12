package org.levelup.lesson1.lessons.java;

import org.levelup.lesson1.lessons.Lesson;
import org.levelup.lesson1.logging.Logging;

/**
 * <p>Занятие по рефлексии в языке JAVA.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
public class Reflection implements Lesson
{
	private String shortName;
	private String fullName;
	private String description;

	public Reflection()
	{
		this.shortName = "Рефлексия";
		this.fullName = "Рефлексия в языке JAVA";
		this.description = "На данном занятии мы разберем понятие рефлексии в языке JAVA, и основные инструменты работы с ней.";
	}

	@Logging
	public String getShortName()
	{
		return shortName;
	}

	@Logging
	public String getFullName()
	{
		return fullName;
	}

	@Logging
	public String getDescription()
	{
		return description;
	}

	@Override
	public String toString()
	{
		return "Reflection{" +
			"shortName='" + this.getShortName() + '\'' +
			", fullName='" + this.getFullName() + '\'' +
			", description='" + this.getDescription() + '\'' +
			'}';
	}
}
