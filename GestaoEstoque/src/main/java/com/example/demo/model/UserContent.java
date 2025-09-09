package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_conteudo")
@IdClass(UserContentId.class)
@Data
public class UserContent {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_conteudo")
    private EducationalContent content;

    @Column(name = "data_acesso")
    private LocalDateTime accessDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EducationalContent getContent() {
		return content;
	}

	public void setContent(EducationalContent content) {
		this.content = content;
	}

	public LocalDateTime getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(LocalDateTime accessDate) {
		this.accessDate = accessDate;
	}
    
    
}