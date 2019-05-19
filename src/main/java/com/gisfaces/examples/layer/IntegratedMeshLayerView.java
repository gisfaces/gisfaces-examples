package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.IntegratedMeshLayer;
import com.gisfaces.model.map.Basemap;

@Named
@SessionScoped
public class IntegratedMeshLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -4343541912028349778L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		IntegratedMeshLayer layer = new IntegratedMeshLayer();
		layer.setId("vricon");
		layer.setTitle("VRICON Mesh Layer");
		layer.setUrl("https://tiles.arcgis.com/tiles/FQD0rKU8X5sAQfh8/arcgis/rest/services/VRICON_Yosemite_Sample_Integrated_Mesh_scene_layer/SceneServer");

		// Initialize the map view.
		this.getModel().setBasemap(Basemap.LIGHTGRAY);
		this.getModel().getViewpoint().setLatitude(37.729935);
		this.getModel().getViewpoint().setLongitude(-119.605251);
		this.getModel().getViewpoint().setZoom(14);
		this.getModel().getLayers().add(layer);
	}
}
