/*
 * The MIT License
 *
 * Copyright (c) 2013-2021 Chris Duncan (cduncan@gisfaces.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.gisfaces.examples.transportation;

import com.gisfaces.model.layer.MapImageLayer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class RailroadView extends ReferenceView implements Serializable {
	private static final long serialVersionUID = -3883136962716915153L;

	private MapImageLayer rail;
	private MapImageLayer stracnet;
	private MapImageLayer ptc;
	private MapImageLayer freight;
	private MapImageLayer passenger;
	private MapImageLayer bridges;
	private MapImageLayer crossings;
	private MapImageLayer safety;
	private MapImageLayer mileposts;

	@PostConstruct
	public void init() {
		super.init();

		// Build the map layers.
		rail = new MapImageLayer("rail", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/MainLine/MapServer");
		stracnet = new MapImageLayer("stracnet", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/STRACNET/MapServer");
		ptc = new MapImageLayer("ptc", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/PTC/MapServer");
		freight = new MapImageLayer("freight", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/Class1s/MapServer");
		passenger = new MapImageLayer("passenger", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/PassengerRail/MapServer");
		bridges = new MapImageLayer("bridges", "https://fragis.fra.dot.gov/fragis/rest/services/Safety/FRA_Bridges/MapServer");
		crossings = new MapImageLayer("crossings", "https://fragis.fra.dot.gov/fragis/rest/services/Safety/Safety_Crossings/MapServer");
		safety = new MapImageLayer("yards", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/FRAGradeX/MapServer");
		mileposts = new MapImageLayer("mileposts", "https://fragis.fra.dot.gov/fragis/rest/services/FRA/Mileposts/MapServer");

		// Set visibility off by default.
		stracnet.setVisible(false);
		ptc.setVisible(false);
		freight.setVisible(false);
		passenger.setVisible(false);

		// Initialize the map view.
		this.getModel().getViewpoint().setLatitude(41.8);
		this.getModel().getViewpoint().setLongitude(-87.7);
		this.getModel().getViewpoint().setZoom(10);
		this.getModel().getLayers().add(rail);
		this.getModel().getLayers().add(stracnet);
		this.getModel().getLayers().add(ptc);
		this.getModel().getLayers().add(freight);
		this.getModel().getLayers().add(passenger);
		this.getModel().getLayers().add(bridges);
		this.getModel().getLayers().add(crossings);
		this.getModel().getLayers().add(safety);
		this.getModel().getLayers().add(mileposts);
	}

	public MapImageLayer getRail() {
		return rail;
	}

	public void setRail(MapImageLayer rail) {
		this.rail = rail;
	}

	public MapImageLayer getStracnet() {
		return stracnet;
	}

	public void setStracnet(MapImageLayer stracnet) {
		this.stracnet = stracnet;
	}

	public MapImageLayer getPtc() {
		return ptc;
	}

	public void setPtc(MapImageLayer ptc) {
		this.ptc = ptc;
	}

	public MapImageLayer getFreight() {
		return freight;
	}

	public void setFreight(MapImageLayer freight) {
		this.freight = freight;
	}

	public MapImageLayer getPassenger() {
		return passenger;
	}

	public void setPassenger(MapImageLayer passenger) {
		this.passenger = passenger;
	}

	public MapImageLayer getBridges() {
		return bridges;
	}

	public void setBridges(MapImageLayer bridges) {
		this.bridges = bridges;
	}

	public MapImageLayer getCrossings() {
		return crossings;
	}

	public void setCrossings(MapImageLayer crossings) {
		this.crossings = crossings;
	}

	public MapImageLayer getSafety() {
		return safety;
	}

	public void setSafety(MapImageLayer safety) {
		this.safety = safety;
	}

	public MapImageLayer getMileposts() {
		return mileposts;
	}

	public void setMileposts(MapImageLayer mileposts) {
		this.mileposts = mileposts;
	}
}
