package br.com.lucasilva;

import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.UrlPathPageParametersEncoder;

import br.com.lucasilva.view.HomePage;
import br.com.lucasilva.view.ListagemPage;
import br.com.lucasilva.view.LoginPage;
import br.com.lucasilva.view.PageableListagemPage;

public class DesafioWicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return LoginPage.class;
	}
	
	@Override
	public void init() {
		super.init();
		getDebugSettings().setAjaxDebugModeEnabled(true);
		
		mount(new MountedMapper("/login", LoginPage.class, new UrlPathPageParametersEncoder()));
		mount(new MountedMapper("/home", HomePage.class, new UrlPathPageParametersEncoder()));
		//mount(new MountedMapper("/cadastro", PageableListagemPage.class, new UrlPathPageParametersEncoder())); //Página com a paginação
		mount(new MountedMapper("/cadastro", ListagemPage.class, new UrlPathPageParametersEncoder()));
	}

}