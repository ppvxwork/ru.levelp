package org.levelup.lesson1.logging;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Класс-процессор, проверяющий объект на наличие аннотации @Logging. Проксирует методы, помеченные данной аннотацией.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
@SuppressWarnings("ALL")
public class LoggingAnnotationProcessor
{
	public Object process(Object object)
	{
		Class<?> objectClass = object.getClass();
		// получаем список методов, которые есть в классе.
		Method[] methods = objectClass.getDeclaredMethods();
		// создаем список методов, которые помечаны искомой аннотацией в классе.
		Set<Method> annotatedMethods = new HashSet<Method>();
		for (Method method : methods)
		{
			// ищем метод, который помечен аннотацией @Logging.
			Logging annotation = method.getAnnotation(Logging.class);
			// если annotation != null, то у данного метода есть аннотация @Logging.
			if (annotation != null)
			{
				annotatedMethods.add(method);
			}
		}
		// если мы нашли методы в объекте, помеченные аннотацией @Logging,
		// то мы создаем прокси-объект,в котором переопределяем поведение метода с аннотацией @Logging.
		if (!annotatedMethods.isEmpty())
		{
			return proxy(object, annotatedMethods, objectClass);
		}
		// иначе возвращаем оригинальный объект, который передали в метод.
		return object;
	}

	private Object proxy(Object object, Set<Method> annotatedMethods, Class<?> objectClass)
	{
		return Proxy.newProxyInstance(
			objectClass.getClassLoader(),
			objectClass.getInterfaces(),
			new LoggingAnnotationInvocationHandler(annotatedMethods, object)
		);
	}

}
