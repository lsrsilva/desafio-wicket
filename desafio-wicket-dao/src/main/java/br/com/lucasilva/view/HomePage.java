
package br.com.lucasilva.view;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.lucasilva.view.templates.TemplatePage;

public class HomePage extends TemplatePage {

	private static final long serialVersionUID = 4115591780665652299L;
	
	private String newMsgButton;
	
	public HomePage() {
		add(new Link<Object>("linkCad") {

			private static final long serialVersionUID = -4306848952762518763L;

			@Override
			public void onClick() {
				setResponsePage(ListagemPage.class);
			}
		}, new Link<Object>("linkLogin") {

			private static final long serialVersionUID = -4306848952762518763L;

			@Override
			public void onClick() {
				setResponsePage(LoginPage.class);
			}
		});
		
		add(new Link<Object>("homeLink") {

			private static final long serialVersionUID = -4306848952762518763L;

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});
		
		
		Form<String> formCad = new Form<String>("form");
		
		final TextField<String> esconderCampo = new TextField<String>("textField", new Model<String>());
		//deve ser utilzado quando o component será utilizado como target do Ajax
		esconderCampo.setOutputMarkupPlaceholderTag(true);
		formCad.add(esconderCampo);
		esconderCampo.setVisible(false);
		
		formCad.add(new AjaxButton("esconde", new PropertyModel<String>(this, "newMsgButton")) {

			private static final long serialVersionUID = 696498801170669530L;
			
			private String changeClass = null;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				if (esconderCampo.isVisible()) {
					changeClass = null;
					setNewMsgButton("Mostre-me (*-*)");
					esconderCampo.setVisible(false);
				} else {
					changeClass = "btn btn-danger";
					setNewMsgButton("Esconda-me (`-´) SEU INSOLENTE");
					esconderCampo.setVisible(true);
				}
				target.add(esconderCampo);
				target.add(this);
			}
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				// TODO Auto-generated method stub
				super.onComponentTag(tag);
				if(changeClass != null) tag.put("class", changeClass);
			}
			
		});
		
		add(formCad);
	}

	public String getNewMsgButton() {
		return newMsgButton;
	}

	public void setNewMsgButton(String newMsgButton) {
		this.newMsgButton = newMsgButton;
	}
}