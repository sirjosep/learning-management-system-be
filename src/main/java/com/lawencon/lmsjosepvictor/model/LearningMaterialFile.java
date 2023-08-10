package com.lawencon.lmsjosepvictor.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_learning_material_file")
public class LearningMaterialFile extends Base{
	@ManyToOne
	@JoinColumn(name = "learning_material_id", nullable = false)
	private LearningMaterial learningMaterial;

	@ManyToOne
	@JoinColumn(name = "file_material_id", nullable = false)
	private File fileMaterial;

	public LearningMaterial getLearningMaterial() {
		return learningMaterial;
	}

	public void setLearningMaterial(LearningMaterial learningMaterial) {
		this.learningMaterial = learningMaterial;
	}

	public File getFileMaterial() {
		return fileMaterial;
	}

	public void setFileMaterial(File fileMaterial) {
		this.fileMaterial = fileMaterial;
	}

}
