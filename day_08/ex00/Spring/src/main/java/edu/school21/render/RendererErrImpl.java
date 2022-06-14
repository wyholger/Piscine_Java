package edu.school21.render;

import edu.school21.pre_processor.PreProcessor;

public class RendererErrImpl implements Renderer
{
	private final PreProcessor pre_processor;

	public RendererErrImpl(PreProcessor pre_processor)
	{
		this.pre_processor = pre_processor;
	}

	@Override
	public void print(String str)
	{
		System.err.println(pre_processor.convert(str));
	}
}
