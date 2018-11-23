package br.com.lucasilva.view.templates;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;

import br.com.lucasilva.view.templates.panels.FooterPanel;

public class TemplatePage extends WebPage {

	private static final long serialVersionUID = 8174488136591686563L;

	private Component footer;
	
	public TemplatePage() {
		add(footer = new FooterPanel("footer"));
	}
	
}
