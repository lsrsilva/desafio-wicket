package br.com.lucasilva.view.panel;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.lucasilva.model.User;
import br.com.lucasilva.model.dao.UserDao;

public class ExclusaoPanel extends Panel {

	private static final long serialVersionUID = -1146544499294817028L;

	public ExclusaoPanel(String id, final List<User> regPesList, final UserDao userDao, final User pessoa) {
		super(id);
		add(new Label("confirma", "Excluir usuário"));
		
		add(new Label("exMsg", "Esta ação irá excluir o usuário \"" + pessoa.getNome() + "\". Prosseguir?"));

		add(new AjaxLink<User>("btn-confirma") {
			private static final long serialVersionUID = -6737235094619280702L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				userDao.excluir(pessoa);
				updAfterDel(target, pessoa);
			}
		});

		add(new AjaxLink<User>("btn-cancelar") {

			private static final long serialVersionUID = -2299143403487701927L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				updAfterDel(target, pessoa);
			}
		});
	}

	public void updAfterDel(AjaxRequestTarget target, User pessoa) {

	}
}