package com.miselmesnjak.sbpft1.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class MenuController implements Serializable {

	private static final long serialVersionUID = -1258136275137302754L;
	private static final Logger LOG = LoggerFactory
			.getLogger(MenuController.class);

	private static final String JSF_FILTER = "jsf-filter";
	private static final String SPRING_FILTER= "spring-filter";

	public MenuController() {
		LOG.debug("... MenuController no-args constructor ...");
	}
	
	private String pageId = "jsf-filter";

	@PostConstruct
	private void init() {
		LOG.debug("... init MenuController ...");
	}

	public void menuAction(String pageId) {

		LOG.debug("Set page:" + pageId);
		this.pageId = pageId;		
		refreshContent();
	}

	private void refreshContent() {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext ctx = FacesContext.getCurrentInstance();

		context.update(ctx.getViewRoot().findComponent(":pnlContent")
				.getClientId());
	}
	
	public void jsfFilter() {
		menuAction(JSF_FILTER);
	}

	public void springFilter() {
		menuAction(SPRING_FILTER);
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
}
