package ca.happyfish.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ca.happyfish.demo.model.HappyMessage;

@RestController
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger( MessageController.class );
    
    @RequestMapping("/test")
    @ResponseBody
    public HappyMessage tellMe(@RequestBody HappyMessage messageBody) {
    	logger.info("Test received a message \"{}\"", messageBody.getMessage());
    	return messageBody;
    }
}
