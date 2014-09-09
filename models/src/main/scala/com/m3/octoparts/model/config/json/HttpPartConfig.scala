package com.m3.octoparts.model.config.json

import com.m3.octoparts.model.HttpMethod
import com.wordnik.swagger.annotations.ApiModelProperty

import scala.annotation.meta.field
import scala.concurrent.duration.Duration

case class HttpPartConfig(
  @(ApiModelProperty @field)(required = true) partId: String,
  @(ApiModelProperty @field)(required = true) owner: String,
  @(ApiModelProperty @field)(required = true) uriToInterpolate: String,
  @(ApiModelProperty @field)(required = true) description: String,
  @(ApiModelProperty @field)(dataType = "string", allowableValues = "get, post, put, delete, head, patch, options") method: HttpMethod.Value,
  @(ApiModelProperty @field)(required = true) hystrixConfig: HystrixConfig,
  @(ApiModelProperty @field)(dataType = "array[integer]") additionalValidStatuses: Set[Int] = Set.empty,
  parameters: Set[PartParam] = Set.empty,
  @(ApiModelProperty @field)(dataType = "string", required = false) deprecatedInFavourOf: Option[String] = None,
  cacheGroups: Set[CacheGroup] = Set.empty,
  @(ApiModelProperty @field)(dataType = "integer", required = false, allowableValues = "range[0, Infinity]", value = "in ms") cacheTtl: Option[Duration] = Some(Duration.Zero),
  @(ApiModelProperty @field)(required = true) alertMailsEnabled: Boolean,
  @(ApiModelProperty @field)(dataType = "integer", required = false, allowableValues = "range[0, Infinity]") alertAbsoluteThreshold: Option[Int],
  @(ApiModelProperty @field)(dataType = "float", required = false, allowableValues = "range[0, 100]") alertPercentThreshold: Option[Double],
  @(ApiModelProperty @field)(dataType = "integer", required = true, allowableValues = "range[0, Infinity]", value = "in ms") alertInterval: Duration,
  @(ApiModelProperty @field)(dataType = "string", required = false) alertMailRecipients: Option[String])
