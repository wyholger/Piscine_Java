package edu.school21.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"edu/school21/annotations/HtmlForm.java", "edu/school21/annotations/HtmlInput.java"})
public class HtmlProcessor extends AbstractProcessor
{
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
	{
		for (Element user_form: roundEnv.getElementsAnnotatedWith(HtmlForm.class))
		{
			HtmlForm html_form = user_form.getAnnotation(HtmlForm.class);
			StringBuilder line = new StringBuilder(String.format("<form action = \"%s\" method = \"%s\">\n", html_form.action(), html_form.method()));
			List<? extends Element> user_form_elements = user_form.getEnclosedElements();
			for (Element field: roundEnv.getElementsAnnotatedWith(HtmlInput.class))
			{
				if (user_form_elements.contains(field))
					continue;
				HtmlInput html_input = field.getAnnotation(HtmlInput.class);
				line.append(String.format("\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n", html_input.type(), html_input.name(), html_input.placeholder()));
			}
			line.append("\t<input type = \"submit\" value = \"Send\">\n</form>");
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("target/classes/" + html_form.fileName())))
			{
				writer.write(line.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
}
