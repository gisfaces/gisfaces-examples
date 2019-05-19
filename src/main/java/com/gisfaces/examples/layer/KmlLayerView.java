package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.KMLLayer;
import com.gisfaces.model.map.MapType;

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
		this.getModel().setMapType(MapType.TWO_D);
		this.getModel().getViewpoint().setLatitude(39.828175);
		this.getModel().getViewpoint().setLongitude(-98.5795);
		this.getModel().getViewpoint().setZoom(3);
		this.getModel().getLayers().add(layer);
	}
}
