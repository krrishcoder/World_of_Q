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

/** This is an auto generated class representing the Result type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Results", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Result implements Model {
  public static final QueryField ID = field("Result", "id");
  public static final QueryField TIME_FINISHED = field("Result", "time_finished");
  public static final QueryField DATE = field("Result", "date");
  public static final QueryField ATTENDED_QUESTION = field("Result", "attended_question");
  public static final QueryField TOTAL_MARKS = field("Result", "total_marks");
  public static final QueryField ATTEMPT_NO = field("Result", "attempt_no");
  public static final QueryField RESULT_TOPIC_ID = field("Result", "resultTopicId");
  public static final QueryField RESULT_USER_ID = field("Result", "resultUserId");
  public static final QueryField RESULT_SUBJECT_ID = field("Result", "resultSubjectId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String time_finished;
  private final @ModelField(targetType="String") String date;
  private final @ModelField(targetType="Int") Integer attended_question;
  private final @ModelField(targetType="Int") Integer total_marks;
  private final @ModelField(targetType="Topic") @HasOne(associatedWith = "id", type = Topic.class) Topic Topic = null;
  private final @ModelField(targetType="User") @HasOne(associatedWith = "id", type = User.class) User User = null;
  private final @ModelField(targetType="Int") Integer attempt_no;
  private final @ModelField(targetType="Subject") @HasOne(associatedWith = "id", type = Subject.class) Subject Subject = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String resultTopicId;
  private final @ModelField(targetType="ID") String resultUserId;
  private final @ModelField(targetType="ID") String resultSubjectId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTimeFinished() {
      return time_finished;
  }
  
  public String getDate() {
      return date;
  }
  
  public Integer getAttendedQuestion() {
      return attended_question;
  }
  
  public Integer getTotalMarks() {
      return total_marks;
  }
  
  public Topic getTopic() {
      return Topic;
  }
  
  public User getUser() {
      return User;
  }
  
  public Integer getAttemptNo() {
      return attempt_no;
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
  
  public String getResultTopicId() {
      return resultTopicId;
  }
  
  public String getResultUserId() {
      return resultUserId;
  }
  
  public String getResultSubjectId() {
      return resultSubjectId;
  }
  
  private Result(String id, String time_finished, String date, Integer attended_question, Integer total_marks, Integer attempt_no, String resultTopicId, String resultUserId, String resultSubjectId) {
    this.id = id;
    this.time_finished = time_finished;
    this.date = date;
    this.attended_question = attended_question;
    this.total_marks = total_marks;
    this.attempt_no = attempt_no;
    this.resultTopicId = resultTopicId;
    this.resultUserId = resultUserId;
    this.resultSubjectId = resultSubjectId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Result result = (Result) obj;
      return ObjectsCompat.equals(getId(), result.getId()) &&
              ObjectsCompat.equals(getTimeFinished(), result.getTimeFinished()) &&
              ObjectsCompat.equals(getDate(), result.getDate()) &&
              ObjectsCompat.equals(getAttendedQuestion(), result.getAttendedQuestion()) &&
              ObjectsCompat.equals(getTotalMarks(), result.getTotalMarks()) &&
              ObjectsCompat.equals(getAttemptNo(), result.getAttemptNo()) &&
              ObjectsCompat.equals(getCreatedAt(), result.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), result.getUpdatedAt()) &&
              ObjectsCompat.equals(getResultTopicId(), result.getResultTopicId()) &&
              ObjectsCompat.equals(getResultUserId(), result.getResultUserId()) &&
              ObjectsCompat.equals(getResultSubjectId(), result.getResultSubjectId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTimeFinished())
      .append(getDate())
      .append(getAttendedQuestion())
      .append(getTotalMarks())
      .append(getAttemptNo())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getResultTopicId())
      .append(getResultUserId())
      .append(getResultSubjectId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Result {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("time_finished=" + String.valueOf(getTimeFinished()) + ", ")
      .append("date=" + String.valueOf(getDate()) + ", ")
      .append("attended_question=" + String.valueOf(getAttendedQuestion()) + ", ")
      .append("total_marks=" + String.valueOf(getTotalMarks()) + ", ")
      .append("attempt_no=" + String.valueOf(getAttemptNo()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("resultTopicId=" + String.valueOf(getResultTopicId()) + ", ")
      .append("resultUserId=" + String.valueOf(getResultUserId()) + ", ")
      .append("resultSubjectId=" + String.valueOf(getResultSubjectId()))
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
  public static Result justId(String id) {
    return new Result(
      id,
      null,
      null,
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
      time_finished,
      date,
      attended_question,
      total_marks,
      attempt_no,
      resultTopicId,
      resultUserId,
      resultSubjectId);
  }
  public interface BuildStep {
    Result build();
    BuildStep id(String id);
    BuildStep timeFinished(String timeFinished);
    BuildStep date(String date);
    BuildStep attendedQuestion(Integer attendedQuestion);
    BuildStep totalMarks(Integer totalMarks);
    BuildStep attemptNo(Integer attemptNo);
    BuildStep resultTopicId(String resultTopicId);
    BuildStep resultUserId(String resultUserId);
    BuildStep resultSubjectId(String resultSubjectId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String time_finished;
    private String date;
    private Integer attended_question;
    private Integer total_marks;
    private Integer attempt_no;
    private String resultTopicId;
    private String resultUserId;
    private String resultSubjectId;
    @Override
     public Result build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Result(
          id,
          time_finished,
          date,
          attended_question,
          total_marks,
          attempt_no,
          resultTopicId,
          resultUserId,
          resultSubjectId);
    }
    
    @Override
     public BuildStep timeFinished(String timeFinished) {
        this.time_finished = timeFinished;
        return this;
    }
    
    @Override
     public BuildStep date(String date) {
        this.date = date;
        return this;
    }
    
    @Override
     public BuildStep attendedQuestion(Integer attendedQuestion) {
        this.attended_question = attendedQuestion;
        return this;
    }
    
    @Override
     public BuildStep totalMarks(Integer totalMarks) {
        this.total_marks = totalMarks;
        return this;
    }
    
    @Override
     public BuildStep attemptNo(Integer attemptNo) {
        this.attempt_no = attemptNo;
        return this;
    }
    
    @Override
     public BuildStep resultTopicId(String resultTopicId) {
        this.resultTopicId = resultTopicId;
        return this;
    }
    
    @Override
     public BuildStep resultUserId(String resultUserId) {
        this.resultUserId = resultUserId;
        return this;
    }
    
    @Override
     public BuildStep resultSubjectId(String resultSubjectId) {
        this.resultSubjectId = resultSubjectId;
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
    private CopyOfBuilder(String id, String timeFinished, String date, Integer attendedQuestion, Integer totalMarks, Integer attemptNo, String resultTopicId, String resultUserId, String resultSubjectId) {
      super.id(id);
      super.timeFinished(timeFinished)
        .date(date)
        .attendedQuestion(attendedQuestion)
        .totalMarks(totalMarks)
        .attemptNo(attemptNo)
        .resultTopicId(resultTopicId)
        .resultUserId(resultUserId)
        .resultSubjectId(resultSubjectId);
    }
    
    @Override
     public CopyOfBuilder timeFinished(String timeFinished) {
      return (CopyOfBuilder) super.timeFinished(timeFinished);
    }
    
    @Override
     public CopyOfBuilder date(String date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder attendedQuestion(Integer attendedQuestion) {
      return (CopyOfBuilder) super.attendedQuestion(attendedQuestion);
    }
    
    @Override
     public CopyOfBuilder totalMarks(Integer totalMarks) {
      return (CopyOfBuilder) super.totalMarks(totalMarks);
    }
    
    @Override
     public CopyOfBuilder attemptNo(Integer attemptNo) {
      return (CopyOfBuilder) super.attemptNo(attemptNo);
    }
    
    @Override
     public CopyOfBuilder resultTopicId(String resultTopicId) {
      return (CopyOfBuilder) super.resultTopicId(resultTopicId);
    }
    
    @Override
     public CopyOfBuilder resultUserId(String resultUserId) {
      return (CopyOfBuilder) super.resultUserId(resultUserId);
    }
    
    @Override
     public CopyOfBuilder resultSubjectId(String resultSubjectId) {
      return (CopyOfBuilder) super.resultSubjectId(resultSubjectId);
    }
  }
  
}
