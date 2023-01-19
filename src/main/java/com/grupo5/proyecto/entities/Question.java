package com.grupo5.proyecto.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "trivial")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpreguntas")
	private int quesId;
	@Column(name = "pregunta")
	private String title;
	@Column(name = "opcionA")
	private String optionA;
	@Column(name = "opcionB")
	private String optionB;
	@Column(name = "opcionC")
	private String optionC;
	@Column(name = "respuesta")
	private int ans;

	public Question() {
		super();
	}

	public Question(int quesId, String title, String optionA, String optionB, String optionC, int ans) {
		super();
		this.quesId = quesId;
		this.title = title;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.ans = ans;
	}

	public int getQuesId() {
		return quesId;
	}

	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public int getAns() {
		return ans;
	}

	public void setAns(int ans) {
		this.ans = ans;
	}

	@Override
	public String toString() {
		return "Question{" +
				"quesId=" + quesId +
				", title='" + title + '\'' +
				", optionA='" + optionA + '\'' +
				", optionB='" + optionB + '\'' +
				", optionC='" + optionC + '\'' +
				", ans=" + ans +
				'}';
	}
}
