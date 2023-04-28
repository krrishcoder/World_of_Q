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

/** This is an auto generated class representing the Topic type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Topics", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Topic implements Model {
  public static final QueryField ID = field("Topic", "id");
  public static final QueryField TOPIC_NAME = field("Topic", "topic_name");
  public static final QueryField TIMER = field("Topic", "timer");
  public static final QueryField TOPIC_SUBJECT_ID = field("Topic", "topicSubjectId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String topic_name;
  private final @ModelField(targetType="Int") Integer timer;
  private final @ModelField(targetType="Subject") @HasOne(associatedWith = "id", type = Subject.class) Subject Subject = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String topicSubjectId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTopicName() {
      return topic_name;
  }
  
  public Integer getTimer() {
      return timer;
  }
  
  public Subject getSubject() {
      return Subject;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getTopicSubjectId() {
      return topicSubjectId;
  }
  
  private Topic(String id, String topic_name, Integer timer, String topicSubjectId) {
    this.id = id;
    this.topic_name = topic_name;
    this.timer = timer;
    this.topicSubjectId = topicSubjectId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Topic topic = (Topic) obj;
      return ObjectsCompat.equals(getId(), topic.getId()) &&
              ObjectsCompat.equals(getTopicName(), topic.getTopicName()) &&
              ObjectsCompat.equals(getTimer(), topic.getTimer()) &&
              ObjectsCompat.equals(getCreatedAt(), topic.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), topic.getUpdatedAt()) &&
              ObjectsCompat.equals(getTopicSubjectId(), topic.getTopicSubjectId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTopicName())
      .append(getTimer())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getTopicSubjectId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Topic {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("topic_name=" + String.valueOf(getTopicName()) + ", ")
      .append("timer=" + String.valueOf(getTimer()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("topicSubjectId=" + String.valueOf(getTopicSubjectId()))
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
  public static Topic justId(String id) {
    return new Topic(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      topic_name,
      timer,
      topicSubjectId);
  }
  public interface BuildStep {
    Topic build();
    BuildStep id(String id);
    BuildStep topicName(String topicName);
    BuildStep timer(Integer timer);
    BuildStep topicSubjectId(String topicSubjectId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String topic_name;
    private Integer timer;
    private String topicSubjectId;
    @Override
     public Topic build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Topic(
          id,
          topic_name,
          timer,
          topicSubjectId);
    }
    
    @Override
     public BuildStep topicName(String topicName) {
        this.topic_name = topicName;
        return this;
    }
    
    @Override
     public BuildStep timer(Integer timer) {
        this.timer = timer;
        return this;
    }
    
    @Override
     public BuildStep topicSubjectId(String topicSubjectId) {
        this.topicSubjectId = topicSubjectId;
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
    private CopyOfBuilder(String id, String topicName, Integer timer, String topicSubjectId) {
      super.id(id);
      super.topicName(topicName)
        .timer(timer)
        .topicSubjectId(topicSubjectId);
    }
    
    @Override
     public CopyOfBuilder topicName(String topicName) {
      return (CopyOfBuilder) super.topicName(topicName);
    }
    
    @Override
     public CopyOfBuilder timer(Integer timer) {
      return (CopyOfBuilder) super.timer(timer);
    }
    
    @Override
     public CopyOfBuilder topicSubjectId(String topicSubjectId) {
      return (CopyOfBuilder) super.topicSubjectId(topicSubjectId);
    }
  }
  
}
