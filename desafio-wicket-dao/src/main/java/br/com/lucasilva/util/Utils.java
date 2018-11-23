package br.com.lucasilva.util;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.component.IRequestablePage;

public class Utils {
	public static <T, C extends IRequestablePage> Link<T> addLink(String id, final Class<C> responsePage) {
		Link<T> link = new Link<T>(id) {

			private static final long serialVersionUID = -892126432330393674L;

			@Override
			public void onClick() {
				setResponsePage(responsePage);
			}

		};

		return link;
	}
}
