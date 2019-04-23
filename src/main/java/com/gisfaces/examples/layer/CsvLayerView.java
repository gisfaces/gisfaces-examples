package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.CSVLayer;
import com.gisfaces.model.map.PopupTemplate;

@Named
@SessionScoped
public class CsvLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -3856849571766278026L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		CSVLayer layer = new CSVLayer();
		layer.setId("earthquakes");
		layer.setTitle("Earthquakes");
		layer.setCopyright("USGS Earthquakes");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("Earthquake {title}", "{*}"));
		layer.setUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.csv");

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
