package com.community.numble.app.user.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="ROLE_TABLE")
@Entity
public class RoleBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String role;

}
