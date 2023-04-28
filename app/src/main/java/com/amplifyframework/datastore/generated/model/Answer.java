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

/** This is an auto generated class representing the Answer type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Answers", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Answer implements Model {
  public static final QueryField ID = field("Answer", "id");
  public static final QueryField ANSWER_LIST = field("Answer", "answer_list");
  public static final QueryField ANSWER_TOPIC_ID = field("Answer", "answerTopicId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Topic") @HasOne(associatedWith = "id", type = Topic.class) Topic Topic = null;
  private final @ModelField(targetType="Int") List<Integer> answer_list;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String answerTopicId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Topic getTopic() {
      return Topic;
  }
  
  public List<Integer> getAnswerList() {
      return answer_list;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getAnswerTopicId() {
      return answerTopicId;
  }
  
  private Answer(String id, List<Integer> answer_list, String answerTopicId) {
    this.id = id;
    this.answer_list = answer_list;
    this.answerTopicId = answerTopicId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Answer answer = (Answer) obj;
      return ObjectsCompat.equals(getId(), answer.getId()) &&
              ObjectsCompat.equals(getAnswerList(), answer.getAnswerList()) &&
              ObjectsCompat.equals(getCreatedAt(), answer.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), answer.getUpdatedAt()) &&
              ObjectsCompat.equals(getAnswerTopicId(), answer.getAnswerTopicId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getAnswerList())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getAnswerTopicId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Answer {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("answer_list=" + String.valueOf(getAnswerList()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("answerTopicId=" + String.valueOf(getAnswerTopicId()))
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
  public static Answer justId(String id) {
    return new Answer(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      answer_list,
      answerTopicId);
  }
  public interface BuildStep {
    Answer build();
    BuildStep id(String id);
    BuildStep answerList(List<Integer> answerList);
    BuildStep answerTopicId(String answerTopicId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private List<Integer> answer_list;
    private String answerTopicId;
    @Override
     public Answer build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Answer(
          id,
          answer_list,
          answerTopicId);
    }
    
    @Override
     public BuildStep answerList(List<Integer> answerList) {
        this.answer_list = answerList;
        return this;
    }
    
    @Override
     public BuildStep answerTopicId(String answerTopicId) {
        this.answerTopicId = answerTopicId;
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
    private CopyOfBuilder(String id, List<Integer> answerList, String answerTopicId) {
      super.id(id);
      super.answerList(answerList)
        .answerTopicId(answerTopicId);
    }
    
    @Override
     public CopyOfBuilder answerList(List<Integer> answerList) {
      return (CopyOfBuilder) super.answerList(answerList);
    }
    
    @Override
     public CopyOfBuilder answerTopicId(String answerTopicId) {
      return (CopyOfBuilder) super.answerTopicId(answerTopicId);
    }
  }
  
}
