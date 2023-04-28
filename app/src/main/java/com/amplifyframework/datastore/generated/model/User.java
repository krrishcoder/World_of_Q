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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField USERNAME = field("User", "username");
  public static final QueryField ROLLNO = field("User", "rollno");
  public static final QueryField SEM_NO = field("User", "sem_no");
  public static final QueryField GMAIL_ID = field("User", "gmail_id");
  public static final QueryField USER_DEGREE_ID = field("User", "userDegreeId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String username;
  private final @ModelField(targetType="String", isRequired = true) String rollno;
  private final @ModelField(targetType="Degree") @HasOne(associatedWith = "id", type = Degree.class) Degree Degree = null;
  private final @ModelField(targetType="Int") Integer sem_no;
  private final @ModelField(targetType="String") String gmail_id;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String userDegreeId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getRollno() {
      return rollno;
  }
  
  public Degree getDegree() {
      return Degree;
  }
  
  public Integer getSemNo() {
      return sem_no;
  }
  
  public String getGmailId() {
      return gmail_id;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getUserDegreeId() {
      return userDegreeId;
  }
  
  private User(String id, String username, String rollno, Integer sem_no, String gmail_id, String userDegreeId) {
    this.id = id;
    this.username = username;
    this.rollno = rollno;
    this.sem_no = sem_no;
    this.gmail_id = gmail_id;
    this.userDegreeId = userDegreeId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getUsername(), user.getUsername()) &&
              ObjectsCompat.equals(getRollno(), user.getRollno()) &&
              ObjectsCompat.equals(getSemNo(), user.getSemNo()) &&
              ObjectsCompat.equals(getGmailId(), user.getGmailId()) &&
              ObjectsCompat.equals(getCreatedAt(), user.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), user.getUpdatedAt()) &&
              ObjectsCompat.equals(getUserDegreeId(), user.getUserDegreeId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getUsername())
      .append(getRollno())
      .append(getSemNo())
      .append(getGmailId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getUserDegreeId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("rollno=" + String.valueOf(getRollno()) + ", ")
      .append("sem_no=" + String.valueOf(getSemNo()) + ", ")
      .append("gmail_id=" + String.valueOf(getGmailId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("userDegreeId=" + String.valueOf(getUserDegreeId()))
      .append("}")
      .toString();
  }
  
  public static UsernameStep builder() {
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
  public static User justId(String id) {
    return new User(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      username,
      rollno,
      sem_no,
      gmail_id,
      userDegreeId);
  }
  public interface UsernameStep {
    RollnoStep username(String username);
  }
  

  public interface RollnoStep {
    BuildStep rollno(String rollno);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id);
    BuildStep semNo(Integer semNo);
    BuildStep gmailId(String gmailId);
    BuildStep userDegreeId(String userDegreeId);
  }
  

  public static class Builder implements UsernameStep, RollnoStep, BuildStep {
    private String id;
    private String username;
    private String rollno;
    private Integer sem_no;
    private String gmail_id;
    private String userDegreeId;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          username,
          rollno,
          sem_no,
          gmail_id,
          userDegreeId);
    }
    
    @Override
     public RollnoStep username(String username) {
        Objects.requireNonNull(username);
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep rollno(String rollno) {
        Objects.requireNonNull(rollno);
        this.rollno = rollno;
        return this;
    }
    
    @Override
     public BuildStep semNo(Integer semNo) {
        this.sem_no = semNo;
        return this;
    }
    
    @Override
     public BuildStep gmailId(String gmailId) {
        this.gmail_id = gmailId;
        return this;
    }
    
    @Override
     public BuildStep userDegreeId(String userDegreeId) {
        this.userDegreeId = userDegreeId;
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
    private CopyOfBuilder(String id, String username, String rollno, Integer semNo, String gmailId, String userDegreeId) {
      super.id(id);
      super.username(username)
        .rollno(rollno)
        .semNo(semNo)
        .gmailId(gmailId)
        .userDegreeId(userDegreeId);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder rollno(String rollno) {
      return (CopyOfBuilder) super.rollno(rollno);
    }
    
    @Override
     public CopyOfBuilder semNo(Integer semNo) {
      return (CopyOfBuilder) super.semNo(semNo);
    }
    
    @Override
     public CopyOfBuilder gmailId(String gmailId) {
      return (CopyOfBuilder) super.gmailId(gmailId);
    }
    
    @Override
     public CopyOfBuilder userDegreeId(String userDegreeId) {
      return (CopyOfBuilder) super.userDegreeId(userDegreeId);
    }
  }
  
}
