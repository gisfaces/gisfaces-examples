package com.gisfaces.examples.edit;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

import com.gisfaces.event.MapGraphicCreateEvent;
import com.gisfaces.event.MapGraphicDeleteEvent;
import com.gisfaces.event.MapGraphicUpdateEvent;
import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.GraphicsLayer;
import com.gisfaces.model.map.MapType;
import com.gisfaces.utilities.JSFUtilities;

@Named
@SessionScoped
public class GraphicsLayerEditView extends MapView implements Serializable
{
	private static final long serialVersionUID = 875780238824377582L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the graphics layer.
		GraphicsLayer layer = new GraphicsLayer();
		layer.setId("graphics");
		layer.setEditable(true);
		layer.setTitle("Graphics Layer Edit");

		// Initialize the map view.
		this.getModel().setMapType(MapType.TWO_D);
		this.getModel().getViewpoint().setLatitude(39.828175);
		this.getModel().getViewpoint().setLongitude(-98.5795);
		this.getModel().getViewpoint().setZoom(4);
		this.getModel().getLayers().add(layer);
	}

	public void doMapGraphicCreateListener(AjaxBehaviorEvent event)
	{
		MapGraphicCreateEvent e = (MapGraphicCreateEvent) event;

		String summary = "Map Graphic Created";
		String detail = String.format("Layer ID='%s', Graphic ID='%s', Geometry='%s'.", e.getLayerId(), e.getGraphicId(), e.getGeometry());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGraphicUpdateListener(AjaxBehaviorEvent event)
	{
		MapGraphicUpdateEvent e = (MapGraphicUpdateEvent) event;

		String summary = "Map Graphic Updated";
		String detail = String.format("Layer ID='%s', Graphic ID='%s', Geometry='%s'.", e.getLayerId(), e.getGraphicId(), e.getGeometry());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}

	public void doMapGraphicDeleteListener(AjaxBehaviorEvent event)
	{
		MapGraphicDeleteEvent e = (MapGraphicDeleteEvent) event;

		String summary = "Map Graphic Deleted";
		String detail = String.format("Layer ID='%s', Graphic ID='%s'.", e.getLayerId(), e.getGraphicId());

		System.out.println(String.format("%s: %s", summary, detail));
		JSFUtilities.addInfoMessage(summary, detail);
	}
}
