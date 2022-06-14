package edu.school21.app;

import edu.school21.pre_processor.PreProcessor;
import edu.school21.pre_processor.PreProcessorToLowerImpl;
import edu.school21.pre_processor.PreProcessorToUpperImpl;
import edu.school21.printer.Printer;
import edu.school21.printer.PrinterWithDateTimeImpl;
import edu.school21.printer.PrinterWithPrefixImpl;
import edu.school21.render.Renderer;
import edu.school21.render.RendererErrImpl;
import edu.school21.render.RendererStandardImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class Program
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		Printer printer = context.getBean("printer_prefix_rend_err_to_lower", Printer.class);
		printer.print("Hello!");
		printer = context.getBean("printer_prefix_rend_err_to_upper", Printer.class);
		printer.print("Hello!!");
		printer = context.getBean("printer_prefix_rend_std_to_lower", Printer.class);
		printer.print("Hello!!!");
		printer = context.getBean("printer_prefix_rend_std_to_upper", Printer.class);
		printer.print("Hello!!!!");
		printer = context.getBean("printer_time_rend_err_to_lower", Printer.class);
		printer.print("Hello!!!!!");
		printer = context.getBean("printer_time_rend_err_to_upper", Printer.class);
		printer.print("Hello!!!!!!");
		printer = context.getBean("printer_time_rend_std_to_lower", Printer.class);
		printer.print("Hello!!!!!!!");
		printer = context.getBean("printer_time_rend_std_to_upper", Printer.class);
		printer.print("Hello!!!!!!!!");
	}
}
