package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.StreamLayer;
import com.gisfaces.model.layer.StreamLayerPurgeOptions;
import com.gisfaces.model.map.Basemap;
import com.gisfaces.model.map.PopupTemplate;

@Named
@SessionScoped
public class StreamLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -1732781736877121348L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		StreamLayer layer = new StreamLayer();
		layer.setId("labus");
		layer.setTitle("LA Bus Stream Layer");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("LA Bus {id} Route {route_id}", "{*}"));
		layer.setPurgeOptions(new StreamLayerPurgeOptions(1000, 10));
		layer.setUrl("https://geoeventsample1.esri.com:6443/arcgis/rest/services/LABus/StreamServer");

		// Initialize the map view.
		this.setBasemap(Basemap.LIGHTGRAY.getValue());
		this.setLatitude(34.095316);
		this.setLongitude(-118.354697);
		this.setZoom(10);
		this.getModel().getLayers().add(layer);
	}
}
