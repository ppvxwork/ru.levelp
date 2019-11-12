package org.levelup.lesson1.lessons.sql;

import org.levelup.lesson1.lessons.Lesson;
import org.levelup.lesson1.logging.Logging;

/**
 * <p>Занятие по синтаксису языка SQL.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
public class Syntax implements Lesson
{
	private String shortName;
	private String fullName;
	private String description;

	public Syntax()
	{
		this.shortName = "Синтаксис";
		this.fullName = "Синтаксис языка SQL";
		this.description = "На данном занятии мы разберем основные конструкции языка SQL.";
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
		return "Syntax{" +
			"shortName='" + this.getShortName() + '\'' +
			", fullName='" + this.getFullName() + '\'' +
			", description='" + this.getDescription() + '\'' +
			'}';
	}
}
