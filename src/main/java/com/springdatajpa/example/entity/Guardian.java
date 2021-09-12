package com.springdatajpa.example.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name = "name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name = "email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name = "mobile",
                column = @Column(name = "guardian_mobile")
        )
})
@Embeddable
public class Guardian {
    private String name;
    private String email;
    private long mobile;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Guardian guardian = (Guardian) o;
        return Objects.equals(name, guardian.name)
                && Objects.equals(email, guardian.email)
                && Objects.equals(mobile, guardian.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, mobile);
    }
}
