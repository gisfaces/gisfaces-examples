package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.GeoJSONLayer;
import com.gisfaces.model.map.PopupTemplate;

@Named
@SessionScoped
public class GeoJsonLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 6680927620368200395L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		GeoJSONLayer layer = new GeoJSONLayer();
		layer.setId("earthquakes");
		layer.setTitle("Earthquakes");
		layer.setCopyright("USGS Earthquakes");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("Earthquake {title}", "{*}"));
		layer.setUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");

		// Initialize the map view.
		this.setLatitude(39.828175);
		this.setLongitude(-98.5795);
		this.setZoom(4);
		this.getModel().getLayers().add(layer);
	}
}
