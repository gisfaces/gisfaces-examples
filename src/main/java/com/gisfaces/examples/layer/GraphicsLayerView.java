package com.gisfaces.examples.layer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.net.ssl.HttpsURLConnection;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.geometry.Point;
import com.gisfaces.model.graphic.Graphic;
import com.gisfaces.model.layer.GraphicsLayer;
import com.gisfaces.model.map.PopupTemplate;
import com.gisfaces.model.symbol.PictureMarkerSymbol;
import com.gisfaces.utilities.JSFUtilities;

@Named
@SessionScoped
public class GraphicsLayerView extends MapView implements Serializable
{
	private static final long serialVersionUID = -2348588565360025570L;

	@PostConstruct
	public void init()
	{
		super.init();

		// Build the graphics layer.
		GraphicsLayer layer = new GraphicsLayer();
		layer.setId("databuoy");
		layer.setTitle("NOAA Data Buoy Graphics Layer");
		layer.setGraphics(this.getGraphics());

		// Initialize the map view.
		this.setLatitude(39.828175);
		this.setLongitude(-98.5795);
		this.setZoom(4);
		this.getModel().getLayers().add(layer);
	}

	public List<Graphic> getGraphics()
	{
		List<Graphic> graphics = null;

		try
		{
			// Get the raw buoy data.
			List<String> data = this.getUrlConnectionOutput("https://www.ndbc.noaa.gov/data/latest_obs/latest_obs.txt");

			if ((data != null) && (!data.isEmpty()))
			{
				// Create models from the raw data.
				graphics = this.createGraphics(data);
			}
		}
		catch (IOException e)
		{
			JSFUtilities.addErrorMessage("An error occurred reading raw buoy data.", e.getMessage());
		}

		return graphics;
	}

	private List<String> getUrlConnectionOutput(String spec) throws IOException
	{
		List<String> list = new ArrayList<String>();

		// Open the url connection.
		URL url = new URL(spec);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		// Parse url response per line.
		String line;
		while ((line = reader.readLine()) != null)
		{
			list.add(line);
		}

		reader.close();

		return list;
	}

	private List<Graphic> createGraphics(List<String> data)
	{
		List<Graphic> graphics = null;

		if ((data != null) && (!data.isEmpty()))
		{
			graphics = new ArrayList<Graphic>();

			for (String line : data)
			{
				// Check for comment line.
				if (!line.startsWith("#"))
				{
					// Preprocess the raw data line into a standardized format.
					line = line.replaceAll("\\s+", "|").replaceAll("MM", "");

					try
					{
						graphics.add(this.createGraphic(line));
					}
					catch (Exception e)
					{
						JSFUtilities.addErrorMessage("An error occurred creating buoy graphic.", e.getMessage());
					}
				}
			}
		}

		return graphics;
	}

	private Graphic createGraphic(String data) throws NullPointerException, IllegalArgumentException, ParseException
	{
		// Tokenize the data string.
		String[] tokens = data.split("\\|", -1);

		// Build the graphic attributes.
		Map<String, Object> attributes = new LinkedHashMap<String, Object>();
		attributes.put("Station ID", tokens[0]);
		attributes.put("Latitude", tokens[1]);
		attributes.put("Longitude", tokens[2]);
		attributes.put("Date", String.format("%s/%s/%s %s:%s", tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
		attributes.put("Wind Origination", tokens[8]);
		attributes.put("Wind Speed", tokens[9]);
		attributes.put("Wind Gust", tokens[10]);
		attributes.put("Significant Wave Height", tokens[11]); 
		attributes.put("Dominant Wave Period", tokens[12]);
		attributes.put("Average Wave Period", tokens[13]);
		attributes.put("Wave Direction", tokens[14]);
		attributes.put("Sea Level Pressure", tokens[15]);
		attributes.put("Pressure Tendency", tokens[16]);
		attributes.put("Air Temperature", tokens[17]);
		attributes.put("Sea Surface Temperature", tokens[18]);
		attributes.put("Dewpoint Temperature", tokens[19]);
		attributes.put("Visibility", tokens[20]);
		attributes.put("Tide", tokens[21]);

		//SimpleMarkerSymbol symbol = new SimpleMarkerSymbol();
		//symbol.setPath(SimpleMarkerSymbolPath.ARROW.toString());
		//symbol.setAngle(Integer.parseInt(tokens[8]));
		//symbol.setColor(new Color("#FFFFFF"));
		//symbol.setSize("12px");

		//SimpleMarkerSymbol symbol = new SimpleMarkerSymbol();
		//symbol.setStyle(SimpleMarkerSymbolStyle.TRIANGLE.toString());
		//symbol.setAngle(Integer.parseInt(tokens[8]));
		//symbol.setColor(new Color("#FFA500"));
		//symbol.setSize("12px");

		PictureMarkerSymbol symbol = new PictureMarkerSymbol();
		symbol.setUrl("http://static.arcgis.com/images/Symbols/Cartographic/esriCartographyMarker_37.png");
		symbol.setAngle(Integer.parseInt(tokens[8]));

		// Build the graphic.
		Graphic graphic = new Graphic();
		graphic.setId(tokens[0]);
		graphic.setGeometry(new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
		graphic.setSymbol(symbol);
		graphic.setPopupTemplate(new PopupTemplate("Data Bouy " + tokens[0]));
		graphic.setAttributes(attributes);

		return graphic;
	}
}
