package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.VectorTileLayer;
import com.gisfaces.model.map.Basemap;

@Named
@SessionScoped
public class VectorTileLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -5082229339828528042L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		VectorTileLayer layer = new VectorTileLayer();
		layer.setId("vectortile");
		layer.setTitle("Vector Tile Layer");
		layer.setUrl("https://jsapi.maps.arcgis.com/sharing/rest/content/items/75f4dfdff19e445395653121a95a85db/resources/styles/root.json");

		// Initialize the map view.
		this.setBasemap(Basemap.LIGHTGRAY.getValue());
		this.getModel().getLayers().add(layer);
	}
}
