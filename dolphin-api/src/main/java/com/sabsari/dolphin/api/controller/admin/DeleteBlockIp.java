package com.sabsari.dolphin.api.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sabsari.dolphin.api.annotation.Api;
import com.sabsari.dolphin.api.component.ResultFactory;
import com.sabsari.dolphin.api.model.ApiResult;
import com.sabsari.dolphin.api.model.request.DeleteBlockIpRequest;
import com.sabsari.dolphin.core.auth.domain.code.Role;
import com.sabsari.dolphin.core.exception.business.InvalidParameterException;
import com.sabsari.dolphin.core.web.security.blockip.BlockIpContainer;

@Controller
public class DeleteBlockIp {

	private final static Logger logger = LoggerFactory.getLogger(DeleteBlockIp.class);
	
	@Autowired
	private BlockIpContainer blockIpContainer;
	
	@Autowired
	private ResultFactory resultFactory;
	
	@Api(level=Role.ADMIN)
    @RequestMapping(value="/admin/blockip", method=RequestMethod.DELETE)
    @ResponseBody    
    public ApiResult deleteBlockIp(@RequestBody @Valid DeleteBlockIpRequest deleteBlockIpRequest,
    		BindingResult paramsResult, Locale locale) throws Exception {
		logger.debug("deleteBlockIp!");
		
		// params check
		List<ObjectError> paramErrors = paramsResult.getAllErrors();
		if (paramErrors.size() != 0) {
			String message = paramErrors.get(0).getDefaultMessage();
			logger.debug("Invalid parameter: " + message);
			throw new InvalidParameterException(message);
		}
		
		blockIpContainer.remove(deleteBlockIpRequest.getIpList());
    	
		return resultFactory.getSuccess(locale);
    }
}
