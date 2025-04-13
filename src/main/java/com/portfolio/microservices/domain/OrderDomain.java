package com.portfolio.microservices.domain;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Orders")
public class OrderDomain {

}