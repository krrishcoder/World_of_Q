package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Semester type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Semesters", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byDegree", fields = {"degreeID"})
public final class Semester implements Model {
  public static final QueryField ID = field("Semester", "id");
  public static final QueryField SEMNO = field("Semester", "semno");
  public static final QueryField DEGREE_ID = field("Semester", "degreeID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int") Integer semno;
  private final @ModelField(targetType="ID", isRequired = true) String degreeID;
  private final @ModelField(targetType="Subject") @HasMany(associatedWith = "semesterID", type = Subject.class) List<Subject> Subjects = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Integer getSemno() {
      return semno;
  }
  
  public String getDegreeId() {
      return degreeID;
  }
  
  public List<Subject> getSubjects() {
      return Subjects;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Semester(String id, Integer semno, String degreeID) {
    this.id = id;
    this.semno = semno;
    this.degreeID = degreeID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Semester semester = (Semester) obj;
      return ObjectsCompat.equals(getId(), semester.getId()) &&
              ObjectsCompat.equals(getSemno(), semester.getSemno()) &&
              ObjectsCompat.equals(getDegreeId(), semester.getDegreeId()) &&
              ObjectsCompat.equals(getCreatedAt(), semester.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), semester.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getSemno())
      .append(getDegreeId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Semester {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("semno=" + String.valueOf(getSemno()) + ", ")
      .append("degreeID=" + String.valueOf(getDegreeId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DegreeIdStep builder() {
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
  public static Semester justId(String id) {
    return new Semester(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      semno,
      degreeID);
  }
  public interface DegreeIdStep {
    BuildStep degreeId(String degreeId);
  }
  

  public interface BuildStep {
    Semester build();
    BuildStep id(String id);
    BuildStep semno(Integer semno);
  }
  

  public static class Builder implements DegreeIdStep, BuildStep {
    private String id;
    private String degreeID;
    private Integer semno;
    @Override
     public Semester build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Semester(
          id,
          semno,
          degreeID);
    }
    
    @Override
     public BuildStep degreeId(String degreeId) {
        Objects.requireNonNull(degreeId);
        this.degreeID = degreeId;
        return this;
    }
    
    @Override
     public BuildStep semno(Integer semno) {
        this.semno = semno;
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
    private CopyOfBuilder(String id, Integer semno, String degreeId) {
      super.id(id);
      super.degreeId(degreeId)
        .semno(semno);
    }
    
    @Override
     public CopyOfBuilder degreeId(String degreeId) {
      return (CopyOfBuilder) super.degreeId(degreeId);
    }
    
    @Override
     public CopyOfBuilder semno(Integer semno) {
      return (CopyOfBuilder) super.semno(semno);
    }
  }
  
}
