package com.gisfaces.examples.map;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class WebmapView extends MapView implements Serializable
{
	private static final long serialVersionUID = -1194698567549649215L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Initialize the map view.
		this.getModel().setWebMapPortalItemId("f2e9b762544945f390ca4ac3671cfa72");
	}
}
