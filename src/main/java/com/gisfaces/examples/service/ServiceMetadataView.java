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

package com.gisfaces.examples.service;

import com.gisfaces.examples.map.MapView;
import com.gisfaces.model.service.ServiceDirectoryMetadata;
import com.gisfaces.model.service.ServiceDirectoryMetadataBuilder;
import com.gisfaces.utilities.JSFUtilities;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class ServiceMetadataView extends MapView implements Serializable {
	private static final long serialVersionUID = 9135707557221898353L;

	private String url;
	private ServiceDirectoryMetadata serviceDirectory;

	@PostConstruct
	public void init() {
		this.url = "http://sampleserver1.arcgisonline.com/ArcGIS/rest/services/PublicSafety";
		this.serviceDirectory = null;
	}

	public void doSearchButtonActionListener(ActionEvent event) {
		try {
			this.serviceDirectory = null;

			ServiceDirectoryMetadataBuilder builder = new ServiceDirectoryMetadataBuilder();
			this.serviceDirectory = builder.build(this.url);
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtilities.addErrorMessage("An error occurred querying map serivce.", e.getMessage());
		}
	}

	public void doResetButtonActionListener(ActionEvent event) {
		this.init();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ServiceDirectoryMetadata getServiceDirectory() {
		return serviceDirectory;
	}

	public void setServiceDirectory(ServiceDirectoryMetadata serviceDirectory) {
		this.serviceDirectory = serviceDirectory;
	}
}
