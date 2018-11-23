package br.com.lucasilva.view.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import br.com.lucasilva.model.User;
import br.com.lucasilva.view.ListagemPage;

public class FormPanel extends Panel {

	private static final long serialVersionUID = 3328553239871472165L;

	private List<String> sxList = Arrays.asList("Masculino", "Feminino");
	private List<User> regPesList = new ArrayList<User>();
	private User pessoaAux;

	/*
	 * É necessário ter os dois construtores para que quando for editar o Wicket
	 * preencha os campos automaticamente. No Construtor que contem o objeto pessoa
	 * deve ser passado um objeto já criado. No caso está sendo passado o regPes
	 * criado no populate da ListView na página LitagemPage.
	 */

	public FormPanel(String id, ModalWindow modal) {
		this(id, new User(), modal);
	}

	public FormPanel(String id, final User pessoa, final ModalWindow modal) {
		super(id);

		final FeedbackPanel feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);

		pessoaAux = pessoa;
		CompoundPropertyModel<User> regPesModel = new CompoundPropertyModel<User>(pessoa);

		final Form<User> cadForm = new Form<User>("cadForm", regPesModel);

		RadioChoice<String> grupo = new RadioChoice<String>("sexo", sxList);
		cadForm.add(new Label("lbSx", "Sexo"));
		TextField<String> nome = addTextField("nome");
		nome.setRequired(true);
		TextField<String> sbnm = addTextField("sbnm");
		sbnm.setRequired(true);
		NumberTextField<Long> cpf = addNumberTextField("cpf");
		cpf.setRequired(true);
		NumberTextField<Long> rg = addNumberTextField("rg");
		rg.setRequired(true);
		NumberTextField<Long> telefone = addNumberTextField("telefone");
		EmailTextField email = addEmailTextField("email");
		email.setRequired(false);
		email.add(EmailAddressValidator.getInstance());
		PasswordTextField senha = addPswTextField("senha");
		senha.setRequired(false);
		TextField<String> rua = addTextField("endereco.rua");
		TextField<String> cep = addTextField("endereco.cep");

		cadForm.add(nome);
		cadForm.add(sbnm);
		cadForm.add(cpf);
		cadForm.add(rg);
		cadForm.add(telefone);
		cadForm.add(email);
		cadForm.add(senha);
		cadForm.add(rua);
		cadForm.add(cep);
		cadForm.add(grupo);

		// mascarar quando for do tipo Date no modelo
		DateTextField dtNasc = new DateTextField("dataNasc");
		cadForm.add(dtNasc);
		DatePicker datePicker = new DatePicker() {

			private static final long serialVersionUID = -3701264872352758583L;

			@Override
			protected String getIconStyle() {
				return super.getIconStyle();
			}
		};

		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		dtNasc.add(datePicker);

		cadForm.add(new CheckBox("termos"));
		cadForm.add(new TextArea<String>("obs"));

		AjaxButton btnConfirmar = new AjaxButton("salvar") {

			private static final long serialVersionUID = -1554457224836692994L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				executarAoSalvar(target, pessoaAux);

				pessoaAux = new User();
				cadForm.clearInput();
				cadForm.modelChanged();
				cadForm.setModelObject(pessoaAux);

				for (User reg : regPesList) {
					System.out.println(reg.getNome());
					System.out.println(reg.getSbnm());
					System.out.println(reg.getCpf());
					System.out.println(reg.getRg());
					System.out.println(reg.getTelefone());
					System.out.println(reg.getEmail());
					System.out.println(reg.getSexo());
					System.out.println(reg.getSenha());
					System.out.println(reg.getObs());
					System.out.println(reg.getDataNasc());
					System.out.println();
				}
				target.add(cadForm);
				target.add(modal);
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.add(feedback);
			}

		};

		cadForm.setOutputMarkupId(true);
		cadForm.add(btnConfirmar);
		add(cadForm);
		add(feedback);
	}

	public void executarAoSalvar(AjaxRequestTarget target, User pessoa) {
	}

	public <T> TextField<T> addTextField(String id) {
		return new TextField<T>(id);
	}

	public <N extends Number & Comparable<N>> NumberTextField<N> addNumberTextField(String id) {
		return new NumberTextField<N>(id);
	}

	public EmailTextField addEmailTextField(String id) {
		return new EmailTextField(id);
	}

	public PasswordTextField addPswTextField(String id) {
		return new PasswordTextField(id);
	}
}