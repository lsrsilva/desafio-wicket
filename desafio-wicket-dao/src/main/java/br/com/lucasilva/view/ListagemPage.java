package br.com.lucasilva.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.lucasilva.model.User;
import br.com.lucasilva.model.dao.UserDao;
import br.com.lucasilva.util.Utils;
import br.com.lucasilva.view.panel.ExclusaoPanel;
import br.com.lucasilva.view.panel.FormPanel;
import br.com.lucasilva.view.templates.TemplatePage;

public class ListagemPage extends TemplatePage {

	private static final long serialVersionUID = 4291324880250962408L;

	private ModalWindow cadModal;
	private ModalWindow excluirMod = new ModalWindow("excluirMod");
	private WebMarkupContainer listContainer = new WebMarkupContainer("container");

	private UserDao userDao = new UserDao();
	List<User> regPesList = userDao.listar();
	
	public ListagemPage() {
		cadModal = new ModalWindow("cadModal");
		add(Utils.addLink("homeLink", HomePage.class), Utils.addLink("cadLink", ListagemPage.class));

		addListView("listView");

		add(new AjaxLink<User>("addUsers") {

			private static final long serialVersionUID = -1009738781609150283L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				FormPanel formPanel = new FormPanel(cadModal.getContentId(), cadModal) {

					private static final long serialVersionUID = 4704737323870277087L;

					public void executarAoSalvar(AjaxRequestTarget target, User pessoa) {
						userDao.salvar(pessoa);

						target.add(listContainer);
						cadModal.close(target);
					}
				};
				cadModal.setContent(formPanel);
				cadModal.show(target);
			}

		});

		cadModal.setInitialHeight(545);
		cadModal.setInitialWidth(1300);

		excluirMod.setInitialHeight(200);
		excluirMod.setInitialWidth(480);
		excluirMod.setResizable(false);

		add(listContainer);
		add(cadModal);
		add(excluirMod);
	}

	private void addListView(String id) {
		LoadableDetachableModel<List<User>> loader = new LoadableDetachableModel<List<User>>() {

			private static final long serialVersionUID = 4592534822245745071L;

			@Override
			protected List<User> load() {
				return regPesList;
			}

		};

		ListView<User> regPesView = new ListView<User>(id, loader) {

			private static final long serialVersionUID = 7075940622701046168L;

			@Override
			protected void populateItem(ListItem<User> item) {
				final User regPes = item.getModelObject();
				item.add(new Label("nome", regPes.getNome()));
				item.add(new Label("sbnm", regPes.getSbnm()));
				item.add(new Label("cpf", regPes.getCpf()));
				item.add(new Label("rg", regPes.getRg()));
				item.add(new Label("telefone", regPes.getTelefone()));
				item.add(new Label("dataNasc", regPes.getDataNasc()));
				item.add(new Label("email", regPes.getEmail()));
				item.add(new Label("termos", regPes.getTermos()));
				item.add(new Label("sexo", regPes.getSexo()));
				item.add(new Label("obs", regPes.getObs()));

				final ListItem<User> itemAux = item;

				item.add(new AjaxLink<User>("edit") {

					private static final long serialVersionUID = 7401008670702528872L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						
						FormPanel formPanel = new FormPanel(cadModal.getContentId(), regPes, cadModal) {

							private static final long serialVersionUID = 5193526591775800960L;

							public void executarAoSalvar(AjaxRequestTarget target, User pessoa) {
								userDao.editar(pessoa);

								target.add(listContainer);

								cadModal.close(target);
							}
						};

						cadModal.setContent(formPanel);
						cadModal.show(target);

					}
				});

				item.add(new AjaxLink<User>("remove") {

					private static final long serialVersionUID = -2498360209316173653L;

					@Override
					public void onClick(AjaxRequestTarget target) {
						ExclusaoPanel excPanel = new ExclusaoPanel(excluirMod.getContentId(), regPesList, userDao,
								regPes) {

							private static final long serialVersionUID = 204782328186132404L;

							public void updAfterDel(AjaxRequestTarget target, User pessoa) {
								target.add(listContainer);
								excluirMod.close(target);
							}
						};

						excluirMod.setContent(excPanel);
						excluirMod.show(target);
					}
				});
			}
		};

		listContainer.setOutputMarkupPlaceholderTag(true);
		listContainer.add(regPesView);
	}

}