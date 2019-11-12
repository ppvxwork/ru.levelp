package org.levelup.lesson1.logging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * <p>Класс-перехватчик вызовов метода прокси объекта.</p>
 * Created by popovtsev-pv on 11.11.2019
 */
@SuppressWarnings("ALL")
public class LoggingAnnotationInvocationHandler implements InvocationHandler
{
	/**
	 * <p>Методы, которые помечен аннотацией @Logging.</p>
	 */
	private Set<Method> annotatedMethods;

	/**
	 * <p>Объект класса, у которого есть хотя бы один метод, который помечен аннотацией @Logging.</p>
	 */
	private Object object;

	public LoggingAnnotationInvocationHandler(Set<Method> annotatedMethods, Object object)
	{
		this.annotatedMethods = annotatedMethods;
		this.object = object;
	}

	/**
	 * <p>В этом методе мы определяем поведение метода.</p>
	 *
	 * @param proxy  прокси-объект
	 * @param method метод, который в данный момент вызывается
	 * @param args   аргументы метода, который в данный момент вызывается
	 * @return результат работы метода
	 * @throws Throwable ошибка работы прокси
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		for (Method annotatedMethod : annotatedMethods)
		{
			if (method.getName().equals(annotatedMethod.getName()))
			{
				System.out.println("Данный метод помечен аннотацией @Logging");
			}
		}
		return method.invoke(object, args);
	}
}
