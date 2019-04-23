package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.WMSLayer;

@Named
@SessionScoped
public class WmsLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 3231186211639544043L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		WMSLayer layer = new WMSLayer();
		layer.setId("terrestris");
		layer.setTitle("Terrestris");
		layer.setUrl("https://ows.terrestris.de/osm/service");

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
