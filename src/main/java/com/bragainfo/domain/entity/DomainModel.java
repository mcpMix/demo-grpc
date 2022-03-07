package com.bragainfo.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@MappedSuperclass
public class DomainModel <T extends DomainModel<T>> implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  private DateModel dateModel;

  @Version
  private Integer version;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DateModel getDateModel() {
    return dateModel;
  }

  public void setDateModel(DateModel dateModel) {
    this.dateModel = dateModel;
  }

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  @PrePersist
  public void initializeDates() {
    LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT-3"));
    dateModel = new DateModel(now, now);
  }

  @PreUpdate
  public void updateUpdatedAt() {
    dateModel.withUpdatedAt(LocalDateTime.now(ZoneId.of("GMT-3")));
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_FIELD_NAMES_STYLE);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DomainModel<?> that = (DomainModel<?>) o;

    return new EqualsBuilder().append(id, that.id).append(dateModel, that.dateModel).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(id).append(dateModel).toHashCode();
  }


}
