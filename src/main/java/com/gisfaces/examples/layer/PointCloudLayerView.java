package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.PointCloudLayer;
import com.gisfaces.model.renderer.FixedSizePointSizeAlgorithm;
import com.gisfaces.model.renderer.PointCloudRGBRenderer;

@Named
@SessionScoped
public class PointCloudLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -3320330687619029441L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the optional point cloud renderer.
		PointCloudRGBRenderer renderer = new PointCloudRGBRenderer();
		renderer.setField("RGB");
		renderer.setPointsPerInch(25);
		renderer.setPointSizeAlgorithm(new FixedSizePointSizeAlgorithm(false, 5));

		// Build a map layer.
		PointCloudLayer layer = new PointCloudLayer("barnegat", "https://tiles.arcgis.com/tiles/V6ZHFr6zdgNZuVG0/arcgis/rest/services/BARNEGAT_BAY_LiDAR_UTM/SceneServer");
		layer.setRenderer(renderer);

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(39.772073);
		this.getModel().getViewpoint().setLongitude(-74.121410);
		this.getModel().getViewpoint().setZoom(12);
		this.getModel().getLayers().add(layer);
	}
}
