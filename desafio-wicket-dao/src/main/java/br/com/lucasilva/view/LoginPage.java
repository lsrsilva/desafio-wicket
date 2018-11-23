package br.com.lucasilva.view;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;

import br.com.lucasilva.model.User;
import br.com.lucasilva.util.Utils;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 8310170959446908559L;

	private User user;
	public LoginPage() {
		user = new User();
		add(Utils.addLink("homeLink", HomePage.class));
//		add(Utils.addLink("sobre", SobrePage.class);)
//		add(Utils.addLink("contaot", ContatoPage.class);)
		
		final Form<Object> loginForm = new Form<Object>("loginForm");
		loginForm.add(new Label("label", "Login"));
		final EmailTextField email = new EmailTextField("email");
		loginForm.add(email);
		final PasswordTextField psw = new PasswordTextField("psw");
		loginForm.add(psw);
		
		AjaxButton loginBtn = new AjaxButton("login") {

			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);

				if(email.getValue().equals(user.getEmail()) && psw.getValue().equals(user.getSenha())) {
					setResponsePage(HomePage.class);
				}
				
			}
		};
		
		loginForm.add(loginBtn);
		
		add(loginForm);
	}

}
