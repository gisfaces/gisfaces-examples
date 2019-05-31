package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.color.Color;
import com.gisfaces.model.layer.CSVLayer;
import com.gisfaces.model.map.PopupTemplate;
import com.gisfaces.model.symbol.LabelClass;
import com.gisfaces.model.symbol.LabelExpressionInfo;
import com.gisfaces.model.symbol.TextSymbol;

@Named
@SessionScoped
public class CsvLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -3856849571766278026L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a symbol for the label.
		TextSymbol symbol = new TextSymbol();
		symbol.setColor(new Color("#FF8C00"));
		symbol.setHaloColor(new Color("#808080"));
		symbol.setHaloSize("1px");

		// Build a label for the specified fields.
		LabelClass label = new LabelClass();
		label.setLabelExpressionInfo(new LabelExpressionInfo("$feature.mag"));
		label.setSymbol(symbol);

		// Build a map layer.
		CSVLayer layer = new CSVLayer();
		layer.setId("earthquakes");
		layer.setTitle("Earthquakes Last 24 Hours");
		layer.setCopyright("USGS Earthquakes");
		layer.setPopupEnabled(true);
		layer.setPopupTemplate(new PopupTemplate("Earthquake", "{*}"));
		layer.setUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.csv");
		layer.setLabelsVisible(true);
		layer.setLabelingInfo(new LabelClass[] { label } );

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
