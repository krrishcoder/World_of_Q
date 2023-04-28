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

/** This is an auto generated class representing the Question type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Questions", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Question implements Model {
  public static final QueryField ID = field("Question", "id");
  public static final QueryField Q_DES = field("Question", "q_des");
  public static final QueryField OPTION1 = field("Question", "option1");
  public static final QueryField OPTION2 = field("Question", "option2");
  public static final QueryField OPTION3 = field("Question", "option3");
  public static final QueryField OPTION4 = field("Question", "option4");
  public static final QueryField QUESTION_TOPIC_ID = field("Question", "questionTopicId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String q_des;
  private final @ModelField(targetType="Topic") @HasOne(associatedWith = "id", type = Topic.class) Topic Topic = null;
  private final @ModelField(targetType="String") String option1;
  private final @ModelField(targetType="String") String option2;
  private final @ModelField(targetType="String") String option3;
  private final @ModelField(targetType="String") String option4;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String questionTopicId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getQDes() {
      return q_des;
  }
  
  public Topic getTopic() {
      return Topic;
  }
  
  public String getOption1() {
      return option1;
  }
  
  public String getOption2() {
      return option2;
  }
  
  public String getOption3() {
      return option3;
  }
  
  public String getOption4() {
      return option4;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getQuestionTopicId() {
      return questionTopicId;
  }
  
  private Question(String id, String q_des, String option1, String option2, String option3, String option4, String questionTopicId) {
    this.id = id;
    this.q_des = q_des;
    this.option1 = option1;
    this.option2 = option2;
    this.option3 = option3;
    this.option4 = option4;
    this.questionTopicId = questionTopicId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Question question = (Question) obj;
      return ObjectsCompat.equals(getId(), question.getId()) &&
              ObjectsCompat.equals(getQDes(), question.getQDes()) &&
              ObjectsCompat.equals(getOption1(), question.getOption1()) &&
              ObjectsCompat.equals(getOption2(), question.getOption2()) &&
              ObjectsCompat.equals(getOption3(), question.getOption3()) &&
              ObjectsCompat.equals(getOption4(), question.getOption4()) &&
              ObjectsCompat.equals(getCreatedAt(), question.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), question.getUpdatedAt()) &&
              ObjectsCompat.equals(getQuestionTopicId(), question.getQuestionTopicId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getQDes())
      .append(getOption1())
      .append(getOption2())
      .append(getOption3())
      .append(getOption4())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getQuestionTopicId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Question {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("q_des=" + String.valueOf(getQDes()) + ", ")
      .append("option1=" + String.valueOf(getOption1()) + ", ")
      .append("option2=" + String.valueOf(getOption2()) + ", ")
      .append("option3=" + String.valueOf(getOption3()) + ", ")
      .append("option4=" + String.valueOf(getOption4()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("questionTopicId=" + String.valueOf(getQuestionTopicId()))
      .append("}")
      .toString();
  }
  
  public static QDesStep builder() {
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
  public static Question justId(String id) {
    return new Question(
      id,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      q_des,
      option1,
      option2,
      option3,
      option4,
      questionTopicId);
  }
  public interface QDesStep {
    BuildStep qDes(String qDes);
  }
  

  public interface BuildStep {
    Question build();
    BuildStep id(String id);
    BuildStep option1(String option1);
    BuildStep option2(String option2);
    BuildStep option3(String option3);
    BuildStep option4(String option4);
    BuildStep questionTopicId(String questionTopicId);
  }
  

  public static class Builder implements QDesStep, BuildStep {
    private String id;
    private String q_des;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questionTopicId;
    @Override
     public Question build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Question(
          id,
          q_des,
          option1,
          option2,
          option3,
          option4,
          questionTopicId);
    }
    
    @Override
     public BuildStep qDes(String qDes) {
        Objects.requireNonNull(qDes);
        this.q_des = qDes;
        return this;
    }
    
    @Override
     public BuildStep option1(String option1) {
        this.option1 = option1;
        return this;
    }
    
    @Override
     public BuildStep option2(String option2) {
        this.option2 = option2;
        return this;
    }
    
    @Override
     public BuildStep option3(String option3) {
        this.option3 = option3;
        return this;
    }
    
    @Override
     public BuildStep option4(String option4) {
        this.option4 = option4;
        return this;
    }
    
    @Override
     public BuildStep questionTopicId(String questionTopicId) {
        this.questionTopicId = questionTopicId;
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
    private CopyOfBuilder(String id, String qDes, String option1, String option2, String option3, String option4, String questionTopicId) {
      super.id(id);
      super.qDes(qDes)
        .option1(option1)
        .option2(option2)
        .option3(option3)
        .option4(option4)
        .questionTopicId(questionTopicId);
    }
    
    @Override
     public CopyOfBuilder qDes(String qDes) {
      return (CopyOfBuilder) super.qDes(qDes);
    }
    
    @Override
     public CopyOfBuilder option1(String option1) {
      return (CopyOfBuilder) super.option1(option1);
    }
    
    @Override
     public CopyOfBuilder option2(String option2) {
      return (CopyOfBuilder) super.option2(option2);
    }
    
    @Override
     public CopyOfBuilder option3(String option3) {
      return (CopyOfBuilder) super.option3(option3);
    }
    
    @Override
     public CopyOfBuilder option4(String option4) {
      return (CopyOfBuilder) super.option4(option4);
    }
    
    @Override
     public CopyOfBuilder questionTopicId(String questionTopicId) {
      return (CopyOfBuilder) super.questionTopicId(questionTopicId);
    }
  }
  
}
