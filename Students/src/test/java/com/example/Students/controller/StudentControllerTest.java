package com.example.Students.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Students.model.Student;
import com.example.Students.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.example.Students.exception.BadArgumentsException;
import com.example.Students.exception.InternalException;
import com.example.Students.exception.ResourceNotFoundException;


@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {
	
	@MockBean
	private StudentService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllStudentsAPI() throws Exception 
    {
    	Student s1= new Student(1l,"test","test@gmail.com","55",89);
    	Student s2= new Student(2l,"test2","test2@gmail.com","525",829);
    	doReturn(Lists.newArrayList(s1,s2)).when(service).getAllStudents();
    	
      mvc.perform( 
          get("/api/getStudents")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(jsonPath("$[0].name", is("test")))
          .andExpect(jsonPath("$[1].name", is("test2")));
    }
    
    @Test
    public void getStudentByIdAPI() throws Exception 
    {
    	Student s1= new Student(1l,"test","test@gmail.com","55",89);
    	doReturn(Optional.of(s1)).when(service).findById(1l);
    	
    	
    	mvc.perform( 
    	          get("/api/getid/{id}", 1L)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andDo(print())
    	          .andExpect(status().isOk())
    	          .andExpect(jsonPath("$.id", is(1)))
    	          .andExpect(jsonPath("$.name", is("test")));
    }
    
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    void testSaveStudent() throws Exception {
    	Student StudentPost = new Student(1l,"test","test@gmail.com","55",89);
    	Student StudentReturn = new Student(1l,"test","test@gmail.com","55",89);
        doReturn(StudentReturn).when(service).saveStudent(any());

        mvc.perform(post("/api/saveStudent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(StudentPost)))
        		.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.score", is("55")))
                .andExpect(jsonPath("$.age", is(89)));
    }
    
    @Test
    void testUpdateStudent() throws Exception {
        Student StudentToPut = new Student(1l,"test","test@gmail.com","55",100);
        Student StudentToReturnFindBy = new Student(1l,"test","test@gmail.com","55",89);
        Student StudentToReturnSave = new Student(1l,"test","test@gmail.com","55",100);
        doReturn(Optional.of(StudentToReturnFindBy)).when(service).findById(1l);
        doReturn(StudentToReturnSave).when(service).saveStudent(any());

        mvc.perform(put("/api/put/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(StudentToPut))
                .accept(MediaType.APPLICATION_JSON))
        
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.age", is(100)));
    }
    
    @Test
    public void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
        String exceptionParam = "not_found";

        mvc.perform(get("/api/exception/{exception_id}", exceptionParam)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isNotFound())
          .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
          .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
    }
    
    @Test
    public void givenBadArguments_whenGetSpecificException_thenBadRequest() throws Exception {
        String exceptionParam = "bad_arguments";

        mvc.perform(get("/api/exception/{exception_id}", exceptionParam)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isBadRequest())
          .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentsException))
          .andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()));
    }
    
    @Test
    public void givenOther_whenGetSpecificException_thenInternalServerError() throws Exception {
        String exceptionParam = "error";

        mvc.perform(get("/api/exception/{exception_id}", exceptionParam)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isInternalServerError())
          .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalException))
          .andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()));
    }


    
    

}
