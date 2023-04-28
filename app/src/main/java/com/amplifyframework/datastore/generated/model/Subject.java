package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasOne;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Subject type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Subjects", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Subject implements Model {
  public static final QueryField ID = field("Subject", "id");
  public static final QueryField SUB_NAME = field("Subject", "sub_name");
  public static final QueryField SEM_NO = field("Subject", "sem_no");
  public static final QueryField SUBJECT_DEGREE_ID = field("Subject", "subjectDegreeId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String sub_name;
  private final @ModelField(targetType="Int") Integer sem_no;
  private final @ModelField(targetType="Degree") @HasOne(associatedWith = "id", type = Degree.class) Degree Degree = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String subjectDegreeId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getSubName() {
      return sub_name;
  }
  
  public Integer getSemNo() {
      return sem_no;
  }
  
  public Degree getDegree() {
      return Degree;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getSubjectDegreeId() {
      return subjectDegreeId;
  }
  
  private Subject(String id, String sub_name, Integer sem_no, String subjectDegreeId) {
    this.id = id;
    this.sub_name = sub_name;
    this.sem_no = sem_no;
    this.subjectDegreeId = subjectDegreeId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Subject subject = (Subject) obj;
      return ObjectsCompat.equals(getId(), subject.getId()) &&
              ObjectsCompat.equals(getSubName(), subject.getSubName()) &&
              ObjectsCompat.equals(getSemNo(), subject.getSemNo()) &&
              ObjectsCompat.equals(getCreatedAt(), subject.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), subject.getUpdatedAt()) &&
              ObjectsCompat.equals(getSubjectDegreeId(), subject.getSubjectDegreeId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getSubName())
      .append(getSemNo())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getSubjectDegreeId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Subject {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("sub_name=" + String.valueOf(getSubName()) + ", ")
      .append("sem_no=" + String.valueOf(getSemNo()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("subjectDegreeId=" + String.valueOf(getSubjectDegreeId()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Subject justId(String id) {
    return new Subject(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      sub_name,
      sem_no,
      subjectDegreeId);
  }
  public interface BuildStep {
    Subject build();
    BuildStep id(String id);
    BuildStep subName(String subName);
    BuildStep semNo(Integer semNo);
    BuildStep subjectDegreeId(String subjectDegreeId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String sub_name;
    private Integer sem_no;
    private String subjectDegreeId;
    @Override
     public Subject build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Subject(
          id,
          sub_name,
          sem_no,
          subjectDegreeId);
    }
    
    @Override
     public BuildStep subName(String subName) {
        this.sub_name = subName;
        return this;
    }
    
    @Override
     public BuildStep semNo(Integer semNo) {
        this.sem_no = semNo;
        return this;
    }
    
    @Override
     public BuildStep subjectDegreeId(String subjectDegreeId) {
        this.subjectDegreeId = subjectDegreeId;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String subName, Integer semNo, String subjectDegreeId) {
      super.id(id);
      super.subName(subName)
        .semNo(semNo)
        .subjectDegreeId(subjectDegreeId);
    }
    
    @Override
     public CopyOfBuilder subName(String subName) {
      return (CopyOfBuilder) super.subName(subName);
    }
    
    @Override
     public CopyOfBuilder semNo(Integer semNo) {
      return (CopyOfBuilder) super.semNo(semNo);
    }
    
    @Override
     public CopyOfBuilder subjectDegreeId(String subjectDegreeId) {
      return (CopyOfBuilder) super.subjectDegreeId(subjectDegreeId);
    }
  }
  
}
