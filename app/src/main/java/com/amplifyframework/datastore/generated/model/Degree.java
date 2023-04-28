package com.amplifyframework.datastore.generated.model;

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

/** This is an auto generated class representing the Degree type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Degrees", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Degree implements Model {
  public static final QueryField ID = field("Degree", "id");
  public static final QueryField DNAME = field("Degree", "dname");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String dname;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getDname() {
      return dname;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Degree(String id, String dname) {
    this.id = id;
    this.dname = dname;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Degree degree = (Degree) obj;
      return ObjectsCompat.equals(getId(), degree.getId()) &&
              ObjectsCompat.equals(getDname(), degree.getDname()) &&
              ObjectsCompat.equals(getCreatedAt(), degree.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), degree.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDname())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Degree {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("dname=" + String.valueOf(getDname()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DnameStep builder() {
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
  public static Degree justId(String id) {
    return new Degree(
      id,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      dname);
  }
  public interface DnameStep {
    BuildStep dname(String dname);
  }
  

  public interface BuildStep {
    Degree build();
    BuildStep id(String id);
  }
  

  public static class Builder implements DnameStep, BuildStep {
    private String id;
    private String dname;
    @Override
     public Degree build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Degree(
          id,
          dname);
    }
    
    @Override
     public BuildStep dname(String dname) {
        Objects.requireNonNull(dname);
        this.dname = dname;
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
    private CopyOfBuilder(String id, String dname) {
      super.id(id);
      super.dname(dname);
    }
    
    @Override
     public CopyOfBuilder dname(String dname) {
      return (CopyOfBuilder) super.dname(dname);
    }
  }
  
}
