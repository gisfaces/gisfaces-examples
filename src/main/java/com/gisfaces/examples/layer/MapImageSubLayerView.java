package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.MapImageLayer;
import com.gisfaces.model.layer.SubLayer;
import com.gisfaces.model.map.PopupTemplate;
import com.gisfaces.model.renderer.SimpleRenderer;
import com.gisfaces.model.symbol.SimpleFillSymbol;

@Named
@SessionScoped
public class MapImageSubLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 6929044495913502165L;

	private MapImageLayer layer;
	private SubLayer states;
	private SubLayer counties;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map sublayer.
		states = new SubLayer();
		states.setId("3");
		states.setTitle("Census State");
		states.setPopupTemplate(new PopupTemplate("State"));
		states.setDefinitionExpression("STATE_NAME='Florida'");
		states.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(128, 128, 128, 0.25d))));

		// Build a map sublayer.
		counties = new SubLayer();
		counties.setId("2");
		counties.setTitle("Census County");
		counties.setPopupTemplate(new PopupTemplate("County"));
		counties.setDefinitionExpression("STATE_NAME='Florida' AND NAME LIKE 'M%'");
		counties.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(255, 255, 128, 0.5d))));

		// Build a map layer.
		layer = new MapImageLayer("census", "https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer");
		layer.setTitle("Census Information");
		layer.getSublayers().add(states);
		layer.getSublayers().add(counties);

		// Initialize the map view.
		this.setLatitude(27.750145);
		this.setLongitude(-82.608625);
		this.setZoom(6);
		this.getModel().getLayers().add(layer);
	}

	public MapImageLayer getLayer()
	{
		return layer;
	}

	public void setLayer(MapImageLayer layer)
	{
		this.layer = layer;
	}

	public SubLayer getStates()
	{
		return states;
	}

	public void setStates(SubLayer states)
	{
		this.states = states;
	}

	public SubLayer getCounties()
	{
		return counties;
	}

	public void setCounties(SubLayer counties)
	{
		this.counties = counties;
	}
}
