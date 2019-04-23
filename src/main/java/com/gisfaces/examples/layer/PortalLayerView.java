package com.gisfaces.examples.layer;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.layer.PortalLayer;

@Named
@SessionScoped
public class PortalLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = 502161578676073476L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build a map layer.
		PortalLayer layer = new PortalLayer("leanings", "8444e275037549c1acab02d2626daaee");
		//PortalLayer layer = new PortalLayer("recreation", "6012738cd1c74582a5f98ea30ae9876f");

		// Initialize the map view.
		this.getModel().getLayers().add(layer);
	}
}
