package com.isika.prestigeacademy.model.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the note database table.
 * 
 */
@Entity
@NamedQuery(name="Note.findAll", query="SELECT n FROM Note n")
public class Note implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long noteID;

	private String contenuNote;

	public Note() {
	}

	public Long getNoteID() {
		return this.noteID;
	}

	public void setNoteID(Long noteID) {
		this.noteID = noteID;
	}

	public String getContenuNote() {
		return this.contenuNote;
	}

	public void setContenuNote(String contenuNote) {
		this.contenuNote = contenuNote;
	}

}