package cl.micro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import cl.micro.config.JwtTokenProvider;
import cl.micro.domain.Person;
import cl.micro.domain.Phone;
import cl.micro.exception.ServiceException;
import cl.micro.repository.PersonRepository;
import cl.micro.repository.PhoneRepository;
import cl.micro.response.PersonResponse;
 
@Service
public class PersonService {
     

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);  
    
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(.*[AZ].*)(?=\\w*[a-z])(?=.*\\d{2,})\\S{4,}$", Pattern.CASE_INSENSITIVE);  

  
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    PersonRepository repository;
    
    @Autowired
    PhoneRepository repositoryPhone;
     
    public List<Person> getAllPersons()
    {
        List<Person> personList = repository.findAll();
        if(personList.size() > 0) {
            return personList;
        } else {
            return new ArrayList<Person>();
        }
    }
     


    public PersonResponse createPerson(Person entity)
    {	
    	Person p = repository.findByEmail(entity.getEmail());
    	
        //validación de correo repetido    	
    	if(p!=null) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"El email ya se encuetran ingresado en el sistema.");
    	}
        //validación de password
    	if(!validarPassword(entity.getPassword())) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Error en el formato del password.");
    	}
    	//validacion de formato de correo
    	if(!validarMail(entity.getEmail())) {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Error en formato de email.");
    	}
    	    	    
    	    //validar email, nombre y password 	
    		Person newEntity = new Person();
            newEntity.setEmail(entity.getEmail());
            newEntity.setName(entity.getName());
            newEntity.setPassword(entity.getPassword());
            String token= crearToken(entity);
            newEntity.setToken(token);
            newEntity = repository.save(newEntity);
            
            PersonResponse response= new PersonResponse();
            // Almacenamos los telefones de una persona
            if(entity.getPhones()!=null) {
            	int largo = entity.getPhones().size();
            	List <Phone> lista = entity.getPhones();
            	for(int i=0;i<largo;i++) {
            		Phone tel= new Phone();
            		tel.setCitycode(lista.get(i).getCitycode());
            		tel.setCountrycode(lista.get(i).getCountrycode());
            		tel.setNumber(lista.get(i).getNumber());
            		tel.setOwner(newEntity.getId());
            		repositoryPhone.save(tel);
            	}
            }   
            
            //Hacemos la validación del token por el proveedor.
            boolean validaToken =jwtTokenProvider.validateToken(token);
            response.setId(newEntity.getId());
            response.setToken(token);
            response.setIsactive(validaToken);

            return response;
            
    }
    

    
    public static boolean validarPassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX .matcher(password);
        return matcher.find();
    }
    
    public static boolean validarMail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    
    public String crearToken(Person per) {
  
        try {
            String name = per.getName();
            List<String> roles= new ArrayList<String>(); // Colocamos los roles por defecto solo para esta prueba
            roles.add("ADMIN");
            roles.add("USER");
            String token = jwtTokenProvider.createToken(name, roles);
            return token;
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Person");
        }
    }
    
    
    public Person getPersonById(Long id)
    {
        Optional<Person> employee = repository.findById(id); 
        if(employee.isPresent()) {
            return employee.get();
        } else {
    		throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"No existe persona con ese id.");
        }
    }

    

}