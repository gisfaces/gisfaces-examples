package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.TileLayer;
import com.gisfaces.model.map.Basemap;

@Named
@SessionScoped
public class TileLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -8628938523118785144L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		TileLayer housing = new TileLayer();
		housing.setId("nyhousing");
		housing.setTitle("NY Housing");
		housing.setUrl("https://tiles.arcgis.com/tiles/nGt4QxSblgDfeJn9/arcgis/rest/services/New_York_Housing_Density/MapServer");
		housing.setMaxScale(0);

		// Build a map layer.
		TileLayer streets = new TileLayer();
		streets.setId("usstreets");
		streets.setTitle("US Streets");
		streets.setUrl("https://server.arcgisonline.com/ArcGIS/rest/services/Reference/World_Transportation/MapServer");

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.LIGHTGRAY);
		this.getModel().getViewpoint().setLatitude(40.664875);
		this.getModel().getViewpoint().setLongitude(-73.988606);
		this.getModel().getViewpoint().setZoom(11);
		this.getModel().getLayers().add(housing);
		this.getModel().getLayers().add(streets);
	}
}
