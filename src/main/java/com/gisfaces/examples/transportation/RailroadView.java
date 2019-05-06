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
	private MapImageLayer yards;
	private MapImageLayer passenger;
	private MapImageLayer freight;
	private MapImageLayer bridges;
	private MapImageLayer crossings;
	private MapImageLayer mileposts;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the map layers.
		rail = new MapImageLayer("rail", "https://fragis.fra.dot.gov/fragis/rest/services/RailMainLine/MapServer");
		yards = new MapImageLayer("yards", "https://fragis.fra.dot.gov/fragis/rest/services/RailYards/MapServer");
		passenger = new MapImageLayer("passenger", "https://fragis.fra.dot.gov/fragis/rest/services/PassengerRail/MapServer");
		freight = new MapImageLayer("freight", "https://fragis.fra.dot.gov/fragis/rest/services/FreightStations/MapServer");
		bridges = new MapImageLayer("bridges", "https://fragis.fra.dot.gov/fragis/rest/services/SafetyBridges/MapServer");
		crossings = new MapImageLayer("crossings", "https://fragis.fra.dot.gov/fragis/rest/services/FRAGradeXing/MapServer");
		mileposts = new MapImageLayer("mileposts", "https://fragis.fra.dot.gov/fragis/rest/services/Mileposts/MapServer");

		// Initialize the map view.
		this.setLatitude(41.8);
		this.setLongitude(-87.7);
		this.setZoom(10);
		this.getModel().getLayers().add(rail);
		this.getModel().getLayers().add(yards);
		this.getModel().getLayers().add(passenger);
		this.getModel().getLayers().add(freight);
		this.getModel().getLayers().add(bridges);
		this.getModel().getLayers().add(crossings);
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

	public MapImageLayer getYards()
	{
		return yards;
	}

	public void setYards(MapImageLayer yards)
	{
		this.yards = yards;
	}

	public MapImageLayer getPassenger()
	{
		return passenger;
	}

	public void setPassenger(MapImageLayer passenger)
	{
		this.passenger = passenger;
	}

	public MapImageLayer getFreight()
	{
		return freight;
	}

	public void setFreight(MapImageLayer freight)
	{
		this.freight = freight;
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

	public MapImageLayer getMileposts()
	{
		return mileposts;
	}

	public void setMileposts(MapImageLayer mileposts)
	{
		this.mileposts = mileposts;
	}
}
