package com.bennsandoval.api;

import com.bennsandoval.config.AppConfig;
import com.bennsandoval.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Mackbook on 7/25/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class TestBucketController {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testBucketList() throws Exception {
        mockMvc.perform(
            get("/api/v1/buckets")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testBucketCreate() throws Exception {

        mockMvc.perform(
                post("/api/v1/buckets/bennsandoval-bucket-test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("bennsandoval-bucket-test")));

        mockMvc.perform(
                post("/api/v1/buckets/bennsandoval-bucket-test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.text", startsWith("Internal error : You create a bucket")));

        mockMvc.perform(
                delete("/api/v1/buckets/bennsandoval-bucket-test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testUploadFileWrongBucket() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("file",
                "filename.txt",
                "text/plain",
                "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/buckets/bennsandoval-bucket-test-wrong/files")
                    .file(firstFile)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.text").exists());

    }

    @Test
    public void testUploadFile() throws Exception {

        mockMvc.perform(
            post("/api/v1/buckets/bennsandoval-bucket-test")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("bennsandoval-bucket-test")));

        MockMultipartFile firstFile = new MockMultipartFile("file",
                "filename.txt",
                "text/plain",
                "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/buckets/bennsandoval-bucket-test/files")
                .file(firstFile)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.file").exists());

        mockMvc.perform(
            get("/api/v1/buckets/bennsandoval-bucket-test/files/filename.txt/?redirect=false")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string("Location", nullValue()))
            .andExpect(jsonPath("$.text").exists());

        mockMvc.perform(
            get("/api/v1/buckets/bennsandoval-bucket-test/files/filename.txt/?redirect=true")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().string("Location", notNullValue()));

        mockMvc.perform(
            delete("/api/v1/buckets/bennsandoval-bucket-test/files/filename.txt/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());

        mockMvc.perform(
            delete("/api/v1/buckets/bennsandoval-bucket-test")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());

    }

}
