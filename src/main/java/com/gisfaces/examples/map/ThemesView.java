package com.gisfaces.examples.map;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.MapImageLayer;
import com.gisfaces.model.map.MapTheme;

@Named
@SessionScoped
public class ThemesView extends MapView implements Serializable
{
	private static final long serialVersionUID = -5176293935224842696L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		MapImageLayer layer = new MapImageLayer("census", "https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer");

		// Initialize the map view.
		this.getModel().setMapTheme(MapTheme.LIGHT_BLUE);
		this.getModel().getLayers().add(layer);
	}
}
