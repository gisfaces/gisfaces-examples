package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.GeoJSONLayer;
import com.gisfaces.model.map.PopupTemplate;
import com.gisfaces.model.symbol.LabelClass;
import com.gisfaces.model.symbol.LabelExpressionInfo;
import com.gisfaces.model.symbol.TextSymbol;

@Named
@SessionScoped
public class GeoJsonLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 6680927620368200395L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a symbol for the label.
		TextSymbol symbol = new TextSymbol();
		symbol.setColor(new Color("#008CFF"));
		symbol.setHaloColor(new Color("#000000"));
		symbol.setHaloSize("1px");

		// Build a label for the specified fields.
		LabelClass label = new LabelClass();
		label.setLabelExpressionInfo(new LabelExpressionInfo("$feature.mag"));
		label.setSymbol(symbol);

		// Build a map layer.
		GeoJSONLayer layer = new GeoJSONLayer();
		layer.setId("earthquakes");
		layer.setTitle("Earthquakes");
		layer.setCopyright("USGS Earthquakes");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("Earthquake {title}", "{*}"));
		layer.setUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson");
		layer.setLabelsVisible(true);
		layer.setLabelingInfo(new LabelClass[] { label } );

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(39.828175);
		this.getModel().getViewpoint().setLongitude(-98.5795);
		this.getModel().getViewpoint().setZoom(4);
		this.getModel().getLayers().add(layer);
	}
}
