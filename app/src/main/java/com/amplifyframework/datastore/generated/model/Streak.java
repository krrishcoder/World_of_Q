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

/** This is an auto generated class representing the Streak type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Streaks", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class Streak implements Model {
  public static final QueryField ID = field("Streak", "id");
  public static final QueryField STREAK_COUNT = field("Streak", "streak_count");
  public static final QueryField STREAK_USER_ID = field("Streak", "streakUserId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Int") Integer streak_count;
  private final @ModelField(targetType="User") @HasOne(associatedWith = "id", type = User.class) User User = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  private final @ModelField(targetType="ID") String streakUserId;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public Integer getStreakCount() {
      return streak_count;
  }
  
  public User getUser() {
      return User;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  public String getStreakUserId() {
      return streakUserId;
  }
  
  private Streak(String id, Integer streak_count, String streakUserId) {
    this.id = id;
    this.streak_count = streak_count;
    this.streakUserId = streakUserId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Streak streak = (Streak) obj;
      return ObjectsCompat.equals(getId(), streak.getId()) &&
              ObjectsCompat.equals(getStreakCount(), streak.getStreakCount()) &&
              ObjectsCompat.equals(getCreatedAt(), streak.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), streak.getUpdatedAt()) &&
              ObjectsCompat.equals(getStreakUserId(), streak.getStreakUserId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getStreakCount())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .append(getStreakUserId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Streak {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("streak_count=" + String.valueOf(getStreakCount()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ")
      .append("streakUserId=" + String.valueOf(getStreakUserId()))
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
  public static Streak justId(String id) {
    return new Streak(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      streak_count,
      streakUserId);
  }
  public interface BuildStep {
    Streak build();
    BuildStep id(String id);
    BuildStep streakCount(Integer streakCount);
    BuildStep streakUserId(String streakUserId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private Integer streak_count;
    private String streakUserId;
    @Override
     public Streak build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Streak(
          id,
          streak_count,
          streakUserId);
    }
    
    @Override
     public BuildStep streakCount(Integer streakCount) {
        this.streak_count = streakCount;
        return this;
    }
    
    @Override
     public BuildStep streakUserId(String streakUserId) {
        this.streakUserId = streakUserId;
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
    private CopyOfBuilder(String id, Integer streakCount, String streakUserId) {
      super.id(id);
      super.streakCount(streakCount)
        .streakUserId(streakUserId);
    }
    
    @Override
     public CopyOfBuilder streakCount(Integer streakCount) {
      return (CopyOfBuilder) super.streakCount(streakCount);
    }
    
    @Override
     public CopyOfBuilder streakUserId(String streakUserId) {
      return (CopyOfBuilder) super.streakUserId(streakUserId);
    }
  }
  
}
