package com.gisfaces.examples.transportation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.model.layer.MapImageLayer;
import com.gisfaces.model.map.Basemap;

@Named
@SessionScoped
public class MaritimeView extends ReferenceView implements Serializable
{
	private static final long serialVersionUID = 2224675449393427902L;

	private MapImageLayer waterways;
	private MapImageLayer ports;
	private MapImageLayer locks;
	private MapImageLayer dams;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the map layers.
		waterways = new MapImageLayer("waterways", "https://maps.bts.dot.gov/services/rest/services/NTAD/Navigable_Waterway_Lines/MapServer");
		ports = new MapImageLayer("ports", "https://maps.bts.dot.gov/services/rest/services/NTAD/Ports/MapServer");
		locks = new MapImageLayer("locks", "https://maps.bts.dot.gov/services/rest/services/NTAD/Locks/MapServer");
		dams = new MapImageLayer("dams", "https://maps.bts.dot.gov/services/rest/services/NTAD/Dams/MapServer");

		// Set the map basemap.
		this.getModel().setBasemap(Basemap.OCEANS);

		// Some services may require the included proxy to be enabled.
		this.getModel().getConfiguration().setProxyEnabled(true);

		// Add the map layers.
		this.getModel().getLayers().add(waterways);
		this.getModel().getLayers().add(ports);
		this.getModel().getLayers().add(locks);
		this.getModel().getLayers().add(dams);
	}

	public MapImageLayer getWaterways()
	{
		return waterways;
	}

	public void setWaterways(MapImageLayer waterways)
	{
		this.waterways = waterways;
	}

	public MapImageLayer getPorts()
	{
		return ports;
	}

	public void setPorts(MapImageLayer ports)
	{
		this.ports = ports;
	}

	public MapImageLayer getLocks()
	{
		return locks;
	}

	public void setLocks(MapImageLayer locks)
	{
		this.locks = locks;
	}

	public MapImageLayer getDams()
	{
		return dams;
	}

	public void setDams(MapImageLayer dams)
	{
		this.dams = dams;
	}
}
