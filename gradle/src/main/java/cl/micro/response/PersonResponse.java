package cl.micro.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonResponse{
    
    private Long id; //ID GENERADO
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime modifiedDate; // fecha de ultima modificación
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLogin; //fecha de ultima entrada
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime creationDate;//FECHA DE CREACIÓN
	private String token; //Token generado
	private boolean isactive; // Validación de activo
	
	public PersonResponse() {
		this.creationDate = LocalDateTime.now();
		this.lastLogin = this.creationDate;
		this.modifiedDate= this.creationDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
}
	
	
	
	
	


	