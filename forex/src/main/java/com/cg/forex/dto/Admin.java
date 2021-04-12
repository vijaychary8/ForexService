package com.cg.forex.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
@Data

@Entity
@Table(name="admin_table")
@JsonRootName("AdminInfo")
@JsonInclude (content = Include.NON_NULL)

public class Admin {
	
	public Admin() {
		
	}

		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		@Column(name="emailId")
		@JsonProperty
		private String email;
		
		@Column(name="FirstName")
		@JsonProperty
		private String firstName;
		
		@Column(name="LastName")
		@JsonProperty
		private String lastName;
		
		@Column(name="Password")
		@JsonProperty
		private String password;

		public Admin(int id, String email, String firstName, String lastName, String password) {
			super();
			this.id = id;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
		}
		
		
		

}
