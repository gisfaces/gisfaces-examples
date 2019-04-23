package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.PortalItem;
import com.gisfaces.model.layer.SceneLayer;
import com.gisfaces.model.map.Basemap;
import com.gisfaces.model.map.PopupTemplate;
import com.gisfaces.model.renderer.SimpleRenderer;
import com.gisfaces.model.symbol.ColorMaterial;
import com.gisfaces.model.symbol.FillSymbol3DLayer;
import com.gisfaces.model.symbol.MeshSymbol3D;

@Named
@SessionScoped
public class SceneLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -573497845346019169L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the symbol layer.
		FillSymbol3DLayer symbolLayer = new FillSymbol3DLayer();
		symbolLayer.setMaterial(new ColorMaterial(new Color(244, 247, 134, 0.5)));

		// Build the symbol.
		MeshSymbol3D symbol = new MeshSymbol3D();
		symbol.getSymbolLayers().add(symbolLayer);

		// Build the renderer.
		SimpleRenderer renderer = new SimpleRenderer();
		renderer.setSymbol(symbol);

		// Build a map layer.
		SceneLayer layer = new SceneLayer();
		layer.setId("nybuildings");
		layer.setTitle("NY Buildings");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("NY Building", "{*}"));
		layer.setPortalItem(new PortalItem("2e0761b9a4274b8db52c4bf34356911e"));
		layer.setRenderer(renderer);

		// Initialize the map view.
		this.setBasemap(Basemap.DARKGRAY.getValue());
		this.setLatitude(40.706222);
		this.setLongitude(-74.011483);
		this.setZoom(16);
		this.getModel().getLayers().add(layer);
	}
}
