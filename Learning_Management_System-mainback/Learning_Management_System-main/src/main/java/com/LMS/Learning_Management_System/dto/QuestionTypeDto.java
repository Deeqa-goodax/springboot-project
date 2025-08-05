package com.LMS.Learning_Management_System.dto;

import com.LMS.Learning_Management_System.entity.QuestionType.QuestionTypeEnum;

public class QuestionTypeDto {
    private int typeId;
    private QuestionTypeEnum typeName;

    public QuestionTypeDto() {
    }

    public QuestionTypeDto(int typeId, QuestionTypeEnum typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public QuestionTypeEnum getTypeName() {
        return typeName;
    }

    public void setTypeName(QuestionTypeEnum typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "QuestionTypeDto{" +
                "typeId=" + typeId +
                ", typeName=" + typeName +
                '}';
    }
}
