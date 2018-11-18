package ca.happyfish.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import ca.happyfish.demo.model.HappyMessage;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {
    @Autowired
    private MessageController classUnderTest;
    @Autowired
    private MockMvc mvcMock;


    @Test
    public void tellMe_SimpleMessage() {
        final long MESSAGE_ID = 28;
    	final String MESSAGE = "Some message";

        //Arrange
    	HappyMessage message = new HappyMessage();
    	message.setId(MESSAGE_ID);
    	message.setMessage(MESSAGE);

    	//Act
    	HappyMessage actualReturnValue = classUnderTest.tellMe(message);

        //Assert
        assertNotNull("Return value is null.", actualReturnValue);
        assertEquals("ID", MESSAGE_ID, actualReturnValue.getId());
        assertEquals("Message", MESSAGE, actualReturnValue.getMessage());
    }

    @Test
    public void tellMe_UrlCall() throws Exception {
    	final String MESSAGE = "Some message";
        //Act
        ResultActions result = mvcMock.perform(get("/test").contentType(MediaType.APPLICATION_JSON).content("{\"message\": \""+ MESSAGE + "\"}"));

        //Assert
        result.andExpect(status().isOk())
              .andExpect(jsonPath("@.message").value(MESSAGE));
    }
}
