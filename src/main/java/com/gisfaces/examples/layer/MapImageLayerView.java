package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.MapImageLayer;

@Named
@SessionScoped
public class MapImageLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 1642697252448095889L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		MapImageLayer layer = new MapImageLayer("census", "https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer");

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
