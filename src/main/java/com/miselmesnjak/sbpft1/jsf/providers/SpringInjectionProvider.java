package com.miselmesnjak.sbpft1.jsf.providers;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.faces.spi.InjectionProvider;
import com.sun.faces.spi.InjectionProviderException;
import com.sun.faces.vendor.WebContainerInjectionProvider;

public class SpringInjectionProvider implements InjectionProvider {

	private static final WebContainerInjectionProvider con = new WebContainerInjectionProvider();
	private static final Logger logger = LoggerFactory.getLogger(SpringInjectionProvider.class);
	
	public void inject(Object managedBean) throws InjectionProviderException {
		if(logger.isDebugEnabled()) {
			logger.debug("inject: " + managedBean.getClass());
		}
		con.inject(managedBean);
	}

	public void invokePostConstruct(Object managedBean)
			throws InjectionProviderException {
		if(logger.isDebugEnabled()) {
			logger.debug("invokePostConstruct: " + managedBean.getClass());
		}
		ServletContext context = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
		if(logger.isDebugEnabled()) {
			logger.debug("context: " + context);
		}
		WebApplicationContext wCtx = WebApplicationContextUtils.getWebApplicationContext(context);
		if(logger.isDebugEnabled()) {
			logger.debug("spring context: " + wCtx);
		}
		if(wCtx != null) {
			if(logger.isDebugEnabled()) {
				logger.debug("trying to autowire");
			}
			try {
				wCtx.getAutowireCapableBeanFactory().autowireBeanProperties(managedBean, AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);
			} catch(Exception ex)  {
				logger.error("Autowire exception", ex);
			}
		}
		con.invokePostConstruct(managedBean);
	}

	public void invokePreDestroy(Object managedBean) throws InjectionProviderException {
		if(logger.isDebugEnabled()) {
			logger.debug("invokePreDestroy: " + managedBean.getClass());
		}
		con.invokePreDestroy(managedBean);
	}
}