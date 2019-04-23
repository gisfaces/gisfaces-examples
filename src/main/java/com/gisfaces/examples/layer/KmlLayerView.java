package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.KMLLayer;

@Named
@SessionScoped
public class KmlLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -1049888372224837792L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		KMLLayer layer = new KMLLayer();
		layer.setId("earthquakes");
		layer.setTitle("Earthquakes");
		layer.setUrl("https://earthquake.usgs.gov/fdsnws/event/1/query?format=kml&minmagnitude=5.0");

		// Initialize the map view.
		this.setLatitude(39.828175);
		this.setLongitude(-98.5795);
		this.setZoom(3);
		this.getModel().getLayers().add(layer);
	}
}
