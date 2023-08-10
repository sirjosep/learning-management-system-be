package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_learning_material")
public class LearningMaterial extends Base {

	@Column(name = "learning_material_title", length = 50, nullable = false)
	private String learningMaterialTitle;

	@Column(name = "learning_material_body", nullable = false)
	private String learningMaterialBody;

	@ManyToOne
	@JoinColumn(name = "learning_id", nullable = false)
	private Learning learning;

	public String getLearningMaterialTitle() {
		return learningMaterialTitle;
	}

	public void setLearningMaterialTitle(String learningMaterialTitle) {
		this.learningMaterialTitle = learningMaterialTitle;
	}

	public String getLearningMaterialBody() {
		return learningMaterialBody;
	}

	public void setLearningMaterialBody(String learningMaterialBody) {
		this.learningMaterialBody = learningMaterialBody;
	}

	public Learning getLearning() {
		return learning;
	}

	public void setLearning(Learning learning) {
		this.learning = learning;
	}

}
