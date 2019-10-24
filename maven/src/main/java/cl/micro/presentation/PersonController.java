package cl.micro.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.micro.domain.Person;
import cl.micro.response.PersonResponse;
import cl.micro.service.PersonService;
import io.swagger.annotations.ApiOperation;
 
@RestController
@RequestMapping("/persons")
public class PersonController
{
    @Autowired
    PersonService service;
 
    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> list = service.getAllPersons();
        return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") Long id)  {
    	Person entity = service.getPersonById(id);
        return new ResponseEntity<Person>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Endpoint para el ingreso de una persona")
    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonResponse> createOrUpdatePerson(@RequestBody Person person) {    	
    PersonResponse response = service.createPerson(person);
    return new ResponseEntity<PersonResponse>(response, new HttpHeaders(), HttpStatus.OK);
    }


    
}