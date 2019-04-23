package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.FeatureLayer;

@Named
@SessionScoped
public class FeatureLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -5505438571864601823L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		FeatureLayer layer = new FeatureLayer("trees", "https://services.arcgis.com/V6ZHFr6zdgNZuVG0/arcgis/rest/services/Landscape_Trees/FeatureServer/0");

		// Initialize the map view.
		this.setLatitude(35.612362);
		this.setLongitude(-82.442788);
		this.setZoom(16);
		this.getModel().getLayers().add(layer);
	}
}
