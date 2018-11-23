package br.com.lucasilva.view.medico;

import org.apache.wicket.markup.html.form.TextField;

import br.com.lucasilva.model.Medico;
import br.com.lucasilva.view.panel.FormPanel;

public class FormPanelMed extends FormPanel {

	private static final long serialVersionUID = 1547368131425630405L;

	public FormPanelMed(String id) {
		this(id, new Medico());
		// TODO Auto-generated constructor stub
	}
	
	public FormPanelMed(String id, Medico medico) {
		super(id, null);
		
		TextField<Character> ufCrm = addTextField("ufCrm");
		ufCrm.setRequired(true);
	}

}
