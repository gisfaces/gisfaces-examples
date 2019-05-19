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
	private String stateColor;
	private String countyColor;

	@PostConstruct
	public void init()
	{
		super.init();

		// Initialize the renderer colors.
		this.stateColor = "808080";
		this.countyColor = "FFFF80";

		// Build a map sublayer.
		this.states = new SubLayer();
		this.states.setId("3");
		this.states.setOpacity(0.5d);
		this.states.setTitle("Census State");
		this.states.setPopupTemplate(new PopupTemplate("State"));
		this.states.setDefinitionExpression("STATE_NAME='Florida'");
		this.states.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(this.stateColor))));

		// Build a map sublayer.
		this.counties = new SubLayer();
		this.counties.setId("2");
		this.counties.setOpacity(0.5d);
		this.counties.setTitle("Census County");
		this.counties.setPopupTemplate(new PopupTemplate("County"));
		this.counties.setDefinitionExpression("STATE_NAME='Florida' AND NAME LIKE 'M%'");
		this.counties.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(this.countyColor))));

		// Build a map layer.
		this.layer = new MapImageLayer("census", "https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer");
		this.layer.setTitle("Census Information");
		this.layer.getSublayers().add(states);
		this.layer.getSublayers().add(counties);

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(27.750145);
		this.getModel().getViewpoint().setLongitude(-82.608625);
		this.getModel().getViewpoint().setZoom(6);
		this.getModel().getLayers().add(layer);
	}

	public void doUpdateListener()
	{
		// Update the layer renderers.
		this.states.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(this.stateColor))));
		this.counties.setRenderer(new SimpleRenderer(new SimpleFillSymbol(new Color(this.countyColor))));
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

	public String getStateColor()
	{
		return stateColor;
	}

	public void setStateColor(String stateColor)
	{
		this.stateColor = stateColor;
	}

	public String getCountyColor()
	{
		return countyColor;
	}

	public void setCountyColor(String countyColor)
	{
		this.countyColor = countyColor;
	}
}
