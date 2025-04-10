package com.portfolio.microservices.domain;

import java.util.List;
import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.portfolio.microservices.suprimeapi.model.Message;
import com.portfolio.microservices.suprimeapi.model.Profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Users")
public class UserDomain {

    @Id
    private String userId = null;
    private String username = null;

    public enum RoleEnum {
        USER("user"),

        EMPLOYEE("employee"),

        ADMIN("admin");

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        private String value;

        RoleEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static RoleEnum fromValue(String text) {
            for (RoleEnum b : RoleEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("role")
    private RoleEnum role = RoleEnum.USER;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("profile")
    @Valid
    private Profile profile = null;

    @JsonProperty("messages")
    @Valid
    private List<Message> messages = null;
}
