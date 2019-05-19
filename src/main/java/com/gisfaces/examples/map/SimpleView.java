package com.gisfaces.examples.map;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.model.map.Basemap;

@Named
@SessionScoped
public class SimpleView extends MapView implements Serializable
{
	private static final long serialVersionUID = 7931811193388517871L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.HYBRID);
		this.getModel().getViewpoint().setLatitude(29.9);
		this.getModel().getViewpoint().setLongitude(-81.3);
		this.getModel().getViewpoint().setZoom(10);
	}
}
