package com.gisfaces.examples.transportation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.gisfaces.model.layer.MapImageLayer;

@Named
@SessionScoped
public class RailroadView extends ReferenceView implements Serializable
{
	private static final long serialVersionUID = -3883136962716915153L;

	private MapImageLayer rail;
	private MapImageLayer passenger;
	private MapImageLayer bridges;
	private MapImageLayer crossings;
	private MapImageLayer safety;
	private MapImageLayer mileposts;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the map layers.
		rail = new MapImageLayer("rail", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/MainLine/MapServer");
		passenger = new MapImageLayer("passenger", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/PassengerRail/MapServer");
		bridges = new MapImageLayer("bridges", "https://fragis.fra.dot.gov/fragis/rest/services/Safety/FRA_Bridges/MapServer");
		crossings = new MapImageLayer("crossings", "https://fragis.fra.dot.gov/fragis/rest/services/Safety/Safety_Crossings/MapServer");
		safety = new MapImageLayer("yards", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/FRAGradeXing/MapServer");
		mileposts = new MapImageLayer("mileposts", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/Mileposts/MapServer");

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(41.8);
		this.getModel().getViewpoint().setLongitude(-87.7);
		this.getModel().getViewpoint().setZoom(10);
		this.getModel().getLayers().add(rail);
		this.getModel().getLayers().add(passenger);
		this.getModel().getLayers().add(bridges);
		this.getModel().getLayers().add(crossings);
		this.getModel().getLayers().add(safety);
		this.getModel().getLayers().add(mileposts);
	}

	public MapImageLayer getRail()
	{
		return rail;
	}

	public void setRail(MapImageLayer rail)
	{
		this.rail = rail;
	}

	public MapImageLayer getPassenger()
	{
		return passenger;
	}

	public void setPassenger(MapImageLayer passenger)
	{
		this.passenger = passenger;
	}

	public MapImageLayer getBridges()
	{
		return bridges;
	}

	public void setBridges(MapImageLayer bridges)
	{
		this.bridges = bridges;
	}

	public MapImageLayer getCrossings()
	{
		return crossings;
	}

	public void setCrossings(MapImageLayer crossings)
	{
		this.crossings = crossings;
	}

	public MapImageLayer getSafety()
	{
		return safety;
	}

	public void setSafety(MapImageLayer safety)
	{
		this.safety = safety;
	}

	public MapImageLayer getMileposts()
	{
		return mileposts;
	}

	public void setMileposts(MapImageLayer mileposts)
	{
		this.mileposts = mileposts;
	}
}
