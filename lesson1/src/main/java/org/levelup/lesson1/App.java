package org.levelup.lesson1;

import com.sun.istack.internal.NotNull;
import org.levelup.lesson1.lessons.Lesson;
import org.levelup.lesson1.logging.LoggingAnnotationProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>Основной класс приложения.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
public class App
{
	private static final String DOT             = ".";
	private static final String LINE            = "/";
	private static final String CLASS_EXTENTION = ".class";

	/**
	 * <p>Основной метод приложения.</p>
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null)
			{
				throw new IllegalStateException();
			}
			final List<Class>                classes   = getClasses(classLoader, "org.levelup.lesson1.lessons");
			final LoggingAnnotationProcessor processor = new LoggingAnnotationProcessor();
			for (Class<?> clazz : classes)
			{
				final Lesson instance          = (Lesson) clazz.newInstance();
				final Lesson processedInstance = (Lesson) processor.process(instance);
				System.out.println(processedInstance);
			}
		}
		catch (Exception e)
		{
			System.out.println("Ошибка: " + e.getMessage());
		}
	}

	/**
	 * <p>Вовзращает все классы пакета, доступные для текущего загрузчика.</p>
	 *
	 * @param classLoader загрузчик
	 * @param packageName пакет, в котором осуществляем поиск
	 * @return все классы пакета, доступные для текущего загрузчика
	 * @throws ClassNotFoundException Исключение, сообщающее о том, что класс не найден
	 * @throws IOException Исключение ввода/вывода
	 */
	private static List<Class> getClasses(@NotNull ClassLoader classLoader, @NotNull String packageName)
		throws ClassNotFoundException, IOException
	{
		final String           path        = packageName.replace(DOT, LINE);
		final Enumeration<URL> resources   = classLoader.getResources(path);
		final List<File>       directories = new ArrayList<File>();
		while (resources.hasMoreElements())
		{
			final URL resource = resources.nextElement();
			directories.add(new File(resource.getFile()));
		}
		final ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : directories)
		{
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	/**
	 * <p>Возвращает все классы по текущему пакету и всех подпакетах.</p>
	 *
	 * @param directory   текущий путь в пакете
	 * @param packageName пакет, в котором осуществляем поиск
	 * @return классы текущего пакета
	 * @throws ClassNotFoundException Исключение, сообщающее о том, что класс не найден
	 */
	private static List<Class> findClasses(@NotNull File directory, @NotNull String packageName) throws ClassNotFoundException
	{
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists())
		{
			return classes;
		}
		File[] files = directory.listFiles();
		if (files != null && files.length > 0)
		{
			for (File file : files)
			{
				if (file.isDirectory())
				{
					assert !file.getName().contains(DOT);
					classes.addAll(findClasses(file, packageName + DOT + file.getName()));
				}
				else if (file.getName().endsWith(CLASS_EXTENTION))
				{
					final Class<?> clazz =
						Class.forName(packageName + DOT + file.getName()
						                                      .substring(0, file.getName()
						                                                        .length() - CLASS_EXTENTION.length()));
					if (!clazz.isInterface())
					{
						classes.add(clazz);
					}
				}
			}
		}
		return classes;
	}
}
