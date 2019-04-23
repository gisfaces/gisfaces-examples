package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.PointCloudLayer;

@Named
@SessionScoped
public class PointCloudLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -3320330687619029441L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		PointCloudLayer layer = new PointCloudLayer("barnegat", "https://tiles.arcgis.com/tiles/V6ZHFr6zdgNZuVG0/arcgis/rest/services/BARNEGAT_BAY_LiDAR_UTM/SceneServer");

		// Initialize the map view.
		this.setLatitude(39.772073);
		this.setLongitude(-74.121410);
		this.setZoom(12);
		this.getModel().getLayers().add(layer);
	}
}
